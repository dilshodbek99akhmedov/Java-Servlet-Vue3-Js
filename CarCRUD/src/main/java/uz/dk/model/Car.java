package uz.dk.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private int id;
    private String model;
    private String color;
    private float price;
    private int speed;
}
