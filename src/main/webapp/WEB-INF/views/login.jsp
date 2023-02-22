<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 로그인</title>
</head>
<body>
	<form action="loginOk">
	<h2>회원 로그인</h2>
	아이디 : <input type="text" name="mid"><br><br>
	비밀번호 : <input type="passwoard" name="mid"><br><br>
	<input type="submit" name="로그인">
	<input type="button" value="회원가입" onclick="javascript:window.location='join'">
	</form>
</body>
</html>