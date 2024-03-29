package commands.userCommands;

import commands.Command;
import dao.UsersDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GiveARoleCommand implements Command {
    private final UsersDAO usersDAO = new UsersDAO();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getMethod().equalsIgnoreCase("get")
                || request.getParameter("role") == null
                || request.getParameter("userId") == null) {
            response.sendRedirect("users_list");
            return;
        }
        int role = Integer.parseInt(request.getParameter("role"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        if (role >= 1 && role <= 3 && userId != 0) usersDAO.updateUserRole(role, userId);
        response.sendRedirect("users_list");
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("admin/give_a_role");
    }
}

