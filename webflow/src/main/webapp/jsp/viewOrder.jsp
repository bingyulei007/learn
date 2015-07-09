<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Order</title>
</head>
<body>
	<h1>Order</h1>
	<a href="${flowExecutionUrl}&_eventId=confirm">Confirm</a>
</body>
<c:choose>
	<c:when test="${empty cart.items}">
		<p>Your cart is empty.</p>
	</c:when>
	<c:otherwise>
	   <table border="1" cellspacing="0">
                <tr>
                    <th>Item</th>
                    <th>Quantity</th>
                    <th>Unit Price</th>
                    <th>Total</th>
                </tr>

                <c:forEach var="item" items="${cart.items}">
                    <tr>
                        <td>${item.product.pName}</td>
                        <td>${item.quantity}</td>
                        <td>${item.product.price}</td>
                        <td>${item.totalPrice}</td>
                    </tr>
                </c:forEach>

                <tr>
                    <td>TOTAL:</td>
                    <td></td>
                    <td></td>
                    <td>${cart.totalPrice}</td>
                </tr>
            </table>
	</c:otherwise>
</c:choose>
</html>