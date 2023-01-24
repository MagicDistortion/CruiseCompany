package commands;

import org.junit.jupiter.api.Test;
import utils.BaseTest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;


class LanguageCommandTest extends BaseTest {

    private final static String path = "index.jsp";

    @Test
    void execute() throws ServletException, IOException {
        final LanguageCommand command = new LanguageCommand();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("lang")).thenReturn("UA");
        when(request.getHeader("referer")).thenReturn(path);

        command.execute(request, response);

        verify(response, times(1)).sendRedirect(path);
        verify(request, times(1)).getSession();
        verify(dispatcher, never()).forward(request, response);
    }
}