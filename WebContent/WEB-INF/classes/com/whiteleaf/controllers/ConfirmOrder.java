package com.whiteleaf.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whiteleaf.database.entities.Cart;
import com.whiteleaf.database.entities.LineItem;
import com.whiteleaf.database.entities.UserAddress;
import com.whiteleaf.database.entities.UserCreditCards;

/**
 * Servlet implementation class ConfirmOrder
 */
@WebServlet("/ConfirmOrder")
public class ConfirmOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		// Validate email address
		if (!Pattern.matches("[A-z0-9._%+-]+@[A-z0-9.-]+\\.[A-z]{2,4}", paramStore.get("email"))) {
				errors.add("Email address isn't in the right form");
				goodRegistration = false;
		}
		// Validate credit card number
		if (!UserCreditCards.validateCreditCardNumber(paramStore.get("creditCard"))) {
			if (!errors.contains("Credit Card Number isn't in the right form")) {
				errors.add("Credit Card Number");
				goodRegistration = false;
			}
		}
		// Validate credit card expiration date
		if (!UserCreditCards.validateCreditCardExpDate(paramStore.get("creditCardExpDate"))) {
			if (!errors.contains("Credit Card Expiration Date")) {
				errors.add("Credit Card Expiration Date isn't in the right form");
				goodRegistration = false;
			}
		}
		
		StringBuilder url = new StringBuilder(); 
		if (goodRegistration) {
			// Send email
            final String fromUsername = "mhenry22jhuedu";
            final String fromPassword = "sh0rtt0es&sh0rterpatience";
            boolean error = false;

            // Create a mail session
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtps");
            props.put("mail.smtps.host", "smtp.gmail.com");
            props.put("mail.smtps.user", fromUsername);
            props.put("mail.smtps.password", fromPassword);
            props.put("mail.smtps.port", 465);
            props.put("mail.smtps.auth", "true");
            props.put("mail.smtps.quitwait", "false");
            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);

            // Create message
            Message message = new MimeMessage(session);
            try {
                    message.setSubject("Whiteleaf Bookstore Order Confirmation");
                    message.setContent(generateEmailHtml(request), "text/html");
            } catch (MessagingException e) {
                    e.printStackTrace();
                    error = true;
            }
            
            // Prepare message for sending
            Address toAddress;
            try {
                    toAddress = new InternetAddress(paramStore.get("email"));
                    message.setRecipient(Message.RecipientType.TO, toAddress);
            } catch (AddressException e) {
                    e.printStackTrace();
                    error = true;
            } catch (MessagingException e) {
                    e.printStackTrace();
                    error = true;
            }

            // Send message
            try {
                    Transport transport = session.getTransport();
                    transport.connect(fromUsername, fromPassword);
                    transport.sendMessage(message, message.getAllRecipients());
                    transport.close();
            } catch (MessagingException e) {
                    e.printStackTrace();
            }
            // Clear cart
            request.getSession().setAttribute("cart", new Cart());
            request.getSession().removeAttribute("total");
			request.setAttribute("email", paramStore.get("email"));
			url.append("/pages/confirmation.jsp");
		} else {
			request.setAttribute("errors", errors);
			url.append("/pages/confirmOrder.jsp");
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
		doGet(request, response);
	}

	private boolean parseParams(
			HttpServletRequest request, Map<String, String> paramStore, ArrayList<String> errors) {
		paramStore.put("email", request.getParameter("email"));
		if (paramStore.get("email") == null || paramStore.get("email").equals("")) {
			errors.add("Email address is empty");
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
		paramStore.put("billingAddress", request.getParameter("billingAddress"));
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
	
	private String generateEmailHtml(HttpServletRequest request) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		StringBuilder html = new StringBuilder();
		html.append("<div style=\"background-color: #FCFFD1; text-align: center; font-family: 'georgia'\">");
		html.append("This email serves as confirmation of your order of " + cart.getCount() + " book(s) ");
		html.append("through Whiteleaf Bookstore for a total of $" + request.getSession().getAttribute("total") + ". ");
		html.append("Your order is on its way!");
		html.append("</div>");
		return html.toString();
	}
}
