<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="header.html" />

<%@ page import="com.whiteleaf.database.entities.*" %>
<%@ page import="com.whiteleaf.database.util.*" %>
<%@ page import="com.whiteleaf.database.dao.CreditCardProviderDAO" %>
<% if (request.getAttribute("errors") != null) { %>
	<div class="error" style="margin-top: 2%;">
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
	<% boolean gotUser = false; 
	   User user = (User) request.getSession().getAttribute("user"); 
	   if (request.getSession().getAttribute("user") == null) { %>
		<h3 style="text-align: center;">Login <a href="/whiteleafbookstore/login.html">here</a> to have the details below filled in for you!</h2>
	<%    gotUser = false; 
	   } else {  
	      gotUser = true;
	   }
	%>
	<article><br>
		<form action="/whiteleafbookstore/ConfirmOrder" method="post">
		<h3 class="registration_section">Contact Information</h3>
		<div class="registration_fields">
		Email Address: <input type="text" name="email" tabindex="2" value="<%= gotUser ?  user.getEmailAddress().getAddress() : "" %>"><br>
		</div>
		<h3 class="registration_section">Shipping Information</h3>
		<div class="registration_fields">
		<%  String shippingAddr = ""; 
		    String shippingCity = "";
		    String shippingState = "";
		    String shippingZipcode = "";
		    if (gotUser) {
		       String[] addrTokens = user.getShippingAddresses().get(0).getAddress().split(",");
		       if (addrTokens.length == 4) {
		    	   shippingAddr = addrTokens[0];
		    	   shippingCity = addrTokens[1];
		    	   shippingState = addrTokens[2];
		    	   shippingZipcode = addrTokens[3];
		       }
		   }
		    String billingAddr = ""; 
		    String billingCity = "";
		    String billingState = "";
		    String billingZipcode = "";
		    if (gotUser) {
		       String[] addrTokens = user.getBillingAddresses().get(0).getAddress().split(",");
		       if (addrTokens.length == 4) {
		    	   billingAddr = addrTokens[0];
		    	   billingCity = addrTokens[1];
		    	   billingState = addrTokens[2];
		    	   billingZipcode = addrTokens[3];
		       }
		   }
		%>
		Address:<input type="text" name="shippingAddress"  tabindex="6" value="<%= shippingAddr %>"><br>
		City:<input type="text" name="shippingCity"  tabindex="7" value="<%= shippingCity %>"><br>
		State:<input type="text" name="shippingState"  tabindex="8" value="<%= shippingState %>"><br>
		Zip Code: <input type="text" name="shippingZipcode"  tabindex="9" value="<%= shippingZipcode %>"><br>
		</div>
		<h3 class="registration_section">Billing Information</h3>
		<div class="registration_fields">
		Address:<input type="text" name="billingAddress"  tabindex="10" value="<%= billingAddr %>"><br>
		City:<input type="text" name="billingCity"  tabindex="11" value="<%= billingCity %>"><br>
		State:<input type="text" name="billingState"  tabindex="12" value="<%= billingState %>"><br>
		Zip Code: <input type="text" name="billingZipcode"  tabindex="13" value="<%= billingZipcode %>"><br>
		<% String ccProvider = "";
		   if (gotUser) {
			   ccProvider = CreditCardProviderDAO.getCreditCardProviderById(user.getCreditCards().get(0).getProviderId()).getName();
		   }
		%>
		Credit Card Provider: <br><div id="ccProviders"><input type="radio" name="creditCardProvider" value="VISA" tabindex="14" <%= ccProvider.equals("VISA") ? "checked" : ""%>>VISA<br>
		 					  <input type="radio" name="creditCardProvider" value="Mastercard" tabindex="15" <%= ccProvider.equals("Mastercard") ? "checked" : "" %>>Master Card<br>
		 					  <input type="radio" name="creditCardProvider" value="Discover" tabindex="16" <%= ccProvider.equals("Discover") ? "checked" : "" %>>Discover<br>
		 					  <input type="radio" name="creditCardProvider" value="American Express" tabindex="17" <%= ccProvider.equals("American Express") ? "checked" : "" %>>American Express<br></div>
		Credit Card Number: <input type="text" name="creditCard" placeholder="****-****-****-****" tabindex="18" value="<%= gotUser ?  user.getCreditCards().get(0).getCardNumber() : "" %>"><br>
		Credit Card Expiration Date: <input type="text" name="creditCardExpDate" placeholder="MM/YYYY" tabindex="19" 
											value="<%= gotUser ?  user.getCreditCards().get(0).getExpirationDate().getMonth() + 1 + "/" +  user.getCreditCards().get(0).getExpirationDate().getYear() : "" %>"><br>
		</div>
		<input type="submit" value="Submit Order" style="margin-top: 2%;">
		</form>
	</article>
	</body>
</html>
