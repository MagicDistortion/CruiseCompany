package commands;

import dao.UsersDAO;
import models.User;
import services.EncodePassword;
import utils.RequestAssistant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogInCommand implements Command {
    private final UsersDAO usersDAO = new UsersDAO();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getMethod().equalsIgnoreCase("get")
                || request.getParameter("login") == null
                || request.getParameter("password") == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = usersDAO.findUserByLogin(login);
        RequestAssistant requestAssistant = new RequestAssistant();
        if (user == null) {
            request.setAttribute("error_message"
                    , " Login -> " + login + " " + requestAssistant.getPhrase(request, "langNotFound"));
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        if (!user.getPassword().equals(new EncodePassword().getHashPassword(password))) {
            requestAssistant.setError(request, "langWrongPassword");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        request.getSession().setAttribute("user", user);
        response.sendRedirect("index.jsp");
    }

    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("login");
    }
}
