package com.whiteleaf.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whiteleaf.database.entities.Cart;
import com.whiteleaf.database.entities.LineItem;

/**
 * Servlet implementation class UpdateCart
 */
@WebServlet("/UpdateCart")
public class UpdateCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		String isbn = request.getParameter("isbn");
		
		String url = "/pages/checkout.jsp";
		for (LineItem item : cart.getItems()) {
			if (item.getBook().getISBN().equals(isbn)) {
				if (action.equals("add")) {
					item.setQuantity(item.getQuantity() + 1);
				} else if (action.equals("remove")) {
					item.setQuantity(item.getQuantity() - 1);
					if (item.getQuantity() == 0) {
						cart.removeItem(item);
					}
				}
				break;
			}
		}    
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
