package com.tian.servlet.home;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tian.entity.LMONKEY_CATEGORY;
import com.tian.entity.LMONKEY_PRODUCT;
import com.tian.service.LMONKEY_CATEGORYDao;
import com.tian.service.LMONKEY_PRODUCTDao;

/**
 * Servlet implementation class SelectProductList
 */
@WebServlet("/selectproductlist")
public class SelectProductList extends HttpServlet {


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        ArrayList<LMONKEY_CATEGORY> flist = LMONKEY_CATEGORYDao.selectCat("father");
        request.setAttribute("flist", flist);

        ArrayList<LMONKEY_CATEGORY> clist = LMONKEY_CATEGORYDao.selectCat("child");
        request.setAttribute("clist", clist);


        String fid = request.getParameter("fid");
        String cid = request.getParameter("cid");

        int id = 0;

        ArrayList<LMONKEY_PRODUCT> list = null;

        if (fid != null) {
            id = Integer.parseInt(fid);
            list = LMONKEY_PRODUCTDao.selectByAllFid(id);

        }
        if (cid != null) {
            id = Integer.parseInt(cid);
            list = LMONKEY_PRODUCTDao.selectByAllCid(id);
        }

        //获取分类名称
        request.setAttribute("title", LMONKEY_CATEGORYDao.selectById(id).getCATE_NAME());

        request.setAttribute("list", list);
        request.getRequestDispatcher("productlist.jsp").forward(request, response);


    }


}
