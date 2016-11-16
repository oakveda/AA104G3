<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cart.model.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<style>
	
</style>
<title>Product: Home</title>
</head>
<body>

<%-- memberVO = null : ${empty memberVO}<br>${memberVO.memno}<br>
cartList.size : <%=((LinkedHashSet<CartVO>)session.getAttribute("cartList")).size() %> --%>

	<!-- 錯誤列表 -->
	<c:if test="${not empty errorMsgs}">
		<ul>
			<c:forEach var="msg" items="${errorMsgs}">
				<li>${msg}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<!-- 列出所有商品 -->
		<li><a href='<%=request.getContextPath()%>/product/product.do?action=getAllByDate'>List</a> all Products.</li>
		<li><a href='<%=request.getContextPath()%>/front-end/product/listAllProduct.jsp'>List</a> all Products.</li>
		<li>
			<!-- 輸入號碼查詢單筆 -->
			<form method="post" action="<%=request.getContextPath()%>/product/product.do">
				<b>輸入商品編號(ex:000001):</b>						 
				<input type="text" name="prono">
				<input type="submit">
				<input type='hidden' name="action" value="getOne_For_Display">
			</form>
		</li>
		
		<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService"/>
		
		<li>
			<!-- 選擇號碼查詢單筆 -->
			<form method="post" action="<%=request.getContextPath()%>/product/product.do">
			<!-- 選擇名稱查詢單筆 -->
				<b>選擇商品:</b>
				<select name="prono">
					<c:forEach var="productVO" items="${productSvc.all}">
						<option value="${productVO.prono}">${productVO.proname}</option>
					</c:forEach>
				</select>
				<input type="submit">
				<input type='hidden' name="action" value="getOne_For_Display">
			</form>
		</li>
		
		<jsp:useBean id="product_classSvc" scope="page" class="com.product_class.model.Product_classService"></jsp:useBean>
		<!-- 選擇類別查詢單筆 -->
		<li>
			<form method="post" action="<%=request.getContextPath()%>/product_class/product_class.do">
			<b>選擇類別:</b>
			<select name="classno">
				<c:forEach var="product_classVO" items="${product_classSvc.all}">
					<option value="${product_classVO.classno}">${product_classVO.classname}</option>
				</c:forEach>
			</select>
			<input type="submit" value="送出">
			<input type="hidden" name="action" value="listProducts_ByClassno_a">
			</form>
		</li>
		<!-- 複合查詢 -->
		<li>
			<form method="post" action="<%=request.getContextPath()%>/product/product.do" name="form1">
				<b>複合查詢</b><br>
				<b>輸入商品編號</b>
				<input type="text" name="prono" value="000003"><br>
				<b>輸入商品名稱</b>
				<input type="text" name="proname" value="哈利波特"><br>
				<b>選擇商品類別</b>
				<select name="product_classVO.classno">
					<option value="">
					<c:forEach var="product_classVO" items="${product_classSvc.all}">
						<option value=${product_classVO.classno }>${product_classVO.classname}
					</c:forEach>
				</select><br>
				<input type="submit">
				<input type="hidden" name="action" value="listProducts_ByCompositeQuery">				
			</form>
		</li>
		<!-- 新增一項商品 -->
		<li><a href='<%=request.getContextPath()%>/front-end/product/addProduct.jsp'>Add</a> a new Product.</li>
		<!-- 類別管理 -->
		<b>類別管理:</b>
		<li><a href='<%=request.getContextPath()%>/front-end/product_class/listAllProduct_class.jsp'>List</a> all Classes.</li>
	</ul>	

</body>
</html>