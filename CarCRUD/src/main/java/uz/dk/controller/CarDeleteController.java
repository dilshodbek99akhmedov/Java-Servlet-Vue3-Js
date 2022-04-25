package uz.dk.controller;

import uz.dk.service.CarService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static uz.dk.constants.Constants.frontUrl;

@WebServlet("/api/car/delete")
public class CarDeleteController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        CarService service = new CarService();
        service.delete(Integer.parseInt(id));
        resp.setContentType("application/json; charset=UTF-8");
        resp.setStatus(200);
        resp.addHeader("Access-Control-Allow-Origin", frontUrl);
    }
}
