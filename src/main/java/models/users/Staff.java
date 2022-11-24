package models.users;

public class Staff extends AbstractUser{

    public Staff(User user) {
        super(user.getId(), user.getSurname(), user.getName());
    }
}
