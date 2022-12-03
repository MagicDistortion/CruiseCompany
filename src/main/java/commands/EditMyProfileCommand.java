package commands;

import dao.UsersDAO;
import models.users.User;
import services.EditProfileValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class EditMyProfileCommand implements Command {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final UsersDAO usersDAO = new UsersDAO();
    private final HttpSession session;

    public EditMyProfileCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    @Override
    public void execute() throws IOException, ServletException {
        EditProfileValidator validatorEditForm = new EditProfileValidator();
        List<String> errors = validatorEditForm.editValidate(request);
        User user = usersDAO.findUserByID(((User) session.getAttribute("user")).getId());
        session.setAttribute("user", user);
        request.setAttribute("errors", errors);
        request.getRequestDispatcher("edit_profile.jsp").forward(request, response);
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("edit_profile")&&method.equalsIgnoreCase("post");
    }
}


