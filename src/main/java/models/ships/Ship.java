package models.ships;

import lombok.Data;

import java.io.Serializable;

@Data
public class Ship implements Serializable {
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
