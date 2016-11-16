<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%
	request.setCharacterEncoding("BIG5");
%> --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_comment.model.*"%>

<%
	ProductService productSvc = new ProductService();
	Product_commentService product_commentSvc = new Product_commentService();
	List<Product_commentVO> product_commentList = product_commentSvc.getAll(request.getParameter("prono"));
	pageContext.setAttribute("product_commentList", product_commentList);
%>



<jsp:useBean id="product_classSvc"
	class="com.product_class.model.Product_classService" />
<jsp:useBean id="memberSvc"
	class="com.member.model.MemberService" />

<!DOCTYPE html>
<html lang="">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>product</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
<link rel="stylesheet" href="css/main.css">
<style type="text/css">
#pic {
	width: 100%;
	border: 1px solid #ccc;
}

.navbar {
	margin-bottom: 30px;
}

textarea {
	min-width: 100%
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
			<!-- ===============================商品分類=============================== -->
			<div class="col-xs-12 col-sm-2">
				<div class="proCategory">
					<h3>商品類別</h3>
				</div>
				<div style="margin-bottom: 250px;">
					<ul class="categoryList">
						<li>派對遊戲</li>
						<li>策略遊戲</li>
						<li>戰爭遊戲</li>
						<li>家庭遊戲</li>
						<li>主題遊戲</li>
					</ul>
				</div>
				<div class="proCategory">
					<h3>商品搜尋</h3>
				</div>
				<div>
					<form class="search ">
						<input type="text" class="form-control" placeholder="請輸入關鍵字">
						<button type="submit" class="btn btn-default">搜尋</button>
					</form>
				</div>
			</div>
			<!-- ===============================商品頁面=============================== -->
			<div class="col-xs-12 col-sm-9 col-sm-offset-1">
				<h3>${productVO.proname}</h3>
			</div>
			<div class="col-xs-12 col-sm-9  col-sm-offset-1">
				<div class="col-xs-12 col-sm-6 text-center">
					<img alt=""
						src="<%=request.getContextPath()%>/product/ShowPicture?prono=${productVO.prono}"
						id="pic" width="250" height="300"> <br> <br> <a
						href="#" class="btn btn-danger" role="button">立即購買</a> <a href="#"
						class="btn btn-default" role="button">加入購物車</a> <a
						href='#modal-id' data-toggle="modal"><span>檢舉</span></a>
				</div>
				<div class="col-xs-12 col-sm-6">
					<ul>
						<br>
						<li>價格 : $${productVO.proprice}</li>
						<br>
						<li>庫存 : ${productVO.proqty}</li>
						<br>
						<li>類別 :
							${product_classSvc.getOneProduct_class(productVO.classno).classname}</li>
						<br>
						<li>商品總評價 : <span class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span> <span
							class="glyphicon glyphicon-star"></span>
						</li>
						<br>
						<li>簡介 : ${productVO.prodesc}</li>
					</ul>
				</div>
			</div>
			<!-- ===============================評論頁面=============================== -->
			<br>
			<div class="col-xs-12 col-sm-12" style="padding-top: 15px">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">會員評論</h3>
					</div>
					<div class="panel-body">
						<ul class="list-group">
							<c:forEach var="product_commnetVO"
								items="${product_commentList}">
								<li class="list-group-item">
									<div class="row">
										<div class="col-xs-12 col-sm-2">
											<a href="#"><img src="https://goo.gl/qXgCZi" id="pic"></a>
										</div>
										<div class="col-xs-12 col-sm-10">

											<span>分數 : ${product_commnetVO.comscore}</span>
											
											<c:set var="memno" value="${product_commnetVO.memno}"/>
											<%String memno = (String)pageContext.getAttribute("memno"); %>
											<h4><%=memberSvc.getOneMember(memno).getMemname() %>:
												
												${product_commnetVO.comdetail}</h4>
											
										</div>
									</div>
								</li>
							</c:forEach>

						</ul>
					</div>
				</div>
				<!-- ===============================商品評論=============================== -->
				<!--                 <div class="col-xs-12 col-sm-12">
                    <div class="row">
                        <div class="form-group">
                            <label for="comment">
                                <h3>評論 :</h3> </label>
                            <textarea class="form-control" rows="5" id="comment"></textarea>
                            <br>
                            <div class=" text-center ">
                                <button type="button " class="btn btn-default ">送出</button>
                            </div>
                        </div>
                    </div> -->
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