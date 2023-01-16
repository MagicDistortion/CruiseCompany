package commands;

import dao.DBManager;
import dao.TicketsDAO;
import dao.UsersDAO;
import models.tickets.Ticket;
import models.users.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PayForTicketCommand implements Command {
    private final TicketsDAO ticketsDAO = new TicketsDAO();
    private final UsersDAO usersDAO = new UsersDAO();
    private final DBManager dbManager = DBManager.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getMethod().equalsIgnoreCase("post")) {
            if (request.getParameter("ticket_id") != null) {
                User user = (User) request.getSession().getAttribute("user");
                Ticket ticket = ticketsDAO.findTicketById(Integer.parseInt(request.getParameter("ticket_id")));
                double money = user.getMoney();
                if (money >= ticket.getTotalPrice()) {
                    double result = money - ticket.getTotalPrice();
                    dbManager.inTransaction(connection -> {
                        ticketsDAO.updateStatus(connection, "paid", ticket.getId());
                        usersDAO.updateUserMoney(connection, money - result, user.getId());
                        user.setMoney(result);
                    });
                }
            }
        }
        response.sendRedirect("my_profile");
    }

    @Override
    public boolean canHandle(String uri, String method) {
        return uri.equalsIgnoreCase("passenger/pay_for_ticket");
    }
}
