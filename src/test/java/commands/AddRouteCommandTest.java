package commands;

import commands.cruiseCommands.AddRouteCommand;
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

class AddRouteCommandTest extends BaseTest {
    private final static String path = "add_route.jsp";
    private final static String uri ="admin/add_route";

    @Test
    void execute() throws ServletException, IOException {
        final AddRouteCommand command = new AddRouteCommand();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getMethod()).thenReturn("post");
        when(request.getParameter("name")).thenReturn("testRouteName");
        when(request.getParameter("route")).thenReturn("Route");

        command.execute(request, response);

        verify(response, times(1)).sendRedirect(path);
        verify(request, never()).getSession();
        verify(dispatcher, never()).forward(request, response);
    }
    @Test
    void canHandle() {
        Assertions.assertTrue(new AddRouteCommand().canHandle(uri,"get"));
    }
}