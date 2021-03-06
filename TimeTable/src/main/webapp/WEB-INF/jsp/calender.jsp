<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Timetable</title>
	<%@ include file="csrf.jsp" %>
	<%@ include file="commonscript.jsp" %>
	<link rel="stylesheet" type="text/css" href="styles/calender.css">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<script src="/js/scheduleform.js" type="text/javascript" charset="UTF-8"></script>
	<script src="/js/calendar.js" type="text/javascript" charset="UTF-8"></script>
</head>
<body>
	<%@ include file="header.jsp" %>
	<%@ include file="nav.jsp" %>
	<div class="w3-row">
		<div class="w3-col m8">
			<div id="calender" class="w3-margin">
				<div class="month">
				  <ul>
					<li class="prev">&#10094;</li>
					<li class="next">&#10095;</li>
					<li>
					  <span id="month"></span><br>
					  <span id="year"></span>
					</li>
				  </ul>
				</div>

				<ul class="weekdays">
				  <li>Mo</li>
				  <li>Tu</li>
				  <li>We</li>
				  <li>Th</li>
				  <li>Fr</li>
				  <li>Sa</li>
				  <li>Su</li>
				</ul>

				<ul class="days">
				  <li></li>
				</ul>
			</div>
		</div>
		<div class="w3-col m4">
			<div id="schedule" class="w3-margin w3-card-4">
				<div class="w3-container w3-deep-purple">
					<h2>스케쥴 목록</h2>
				</div>
				<div id="s_list_outer">
					<div>
						<ul id="s_list" class="w3-ul w3-hoverable">
						</ul>
					</div>
					<br>
					<input id="s_add" type="button" value="[임시추가]" class="w3-button w3-deep-purple">
					<span id="testing"></span>

					<div id="s_popup" class="w3-container w3-cell-middle">
						<div id="addpopup" class="w3-panel w3-white w3-card-4">
							<h3>스케쥴</h3>
							<label class="w3-text-indigo" for="s_add_name">이름 : </label><input type="text" id="s_add_name" class="w3-input w3-border" required><br>
							<label class="w3-text-indigo" for="s_add_timeStart">시간 : </label><span class="w3-input w3-border"><input type="time" id="s_add_timeStart" value="00:00" required> ~ <input type="time" id="s_add_timeEnd" value="00:00" required></span><br>
							<label class="w3-text-indigo" for="s_add_location">장소 : </label><input type="text" id="s_add_location" class="w3-input w3-border" required><br>
							<label class="w3-text-indigo" for="s_add_text">메모 : </label><textarea id="s_add_text" class="w3-input w3-border" rows="4" cols="20"></textarea><br>
							<input type="button" class="w3-btn w3-deep-purple" id="s_add_ok" value="OK">
							<input type="button" class="w3-btn w3-deep-purple" id="s_add_cancel" value="CANCEL">
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>