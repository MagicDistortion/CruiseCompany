package commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoTo404Command implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("error404.jsp");
    }
    @Override
    public boolean canHandle(String uri, String method) {
        return true;
    }
}
