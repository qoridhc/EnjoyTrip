<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<main>
	<div class="col-md-6 m-auto">
		<div class="alert mt-2 text-center fw-bold" role="alert"
			style="border: 1px solid gray;">나만의 핫플 자랑하기</div>
		<div class="row">
			<!-- 지도 표시 -->
			<div class="col-6">
				<div id="map" style="width: 100%; height: 550px;"></div>
				<div id="clickLatlng"></div>
			</div>

			<!-- 글 작성 -->
			<div class="col-6">
				<form action="/action_page.php">
					<div class="mb-3">
						<label for="image" class="form-label"
							style="font-size: small; font-style: italic; color: red;">스마트폰으로
							찍은 사진을 올려주세요.</label> <input type="file" class="form-control"
							id="inputGroupFile02">
					</div>
					<div class="mb-3 mt-3">
						<label for="text" class="form-label">핫플 이름</label> <input
							type="textarea" class="form-control" id="textarea"
							name="textarea">
					</div>
					<div class="mb-3 mt-3">
						<label for="date" class="form-label">다녀온 날짜</label> <input
							type="date" class="form-control" id="date" name="date">
					</div>
					<div class="mb-3 mt-3">
						<label for="locationType" class="form-label">장소 유형</label> <select
							class="form-select" id="locationType">
							<option selected>장소 유형을 선택해주세요.</option>
							<option value="1">행사/공연/축제</option>
							<option value="2">데이트</option>
							<option value="3">운동/건강</option>
							<option value="3">힐링/치유</option>
						</select>
					</div>
					<div class="mb-3 mt-3">
						<span class="text">핫플 상세설명</span>
						<textarea class="form-control" rows="5" aria-label="With textarea"></textarea>
					</div>

					<button type="submit" class="btn btn-primary">등록</button>
				</form>
			</div>
		</div>
	</div>
</main>

<!-- Footer-->
<footer class="py-4 fixed-bottom mt-auto" style="background-color:#f2d9d6">
  <div class="container px-5">
    <div class="row align-items-center justify-content-between flex-column flex-sm-row">
      <div class="col-auto">
        <div class="small m-0 ">Copyright &copy; SSAFY Heodongwon LeeSeongHyeon</div>
      </div>
      <div class="col-auto">
        <a class="link-secondary small" href="#!">Privacy</a>
        <span class=" mx-1">&middot;</span>
        <a class="link-secondary small" href="#!">Terms</a>
        <span class=" mx-1">&middot;</span>
        <a class="link-secondary small" href="#!">Contact</a>
      </div>
    </div>
  </div>
</footer>
  <script src="js/scripts2.js"></script>
