package uz.dk.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import uz.dk.model.Car;
import uz.dk.model.TotalData;
import uz.dk.service.CarService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static uz.dk.constants.Constants.frontUrl;

@WebServlet("/api/cars")
public class CarGetController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        CarService service = new CarService();

        resp.setContentType("application/json; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(200);
        resp.addHeader("Access-Control-Allow-Origin", frontUrl);

        String page = req.getParameter("page");
        String size = req.getParameter("limit");
        String ord = req.getParameter("ord");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        TotalData totalData = new TotalData();
        totalData.setCars(service.getAll(Integer.parseInt(page), Integer.parseInt(size), Boolean.valueOf(ord)));
        totalData.setPages(service.getPage());
        String toJson = gson.toJson(totalData);
        PrintWriter out = resp.getWriter();
        out.print(toJson);

    }
}
