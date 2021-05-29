package com.lmonkey.servlet.order;

import com.lmonkey.service.ORDER_INFODao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/manage/admin_doorderdelete")
public class DoOrderDelete extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html charset=utf-8");
        String order_sn = request.getParameter("sn");
        int count = ORDER_INFODao.DeleteOrder(order_sn);
        if (count > 0) {
            response.sendRedirect("admin_getorderinfo");
        }
    }
}
