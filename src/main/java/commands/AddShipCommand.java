package commands;

import dao.ShipsDAO;
import dao.UsersDAO;
import models.ships.Ship;
import models.users.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AddShipCommand implements Command {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final ShipsDAO shipsDAO = new ShipsDAO();


    public AddShipCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() throws IOException, ServletException {
        if (request.getParameter("name") != null
                && request.getParameter("capacity") != null && request.getParameter("current_point") != null) {

            String name = request.getParameter("name");
            int capacity = Integer.parseInt(request.getParameter("capacity"));
            String currentPoint = request.getParameter("current_point");

            if (!shipsDAO.shipsNameExist(name)) {
                shipsDAO.insertShip(new Ship(name, capacity, currentPoint));
                setError(((Map<?, ?>) request.getAttribute("phrases")).get("langSuccessfulAdd").toString());
            } else setError(((Map<?, ?>) request.getAttribute("phrases")).get("langAlreadyExist").toString());
        } else
            setError(((Map<?, ?>) request.getAttribute("phrases")).get("langInvalidData").toString());
        request.getRequestDispatcher("/admin/add_ship.jsp").forward(request, response);
    }

    private void setError(String string) {
        request.setAttribute("error_message", string);
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("admin/add_ship") && method.equalsIgnoreCase("Post");
    }
}

