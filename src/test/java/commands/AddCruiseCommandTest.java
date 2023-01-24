package commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.BaseTest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

class AddCruiseCommandTest extends BaseTest {
    private final static String path = "add_cruise.jsp";
    private final static String uri ="admin/add_cruise";

    @Test
    void execute() throws ServletException, IOException {
        final AddCruiseCommand command = new AddCruiseCommand();

        final Map<String, String> map = new HashMap<>();
        final LocalDateTime startTime = LocalDateTime.of(2023, 2,10,12, 0);
        final LocalDateTime endTime = LocalDateTime.of(2023, 2,20,12, 0);
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getMethod()).thenReturn("post");
        when(request.getAttribute("phrases")).thenReturn(map);
        when(request.getParameter("shipId")).thenReturn("1");
        when(request.getParameter("routeId")).thenReturn("1");
        when(request.getParameter("cruiseName")).thenReturn("testCruise");
        when(request.getParameter("price")).thenReturn("150");
        when(request.getParameter("description")).thenReturn("Description");
        when(request.getParameter("startTime")).thenReturn(startTime.toString());
        when(request.getParameter("endTime")).thenReturn(endTime.toString());

        command.execute(request, response);

        verify(response, times(1)).sendRedirect(path);
        verify(request, never()).getSession();
        verify(dispatcher, never()).forward(request, response);
    }

    @Test
    void canHandle() {
        Assertions.assertTrue(new AddCruiseCommand().canHandle(uri,"get"));
    }
}