package models.ships;

import lombok.Data;

@Data
public class Ship {
    private int id;
    private int capacity;
    private String current_point;

    public Ship(int capacity, String current_point) {
        this.capacity = capacity;
        this.current_point = current_point;
    }
}
