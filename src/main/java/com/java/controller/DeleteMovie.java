package com.java.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.dao.MovieDAO;
@WebServlet("/deletemovie")
public class DeleteMovie  extends HttpServlet
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
   {
	int id=Integer.parseInt(req.getParameter("id"));
	MovieDAO dao=new MovieDAO();
	
	try {
		
		HttpSession session=req.getSession();
		String adminName=(String)session.getAttribute("name");
		if (adminName !=null) {
			dao.delete(id);
			RequestDispatcher rd=req.getRequestDispatcher("admin.jsp");
			rd.include(req, resp);
		}
		else {
			req.setAttribute("messgage", "Access Denied Login Required");
			RequestDispatcher rd=req.getRequestDispatcher("alogin.jsp");
			rd.include(req, resp);
		}
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
}
