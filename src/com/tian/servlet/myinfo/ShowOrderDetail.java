package com.tian.servlet.myinfo;

import com.tian.entity.LMONKEY_USER;
import com.tian.entity.ORDER_DETAIL;
import com.tian.entity.ORDER_INFO;
import com.tian.entity.RECEIVE_ADDRESS;
import com.tian.service.ORDER_INFODao;
import com.tian.service.TIAN_ADDRESSDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/showorderdetail")
public class ShowOrderDetail extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        String isLogin = String.valueOf(session.getAttribute("isLogin"));
        LMONKEY_USER user = (LMONKEY_USER) session.getAttribute("name");
        int oid = Integer.parseInt(request.getParameter("oid")); //获取订单ID
        if (user != null && isLogin.equals("1")) {

            ORDER_INFO odinfo = ORDER_INFODao.selectByOrderID(oid);
            ArrayList<ORDER_DETAIL> delist = ORDER_INFODao.GetOrderDetailByOrderID(oid);//产品详情
            RECEIVE_ADDRESS address = TIAN_ADDRESSDao.GetAddressByID(odinfo.getReceive_address_id());//订单对应的地址信息

            request.setAttribute("detaillist", delist);
            request.setAttribute("adinfo", address);
            request.setAttribute("odinfo", odinfo);
            request.getRequestDispatcher("./orderxq.jsp").forward(request, response);
        }
        else{
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('请先登陆账户');");
            out.write("location.href='login.jsp'");
            out.write("</script>");
            out.close();
            return;
        }
    }
}
