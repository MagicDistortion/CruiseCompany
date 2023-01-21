package commands;

import dao.TicketsDAO;

import models.tickets.Ticket;
import utils.RequestAssistant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PaidTicketsCommand implements Command {
    private final TicketsDAO ticketsDAO = new TicketsDAO();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestAssistant requestAssistant = new RequestAssistant();
        List<Ticket> allPaidTickets = ticketsDAO.findAllPaidTickets();
        request.setAttribute("all_paid_tickets", allPaidTickets);
        if (allPaidTickets.isEmpty()) requestAssistant.setError(request, "langEmpty");
        request.getRequestDispatcher("confirm_tickets.jsp").forward(request,response);
    }

    public boolean canHandle(String uri, String method) {

        return uri.equalsIgnoreCase("admin/paid_tickets") && method.equalsIgnoreCase("Get");
    }
}
