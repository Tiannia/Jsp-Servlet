package com.lmonkey.servlet.order;

import com.lmonkey.entity.LMONKEY_CART;
import com.lmonkey.entity.LMONKEY_USER;
import com.lmonkey.entity.ORDER_INFO;
import com.lmonkey.service.LMONKEY_CARTDao;
import com.lmonkey.service.ORDER_INFODao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/addorder")
public class addOrder extends HttpServlet {
    public static final String PREFIX1 = "LT";
    public static final String PREFIX2 = "KD";
    private static long code;
    private static final HashMap<String, Integer> py = new HashMap<String, Integer>() {
        {
            put("zhifubao", 1);
            put("weixin", 2);
            put("yinlian", 3);
            put("daofu", 4);
        }
    };
    private static final HashMap<String, String> sn = new HashMap<String, String>() {
        {
            put("shunfeng", "顺丰快递");
            put("baishi", "百世汇通");
            put("yuantong", "圆通快递");
            put("shentong", "申通快递");
        }
    };

    public static synchronized String nextCode1() {
        code++;
        String str = new SimpleDateFormat("yyyyMMdd").format(new Date());
        long m = Long.parseLong((str)) * 10000;
        Random r = new Random();
        m += code + r.nextInt(5000);
        return PREFIX1 + m;
    }

    public static synchronized String nextCode2() {
        code++;
        String str = new SimpleDateFormat("yyyyMMdd").format(new Date());
        long m = Long.parseLong((str)) * 10000;
        Random r = new Random();
        m += code + r.nextInt(8000);
        return PREFIX2 + m;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //user:
        HttpSession session = request.getSession();

        String isLogin = String.valueOf(session.getAttribute("isLogin"));
        LMONKEY_USER user = (LMONKEY_USER) session.getAttribute("name");

        if (user != null && isLogin.equals("1")) {
            String order_sn = nextCode1();
            String addinfo = request.getParameter("addinfo");
            String eids = request.getParameter("eids");
            String ids[] = addinfo.split(",");
            if (ids.length < 3) return;
            int receive_address_id = Integer.parseInt(ids[0]);
            int payment_method = py.get(ids[1]);
            String shipping_comp_name = sn.get(ids[2]);
            String pay_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            int payment_money = Integer.parseInt(request.getParameter("money"));
            String shipping_sn = nextCode2();
            String shipping_time = null;
            String receive_time = null;
            int order_status = 0;//未发货
            System.out.println(pay_time);
            ORDER_INFO od = new ORDER_INFO(
                    0,
                    order_sn,
                    user.getUSER_ID(),
                    receive_address_id,
                    payment_method,
                    payment_money,
                    pay_time,
                    shipping_comp_name,
                    shipping_sn,
                    shipping_time,
                    receive_time,
                    order_status
            );
            int count = ORDER_INFODao.insert(od);
            System.out.println(order_sn + " " + shipping_sn);
            if (count > 0) {
                int oid = ORDER_INFODao.getOrderID(order_sn);
                //带上购物车号和订单号完事
                //response.redirect 是get 请求
                response.sendRedirect("addorder_detail?eids=" + eids + "&oid=" + oid);
            }
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
