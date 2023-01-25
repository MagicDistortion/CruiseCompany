package commands;

import dao.StaffsDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AssignShipCommand implements Command {
    private final StaffsDAO staffsDAO = new StaffsDAO();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getMethod().equalsIgnoreCase("get")
                || request.getParameter("shipId") == null
                || request.getParameter("userId") == null) {
            response.sendRedirect("users_list");
            return;
        }
        int userId = Integer.parseInt(request.getParameter("userId"));
        int shipId = Integer.parseInt(request.getParameter("shipId"));
        staffsDAO.insertStaff(userId, shipId);
        response.sendRedirect("users_list");
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("admin/assign_a_ship");
    }
}

