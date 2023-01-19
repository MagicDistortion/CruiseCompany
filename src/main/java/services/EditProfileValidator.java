package services;

import dao.UsersDAO;
import models.users.User;

import javax.servlet.http.HttpServletRequest;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*класс для перевірки даних у формі редагування профілю*/
public class EditProfileValidator {
    private final List<String> errors = new ArrayList<>();
    private final UsersDAO usersDAO = new UsersDAO();
    private User user;

    /* метод отримує дані з Http запиту, перевіряє які поля були змінені, якщо такі є то
     * перевіряє дані на валідність та повертає в результаті лист з помилками, якщо такі є,та підтвердженням
     * змінених полей */
    public List<String> editValidate(HttpServletRequest req) {
        errors.clear();
        User oldUser = (User) req.getSession().getAttribute("user");
        user = new User(req.getParameter("surname"), req.getParameter("name"), req.getParameter("login")
                , req.getParameter("password"), req.getParameter("tel"), LocalDate.parse(req.getParameter("date_of_birth")));
        user.setId(oldUser.getId());

        if (!user.getSurname().equals(oldUser.getSurname()) && !user.getSurname().isEmpty())
            errors.add(validateSurname(user.getSurname()));
        if (!user.getName().equals(oldUser.getName()) && !user.getName().isEmpty())
            errors.add(validateName(user.getName()));
        if (!user.getLogin().equals(oldUser.getLogin()) && !user.getLogin().isEmpty())
            errors.add(validateLogin(user.getLogin()));
        if (!String.valueOf(user.getPassword().hashCode()).equals(oldUser.getPassword()) && !user.getPassword().isEmpty())
            errors.add(validatePassword(user.getPassword(), req.getParameter("repassword")));
        if (!user.getTel().equals(oldUser.getTel()) && !user.getTel().isEmpty())
            errors.add(validatePhoneNumber(user.getTel()));
        if (!user.getDateOfBirth().equals(oldUser.getDateOfBirth()) && !user.getDateOfBirth().toString().isEmpty())
            errors.add(validateDateOfBirth(user.getDateOfBirth().toString()));

        errors.removeIf(Objects::isNull);
        return errors;
    }

    /* перевірка та оновлення прізвища */
    private String validateSurname(String surname) {
        if (surname != null && surname.length() >= 2 && surname.length() < 32) {
            usersDAO.updateUserSurname(surname, user.getId());
            return "surname is changed";
        }
        return "surname is wrong";
    }

    /* перевірка та оновлення імені */
    private String validateName(String name) {
        if (name != null && name.length() >= 2 && name.length() < 32) {
            usersDAO.updateUserName(name, user.getId());
            return "name is changed";
        }
        return "name is wrong";
    }

    /* перевірка та оновлення логіну */
    private String validateLogin(String login) {
        if (usersDAO.findUserByLogin(login) != null) return "The login is already in use";
        if (login != null && login.length() >= 2 && login.length() < 32) {
            usersDAO.updateUserLogin(login, user.getId());
            return "login is changed";
        }
        return "login is wrong";
    }

    /* перевірка та оновлення паролю*/
    private String validatePassword(String password, String rePassword) {
        if (!password.equals(rePassword)) return "passwords not a same";
        if (password.length() >= 4 && password.length() < 32) {
            usersDAO.updateUserPassword(password, user.getId());
            return "password is changed";
        }
        return "password is wrong";
    }

    /* перевірка та оновлення телефону */
    private String validatePhoneNumber(String number) {
        String finalTel;
        if (number == null) return "phone number is wrong";
        if (number.length() == 11 && number.startsWith("80")) {
            number = number.substring(1);
            finalTel = SignUpValidator.updateTel(number);
        } else if (number.length() == 9
                || number.length() == 10 && number.charAt(0) == '0'
                || number.length() == 12 && number.startsWith("380"))
            finalTel = SignUpValidator.updateTel(number);
        else return "phone number is wrong";
        if (usersDAO.existTel(finalTel)) return "phone number is already in use";
        usersDAO.updateUserTel(finalTel, user.getId());
        return "phone number is changed";
    }

    /* перевірка та оновлення дати народження */
    private String validateDateOfBirth(String date) {
        LocalDate dateOfBirth;
        if (date == null) return "date is wrong";
        try {
            dateOfBirth = LocalDate.parse(date);
        } catch (DateTimeException e) {
            return "date is wrong";
        }
        if (dateOfBirth.isBefore(LocalDate.of(1900, 1, 1)) || dateOfBirth.isAfter(LocalDate.now()))
            return "date is wrong";
        usersDAO.updateUserDateOfBirth(dateOfBirth, user.getId());
        return "date of birth is changed";
    }
}

