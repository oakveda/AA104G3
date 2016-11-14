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
<title>修改商品</title>
</head>
<body>
	<a href="<%=request.getContextPath()%>/select_page.jsp">回首頁</a>
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
				<td>商品編號:</td>
				<td><%=productVO.getProno()%></td>
			</tr>
			<tr>
				<td>商店編號:</td>
				<td><%=productVO.getStono()%></td>
			</tr>
			<jsp:useBean id="product_classSvc" scope="page"
				class="com.product_class.model.Product_classService"></jsp:useBean>
			<tr>
				<td>類別編號:</td>
				<td><select name="classno">
						<c:forEach var="product_classVO" items="${product_classSvc.all}">

							<option value="${product_classVO.classno}"
								${(productVO.classno == product_classVO.classno)? 'selected':''}>${product_classVO.classname}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>商品名稱:</td>
				<td><input type="text" name="proname"
					value="<%=(productVO == null) ? "Cry Havoc 浩劫驚魂" : productVO.getProname()%>" />
				</td>
			</tr>
			<tr>
				<td>商品價格:</td>
				<td><input type="text" name="proprice"
					value="<%=(productVO == null) ? 1000 : productVO.getProprice()%>" /></td>
			</tr>
			<tr>
				<td>商品狀態:</td>
				<td><input type="text" name="prostate"
					value="<%=(productVO == null) ? 0 : productVO.getProstate()%>" /></td>
			</tr>
			<tr>
				<td>商品庫存:</td>
				<td><input type="text" name="proqty"
					value="<%=(productVO == null) ? 5 : productVO.getProqty()%>" /></td>
			</tr>
			<tr>
				<td>遊玩人數:</td>
				<td><input type="text" name="playernum"
					value="<%=(productVO == null) ? "6人" : productVO.getPlayernum()%>" /></td>
			</tr>
			<tr>
				<td>遊玩年紀:</td>
				<td><input type="text" name="playerage"
					value="<%=(productVO == null) ? "10+" : productVO.getPlayerage()%>" /></td>
			</tr>
			<tr>
				<td>語言:</td>
				<td><input type="text" name="lang"
					value="<%=(productVO == null) ? "英文" : productVO.getLang()%>" /></td>
			</tr>
			<tr>
				<td>遊玩時間:</td>
				<td><input type="text" name="playtime"
					value="<%=(productVO == null) ? "1小時" : productVO.getPlaytime()%>" /></td>
			</tr>
			<tr>
				<td>商品敘述:</td>
				<td><textarea name="prodesc" cols="40" rows="5"><%=(productVO == null)
					? "三個強大的種族-人類、機械和朝聖者-同時發現了一顆資源豐富得超乎想像的星球，但是當地的原住民Trogs拒絕拱手讓出自己的土地。一場蔓延整顆星球的戰爭開始了，勝者將獨享豐美的果實，敗者只能空手而回..."
					: productVO.getProdesc()%></textarea></td>
			</tr>
			<tr>
				<td>圖片預覽:</td>
				<td><img id="image" style="width: 100px; height: 100px;"></td>
			</tr>
			<tr>
				<td>上傳圖片:</td>
				<td><input type="file" name="addimg" id="myFile"></td>
			</tr>
		</table>
		<input type="hidden" name="prono" value="${productVO.prono}">
		<input type="hidden" name="stono" value="${productVO.stono}">
		<input type="hidden" name="action" value="update" /> <input
			type="submit" value="修改" />
	</form>
</body>
</html>