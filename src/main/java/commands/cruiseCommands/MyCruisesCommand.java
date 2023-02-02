package commands.cruiseCommands;

import commands.Command;
import dao.CruisesDAO;
import dao.StaffsDAO;
import models.cruises.Cruise;
import models.users.User;
import utils.RequestAssistant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MyCruisesCommand implements Command {
    private final CruisesDAO cruisesDAO = new CruisesDAO();
    private final StaffsDAO staffsDAO = new StaffsDAO();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestAssistant requestAssistant = new RequestAssistant();
        User user = (User) request.getSession().getAttribute("user");
        List<Cruise> cruisesList= cruisesDAO.findCruisesByShipID(staffsDAO.findShipByStaffId(user.getId()));
        request.setAttribute("cruisesList",cruisesList);
        if (cruisesList.isEmpty()) requestAssistant.setError(request, "langEmpty");
        request.getRequestDispatcher("my_cruises.jsp").forward(request, response);
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("staff/my_cruises") && method.equalsIgnoreCase("Get");
    }
}
