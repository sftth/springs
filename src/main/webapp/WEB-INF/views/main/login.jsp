<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script src="/js/jquery-3.3.1.js"></script>
<script src="/js/bootstrap.js"></script>
<link href="/css/bootstrap.css" rel="stylesheet">
<link href="/css/signin.css" rel="stylesheet">

<html>
	<head>
	
	</head>
	<body class="text-center">
		<div class="container">
			<form class="form-signin" action="/web/signin" method="post">
				<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
				<label for="inputEmail" class="sr-only">Email address</label>
				<input type="email" id="inputEmail" name="email" class="form-control" placeholder="Email address" required autofocus value="${email}"/>
				<label for="inputPassword" class="sr-only">Password</label>
				<input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required value="${password}">
					<div class="checkbox mb-3">
						<label for="rememberme">
							<input type="checkbox" id="rememberme" value="remember-me">
							Remember me
						</label>				
					</div>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
			</form>
		</div>
	</body>
</html>