package commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CruiseDetailsCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("cruise_details.jsp");
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("cruise_details")&&method.equalsIgnoreCase("Get");
    }
}
