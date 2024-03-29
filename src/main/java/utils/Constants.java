package utils;

public class Constants {
    public static final String INSERT_USERS
            = "INSERT INTO users (surname,name,login,password,tel,date_of_birth) VALUES (?,?,?,?,?,?)";
    public static final String INSERT_STAFF = "INSERT INTO staff (staff_id,id_of_ship) VALUES (?,?)";
    public static final String INSERT_TICKET
            = "INSERT INTO tickets (cruise_id,user_id,number_of_passengers,total_price) VALUES (?,?,?,?)";
    public static final String INSERT_SHIP = "INSERT INTO ships (name,capacity,image) VALUES (?,?,?)";
    public static final String INSERT_ROUTE = "INSERT INTO route (name,route) VALUES (?,?)";
    public static final String INSERT_CRUISE = "INSERT INTO cruise (ship_id,ship_name,route_id,cruise_name,number_of_ports,price," +
            "start_time,end_time,duration,description) VALUES (?,?,?,?,?,?,?,?,?,?)";
    public static final String FROM_USERS_WITHOUT_ROLE = "SELECT * FROM users where roles_id = 4";
    public static final String FROM_USERS_FIND_STAFF = "SELECT * FROM users left join staff" +
            " on users_id=staff_id where roles_id =3 and staff_id is null";
    public static final String FROM_SHIPS = "SELECT * FROM ships ";
    public static final String FROM_ROUTE = "SELECT * FROM route ";
    public static final String FIND_ALL_CRUISES = "SELECT * FROM cruise where status!='completed'";
    public static final String FROM_CRUISES = "SELECT * FROM cruise where status='didn`t start'";
    public static final String FROM_CRUISES_BY_DATE = "SELECT * FROM cruise where" +
            " date_format(start_time, '%Y-%m-%d')=? AND status='didn`t start'";
    public static final String FROM_TICKETS = "SELECT count(number_of_passengers) FROM tickets where cruise_id=? and (status='paid' or status='confirmed')";
    public static final String FROM_CRUISES_BY_DURATION = "SELECT * FROM cruise  where duration = ? AND status='didn`t start'";
    public static final String UPDATE_USER_ROLE = "UPDATE users SET `roles_id` = ? WHERE (`users_id` = ?)";
    public static final String UPDATE_USER_SURNAME = "UPDATE users SET `surname` = ? WHERE (`users_id` = ?)";
    public static final String UPDATE_USER_NAME = "UPDATE users SET `name` = ? WHERE (`users_id` = ?)";
    public static final String UPDATE_USER_LOGIN = "UPDATE users SET `login` = ? WHERE (`users_id` = ?)";
    public static final String UPDATE_USER_PASSWORD = "UPDATE users SET `password` = ? WHERE (`users_id` = ?)";
    public static final String UPDATE_USER_TEL = "UPDATE users SET `tel` = ? WHERE (`users_id` = ?)";

    public static final String UPDATE_USER_DATE_OF_BIRTH = "UPDATE users SET `date_of_birth`= ?  WHERE `users_id` = ?";
    public static final String FIND_BY_LOGIN = "SELECT * FROM users WHERE login = ? ";
    public static final String FIND_BY_ID = "SELECT * FROM users WHERE users_id = ? ";
    public static final String FIND_TICKET_BY_USER_ID = "SELECT * FROM tickets left join cruise on tickets.cruise_id=cruise.cruise_id where user_id=? ";
    public static final String FIND_PAID_TICKETS = "SELECT * FROM tickets where status='paid'";
    public static final String FIND_TICKET_BY_ID = "SELECT * FROM tickets where ticket_id=? ";
    public static final String FIND_CRUISE_BY_ID = "SELECT * FROM cruise WHERE cruise_id=? ";
    public static final String FIND_CRUISE_BY_SHIP_ID = "SELECT * FROM cruise WHERE ship_id=? AND status='didn`t start' ";
    public static final String FIND_SHIP_ID_BY_STAFF_ID = "SELECT * FROM staff WHERE staff_id=? ";
    public static final String FIND_ROUTE_BY_ID = "SELECT * FROM route WHERE route_id=? ";
    public static final String FIND_SHIP_BY_ID = "SELECT * FROM ships WHERE ship_id=? ";
    public static final String UPDATE_TICKET_STATUS = "Update tickets SET status=? WHERE ticket_id = ?";
    public static final String UPDATE_CRUISE_STATUS = "Update cruise SET status=? WHERE cruise_id = ?";
    public static final String GET_CRUISE_PRICE = "SELECT * FROM cruise left join tickets on cruise.cruise_id=tickets.cruise_id" +
            " WHERE ticket_id =?";
    public static final String EXIST_TEL = "SELECT count(*) FROM users WHERE tel = ? ";
    public static final String FROM_SHIPS_COUNT = "SELECT count(*) FROM ships";
    public static final String SHIPS_NAME_EXIST = "SELECT count(*) FROM ships where name = ?";
    public static final String ROUTE_NAME_EXIST = "SELECT count(*) FROM route where name = ?";
    public static final String THE_SHIP_IS_FREE_ON_DATES = "SELECT count(*) FROM cruise where ship_id = ? and " +
            "(? BETWEEN start_time AND end_time OR ? BETWEEN start_time AND end_time OR ? <start_time AND ?>end_time)";
    public static final String FROM_CRUISES_COUNT = "SELECT count(*) FROM cruise where status='didn`t start'";
}
