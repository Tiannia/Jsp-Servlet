package com.tian.servlet.myinfo;

import com.tian.service.ORDER_INFODao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/comfirmgoods")
public class ComfirmGoods extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String sn = request.getParameter("sn");
        String receive_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        int count = ORDER_INFODao.DoReceive(receive_time, sn);
        if(count > 0){
            response.sendRedirect("showorderinfo");
        }
    }
}
