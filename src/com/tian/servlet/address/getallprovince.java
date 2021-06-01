package com.tian.servlet.address;

import com.tian.entity.ADDRESS_AREA;
import com.tian.service.ADDRESS_AREADao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "getprovince")
public class getallprovince extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html charset=utf-8");

        ArrayList<ADDRESS_AREA> list = ADDRESS_AREADao.selectAll();
        if(list != null){
            request.setAttribute("province_list", list);
        }
        request.getRequestDispatcher("#").forward(request,response);
    }
}
