<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<c:import url="/includes/header.html"/>

<%@ page import="com.whiteleaf.database.entities.*" %>

    <article>
        <h1>Admin</h1>
        <h2>Add a Book</h2>
        <form action="/AdminServlet" method="get">
            Title: <input type="text" name="title" tabindex="1"><br/>
            Author: <input type="text" name="author" tabindex="2"><br/>
            Image Link: <input type="text" name="image" tabindex="3"><br/>
            Price: <input type="text" name="price" tabindex="4"><br/>
            Pages: <input type="text" name="page" tabindex="5"><br/>
            Year: <input type="text" name="year" tabindex="6"><br/>
            Publisher: <input type="text" name="publisher" tabindex="7"><br/>
            ISBN: <input type="text" name="ISBN" tabindex="8"><br/>
            Category: <input type="text" name="category" tabindex="9"><br/>
            Summary: <input type="text" name="summary" tabindex="10"><br/>
            <input type="hidden" name="action" value="add">
        <input type="submit" value="Add">
        </form>
        
        <h2>Delete a Book</h2>
            <form action="/AdminServlet" method="get">
            ISBN: <input type="text" name="ISBN" tabindex="8"><br/>
            <input type="hidden" name="action" value="delete">
            <input type="submit" value="Delete">
    </body>
    </article>

</html>