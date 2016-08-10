<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<c:import url="/includes/header.html"/>

<%@ page import="beans.*" %>
<% User user = (User) request.getAttribute("user");%>
    <article>
        <h1>Welcome <%= user.getName() %>></h1>
        <form action="/RegistrationServlet" method="get">
        <h2>Account Information</h2>
        Name: <input type="text" name="name" placeholder="<%= user.getName() %>" tabindex="1">
        User Name:<input type="text" name="userName" placeholder="<%= user.getUserName() %>" tabindex="2">
        Password:<input type="text" name="password" placeholder="*********" tabindex="3">
        <h2>Shipping Information</h2>
        Shipping Address: <input type="text" name="shippingAddress" placeholder="<%= user.getShippingAddress() %>" tabindex="4">
        <h2>Billing Information</h2>
        Billing Address: <input type="text" name="billingAddress" placeholder="<%= user.getBillingAddress() %>" tabindex="1">
        Credit Card Number: <input type="text" name="creditCard" placeholder="****-****-****-****" tabindex="1">
        <input type="submit" value="Submit">
        </form>
    </article>
    </body>
</html>