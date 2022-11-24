package models.routes;

import lombok.Data;

import java.util.List;

@Data
public class Route {
    private int id;
    private String name;
    private List<String> route;

    public Route(String name, List<String> route) {
        this.name = name;
        this.route = route;
    }
}
