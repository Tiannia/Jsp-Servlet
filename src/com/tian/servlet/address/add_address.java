package com.tian.servlet.address;

import com.tian.entity.RECEIVE_ADDRESS;
import com.tian.service.ADDRESS_AREADao;
import com.tian.service.TIAN_ADDRESSDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/add_address")
public class add_address extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String id = request.getParameter("id");
        String receive_name = request.getParameter("receive_name");
        String province_id = request.getParameter("province");
        String city_id = request.getParameter("city");
        String district_id= request.getParameter("district");
        int send_place_id = Integer.parseInt(request.getParameter("send_place"));
        String address = request.getParameter("address");
        String zipcode = request.getParameter("zipcode");
        String telephone = request.getParameter("telephone");
        String eids = request.getParameter("eids");

        String province = ADDRESS_AREADao.selectById(province_id);
        String city = ADDRESS_AREADao.selectById(city_id);
        String district = ADDRESS_AREADao.selectById(district_id);
        String send_place = null;
        String[] send_place_all = {"学校","家庭","公司"};
        if(send_place_id == 0) {
            send_place = "无";
        }
        else
            send_place = send_place_all[send_place_id - 1];

        RECEIVE_ADDRESS ar = new RECEIVE_ADDRESS(
                0,
                id,
                receive_name,
                province,
                city,
                district,
                send_place,
                address,
                zipcode,
                telephone
        );
        int count = TIAN_ADDRESSDao.insert(ar);
        //response.sendRedirect("orderselect?eids="+ eids);
//        类型 状态报告
//        消息 此URL不支持Http方法POST
//        描述 请求行中接收的方法由源服务器知道，但目标资源不支持
        //request.setAttribute("eids",eids);
        //request.getRequestDispatcher("orderselect").forward(request, response);
        if(count > 0) {
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('地址添加成功！');");
            out.write("location.href='/book_mall/orderselect?eids="+ eids + "'");
            out.write("</script>");
        }
        else{
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('地址添加失败，请检查！');");
            out.write("location.href='/book_mall/orderselect?eids="+ eids + "'");
            out.write("</script>");
        }
    }
}
