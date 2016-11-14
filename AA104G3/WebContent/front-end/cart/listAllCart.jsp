<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cart.model.*"%>
<%
	CartService cartSvc = new CartService();
	LinkedHashSet<CartVO> list = cartSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="memberSvc" scope="page"
	class="com.member.model.MemberService"></jsp:useBean>
<jsp:useBean id="prodcutSvc" scope="page"
	class="com.product.model.ProductService"></jsp:useBean>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<style type="text/css">
table {
	text-align: center;
}
</style>
<title>�Ҧ��ʪ������ listAllCart</title>
</head>

<body>
	<a href="<%=request.getContextPath()%>/front-end/cart/select_page.jsp">�^����</a>
	<br>
	<br>
	<!-- ��ܿ��~�C�� -->
	<c:if test="${not empty errorMsgs}">
		<ul>
			<c:forEach var="message" items="${errorMsgs}}">
				<li>${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<table border='1'>
		<tr>
			<th>�|���s��</th>
			<th>���~�s��</th>
			<th>���~�ƶq</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="cartVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">		
			<tr>
				<%-- <td>${cartVO.memno}</td> --%>
				<td>${memberSvc.getOneMember(cartVO.memno).memname}</td>
				<%-- <td>${cartVO.prono}</td> --%>
				<td>${prodcutSvc.getOneProduct(cartVO.prono).proname}</td>
				<td>${cartVO.procount}</td>

				<td>
					<form method="post"
						action="<%=request.getContextPath()%>/cart/cart.do">
						<input type="submit" value="�ק�"> <input type="hidden"
							name="memno" value="${cartVO.memno}"> <input
							type="hidden" name="prono" value="${cartVO.prono}"> <input
							type="hidden" name="action" value="getOne_For_Update">
							<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
					</form>
				</td>
				<td>
					<form method="post"
						action="<%=request.getContextPath()%>/cart/cart.do">
						<input type="submit" value="�R��"> <input type="hidden"
							name="memno" value="${cartVO.memno}"> <input
							type="hidden" name="prono" value="${cartVO.prono}"> <input
							type="hidden" name="action" value="delete_One_Product">
							<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>
</body>

</html>
