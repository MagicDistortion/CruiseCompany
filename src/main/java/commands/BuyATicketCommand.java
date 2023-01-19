package commands;

import dao.CruisesDAO;
import dao.ShipsDAO;
import dao.TicketsDAO;
import models.cruises.Cruise;
import models.ships.Ship;
import models.tickets.Ticket;
import models.users.User;
import utils.WithRequestHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

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

        User user = (User) request.getSession().getAttribute("user");
        Cruise cruise = cruisesDAO.findCruiseByID(Integer.parseInt(request.getParameter("cruiseId")));
        int amount = Integer.parseInt(request.getParameter("amount"));
        Ticket ticket = new Ticket(cruise, user.getId(), amount);
        Ship ship = shipsDAO.findShipByID(cruise.getShipId());
        WithRequestHelper withRequestHelper = new WithRequestHelper();

        if (!ticketsDAO.availabilityCheck(ship, cruise.getId(), amount)) {
            withRequestHelper.setError(request, "langNotEnoughTickets");
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

