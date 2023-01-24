package commands;

import services.TicketDownloadManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DownloadTicketCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TicketDownloadManager ticketDownloadManager = new TicketDownloadManager();
        ticketDownloadManager.run(request);
        response.sendRedirect("my_profile");
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("passenger/download_ticket")&&method.equalsIgnoreCase("Get");
    }
}
