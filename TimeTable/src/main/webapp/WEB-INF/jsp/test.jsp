<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Timetable</title>
</head>
<body>
	<h2>Hello JSP</h2>
	<h3>your id : <sec:authentication property="principal.username"/></h3>
</body>
</html>
