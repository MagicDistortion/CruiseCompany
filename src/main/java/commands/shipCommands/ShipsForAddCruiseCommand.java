package commands.shipCommands;

import commands.Command;
import dao.CruisesDAO;
import dao.RouteDAO;
import dao.ShipsDAO;
import models.route.Route;
import models.ships.Ship;
import utils.RequestAssistant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ShipsForAddCruiseCommand implements Command {
    private final ShipsDAO shipsDAO = new ShipsDAO();
    private final CruisesDAO cruisesDAO = new CruisesDAO();
    private final RouteDAO routeDAO = new RouteDAO();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestAssistant requestAssistant = new RequestAssistant();
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        request.setAttribute("startTime", startTime);
        request.setAttribute("endTime", endTime);
        if (startTime == null || endTime == null || LocalDateTime.parse(endTime).isBefore(LocalDateTime.parse(startTime))
                || LocalDateTime.parse(startTime).isBefore(LocalDateTime.now())) {
            requestAssistant.setError(request, "langInvalidData");
            request.getRequestDispatcher("add_cruise.jsp").forward(request, response);
            return;
        }
        List<Ship> shipList = shipsDAO.findAllShips().stream().filter(ship ->
                cruisesDAO.checkingTheShipIsFreeOnDates(ship.getId(), startTime, endTime)).collect(Collectors.toList());
        List<Route> routeList = routeDAO.findAllRoutes();
        request.setAttribute("shipsList", shipList);
        request.setAttribute("routeList", routeList);
        request.getRequestDispatcher("add_cruise.jsp").forward(request, response);
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("admin/ships_for_add_cruise") && method.equalsIgnoreCase("Get");
    }
}

