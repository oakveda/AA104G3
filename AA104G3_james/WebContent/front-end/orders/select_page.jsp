<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>orders : home</title>
</head>
<body>
	<c:if test="${not empty errorMsgs}">
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li>${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='listAllOrders.jsp'>列出所有訂單</a></li>
		<br>

		<!-- 查詢會員訂單 -->
		<jsp:useBean id="memberSvc" class="com.member.model.MemberService"></jsp:useBean>
		<b>選擇會員編號 : </b>
		<li>
			<form method="post" action="<%=request.getContextPath()%>/orders/orders.do">
				<select name="memno">
					<c:forEach var="memberVO" items="${memberSvc.all}">
						<option value="${memberVO.memno}">${memberVO.memname}</option>
					</c:forEach>
				</select> 
				<input type="hidden" name="action" value="get_One_Member">
				<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
				<input type="submit">
			</form>
		</li>
	</ul>
</body>
</html>