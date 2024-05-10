<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content />
    <meta name="author" content />
    <title>EnjoyTrip 로그인</title>
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
          
          <div class="bg-light rounded-3 py-5 px-4 px-md-5 m-5">
            <div class="text-center mb-5">
              <div
                class="feature bg-gradient rounded-3 mb-3"
                style="font-size: 2rem; color: rgb(0, 0, 0)"
              >
                <i class="bi bi-person"></i>
              </div>
              <h1 class="fw-bolder">로그인</h1>
              <!-- <p class="lead fw-normal text-muted mb-0">We'd love to hear from you</p> -->
            </div>
            <div class="row gx-5 justify-content-center">
              <div class="col-lg-8 col-xl-6">
              
              
               <!-- Form -->
                <form id="signup" method="post" action="${root}/user/login">
                  <!--contactForm-->
                  <!-- <input type="hidden" name="action" value="login"> -->
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
                      아이디를 입력하세요.
                    </div>
                  </div>
                  <!-- PWD input -->
                  <div class="form-floating mb-3">
                    <input
                      class="form-control"
                      id="name"
                      name="pw"
                      type="password"
                      placeholder="Enter your name..."
                      data-sb-validations="required"
                    />
                    <label for="name">비밀번호</label>
                    <div class="invalid-feedback" data-sb-feedback="name:required">
                      비밀번호를 입력하세요
                    </div>
                  </div>

                  <div class="d-grid">
                    <input
                      class="btn btn-dark btn-lg"
                      id="submitButton"
                      type="submit"
                      name="action" value="login"
                      style="background-color: #e9859e"
                    >
                    
                    
                  </div>
                  <div class="d-flex justify-content-end mt-3">
                    <a class="link-dark small me-2" href="#!">아이디 찾기</a>
                    <a class="link-dark small" href="#!">비밀번호 찾기</a>
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
  </body>
</html>
    