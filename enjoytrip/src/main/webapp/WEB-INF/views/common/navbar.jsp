<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>

<!-- Navigation-->
<nav class="navbar navbar-light navbar-expand-lg"
	style="background-color: #feeaef">
	<div class="container px-5">
		<a class="navbar-brand" href="${root}/">Enjoy Trip</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link" href="${root}/attraction">지역별여행지</a></li>
				<li class="nav-item"><a class="nav-link" href="${root}/article/list">여행정보공유</a></li>
				
				<c:if test="${empty user}">
					<li class="nav-item"><a class="nav-link" href="${root}/user/signup">회원가입</a></li>
					<li class="nav-item"><a class="nav-link" href="${root}/user/login">로그인</a></li>
				</c:if>
				
				<c:if test="${not empty user}">
					<li class="nav-item"><a class="nav-link" href="${root}/attractoin/myroute">나의여행계획</a></li>
					<li class="nav-item"><a class="nav-link" href="${root}/attraction/myplace">핫플자랑하기</a></li>
					<li class="nav-item"><a class="nav-link" href="${root}/user/profile">마이페이지</a></li>
					<li class="nav-item"><a class="nav-link" href="${root}/user/logout">로그아웃</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>