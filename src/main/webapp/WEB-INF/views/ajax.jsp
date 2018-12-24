<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script type="text/javascript">
			//ajax 테스트 
			$(document).ready(function(){
				$('#loginIdCheck').click(function(){
					$.getJSON('checkLoginId/'+$('#loginId').val(), function(result){
						if(result.duplicated == true) {
							alert('ID is already registered.' + result.availableId + 'is available.');
						} else {
							alert('ID is available.');
						}
					});
				});
			});
			
		</script>
	</head>
	<body>
		<h1> Ajax </h1>
		<label>로그인 아이디 : </label>
		<input type="text" id="loginId" name="loginId" />
		<input type="button" id="loginIdCheck" value="아이디 중복검사" />
	</body>
</html>