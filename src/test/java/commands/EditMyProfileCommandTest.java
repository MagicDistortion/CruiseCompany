package commands;

import models.users.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.BaseTest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


class EditMyProfileCommandTest extends BaseTest {
    private final static String path = "edit_profile.jsp";
    private final static String uri = "edit_profile";

    @Test
    void execute() throws ServletException, IOException {
        final EditMyProfileCommand command = new EditMyProfileCommand();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        final HttpSession session = mock((HttpSession.class));
        final User user = new User("Baranets", "Artem", "Artem", "9582", "0662221199",
                LocalDate.of(1993, 3, 10));
        user.setId(1);

        when(request.getMethod()).thenReturn("post");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("surname")).thenReturn("No Baranets");
        when(request.getParameter("name")).thenReturn("no Artem");
        when(request.getParameter("login")).thenReturn("no Artem");
        when(request.getParameter("password")).thenReturn("9581");
        when(request.getParameter("repassword")).thenReturn("9581");
        when(request.getParameter("tel")).thenReturn("0662222299");
        when(request.getParameter("date_of_birth")).thenReturn(LocalDate.of(1993, 4, 10).toString());

        command.execute(request, response);

        verify(response, never()).sendRedirect(path);
        verify(request, times(3)).getSession();
        verify(dispatcher, times(1)).forward(request, response);
    }

    @Test
    void canHandle() {
        Assertions.assertTrue(new EditMyProfileCommand().canHandle(uri, "post"));
    }

}