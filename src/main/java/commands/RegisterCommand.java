package commands;

import dao.UsersDAO;
import models.users.User;
import services.SignUpValidator;

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
        if (request.getMethod().equalsIgnoreCase("post")) {
            SignUpValidator validator = new SignUpValidator();
            List<String> errors = validator.registerValidate(request);
            User user = new User(request.getParameter("surname")
                    , request.getParameter("name")
                    , request.getParameter("login")
                    , request.getParameter("password")
                    , SignUpValidator.updateTel(request.getParameter("tel"))
                    , LocalDate.parse(request.getParameter("date_of_birth")));
            if (errors.isEmpty()) {
                usersDAO.insertUser(user);
                request.getSession().setAttribute("user", user);
                response.sendRedirect("index.jsp");
            } else {
                request.setAttribute("user", user);
                request.setAttribute("tel", request.getParameter("tel"));
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("register_form.jsp").forward(request, response);
            }
        }else request.getRequestDispatcher("register_form.jsp").forward(request, response);
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("register");
    }
}

