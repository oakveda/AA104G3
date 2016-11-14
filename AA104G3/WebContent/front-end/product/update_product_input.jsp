<%@page import="com.product_class.model.Product_classVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<script src="js/imgRead.js"></script>
<title>�ק�ӫ~</title>
</head>
<body>
	<a href="<%=request.getContextPath()%>/select_page.jsp">�^����</a>
	<br>
	<br>

	<ul>
		<c:if test="${not empty errorMsgs}">
			<c:forEach var="msg" items="${errorMsgs}">
				<li>${msg}</li>
			</c:forEach>
		</c:if>
	</ul>

	<form method="post"
		action="<%=request.getContextPath()%>/product/product.do"
		name="form1" enctype="multipart/form-data">
		<table border=0>
			<jsp:useBean id="storeSvc" scope="page"
				class="com.store.model.StoreService"></jsp:useBean>

			<tr>
				<td>�ӫ~�s��:</td>
				<td><%=productVO.getProno()%></td>
			</tr>
			<tr>
				<td>�ө��s��:</td>
				<td><%=productVO.getStono()%></td>
			</tr>
			<jsp:useBean id="product_classSvc" scope="page"
				class="com.product_class.model.Product_classService"></jsp:useBean>
			<tr>
				<td>���O�s��:</td>
				<td><select name="classno">
						<c:forEach var="product_classVO" items="${product_classSvc.all}">

							<option value="${product_classVO.classno}"
								${(productVO.classno == product_classVO.classno)? 'selected':''}>${product_classVO.classname}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>�ӫ~�W��:</td>
				<td><input type="text" name="proname"
					value="<%=(productVO == null) ? "Cry Havoc �E�T���" : productVO.getProname()%>" />
				</td>
			</tr>
			<tr>
				<td>�ӫ~����:</td>
				<td><input type="text" name="proprice"
					value="<%=(productVO == null) ? 1000 : productVO.getProprice()%>" /></td>
			</tr>
			<tr>
				<td>�ӫ~���A:</td>
				<td><input type="text" name="prostate"
					value="<%=(productVO == null) ? 0 : productVO.getProstate()%>" /></td>
			</tr>
			<tr>
				<td>�ӫ~�w�s:</td>
				<td><input type="text" name="proqty"
					value="<%=(productVO == null) ? 5 : productVO.getProqty()%>" /></td>
			</tr>
			<tr>
				<td>�C���H��:</td>
				<td><input type="text" name="playernum"
					value="<%=(productVO == null) ? "6�H" : productVO.getPlayernum()%>" /></td>
			</tr>
			<tr>
				<td>�C���~��:</td>
				<td><input type="text" name="playerage"
					value="<%=(productVO == null) ? "10+" : productVO.getPlayerage()%>" /></td>
			</tr>
			<tr>
				<td>�y��:</td>
				<td><input type="text" name="lang"
					value="<%=(productVO == null) ? "�^��" : productVO.getLang()%>" /></td>
			</tr>
			<tr>
				<td>�C���ɶ�:</td>
				<td><input type="text" name="playtime"
					value="<%=(productVO == null) ? "1�p��" : productVO.getPlaytime()%>" /></td>
			</tr>
			<tr>
				<td>�ӫ~�ԭz:</td>
				<td><textarea name="prodesc" cols="40" rows="5"><%=(productVO == null)
					? "�T�ӱj�j���ر�-�H���B����M�¸t��-�P�ɵo�{�F�@���귽�״I�o�W�G�Q�����P�y�A���O��a������Trogs�ڵ��������X�ۤv���g�a�C�@�����������P�y���Ԫ��}�l�F�A�Ӫ̱N�W���׬����G��A�Ѫ̥u��Ť�Ӧ^..."
					: productVO.getProdesc()%></textarea></td>
			</tr>
			<tr>
				<td>�Ϥ��w��:</td>
				<td><img id="image" style="width: 100px; height: 100px;"></td>
			</tr>
			<tr>
				<td>�W�ǹϤ�:</td>
				<td><input type="file" name="addimg" id="myFile"></td>
			</tr>
		</table>
		<input type="hidden" name="prono" value="${productVO.prono}">
		<input type="hidden" name="stono" value="${productVO.stono}">
		<input type="hidden" name="action" value="update" /> <input
			type="submit" value="�ק�" />
	</form>
</body>
</html>