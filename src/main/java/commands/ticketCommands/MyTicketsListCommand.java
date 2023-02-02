package commands.ticketCommands;

import commands.Command;
import dao.TicketsDAO;
import models.tickets.Ticket;
import models.users.User;
import utils.RequestAssistant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MyTicketsListCommand implements Command {
    private final TicketsDAO ticketsDAO = new TicketsDAO();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String sort = "not paid";
        RequestAssistant requestAssistant = new RequestAssistant();
        if (request.getParameter("sort") != null) sort = request.getParameter("sort");
        request.setAttribute("sort", sort);
        switch (sort) {
            case "not paid":
                sort = "and tickets.status='not paid' and cruise.status='didn`t start'";
                break;
            case "paid":
                sort = "and (tickets.status='paid' or tickets.status='confirmed') and cruise.status!='completed'";
                break;
            case "rejected":
                sort = "and tickets.status='rejected' and cruise.status='didn`t start'";
        }
        int userId = ((User) request.getSession().getAttribute("user")).getId();
        List<Ticket> ticketList = ticketsDAO.findTicketsByUserId(userId, sort);
        request.setAttribute("my_tickets_list", ticketList);
        if (ticketList.isEmpty()) requestAssistant.setError(request, "langEmpty");
        request.getRequestDispatcher("my_profile.jsp").forward(request, response);
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("passenger/my_profile") && method.equalsIgnoreCase("get");
    }
}

