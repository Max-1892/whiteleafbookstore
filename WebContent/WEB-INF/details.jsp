<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.whiteleaf.database.dao.BooksDAO"%>
<%@page import="com.whiteleaf.database.dao.AuthorDAO"%>
<%@page import="com.whiteleaf.database.dao.PublisherDAO"%>
<%@page import="com.whiteleaf.database.dao.CategoryDAO"%>
<c:import url="/includes/header.html"/>

<%@ page import="com.whiteleaf.database.entities.*" %>
<% Book book = (Book) request.getAttribute("book");%>
	<article>
		<h1><%= book.getTitle() %></h1>
		<img src="<%= book.getIllustration() %>"/>
		<p><%= AuthorDAO.getAuthorFromId(book.getAuthorId()) %></p>
		<p><%= book.getPrice() %> &nbsp; | &nbsp;<p><%= book.getPageCount() %> &nbsp; 
		| &nbsp;<p><%= PublisherDAO.getPublisherFromId(book.getPublisherId()) %></p>
		<p><%= book.getISBN() %> &nbsp; | &nbsp;<p><%= CategoryDAO.getCategoryFromId(book.getCategoryId()) %></p>
		<p><%= book.getSummary() %></p>
	</article>
	</body>
</html>