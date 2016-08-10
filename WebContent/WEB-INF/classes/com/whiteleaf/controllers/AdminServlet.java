package com.whiteleaf.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.whiteleaf.database.dao.ConnectionPool;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());	
		
		HttpSession session = request.getSession();
		String url = "";
		
		//Get parameters
		String action = request.getParameter("action");
		
		//Get Connection
		try{
			String dbURL = "jdbc:mysql://localhost:3306/whiteleafbookstore";
			String username = "";
			String password = "";
			Connection connection = DriverManager.getConnection(dbURL, username, password);
			ConnectionPool cp = ConnectionPool.getInstance();
	        Connection c = cp.getConnection();
	        
			//For category results list
			if(action.equals("add")){
				
				
				String preparedSQLCategory = "INSERT INTO Books (Title, AuthorID, ISBN, Publication_Date, Publisher_ID, Page_Count, Summary, Illustration, Category_ID, Price)"
						+ "VALUES (? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
				PreparedStatement ps = connection.prepareStatement(preparedSQLCategory);
				ps.executeQuery();
				ps.setString(1, request.getParameter("title"));
				ps.setString(2, request.getParameter("author"));
				ps.setString(3, request.getParameter("ISBN"));
				ps.setString(4, request.getParameter("year"));
				ps.setString(5, request.getParameter("publisher"));
				ps.setString(6, request.getParameter("page"));
				ps.setString(7, request.getParameter("summary"));
				ps.setString(8, request.getParameter("image"));
				ps.setString(9, request.getParameter("category"));
				ps.setString(10, request.getParameter("price"));
				String message = "You have added a book.";
				request.setAttribute("message", message);
				url = "/error.jsp";
			}

			else if(action.equals("delete")){
				String ISBN = request.getParameter("ISBN");
				String preparedSQLCategory = "DELETE FROM Books " + 
						"WHERE ISBN = ?";
				PreparedStatement ps = connection.prepareStatement(preparedSQLCategory);
				ps.setString(1, ISBN);
				ps.executeUpdate();
				String message = "You have deleted a book by ISBN.";
				request.setAttribute("message", message);
				url = "/error.jsp";
			}
			else{
				url = "/error.jsp";
			}
			cp.freeConnection(c);
		}
		catch(SQLException e){
			for(Throwable t : e){
				t.printStackTrace();
			}
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
