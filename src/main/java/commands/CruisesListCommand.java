package commands;

import models.cruises.Cruise;
import models.ships.Ship;
import services.Paginator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CruisesListCommand implements Command {
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    private final Paginator paginator = new Paginator();

    public CruisesListCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() throws IOException, ServletException {
        List<Cruise> cruiseList = paginator.paginationCruisesList(request);
        request.setAttribute("cruisesList", cruiseList);
        request.getRequestDispatcher("cruises_list.jsp").forward(request, response);
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("cruises_list") && method.equalsIgnoreCase("Get");
    }
}

