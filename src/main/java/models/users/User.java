package models.users;

import lombok.Data;
import services.SignUpValidator;

import java.io.Serializable;
import java.time.LocalDate;

/* Клас користувачів */
@Data
public class User implements Serializable {

    private int id;
    private String surname;
    private String name;
    private String login;
    private String password;
    private String tel;
    private int rolesId;
    private LocalDate dateOfBirth;
    private double money;

    public User(String surname, String name, String login, String password, String tel, LocalDate dateOfBirth) {
        this.tel = tel;
        this.surname = surname;
        this.name = name;
        this.login = login;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    public void setTel(String tel) {
        this.tel = SignUpValidator.updateTel(tel);
    }
}
