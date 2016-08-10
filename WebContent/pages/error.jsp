<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<c:import url="/includes/header.html"/>

<%@ page import="beans.*" %>
<% String message = (String) request.getAttribute("message");%>
    <article>
            <h1>Results</h1>
            <table>
            <%= message %>
            </table>
    </article>
    </body>
</html>