<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
	<c:redirect url="/"/>
</sec:authorize>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Timetable</title>
	<link rel="stylesheet" type="text/css" href="/styles/login.css">
</head>
<body>
<h1> Please, log in </h1>

<div>
<c:if test="${param.error != null}">
	<p>
		id or password was invalid.
	</p>
</c:if>
<c:if test="${param.logout != null}">
	<p>
		로그아웃 되었습니다.
	</p>
</c:if>
	<form id="login" action="/login" method="post">
		<p>ID:</p>
		<input type="text" name="${id}" />
		<p>PASSWORD:</p>
		<input type="password" name="${password}" />
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<div id="login">
			<input type="submit" name="submit" value="login" />
		</div>
	</form>
	<a href="/signup"><input type="submit" name="submit" value="Signup" /></a>
	<a href="/oauth2/authorization/google"><button class="btn btn-danger">
		<svg class="social-icons social-icons__google social-icons--enabled" width="20" height="20" viewBox="0 0 20     20" xmlns="http://www.w3.org/2000/svg">
			<g fill="none" fill-rule="evenodd">
				<path d="M19.6 10.227c0-.709-.064-1.39-.182-2.045H10v3.868h5.382a4.6 4.6 0 0 1-1.996     3.018v2.51h3.232c1.891-1.742 2.982-4.305 2.982-7.35z" fill="#4285F4"></path>
				<path d="M10 20c2.7 0 4.964-.895 6.618-2.423l-3.232-2.509c-.895.6-2.04.955-3.386.955-2.605     0-4.81-1.76-5.595-4.123H1.064v2.59A9.996 9.996 0 0 0 10 20z" fill="#34A853"></path>
				<path d="M4.405 11.9c-.2-.6-.314-1.24-.314-1.9 0-.66.114-1.3.314-1.9V5.51H1.064A9.996 9.996     0 0 0 0 10c0 1.614.386 3.14 1.064 4.49l3.34-2.59z" fill="#FBBC05"></path>
				<path d="M10 3.977c1.468 0 2.786.505 3.823 1.496l2.868-2.868C14.959.99 12.695 0 10 0 6.09 0     2.71 2.24 1.064 5.51l3.34 2.59C5.192 5.736 7.396 3.977 10 3.977z" fill="#EA4335"></path>
			</g>
		</svg>
		<span class="social-buttons__service-name">Google 계정으로 로그인</span>
	</button></a>

	
</div>

</body>
</html>