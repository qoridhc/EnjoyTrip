<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content />
<meta name="author" content />
<title>EnjoyTrip 회원정보</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Bootstrap icons-->
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" /> -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="${root}/assets/css/styles.css" rel="stylesheet" />
</head>
<body class="d-flex flex-column">
	<main class="flex-shrink-0">
		<!-- Navigation-->
		<%@ include file="/WEB-INF/views/common/navbar.jsp"%>

		<!-- Page content-->
		<section class="py-5">
			<div class="container px-5">
				<!-- Contact form-->
				<div class="bg-light rounded-3 py-5 px-4 px-md-5 mb-5">
					<div class="text-center mb-5">
						<div class="feature bg-gradient rounded-3 mb-3 mt-2"
							style="font-size: 5rem; color: rgb(0, 0, 0)">
							<i class="bi bi-person"></i>
						</div>
					</div>
					<div class="row gx-5 justify-content-start">
						<h2 class="fw-bolder mb-3">회원정보</h2>
						<hr />
						<form action="${root}/user/modify" method="post">

							<!-- <input type="hidden" name="action" value="delete"> -->
							<input type="hidden" name="action" value="modify">

							<!--contactForm-->

							<!-- Name-->
							<div class="col-auto mb-3">
								<label for="name">이름</label> 
								<label for="name">${loginUser.name }</label> 
								<input type="hidden" name="name"
									value="${loginUser.name }" />
								<!-- 수정 -->

							</div>
							<!-- ID -->
							<div class="col-auto mb-3">
								<label for="id">아이디</label> 
								<label for="id">${loginUser.id }</label>
								<input type="hidden" name="id" value="${loginUser.id }" />
								<!-- 수정 -->
							</div>
							<!-- PWD input -->
							<div class="col-auto mb-3">
								<label for="pwd">비밀번호</label> 
								<input id="name" type="password" "
									name="pw" placeholder="수정할 비밀번호 입력"
									data-sb-validations="required" />
							</div>
							<!-- PWD recheck input -->
							<div class="col-auto mb-3">
								<label for="pwd-recheck">비밀번호 확인</label> <input class=""
									id="name" type="password" placeholder="수정할 비밀번호 재확인"
									data-sb-validations="required" />
							</div>

							<!-- Email address input-->
							<div class="col-auto mb-3">
								<label for="email">이메일</label>
								<label for="email">
								${loginUser.email_1 }@${loginUser.email_2}</label>
								<input type="hidden" name="email"
									value= "${loginUser.email_1 }@${loginUser.email_2}" />
								<!-- 수정 -->
								<%-- email도 수정가능하게
                  <input
                    class=""
                    id="email"
                    type="email"
                    name="email"
                    placeholder="${user.email_1 }@${user.email_2}"
                    value="${user.email_1 }@${user.email_2}"
                    data-sb-validations="required,email"
                  />
 					--%>
								<div class="invalid-feedback" data-sb-feedback="email:required">
									An email is required.</div>
								<div class="invalid-feedback" data-sb-feedback="email:email">
									Email is not valid.</div>
							</div>


							<div class="d-flex justify-content-end">
								<!-- 수정 Button-->
								<div class="d-grid">
									<input class="btn btn-primary btn-md me-2" id="submitButton"
										type="submit" value="수정">
								</div>

								<!-- 탈퇴 Button-->
								<div>
									<a href="${root}/user/delete/${loginUser.id}">
										<button class="btn btn-danger btn-md me-2" id="submitButton"
											value="삭제" type="button">삭제</button>
									</a>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</section>
	</main>
	<!-- Footer-->
	<%@include file="/WEB-INF/views/common/footer.jsp"%>

	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- * *                               SB Forms JS                               * *-->
	<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
</body>
</html>
