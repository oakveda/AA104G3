<!-- 從OrdersServlet轉送而來 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.cart.model.*"%>
<%@ page import="java.util.*"%>
<%
/* 	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
	CartService cartSvc = new CartService();
	List<CartVO> list = (List<CartVO>) cartSvc.getOneCart(memberVO.getMemno()); */
%>

<!DOCTYPE html>
<html lang="">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/check_info.js"></script>
<title>訂單資訊填寫</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<style type="text/css"></style>
<!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
</head>
<body>
	<c:if test="${not empty errorMsgs}">
		<ul>
			<c:forEach var="msg" items="${errorMsgs}">
				<li>${msg}</li>
			</c:forEach>
		</ul>
	</c:if>
	<br>


	<!-- ==============================頁面開始============================== -->
	<div class="container">
		<div class="row">
			<!-- ==============================填寫訂單資訊============================== -->
			<div class="col-xs-12 col-sm-12">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">填寫訂單資訊</h3>
					</div>
					<div class="panel-body">
						<b>請確認以下資訊</b>
					</div>
					<form method="post"
						action="orders.do">
						<table class="table">
							<tr>
								<td>收件人姓名 : <input type="text" name="memname" value="${memberVO.memname }"
									required>
								</td>
							</tr>
							<tr>
								<td>連絡電話 : <input type="text" name="memphone"
									value="${memberVO.memphone}" required>
								</td>
							</tr>
							<tr>
								<td>電子信箱 : <input type="text" name="mememail"
									value="${memberVO.mememail}" required>
								</td>
							</tr>
							<tr>
								<td>收件地址 : <input type="text" name="memaddr"
									value="${memberVO.memaddr }" size="50" required>
								</td>
							</tr>
							<tr>
								<td>付款方式 : <input type="radio" name="payment" value="1"
									checked id="remit"> 匯款 <input type="radio"
									name="payment" value="0" id="creait"> 信用卡
								</td>
							</tr>
							<tr>
								<td class="credit">
										卡號 : <select name="cardtype">
										<option value="VISA">VISA</option>
										<option value="Master Card">Master Card</option>
										<option value="JCB">JCB</option>
								</select>
								<input type="text" name="firstnum" size="1" maxlength="4" pattern="[0-9]{4}">- 
								<input type="text" name="secondnum" size="1" maxlength="4" pattern="[0-9]{4}">-
								<input type="text" name="thirdnum" size="1" maxlength="4" pattern="[0-9]{4}">- 
								<input type="text" name="forthnum" size="1" maxlength="4" pattern="[0-9]{4}"> 
								<br><br>
								有效日期 : <input type="month">
								<br><br>
								驗證碼(背後三碼) : <input type="text" name="idnt" size="1" maxlength="3" pattern="[0-9]{4}">
								</td>
							</tr>
							<tr>
								<td>運貨方式 : <input type="radio" name="pickup" value="0"
									checked> 郵寄 <input type="radio" name="pickup" value="1">
									快遞
								</td>
							</tr>
							<tr>
								<td>
									<center><input type="submit" value="確認送出"></center>
									<input type="hidden" name="action" value="check_Orders_Detail">
									<input type="hidden" name="memno" value="${memberVO.memno}">
									<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<!-- ==============================填寫訂單資料結束============================== -->
		</div>
		<!-- container over -->
	</div>
	<!-- row over -->
	<!-- ==============================頁面結束============================== -->
	<script
		src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>









