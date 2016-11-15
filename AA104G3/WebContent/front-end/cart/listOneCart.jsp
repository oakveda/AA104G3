<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cart.model.*"%>


<jsp:useBean id="memberSvc" scope="page"
	class="com.member.model.MemberService"></jsp:useBean>
<jsp:useBean id="productSvc" scope="page"
	class="com.product.model.ProductService"></jsp:useBean>
<jsp:useBean id="storeSvc" class="com.store.model.StoreService" />

<!DOCTYPE html>
<html lang="">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>個別購物車資料</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/main.css">
<!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
<link rel="stylesheet" href="css/main.css">
<style type="text/css">
.navbar {
	margin-bottom: 30px;
}

.ck {
	float: right;
	padding-right: 5px;
}
</style>
</head>

<body>

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-4"></div>
			<div class="col-xs-12 col-sm-4" style="text-align: center;">
				<img src="images/logo.png">
			</div>
			<div class="col-xs-12 col-sm-4">
				<div class="collapse navbar-collapse navbar-ex1-collapse">
					<!-- 右選單 -->
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#">${memberVO.memname} 您好</a></li>
						<li><a href="#">登出</a></li>
					</ul>
				</div>
			</div>
		</div>
		<!-- End of row-->
	</div>
	<!-- 導覽列 -->
	<nav class="navbar navbar-default" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle aa"
					data-toggle="collapse" data-target=".navbar-ex1-collapse">
					<span class="sr-only">選單切換</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
			</div>
			<!-- 手機隱藏選單區 -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<!-- 左選單 -->
				<ul class="nav navbar-nav">
					<li><a href="index.html">首頁</a></li>
					<li><a href="news.html">最新消息</a></li>
					<li class="active"><a href="product.html">商城</a></li>
					<li><a href="team.html">揪團</a></li>
					<li><a href="store.html">店家</a></li>
					<li><a href="faq.html">常見問答</a></li>
					<li><a href="#">聯絡我們</a></li>
				</ul>
			</div>
		</div>
		<!-- 手機隱藏選單區結束 -->
	</nav>
	<!-- ===============================主要頁面=============================== -->
	<div class="container">
		<div class="row">
			<!-- ==============================購物車頁面============================== -->
			<div class="col-xs-12 col-sm-12">

				<!-- 把session內的購物車清單依店家分類，放到map內 -->
				<%
					LinkedHashSet<CartVO> cartList = (LinkedHashSet<CartVO>) session.getAttribute("cartList");
					Map<String, LinkedHashSet<CartVO>> map = new HashMap<String, LinkedHashSet<CartVO>>();

					for (CartVO cartVO : cartList) {
						/* 從購物車的編號推回商店編號 */
						String stono = productSvc.getOneProduct(cartVO.getProno()).getStono();
						/* 如果商店編號相同就放到同一個set內，否則新建一個 */
						if (map.containsKey(stono)) {
							LinkedHashSet<CartVO> set = map.get(stono);
							set.add(cartVO);
						} else {
							LinkedHashSet<CartVO> set = new LinkedHashSet<CartVO>();
							set.add(cartVO);
							map.put(stono, set);
						}
					}

					pageContext.setAttribute("key", map.keySet());
					session.setAttribute("map", map);
				%>

				<!-- 購物車中的商品的店家的集合 -->
				<c:forEach var="key" items="${key}">
					
					<%
						pageContext.setAttribute("list", map.get(pageContext.getAttribute("key")));
					%>

					<div class="panel panel-info">
						<div class="panel-heading">
							<i class="glyphicon glyphicon-shopping-cart"></i> 購物車
						</div>
						<table class="table">
							<thead>
								<tr>
									<th>店家名稱</th>
									<th>商品名稱</th>
									<th>商品價格</th>
									<th>購買數量</th>
									<th>小計</th>
									<th>刪除</th>
								</tr>
							</thead>
							
							<!-- 購物車中為key店家的商品 -->			
							<c:forEach var="cartVO" items="${list}"> 
								<tbody>
									<tr>
										<td>${storeSvc.getOneStore(productSvc.getOneProduct(cartVO.prono).stono).stoname}</td>
										<td>${productSvc.getOneProduct(cartVO.prono).proname}</td>
										<td>NT $
											${productSvc.getOneProduct(cartVO.prono).proprice} 元</td>
										<td>
											<form method="post" action="<%=request.getContextPath()%>/cart/cart.do">
											    <input type="number" name="newcount" value="${cartVO.procount}" min="1" max="${cartVO.procount+productSvc.getOneProduct(cartVO.prono).proqty}" style="width: 3em;">
											    <input type="submit" value="修改" class="btn btn-info ">
											    <input type="hidden" name="memno" value="${cartVO.memno}">
											    <input type="hidden" name="prono" value="${cartVO.prono}">
											    <input type="hidden" name="oldcount" value="${cartVO.procount}">
											    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
											    <input type="hidden" name="action" value="getOne_For_Update">
											</form>
										</td>
										<td>NT ${cartVO.procount * productSvc.getOneProduct(cartVO.prono).proprice}
											元</td>
										<td>
											<form method="post" action="<%=request.getContextPath()%>/cart/cart.do">
											    <button type="submit" value="刪除" class="glyphicon glyphicon-remove"></button>
											    <input type="hidden" name="memno" value="${cartVO.memno}">
											    <input type="hidden" name="prono" value="${cartVO.prono}">
											    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
											    <input type="hidden" name="action" value="delete_One_Product">
											</form>
										</td>
									</tr>
								</tbody>
							</c:forEach>
						</table>


						<!-- 若購物車是空的就不顯示 -->
						<c:if test="${not empty cartList}">
							<div class="panel-body">
								<div class=" text-right ">

									<c:if test="${memberVO.memno != '000000'}">
										<!-- 一般會員的結帳 -->
										<div class="ck">
											<form method="post" action="<%=request.getContextPath()%>/orders/orders.do">
											    <button type="submit" class="btn btn-info">結帳</button>
											    <input type="hidden" name="memno" value="${memberVO.memno}">
											    <input type="hidden" name="stono" value="${key}">
											    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
											    <input type="hidden" name="action" value="write_Orders_Detail">
											</form>
										</div>
									</c:if>

									<div class="ck">
										<c:if test="${memberVO.memno == '000000'}">
											<!-- 訪客的結帳 -->
											<form method="post" action="<%=request.getContextPath()%>/login.jsp">
											    <input type="submit" value="結帳" class="btn btn-default">
											    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
											</form>
										</c:if>
									</div>


									<div class="ck">
										<a
											href="<%=request.getContextPath()%>/front-end/cart/select_page.jsp">
											<button type="button" class="btn btn-default ">繼續購物</button>
										</a>
									</div>
								</div>
							</div>
						</c:if>
					</div>

				</c:forEach>

			</div>
			<!-- ==============================購物車結束============================== -->
			<!-- ===============================頁面結束=============================== -->
		</div>
		<!-- row over -->
	</div>
	<!-- container over -->
	<div class="col-xs-12 col-sm-12 footer ">Get a Board ©2016 All
		rights reserved.</div>
	<script src="https://code.jquery.com/jquery.js "></script>
	<script
		src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js "></script>
</body>