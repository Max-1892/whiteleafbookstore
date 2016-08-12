<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="header.html" %>

<%@ page import="com.whiteleaf.database.entities.*" %>
<% if (request.getAttribute("errors") != null) { %>
	<div class="error">
	Oops, some things don't seem quite right:
	<ul>
    <%@ page import="java.util.ArrayList" %>
    <% ArrayList<String> errors = (ArrayList<String>) request.getAttribute("errors");
       for (int errCount = 0; errCount < errors.size(); errCount++) { %>
         <li><%= errors.get(errCount) %></li>
       <% } %>
       </ul>
     </div>
<% } %>
	<article><br>
		<form action="../RegistrationServlet" method="post">
		<h2>Account Information</h2>
		Name: <input type="text" name="name" tabindex="1"><br>
		User Name:<input type="text" name="userName"  tabindex="2"><br>
		Password:<input type="password" name="password" placeholder="*********" tabindex="3"><br>
		Reenter Password:<input type="password" name="password2" placeholder="*********" tabindex="4"><br>
		<h2>Shipping Information</h2>
		Address:<input type="text" name="shippingAddress"  tabindex="5"><br>
		City:<input type="text" name="shippingCity"  tabindex="6"><br>
		State:<input type="text" name="shippingState"  tabindex="7"><br>
		Zip Code: <input type="text" name="shippingZipcode"  tabindex="8"><br>
		<h2>Billing Information</h2>
		Address:<input type="text" name="billingAddress"  tabindex="9"><br>
		City:<input type="text" name="billingCity"  tabindex="10"><br>
		State:<input type="text" name="billingState"  tabindex="11"><br>
		Zip Code: <input type="text" name="billingZipcode"  tabindex="12"><br>
		Credit Card Provider: <input type="radio" name="creditCardProvider" value="VISA" tabindex="13">VISA<br>
		 					  <input type="radio" name="creditCardProvider" value="Master_Card" tabindex="14">Master Card<br>
		 					  <input type="radio" name="creditCardProvider" value="Discover" tabindex="15">Discover<br>
		 					  <input type="radio" name="creditCardProvider" value="American_Express" tabindex="16">American Express<br>
		Credit Card Number: <input type="text" name="creditCard" placeholder="****-****-****-****" tabindex="17"><br>
		Credit Card Expiration Date: <input type="text" name="creditCardExpDate" placeholder="MM/YYYY" tabindex="18"><br>
		<input type="submit" value="Submit Registration">
		</form>
	</article>
	</body>
</html>
