<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="header.html" />

<%@ page import="com.whiteleaf.database.util.User" %>
<%@ page import="com.whiteleaf.database.entities.Cart" %>
<%@ page import="com.whiteleaf.database.entities.LineItem" %>
<%@ page import="com.whiteleaf.database.dao.AuthorDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<% User user = (User) session.getAttribute("user"); 
   Cart cart = (Cart) session.getAttribute("cart"); %>
   
<% if (cart == null || cart.getCount() == 0) { %>
	<article><div style="margin-top: 3%;">You don't have books in your cart! Keep on shopping!</div></article>
<% } else { 
   	BigDecimal total = new BigDecimal(0.00); %>
		<table style="width: 80%; align: center; margin-left: auto; margin-right: auto; margin-top: 2%;">
        	<tr>
            	<th id="titles_header">Titles</th>
            	<th>Quantity</th>
            	<th>Unit Cost</th>
            </tr>
            <% ArrayList<LineItem> items = cart.getItems();
            	BigDecimal temp = new BigDecimal(0);
            	temp.setScale(2);
               for (LineItem item : items) { 
            	   total = total.add(item.getBook().getPrice().multiply(new BigDecimal(item.getQuantity())));
            %>
            <tr>
                <td class="title_col" align="center"><%= item.getBook().getTitle() + " by " + AuthorDAO.getAuthorFromId(item.getBook().getAuthorId()).getName() %></td>
                <td class="quantity_col" align="center"><%= item.getQuantity() %>
                <td class="unit_cost_col" align="center">$<%= item.getBook().getPrice() %></td>
                <td align="center">
                	<form action="UpdateCart" method="post">
                		<input type="hidden" name="action" value="add">
                		<input type="hidden" name="lineItem" value="<%= item %>">
                		<input type="submit" value="Add">
                	</form>
                </td>
                <td align="center">
                	<form action="UpdateCart" method="post">
                		<input type="hidden" name="action" value="remove">
                		<input type="hidden" name="lineItem" value="<%= item %>">
                		<input type="submit" value="Remove">
                	</form>
                </td>
            </tr>
            <% } %>
        </table>
        <div id="total_cost" style="margin-top: 3%; margin-left: 70%; margin-bottom: 3%;">Total: $<%= total.setScale(2) %></div>
        <form action="confirmOrder.jsp" method="post">
        	<input style="margin-left: 30%; width: 50%;" type="submit" value="Proceed to Billing">
        </form>
<% } %>