package commands;

import commands.shipCommands.AddShipCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.BaseTest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

class AddShipCommandTest extends BaseTest {
    private final static String path = "add_ship.jsp";
    private final static String uri ="admin/add_ship";
    @Test
    void execute() throws ServletException, IOException {
        final AddShipCommand command = new AddShipCommand();

        final ServletContext servletContext =mock((ServletContext.class));
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getMethod()).thenReturn("post");
        when(request.getParameter("name")).thenReturn("TestShip");
        when(request.getParameter("capacity")).thenReturn("300");
        when(request.getServletContext()).thenReturn(servletContext);

        command.execute(request, response);

        verify(response, times(1)).sendRedirect(path);
        verify(request, never()).getSession();
        verify(dispatcher, never()).forward(request, response);
    }
    @Test
    void canHandle() {
        Assertions.assertTrue(new AddShipCommand().canHandle(uri,"get"));
    }
}