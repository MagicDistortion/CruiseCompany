package commands;

import dao.CruisesDAO;
import dao.ShipsDAO;
import models.ships.Ship;
import utils.WithRequestHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ShipsForAddCruiseCommand implements Command {
    private final ShipsDAO shipsDAO = new ShipsDAO();
    private final CruisesDAO cruisesDAO = new CruisesDAO();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Ship> shipList = new ArrayList<>();
        WithRequestHelper withRequestHelper = new WithRequestHelper();
        if (request.getParameter("startTime") == null || request.getParameter("endTime") == null) {
            withRequestHelper.setError(request, "langInvalidData");
            request.getRequestDispatcher("add_cruise.jsp").forward(request, response);
            return;
        }
        LocalDateTime startTime = LocalDateTime.parse(request.getParameter("startTime"));
        LocalDateTime endTime = LocalDateTime.parse(request.getParameter("endTime"));
        request.setAttribute("startTime", startTime);
        request.setAttribute("endTime", endTime);
        if (endTime.isBefore(startTime)) {
            withRequestHelper.setError(request, "langInvalidData");
            request.getRequestDispatcher("add_cruise.jsp").forward(request, response);
            return;
        }

        for (Ship ship : shipsDAO.findAllShips()) {
            if (cruisesDAO.checkingTheShipIsFreeOnDates(ship.getId(), startTime, endTime)) {
                shipList.add(ship);
            }
        }
        request.setAttribute("shipsList", shipList);
        request.getRequestDispatcher("add_cruise.jsp").forward(request, response);
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("admin/ships_for_add_cruise") && method.equalsIgnoreCase("Get");
    }
}

