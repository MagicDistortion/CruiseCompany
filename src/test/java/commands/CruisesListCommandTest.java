package commands;

import commands.cruiseCommands.CruisesListCommand;
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

class CruisesListCommandTest extends BaseTest {
    private final static String path = "cruises_list.jsp";
    private final static String uri ="cruises_list";

    @Test
    void execute() throws ServletException, IOException {
        final CruisesListCommand command = new CruisesListCommand();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(request.getParameter("sort")).thenReturn("name");

        command.execute(request, response);

        verify(response, never()).sendRedirect(path);
        verify(request, never()).getSession();
        verify(dispatcher, times(1)).forward(request, response);
    }
    @Test
    void canHandle() {
        Assertions.assertTrue(new CruisesListCommand().canHandle(uri,"get"));
    }
}