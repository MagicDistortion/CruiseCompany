package utils;

public class Constants {
    public static final String INSERT_USERS
            = "INSERT INTO users (surname,name,login,password,tel,date_of_birth) VALUES (?,?,?,?,?,?)";
    public static final String FROM_USERS = "SELECT * FROM users ";
    public static final String UPDATE_USER_ROLE = "UPDATE users SET `roles_id` = ? WHERE (`users_id` = ?)";
    public static final String UPDATE_USER_SURNAME = "UPDATE users SET `surname` = ? WHERE (`users_id` = ?)";
    public static final String UPDATE_USER_NAME = "UPDATE users SET `name` = ? WHERE (`users_id` = ?)";
    public static final String UPDATE_USER_LOGIN = "UPDATE users SET `login` = ? WHERE (`users_id` = ?)";
    public static final String UPDATE_USER_PASSWORD = "UPDATE users SET `password` = ? WHERE (`users_id` = ?)";
    public static final String UPDATE_USER_TEL = "UPDATE users SET `tel` = ? WHERE (`users_id` = ?)";
    public static final String UPDATE_USER_DATE_OF_BIRTH = "UPDATE users SET `date_of_birth`= ?  WHERE `users_id` = ?";
}
