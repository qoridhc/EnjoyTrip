<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String root = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>EnjoyTrip</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Bootstrap icons-->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
      rel="stylesheet"
    />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="<%=root%>/assets/css/styles.css" rel="stylesheet" />
  </head>

  <body class="d-flex flex-column h-100">
    <main class="flex-shrink-0">
      <!-- Navigation-->
      <%@ include file="/WEB-INF/views/common/navbar.jsp" %>
      

      <!-- Header-->
      <header class="py-5 navbar-light" style="background-color: #feeaef">
        <div class="container px-5 py-5">
          <div class="row gx-5 py-3 align-items-center justify-content-center">
            <div class="col-lg-8 col-xl-7 col-xxl-6">
              <div class="my-5 text-center text-xl-start">
                <h1 class="display-5 fw-bolder mb-2">Enjoy Trip</h1>
                <p class="lead fw-normal fw-bold text-black-50 mb-4">
                  여행을 긍정적으로, 시간을 효율적으로
                </p>
                <div
                  class="d-grid gap-3 d-sm-flex justify-content-sm-center justify-content-xl-start"
                >
                  <a class="btn btn-outline-dark btn-lg px-4 me-sm-3" href="tourSpotFinder.html">시작하기</a>
                  <a class="btn btn-outline-secondary btn-lg px-4" href="#features">알아보기</a>
                </div>
              </div>
            </div>
            <div class="col-xl-5 col-xxl-6 d-none d-xl-block text-center">
              <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner">
                  <div class="carousel-item active">
                    <img
                      class="d-block w-100"
                      src="assets/img-main/carousel1.jpg"
                      alt="First slide"
                    />
                  </div>
                  <div class="carousel-item">
                    <img
                      class="d-block w-100"
                      src="assets/img-main/carousel2.jpg"
                      alt="First slide"
                    />
                  </div>
                  <div class="carousel-item">
                    <img
                      class="d-block w-100"
                      src="assets/img-main/carousel3.jpg"
                      alt="First slide"
                    />
                  </div>
                  <div class="carousel-item">
                    <img
                      class="d-block w-100"
                      src="assets/img-main/carousel4.jpg"
                      alt="First slide"
                    />
                  </div>
                </div>
                <button
                  class="carousel-control-prev"
                  type="button"
                  data-bs-target="#carouselExampleControls"
                  data-bs-slide="prev"
                >
                  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                  <!-- <span class="visually-hidden"></span> -->
                </button>
                <button
                  class="carousel-control-next"
                  type="button"
                  data-bs-target="#carouselExampleControls"
                  data-bs-slide="next"
                >
                  <span class="carousel-control-next-icon" aria-hidden="true"></span>
                  <!-- <span class="visually-hidden"></span> -->
                </button>
              </div>
            </div>
          </div>
        </div>
      </header>
      <!-- Features section-->
      <section class="py-5" id="features">
        <div class="container px-5 my-5">
          <div class="row gx-5">
            <div class="col-lg-12">
              <div class="row gx-5 row-cols-1 row-cols-md-4" style="margin: 0 auto">
                <div class="col mb-5 h-100">
                  <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3">
                    <i class="bi bi-collection"></i>
                  </div>
                  <h2 class="h5">지역별 여행지</h2>
                  <p class="mb-0">시,도,구,군 별로 관광지 정보 조회 컨텐츠별로 모아보기</p>
                </div>
                <div class="col mb-5 h-100">
                  <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3">
                    <i class="bi bi-building"></i>
                  </div>
                  <h2 class="h5">나만의 여행 계획</h2>
                  <p class="mb-0">
                    여행 경로, 숙박, 관광지, 예상금액 등 나만의 멋진<br />계획을 세우기 사람들과
                    공유하기
                  </p>
                </div>
                <div class="col mb-5 mb-md-0 h-100">
                  <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3">
                    <i class="bi bi-toggles2"></i>
                  </div>
                  <h2 class="h5">핫플 공유하기</h2>
                  <p class="mb-0">나만의 핫플을 사람들과 공유하기</p>
                </div>
                <div class="col h-100">
                  <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3">
                    <i class="bi bi-toggles2"></i>
                  </div>
                  <h2 class="h5">여행 정보 공유</h2>
                  <p class="mb-0">여행지에 대한 소소한 꿀팁을 공유해보아요</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>

      <!-- Testimonial section-->

      <div class="container"></div>
      <div class="py-5" style="background-color: #f3e4e2">
        <div class="row gx-5 pb-3 justify-content-center">
          <div class="col-lg-12 col-xl-10">
            <div class="text-center">
              <h2 class="fw-bolder">지역별 관광지 정보</h2>
              <h4 class="mt-5">원하는 테마별로 얻는 관광지 정보</h4>
            </div>
            <div class="d-flex justify-content-center">
              <img class="mt-5" style="width: 40%" src="assets/img-main/info1.png" alt="" />
            </div>
          </div>
        </div>
      </div>

      <!-- 나의 여행 계획 -->
      <div class="py-5" style="background-color: #fbf3ea; height:auto">
        <div class="container p-5 my-5">
          <div class="row gx-5 justify-content-center">
            <div class="col-lg-4 col-xl-4">
              <div class="py-5">
                <h2 class="fw-bolder pt-5 mt-5">나의 여행 계획</h2>
                <h4 class="mt-5">여행 루트를 만들고</h4>
                <h4 class="mt-5">최적의 경로로 이동해요</h4>
              </div>
            </div>

            <div class="col-lg-8 col-xl-6 py-5">
              <img class="" style="width: 110%" src="assets/img-main/mytrip.png" alt="" />
            </div>
          </div>
        </div>
      </div>

      <!-- 핫플 자랑하기 -->
      <section class="pt-5 pb-3" style="background-color: #f7fbea">
        <div class="container px-5 my-5">
          <div class="row gx-5 justify-content-center">
            <div class="col-lg-8 col-xl-6">
              <div class="text-center">
                <h2 class="fw-bolder">핫플 자랑하기</h2>
                <p class="lead fw-normal text-muted mb-5 mt-4">
                  요즘 뜨는 🔥핫🔥플레이스를 알아가요
                </p>
              </div>
            </div>
          </div>
          <div class="row gx-5">
            <div class="col-lg-4 mb-5">
              <div class="card shadow border-0" style="height: 80%">
                <img
                  class="card-img-top"
                  style="height: 45%"
                  src="assets/img-main/blog1.png"
                  alt="..."
                />
                <div class="card-body p-4">
                  <div class="badge bg-primary bg-gradient rounded-pill mb-2">Sights</div>
                  <a class="text-decoration-none link-dark stretched-link" href="#!">
                    <h5 class="card-title mb-3 fw-bold">연인과 데이트 코스 추천</h5>
                  </a>
                  <p class="card-text mb-0">
                    연인과 로맨틱한 시간을 보내시고 싶으신가요? 남산타워의 야경을 즐겨보세요.
                     
                  </p>
                </div>
                <div class="card-footer p-4 pt-0 bg-transparent border-top-0">
                  <div class="d-flex align-items-end justify-content-between">
                    <div class="d-flex align-items-center">
                      <img
                        class="rounded-circle me-3"
                        src="https://dummyimage.com/40x40/ced4da/6c757d"
                        alt="..."
                      />
                      <div class="small">
                        <div class="fw-bold">김싸피</div>
                        <div class="text-muted">March 23, 2023 &middot; 4 min read</div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-4 mb-5">
              <div class="card shadow border-0" style="height: 80%">
                <img
                  class="card-img-top"
                  style="height: 45%"
                  src="assets/img-main/blog2.png"
                  alt="..."
                />
                <div class="card-body p-4">
                  <div class="badge bg-primary bg-gradient rounded-pill mb-2">Restaurant</div>
                  <a class="text-decoration-none link-dark stretched-link" href="#!">
                    <h5 class="card-title mb-3 fw-bold">수플레 맛집</h5>
                  </a>
                  <p class="card-text mb-0">
                    프랑스의 전통 간식 수플레 맛집을 알려드립니다. 
                    프랑스보다 더 맛있어요!
                  </p>
                </div>
                <div class="card-footer p-4 pt-0 bg-transparent border-top-0">
                  <div class="d-flex align-items-end justify-content-between">
                    <div class="d-flex align-items-center">
                      <img
                        class="rounded-circle me-3"
                        src="https://dummyimage.com/40x40/ced4da/6c757d"
                        alt="..."
                      />
                      <div class="small">
                        <div class="fw-bold">노나현</div>
                        <div class="text-muted">March 23, 2023 &middot; 4 min read</div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-4 mb-5">
              <div class="card shadow border-0" style="height: 80%">
                <img
                  class="card-img-top"
                  style="height: 45%"
                  src="assets/img-main/blog3.jpg"
                  alt="..."
                />
                <div class="card-body p-4">
                  <div class="badge bg-primary bg-gradient rounded-pill mb-2">Sights</div>
                  <a class="text-decoration-none link-dark stretched-link" href="#!">
                    <h5 class="card-title mb-3 fw-bold">가족과 주말 나들이</h5>
                  </a>
                  <p class="card-text mb-0">
                    이번주 주말 가족과 강정보에 다녀왔어요~
                  </p>
                </div>
                <div class="card-footer p-4 pt-0 bg-transparent border-top-0">
                  <div class="d-flex align-items-end justify-content-between">
                    <div class="d-flex align-items-center">
                      <img
                        class="rounded-circle me-3"
                        src="https://dummyimage.com/40x40/ced4da/6c757d"
                        alt="..."
                      />
                      <div class="small">
                        <div class="fw-bold">허동원</div>
                        <div class="text-muted">March 23, 2023 &middot; 4 min read</div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>

      <!-- 리뷰 공유 -->
      <div class="py-5" style="background-color: #EAF5FB">
        <div class="container p-5 my-5">
          <div class="row gx-5 justify-content-center">
            <div class="col-lg-4 col-xl-4">
              <div>
                <h2 class="fw-bolder pt-5 mt-5">여행기와 리뷰를</h2>
                <h2 class="fw-bolder" >공유하세요</h2>
                <h4 class="mt-5">먼저 다녀온 여행자의</h4>
                <h4 >생생한 리뷰와 꿀팁을 확인하세요.</h4>
              </div>
            </div>

            <div class="col-lg-8 col-xl-6">
              <img class="" style="width: 110%" src="assets/img-main/info1.png" alt="" />
            </div>
          </div>
        </div>
      </div>
    </main>

    <!-- Footer-->
    <%@include file="/WEB-INF/views/common/footer.jsp" %>
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="js/scripts.js"></script>
  </body>
</html>
