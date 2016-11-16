<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("BIG5");
	Team_reportService team_reportSvc = new Team_reportService();
	List<Team_reportVO> list = team_reportSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.team_report.model.*"%>


<jsp:useBean id="memberSvc" class="com.member.model.MemberService" />
<jsp:useBean id="teamSvc" class="com.team.model.TeamService" />

<!DOCTYPE html>
<html lang="">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Get a Board 後台管理系統</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link
	href="https://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="../css/main.css">
<!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
</head>

<style type="text/css">
.table-hover>tbody>tr:hover {
	background-color: #e0f7fa;
}
</style>

<body class="bBody">
	<!-- header==================================================================================== -->
	<nav class="navbar navbar-default" role="navigation">
		<div class="col-xs-12 col-sm-10">
			<h1 class="bHeader">Get a Board 後台管理系統</h1>
		</div>
		<div class="col-xs-12 col-sm-2">
			<ul class="nav navbar-nav navbar-right" style="margin-top: 10px;">
				<li><a href="#">系統管理員</a></li>
				<li><a href="#">登出</a></li>
			</ul>
		</div>
	</nav>
	<!-- menu==================================================================================== -->
	<div class="col-xs-12 col-sm-2"
		style="background-color: #3179b5; height: 800px; padding-top: 20px;">
		<div class="panel-group" id="accordion2" role="tablist"
			aria-multiselectable="true">
			<!-- 區塊1 -->
			<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="tab1">
					<h4 class="panel-title">
						<a class="collapsed" role="button" data-toggle="collapse"
							data-parent="#accordion2" href="#aaa" aria-expanded="false"
							aria-controls="aaa"> 員工資料管理<span
							class="glyphicon glyphicon-chevron-down" style="float: right"></span>
						</a>
					</h4>
				</div>
				<div id="aaa" class="panel-collapse collapse in" role="tabpanel"
					aria-labelledby="tab1">
					<div class="panel-body">
						<a href="employee.html">員工管理</a>
					</div>
				</div>
			</div>
			<!-- 區塊2 -->
			<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="tab2">
					<h4 class="panel-title">
						<a role="button" data-toggle="collapse" data-parent="#accordion2"
							href="#bbb" aria-expanded="true" aria-controls="bbb"> 網站管理<span
							class="glyphicon glyphicon-chevron-down" style="float: right"></span>
						</a>
					</h4>
				</div>
				<div id="bbb" class="panel-collapse collapse" role="tabpanel"
					aria-labelledby="tab2">
					<div class="panel-body">
						<a href="news.html">最新消息管理</a>
					</div>
					<div class="panel-body">
						<a href="ad.html">廣告管理</a>
					</div>
				</div>
			</div>
			<!-- 區塊3 -->
			<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="tab3">
					<h4 class="panel-title">
						<a class="collapsed" role="button" data-toggle="collapse"
							data-parent="#accordion2" href="#ccc" aria-expanded="false"
							aria-controls="ccc"> 店家管理<span
							class="glyphicon glyphicon-chevron-down" style="float: right"></span>
						</a>
					</h4>
				</div>
				<div id="ccc" class="panel-collapse collapse" role="tabpanel"
					aria-labelledby="tab3">
					<div class="panel-body">
						<a href="store.html">店家帳號管理</a>
					</div>
					<div class="panel-body">
						<a href="storeCheck.html">店家審核管理</a>
					</div>
				</div>
			</div>
			<!-- 區塊4 -->
			<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="tab2">
					<h4 class="panel-title">
						<a class="collapsed" role="button" data-toggle="collapse"
							data-parent="#accordion2" href="#ddd" aria-expanded="false"
							aria-controls="ddd"> 會員管理<span
							class="glyphicon glyphicon-chevron-down" style="float: right"></span>
						</a>
					</h4>
				</div>
				<div id="ddd" class="panel-collapse collapse" role="tabpanel"
					aria-labelledby="tab4">
					<div class="panel-body">
						<a href="member.html">會員管理</a>
					</div>
				</div>
			</div>
			<!-- 區塊5 -->
			<div class="panel panel-primary">
				<div class="panel-heading" role="tab" id="tab3">
					<h4 class="panel-title">
						<a class="collapsed" role="button" data-toggle="collapse"
							data-parent="#accordion2" href="#eee" aria-expanded="false"
							aria-controls="eee"> 檢舉管理<span
							class="glyphicon glyphicon-chevron-down" style="float: right"></span>
						</a>
					</h4>
				</div>
				<div id="eee" class="panel-collapse collapse" role="tabpanel"
					aria-labelledby="tab5">
					<div class="panel-body">
						<a href="reportTeam.html">商品檢舉管理</a>
					</div>
					<div class="panel-body">
						<a href="reportStore.html">店家檢舉管理</a>
					</div>
					<div class="panel-body">
						<a href="reportTeam.html">揪團檢舉管理</a>
					</div>
				</div>
			</div>
		</div>
		<!-- End of <div class="panel-group"> -->
	</div>
	<!-- End of <div class="col-xs-12 col-sm-3"> -->
	<!-- content=================================================================================== -->
	<div class="col-xs-12 col-sm-10 bList">
		<div class="bListTitle">揪團檢舉管理</div>
		<!-- Start of 主內容-->
		<div class="bListConent">
			<div>
				<table class="table table-hover table-bordered table-striped">
					<thead>
						<tr>
							<th style="width: 5%;">編號</th>
							<th style="width: 10%;">檢舉商品</th>
							<th style="width: 10%;">檢舉者</th>
							<th style="width: 10%;">檢舉日期</th>
							<th style="width: 30%;">檢舉原因</th>
							<th style="width: 15%;">審核</th>
						</tr>
					</thead>
					<tbody>
					<%@ include file="page1.file" %>
						<c:forEach var="team_reportVO" items="${list}">
							<tr>
								<td>${team_reportVO.teamrepno}</td>
								<td>${teamSvc.getOneTeam(team_reportVO.teamno).teamtitle}</td>
								<td>${memberSvc.getOneMember(team_reportVO.memno).memname}</td>
								<td>${team_reportVO.repdate}</td>
								<td>${team_reportVO.reason}</td>
								<td>
									<form method="post"	action="<%=request.getContextPath()%>/team_report/team_report.do">										
										<button type="submit" class="btn btn-success glyphicon glyphicon-ok">通過</button>
										<input type="hidden" name="teamrepno" value="${team_reportVO.teamrepno}">
										<input type="hidden" name="teamno" value="${team_reportVO.teamno}">
										<input type="hidden" name="checked" value="2">
										<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
										<input type="hidden" name="action" value="getOne_For_Update">																			
									</form>								
									<form method="post"	action="<%=request.getContextPath()%>/team_report/team_report.do">										
										<button type="submit" class="btn btn-danger glyphicon glyphicon-remove">否決</button>
										<input type="hidden" name="teamrepno" value="${team_reportVO.teamrepno}">
										<input type="hidden" name="teamno" value="${team_reportVO.teamno}">
										<input type="hidden" name="checked" value="1">
										<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
										<input type="hidden" name="action" value="getOne_For_Update">
									</form>									
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<%@ include file="page2.file" %>
			
			</div>
			<!-- End of <div>-->
		</div>
		<!-- End of <div class="bListConent">-->
		<!-- End of 主內容 -->
		<hr>
		<div class="bFooter text-center">Get a Board ©2016 All rights reserved.</div>
	</div>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>

</html>
