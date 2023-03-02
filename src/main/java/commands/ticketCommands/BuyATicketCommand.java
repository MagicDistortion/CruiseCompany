package commands.ticketCommands;

import commands.Command;
import dao.CruisesDAO;
import dao.ShipsDAO;
import dao.TicketsDAO;
import models.Cruise;
import models.Ship;
import models.Ticket;
import models.User;
import utils.RequestAssistant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BuyATicketCommand implements Command {
    private final CruisesDAO cruisesDAO = new CruisesDAO();
    private final TicketsDAO ticketsDAO = new TicketsDAO();
    private final ShipsDAO shipsDAO = new ShipsDAO();


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getMethod().equalsIgnoreCase("get")) {
            response.sendRedirect("buy_a_ticket.jsp");
            return;
        }
        RequestAssistant requestAssistant = new RequestAssistant();
        int amount = Integer.parseInt(request.getParameter("amount"));
        if (amount <= 0) {
            requestAssistant.setError(request, "langInvalidData");
            request.getRequestDispatcher("buy_a_ticket.jsp").forward(request, response);
            return;
        }
        User user = (User) request.getSession().getAttribute("user");
        Cruise cruise = cruisesDAO.findCruiseByID(Integer.parseInt(request.getParameter("cruiseId")));
        Ticket ticket = new Ticket(cruise, user.getId(), amount);
        Ship ship = shipsDAO.findShipByID(cruise.getShipId());
        if (!ticketsDAO.availabilityCheck(ship, cruise.getId(), amount)) {
            requestAssistant.setError(request, "langNotEnoughTickets");
            request.getRequestDispatcher("buy_a_ticket.jsp").forward(request, response);
            return;
        }
        ticketsDAO.insertTicket(ticket);
        response.sendRedirect("buy_a_ticket.jsp");
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("passenger/buy_a_ticket");
    }
}

