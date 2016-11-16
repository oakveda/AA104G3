<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_report.model.*"%>
<%
	Product_reportVO product_reportVO = (Product_reportVO) request.getAttribute("product_reportVO");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
</head>
<body>
	<a
		href="<%=request.getContextPath()%>/back-end/product_report/select_page.jsp">回首頁</a>
	<br>
	<br>
	<c:if test="${not empty errorMsgs}">
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li>${message}</li>				
			</c:forEach>
		</ul>
	</c:if>



	<jsp:useBean id="productSvc" class="com.product.model.ProductService" />
	<jsp:useBean id="memberSvc" class="com.member.model.MemberService" />

	<form method="post"
		action="<%=request.getContextPath()%>/product_report/product_report.do"
		name="form1">
		<table>
			<tr>
				<td>商品名稱 : <select name="prono">
						<c:forEach var="productVO" items="${productSvc.all}">
							<option value="${productVO.prono}">${productVO.proname}
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>會員名稱 : <select name="memno">
						<c:forEach var="memberVO" items="${memberSvc.all}">
							<option value="${memberVO.memno}">${memberVO.memname}
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>請輸入檢舉理由(200字以內):</td>
			</tr>
			<tr>
				<td><textarea name="reason" rows="7" cols="60"
						style="border: 1px solid skyblue; resize: none"></textarea></td>
			</tr>
		</table>
		<input type="hidden" name="action" value="insert"> <input
			type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
		<input type="submit" value="送出檢舉">
	</form>

</body>
</html>