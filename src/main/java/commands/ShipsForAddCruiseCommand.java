package commands;

import dao.ShipsDAO;
import models.ships.Ship;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShipsForAddCruiseCommand implements Command {
    private final ShipsDAO shipsDAO = new ShipsDAO();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Ship> shipsList = shipsDAO.findAllShips();
        request.setAttribute("shipsList", shipsList);
        request.getRequestDispatcher("add_cruise.jsp").forward(request, response);
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("admin/ships_for_add_cruise");
    }
}

