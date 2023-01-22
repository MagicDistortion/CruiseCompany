package commands;

import dao.RouteDAO;
import dao.ShipsDAO;
import models.route.Route;
import models.ships.Ship;
import utils.RequestAssistant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class AddRouteCommand implements Command {
    private final ShipsDAO shipsDAO = new ShipsDAO();
    private final RouteDAO routeDAO = new RouteDAO();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestAssistant requestAssistant = new RequestAssistant();
        if (request.getMethod().equalsIgnoreCase("get")) {
            response.sendRedirect("add_route.jsp");
            return;
        }
        String name = request.getParameter("name");
        String route = request.getParameter("route");
        if (name == null || route == null) {
            requestAssistant.setError(request, "langInvalidData");
            request.getRequestDispatcher("/admin/add_route.jsp").forward(request, response);
            return;
        }
        if (routeDAO.routeNameExist(name)) {
            requestAssistant.setError(request, "langAlreadyExist");
            request.getRequestDispatcher("/admin/add_route.jsp").forward(request, response);
            return;
        }
        routeDAO.insertRoute(new Route(name, route));
        response.sendRedirect("add_route.jsp");
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("admin/add_route");
    }
}

