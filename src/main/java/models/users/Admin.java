package models.users;

/* Клас адміністраторів */

public class Admin extends AbstractUser {
    public Admin(User user) {
        super(user.getId(), user.getSurname(), user.getName());
    }
}
