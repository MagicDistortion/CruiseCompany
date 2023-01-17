package commands;

import dao.CruisesDAO;
import dao.ShipsDAO;
import models.cruises.Cruise;
import models.ships.Ship;
import services.CruiseValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class AddCruiseCommand implements Command {
    private final ShipsDAO shipsDAO = new ShipsDAO();
    private final CruisesDAO cruisesDAO = new CruisesDAO();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getMethod().equalsIgnoreCase("post")) {
            CruiseValidator cruiseValidator = new CruiseValidator();
            int shipId = Integer.parseInt(request.getParameter("shipId"));
            Ship ship = shipsDAO.findShipByID(shipId);
            String shipName = ship.getName();
            List<String> errors = cruiseValidator.validate(request, shipName);
            if (errors.isEmpty()) {
                Cruise cruise = new Cruise(shipId
                        , shipName
                        , request.getParameter("cruiseName")
                        , Integer.parseInt(request.getParameter("numberOfPorts"))
                        , Double.parseDouble(request.getParameter("price"))
                        , LocalDateTime.parse(request.getParameter("startTime"))
                        , LocalDateTime.parse(request.getParameter("endTime")));
                cruise.setDescription(request.getParameter("description"));
                cruisesDAO.insertCruise(cruise);
                errors.add(((Map<?, ?>) request.getAttribute("phrases")).get("langSuccessfulAdd").toString());
            }
            request.setAttribute("errors", errors);
        }
        request.getRequestDispatcher("ships_for_add_cruise").forward(request, response);
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("admin/add_cruise");
    }
}
