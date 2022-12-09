package commands;

import dao.CruisesDAO;
import dao.ShipsDAO;
import models.cruises.Cruise;
import models.ships.Ship;
import org.w3c.dom.ls.LSOutput;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        if (request.getParameter("shipId") != null
                && request.getParameter("cruiseName") != null
                && request.getParameter("numberOfPorts") != null
                && request.getParameter("price") != null
                && request.getParameter("startTime") != null
                && request.getParameter("endTime") != null) {

            int shipId = Integer.parseInt(request.getParameter("shipId"));
            Ship ship = shipsDAO.findShipByID(shipId);
            String shipName = ship.getName();
            String cruiseName = request.getParameter("cruiseName");
            int numbersOfPort = Integer.parseInt(request.getParameter("numberOfPorts"));
            double price = Double.parseDouble(request.getParameter("price"));
            LocalDateTime startTime = LocalDateTime.parse(request.getParameter("startTime"));
            LocalDateTime endTime = LocalDateTime.parse(request.getParameter("endTime"));

            if (!cruisesDAO.cruiseNameExist(cruiseName)) {
                Cruise cruise = new Cruise(shipId, shipName, cruiseName, numbersOfPort, price, startTime, endTime);
                cruisesDAO.insertCruise(cruise);
                setError(((Map<?, ?>) request.getAttribute("phrases")).get("langSuccessfulAdd").toString());
            } else setError(((Map<?, ?>) request.getAttribute("phrases")).get("langAlreadyExist").toString());
        } else
            setError(((Map<?, ?>) request.getAttribute("phrases")).get("langInvalidData").toString());
        request.getRequestDispatcher("add_cruise.jsp").forward(request, response);
    }

    private void setError(String string) {
        request.setAttribute("error_message", string);
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("admin/add_cruise") && method.equalsIgnoreCase("Post");
    }
}
