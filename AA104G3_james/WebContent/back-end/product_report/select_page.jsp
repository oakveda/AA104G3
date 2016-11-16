<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>商品檢舉管理首頁</title>
</head>
<body>
	<c:if test="${not empty errorMsgs}">
		<ul>
			<c:forEach var="msg" items="${errorMsgs}">
				<li>${msg}</li>
			</c:forEach>
		</ul>
	</c:if>
</body>

<ul>
	<li><a href="<%=request.getContextPath()%>/back-end/product_report/listAllProduct_report.jsp">檢舉清單列表</a></li>
	<li><a href="<%=request.getContextPath()%>/back-end/product_report/addProduct_report.jsp">新增商品檢舉</a></li>
</ul>

</html>