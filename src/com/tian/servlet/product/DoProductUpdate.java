package com.tian.servlet.product;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tian.entity.LMONKEY_PRODUCT;
import com.tian.service.LMONKEY_PRODUCTDao;

/**
 * Servlet implementation class DoProductUpdate
 */
@WebServlet("/manage/admin_doproductupdate")
public class DoProductUpdate extends HttpServlet {


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html charset=utf-8");

        String name = request.getParameter("productName");
        int price = Integer.parseInt(request.getParameter("productPrice"));
        String desc = request.getParameter("productDesc");
        String id = request.getParameter("parentId");
        int product_id = Integer.parseInt(request.getParameter("id"));
        int stock = Integer.parseInt(request.getParameter("productStock"));


        //创建用户实体
        LMONKEY_PRODUCT product = new LMONKEY_PRODUCT(
                product_id,
                name,
                desc,
                price,
                stock,
                Integer.parseInt(id.split("-")[0]),
                Integer.parseInt(id.split("-")[1]),
                null
        );


        int count = LMONKEY_PRODUCTDao.update(product);
        //System.out.print(u);
        //成功或失败的去向
        PrintWriter pw = response.getWriter();
        if (count > 0) {
            response.sendRedirect("/book_mall/manage/admin_productselect");

        } else {
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alter('商品修改失败')");
            out.write("location.href='manage/admin_toproductupdate");
            out.write("</script>");

        }


    }

}
