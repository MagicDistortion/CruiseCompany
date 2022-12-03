package commands;

import dao.UsersDAO;
import models.users.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class LogInCommand implements Command {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final UsersDAO usersDAO = new UsersDAO();
    private final HttpSession session;

    public LogInCommand(HttpServletRequest req, HttpServletResponse resp) {
        this.request = req;
        this.response = resp;
        this.session = request.getSession();
    }

    @Override
    public void execute() throws IOException, ServletException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = usersDAO.findUserByLogin(login);
        Map<?, ?> phrases = (Map<?, ?>) request.getAttribute("phrases");

        if (user != null) {
            if (user.getPassword().equals(password)) {
                session.setAttribute("user", user);
                response.sendRedirect("index.jsp");
            } else {
                request.setAttribute("error_message", phrases.get("langWrongPassword"));
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error_message", " Login -> " + login + " " + phrases.get("langNotFound"));
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("login") && method.equalsIgnoreCase("Post");
    }
}
