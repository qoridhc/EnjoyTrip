<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="root" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Enjoy Trip!!</title>

<!-- Favicon-->	
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
<link href="css/myroute.css" rel="stylesheet" />
</head>

<body>
	
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

	<!-- Header-->

	<header class="py-1", style="background-color: #feeaef">
		<div class="container px-4 px-lg-5 my-4">
			<div class="text-center text-white">
				<h1 class="display-4 fw-bolder " style="color : white;">EnjoyTrip</h1>
				<p class="lead fw-normal mb-0" style="color : white;">With this shop
					homepage template</p>
			</div>
		</div>
	</header>

	<%-- <!-- 로그인 modal -->
	<div class="modal fade" id="loginModal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="login-content modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">로그인</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<!-- Modal body -->
				<form action="${root}/member" method="post">
					<input type="hidden" name="action" value="login">
					<div class="modal-body">
						<div class="input-group mb-3">
							<span class="input-group-text col-3" id="login-addon1">아이디</span>
							<input type="text" class="form-control" placeholder="아이디를 입력하세요."
								aria-label="Username" aria-describedby="basic-addon2" name="id" />
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text col-3" id="basic-addon4">비밀번호</span>
							<input type="password" class="form-control"
								placeholder="사용할 비밀번호를 입력하세요." aria-label="Username"
								aria-describedby="basic-addon4" name="password" />
						</div>
					</div>


					<!-- Modal footer -->
					<div class="modal-footer">
						<input type="submit" class="btn btn-primary" id="loginBtn"
							value="로그인" data-bs-dismiss="modal"></input>
					</div>
				</form>

			</div>
		</div>
	</div>

	<!-- 회원가입 modal -->
	<div class="modal fade" id="joinModal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="join-content modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">회원가입</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<!-- Modal body -->

				<form action="${root}/member" method="post">
					<input type="hidden" name="action" value="register">
					<div class="modal-body">
						<div class="input-group mb-3">
							<span class="input-group-text col-4" id="basic-addon1">이름</span>
							<input type="text" class="form-control" placeholder="이름을 입력하세요."
								aria-label="Username" aria-describedby="basic-addon1"
								name="userName" />
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text col-4" id="basic-addon2">아이디</span>
							<input type="text" class="form-control" placeholder="아이디를 입력하세요."
								aria-label="Username" aria-describedby="basic-addon2"
								name="userId" />
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text col-4" id="basic-addon3">이메일</span>
							<input type="email" class="form-control"
								placeholder="이메일을 입력하세요." aria-label="Username"
								aria-describedby="basic-addon3" name="userEmail" />
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text col-4" id="basic-addon4">비밀번호</span>
							<input type="password" class="form-control"
								placeholder="사용할 비밀번호를 입력하세요." aria-label="Username"
								aria-describedby="basic-addon4" name="userPwd" />
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text col-4" id="basic-addon5">비밀번호
								확인</span> <input type="password" class="form-control"
								placeholder="비밀번호를 다시 입력해주세요." aria-label="Username"
								aria-describedby="basic-addon5" name="pwdValidate" />
						</div>
						<div class="input-group">
							<span class="input-group-text col-4">자기소개</span>
							<textarea class="form-control" aria-label="With textarea"
								placeholder="간단한 자기소개를 입력해주세요." name="userInfo"></textarea>
						</div>
					</div>
					<!-- Modal footer -->
					<div class="modal-footer">
						<input type="submit" class="btn btn-primary" id="registerBtn"
							data-bs-dismiss="modal" value="가입하기"></input>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- 회원수정 modal -->
	<div class="modal fade" id="updateModal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="join-content modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">회원 수정</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<!-- Modal body -->
				<form action="${root}/member" method="post">
					<input type="hidden" name="action" value="modify">
					<div class="modal-body">
						<div class="input-group mb-3">
							<span class="input-group-text col-4" id="basic-addon1">이름</span>
							<input type="text" class="form-control" placeholder="이름을 입력하세요."
								aria-label="Username" aria-describedby="basic-addon1"
								name="userName" />
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text col-4" id="basic-addon2">아이디</span>
							<input type="text" class="form-control" value="${user.userId}"
								aria-label="Username" aria-describedby="basic-addon2"
								name="userId" readonly />
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text col-4" id="basic-addon3">이메일</span>
							<input type="email" class="form-control"
								placeholder="이메일을 입력하세요." aria-label="Username"
								aria-describedby="basic-addon3" name="userEmail" />
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text col-4" id="basic-addon4">비밀번호</span>
							<input type="password" class="form-control"
								placeholder="사용할 비밀번호를 입력하세요." aria-label="Username"
								aria-describedby="basic-addon4" name="userPwd" />
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text col-4" id="basic-addon5">비밀번호
								확인</span> <input type="password" class="form-control"
								placeholder="비밀번호를 다시 입력해주세요." aria-label="Username"
								aria-describedby="basic-addon5" name="pwdValidate" />
						</div>
						<div class="input-group">
							<span class="input-group-text col-4">자기소개</span>
							<textarea class="form-control" aria-label="With textarea"
								placeholder="간단한 자기소개를 입력해주세요." name="userInfo"></textarea>
						</div>
					</div>
					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary" id="updateBtn"
							data-bs-dismiss="modal">수정하기</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- 회원 탈퇴 modal -->
	<div class="modal fade" id="deleteModal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="delete-content modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">회원탈퇴</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<!-- Modal body -->
				<form action="${root}/member" method="post">
					<input type="hidden" name="action" value="delete">
					<div class="modal-body">
						<div class="input-group mb-3">
							<span class="input-group-text col-4" id="basic-addon4">비밀번호</span>
							<input type="password" class="form-control"
								placeholder="비밀번호를 입력하세요." aria-label="Username"
								aria-describedby="basic-addon4" name="password" />
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text col-4" id="basic-addon5">비밀번호
								확인</span> <input type="password" class="form-control"
								placeholder="비밀번호를 다시 입력해주세요." aria-label="Username"
								aria-describedby="basic-addon5" name="pwdValidate" />
						</div>
					</div>

					<!-- Modal footer -->
					<div class="modal-footer">
						<input type="submit" class="btn btn-primary" id="deleteBtn"
							value="탈퇴하기" data-bs-dismiss="modal"></input>
					</div> --%>
				</form>

			</div>
		</div>
	</div>