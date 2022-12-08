package controller;

import commands.*;
import org.apache.log4j.Logger;

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
        action(req, resp, req.getRequestURI().substring(req.getContextPath().length() + 1), "Get");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        action(req, resp, req.getRequestURI().substring(req.getContextPath().length() + 1), "Post");
    }

    public void action(HttpServletRequest req, HttpServletResponse resp, String uri, String method) throws IOException {
        commands.put("login", new LogInCommand(req, resp));
        commands.put("logout", new LogOutCommand(req, resp));
        commands.put("lang", new LanguageCommand(req, resp));
        commands.put("register", new RegisterCommand(req, resp));
        commands.put("edit_profile", new EditMyProfileCommand(req, resp));
        commands.put("ships_list", new ShipsListCommand(req, resp));
        commands.put("cruises_list", new CruisesListCommand(req, resp));
        commands.put("admin/users_list", new UsersListCommand(req, resp));
        commands.put("admin/give_a_role", new GiveARoleCommand(req, resp));

        try {
            commands.values().stream().filter(command -> command.canHandle(uri, method))
                    .findFirst().orElse(new GoTo404Command(resp)).execute();
        } catch (ServletException e) {
            logger.error("failed to execute command with uri ->" + uri + " and method-> " + method, e);
            throw new RuntimeException(e);
        }
    }
}
