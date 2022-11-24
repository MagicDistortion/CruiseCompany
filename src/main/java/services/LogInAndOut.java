package services;

public class LogInAndOut {
    private static LogInAndOut instance;

    public static synchronized LogInAndOut getInstance() {
        if (instance == null) instance = new LogInAndOut();
        return instance;
    }

    public void logIn() {
        System.out.println("u are logged in");
    }

    public void logOut() {
        System.out.println("u are logged out");
    }
}
