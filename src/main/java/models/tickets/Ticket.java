package models.tickets;

import lombok.Data;
import models.cruises.Cruise;

@Data
public class Ticket {
    private int id;
    private int cruiseId;
    private String cruiseName;
    private int duration;
    private int userId;
    private int numberOfPassengers;
    private double totalPrice;
    private double price;
    private String status;

    public Ticket(Cruise cruise, int userId, int numberOfPassengers) {
        this.cruiseId = cruise.getId();
        this.cruiseName=cruise.getCruiseName();
        this.duration=cruise.getDuration();
        this.userId = userId;
        this.price = cruise.getPrice();
        this.numberOfPassengers = numberOfPassengers;
        this.totalPrice = cruise.getPrice() * numberOfPassengers;
        this.status = "not paid";
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
        this.totalPrice = price * numberOfPassengers;
    }
}
