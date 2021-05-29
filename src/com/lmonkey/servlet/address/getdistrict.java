package com.lmonkey.servlet.address;

import com.lmonkey.entity.ADDRESS_AREA;
import com.lmonkey.service.ADDRESS_AREADao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.google.gson.Gson;

@WebServlet("/areagetdistrict")
public class getdistrict extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("city");
        if (id == "0") return;

        String city = id.substring(0, 4);

        ArrayList<ADDRESS_AREA> list = ADDRESS_AREADao.selectCity(id, city, 2);

        PrintWriter out = response.getWriter();

        if (list != null) {
            Gson gson = new Gson();
            String info = gson.toJson(list);
            System.out.println(info);
            out.write(info);
            out.close();
        }
    }
}
