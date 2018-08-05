<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<sec:authorize access="isAnonymous()">
	<c:redirect url="/login"/>
</sec:authorize>
<script src="js/signout.js" type="text/javascript"></script>
<header class="w3-right">
	<span>
		<sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal.name"/>님 환영합니다</span>
			<sec:authorize access="hasAuthority('SUPER')">[슈퍼유저]</sec:authorize>
			<button class="w3-btn w3-round-xxlarge w3-black" id="logout">LOGOUT</button>
		</sec:authorize>
</header>
