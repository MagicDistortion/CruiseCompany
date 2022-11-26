package commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogInCommand implements Command {
   private final HttpServletRequest request;
    private final HttpServletResponse response;

    public LogInCommand(HttpServletRequest req, HttpServletResponse resp) {
        this.request = req;
        this.response = resp;
    }
    @Override
    public void execute() throws IOException {
        response.getWriter().println("hello "+request.getParameter("name"));
    }
}
