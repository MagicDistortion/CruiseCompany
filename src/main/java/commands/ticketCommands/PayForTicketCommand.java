package commands.ticketCommands;

import commands.Command;
import dao.*;
import models.Cruise;
import models.Ship;
import models.Ticket;
import models.User;
import utils.RequestAssistant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class PayForTicketCommand implements Command {
    private final Map<Integer, ReentrantLock> CRUISE_LOCKS = new ConcurrentHashMap<>();
    private final TicketsDAO ticketsDAO = new TicketsDAO();
    private final UsersDAO usersDAO = new UsersDAO();
    private final DBManager dbManager = DBManager.getInstance();
    private final ShipsDAO shipsDAO = new ShipsDAO();
    private final CruisesDAO cruisesDAO = new CruisesDAO();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getMethod().equalsIgnoreCase("get") || request.getParameter("ticket_id") == null) {
            response.sendRedirect("my_profile");
            return;
        }
        User user = (User) request.getSession().getAttribute("user");
        Ticket ticket = ticketsDAO.findTicketById(Integer.parseInt(request.getParameter("ticket_id")));
        RequestAssistant requestAssistant = new RequestAssistant();
        ReentrantLock lock = CRUISE_LOCKS.computeIfAbsent(ticket.getCruiseId(), (Integer id) -> new ReentrantLock());

        try {
            if (!lock.tryLock(30, TimeUnit.SECONDS)) {
                response.sendRedirect("my_profile");
                return;
            }
        } catch (InterruptedException e) {
            response.sendRedirect("my_profile");
            return;
        }

        double money = user.getMoney();
        if (!(money >= ticket.getTotalPrice())) {
            requestAssistant.setError(request, "langNotEnoughMoney");
            request.getRequestDispatcher("my_profile.jsp").forward(request, response);
            return;
        }
        Cruise cruise = cruisesDAO.findCruiseByID(ticket.getCruiseId());
        Ship ship = shipsDAO.findShipByID(cruise.getShipId());
        if (!ticketsDAO.availabilityCheck(ship, ticket.getCruiseId(), ticket.getNumberOfPassengers())) {
            requestAssistant.setError(request, "langNotEnoughTickets");
            request.getRequestDispatcher("my_profile.jsp").forward(request, response);
            return;
        }
        dbManager.inTransaction(connection -> {
            ticketsDAO.updateStatus(connection, "paid", ticket.getId());
            usersDAO.updateUserMoney(connection, ticket.getTotalPrice(), user.getId(), "-");
            user.setMoney(money - ticket.getTotalPrice());
        });

        lock.unlock();
        response.sendRedirect("my_profile");
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("passenger/pay_for_ticket");
    }
}

