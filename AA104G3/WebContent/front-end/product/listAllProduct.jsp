<%@ page import="util.Change"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product.controller.*"%>
<%@ page import="java.io.*"%>

<%
	ProductService productSvc = new ProductService();
	List<ProductVO> list = productSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<jsp:useBean id="storeSvc" scope="page"
	class="com.store.model.StoreService"></jsp:useBean>
<jsp:useBean id="product_classSvc" scope="page"
	class="com.product_class.model.Product_classService"></jsp:useBean>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<style type="text/css">
img {
	height: 100px;
	width: 100px;
}

table {
	text-align: 'center';
}
</style>
<title>所有商品資料</title>
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
			<th>商品圖片</th>
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

		<%@ include file="page1.file"%>
		<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
				<%-- 			<tr ${(prodcutVO.prono==param.prono)?'bgcolor=skyblue':''}><!-- 更改修改過的背景顏色 --> --%>
				<td>${productVO.prono}</td>
				<td>${storeSvc.getOneStore(productVO.stono).stoname}</td>
				<td>${product_classSvc.getOneProduct_class(productVO.classno).classname}</td>
				<td>${productVO.proname}</td>
				<td><img alt="商品圖片"
					src="/AA104G3/product/ShowPicture?prono=${productVO.prono}"></td>
				<td>${productVO.proprice}</td>
				
				<c:set var="state" value="${productVO.prostate}" scope="request"></c:set>
				<td><%=Change.changeProState((String) request.getAttribute("state"))%></td>

				<td>${productVO.proqty}</td>
				<td>${productVO.adddate}</td>
				<td>${productVO.playernum}</td>
				<td>${productVO.playerage}</td>
				<td>${productVO.lang}</td>
				<td>${productVO.playtime}</td>
				<td>${productVO.prodesc}</td>

				<td>
					<form method="post"
						action="<%=request.getContextPath()%>/product/product.do">
						<input type="submit" value="修改"> <input type="hidden"
							name="prono" value="${productVO.prono}"> <input
							type="hidden" name="action" value="getOne_For_Update"> <input
							type="hidden" name="requestURL"
							value="<%=request.getServletPath()%>">
						<!-- 當前頁面的路徑 -->
						<input type="hidden" name="whichPage" value="<%=whichPage%>">
						<!--當前頁面的頁碼-->
					</form>
				</td>
				<td>
					<form method="post"
						action="<%=request.getContextPath()%>/product/product.do">
						<input type="submit" value="刪除"> <input type="hidden"
							name="prono" value="${productVO.prono}"> <input
							type="hidden" name="action" value="delete"> <input
							type="hidden" name="requestURL"
							value="<%=request.getServletPath()%>"> <input
							type="hidden" name="whichPage" value="<%=whichPage%>">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

	<b>servlet path : <%=request.getServletPath()%></b>
	<br>
	<b>request uri : <%=request.getRequestURI()%></b>
</body>
</html>


