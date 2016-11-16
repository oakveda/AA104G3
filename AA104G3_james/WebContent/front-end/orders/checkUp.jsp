<!-- 從OrdersServlet的check_Orders_Detail轉出 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.orders.model.*"%>
<%@ page import="com.cart.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="util.*"%>


<!DOCTYPE html>
<html lang="">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>訂單資訊確認</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
</head>

<body>
	<a
		href="<%=request.getContextPath()%>/front-end/orders/select_page.jsp">回首頁</a>
	<br>
	<br>
	<!-- ==============================頁面開始============================== -->
	<div class="container">
		<div class="row">
			<!-- ==============================顯示訂單資料============================== -->
			<div class="col-xs-12 col-sm-12">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">結帳</h3>
					</div>
					<div class="panel-body">
						<b>訂單確認</b>
					</div>
					<table class="table">

						<tr>
							<td>收件人 : ${ordersVO.pickname}</td>
						</tr>
						<tr>
							<td>連絡電話 : ${ordersVO.pickphone}</td>
						</tr>
						<tr>
							<td>收件地址 : ${ordersVO.pickaddr}</td>
						</tr>

						<c:set var="pickup" value="${ordersVO.pickup}" />
						<tr>
							<td>收件方式 : <%=Change.changePickup((String) pageContext.getAttribute("pickup"))%>
							</td>
						</tr>
						<tr>
							<td>下單日期 : ${ordersVO.orddate}</td>
						</tr>
						<tr>
							<td class="bg-danger">銀行代碼 : ${sto.bankno}</td>
						</tr>
						<tr>
							<td class="bg-danger">匯款銀行名稱 : ${sto.bankname}</td>
						</tr>
						<tr>
							<td class="bg-danger">匯款銀行戶名 : ${sto.accountname}</td>
						</tr>
						<tr>
							<td class="bg-danger">匯款帳號 : ${sto.accountno}</td>
						</tr>
						<tr>
							<td class="bg-danger">付款金額 : 新台幣 $ ${ordersVO.pricesum} 元</td>
						</tr>
					</table>
				</div>
				<br> <br>





				<div class="panel panel-info">
					<div class="panel-heading">訂購商品</div>
					<div class="panel-body">
						<table class="table">
							<tr>
								<th>產品名稱</th>
								<th>價格</th>
								<th>購買數量</th>
								<th>小計</th>
							</tr>
							<%
								LinkedHashSet<CartVO> checkList = (LinkedHashSet<CartVO>) session.getAttribute("checkList");
							%>
							<jsp:useBean id="productSvc"
								class="com.product.model.ProductService" />
							<c:forEach var="cartVO" items="${checkList}">
								<tr>
									<td>${productSvc.getOneProduct(cartVO.prono).proname}</td>
									<td>${productSvc.getOneProduct(cartVO.prono).proprice}元</td>
									<td>${cartVO.procount}
									<td>${cartVO.procount * productSvc.getOneProduct(cartVO.prono).proprice}元
									</td>
								</tr>
							</c:forEach>
						</table>

					</div>
				</div>

				<!-- 移除結帳過的購物車 -->
				<%-- <%session.removeAttribute("checkList");%> --%>
			</div>
			<!-- ==============================顯示訂單資料結束============================== -->
		</div>
		<!-- container over -->
	</div>
	<!-- row over -->
	<!-- ==============================頁面結束============================== -->
	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>

</html>