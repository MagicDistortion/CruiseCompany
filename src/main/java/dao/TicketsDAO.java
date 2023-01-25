package dao;

import models.ships.Ship;
import models.tickets.Ticket;
import org.apache.log4j.Logger;
import utils.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketsDAO {
    private final static Logger logger = Logger.getLogger(TicketsDAO.class);
    private final DBManager dbManager = DBManager.getInstance();
    private final CruisesDAO cruiseDAO = new CruisesDAO();


    /* метод додавання квитка  */
    public void insertTicket(Ticket ticket) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(Constants.INSERT_TICKET, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, ticket.getCruiseId());
            preparedStatement.setInt(2, ticket.getUserId());
            preparedStatement.setInt(3, ticket.getNumberOfPassengers());
            preparedStatement.setDouble(4, ticket.getTotalPrice());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                generatedKeys.next();
                ticket.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            logger.error("failed to insert ticket", e);
            throw new RuntimeException(e);
        }
    }

    /* метод отримання користувача з різалтсету */
    public Ticket getTicket(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket(cruiseDAO.findCruiseByID(resultSet.getInt("cruise_id"))
                , resultSet.getInt("user_id")
                , resultSet.getInt("number_of_passengers")
        );
        ticket.setId(resultSet.getInt("ticket_id"));
        ticket.setTotalPrice(resultSet.getDouble("total_price"));
        ticket.setStatus(resultSet.getString("status"));
        return ticket;
    }

    /* метод пошуку квитка по id пасажира */
    public List<Ticket> findTicketsByUserId(int userId,String sort) {
        List<Ticket> ticketList = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.FIND_TICKET_BY_USER_ID+sort)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    ticketList.add(getTicket(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("failed to find tickets by user`s id ->" + userId, e);
            throw new RuntimeException(e);
        }
        return ticketList;
    }

    /* метод отримання всіх сплачених квитків */
    public List<Ticket> findAllPaidTickets() {
        List<Ticket> ticketList = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.FIND_PAID_TICKETS)) {
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) ticketList.add(getTicket(resultSet));
            }
        } catch (SQLException e) {
            logger.error("failed to find paid tickets list", e);
            throw new RuntimeException(e);
        }
        return ticketList;
    }

    /* метод отримання квитка по id квитка */
    public Ticket findTicketById(int ticketId) {
        Ticket ticket = null;
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.FIND_TICKET_BY_ID)) {
            preparedStatement.setInt(1, ticketId);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                if (resultSet.next()) {
                    ticket = getTicket(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("failed to find ticket by id ->" + ticketId, e);
            throw new RuntimeException(e);
        }
        return ticket;
    }

    /* метод оновлення кількості пасажирів по квитку*/
    public void updateNumberOfPassengers(int numberOfPassengers, int ticketId) {
        dbManager.inTransaction(connection -> {
            try (PreparedStatement preparedStatement = connection.prepareStatement(Constants.UPDATE_NUMBER_OF_PASSENGERS)) {
                preparedStatement.setInt(1, numberOfPassengers);
                preparedStatement.setInt(2, ticketId);
                preparedStatement.executeUpdate();
                double price = cruiseDAO.getPriceOfCruise(connection, ticketId);
                updateTotalPrice(connection, numberOfPassengers, price, ticketId);
            } catch (SQLException e) {
                logger.error("failed to update number of passengers by ticket`s id ->" + ticketId, e);
                throw new RuntimeException(e);
            }
        });
    }

    /* метод оновлення загальної вартості по квитку*/
    public void updateTotalPrice(Connection connection, int numberOfPassengers, double price, int ticketId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constants.UPDATE_TOTAL_PRICE)) {
            preparedStatement.setDouble(1, numberOfPassengers * price);
            preparedStatement.setInt(2, ticketId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("failed to update number of passengers by ticket`s id ->" + ticketId, e);
            throw new RuntimeException(e);
        }
    }

    /* метод оновлення статусу квитка*/
    public void updateStatus(Connection connection, String status, int ticketId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constants.UPDATE_TICKET_STATUS)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, ticketId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("failed to update status for ticket by id ->" + ticketId, e);
            throw new RuntimeException(e);
        }
    }


    /* метод перевірки наявності квитків */
    public Boolean availabilityCheck(Ship ship, int cruiseId, int amount) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.FROM_TICKETS)) {
            preparedStatement.setInt(1, cruiseId);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    amount += resultSet.getInt("number_of_passengers");
                }
                return amount <= ship.getCapacity();
            }
        } catch (SQLException e) {
            logger.error("failed to checking for availability ", e);
            throw new RuntimeException(e);
        }
    }
}
