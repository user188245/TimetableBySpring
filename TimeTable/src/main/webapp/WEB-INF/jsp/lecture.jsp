<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="csrf.jsp" %>
    <%@ include file="commonscript.jsp" %>
    <script src="js/scheduleform.js" type="text/javascript" charset="UTF-8"></script>
    <script src="js/lecturemanager.js" type="text/javascript" charset="UTF-8"></script>
    <link rel="stylesheet" type="text/css" href="/styles/lecture.css" />
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>Timetable</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	<%@ include file="nav.jsp" %>
<div id="lecture" class="w3-card-4">
    <h2>내 강의</h2>

    <div class="w3-container w3-deep-purple">
        <h3>수업리스트</h3>
    </div>

        <ul id="lecture_list" class="w3-ul w3-hoverable">
        </ul>
    <br>
    <input id="lecture_add" type="button" value="새 수업" class="w3-btn w3-deep-purple">
    <span id="testing"></span>

    <div id="lecture_popup">
        <div id="addpopup" class="w3-pale-green">
            <div id="addpopup_header" class="w3-deep-purple">HEADER</div>
            <fieldset>
                <legend>[ADD/MODIFY] LECTURE</legend>
                <div class="w3-container w3-cell">
                    <label for="lec_add_name">이름</label><input type="text" id="lec_add_name" class="w3-input w3-border" required><br>
                    <label for="lec_add_instructor">강사</label><input type="text" id="lec_add_instructor" class="w3-input w3-border" required><br>
                    <label for="lec_add_homepage">홈페이지</label><input type="text" id="lec_add_homepage" class="w3-input w3-border" value="http://" required><br>
                </div>
                <div class="w3-container w3-cell w3-pale-blue w3-panel w3-border w3-round-large">
                    <div class="w3-container">

                        <legend class="w3-blue"></legend>
                        시간<span class="w3-input w3-border"><input type="time" id="lec_add_timeStart" value="00:00" required> ~ <input type="time" id="lec_add_timeEnd" value="00:00" required></span><br>
                        장소<input type="text" id="lec_add_location" class="w3-input w3-border" required><br>
                        요일<select class="w3-input w3-border" id="lec_add_week" name="week">
                        <option value="0">월</option>
                        <option value="1">화</option>
                        <option value="2">수</option>
                        <option value="3">목</option>
                        <option value="4">금</option>
                        <option value="5">토</option>
                        <option value="6">일</option>
                    </select><br>
                        <button id="lec_add_timeButton" class="w3-btn w3-deep-purple">Add</button>
                    </div>
                    <div class="w3-container">
                        <ul id="lec_add_timeList" class="w3-ul w3-border">
                        </ul>
                    </div>
                </div>
                <br>
                <input type="button" class="w3-btn w3-deep-purple" id="lec_add_ok" value="OK">
                <input type="button" class="w3-btn w3-deep-purple" id="lec_add_cancel" value="CANCEL">
            </fieldset>
        </div>
    </div>
</div>
</body>
</html>
