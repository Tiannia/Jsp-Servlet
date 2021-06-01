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

@WebServlet(name = "DoAddressUpdate")
public class DoAddressUpdate extends HttpServlet {
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
        int receive_id = Integer.parseInt(request.getParameter("reid"));
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
                receive_id,
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
        int count = TIAN_ADDRESSDao.update(ar);
        if(count > 0) {
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('地址修改成功，已保存至用户信息！');");
            out.write("location.href='/book_mall/orderselect?eids="+ eids + "'");
            out.write("</script>");
        }
        else{
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('地址修改失败，请检查！');");
            out.write("location.href='/book_mall/orderselect?eids="+ eids + "'");
            out.write("</script>");
        }
    }
}
