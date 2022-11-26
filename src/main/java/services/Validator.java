package services;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import dao.UsersDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*класс для перевірки даних потрібних для реєстрації нового користувача */
public class Validator {
    private static final List<String> errors = new ArrayList<>();
    private static final UsersDAO usersDAO = new UsersDAO();
    private final static Logger logger = Logger.getLogger(Validator.class);

    /* метод запускає перевірку отриманих з Http запиту данних, та повертає лист з помилками якщо вони є */
    public List<String> registerValidate(HttpServletRequest req) {
        errors.clear();
        errors.add(validateName(req.getParameter("name")));
        errors.add(validateSurname(req.getParameter("surname")));
        errors.add(validateLogin(req.getParameter("login")));
        errors.add(validatePassword(req.getParameter("password"), req.getParameter("repassword")));
        errors.add(validatePhoneNumber(req.getParameter("tel")));
        errors.add(validateDateOfBirth(req.getParameter("date_of_birth")));
        errors.removeIf(Objects::isNull);
        return errors;
    }

    /* перевірка імені */
    private String validateName(String name) {
        return name != null && name.length() >= 2 && name.length() < 32 ? null : "name is wrong";
    }

    /* перевірка прізвища */
    private String validateSurname(String surname) {
        return surname != null && surname.length() >= 2 && surname.length() < 32 ? null : "surname is wrong";
    }

    /* перевірка логіну */
    private String validateLogin(String login) {
        if (usersDAO.findUserByLogin(login) != null) return "The login is already in use";
        return login != null && login.length() >= 2 && login.length() < 32 ? null : "login is wrong";
    }

    /* перевірка паролю*/
    private String validatePassword(String password, String rePassword) {
        if (!password.equals(rePassword)) return "passwords not a same";
        return password.length() >= 4 && password.length() < 32 ? null : "password is wrong";
    }

    /* перевірка телефону */
    public String validatePhoneNumber(String number) {
        if (number == null || number.length() < 9 || number.length() > 12) return "phone number is wrong";
        return usersDAO.existTel(updateTel(number)) ? "phone number is already in use" : null;
    }

    /* перевірка дати народження */
    private String validateDateOfBirth(String date) {
        LocalDate dateOfBirth;
        if (date == null) return "date is wrong";
        try {
            dateOfBirth = LocalDate.parse(date);
        } catch (DateTimeException e) {
            return "date is wrong";
        }
        return dateOfBirth.isBefore(LocalDate.of(1900, 1, 1)) || dateOfBirth.isAfter(LocalDate.now())
                ? "date is wrong" : null;
    }

    /* приводить номер телефону до потрібного вигляду */
    public static String updateTel(String tel) {
        try {
            Phonenumber.PhoneNumber ua = PhoneNumberUtil.getInstance().parse(tel, "UA");
            String result = ua.getCountryCode() + "" + ua.getNationalNumber();
            if (result.length() == 12) return result;
            throw new RuntimeException("wrong number tel");
        } catch (NumberParseException | RuntimeException e) {
            logger.error("failed to update telephone number ->" + tel, e);
            throw new RuntimeException(e);
        }
    }
}

