<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="listProducts_ByCompositeQuery" scope="request"
	type="java.util.List"></jsp:useBean>
<jsp:useBean id="product_classSvc" scope="page"
	class="com.product_class.model.Product_classService" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<style type="text/css">
table {
	text-align: center;
}
</style>
<title>�ƦX�d��</title>
</head>
<body>
	<a href="<%=request.getContextPath()%>/select_page.jsp">�^����</a>
	<br>
	<br>

	<table>
		<tr>
			<th>�ӫ~�s��</th>
			<th>�ӫ~�W��</th>
			<th>�ӫ~����</th>
			<th>�ӫ~���O</th>
		</tr>
		<c:forEach var="productVO" items="${listProducts_ByCompositeQuery}">
			<tr>
				<td>${productVO.prono}</td>
				<td>${productVO.proname}</td>
				<td>${productVO.proprice}</td>
				<td><c:forEach var="product_classVO"
						items="${product_classSvc.all}">
						<c:if test="${productVO.classno==product_classVO.classno }">
							${product_classVO.classname}
						</c:if>
					</c:forEach></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>