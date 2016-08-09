<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<c:import url="/includes/header.html"/>

<%@ page import="com.whiteleaf.database.entities.*" %>
<% String results = (String) request.getAttribute("results");%>
	<article>
		<h1>Results</h1>
		<table>
		<%= results %>
		</table>
	</article>
	</body>
</html>