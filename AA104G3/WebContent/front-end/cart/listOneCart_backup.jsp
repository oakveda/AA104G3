<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cart.model.*"%>
<%
	List<CartVO> list = (List<CartVO>) request.getAttribute("list");
%>
<jsp:useBean id="memberSvc" scope="page"
	class="com.member.model.MemberService"></jsp:useBean>
<jsp:useBean id="productSvc" scope="page"
	class="com.product.model.ProductService"></jsp:useBean>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<style type="text/css">
table {
	text-align: center;
}
</style>
<title>�ӧO�ʪ������</title>
</head>
<body>
	<a href="select_page.jsp">�^����</a>
	<br>
	<br>

	<c:if test="${not empty errorMsgs}">
		<ul>
			<c:forEach var="msg" items="${errorMsgs}">
				<li>${msg}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	
	<table border='1'>
		<tr>
			<th>�|���W��</th>
			<th>���~�W��</th>
			<th>����</th>
			<th>�w�s</th>
			<th>�ʶR�ƶq</th>
			<th>�p�p</th>
		</tr>
		
		<c:forEach var="cartVO" items="${list}">		
			<tr>
				<td>${memberSvc.getOneMember(cartVO.memno).memname}</td>
				<td>${productSvc.getOneProduct(cartVO.prono).proname}</td>
				<td>${productSvc.getOneProduct(cartVO.prono).proprice} ��</td>
				<td>${productSvc.getOneProduct(cartVO.prono).proqty}</td>
				<td>
					<!-- �ק�ƶq -->
					<form method="post" action="cart.do">
					    <input type="number" name="newcount" value="${cartVO.procount}" min="0" style="width: 3em;">
					    <input type="submit" value="�ק�">
					    <input type="hidden" name="memno" value="${cartVO.memno}">
					    <input type="hidden" name="prono" value="${cartVO.prono}">
					    <input type="hidden" name="oldcount" value="${cartVO.procount}">
					    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
					    <input type="hidden" name="action" value="getOne_For_Update">
					</form>
				</td>
				<td>
					${cartVO.procount * productSvc.getOneProduct(cartVO.prono).proprice}��
				</td>
				
				<td>
					<form method="post" action="cart.do">
					    <input type="submit" value="�R��">
					    <input type="hidden" name="memno" value="${cartVO.memno}">
					    <input type="hidden" name="prono" value="${cartVO.prono}">
					    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
					    <input type="hidden" name="action" value="delete_One_Product">
					</form>
				</td>
			</tr>
			<c:set var="memno" value="${cartVO.memno}" scope="page" />
			
		</c:forEach>
		
	</table>
	
	
	<br>
	<!-- ���b���s�A�ǰeid�B�ʪ���list -->
	
	<form method="post"	action="<%=request.getContextPath()%>/front-end/orders/orders.do">
		<input type="submit" value="���b">
		<input type="hidden" name="memno" value="${memno}">
		<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
		<input type="hidden" name="action" value="write_Orders_Detail">
	</form>


</body>
</html>