package uz.dk.mapper;

import uz.dk.dto.CarUpdateDto;
import uz.dk.model.Car;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class CarMapper {
    public void getCar(ResultSet resultSet, Car car) throws SQLException {
        car.setId(Integer.parseInt(resultSet.getString("id")));
        car.setModel(resultSet.getString("model"));
        car.setColor(resultSet.getString("color"));
        car.setPrice(Float.parseFloat(resultSet.getString("price")));
        car.setSpeed(Integer.parseInt(resultSet.getString("speed")));
    }

    public Car fromUpdateDto(CarUpdateDto dto, Car car) {
        if (Objects.nonNull(dto.getModel()) && !dto.getModel().isBlank())
            car.setModel(dto.getModel());

        if (Objects.nonNull(dto.getColor()) && !dto.getColor().isBlank())
            car.setColor(dto.getColor());

        if (dto.getPrice() > 0)
            car.setPrice(dto.getPrice());

        if (dto.getSpeed() > 0)
            car.setSpeed(dto.getSpeed());

        return car;
    }
}
