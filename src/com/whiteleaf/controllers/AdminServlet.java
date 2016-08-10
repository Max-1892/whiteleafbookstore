package com.whiteleaf.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.whiteleaf.database.dao.AuthorDAO;
import com.whiteleaf.database.dao.BooksDAO;
import com.whiteleaf.database.dao.CategoryDAO;
import com.whiteleaf.database.dao.PublisherDAO;
import com.whiteleaf.database.entities.Author;
import com.whiteleaf.database.entities.Book;
import com.whiteleaf.database.entities.Category;
import com.whiteleaf.database.entities.Publisher;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        if(action.equals("add")){
            // get request parameters
            String title = request.getParameter("title");
            String authorName = request.getParameter("author");
            String ISBN = request.getParameter("ISBN");
            String publicationDate = request.getParameter("year");
            String publisherName = request.getParameter("publisher");
            String pageCount = request.getParameter("page");
            String summary = request.getParameter("summary");
            String image = request.getParameter("image");
            String categoryName = request.getParameter("category");
            String price = request.getParameter("price");

            String message = String.format(
                    "Unable to add book: %s, %s, %s, %s, %s, %s, %s, %s, %s, %s",
                    title, authorName, ISBN, publicationDate, publisherName,
                    pageCount, summary, image, categoryName, price);

            // make necessary conversions
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("");
            Date date;
            try {
                date = (Date) simpleDateFormat.parse(publicationDate);
                int count = Integer.valueOf(pageCount);
                BigDecimal cost = BigDecimal.valueOf(Double.parseDouble(price));

                // get entity objects for IDs
                Author author = AuthorDAO.getAuthorByName(authorName);
                Category category = CategoryDAO.getCategoryByName(categoryName);
                Publisher publisher = PublisherDAO.getPublisherByName(publisherName);

                // add book
                Book book = new Book(-1, title, author.getId(), ISBN, date,
                                        publisher.getId(), count, summary, image,
                                        category.getId(), cost);

                if (BooksDAO.addBook(book))
                    message = "You have added a book.";
            } catch (ParseException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.setAttribute("message", message);
            url = "/error.jsp";
        }
        else if(action.equals("delete")){
            String ISBN = request.getParameter("ISBN");
            String message = "Unable to delete the book with ISBN: " + ISBN;
            if (BooksDAO.removeBook(ISBN))
                message = "You have deleted a book by ISBN.";
            request.setAttribute("message", message);
            url = "/error.jsp";
        }
        else{
                url = "/error.jsp";
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
