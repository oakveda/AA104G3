<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>


<%
	ProductService productSvc = new ProductService();
	//ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<style type="text/css">
img{
	height:100px;
	width:100px;
}
table {
	text-align: 'center';
}
</style>
<title>單一商品資料</title>
</head>
<body>	
	<a href="<%=request.getContextPath()%>/select_page.jsp">回首頁</a>
	<br>
	<br>
	<c:if test="${not empty errorMsgs}">
		<ul>
			<c:forEach var="msg" items="${errorMsgs}">
				<li>${msg}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table border='1'>
		<tr>
			<th>商品編號</th>
			<th>店家編號</th>
			<th>商品類別編號</th>
			<th>商品名稱</th>
			<th>商品價格</th>
			<th>商品狀態</th>
			<th>商品庫存</th>
			<th>上架日期</th>
			<th>遊玩人數</th>
			<th>建議年齡</th>
			<th>語言</th>
			<th>遊戲時間</th>
			<th>商品簡介</th>
		</tr>

		<tr>
			<td>${productVO.prono}</td>
			<td>${productVO.stono}</td>
			<td>${productVO.classno}</td>
			<td>${productVO.proname}</td>
			<td>${productVO.proprice}</td>
			<td>${productVO.prostate}</td>
			<td>${productVO.proqty}</td>
			<td>${productVO.adddate}</td>
			<td>${productVO.playernum}</td>
			<td>${productVO.playerage}</td>
			<td>${productVO.lang}</td>
			<td>${productVO.playtime}</td>
			<td>${productVO.prodesc}</td>
			<td><img alt="" src="/AA104G3/ShowPicture?prono=${productVO.prono}"></td>
			
			<td>
				<form method="post"
					action="<%=request.getContextPath()%>/product/product.do">
					<input type="submit" value="修改"> <input type="hidden"
						name="prono" value="${productVO.prono}"> <input
						type="hidden" name="action" value="getOne_For_Update">
				</form>
			</td>
<%-- 			
			<td>
				<form method="post"
					action="<%=request.getContextPath()%>/product/product.do">
					<input type="submit" value="刪除"> <input type="hidden"
						name="prono" value="${productVO.prono}"> <input
						type="hidden" name="action" value="delete">
				</form>
			</td>
--%>
		</tr>

	</table>

</body>
</html>