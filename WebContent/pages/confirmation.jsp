<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="header.html" />

<article style="margin-top: 2%;">Your order was successfully placed. <br>A confirmation email has been sent to <%= request.getAttribute("email") %>.</article>