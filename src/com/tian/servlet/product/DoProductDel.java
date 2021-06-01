package com.tian.servlet.product;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tian.service.LMONKEY_PRODUCTDao;

/**
 * Servlet implementation class DoProductDel
 */
@WebServlet("/manage/admin_doprodel")
public class DoProductDel extends HttpServlet {
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html charset=utf-8");
		
	   			
		int id = Integer.parseInt(request.getParameter("id"));
		//加入到数据库的用户表中
		int count = LMONKEY_PRODUCTDao.del(id);
		//成功或失败的去向
		if(count > 0) {
			response.sendRedirect("/book_mall/manage/admin_productselect");//一开始地址没写全
			
		}else {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alter('商品删除失败')");
			out.write("location.href='manger/admin_productselect'");
			out.write("</script>");
		}  
	}

		
	}

	

