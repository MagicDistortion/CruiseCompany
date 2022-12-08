package models.ships;

import lombok.Data;

@Data
public class Ship {
    private int id;
    private String name;
    private int capacity;
    private String current_point;

    public Ship(String name, int capacity, String current_point) {
        this.name = name;
        this.capacity = capacity;
        this.current_point = current_point;
    }
}
