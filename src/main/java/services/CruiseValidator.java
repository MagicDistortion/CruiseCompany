package services;

import dao.CruisesDAO;

import javax.servlet.http.HttpServletRequest;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/*класс для перевірки даних потрібних для реєстрації нового користувача */
public class CruiseValidator {
    private static final List<String> errors = new ArrayList<>();
    private final CruisesDAO cruisesDAO = new CruisesDAO();
    private Map<String, String> phrases;

    /* метод запускає перевірку отриманих з Http запиту данних, та повертає лист з помилками якщо вони є */
    public List<String> validate(HttpServletRequest request) {
        phrases = (Map<String, String>) request.getAttribute("phrases");
        errors.clear();
        errors.add(validateCruiseName(request.getParameter("cruiseName")));
        errors.add(validateNumberOfPorts(Integer.parseInt(request.getParameter("numberOfPorts"))));
        errors.add(validatePrice(Double.parseDouble(request.getParameter("price"))));
        errors.add(validateStartTime(request.getParameter("startTime")));
        errors.add(validateEndTime(request.getParameter("endTime"), request.getParameter("startTime")));
        errors.add(checkNameExist(request.getParameter("cruiseName")));
        errors.add(checkingTheShipIsFree(Integer.parseInt(request.getParameter("shipId"))
                , request.getParameter("startTime"), request.getParameter("endTime")));
        errors.removeIf(Objects::isNull);
        return errors;
    }

    private String validateCruiseName(String cruiseName) {
        return cruiseName != null && cruiseName.length() >= 2 && cruiseName.length() < 32 ? null : phrases.get("langCruiseNameIsWrong");
    }

    private String validateNumberOfPorts(int numberOfPorts) {
        return numberOfPorts > 0 ? null : phrases.get("langNumberOfPortsIsWrong");
    }

    private String validatePrice(double price) {
        return price > 0 ? null : phrases.get("langPriceIsWrong");
    }

    private String validateStartTime(String date) {
        LocalDateTime startTime;
        if (date == null) return phrases.get("langStartTimeIsWrong");
        try {
            startTime = LocalDateTime.parse(date);
        } catch (DateTimeException e) {
            return phrases.get("langStartTimeIsWrong");
        }
        return startTime.isBefore(LocalDateTime.now()) || startTime.isAfter(startTime.plusYears(1))
                ? phrases.get("langStartTimeIsWrong") : null;
    }

    private String validateEndTime(String end, String start) {
        LocalDateTime endTime;
        LocalDateTime startTime;
        if (end == null || start == null) return phrases.get("langEndTimeIsWrong");
        try {
            startTime = LocalDateTime.parse(start);
            endTime = LocalDateTime.parse(end);
        } catch (DateTimeException e) {
            return phrases.get("langEndTimeIsWrong");
        }
        return endTime.isBefore(startTime) || endTime.isAfter(endTime.plusYears(1))
                ? phrases.get("langEndTimeIsWrong") : null;
    }

    private String checkNameExist(String cruiseName) {
        return cruisesDAO.cruiseNameExist(cruiseName) ? phrases.get("langAlreadyExist") : null;
    }

    private String checkingTheShipIsFree(int shipId, String start, String end) {
        return cruisesDAO.checkingTheShipIsFreeOnDates(shipId, start, end) ? null : phrases.get("langShipIsBusy");
    }

}

