package commands;

import dao.CruisesDAO;
import models.cruises.Cruise;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetCruisesCommand implements Command {
    private final CruisesDAO cruisesDAO = new CruisesDAO();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getMethod().equalsIgnoreCase("post")) {
            request.setAttribute("sortBy", request.getParameter("sortBy"));
            List<Cruise> cruiseList = new ArrayList<>();
            if (request.getParameter("date") != null) {
                LocalDate date = LocalDate.parse(request.getParameter("date"));
                request.setAttribute("date", date);
                cruiseList = cruisesDAO.findCruisesListByDate(date);
            } else if (request.getParameter("duration") != null) {
                int duration = Integer.parseInt(request.getParameter("duration"));
                request.setAttribute("duration", duration);
                cruiseList = cruisesDAO.findCruisesListByDuration(duration);
            }
            request.setAttribute("cruisesList", cruiseList);
            if (cruiseList.isEmpty())
                request.setAttribute("error_message", ((Map<?, ?>) request.getAttribute("phrases")).get("langEmpty"));
        }
        request.getRequestDispatcher("buy_a_ticket.jsp").forward(request, response);
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("passenger/getCruises");
    }
}

