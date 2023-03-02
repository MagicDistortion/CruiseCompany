package models;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Route {
    private int id;
    private String name;
    private String route;
    private List<String> list;

    public Route(String name, String route) {
        this.name = name;
        this.route = route;
        this.list = Arrays.stream(route.split(",")).collect(Collectors.toList());
    }
}
