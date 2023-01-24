package commands;

import dao.UsersDAO;
import models.users.User;
import services.EditProfileValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditMyProfileCommand implements Command {
    private final UsersDAO usersDAO = new UsersDAO();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getMethod().equalsIgnoreCase("get")) {
            response.sendRedirect("edit_profile.jsp");
            return;
        }
        List<String> errors = new EditProfileValidator().editValidate(request);
        User user = usersDAO.findUserByID(((User) request.getSession().getAttribute("user")).getId());
        request.getSession().setAttribute("user", user);
        request.setAttribute("errors", errors);
        request.getRequestDispatcher("edit_profile.jsp").forward(request, response);
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("edit_profile");
    }
}


