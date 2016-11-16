<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product_class.model.*"%>


<%-- <%
	Product_classService product_classSvc = new Product_classService();
	List<Product_classVO> list = product_classSvc.getAll();
	request.setAttribute("list", list);
%> --%>
<jsp:useBean id="product_classSvc" scope="page"
	class="com.product_class.model.Product_classService"></jsp:useBean>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<style>
table {f
	text-align: 'center';
}
</style>
<title>�Ҧ����O</title>
</head>
<body>
	<a href="<%=request.getContextPath()%>/select_page.jsp">�^����</a>
	<br>
	<br>
	<!-- ���~�C�� -->
	<c:if test="${not empty errorMsgs}">
		<ul>
			<c:forEach var="msg" items="${errorMsgs}">
				<li>${msg}</li>
			</c:forEach>
		</ul>
	</c:if>


	<table>
		<tr>
			<th>���O�s��</th>
			<th>���O�W��</th>
			<th>�ק�</th>
			<th>�R��</th>
			<th>�d�����O</th>
		</tr>
		<c:forEach var="product_classVO" items="${product_classSvc.all}">
			<tr>
				<td>${product_classVO.classno}</td>
				<td>${product_classVO.classname}</td>
				<td>
					<form method="post"
						action="<%=request.getContextPath()%>/prodcut_class/product_class.do">
						<input type="submit" value="�ק�" disabled="true"> <input
							type="hidden" name="classno" value="${prodcut_classVO.classno}">
						<input type="hidden" name="action"
							value="getOne_For_Update_Product_class">
					</form>
				</td>
				<td>
					<form method="post"
						action="<%=request.getContextPath()%>/product_class/product_class.do">
						<input type="submit" value="�R��"> <input type="hidden"
							name="classno" value="${product_classVO.classno}"> <input
							type="hidden" name="action" value="delete_Product_class">
					</form>
				</td>

				<td>
					<form method="post"
						action="<%=request.getContextPath()%>/product_class/product_class.do">
						<input type="submit" value="�e�X�d��"> 
						<input type="hidden" name="classno" value="${product_classVO.classno}"> 
						<input type="hidden" name="action" value="listProducts_ByClassno_b">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<%
		if (request.getAttribute("listProducts_ByClassno") != null) {
	%>
	<jsp:include page="listProducts_ByClassno.jsp" />
	<%
		}
	%>

</body>
</html>