package com.tian.servlet.cate;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tian.entity.LMONKEY_CATEGORY;
import com.tian.service.LMONKEY_CATEGORYDao;

/**
 * Servlet implementation class CateSelect
 */
@WebServlet("/manage/admin_cateselect")
public class CateSelect extends HttpServlet {

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<LMONKEY_CATEGORY> catelist = LMONKEY_CATEGORYDao.selectAll();

        request.setAttribute("catelist", catelist);

        request.getRequestDispatcher("/manger/admin_cate.jsp").forward(request, response);


    }


}
