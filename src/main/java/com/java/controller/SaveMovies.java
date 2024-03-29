package com.java.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import com.java.dao.MovieDAO;
import com.java.dto.Movie;

@WebServlet("/moviepage")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024)
public class SaveMovies extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			MovieDAO dao = new MovieDAO();
			int id=dao.lastId();
			String mname = req.getParameter("mname");
			double mprice = Double.parseDouble(req.getParameter("mprice"));
			String mgenre = req.getParameter("mgenre");
			String mlang = req.getParameter("mlang");
			String url=req.getParameter("url");
			Part mimage = req.getPart("mimage");

			Movie movie = new Movie();
			movie.setMid(id+1);
			movie.setMname(mname);
			movie.setMprice(mprice);
			movie.setMgenre(mgenre);
			movie.setMlang(mlang);
			movie.setUrl(url);
			movie.setMimage(mimage.getInputStream().readAllBytes());

			
			
			
			HttpSession session=req.getSession();
			String adminName=(String)session.getAttribute("name");
			if (adminName !=null) {
				dao.savamovies(movie);
				RequestDispatcher rd=req.getRequestDispatcher("admin.jsp");
				rd.include(req, resp);
			}
			else {
				req.setAttribute("messgage", "Access Denied Login Required");
				RequestDispatcher rd=req.getRequestDispatcher("alogin.jsp");
				rd.include(req, resp);
			}
		} 
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}
}
