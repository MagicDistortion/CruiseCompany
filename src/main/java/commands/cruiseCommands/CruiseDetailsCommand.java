package commands.cruiseCommands;

import commands.Command;
import dao.CruisesDAO;
import dao.RouteDAO;
import models.Cruise;
import models.Route;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CruiseDetailsCommand implements Command {
    private final CruisesDAO cruisesDAO = new CruisesDAO();
    private final RouteDAO routeDAO = new RouteDAO();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("cruiseId") != null) {
            Cruise cruise = cruisesDAO.findCruiseByID(Integer.parseInt(request.getParameter("cruiseId")));
            Route route = routeDAO.findRouteByID(cruise.getRouteId());
            request.setAttribute("cruise", cruise);
            request.setAttribute("route", route);
        }
        request.getRequestDispatcher("cruise_details.jsp").forward(request, response);
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("cruise_details") && method.equalsIgnoreCase("Get");
    }
}
