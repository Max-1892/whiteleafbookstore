<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="header.html" />

<%@ page import="com.whiteleaf.database.entities.Book" %>
<%@ page import="com.whiteleaf.database.dao.AuthorDAO" %>
<%@ page import="com.whiteleaf.database.dao.PublisherDAO" %>
<%@ page import="com.whiteleaf.database.dao.CategoryDAO" %>
<% Book book = (Book) request.getAttribute("book"); %>
    <article>
        <h1><%= book.getTitle() %></h1>
        <img src="<%= book.getIllustration() %>"/>
        <p><%= AuthorDAO.getAuthorFromId(book.getAuthorId()) %></p>
        <p><%= book.getPrice() %> &nbsp; | &nbsp;<p><%= book.getPageCount() %> &nbsp; 
        | &nbsp;<p><%= book.getDate().getYear() %>&nbsp; | &nbsp;<p><%= PublisherDAO.getPublisherFromId(book.getPublisherId()) %></p></p>
        <p><%= book.getISBN() %> &nbsp; | &nbsp;<p><%= CategoryDAO.getCategoryFromId(book.getCategoryId()) %></p>
        <p><%= book.getSummary() %></p>
    </article>
    </body>
</html>