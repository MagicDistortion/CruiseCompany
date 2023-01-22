package models.route;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Route {
    private int id;
    private String name;
    private String route;

    public Route(String name, String route) {
        this.name = name;
        this.route = route;
    }

    public static void main(String[] args) {
        String routeString = "Odesa, mariupol, stambul, alzhir, odesa";
        List<String> collect= Arrays.stream("Odesa, mariupol, stambul, alzhir, odesa".split(","))
                .collect(Collectors.toList());
        Route route = new Route("Romantic",routeString);
    }
}
