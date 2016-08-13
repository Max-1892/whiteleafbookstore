<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="header.html" />

<%@ page import="com.whiteleaf.database.entities.*" %>
<h2 style="text-align: center;">Please register below!</h2>
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
		<form action="/whiteleafbookstore/RegistrationServlet" method="post">
		<h3 class="registration_section">Account Information</h3>
		<div class="registration_fields">
		Name: <input type="text" name="name" tabindex="1"><br>
		Email Address: <input type="text" name="email" tabindex="2"><br>
		User Name:<input type="text" name="userName"  tabindex="3"><br>
		Password:<input type="password" name="password" placeholder="*********" tabindex="4"><br>
		Reenter Password:<input type="password" name="password2" placeholder="*********" tabindex="5"><br>
		</div>
		<h3 class="registration_section">Shipping Information</h3>
		<div class="registration_fields">
		Address:<input type="text" name="shippingAddress"  tabindex="6"><br>
		City:<input type="text" name="shippingCity"  tabindex="7"><br>
		State:<input type="text" name="shippingState"  tabindex="8"><br>
		Zip Code: <input type="text" name="shippingZipcode"  tabindex="9"><br>
		</div>
		<h3 class="registration_section">Billing Information</h3>
		<div class="registration_fields">
		Address:<input type="text" name="billingAddress"  tabindex="10"><br>
		City:<input type="text" name="billingCity"  tabindex="11"><br>
		State:<input type="text" name="billingState"  tabindex="12"><br>
		Zip Code: <input type="text" name="billingZipcode"  tabindex="13"><br>
		Credit Card Provider: <br><div id="ccProviders"><input type="radio" name="creditCardProvider" value="VISA" tabindex="14">VISA<br>
		 					  <input type="radio" name="creditCardProvider" value="Mastercard" tabindex="15">Master Card<br>
		 					  <input type="radio" name="creditCardProvider" value="Discover" tabindex="16">Discover<br>
		 					  <input type="radio" name="creditCardProvider" value="American Express" tabindex="17">American Express<br></div>
		Credit Card Number: <input type="text" name="creditCard" placeholder="****-****-****-****" tabindex="18"><br>
		Credit Card Expiration Date: <input type="text" name="creditCardExpDate" placeholder="MM/YYYY" tabindex="19"><br>
		</div>
		<input type="submit" value="Submit Registration" style="margin-top: 2%;">
		</form>
	</article>
	</body>
</html>
