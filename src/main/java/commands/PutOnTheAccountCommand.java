package commands;

import dao.DBManager;
import dao.UsersDAO;
import models.users.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PutOnTheAccountCommand implements Command {
    private final UsersDAO usersDAO = new UsersDAO();
    private final DBManager dbManager = DBManager.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getMethod().equalsIgnoreCase("post")) {
            if (request.getParameter("deposit") != null) {
                User user = (User) request.getSession().getAttribute("user");
                double deposit = user.getMoney() + Integer.parseInt(request.getParameter("deposit"));
                dbManager.inTransaction(connection -> {
                    usersDAO.updateUserMoney(connection, deposit, user.getId());
                    user.setMoney(deposit);
                });
            }
        }
        response.sendRedirect("my_profile");
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("passenger/put_on_the_account");
    }
}

