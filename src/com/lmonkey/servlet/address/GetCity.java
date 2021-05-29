package com.lmonkey.servlet.address;

import com.lmonkey.entity.ADDRESS_AREA;
import com.lmonkey.service.ADDRESS_AREADao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.google.gson.Gson;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class GetCity
 */

@WebServlet("/areagetcity")
public class GetCity extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");

        String id = request.getParameter("province");

        if (id == "0") return;

        String province = id.substring(0, 2);

        ArrayList<ADDRESS_AREA> list = ADDRESS_AREADao.selectCity(id, province, 1);

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
