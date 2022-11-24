package models.users;

import lombok.Data;

@Data
public abstract class AbstractUser {
    private int id;
    private String surname;
    private String name;

    public AbstractUser(int id, String surname, String name) {
        this.id = id;
        this.surname = surname;
        this.name = name;
    }
}
