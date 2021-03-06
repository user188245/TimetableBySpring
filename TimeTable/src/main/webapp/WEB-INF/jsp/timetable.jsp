<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<%@ include file="csrf.jsp" %>
	<link rel="stylesheet" type="text/css" href="styles/index.css" />
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<%@ include file="commonscript.jsp" %>
	<script src="/js/scheduleform.js" type="text/javascript" charset="UTF-8"></script>
	<script src="/js/weeklytimetable.js" type="text/javascript" charset="UTF-8"></script>
	<title>Timetable</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	<%@ include file="nav.jsp" %>
	<div class="w3-container">
		<aside>
			날짜 변경<input type="date" id="datepicker">
		</aside>

		<article id="article">
			<div class="weeklyTimeTable" width="2000" height="1200"></div>
		</article>
	</div>
	<footer>
		<div id="lec_info" class="popup w3-card-4 w3-display-middle">
			<div class="w3-container w3-deep-purple">
				<h2>COURSE INFO</h2>
			</div>
			<div id="lec_inner">
					<span class="w3-col l12 w3-panel w3-border">COURSE NAME:<span id="lec_info_name"></span></span>
					<span class="w3-col l12 w3-panel w3-border">INSTRUCTOR:<span id="lec_info_instructor"></span></span>
					<span class="w3-col l12 w3-panel w3-border">HOMEPAGE:<span id="lec_info_homepage"></span></span>
					<span class="w3-col l12 w3-panel w3-border">SELECTED_LESSON_PERIOD:<span id="lec_info_period"></span></span>
					<span class="w3-col l12 w3-panel w3-border">SELECTED_LESSON_LOCATION:<span id="lec_info_location"></span></span>
					IS_CANCELED:<input type="checkbox" id="lec_info_invalid" class="w3-check"><br>
					<input type="button" id="lec_info_hide" value="HIDE" class="w3-button w3-deep-purple">
			</div>
		</div>
		<div id="plan_info" class="popup w3-card-4 w3-display-middle">
			<div class="w3-container w3-deep-purple">
				<h2>PLAN INFO</h2>
			</div>
			<div id="plan_inner">
				<span class="w3-col l12 w3-panel w3-border">PLAN NAME:<span id="plan_info_name"></span></span><br>
				<span class="w3-col l12 w3-panel w3-border">PLAN_DATE:<span id="plan_info_date"></span></span><br>
				<span class="w3-col l12 w3-panel w3-border">PLAN_PERIOD:<span id="plan_info_period"></span></span><br>
				<span class="w3-col l12 w3-panel w3-border">PLAN_LOCATION:<span id="plan_info_location"></span></span><br>
				<input type="button" id="plan_info_hide" value="HIDE" class="w3-button w3-deep-purple">
			</div>
		</div>
	</footer>
</body>
</html>