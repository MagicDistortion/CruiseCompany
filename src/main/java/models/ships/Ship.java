package models.ships;

import lombok.Data;

import java.io.Serializable;

@Data
public class Ship implements Serializable {
    private int id;
    private String name;
    private int capacity;
    private String image;

    public Ship(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }
}
