package drivezero.models;

import lombok.Getter;
import lombok.Setter;
public class User {
    // TODO ...

    @Getter @Setter
    private String name;

    @Getter @Setter
    private int id;

    public User() {
        name = "default";
        id = 0;
    }
}