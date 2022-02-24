package drivezero.models;

import lombok.Getter;
import lombok.Setter;
public class User {
    // TODO ...

    @Getter @Setter
    private String name;

    @Getter @Setter
    private int id;

    @Getter @Setter
    private int age;

    @Getter @Setter
    private String location;

    @Getter @Setter
    private String carModel;

    @Getter @Setter
    private int carAge;

    @Getter @Setter
    private String carUse;

    @Getter @Setter
    private int carMiles;

    @Getter @Setter
    private double goal;

    Getter @Setter
    private String carbonInterest;

    Getter @Setter
    private String reduceIdeas;
    
    public User() {
        name = "default";
        id = 0;
        age = 0;
        location = "";
        carModel = "";
        carAge = 0;
        carUse = "";
        carMiles = 0;
        goal = 0.0;
        carbonInterest = "";
        reduceIdeas = "";
    }


}