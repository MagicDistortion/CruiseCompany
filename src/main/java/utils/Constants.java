package utils;

public class Constants {
    public static final String INSERT_USERS
            = "INSERT INTO users (surname,name,login,password,tel,date_of_birth) VALUES (?,?,?,?,?,?)";
    public static final String INSERT_TICKET
            = "INSERT INTO tickets (cruise_id,user_id,number_of_passengers,total_price) VALUES (?,?,?,?)";
    public static final String INSERT_SHIP = "INSERT INTO ships (capacity,current_point) VALUES (?,?)";
    public static final String INSERT_CRUISE = "INSERT INTO cruise (ship_id,cruise_name,number_of_ports,price," +
            "start_time,end_time) VALUES (?,?,?,?,?,?)";
    public static final String FROM_USERS = "SELECT * FROM users ";
    public static final String UPDATE_USER_ROLE = "UPDATE users SET `roles_id` = ? WHERE (`users_id` = ?)";
    public static final String UPDATE_USER_SURNAME = "UPDATE users SET `surname` = ? WHERE (`users_id` = ?)";
    public static final String UPDATE_USER_NAME = "UPDATE users SET `name` = ? WHERE (`users_id` = ?)";
    public static final String UPDATE_USER_LOGIN = "UPDATE users SET `login` = ? WHERE (`users_id` = ?)";
    public static final String UPDATE_USER_PASSWORD = "UPDATE users SET `password` = ? WHERE (`users_id` = ?)";
    public static final String UPDATE_USER_TEL = "UPDATE users SET `tel` = ? WHERE (`users_id` = ?)";
    public static final String UPDATE_USER_DATE_OF_BIRTH = "UPDATE users SET `date_of_birth`= ?  WHERE `users_id` = ?";
    public static final String FIND_BY_LOGIN = "SELECT * FROM users WHERE login = ? ";
    public static final String FIND_BY_ID = "SELECT * FROM users WHERE users_id = ? ";
    public static final String FIND_ALL_PASSENGERS = "SELECT * FROM users where roles_id=1 ";
    public static final String FIND_ALL_ADMINS = "SELECT * FROM users where roles_id=2 ";
    public static final String FIND_TICKET_BY_USER_ID = "SELECT * FROM tickets where user_id=? ";
    public static final String FIND_CRUISE_BY_ID = "SELECT * FROM cruise WHERE cruise_id=? ";
    public static final String UPDATE_NUMBER_OF_PASSENGERS = "UPDATE tickets SET number_of_passengers=? WHERE ticket_id=? ";
    public static final String UPDATE_TOTAL_PRICE = "Update tickets SET total_price=? WHERE ticket_id = ?";
    public static final String GET_CRUISE_PRICE = "SELECT * FROM cruise left join tickets on cruise.cruise_id=tickets.cruise_id" +
            " WHERE ticket_id =?";


}
