package controller;

import commands.Command;
import commands.LogInCommand;
import commands.LogOutCommand;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        action(req, resp, req.getRequestURI().substring(req.getContextPath().length() + 1));
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        action(req, resp, req.getRequestURI().substring(req.getContextPath().length() + 1));
    }

    public void action(HttpServletRequest req, HttpServletResponse resp, String uri) throws IOException {
        commands.put("login", new LogInCommand(req,resp));
        commands.put("logout", new LogOutCommand(req,resp));

        if (commands.get(uri) == null)
            resp.sendRedirect("error404.jsp");
        else commands.get(uri).execute();
    }

}
