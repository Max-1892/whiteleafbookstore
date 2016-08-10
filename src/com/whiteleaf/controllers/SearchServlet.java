package com.whiteleaf.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;

import com.whiteleaf.database.dao.AuthorDAO;
import com.whiteleaf.database.dao.BooksDAO;
import com.whiteleaf.database.dao.CategoryDAO;
import com.whiteleaf.database.dao.PublisherDAO;
import com.whiteleaf.database.entities.Author;
import com.whiteleaf.database.entities.Book;
import com.whiteleaf.database.entities.Category;
import com.whiteleaf.database.entities.Publisher;
import com.whiteleaf.util.DisplayBook;

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

        //Get parameters
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String searchWord = request.getParameter("searchWord");
        String url = "";

        String resultDisplay = "";
        //For category results list
        if(action.equals("category")){
            Category category = CategoryDAO.getCategoryByName(searchWord);
            ArrayList<Book> books = (ArrayList<Book>) BooksDAO.getBooksInCategory(category);
            //Format results
            for (Book book : books) {
                Author author = AuthorDAO.getAuthorFromId(book.getAuthorId());
                Publisher publisher = PublisherDAO.getPublisherFromId(book.getPublisherId());
                String html = DisplayBook.displayBook(book, author, category, publisher);
                resultDisplay = resultDisplay + html;
            }
            request.setAttribute("results", resultDisplay);
            url = "/results.jsp";
        }
        //For search box result list 
        else if(action.equals("keyword")){
            ArrayList<Book> books = (ArrayList<Book>) BooksDAO.getBooksByTitle(searchWord);
            //Format results
            for (Book book : books) {
                Author author = AuthorDAO.getAuthorFromId(book.getAuthorId());
                Category category = CategoryDAO.getCategoryFromId(book.getCategoryId());
                Publisher publisher = PublisherDAO.getPublisherFromId(book.getPublisherId());
                String html = DisplayBook.displayBook(book, author, category, publisher);
                resultDisplay = resultDisplay + html;
            }
            request.setAttribute("results", resultDisplay);
            url = "/results.jsp";
        }
        //For searching for books by ISBN
        else if(action.equals("ISBN")){
            Book book = BooksDAO.getBookByISBN(searchWord);
            request.setAttribute("book", book);
            url = "/details.jsp";
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
