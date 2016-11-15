<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cart.model.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>cart: Home</title>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
</head>
<body>
memberVO = null : ${empty memberVO}<br>${memberVO.memno}<br>
cartList.size :  <%=((LinkedHashSet<CartVO>)session.getAttribute("cartList")).size() %>

	<!-- 錯誤訊息 -->
	<c:if test="${not empty errorMsgs}">
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li>${message}</li>
				<br>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<!-- 秀出所有資料 -->
		<li><a href='<%=request.getContextPath()%>/front-end/cart/listAllCart.jsp'>List</a> all Carts.</li>
		<br>
		<li>
			<!-- 輸入搜尋條件 -->
			<form method="post" action="<%=request.getContextPath()%>/cart/cart.do">
				<b>輸入會員編號:</b> <input type="text" name="memno"> <input
					type="submit" value="送出"> <input type="hidden"
					name="action" value="getOne_For_Display">
			</form>
		</li>
		<br>


		<jsp:useBean id="cartSvc" scope="page"
			class="com.cart.model.CartService" />

		<li>
			<!-- 輸入搜尋條件 -->
			<form method="post" action="<%=request.getContextPath()%>/cart/cart.do">
				<b>選擇會員編號:</b> <select size="1" name="memno">
					<c:forEach var="cartVO" items="${cartSvc.all}">
						<option value="${cartVO.memno}">${cartVO.memno}
					</c:forEach>
				</select> 
					<input type="submit" value="查詢"> 
					<input type="hidden" name="action" value="getOne_For_Display">
			</form>
		</li>
		<br>
		<li><a href='<%=request.getContextPath()%>/front-end/cart/addCart.jsp'>Add</a> a new item.</li>
		<br>
	</ul>

</body>
</html>