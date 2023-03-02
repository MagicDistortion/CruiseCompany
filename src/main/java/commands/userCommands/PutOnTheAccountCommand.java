package commands.userCommands;

import commands.Command;
import dao.DBManager;
import dao.UsersDAO;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PutOnTheAccountCommand implements Command {
    private final UsersDAO usersDAO = new UsersDAO();
    private final DBManager dbManager = DBManager.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getMethod().equalsIgnoreCase("get") || request.getParameter("deposit") == null) {
            response.sendRedirect("my_profile");
            return;
        }
        User user = (User) request.getSession().getAttribute("user");
        dbManager.inTransaction(connection -> {
            usersDAO.updateUserMoney(connection, Integer.parseInt(request.getParameter("deposit")), user.getId(), "+");
            user.setMoney(user.getMoney() + Integer.parseInt(request.getParameter("deposit")));
        });
        response.sendRedirect("my_profile");
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("passenger/put_on_the_account");
    }
}

