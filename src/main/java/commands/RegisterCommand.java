package commands;

import dao.UsersDAO;
import models.users.User;
import services.SignUpValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class RegisterCommand implements Command {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final UsersDAO usersDAO = new UsersDAO();
    private final HttpSession session;

    public RegisterCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    @Override
    public void execute() throws IOException, ServletException {
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
            session.setAttribute("user", user);
            response.sendRedirect("index.jsp");
        } else {
            request.setAttribute("user", user);
            request.setAttribute("tel", request.getParameter("tel"));
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("register_form.jsp").forward(request, response);
        }
    }
}
