package uz.dk.controller;

import uz.dk.dto.CarUpdateDto;
import uz.dk.service.CarService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static uz.dk.constants.Constants.frontUrl;

@WebServlet("/api/car/update")
public class CarPutController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarService service = new CarService();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        resp.addHeader("Access-Control-Allow-Origin", frontUrl);
        CarUpdateDto dto = new CarUpdateDto();
        dto.setId(Integer.parseInt(req.getParameter("id")));
        dto.setModel(req.getParameter("model"));
        dto.setColor(req.getParameter("color"));
        String price = req.getParameter("price");
        if (price != null && price.length() > 0) dto.setPrice(Float.parseFloat(price));
        else dto.setPrice(0);
        String speed = req.getParameter("speed");
        if (speed != null && speed.length() > 0) dto.setSpeed(Integer.parseInt(speed));
        else dto.setSpeed(0);
        service.update(dto);
        resp.setStatus(200);
    }
}
