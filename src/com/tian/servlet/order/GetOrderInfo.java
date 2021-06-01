package com.tian.servlet.order;

import com.tian.entity.ORDER_INFO;
import com.tian.service.ORDER_INFODao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/manage/admin_getorderinfo")
public class GetOrderInfo extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");


        //获取所有用户所有订单
        ArrayList<ORDER_INFO> orderlist = ORDER_INFODao.GetAllOrderInfo();

        request.setAttribute("orderlist", orderlist);

        request.getRequestDispatcher("../manger/admin_order.jsp").forward(request, response);
    }
}

