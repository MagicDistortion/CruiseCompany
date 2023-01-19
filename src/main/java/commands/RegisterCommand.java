package commands;

import dao.UsersDAO;
import models.users.User;
import services.SignUpValidator;
import utils.RequestAssistant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class RegisterCommand implements Command {
    private final UsersDAO usersDAO = new UsersDAO();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getMethod().equalsIgnoreCase("get")) {
            response.sendRedirect("register_form.jsp");
            return;
        }
        if (request.getParameter("surname") == null
                || request.getParameter("name") == null
                || request.getParameter("login") == null
                || request.getParameter("password") == null
                || request.getParameter("repassword") == null
                || request.getParameter("tel") == null
                || request.getParameter("date_of_birth") == null) {
            request.getRequestDispatcher("register_form.jsp").forward(request, response);
            RequestAssistant requestAssistant = new RequestAssistant();
            requestAssistant.setError(request, "langInvalidData");
        }
        List<String> errors = new SignUpValidator().registerValidate(request);
        User user = new User(request.getParameter("surname")
                , request.getParameter("name")
                , request.getParameter("login")
                , request.getParameter("password")
                , SignUpValidator.updateTel(request.getParameter("tel"))
                , LocalDate.parse(request.getParameter("date_of_birth")));
        if (!errors.isEmpty()) {
            request.setAttribute("user", user);
            request.setAttribute("tel", request.getParameter("tel"));
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("register_form.jsp").forward(request, response);
            return;
        }
        usersDAO.insertUser(user);
        request.getSession().setAttribute("user", user);
        response.sendRedirect("index.jsp");
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("register");
    }
}

