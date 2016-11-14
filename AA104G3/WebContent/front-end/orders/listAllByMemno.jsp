<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.orders.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="util.*"%>



<jsp:useBean id="memberSvc" class="com.member.model.MemberService" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>�|���Ҧ��q��</title>
</head>
<body>
	<a href="<%=request.getContextPath()%>/front-end/orders/select_page.jsp">�^����</a>
	<br>
	<br>

	<c:if test="${not empty errorMsgs}">
		<ul>
			<c:forEach var="msg" items="${errorMsgs}">
				<li>${msg}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>�q��s��</th>
			<th>�|���m�W</th>
			<th>�`���B</th>
			<th>�`�ƶq</th>
			<th>�I�ڤ覡</th>
			<th>�q�檬�A</th>
			<th>�U����</th>
			<th>����H</th>
			<th>���f�覡</th>
			<th>���f�a�}</th>
		</tr>

		<c:forEach var="ordersVO" items="${list}">
			<tr>
				<td>${ordersVO.ordno}</td>
				<td>${memberSvc.getOneMember(ordersVO.memno).memname}</td>
				<td>${ordersVO.pricesum}</td>
				<td>${ordersVO.total}</td>
				
				<c:set var="paymnet" value="${ordersVO.payment}" />
				<td><%=Change.changePayment((String) pageContext.getAttribute("paymnet"))%></td>
				
				<c:set var="state" value="${ordersVO.ordstate}" />
				<td><%=Change.changeOrdState((String) pageContext.getAttribute("state"))%></td>

				<td>${ordersVO.orddate}</td>
				<td>${ordersVO.pickname}</td>
				
				<c:set var="pickup" value="${ordersVO.pickup}" />
				<td><%=Change.changePickup((String) pageContext.getAttribute("pickup"))%></td>

				<td>${ordersVO.pickaddr}</td>				
			</tr>
		</c:forEach>
	</table>
</body>
</html>