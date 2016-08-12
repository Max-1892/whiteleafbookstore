package com.whiteleaf.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whiteleaf.database.entities.User;
import com.whiteleaf.database.entities.UserAddress;
import com.whiteleaf.database.entities.UserCreditCards;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Check all fields are filled in
		boolean goodRegistration = false;
		ArrayList<String> errors = new ArrayList<String>();
		Map<String, String> paramStore = new HashMap<String, String>();
		goodRegistration = parseParams(request, paramStore, errors) ? true : false;
		// Validate zip codes
		if (!UserAddress.validateZipcode(paramStore.get("shippingZipcode"))) {
			if (!errors.contains("Shipping Zip Code")) {
				errors.add("Shipping Zip Code");
			}
		}
		if (!UserAddress.validateZipcode(paramStore.get("billingZipcode"))) {
			if (!errors.contains("Billing Zip Code")) {
				errors.add("Billing Zip Code");
				goodRegistration = false;
			}
		}
		// Validate credit card number
		if (!UserCreditCards.validateCreditCardNumber(paramStore.get("creditCard"))) {
			if (!errors.contains("Credit Card Number")) {
				errors.add("Credit Card Number");
				goodRegistration = false;
			}
		}
		// Validate credit card expiration date
		if (!UserCreditCards.validateCreditCardExpDate(paramStore.get("creditCardExpDate"))) {
			if (!errors.contains("Credit Card Expiration Date")) {
				errors.add("Credit Card Expiration Date");
				goodRegistration = false;
			}
		}
		// Validate password
		if (!paramStore.get("password2").equals(paramStore.get("password"))) {
			errors.add("Passwords don't match");
			goodRegistration = false;
		}
		// TODO: Validate username is available
		
		StringBuilder url = new StringBuilder(); 
		if (goodRegistration) {
			// TODO: Add user to DB
			// TODO: Proceed to log in screen
		} else {
			request.setAttribute("errors", errors);
			url.append("/pages/registration.jsp");
		}
        // forward request and response objects to JSP page
        RequestDispatcher dispatcher =
                 getServletContext().getRequestDispatcher(url.toString());
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private boolean parseParams(
			HttpServletRequest request, Map<String, String> paramStore, ArrayList<String> errors) {
		paramStore.put("name", request.getParameter("name"));
		if (paramStore.get("name") == null || paramStore.get("name").equals("")) {
			errors.add("Name is empty");
		}
		paramStore.put("userName", request.getParameter("userName"));
		if (paramStore.get("userName") == null || paramStore.get("userName").equals("")) {
			errors.add("Username is empty");
		}
		paramStore.put("password", request.getParameter("password"));
		if (paramStore.get("password") == null || paramStore.get("password").equals("")) {
			errors.add("Password is empty");
		}
		paramStore.put("password2", request.getParameter("password2"));
		if (paramStore.get("password2") == null || paramStore.get("password2").equals("")) {
			errors.add("Reentered Password is empty");
		}
		paramStore.put("shippingAddress", request.getParameter("shippingAddress"));
		if (paramStore.get("shippingAddress") == null || paramStore.get("shippingAddress").equals("")) {
			errors.add("Shipping Address is empty");
		}
		paramStore.put("shippingCity", request.getParameter("shippingCity"));
		if (paramStore.get("shippingCity") == null || paramStore.get("shippingCity").equals("")) {
			errors.add("Shipping City is empty");
		}
		paramStore.put("shippingState", request.getParameter("shippingState"));
		if (paramStore.get("shippingState") == null || paramStore.get("shippingState").equals("")) {
			errors.add("Shipping State is empty");
		}
		paramStore.put("shippingZipcode", request.getParameter("shippingZipcode"));
		if (paramStore.get("shippingZipcode") == null || paramStore.get("shippingZipcode").equals("")) {
			errors.add("Shipping Zip Code is empty");
		}
		paramStore.put("billingAddess", request.getParameter("billingAddress"));
		if (paramStore.get("billingAddress") == null || paramStore.get("billingAddress").equals("")) {
			errors.add("Billing Address is empty");
		}
		paramStore.put("billingCity", request.getParameter("billingCity"));
		if (paramStore.get("billingCity") == null || paramStore.get("billingCity").equals("")) {
			errors.add("Billing City is empty");
		}
		paramStore.put("billingState", request.getParameter("billingState"));
		if (paramStore.get("billingState") == null || paramStore.get("billingState").equals("")) {
			errors.add("Billing State is empty");
		}
		paramStore.put("billingZipcode", request.getParameter("billingZipcode"));
		if (paramStore.get("billingZipcode") == null || paramStore.get("billingZipcode").equals("")) {
			errors.add("Billing Zip Code is empty");
		}
		paramStore.put("creditCardProvider", request.getParameter("creditCardProvider"));
		if (paramStore.get("creditCardProvider") == null || paramStore.get("creditCardProvider").equals("")) {
			errors.add("Credit Card Provider is empty");
		}
		paramStore.put("creditCard", request.getParameter("creditCard"));
		if (paramStore.get("creditCard") == null || paramStore.get("creditCard").equals("")) {
			errors.add("Credit Card Number is empty");
		}
		paramStore.put("creditCardExpDate", request.getParameter("creditCardExpDate"));
		if (paramStore.get("creditCardExpDate") == null || paramStore.get("creditCardExpDate").equals("")) {
			errors.add("Credit Card Expiration Date is empty");
		}
		return errors.size() == 0;
	}

}
