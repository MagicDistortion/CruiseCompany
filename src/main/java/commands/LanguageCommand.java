package commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LanguageCommand implements Command {
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public LanguageCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() throws IOException {

        request.getSession().setAttribute("lang", request.getParameter("lang"));
        response.sendRedirect(request.getHeader("referer"));
    }
}
