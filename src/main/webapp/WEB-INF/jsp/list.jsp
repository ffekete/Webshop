<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page isELIgnored="false"%>
<html>
<head>
	<link rel="stylesheet" href="<spring:theme code='style'/>" type="text/css" />
</head>
<body>
	<h2><spring:message code="list.title"/></h2>
	
		<table id="cart">
			<tr>
				<th><spring:message code="item.name.literal"/></th>
				<th><spring:message code="item.price.literal"/></th>
				<th><spring:message code="action"/></th>
			<tr>
			<c:forEach items="${shoppingCartModel}" var="item">
			<tr>
				<td><c:out value="${item.name}"/></td>
				<td><c:out value="${item.price}"/></td>
				<td><button onClick="parent.location='/webshop/removeItem.html?id=${item.id}'"><spring:message code="item.remove"/></button></td>
			</tr>
			</c:forEach>
		</table>
			
		<table>
			<tr>
				<th><spring:message code="item.name.literal"/></th>
				<th><spring:message code="item.price.literal"/></th>
				<th><spring:message code="item.description.literal"/></th>
				<th><spring:message code="item.store.amount"/></th>
				<th><spring:message code="item.quantity"/></th>
				<th><spring:message code="action"/></th>
			<tr>
			<c:forEach items="${itemStoreModel}" var="item">
			<form action="/webshop/addItem.html">
			<tr>
				<td><c:out value="${item.item.name}"/></td>
				<td><c:out value="${item.item.price}"/></td>
				<td><c:out value="${item.item.description}"/></td>
				<td><c:out value="${item.store.amount}"/></td>
				
				
				<c:if test="${item.store.amount > 0}">
					
					<input type="hidden" name="id" value="${item.item.id}"/>
					<td><input type="number" name="quantity" min="1" max="${item.store.amount}" step="1"/></td>
					<td><input type="submit" value=<spring:message code="item.add_to_cart"/>/></td>
				</c:if>
				<c:if test="${item.store.amount == 0}">
					<td><spring:message code="item.outOfStock"/></td>
					<td></td>
				</c:if>
			</tr>
			</form>
			</c:forEach>
		</table>
</body>
</html>
