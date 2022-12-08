package models.cruises;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Cruise {
    private int id;
    private int shipId;
    private String shipName;
    private String cruiseName;
    private int numberOfPorts;
    private double price;
    private String status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int duration;

    public Cruise(int shipId, String shipName, String cruiseName, int numberOfPorts
            , double price, LocalDateTime startTime, LocalDateTime endTime) {
        this.shipId = shipId;
        this.shipName = shipName;
        this.cruiseName = cruiseName;
        this.numberOfPorts = numberOfPorts;
        this.price = price;
        this.status = "not started";
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = endTime.getDayOfYear() - startTime.getDayOfYear();
    }

    public String getStartTimeString() {
        return startTime.toLocalDate()+" "+startTime.toLocalTime();
    }

    public String getEndTimeString() {
        return endTime.toLocalDate()+" "+endTime.toLocalTime();
    }
}
