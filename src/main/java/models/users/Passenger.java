package models.users;

public class Passenger extends AbstractUser {

    public Passenger(User user) {
        super(user.getId(), user.getSurname(), user.getName());
    }
}
