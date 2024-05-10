<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
    
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>EnjoyTrip - 여행지정보</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Bootstrap icons-->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
      rel="stylesheet"
    />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/styles.css" rel="stylesheet" />
  </head>

  <body class="d-flex flex-column h-100">
    <main class="flex-shrink-0">
      <!-- Navigation-->
      <%@ include file="/navbar.jsp" %>
      
    </main>

    <!-- Section -->
    <section class="container">
      <div class="row pt-5">
        <div class="col-auto py-5" style="float: none; margin: 0 auto">
          <h1 class="fw-bolder">지역별 관광정보</h1>
        </div>
      </div>
      <!-- 시, 도 // 구, 군 // 관광지유형   선택바 START -->
      <div class="row d-flex justify-content-center mb-5">
        <div class="col-md-3 col-lg-3 col-xl-3">
          <div class="nav-item dropdown">
            <select id="search-sido" class="form-select me-2" onchange="changeSelect()">
              <option value="0" selected>시,도 선택</option>
              <c:forEach items="${sidoList}" var="sido">
              	<option value="${sido.sido_code}">${sido.sido_name}</option>
              </c:forEach>
            </select>
          </div>
        </div>
        <div class="col-md-3 col-lg-3 col-xl-3">
          <div class="nav-item dropdown">
            <select id="search-gugun" class="form-select me-2">
              <option value="0" selected>구, 군 선택</option>
              <c:forEach items="${gugunList}" var="gugun">
              	<option value="${gugun.gugun_code}">${gugun.gugun_name}</option>
              </c:forEach>
            </select>
          </div>
        </div>
        <div class="col-md-3 col-lg-3 col-xl-3">
          <!-- (12:관광지, 14:문화시설, 15:축제공연행사, 25:여행코스, 28:레포츠, 32:숙박, 38:쇼핑, 39:음식점) -->
          <div class="nav-item dropdown">
            <select id="search-content-id" class="form-select me-2">
              <option value="0" selected>관광지 유형</option>
              <option value="12">관광지</option>
              <option value="14">문화시설</option>
              <option value="15">축제공연행사</option>
              <option value="25">여행코스</option>
              <option value="28">레포츠</option>
              <option value="32">숙박</option>
              <option value="38">쇼핑</option>
              <option value="39">음식점</option>
            </select>
          </div>
        </div>
        <div class="col-md-1 col-lg-1 col-xl-1">
          <button class="btn btn-primary" id="btn-search">검색</button>
        </div>
      </div>
      <!-- 시, 도 // 구, 군 // 관광지유형   선택바 END -->

      <!-- kakao map start -->
      <div class="row d-flex justify-content-center mt-4 mb-5">
        <!-- kakao map start -->
        <div id="map" class="w-100" style="height: 600px">map</div>
        <!-- kakao map end -->

        <!-- 관광지 목록-->
        <div class="row">
          <table class="table table-striped" style="display: none">
            <thead>
              <tr>
                <th>대표이미지</th>
                <th>관광지명</th>
                <th>주소</th>
                <th>위도</th>
                <th>경도</th>
              </tr>
            </thead>
            <tbody id="trip-list">
            </tbody>
          </table>
        </div>
      </div>
    </section>

    <!-- Footer-->
    <%@include file="/footer.jsp" %>

    <script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=773d23c4f5a4f8688d1d15740383ddd7"
    ></script>

    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="${root}/js/scripts.js"></script>
    <!-- Kakao MAP API JS -->
    <script type="text/javascript" src="${root}/js/attractionFinderMap.js"></script>
  </body>
</html>
    