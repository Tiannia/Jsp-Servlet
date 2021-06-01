package com.tian.servlet.home;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tian.entity.LMONKEY_CATEGORY;
import com.tian.entity.LMONKEY_PRODUCT;
import com.tian.service.LMONKEY_CATEGORYDao;
import com.tian.service.LMONKEY_PRODUCTDao;

/**
 * Servlet implementation class SelectProductView
 */
@WebServlet("/selectproductview")
public class SelectProductView extends HttpServlet {

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        ArrayList<LMONKEY_CATEGORY> flist = LMONKEY_CATEGORYDao.selectCat("father");
        request.setAttribute("flist", flist);

        ArrayList<LMONKEY_CATEGORY> clist = LMONKEY_CATEGORYDao.selectCat("child");
        request.setAttribute("clist", clist);

        String id = request.getParameter("id");

        //使用Session获取最近访问

        HttpSession session = request.getSession();

        //从session获取ids

        ArrayList<Integer> ids = (ArrayList<Integer>) session.getAttribute("ids");
        if (ids == null) {
            ids = new ArrayList<Integer>();

        }
        //最多放5，如果多出5个将第一个删除

        if (ids.size() >= 5) {
            ids.remove(0);
        }

        //添加列表里，但只要一份
        if (id != null && (!ids.contains(Integer.parseInt(id)))) {
            ids.add(Integer.parseInt(id));

        }
        session.setAttribute("ids", ids);

        if (ids != null) {
            ArrayList<LMONKEY_PRODUCT> lastlylist = LMONKEY_PRODUCTDao.selectAllById(ids);
            request.setAttribute("lastlylist", lastlylist); //最近商品访问列表
        }


        LMONKEY_PRODUCT p = null;
        if (id != null) {
            p = LMONKEY_PRODUCTDao.selectById(Integer.parseInt(id));
            request.setAttribute("p", p);
        }

        if (p != null) { //获取商品分类信息
            int cid = p.getPRODUCT_CID();
            //获取同一子分类下的商品
            ArrayList<LMONKEY_PRODUCT> classlist = LMONKEY_PRODUCTDao.selectByAllCid(cid);

            request.setAttribute("classlist", classlist);
            //查找子分类的信息，因为我们需要用到子分类的名字
            LMONKEY_CATEGORY cate = LMONKEY_CATEGORYDao.selectById(cid);

            request.setAttribute("cate", cate);

        }


        request.getRequestDispatcher("productview.jsp").forward(request, response);


    }


}
