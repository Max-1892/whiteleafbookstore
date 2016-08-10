<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<c:import url="/includes/header.html"/>

<%@ page import="beans.*" %>

        <article>
            <h1>Welcome to White Leaf Bookstore</h1>
            <form action="/RegistrationServlet" method="get">
            <h2>Account Information</h2>
            Name: <input type="text" name="name" tabindex="1">
            User Name:<input type="text" name="userName"  tabindex="2">
            Password:<input type="text" name="password" placeholder="*********" tabindex="3">
            <h2>Shipping Information</h2>
            Shipping Address: <input type="text" name="shippingAddress"  tabindex="4">
            <h2>Billing Information</h2>
            Billing Address: <input type="text" name="billingAddress"  tabindex="5">
            Credit Card Number: <input type="text" name="creditCard" placeholder="****-****-****-****" tabindex="6">
            <input type="submit" value="Submit">
            </form>
        </article>
    </body>
</html>