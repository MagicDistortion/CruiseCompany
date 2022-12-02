package controller;

import commands.*;
import org.apache.log4j.Logger;
import services.SignUpValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/")
public class Controller extends HttpServlet {
    private final Map<String, Command> commands = new HashMap<>();
    private final static Logger logger = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        action(req, resp, req.getRequestURI().substring(req.getContextPath().length() + 1));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        action(req, resp, req.getRequestURI().substring(req.getContextPath().length() + 1));
    }

    public void action(HttpServletRequest req, HttpServletResponse resp, String uri) throws IOException {
        commands.put("login", new LogInCommand(req, resp));
        commands.put("logout", new LogOutCommand(req, resp));
        commands.put("lang", new LanguageCommand(req, resp));
        commands.put("register", new RegisterCommand(req, resp));
        commands.put("edit_profile", new EditMyProfileCommand(req, resp));

        if (commands.get(uri) == null)
            resp.sendRedirect("error404.jsp");
        else {
            try {
                commands.get(uri).execute();
            } catch (ServletException e) {
                logger.error("failed to execute", e);
                throw new RuntimeException(e);
            }
        }
    }
}
