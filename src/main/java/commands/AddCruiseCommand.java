package commands;

import dao.CruisesDAO;
import dao.ShipsDAO;
import models.cruises.Cruise;
import models.ships.Ship;
import org.w3c.dom.ls.LSOutput;
import services.CruiseValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class AddCruiseCommand implements Command {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final ShipsDAO shipsDAO = new ShipsDAO();
    private final CruisesDAO cruisesDAO = new CruisesDAO();

    public AddCruiseCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() throws IOException, ServletException {
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
            cruisesDAO.insertCruise(cruise);
            errors.add(((Map<?, ?>) request.getAttribute("phrases")).get("langSuccessfulAdd").toString());
        }
        request.setAttribute("errors", errors);
        request.getRequestDispatcher("add_cruise.jsp").forward(request, response);
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("admin/add_cruise") && method.equalsIgnoreCase("Post");
    }
}
