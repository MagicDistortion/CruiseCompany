package commands;

import dao.CruisesDAO;
import dao.ShipsDAO;
import dao.TicketsDAO;
import models.cruises.Cruise;
import models.ships.Ship;
import models.tickets.Ticket;
import models.users.User;

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
        if (request.getMethod().equalsIgnoreCase("post")) {
            User user = (User) request.getSession().getAttribute("user");
            Cruise cruise = cruisesDAO.findCruiseByID(Integer.parseInt(request.getParameter("cruiseId")));
            int amount = Integer.parseInt(request.getParameter("amount"));
            Ticket ticket = new Ticket(cruise, user.getId(), amount);
            Ship ship = shipsDAO.findShipByID(cruise.getShipId());
            Map<?, ?> phrases = (Map<?, ?>) request.getAttribute("phrases");
            if (ticketsDAO.checkingForAvailability(ship, cruise.getId(), amount)) {
                ticketsDAO.insertTicket(ticket);
                request.setAttribute("error_message", phrases.get("langSuccessfulAdd"));
            } else request.setAttribute("error_message", phrases.get("langNotEnoughTickets"));
        }
        request.getRequestDispatcher("buy_a_ticket.jsp").forward(request, response);
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("passenger/buy_a_ticket");
    }
}

