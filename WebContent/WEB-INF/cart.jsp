<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<c:import url="/includes/header.html"/>

<%@ page import="com.whiteleaf.database.entities.*" %>

<table>
  <tr>
    <th>Quantity</th>
    <th>Title</th>
    <th>Price</th>
    <th>Amount</th>
  </tr>

<%@ taglib uri="whiteleaf.tld" prefix="mma" %>
<mma:cart>
  <tr>
    <td>${quantity}</td>
    <td>${title}</td>
    <td>${price}</td>
    <td>${total}</td>
  </tr>
</mma:cart>

</table>
 
<form action="" method="post" class="pad_top">
  <input type="hidden" name="action" value="shop">
  <input type="submit" value="Continue Shopping">
</form>

<form action="" method="post">
  <input type="hidden" name="action" value="checkout">
  <input type="submit" value="Checkout">
</form>

</body>