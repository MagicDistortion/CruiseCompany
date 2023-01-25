package commands;

import dao.ShipsDAO;
import models.ships.Ship;
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
    private final ShipsDAO shipsDAO = new ShipsDAO();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestAssistant requestAssistant = new RequestAssistant();
        List<User> allUsers = usersDAO.findUsersWithOutRole();
        List<User> staffList = usersDAO.findStaff();
        List<Ship> shipsList = shipsDAO.findAllShips();
        request.setAttribute("userList", allUsers);
        request.setAttribute("staffList", staffList);
        request.setAttribute("shipsList", shipsList);

        if (allUsers.size() == 0)
            request.setAttribute("users_List_error", (requestAssistant.getPhrase(request, "langEmpty")));
        if (staffList.size() == 0)
            request.setAttribute("staff_List_error", (requestAssistant.getPhrase(request, "langEmpty")));

        request.getRequestDispatcher("/admin/giving_a_role.jsp").forward(request, response);
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("admin/users_list") && method.equalsIgnoreCase("Get");
    }
}

