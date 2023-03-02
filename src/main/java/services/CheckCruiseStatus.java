package services;

import dao.CruisesDAO;
import dao.DBManager;
import models.Cruise;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* класс перевірки статусів круїзів  */
public class CheckCruiseStatus {
    private static CheckCruiseStatus instance;

    public static synchronized CheckCruiseStatus getInstance() {
        if (instance == null) instance = new CheckCruiseStatus();
        return instance;
    }

    /* метод раз на хвилину перевіряє статуси незавершених круїзів  */
    public void checkStatus() {
        CruisesDAO cruisesDAO = new CruisesDAO();
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                DBManager.getInstance().inTransaction(connection -> {
                    List<Cruise> cruisesList = cruisesDAO.findAllCruises();
                    LocalDateTime timeNow = LocalDateTime.now();
                    cruisesList.forEach(cruise -> {
                        LocalDateTime startTime = cruise.getStartTime();
                        LocalDateTime endTime = cruise.getEndTime();
                        if (timeNow.isAfter(startTime) && timeNow.isBefore(endTime) && cruise.getStatus().equals("didn`t start"))
                            cruisesDAO.updateStatus(connection, "started", cruise.getId());
                        if (timeNow.isAfter(endTime) && cruise.getStatus().equals("started"))
                            cruisesDAO.updateStatus(connection, "completed", cruise.getId());
                    });
                });
            }
        }, 60 * 1000, 60 * 1000);
    }
}
