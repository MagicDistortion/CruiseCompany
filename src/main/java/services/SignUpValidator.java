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
import java.util.Map;
import java.util.Objects;

/*класс для перевірки даних потрібних для реєстрації нового користувача */
public class SignUpValidator {
    private static final List<String> errors = new ArrayList<>();
    private static final UsersDAO usersDAO = new UsersDAO();
    private final static Logger logger = Logger.getLogger(SignUpValidator.class);
    private Map<String, String> phrases;

    /* метод запускає перевірку отриманих з Http запиту данних, та повертає лист з помилками якщо вони є */
    public List<String> registerValidate(HttpServletRequest req) {
        phrases = (Map<String, String>) req.getAttribute("phrases");
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
        return name != null && name.length() >= 2 && name.length() < 32 ? null : phrases.get("langNameIsWrong");
    }

    /* перевірка прізвища */
    private String validateSurname(String surname) {
        return surname != null && surname.length() >= 2 && surname.length() < 32 ? null : phrases.get("langSurnameIsWrong");
    }

    /* перевірка логіну */
    private String validateLogin(String login) {
        if (usersDAO.findUserByLogin(login) != null) return phrases.get("langTheLoginIsAlreadyInUse");
        return login != null && login.length() >= 2 && login.length() < 32 ? null : phrases.get("langLoginIsWrong");
    }

    /* перевірка паролю*/
    private String validatePassword(String password, String rePassword) {
        if (!password.equals(rePassword)) return phrases.get("langPasswordsAreNotTheSame");
        return password.length() >= 4 && password.length() < 32 ? null : phrases.get("langWrongPassword");
    }

    /* перевірка телефону */
    private String validatePhoneNumber(String number) {
        if (number.length() == 11 && number.startsWith("80")) number = number.substring(1);
        if (number.length() == 9
                || number.length() == 10 && number.charAt(0) == '0'
                || number.length() == 12 && number.startsWith("380"))
            return usersDAO.existTel(updateTel(number)) ? phrases.get("langPhoneNumberIsAlreadyInUse") : null;
        else return phrases.get("langPhoneNumberIsWrong");
    }

    /* перевірка дати народження */
    private String validateDateOfBirth(String date) {
        LocalDate dateOfBirth;
        if (date == null) return phrases.get("langDateIsWrong");
        try {
            dateOfBirth = LocalDate.parse(date);
        } catch (DateTimeException e) {
            return phrases.get("langDateIsWrong");
        }
        return dateOfBirth.isBefore(LocalDate.of(1900, 1, 1)) || dateOfBirth.isAfter(LocalDate.now())
                ? phrases.get("langDateIsWrong") : null;
    }

    /* приводить номер телефону до потрібного вигляду */
    public static String updateTel(String tel) {
        try {
            Phonenumber.PhoneNumber ua = PhoneNumberUtil.getInstance().parse(tel, "UA");
            String result = ua.getCountryCode() + "" + ua.getNationalNumber();
            if (result.length() == 12) return result;
            throw new RuntimeException("phone number is wrong");
        } catch (NumberParseException | RuntimeException e) {
            logger.error("failed to update telephone number ->" + tel, e);
            throw new RuntimeException(e);
        }
    }
}

