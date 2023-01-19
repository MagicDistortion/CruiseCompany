package commands;

import models.cruises.Cruise;
import services.Paginator;
import utils.WithRequestHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CruisesListCommand implements Command {
    private final Paginator paginator = new Paginator();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        WithRequestHelper withRequestHelper = new WithRequestHelper();
        List<Cruise> cruiseList = paginator.paginationCruisesList(request);
        request.setAttribute("cruisesList", cruiseList);
        if (cruiseList.isEmpty()) withRequestHelper.setError(request, "langEmpty");
        request.getRequestDispatcher("cruises_list.jsp").forward(request, response);
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("cruises_list") && method.equalsIgnoreCase("Get");
    }
}

