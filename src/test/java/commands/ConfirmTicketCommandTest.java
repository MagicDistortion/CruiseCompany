package commands;

import org.junit.jupiter.api.Test;
import utils.BaseTest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

class ConfirmTicketCommandTest extends BaseTest {
    private final static String path = "paid_tickets";

    @Test
    void execute() throws ServletException, IOException {
        final ConfirmTicketCommand command = new ConfirmTicketCommand();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getMethod()).thenReturn("post");
        when(request.getParameter("ticket_id")).thenReturn("1");

        command.execute(request, response);

        verify(response, times(1)).sendRedirect(path);
        verify(request, never()).getSession();
        verify(dispatcher, never()).forward(request, response);
    }
}