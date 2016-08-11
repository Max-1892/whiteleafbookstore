<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<c:import url="/includes/header.html"/>

<%@ page import="com.whiteleaf.database.entities.*" %>

	<article>
		<h1>Welcome to Whiteleaf Bookstore</h1>
		<form action="../RegistrationServlet" method="post">
		<h2>Account Information</h2>
		Name: <input type="text" name="name" tabindex="1"><br>
		User Name:<input type="text" name="userName"  tabindex="2"><br>
		Password:<input type="text" name="password" placeholder="*********" tabindex="3"><br>
		<h2>Shipping Information</h2>
		Address:<input type="text" name="shippingAddress"  tabindex="4"><br>
		City:<input type="text" name="shippingCity"  tabindex="5"><br>
		State:<input type="text" name="shippingState"  tabindex="6"><br>
		Zip Code: <input type="text" name="shippingZipcode"  tabindex="8"><br>
		<h2>Billing Information</h2>
		Address:<input type="text" name="shippingAddress"  tabindex="9"><br>
		City:<input type="text" name="shippingCity"  tabindex="10"><br>
		State:<input type="text" name="shippingState"  tabindex="11"><br>
		Zip Code: <input type="text" name="shippingZipcode"  tabindex="12"><br>
		Credit Card Number: <input type="text" name="creditCard" placeholder="****-****-****-****" tabindex="13"><br>
		<input type="submit" value="Submit Registration">
		</form>
	</article>
	</body>
</html>