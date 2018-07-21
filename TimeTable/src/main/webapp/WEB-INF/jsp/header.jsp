<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header class="w3-right">
	<form action="/login?logout" method="post">
		<span>
			<sec:authorize access="isAuthenticated()">
				<sec:authentication property="principal.username"/>님 환영합니다</span>
			</sec:authorize>
		<button class="w3-btn w3-round-xxlarge w3-black">LOGOUT</button>
	</form>
</header>
