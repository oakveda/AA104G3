<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cart.model.*"%>
<%
	CartVO cartVO = (CartVO) request.getAttribute("cartVO");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>新增資料</title>
</head>
<body>
	<a href="<%=request.getContextPath()%>/front-end/cart/select_page.jsp">回首頁</a>
	<br>
	<br>

	<c:if test="${not empty errorMsgs}">
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li>${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<jsp:useBean id="memberSvc" class="com.member.model.MemberService"></jsp:useBean>
	<jsp:useBean id="productSvc" class="com.product.model.ProductService"></jsp:useBean>

	<form method="post" action="<%=request.getContextPath()%>/cart/cart.do"
		name="form1">
		<table>
			<tr>
				<td>會員編號:</td>
				<td><select name="memno">
						<c:forEach var="memberVO" items="${memberSvc.all}">
							<option value="${memberVO.memno}">${memberVO.memname}
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>商品編號:</td>
				<td><select name="prono">
						<c:forEach var="productVO" items="${productSvc.all}">
							<option value="${productVO.prono}">${productVO.proname}
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>商品數量:</td>
				<td><input type="text" name="procount"
					value="<%=(cartVO == null) ? 1 : cartVO.getProcount()%>" min="1"></td>
			</tr>
		</table>

		<c:if test="${memberVO.memno != '000000'}">
			<!-- 給會員用的新增到購物車 -->
			<input type="hidden" name="action" value="insert">
			<input type="submit" value="送出新增">
		</c:if>

		<c:if test="${memberVO.memno == '000000'}">
			<!-- 給訪客用的新增到購物車 -->
			<input type="hidden" name="action" value="insert_For_Guest">
			<input type="submit" value="送出新增">
		</c:if>

	</FORM>
</body>
</html>