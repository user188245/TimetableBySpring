<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<title>Timetable</title>
	<link rel="stylesheet" type="text/css" href="/styles/login.css">
	<%@ include file="csrf.jsp" %>
	<%@ include file="commonscript.jsp" %>
	<script src="/js/signup_social.js" type="text/javascript" charset="UTF-8"></script>
	<script src="/js/signout.js" type="text/javascript" charset="UTF-8"></script>
	
</head>
<body>
<h3><c:out value="${name}"/>님 환영합니다.</h3>
<h3> 사이트에서 사용할 당신의 ID를 적으세요. </h3>
<span id="exception"></span><br/>
<div id="registration">
	<label for="id">아이디:</label>
	<input id="id" type="text" name="${id}" /><br/>
	<div>
		<input type="submit" id="btn"/>
	</div>
</div>
<a href="#" id="logout">돌아가기</a>

</body>
</html>