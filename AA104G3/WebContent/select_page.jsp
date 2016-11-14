<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<style>
	
</style>
<title>Product: Home</title>
</head>
<body>

	<!-- ���~�C�� -->
	<c:if test="${not empty errorMsgs}">
		<ul>
			<c:forEach var="msg" items="${errorMsgs}">
				<li>${msg}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<!-- �C�X�Ҧ��ӫ~ -->
		<li><a href='<%=request.getContextPath()%>/product/product.do?action=getAllByDate'>List</a> all Products.</li>
		<li><a href='<%=request.getContextPath()%>/front-end/product/listAllProduct.jsp'>List</a> all Products.</li>
		<li>
			<!-- ��J���X�d�߳浧 -->
			<form method="post" action="<%=request.getContextPath()%>/product/product.do">
				<b>��J�ӫ~�s��(ex:000001):</b>						 
				<input type="text" name="prono">
				<input type="submit">
				<input type='hidden' name="action" value="getOne_For_Display">
			</form>
		</li>
		
		<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService"/>
		
		<li>
			<!-- ��ܸ��X�d�߳浧 -->
			<form method="post" action="<%=request.getContextPath()%>/product/product.do">
			<!-- ��ܦW�٬d�߳浧 -->
				<b>��ܰӫ~:</b>
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
		<!-- ������O�d�߳浧 -->
		<li>
			<form method="post" action="<%=request.getContextPath()%>/product_class/product_class.do">
			<b>������O:</b>
			<select name="classno">
				<c:forEach var="product_classVO" items="${product_classSvc.all}">
					<option value="${product_classVO.classno}">${product_classVO.classname}</option>
				</c:forEach>
			</select>
			<input type="submit" value="�e�X">
			<input type="hidden" name="action" value="listProducts_ByClassno_a">
			</form>
		</li>
		<!-- �ƦX�d�� -->
		<li>
			<form method="post" action="<%=request.getContextPath()%>/product/product.do" name="form1">
				<b>�ƦX�d��</b><br>
				<b>��J�ӫ~�s��</b>
				<input type="text" name="prono" value="000003"><br>
				<b>��J�ӫ~�W��</b>
				<input type="text" name="proname" value="���Q�i�S"><br>
				<b>��ܰӫ~���O</b>
				<select name="classno">
					<option value="">
					<c:forEach var="product_classVO" items="${product_classSvc.all}">
						<option value=${product_classVO.classno }>${product_classVO.classname}
					</c:forEach>
				</select><br>
				<input type="submit">
				<input type="hidden" name="action" value="listProducts_ByCompositeQuery">				
			</form>
		</li>
		<!-- �s�W�@���ӫ~ -->
		<li><a href='<%=request.getContextPath()%>/front-end/product/addProduct.jsp'>Add</a> a new Product.</li>
		<!-- ���O�޲z -->
		<b>���O�޲z:</b>
		<li><a href='<%=request.getContextPath()%>/front-end/product_class/listAllProduct_class.jsp'>List</a> all Classes.</li>
	</ul>
	
	
	

</body>
</html>