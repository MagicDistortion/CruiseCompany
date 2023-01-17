package utils;

public class Constants {
    public static final String INSERT_USERS
            = "INSERT INTO users (surname,name,login,password,tel,date_of_birth) VALUES (?,?,?,?,?,?)";
    public static final String INSERT_TICKET
            = "INSERT INTO tickets (cruise_id,user_id,number_of_passengers,total_price) VALUES (?,?,?,?)";
    public static final String INSERT_SHIP = "INSERT INTO ships (name,capacity,image) VALUES (?,?,?)";
    public static final String INSERT_CRUISE = "INSERT INTO cruise (ship_id,ship_name,cruise_name,number_of_ports,price," +
            "start_time,end_time,duration,description) VALUES (?,?,?,?,?,?,?,?,?)";
    public static final String FROM_USERS_WITHOUT_ROLE = "SELECT * FROM users where roles_id = 4";
    public static final String FROM_USERS = "SELECT * FROM users ";
    public static final String FROM_SHIPS = "SELECT * FROM ships ";
    public static final String FROM_CRUISES = "SELECT * FROM cruise where status!='finished' AND status!='canceled'";
    public static final String FROM_TICKETS = "SELECT * FROM tickets where cruise_id=? ";
    public static final String FROM_CRUISES_BY_DURATION = "SELECT * FROM cruise  where duration = ? AND status!='finished' AND status!='canceled'";
    public static final String UPDATE_USER_ROLE = "UPDATE users SET `roles_id` = ? WHERE (`users_id` = ?)";
    public static final String UPDATE_USER_SURNAME = "UPDATE users SET `surname` = ? WHERE (`users_id` = ?)";
    public static final String UPDATE_USER_NAME = "UPDATE users SET `name` = ? WHERE (`users_id` = ?)";
    public static final String UPDATE_USER_LOGIN = "UPDATE users SET `login` = ? WHERE (`users_id` = ?)";
    public static final String UPDATE_USER_PASSWORD = "UPDATE users SET `password` = ? WHERE (`users_id` = ?)";
    public static final String UPDATE_USER_TEL = "UPDATE users SET `tel` = ? WHERE (`users_id` = ?)";
    public static final String UPDATE_USER_MONEY = "UPDATE users SET money = ? WHERE (`users_id` = ?)";
    public static final String UPDATE_USER_DATE_OF_BIRTH = "UPDATE users SET `date_of_birth`= ?  WHERE `users_id` = ?";
    public static final String FIND_BY_LOGIN = "SELECT * FROM users WHERE login = ? ";
    public static final String FIND_BY_ID = "SELECT * FROM users WHERE users_id = ? ";
    public static final String FIND_ALL_PASSENGERS = "SELECT * FROM users where roles_id=1 ";
    public static final String FIND_ALL_ADMINS = "SELECT * FROM users where roles_id=2 ";
    public static final String FIND_TICKET_BY_USER_ID = "SELECT * FROM tickets where user_id=? ";
    public static final String FIND_PAID_TICKETS = "SELECT * FROM tickets where status='paid' or status='confirm'";
    public static final String FIND_TICKET_BY_ID = "SELECT * FROM tickets where ticket_id=? ";
    public static final String FIND_CRUISE_BY_ID = "SELECT * FROM cruise WHERE cruise_id=? ";
    public static final String FIND_SHIP_BY_ID = "SELECT * FROM ships WHERE ship_id=? ";
    public static final String UPDATE_NUMBER_OF_PASSENGERS = "UPDATE tickets SET number_of_passengers=? WHERE ticket_id=? ";
    public static final String UPDATE_TOTAL_PRICE = "Update tickets SET total_price=? WHERE ticket_id = ?";
    public static final String UPDATE_TICKET_STATUS = "Update tickets SET status=? WHERE ticket_id = ?";
    public static final String GET_CRUISE_PRICE = "SELECT * FROM cruise left join tickets on cruise.cruise_id=tickets.cruise_id" +
            " WHERE ticket_id =?";
    public static final String EXIST_TEL = "SELECT count(*) FROM users WHERE tel = ? ";
    public static final String FROM_SHIPS_COUNT = "SELECT count(*) FROM ships";
    public static final String SHIPS_NAME_EXIST = "SELECT count(*) FROM ships where name = ?";
    public static final String CRUISE_NAME_EXIST = "SELECT count(*) FROM cruise where cruise_name = ?";
    public static final String FROM_CRUISES_COUNT = "SELECT count(*) FROM cruise where status!='finished' AND status!='canceled'";


}
