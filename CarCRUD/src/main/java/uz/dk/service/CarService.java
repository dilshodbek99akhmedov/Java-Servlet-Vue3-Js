package uz.dk.service;

import uz.dk.dao.DbConnection;
import uz.dk.dto.CarCreateDto;
import uz.dk.dto.CarUpdateDto;
import uz.dk.mapper.CarMapper;
import uz.dk.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarService {

    private final DbConnection dbConnection = new DbConnection();
    private final CarMapper carMapper = new CarMapper();

    public boolean create(CarCreateDto dto) {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO car(model, color, price, speed) VALUES (?, ?, ?, ?);");
            preparedStatement.setString(1, dto.getModel());
            preparedStatement.setString(2, dto.getColor());
            preparedStatement.setFloat(3, dto.getPrice());
            preparedStatement.setInt(4, dto.getSpeed());
            preparedStatement.execute();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public Car get(Integer id) {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM car WHERE id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Car car = new Car();
            while (resultSet.next()) {
                carMapper.getCar(resultSet, car);
            }
            return car;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Car> getAll(Integer page, Integer size, Boolean ord) {
        Connection connection = dbConnection.getConnection();
        try {
            String sql;
            if (ord) sql = "SELECT * FROM car ORDER BY id ASC limit ? OFFSET ?;";
            else sql = "SELECT * FROM car ORDER BY id DESC limit ? OFFSET ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, size);
            preparedStatement.setInt(2, (page - 1) * size);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Car> cars = new ArrayList<>();
            while (resultSet.next()) {
                Car car = new Car();
                carMapper.getCar(resultSet, car);
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getPage() {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT count(*) FROM car");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    public boolean update(CarUpdateDto dto) {
        Connection connection = dbConnection.getConnection();
        try {
            Car car = get(dto.getId());
            car = carMapper.fromUpdateDto(dto, car);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE car SET model = ?, color = ?, price = ?, speed = ? WHERE id = ?;");
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setString(2, car.getColor());
            preparedStatement.setFloat(3, car.getPrice());
            preparedStatement.setInt(4, car.getSpeed());
            preparedStatement.setInt(5, car.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean delete(Integer id) {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM car WHERE id = ?;");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }


}


