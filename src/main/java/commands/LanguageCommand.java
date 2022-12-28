package commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LanguageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().setAttribute("lang", request.getParameter("lang"));
        response.sendRedirect(request.getHeader("referer"));
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("lang") && method.equalsIgnoreCase("Post");
    }
}
