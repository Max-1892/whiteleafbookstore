<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<c:import url="/includes/header.html"/>

<%@ page import="beans.*" %>
<% Book book = (Book) request.getAttribute("book");%>
    <article>
        <h1><%= book.getTitle() %></h1>
        <img src="<%= book.getImage() %>"/>
        <p><%= book.getAuthor() %></p>
        <p><%= book.getPrice() %> &nbsp; | &nbsp;<p><%= book.getPages() %> &nbsp; 
        | &nbsp;<p><%= book.getYear() %>&nbsp; | &nbsp;<p><%= book.getPublisher() %></p></p>
        <p><%= book.getISBN() %> &nbsp; | &nbsp;<p><%= book.getCategory() %></p>
        <p><%= book.getSummary() %></p>
    </article>
    </body>
</html>