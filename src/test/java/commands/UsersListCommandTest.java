package commands;

import commands.userCommands.UsersListCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.BaseTest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


class UsersListCommandTest extends BaseTest {
    private final static String path = "/admin/giving_a_role.jsp";
    private final static String uri = "admin/users_list";


    @Test
    void execute() throws ServletException, IOException {
        final UsersListCommand command = new UsersListCommand();
        final Map<String, String> map = new HashMap<>();
        map.put("langEmpty", "Empty");

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getAttribute("phrases")).thenReturn(map);
        when(request.getMethod()).thenReturn("post");
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);

        command.execute(request, response);

        verify(response, never()).sendRedirect(path);
        verify(request, never()).getSession();
        verify(dispatcher, times(1)).forward(request, response);
    }

    @Test
    void canHandle() {
        Assertions.assertTrue(new UsersListCommand().canHandle(uri, "get"));
    }
}