package com.lmonkey.servlet.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lmonkey.entity.LMONKEY_CART;
import com.lmonkey.entity.LMONKEY_CATEGORY;
import com.lmonkey.entity.LMONKEY_USER;
import com.lmonkey.service.LMONKEY_CARTDao;
import com.lmonkey.service.LMONKEY_CATEGORYDao;

/**
 * Servlet implementation class ShowCart
 */
@WebServlet("/showcart")
public class ShowCart extends HttpServlet {

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        /* show head ding category: */
//        ArrayList<LMONKEY_CATEGORY> flist = LMONKEY_CATEGORYDao.selectCat("father");
//        request.setAttribute("flist", flist);
//
//        ArrayList<LMONKEY_CATEGORY> clist = LMONKEY_CATEGORYDao.selectCat("child");
//        request.setAttribute("clist", clist);
        /*                           */
        HttpSession session = request.getSession();

        String isLogin = String.valueOf(session.getAttribute("isLogin"));//Integer to String

        LMONKEY_USER user = (LMONKEY_USER) session.getAttribute("name");

        if (user != null && isLogin.equals("1")) {
            String uid = (String) user.getUSER_ID();
            //获取该用户的购物车清单，从数据库中采集
            ArrayList<LMONKEY_CART> list = LMONKEY_CARTDao.getCart(uid);
            request.setAttribute("shoplist", list);
            request.getRequestDispatcher("cart.jsp").forward(request, response);

        } else {
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('请先登录再进入购物车');");
            out.write("location.href='login.jsp'");
            out.write("</script>");
            out.close();
            return;
        }

    }


}
