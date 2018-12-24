<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
	<%@ include file="/jspf/webSecurity.jspf" %>
	<script src="/js/jquery-3.3.1.min.js"></script>
	<script src="/js/bootstrap.js"></script>
	<link href="/css/bootstrap.css" rel="stylesheet">
	<link href="/css/signin.css" rel="stylesheet">
	
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<form class="form-signin" id="signinForm">
			<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
			<label for="inputEmail" class="sr-only">Email address</label>
			<input type="email" id="inputEmail" name="email" class="form-control" placeholder="Email address" required autofocus value="${email}">
			<label for="inputPassword" class="sr-only">Password</label>
			<input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required value="${password}">
			<div class="checkbox mb-3">
				<label>
					<input type="checkbox" value="remember-me">
					Remember me
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		</form>
	</body>
	
	<script type="text/javascript">
		$("#signinForm").submit(function(){
			console.log("=== 1.signingForm submit event start");
			
			secureProcess();
			
			return false;
		});
		
		function secureProcess(email, password) {
			var email = $("#inputEmail").val();
			var password = $("#inputPassword").val();
			var formData = {email:email, password:password};

			$.ajax({
			   url : "/json/encrypt",
			   type : "get",
			   contentType: "application/json",
			   success : function(data, textStatus, jqXHR) {
				   //var publicKey = eval('('+ jqXHR.responseText + ')');
				   var publicKey = data;
				   var encryptedPassword;
				   try {
					   encryptedPassword = WCrypto.rsa.encrypt(publicKey, password);
				   } catch(e) {
					   encryptedPassword = "";
				   }
				   console.log(encryptedPassword);
			       siginProcess(email, encryptedPassword);
			   },
			   error : function(jqXHR, textStatus, errorThrown) {
				   alert("Encryption Error.");
			   }
			   
			});
		}
		
		function siginProcess(email, password) {
			$.ajax({
				url : "/json/signin",
				type: "post",
				data: JSON.stringify({email: email, password: password}),
				contentType: "application/json",
				success : function(data, textStatus, jqXHR) {
					console.log("sigin data" + jqXHR.responseText);
					success(data);
				},
				error : function(jqXHR, textStatus, errorThronw) {
					alert("SignIn Error");
				}
			})
		}
		
		function success(jsonData) {
			if(jsonData.success == 'Y') {
				location.href = "/web/body";
			}
		}
	</script>
</html>