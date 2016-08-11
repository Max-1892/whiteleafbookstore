<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<c:import url="/includes/header.html"/>

<h1>Your order has been completed.</h1>
<table>
    <tr>
        <th>Quantity</th>
        <th>Title</th>
        <th>Price</th>
        <th>Amount</th>
    </tr>

<%@ taglib uri="/WEB-INF/whiteleaf.tld" prefix="mma" %>
<mma:cart>
    <tr>
        <td>${quantity}</td>
        <td>${title}</td>
        <td>${price}</td>
        <td>${total}</td>
    </tr>
</mma:cart>

</table>

</body>