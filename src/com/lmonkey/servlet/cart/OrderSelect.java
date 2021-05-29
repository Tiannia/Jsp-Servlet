package com.lmonkey.servlet.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lmonkey.entity.ADDRESS_AREA;
import com.lmonkey.entity.LMONKEY_CART;
import com.lmonkey.entity.LMONKEY_USER;
import com.lmonkey.entity.RECEIVE_ADDRESS;
import com.lmonkey.service.ADDRESS_AREADao;
import com.lmonkey.service.LMONKEY_CARTDao;
import com.lmonkey.service.TIAN_ADDRESSDao;

/**
 * Servlet implementation class OrderSelect
 */
@WebServlet("/orderselect")
public class OrderSelect extends HttpServlet {

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();

        String isLogin = String.valueOf(session.getAttribute("isLogin"));
        LMONKEY_USER user = (LMONKEY_USER) session.getAttribute("name");
        String uid = user.getUSER_ID();
        String eids = request.getParameter("eids");

        RECEIVE_ADDRESS defad = null;
        if (user != null && isLogin.equals("1")) {

            List<RECEIVE_ADDRESS> addlist = TIAN_ADDRESSDao.getAddress(uid);
            if(addlist.size() != 0)
                defad = addlist.get(0); //默认地址


            int total_price = 0;
            String ids[] = eids.split(",");
            List<LMONKEY_CART> list = new ArrayList<LMONKEY_CART>();
            for (int i = 0; i < ids.length; i++) {
                LMONKEY_CART es = LMONKEY_CARTDao.getCartShop(ids[i]); //根据购物车商品id来获取其全部信息
                int single_price = es.getCart_p_price() * es.getCart_quantity();
                total_price += single_price;
                list.add(es);
            }
            ArrayList<ADDRESS_AREA> list1 = ADDRESS_AREADao.selectAll();

            if(list != null){
                request.setAttribute("province_list", list1);
            }

            request.setAttribute("shoplist", list);
            request.setAttribute("totalprice", total_price);
            request.setAttribute("eids", eids);
            request.setAttribute("addresslist", addlist);
            request.setAttribute("defad", defad);
            request.getRequestDispatcher("order.jsp").forward(request, response);


        } else {
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
