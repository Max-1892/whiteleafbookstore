package com.whiteleaf.controllers;

import java.io.IOException;

import com.whiteleaf.database.dao.BooksDAO;
import com.whiteleaf.database.dao.ConnectionPool;
import com.whiteleaf.database.entities.Book;
import com.whiteleaf.database.entities.DisplayBook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class ResultsServlet
 */
@WebServlet("/ResultsServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session = request.getSession();
		
		//Get parameters
		String action = request.getParameter("action");
		String searchWord = request.getParameter("searchWord");
		String url = "";
		
		//Get Connection
		try{
			String dbURL = "jdbc:mysql://localhost:3306/whiteleafbookstore";
			String username = "";
			String password = "";
			Connection connection = DriverManager.getConnection(dbURL, username, password);
			ConnectionPool cp = ConnectionPool.getInstance();
	        Connection c = cp.getConnection();
			
	        String resultDisplay = "";
	        
			//For category results list
			if(action.equals("category")){
				String preparedSQLCategory = "SELECT * " + 
						"FROM Books INNER JOIN Categories ON Books.CategoryID = Categories.ID " 
						+ "WHERE CATEGORY = ? " +
						"ORDER BY Title ASC";
				PreparedStatement ps = connection.prepareStatement(preparedSQLCategory);
				ps.setString(1, searchWord);
				ResultSet results = ps.executeQuery();
				
				//Format results
				boolean resultExists = results.next();
				while (results.next()){
					String title = results.getString("Title");
					String author = results.getString("Author");
					String image = results.getString("Illustration");
					String ISBN = results.getString("ISBN");
					double price = results.getDouble("Price");
					int year = results.getInt("Publication_Date"); 
					String publisher = results.getString("Publisher_ID");
					int pages = results.getInt("Pages");
					String summary = results.getString("Summary");
					String category = results.getString("Category_ID");
					//Book book = new Book(title, author, ISBN, price, new Date().setYear(year), publisher, pages, summary, image, category);
					//resultDisplay = resultDisplay + DisplayBook.displayBookResult(book);
				}
				
				request.setAttribute("results", resultDisplay);
				
				url = "/results.jsp";
			}
			//For search box result list 
			else if(action.equals("keyword")){
				String preparedSQLCategory = "SELECT * " + 
						"FROM Books INNER JOIN Categories ON Books.CategoryID = Categories.ID " 
						+ "WHERE CATEGORY = ? " +
						"ORDER BY Title ASC";
				PreparedStatement ps = connection.prepareStatement(preparedSQLCategory);
				ps.setString(1, searchWord);
				ResultSet results = ps.executeQuery();
				
				//Format results
				boolean resultExists = results.next();
				while (results.next()){
					String title = results.getString("Title");
					String author = results.getString("Author");
					String image = results.getString("Illustration");
					String ISBN = results.getString("ISBN");
					double price = results.getDouble("Price");
					int year = results.getInt("Publication_Date"); 
					String publisher = results.getString("Publisher_ID");
					int pages = results.getInt("Pages");
					String summary = results.getString("Summary");
					String category = results.getString("Category_ID");
					//Book book = new Book(title, author, image, ISBN, price, year, publisher, pages, summary, category);
					//resultDisplay = resultDisplay+ DisplayBook.displayBookResult(book);
				}
				
				request.setAttribute("results", resultDisplay);
				url = "/results.jsp";
			}
			//For searching for books by ISBN
			else if(action.equals("ISBN")){
				String preparedSQLCategory = "SELECT * " + 
						"FROM Books INNER JOIN Categories ON Books.CategoryID = Categories.ID " 
						+ "WHERE ISBN = ? " +
						"ORDER BY Title ASC";
				PreparedStatement ps = connection.prepareStatement(preparedSQLCategory);
				ps.setString(1, searchWord);
				ResultSet results = ps.executeQuery();
				
				//Format results
				boolean resultExists = results.next();
				while (results.next()){
					String title = results.getString("Title");
					String author = results.getString("Author");
					String image = results.getString("Illustration");
					String ISBN = results.getString("ISBN");
					double price = results.getDouble("Price");
					int year = results.getInt("Publication_Date"); 
					String publisher = results.getString("Publisher_ID");
					int pages = results.getInt("Pages");
					String summary = results.getString("Summary");
					String category = results.getString("Category_ID");
					//Book book = new Book(title, author, image, ISBN, price, year, publisher, pages, summary, category);
					//request.setAttribute("book", book);
				}
				
				
				url = "/details.jsp";
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
