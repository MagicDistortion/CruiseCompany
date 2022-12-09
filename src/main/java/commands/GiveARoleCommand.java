package commands;

import dao.UsersDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GiveARoleCommand implements Command {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final UsersDAO usersDAO = new UsersDAO();

    public GiveARoleCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() throws IOException, ServletException {
        int role = Integer.parseInt(request.getParameter("role"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        if (role >= 1 && role <= 3 && userId != 0)
            usersDAO.updateUserRole(role, userId);
        response.sendRedirect("users_list");
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("admin/give_a_role") && method.equalsIgnoreCase("Post");
    }
}
