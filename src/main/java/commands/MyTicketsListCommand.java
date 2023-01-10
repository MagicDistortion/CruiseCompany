package commands;

import dao.TicketsDAO;
import models.tickets.Ticket;
import models.users.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MyTicketsListCommand implements Command {
    private final TicketsDAO ticketsDAO = new TicketsDAO();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int userId = ((User) request.getSession().getAttribute("user")).getId();
        List<Ticket> ticketList = ticketsDAO.findTicketByUserId(userId);
        request.setAttribute("my_tickets_list", ticketList);
        request.getRequestDispatcher("my_profile.jsp").forward(request, response);
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("passenger/my_profile") && method.equalsIgnoreCase("get");
    }
}

