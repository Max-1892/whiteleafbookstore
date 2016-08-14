<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="header.html" />

<%@ page import="com.whiteleaf.database.entities.Cart" %>

<% Cart cart = (Cart) request.getSession().getAttribute("cart");
   if (cart != null && cart.getCount() <= 0) { %>
	<article><div style="margin-top: 3%;">You don't have books in your cart! Keep on shopping!</div></article>
<% } else { %>
<table style="width: 80%; align: center; margin-left: auto; margin-right: auto; margin-top: 2%;">
    <tr>
        <th>Title</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Amount</th>
    </tr>

<%@ taglib uri="/WEB-INF/whiteleaf.tld" prefix="mma" %>
<mma:cart>
    <tr>
        <td id="productTitle" align="center">${productTitle}</td>
        <td align="center">$${productPrice}</td>
        <td align="center">${quantity}</td>
        <td align="center">${total}</td>
    </tr>
</mma:cart>

</table>
<table id="confirm_buttons">
	<tr>
		<form action="/whiteleafbookstore/index.html" method="get" class="pad_top">
    		<input type="hidden" name="action" value="shop">
    		<input id="confirm_buttons" type="submit" value="Continue Shopping">
		</form>

		<form action="/whiteleafbookstore/pages/checkout.jsp" method="post">
    		<input type="hidden" name="action" value="checkout">
    		<input type="submit" value="Checkout">
		</form>
	</tr>
</table>
<% } %>

</body>