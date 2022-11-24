package models.users;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import lombok.Data;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.time.LocalDate;

/* Клас користувачів */
@Data
public class User implements Serializable {
    private final static Logger logger = Logger.getLogger(User.class);
    private int id;
    private String surname;
    private String name;
    private String login;
    private String password;
    private String tel;
    private int rolesId;
    private LocalDate dateOfBirth;

    public User(String surname, String name, String login, String password, String tel, LocalDate dateOfBirth) {
        this.tel = updateTel(tel);
        this.surname = surname;
        this.name = name;
        this.login = login;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    public void setTel(String tel) {
        this.tel = updateTel(tel);
    }

    public static String updateTel(String tel) {
        Phonenumber.PhoneNumber ua;
        try {
            ua = PhoneNumberUtil.getInstance().parse(tel, "UA");
            String result = ua.getCountryCode() + "" + ua.getNationalNumber();
            if (result.length() != 12) throw new RuntimeException("wrong number tel");
            return result;
        } catch (NumberParseException | RuntimeException e) {
            logger.error("failed to upgrade telephone number ", e);
            throw new RuntimeException(e);
        }
    }
}
