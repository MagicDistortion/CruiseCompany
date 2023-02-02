package commands;

import commands.shipCommands.ShipsListCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.BaseTest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class ShipsListCommandTest extends BaseTest {
    private final static String path = "ships_list.jsp";
    private final static String uri = "ships_list";

    @Test
    void execute() throws ServletException, IOException {
        final ShipsListCommand command = new ShipsListCommand();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);

        command.execute(request, response);

        verify(response, never()).sendRedirect(path);
        verify(request, never()).getSession();
        verify(dispatcher, times(1)).forward(request, response);
    }
    @Test
    void canHandle() {
        Assertions.assertTrue(new ShipsListCommand().canHandle(uri, "get"));
    }
}