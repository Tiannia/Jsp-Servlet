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
import java.util.HashMap;

@WebServlet("/showorderinfo")
public class ShowOrderInfo extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        String isLogin = String.valueOf(session.getAttribute("isLogin"));
        LMONKEY_USER user = (LMONKEY_USER) session.getAttribute("name");

        HashMap<Integer, ORDER_DETAIL> info_detail = new HashMap<>();//对应订单的一个产品信息
        if (user != null && isLogin.equals("1")) {

            //获取该用户所有订单
            ArrayList<ORDER_INFO> orderlist = ORDER_INFODao.selectByUserID(user.getUSER_ID());
            ArrayList<RECEIVE_ADDRESS> addlist = new ArrayList<>();

            for(int i = 0; i < orderlist.size(); ++i){
                int order_id = orderlist.get(i).getOrder_id();
                int receive_address_id = orderlist.get(i).getReceive_address_id();

                ArrayList<ORDER_DETAIL> delist = ORDER_INFODao.GetOrderDetailByOrderID(order_id);//产品详情
                RECEIVE_ADDRESS address = TIAN_ADDRESSDao.GetAddressByID(receive_address_id);//订单对应的地址信息

                addlist.add(address);
                info_detail.put(order_id, delist.get(0)); //拿到第一个产品详情就行，我们只需要挂图片和名称和数量
            }
            request.setAttribute("orderlist",orderlist);
            request.setAttribute("info_detail",info_detail);
            request.setAttribute("addresslist", addlist);
            request.getRequestDispatcher("./myorderq.jsp").forward(request, response);
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
