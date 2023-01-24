package commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.BaseTest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

class GoTo404CommandTest extends BaseTest {
    private final static String path = "error404.jsp";
    private final static String uri = "Some uri";

    @Test
    void execute() throws ServletException, IOException {
        final GoTo404Command command = new GoTo404Command();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        command.execute(request, response);

        verify(response, times(1)).sendRedirect(path);
        verify(request, never()).getSession();
        verify(dispatcher, never()).forward(request, response);
    }
    @Test
    void canHandle() {
        Assertions.assertTrue(new GoTo404Command().canHandle(uri, "does not matter method"));
    }
}