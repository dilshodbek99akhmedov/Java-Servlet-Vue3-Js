package uz.dk.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarCreateDto {
    private String model;
    private String color;
    private float price;
    private int speed;
}
