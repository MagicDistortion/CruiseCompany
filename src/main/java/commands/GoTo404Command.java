package commands;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoTo404Command implements Command {
    private final HttpServletResponse response;

    public GoTo404Command(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void execute() throws IOException {
        response.sendRedirect("error404.jsp");
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return true;
    }
}
