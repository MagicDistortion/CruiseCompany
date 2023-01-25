package commands;

import dao.TicketsDAO;
import models.tickets.Ticket;
import services.TicketDownloadManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DownloadTicketCommand implements Command {
    private final TicketsDAO ticketsDAO = new TicketsDAO();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Ticket ticket = ticketsDAO.findTicketById(Integer.parseInt(request.getParameter("ticket_id")));
        String fileName = ticket.getCruiseName() + ticket.getId()+".pdf";
        response.setHeader("Content-Type", "application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        TicketDownloadManager ticketDownloadManager = new TicketDownloadManager();
        ticketDownloadManager.run(ticket, response);
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("passenger/download_ticket") && method.equalsIgnoreCase("Get");
    }
}
