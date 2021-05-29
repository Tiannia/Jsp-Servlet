package com.lmonkey.servlet.cart;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lmonkey.entity.LMONKEY_CART;
import com.lmonkey.entity.LMONKEY_PRODUCT;
import com.lmonkey.entity.LMONKEY_USER;
import com.lmonkey.service.LMONKEY_CARTDao;
import com.lmonkey.service.LMONKEY_PRODUCTDao;

/**
 * Servlet implementation class CartAdd
 */
@WebServlet("/cartadd")
public class CartAdd extends HttpServlet {
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        LMONKEY_PRODUCT p = null;
        String pid = request.getParameter("id");
        String count = request.getParameter("count");
        String url = request.getParameter("url");

        HttpSession session = request.getSession();

        String isLogin = String.valueOf(session.getAttribute("isLogin"));

        LMONKEY_USER user = (LMONKEY_USER) session.getAttribute("name");//获取用户信息
        if (user != null && isLogin.equals("1")) {

            String uid = user.getUSER_ID();

            //通过用户ID和购物车中的商品ID 查看有没有这条记录

            LMONKEY_CART src_record = LMONKEY_CARTDao.getCartShop(uid, pid);

            if (src_record != null) {
                int srccount = src_record.getCart_quantity();
                int newcount = srccount + Integer.parseInt(count);

                if (newcount >= 5) {
                    newcount = 5;
                }
                //根据购物车id更新 对应购物车商品数量
                LMONKEY_CARTDao.updatenum(src_record.getCart_id(), newcount);

            } else {

                if (pid != null) {
                    p = LMONKEY_PRODUCTDao.selectById(Integer.parseInt(pid));
                }

                LMONKEY_CART cart = new LMONKEY_CART(
                        0,
                        p.getPRODUCT_FILENAME(),
                        p.getPRODUCT_NAME(),
                        p.getPRODUCT_PRICE(),
                        Integer.parseInt(count),
                        p.getPRODUCT_STOCK(),
                        p.getPRODUCT_ID(),
                        uid,
                        1
                );
                LMONKEY_CARTDao.insert(cart);
            }
        } else {
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('请先登陆再购买');");
            out.write("location.href='login.jsp'");
            out.write("</script>");
            out.close();
            return;
        }
        //你自己去访问下面的servlet吧
        if (url.equals("z")) {
            response.sendRedirect("showcart");
        } else {
            response.sendRedirect("selectproductview?id=" + pid);
        }


    }

}
