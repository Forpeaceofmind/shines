<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="title" /></title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	font-family: Arial, sans-serif;
	background-image: linear-gradient(#FFFFFF, rgb(255, 122, 89));
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.container {
	background-color: #ffffff;
	padding: 100px;
	border-radius: 8px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
	width: 400px;
}

.form-group {
	margin-bottom: 5px;
}

label {
	display: block;
	font-weight: bold;
	margin-bottom: 10px;
}

input[type="text"], input[type="password"] {
	width: 100%;
	padding: 15px;
	border-radius: 4px;
	border: 1px solid #ccc;
	font-size: 16px;
}

button {
	padding: 15px 25px;
	background-color: #007BFF;
	color: #fff;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 32px;
}

button:hover {
	background-color: #0056b3;
}

.register-link {
	margin-top: 15px;
	text-align: center;
}

.error-message {
	color: #a94442;
}
</style>
</head>


<body>


	<div class="container">
		<form class="form-signin" action="Controller" method="post">
			<input type="hidden" name="command" value="do_auth" />
			<h2 class="form-signin-heading text-java text-center">
				<fmt:message key="login.title" />
			</h2>
			<div class="error-message" id="error-message">
				<c:if test="${not (param.authError eq null) }">
					<c:out value="${param.authError}" />
				</c:if>
			</div>
			<div class="form-group">
				<label for="inputEmail" class="sr-only"><fmt:message
						key="login.email" /></label> <input type="email" id="inputEmail"
					class="form-control" placeholder="<fmt:message key="login.email"/>"
					name="login" required autofocus> <label for="inputPassword"
					class="sr-only"><fmt:message key="login.password" /></label> <input
					type="password" id="inputPassword" class="form-control"
					placeholder="<fmt:message key="login.password"/>" name="password"
					required>
				<div class="checkbox mb-3">
					<label><input type="checkbox" value="remember-me">
						<fmt:message key="login.rememberMe" /></label>
				</div>
			</div>
			<button type="submit" class="btn btn-primary">
				<fmt:message key="login.submit" />
			</button>
		</form>
		<p>
			<fmt:message key="login.notRegistered" />
			<a href="Controller?command=go_to_registration_page"><fmt:message
					key="login.register" /></a>
		</p>
			<div class="language-selector">
		<span><fmt:message key="language" /></span> <a
			href="Controller?command=localizations&lang=en" class="language-link"><fmt:message
				key="language.EN" /></a> <a
			href="Controller?command=localizations&lang=ru" class="language-link"><fmt:message
				key="language.RU" /></a>
	</div>
		
	</div>
</body>
</html>