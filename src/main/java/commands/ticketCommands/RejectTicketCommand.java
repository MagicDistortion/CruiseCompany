package commands.ticketCommands;

import commands.Command;
import dao.DBManager;
import dao.TicketsDAO;
import dao.UsersDAO;
import models.Ticket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RejectTicketCommand implements Command {
    private final TicketsDAO ticketsDAO = new TicketsDAO();
    private final DBManager dbManager = DBManager.getInstance();
    private final UsersDAO usersDAO = new UsersDAO();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getMethod().equalsIgnoreCase("get") || request.getParameter("ticket_id") == null) {
            response.sendRedirect("paid_tickets");
            return;
        }
        dbManager.inTransaction(connection -> {
            Ticket ticket = ticketsDAO.findTicketById(Integer.parseInt(request.getParameter("ticket_id")));
            ticketsDAO.updateStatus(connection, "rejected", ticket.getId());
            usersDAO.updateUserMoney(connection, ticket.getTotalPrice(), ticket.getUserId(),"+");
        });
        response.sendRedirect("paid_tickets");
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("admin/reject_ticket");
    }
}

