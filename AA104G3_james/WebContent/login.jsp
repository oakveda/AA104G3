<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ page import="com.member.model.*"%>
<%
	MemberService memberSvc = new MemberService();
	MemberVO memberVO = memberSvc.getOneMember("000001");
	session.setAttribute("memberVO", memberVO);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>登入測試</title>
</head>

<body>
	<div class="container" style="padding-top: 200px; width: 400px">
		<div class="panel panel-info">
			<div class="panel-heading">
				<h3 class="panel-title">請輸入帳號密碼</h3>
			</div>
			<div class="panel-body">
				<form>
					<div class="form-group">
						<label for="email">Email:</label> <input type="email"
							class="form-control" id="email" placeholder="Enter email">
					</div>
					<div class="form-group">
						<label for="pwd">Password:</label> <input type="password"
							class="form-control" id="pwd" placeholder="Enter password">
					</div>
					<center>
						<button type="submit" class="btn btn-default">Submit</button>
					</center>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
