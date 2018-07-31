<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
	<c:redirect url="/"/>
</sec:authorize>
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>Timetable</title>
	<link rel="stylesheet" type="text/css" href="/styles/login.css">
	<%@ include file="csrf.jsp" %>
	<%@ include file="commonscript.jsp" %>
	<script src="/js/signup.js" type="text/javascript" charset="UTF-8"></script>
	
</head>
<body>
<h1> Sign-Up </h1>
<span id="exception"></span><br/>
<div id="registration">
	<label for="id">아이디:</label>
	<input id="id" type="text" name="${id}" /><br/>
	<label for="password">비밀번호:</label>
	<input id="password" type="password" name="${password}" /><br/>
	<label for="passwordValidation">비밀번호확인:</label>
	<input id="passwordValidation" type="password" name="${passwordValidation}" /><br/>
	<label for="email">E-mail 주소:</label>
	<input id="email" type="email" name="${email}" /><br/>
	<label for="description">소개:</label>
	<textarea id="description" name="${description}" style="width:180px; height:50px"></textarea><br/>
	
	<div>
		<input type="submit" id="btn"/>
	</div>
</div>
</body>
</html>