package com.whiteleaf.controllers;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.whiteleaf.database.dao.BooksDAO;
import com.whiteleaf.database.entities.Book;
import com.whiteleaf.database.entities.Cart;
import com.whiteleaf.database.entities.LineItem;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext sc = getServletContext();
        
        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "cart";  // default action
        }

        // perform action and set URL to appropriate page
        String url = "/whiteleafbookstore/index.html";
        if (action.equals("shop")) {
            url = "/whiteleafbookstore/index.html";
        } 
        else if (action.equals("cart")) {
            String productCode = request.getParameter("productCode");
            String quantityString = request.getParameter("quantity");

            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
            }

            //if the user enters a negative or invalid quantity,
            //the quantity is automatically reset to 1.
            int quantity;
            try {
                quantity = Integer.parseInt(quantityString);
                if (quantity < 0) {
                    quantity = 1;
                }
            } catch (NumberFormatException nfe) {
                quantity = 1;
            }

            // Check if book is already in cart, if so increase quantity
            boolean updatedCart = false;
            for (LineItem item : cart.getItems()) {
            	if (item.getBook().getISBN().equals(productCode)) {
            		item.setQuantity(item.getQuantity() + 1);
            		updatedCart = true;
            		break;
            	}
            }
            
            if (!updatedCart) {
            	Book book = BooksDAO.getBookByISBN(productCode);

            	LineItem lineItem = new LineItem();
            	lineItem.setBook(book);
            	lineItem.setQuantity(quantity);
            	if (quantity > 0) {
            	    cart.addItem(lineItem);
            	} else if (quantity == 0) {
            	    cart.removeItem(lineItem);
            	}
            }

            session.setAttribute("cart", cart);
            url = "/pages/cart.jsp";
        }
        else if (action.equals("checkout")) {
            url = "/pages/checkout.jsp";
        }

        sc.getRequestDispatcher(url)
                .forward(request, response);
    }
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}