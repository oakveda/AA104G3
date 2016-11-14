<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="listProducts_ByClassno" scope="request"
	type="java.util.Set" />
<jsp:useBean id="product_classSvc" scope="page"
	class="com.product_class.model.Product_classService" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<style>
table {
	text-align: 'center';
}
</style>
<title>由類別編號選擇商品</title>
</head>
<body>
	<a href="<%=request.getContextPath()%>/select_page.jsp">回首頁</a>
	<br>
	<br>
	<!-- 錯誤列表 -->
	<c:if test="${not empty errorMsgs}">
		<ul>
			<c:forEach var="msg" items="${errorMsgs}">
				<li>${msg}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table border='1'>
		<tr>
			<th>商品編號</th>
			<th>商品類別</th>
			<th>商品名稱</th>
			<th>商品價格</th>
			<th>商品介紹</th>
		</tr>

		<c:forEach var="productVO" items="${listProducts_ByClassno}">
			<tr>
				<td>${productVO.prono}</td>
				<td><c:forEach var="product_classVO"
						items="${product_classSvc.all }">
						<c:if test="${productVO.classno==product_classVO.classno }">
							${product_classVO.classname}
						</c:if>
					</c:forEach></td>
				<td>${productVO.proname }</td>
				<td>${productVO.proprice }元</td>
				<td>${productVO.prodesc }</td>

			</tr>
		</c:forEach>
	</table>


</body>
</html>