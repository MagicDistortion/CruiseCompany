package commands.shipCommands;

import commands.Command;
import models.Ship;
import services.Paginator;
import utils.RequestAssistant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShipsListCommand implements Command {
    private final Paginator paginator = new Paginator();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestAssistant requestAssistant = new RequestAssistant();
        List<Ship> shipList = paginator.paginationShipsList(request);
        request.setAttribute("shipsList", shipList);
        if (shipList.isEmpty()) requestAssistant.setError(request, "langEmpty");
        request.getRequestDispatcher("ships_list.jsp").forward(request, response);
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("ships_list") && method.equalsIgnoreCase("Get");
    }
}

