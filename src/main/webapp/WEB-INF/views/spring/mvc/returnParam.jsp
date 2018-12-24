<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="userModel" name="userModel" action="/web/return1" method="post">
		<label>Name: </label>
		<input type="text" id="name" name="name">
		<label>PassWord: </label>
		<input type="text" id="password" name="password">
		<input type="submit" value="확인">
	</form>
	
	<a href="/web/return2?lang=ko"> Korean</a>
	<a href="/web/return2?lang=en"> English</a>
	<a href="/web/return2?lang=ja"> Japan</a>
</body>
</html>