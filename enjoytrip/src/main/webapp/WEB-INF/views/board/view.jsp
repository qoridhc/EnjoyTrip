<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />


<c:if test="${article ne null}">
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content />
    <meta name="author" content />
    <title>EnjoyTrip 글 상세보기</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"
    />
    <link href="/assets/css/styles.css" rel="stylesheet" />
  </head>
  <body class="d-flex flex-column">
    <main class="flex-shrink-0">
    
      <!-- Navigation-->
      <%@ include file="/WEB-INF/views/common/navbar.jsp" %>
      <%-- <%@ include file="/common/confirm.jsp" %> --%>
      
      <section class="py-5">
      <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12 justify-content-start">
          <h2 class="fw-bolder mb-3">글보기</h2>
			<hr />
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
          <div class="row my-2">
            <h2 class="text-primary fw-bolder">${article.articleNo}. ${article.subject}</h2>
          </div>
          <div class="row">
            <div class="col-md-8">
              <div class="clearfix align-content-center">
                <img
                  class="avatar me-2 float-md-start bg-light p-2"
                  src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"
                />
                <p>
                  <span class="fw-bold">${article.userId}</span> <br />
                </p>
              </div>
            </div>
            <div class="col-md-4 align-self-center text-end">댓글 : 0</div>
            <div class="divider mb-3"></div>
            <div class="text-secondary">
              ${article.content}
            </div>
            <div class="divider mt-3 mb-3"></div>
            <div class="d-flex justify-content-end">
              <button type="button" id="btn-list" class="btn btn-outline-primary mb-3">
                글목록
              </button>
				<c:if test="${user.id eq article.userId}">
					<button type="button" id="btn-mv-modify"
					class="btn btn-outline-success mb-3 ms-1">글수정</button>
					<button type="button" id="btn-delete"
					class="btn btn-outline-danger mb-3 ms-1">글삭제</button>
				</c:if>
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
    
    <script>
      document.querySelector("#btn-list").addEventListener("click", function () {
        location.href = "${root}/article?action=list";
      });
      document.querySelector("#btn-mv-modify").addEventListener("click", function () {
        alert("글 수정");
        location.href = "${root}/article/modify/${article.articleNo}";
      });
      document.querySelector("#btn-delete").addEventListener("click", function () {
        alert("글 삭제");
        location.href = "${root}/article/delete/${article.articleNo}";
      });
    </script>
  </body>
</html>
</c:if>
<c:if test="${article eq null}">
	<script>
	alert("글이 삭제되었거나 부적절한 URL 접근입니다.");
	location.href = "${root}/article?action=list";
	</script>
</c:if>
