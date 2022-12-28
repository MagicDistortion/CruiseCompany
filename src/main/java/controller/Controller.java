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
    public void init() {
        commands.put("login", new LogInCommand());
        commands.put("logout", new LogOutCommand());
        commands.put("lang", new LanguageCommand());
        commands.put("register", new RegisterCommand());
        commands.put("edit_profile", new EditMyProfileCommand());
        commands.put("ships_list", new ShipsListCommand());
        commands.put("cruises_list", new CruisesListCommand());
        commands.put("admin/users_list", new UsersListCommand());
        commands.put("admin/give_a_role", new GiveARoleCommand());
        commands.put("admin/add_ship", new AddShipCommand());
        commands.put("admin/add_cruise", new AddCruiseCommand());
        commands.put("admin/ships_for_add_cruise", new ShipsForAddCruiseCommand());
        commands.put("passenger/getCruises", new GetCruisesCommand());
        commands.put("passenger/buy_a_ticket", new BuyATicketCommand());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        action(req, resp, req.getRequestURI().substring(req.getContextPath().length() + 1));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        action(req, resp, req.getRequestURI().substring(req.getContextPath().length() + 1));
    }

    public void action(HttpServletRequest req, HttpServletResponse resp, String uri) throws IOException {
        try {
            commands.values().stream().filter(command -> command.canHandle(uri, req.getMethod()))
                    .findFirst().orElse(new GoTo404Command()).execute(req, resp);
        } catch (ServletException e) {
            logger.error("failed to execute command with uri ->" + uri + " and method-> " + req.getMethod(), e);
            throw new RuntimeException(e);
        }
    }
}
