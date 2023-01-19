package commands;

import models.users.User;
import dao.UsersDAO;
import utils.RequestAssistant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UsersListCommand implements Command {
    private final UsersDAO usersDAO = new UsersDAO();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<User> allUsers = usersDAO.findUsersWithOutRole();
        request.setAttribute("userList", allUsers);
        if (allUsers.size() == 0){
            RequestAssistant requestAssistant = new RequestAssistant();
            requestAssistant.setError(request, "langEmpty");
        }
        request.getRequestDispatcher("/admin/giving_a_role.jsp").forward(request, response);
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("admin/users_list") && method.equalsIgnoreCase("Get");
    }
}

