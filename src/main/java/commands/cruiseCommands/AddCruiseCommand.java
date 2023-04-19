package commands.cruiseCommands;

import commands.Command;
import dao.CruisesDAO;
import dao.RouteDAO;
import dao.ShipsDAO;
import models.Cruise;
import models.Route;
import models.Ship;
import services.CruiseValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class AddCruiseCommand implements Command {
    private final ShipsDAO shipsDAO = new ShipsDAO();
    private final CruisesDAO cruisesDAO = new CruisesDAO();
    private final RouteDAO routeDAO = new RouteDAO();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getMethod().equalsIgnoreCase("get")) {
            response.sendRedirect("add_cruise.jsp");
            return;
        }
        CruiseValidator cruiseValidator = new CruiseValidator();
        Ship ship = shipsDAO.findShipByID(Integer.parseInt(request.getParameter("shipId")));
        Route route = routeDAO.findRouteByID(Integer.parseInt(request.getParameter("routeId")));
        List<String> errors = cruiseValidator.validate(request, route.getPorts().size());
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("add_cruise.jsp").forward(request, response);
            return;
        }
        Cruise cruise = new Cruise(ship.getId()
                , route.getId()
                , ship.getName()
                , request.getParameter("cruiseName")
                , route.getPorts().size()
                , Double.parseDouble(request.getParameter("price"))
                , LocalDateTime.parse(request.getParameter("startTime"))
                , LocalDateTime.parse(request.getParameter("endTime")));
        cruise.setDescription(request.getParameter("description"));
        cruisesDAO.insertCruise(cruise);
        response.sendRedirect("add_cruise.jsp");
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("admin/add_cruise");
    }
}
