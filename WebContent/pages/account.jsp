<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="header.html" />
<%@page import="com.whiteleaf.database.dao.UserAddressDAO, com.whiteleaf.database.util.User" %>
<% User user = (User) session.getAttribute("user"); %>
<% if (user != null && !user.equals("")) { %>
	<article>
		<form action="/RegistrationServlet" method="get">
		Name: <input type="text" name="name" placeholder="name" tabindex="1">
		User Name:<input type="text" name="userName" placeholder="username" tabindex="2">
		Password:<input type="text" name="password" placeholder="*********" tabindex="3">
		<h2>Shipping Information</h2>
		Shipping Address: <input type="text" name="shippingAddress" placeholder="shipping address" %>" tabindex="4">
		<h2>Billing Information</h2>
		Billing Address: <input type="text" name="billingAddress" placeholder="billing address" tabindex="1">
		Credit Card Number: <input type="text" name="creditCard" placeholder="****-****-****-****" tabindex="1">
		<input type="submit" value="Submit">
		</form>
	</article>
<% } else { %>
	<article>
		<h3>You aren't logged in!<br> Please register <a href="registration.jsp">here</a> or login <a href="../login.html">here</a></h3>
	</article>
<% } %>
	</body>
</html>
