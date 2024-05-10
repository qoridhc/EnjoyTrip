<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content />
    <meta name="author" content />
    <title>EnjoyTrip 회원가입</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Bootstrap icons-->
    <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" /> -->
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"
    />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="${root}/assets/css/styles.css" rel="stylesheet" />
  </head>
  <body class="d-flex flex-column">
    <main class="flex-shrink-0">
      <!-- Navigation-->
      <%@ include file="/WEB-INF/views/common/navbar.jsp" %>
      <!-- Page content-->
      <section class="py-5">
        <div class="container px-5">
          <!-- Contact form-->
          <div class="bg-light rounded-3 py-5 px-4 px-md-5 mb-5">
            <div class="text-center mb-5">
              <div
                class="feature bg-gradient rounded-3 mb-3"
                style="font-size: 2rem; color: rgb(0, 0, 0)"
              >
                <i class="bi bi-person-add"></i>
              </div>
              <h1 class="fw-bolder">회원가입</h1>
              <!-- <p class="lead fw-normal text-muted mb-0">We'd love to hear from you</p> -->
            </div>
            <div class="row gx-5 justify-content-center">
              <div class="col-lg-8 col-xl-6">
                <form id="signupForm" method="post" action="${root}/user/signup">
                  <!-- <input type="hidden" name="action" value="signup"> -->
                  <!--contactForm-->
                  
                  <!-- Name input-->
                  <div class="form-floating mb-3">
                    <input
                      class="form-control"
                      id="name"
                      name="name"
                      type="text"
                      placeholder="Enter your name..."
                      data-sb-validations="required"
                    />
                    <label for="name">이름</label>
                    <div class="invalid-feedback" data-sb-feedback="name:required">
                      A name is required.
                    </div>
                  </div>
                  
                  <!-- ID input -->
                  <div class="form-floating mb-3">
                    <input
                      class="form-control"
                      id="name"
                      name="id"
                      type="text"
                      placeholder="Enter your name..."
                      data-sb-validations="required"
                    />
                    <label for="name">아이디</label>
                    <div class="invalid-feedback" data-sb-feedback="name:required">
                      A name is required.
                    </div>
                  </div>
                  
                  <!-- PWD input -->
                  <div class="form-floating mb-3">
                    <input
                      class="form-control"
                      id="pwd"
                      name="pwd"
                      type="password"
                      placeholder="Enter your name..."
                      data-sb-validations="required"
                    />
                    <label for="name">비밀번호</label>
                    <div class="invalid-feedback" data-sb-feedback="name:required">
                      A name is required.
                    </div>
                  </div>
                  
                  <!-- PWD recheck input -->
                  <div class="form-floating mb-3">
                    <input
                      class="form-control"
                      id="pwdCheck"
                      name="pwdcheck"
                      type="password"
                      placeholder="Enter your name..."
                      data-sb-validations="required"
                    />
                    <label for="name">비밀번호 확인</label>
                    <div class="invalid-feedback" data-sb-feedback="name:required">
                      A name is required.
                    </div>
                  </div>
                  
                <!--   
                  Phone number input
                  <div class="form-floating mb-3">
                    <input
                      class="form-control"
                      id="phone"
                      name="phone"
                      type="tel"
                      placeholder="(123) 456-7890"
                      data-sb-validations="required"
                    />
                    <label for="phone">전화번호</label>
                    <div class="invalid-feedback" data-sb-feedback="phone:required">
                      A phone number is required.
                    </div>
                  </div>
                   -->
                   
                  <!-- Email address input-->
                  <div class="form-floating mb-3">
                    <input
                      class="form-control"
                      id="email"
                      name="email"
                      type="email"
                      placeholder="name@example.com"
                      data-sb-validations="required,email"
                    />
                    <label for="email">이메일</label>
                    <div class="invalid-feedback" data-sb-feedback="email:required">
                      An email is required.
                    </div>
                    <div class="invalid-feedback" data-sb-feedback="email:email">
                      Email is not valid.
                    </div>
                  </div>


                  <!-- Submit Button-->
                  <div class="d-grid">
                    <input class="btn btn-lg border text-white" id="submitButton" type="submit" style="background-color: #e9859e;">
                  </div>
                  
                  
                </form>
              </div>
            </div>
          </div>
        </div>
      </section>
    </main>
    <!-- Footer-->
    <%@include file="/WEB-INF/views/common/footer.jsp" %>
    
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="js/scripts.js"></script>
    <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
    <!-- * *                               SB Forms JS                               * *-->
    <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
    <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
    <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    <script>
    document.getElementById("signupForm").addEventListener("submit", function(event) {
        var password = document.getElementById("pwd").value;
        var confirmPassword = document.getElementById("pwdCheck").value;
        if (password != confirmPassword) {
            alert("비밀번호가 일치하지 않습니다.");
            event.preventDefault(); // 폼 제출 방지
        }
    });
    
	let msg = "${msg}";
	
	if (msg) {
		alert(msg)
	}
    
    </script>
  </body>
</html>
    