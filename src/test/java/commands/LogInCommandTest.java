package commands;

import org.junit.jupiter.api.Test;
import utils.BaseTest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

class LogInCommandTest extends BaseTest {
    private final static String path = "index.jsp";

    @Test
    void execute() throws IOException, ServletException {
        final LogInCommand command = new LogInCommand();

        final HttpSession session = mock((HttpSession.class));
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getMethod()).thenReturn("post");
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("login")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("9582");

        command.execute(request, response);

        verify(response, times(1)).sendRedirect(path);
        verify(request, times(1)).getSession();
        verify(dispatcher, never()).forward(request, response);
    }

    @Test
    void doPostWrongLogin() throws ServletException, IOException {
        final LogInCommand command = new LogInCommand();
        final Map<String, String> map = new HashMap<>();
        map.put("langNotFound", "Not found");

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getMethod()).thenReturn("post");
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(request.getParameter("login")).thenReturn("admin4ik");
        when(request.getParameter("password")).thenReturn("9582");
        when(request.getAttribute("phrases")).thenReturn(map);

        command.execute(request, response);

        verify(response, never()).sendRedirect(path);
        verify(request, never()).getSession();
        verify(dispatcher, times(1)).forward(request, response);
    }
}