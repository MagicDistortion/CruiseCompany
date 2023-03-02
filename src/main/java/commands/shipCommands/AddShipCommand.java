package commands.shipCommands;

import commands.Command;
import dao.ShipsDAO;
import models.Ship;
import utils.RequestAssistant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class AddShipCommand implements Command {
    private final ShipsDAO shipsDAO = new ShipsDAO();
    private static final String SAVE_DIR = "images";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestAssistant requestAssistant = new RequestAssistant();
        if (request.getMethod().equalsIgnoreCase("get")) {
            response.sendRedirect("add_ship.jsp");
            return;
        }
        if (request.getParameter("name") == null || request.getParameter("capacity") == null) {
            requestAssistant.setError(request, "langInvalidData");
            request.getRequestDispatcher("/admin/add_ship.jsp").forward(request, response);
            return;
        }

        String savePath = request.getServletContext().getRealPath("") + File.separator + SAVE_DIR;
        String name = request.getParameter("name");
        int capacity = Integer.parseInt(request.getParameter("capacity"));

        for (Part part : request.getParts()) {
            String fileName = name + ".jpeg";
            fileName = new File(fileName).getName();
            part.write(savePath + File.separator + fileName);
        }
        if (shipsDAO.shipsNameExist(name)) {
            requestAssistant.setError(request, "langAlreadyExist");
            request.getRequestDispatcher("/admin/add_ship.jsp").forward(request, response);
            return;
        }
        shipsDAO.insertShip(new Ship(name, capacity));
        response.sendRedirect("add_ship.jsp");
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("admin/add_ship");
    }
}

