package commands;

import dao.ShipsDAO;
import models.ships.Ship;
import services.Paginator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShipsListCommand implements Command {
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    private final Paginator paginator = new Paginator();

    public ShipsListCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() throws IOException, ServletException {
        List<Ship> shipList = paginator.paginationShipsList(request);
        request.setAttribute("shipsList", shipList);
        request.getRequestDispatcher("ships.jsp").forward(request, response);
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("ships_list") && method.equalsIgnoreCase("Get");
    }
}

