package commands.ticketCommands;

import commands.Command;
import dao.DBManager;
import dao.TicketsDAO;
import models.tickets.Ticket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RefuseTicketCommand implements Command {
    private final TicketsDAO ticketsDAO = new TicketsDAO();
    private final DBManager dbManager = DBManager.getInstance();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getMethod().equalsIgnoreCase("get") || request.getParameter("ticket_id") == null) {
            response.sendRedirect("my_profile");
            return;
        }
        dbManager.inTransaction(connection -> {
            Ticket ticket = ticketsDAO.findTicketById(Integer.parseInt(request.getParameter("ticket_id")));
            if (ticket.getStatus().equals("not paid"))
                ticketsDAO.updateStatus(connection, "rejected", ticket.getId());
        });
        response.sendRedirect("my_profile");
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("passenger/refuse_ticket");
    }
}

