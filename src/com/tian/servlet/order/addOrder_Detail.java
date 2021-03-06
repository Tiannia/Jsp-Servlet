package com.tian.servlet.order;

import com.tian.entity.*;
import com.tian.service.LMONKEY_CARTDao;
import com.tian.service.LMONKEY_PRODUCTDao;
import com.tian.service.ORDER_INFODao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addorder_detail")
public class addOrder_Detail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();

        String isLogin = String.valueOf(session.getAttribute("isLogin"));
        LMONKEY_USER user = (LMONKEY_USER) session.getAttribute("name");
        String uid = user.getUSER_ID();
        String eids = request.getParameter("eids");
        String oid = request.getParameter("oid");

        String ids[] = eids.split(",");
        List<LMONKEY_CART> list = new ArrayList<LMONKEY_CART>();
        for (int i = 0; i < ids.length; i++) {
            LMONKEY_CART es = LMONKEY_CARTDao.getCartShop(ids[i]); //根据购物车商品id来获取其全部信息（数量，名称，单价，产品id，是否被购买->要使得valid变成0）
            LMONKEY_CARTDao.DeleteCart(Integer.parseInt(ids[i])); //这个地方可以删除了（也可以让valid=0,做成未支付的订单样式）
            //更新库存——获取库存并将数量更新即可，这里已经获取购物车的信息了，使用cart_p_id, cart_p_quantity即可
            int ProductStock = LMONKEY_PRODUCTDao.selectStockByID(es.getCart_p_id());
            LMONKEY_PRODUCTDao.updateStock(ProductStock - es.getCart_quantity(), es.getCart_p_id());
            list.add(es);
        }
        //根据list逐个插入到order_detail中，带上order_id
        for(int i = 0; i < list.size(); ++i){
            ORDER_DETAIL or = new ORDER_DETAIL(
                    0,
                    Integer.parseInt(oid),
                    list.get(i).getCart_p_id(),
                    list.get(i).getCart_p_name(),
                    list.get(i).getCart_quantity(),
                    list.get(i).getCart_p_price(),
                    list.get(i).getCart_p_filename()
            );
            int count = ORDER_INFODao.insert_detail(or); //插入订单详情到订单详情表中
        }
        request.getRequestDispatcher("./ok.jsp").forward(request, response);
    }
}
