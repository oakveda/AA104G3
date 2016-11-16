<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product_report.model.*"%>

<jsp:useBean id="product_reportSvc"
	class="com.product_report.model.Product_reportService" />
<jsp:useBean id="memberSvc"
	class="com.member.model.MemberService" />
<jsp:useBean id="productSvc"
	class="com.product.model.ProductService" />
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<style>

</style>
<title>�ӫ~���|�M��C��</title>
</head>
<body>
	<a
		href="<%=request.getContextPath()%>/back-end/product_report/select_page.jsp">�^����</a>
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
			<th>�^���s��</th>
			<th>�^���|��</th>
			<th>�ӫ~�W��</th>
			<th>�^���z��</th>
			<th>�^�����</th>
			<th>�f�֪��A</th>
			
		</tr>
		<c:forEach var="prodcut_reportVO" items="${product_reportSvc.all}">
			<tr>
				<td>${prodcut_reportVO.prorepno}</td>
				<td>${memberSvc.getOneMember(prodcut_reportVO.memno).memname}</td>
				<td>${productSvc.getOneProduct(prodcut_reportVO.prono).proname}</td>
				<td>${prodcut_reportVO.reason}</td>
				<td>${prodcut_reportVO.repdate}</td>
				<td>${prodcut_reportVO.checked}</td>
				<td>
					<form action="post" action="<%=request.getContextPath()%>/back-end/product_report.do">
						<input type="submit" value="�f��">
						<input type="hidden" name="prorepno" value="${prodcut_reportVO.prorepno}">
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">						
						<input type="hidden" name="action" value="getOne_For_Update">
					</form>
				</td>				
			</tr>
		</c:forEach>
	</table>
</body>
</html>