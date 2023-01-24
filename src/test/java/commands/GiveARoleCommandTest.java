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


class GiveARoleCommandTest extends BaseTest {
    private final static String path = "users_list";
    private final static String uri = "admin/give_a_role";


    @Test
    void execute() throws ServletException, IOException {
        final GiveARoleCommand command = new GiveARoleCommand();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getMethod()).thenReturn("post");
        when(request.getParameter("role")).thenReturn("1");
        when(request.getParameter("userId")).thenReturn("5");

        command.execute(request, response);

        verify(response, times(1)).sendRedirect(path);
        verify(request, never()).getSession();
        verify(dispatcher, never()).forward(request, response);
    }

    @Test
    void canHandle() {
        Assertions.assertTrue(new GiveARoleCommand().canHandle(uri, "does not matter"));
    }
}