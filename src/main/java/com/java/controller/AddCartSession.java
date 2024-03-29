package com.java.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.dao.CartDAO;

@WebServlet("/addcart")
public class AddCartSession extends HttpServlet {

	/**
	 *          
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CartDAO dao = new CartDAO();
		int movieid = Integer.parseInt(req.getParameter("id"));

		HttpSession session = req.getSession();
		

		Integer uid =(Integer)session.getAttribute("userId");
		          
			try {
				if (uid !=null && uid>0) {
					dao.saveCart(uid, movieid);
					Boolean login =(Boolean)session.getAttribute("login");
				if (login !=null && login) {
                    
                    
                    
                    resp.sendRedirect("index.jsp");
					

				}
				else {
					resp.sendRedirect("UserLogin.jsp");
				}
				} 
			else {
				resp.sendRedirect("UserLogin.jsp");
			}
			}
			catch (ClassNotFoundException | SQLException e) {

				e.printStackTrace();
			}
			
			
			
			
			
		
		
	}
}
