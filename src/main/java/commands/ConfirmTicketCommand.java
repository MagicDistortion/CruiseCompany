package commands;

import dao.DBManager;
import dao.TicketsDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfirmTicketCommand implements Command {
    private final TicketsDAO ticketsDAO = new TicketsDAO();
    private final DBManager dbManager = DBManager.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getMethod().equalsIgnoreCase("post")) {
            if (request.getParameter("ticket_id") != null) {
                dbManager.inTransaction(connection -> {
                    ticketsDAO.updateStatus(connection, "confirm", Integer.parseInt(request.getParameter("ticket_id")));
                });
            }
        }
        response.sendRedirect("paid_tickets");
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("admin/confirm_ticket");
    }
}

