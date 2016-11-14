<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("BIG5");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product.controller.*" %>
<%@ page import="java.io.*"%>

<%
/* 	ProductService productSvc = new ProductService();
	List<ProductVO> list = productSvc.getAll(); */
	List<ProductVO> list = (List<ProductVO>)request.getAttribute("list");
	pageContext.setAttribute("list", list);	
%>


<jsp:useBean id="storeSvc" scope="page"
	class="com.store.model.StoreService"></jsp:useBean>
<jsp:useBean id="product_classSvc" scope="page"
	class="com.product_class.model.Product_classService"></jsp:useBean>

<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>product</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
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
    </style>
</head>

<body>
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-4"></div>
            <div class="col-xs-12 col-sm-4" style="text-align: center;"><img src="images/logo.png"></div>
            <div class="col-xs-12 col-sm-4">
                <div class="collapse navbar-collapse navbar-ex1-collapse">
                    <!-- 右選單 -->
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#">${memberVO.memname} 您好</a></li>
                        <li><a href="<%=request.getContextPath() %>/member/member.do?action=logout">登出</a></li>
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
                <button type="button" class="navbar-toggle aa" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">選單切換</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <!-- 手機隱藏選單區 -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <!-- 左選單 -->
                <ul class="nav navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/select_page.jsp">首頁</a></li>
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
                    <h3>商品類別</h3></div>
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
                    <h3>商品搜尋</h3></div>
                <div>
                    <form class="search ">
                        <input type="text" class="form-control" placeholder="請輸入關鍵字">
                        <button type="submit" class="btn btn-default">搜尋</button>
                    </form>
                </div>
            </div>
<!-- ===============================商品列表=============================== -->
            <div class="col-xs-12 col-sm-9 col-sm-offset-1">           
            <%-- <%@ include file="page1.file"%> --%>
            
            <div style="text-align:right;">
           		 <a href="<%=request.getContextPath()%>/product/product.do?action=getAllByDate">依新增時間排序</a> 
           		 <a href="<%=request.getContextPath()%>/product/product.do?action=getAllByName">依名稱排序</a> 
            </div>  
                      
<%--             <c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">  --%>            
            <c:forEach var="productVO" items="${list}">             
                    <a href="<%=request.getContextPath()%>/product/product.do?prono=${productVO.prono}&amp;action=getOne_For_Display" class="list-group-item">
                        <div class="row">                        
                            <div class="col-xs-12 col-sm-2">
                                <img alt="" src="<%=request.getContextPath()%>/product/ShowPicture?prono=${productVO.prono}" id="pic">
                            </div>
                            <div class="col-xs-12 col-sm-10">
                                <h4>${productVO.proname}</h4>
                                <h5>價格 : ${productVO.proprice} 元 數量 : ${productVO.proqty}</h5>
                            </div>
                        </div>
                    </a>
             </c:forEach>
             <%-- <%@ include file="page2.file"%>  --%>      
<!-- ===============================分頁開始=============================== -->
<!--             <div class="col-xs-12 col-sm-12 text-right">
                        <ul class="pagination">
                            <li><a href="#">&laquo;</a></li>
                            <li><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li class="active"><a href="#">3</a></li>
                            <li><a href="#">4</a></li>
                            <li><a href="#">5</a></li>
                            <li><a href="#">&raquo;</a></li>
                        </ul>
                    </div>
            </div> -->
<!-- ===============================頁面結束=============================== -->
        </div><!-- row over -->
    </div><!-- container over -->

 <div class="col-xs-12 col-sm-12 footer">Get a Board ©2016 All rights reserved.</div>
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
