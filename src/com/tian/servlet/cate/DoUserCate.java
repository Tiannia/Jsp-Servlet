package com.tian.servlet.cate;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tian.entity.LMONKEY_CATEGORY;
import com.tian.service.LMONKEY_CATEGORYDao;

/**
 * Servlet implementation class DoUserCate
 */
@WebServlet("/manage/admin_docateadd")
public class DoUserCate extends HttpServlet {

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html charset=utf-8");

        int fid = Integer.parseInt(request.getParameter("parentId"));
        String name = request.getParameter("className");

        LMONKEY_CATEGORY cate = new LMONKEY_CATEGORY(0, name, fid);
        LMONKEY_CATEGORYDao.insert(cate);
        response.sendRedirect("admin_cateselect");
    }

}
