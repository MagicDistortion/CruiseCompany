package commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOutCommand implements Command {
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public LogOutCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() throws IOException {

        request.getSession().invalidate();
        response.sendRedirect("index.jsp");
    }
}
