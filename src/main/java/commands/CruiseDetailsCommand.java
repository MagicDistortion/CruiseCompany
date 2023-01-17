package commands;

import dao.CruisesDAO;
import models.cruises.Cruise;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CruiseDetailsCommand implements Command {
    private final CruisesDAO cruisesDAO = new CruisesDAO();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("cruiseId") != null) {
            Cruise cruise = cruisesDAO.findCruiseByID(Integer.parseInt(request.getParameter("cruiseId")));
            request.setAttribute("cruise", cruise);
        }
        request.getRequestDispatcher("cruise_details.jsp").forward(request, response);
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("cruise_details") && method.equalsIgnoreCase("Get");
    }
}
