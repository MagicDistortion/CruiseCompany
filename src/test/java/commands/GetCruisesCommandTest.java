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
import static org.mockito.Mockito.times;


class GetCruisesCommandTest extends BaseTest {
    private final static String path = "buy_a_ticket.jsp";
    private final static String uri ="passenger/getCruises";


    @Test
    void execute() throws ServletException, IOException {
        final GetCruisesCommand command = new GetCruisesCommand();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getMethod()).thenReturn("post");
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(request.getParameter("duration")).thenReturn("8");
        when(request.getParameter("sort_by")).thenReturn("duration");

        command.execute(request, response);

        verify(response, never()).sendRedirect(path);
        verify(request, never()).getSession();
        verify(dispatcher, times(1)).forward(request, response);
    }
    @Test
    void canHandle() {
        Assertions.assertTrue(new GetCruisesCommand().canHandle(uri,"post"));
    }
}