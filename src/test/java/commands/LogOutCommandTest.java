package commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.BaseTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class LogOutCommandTest extends BaseTest {
    private final static String path = "index.jsp";
    private final static String uri = "logout";


    @Test
    void execute() throws IOException {
        final LogOutCommand command = new LogOutCommand();

        final HttpSession session = mock(HttpSession.class);
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getSession()).thenReturn(session);
        command.execute(request, response);

        verify(response, times(1)).sendRedirect(path);
        verify(request, times(1)).getSession();
    }
    @Test
    void canHandle() {
        Assertions.assertTrue(new LogOutCommand().canHandle(uri, "get"));
    }
}