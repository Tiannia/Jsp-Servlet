package com.lmonkey.servlet.order;

import com.lmonkey.entity.LMONKEY_USER;
import com.lmonkey.entity.ORDER_DETAIL;
import com.lmonkey.entity.ORDER_INFO;
import com.lmonkey.entity.RECEIVE_ADDRESS;
import com.lmonkey.service.ORDER_INFODao;
import com.lmonkey.service.TIAN_ADDRESSDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

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

