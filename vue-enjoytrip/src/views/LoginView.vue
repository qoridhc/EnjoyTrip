<script setup></script>

<template>
  <div>
    <div class="d-flex justify-content-center align-items-center" style="height: 100vh">
      <div class="col-lg-4">
        <form id="signup" method="post" action="${root}/user/login">
          <!-- ID input -->
          <div class="form-floating mb-3">
            <input
              class="form-control"
              id="id"
              name="id"
              type="text"
              placeholder="Enter your ID..."
              data-sb-validations="required" />
            <label for="id">아이디</label>
            <div class="invalid-feedback" data-sb-feedback="id:required">아이디를 입력하세요.</div>
          </div>
          <!-- Password input -->
          <div class="form-floating mb-3">
            <input
              class="form-control"
              id="password"
              name="pw"
              type="password"
              placeholder="Enter your password..."
              data-sb-validations="required" />
            <label for="password">비밀번호</label>
            <div class="invalid-feedback" data-sb-feedback="password:required">비밀번호를 입력하세요.</div>
          </div>
          <!-- Submit button -->
          <div class="d-grid">
            <input
              class="btn btn-dark btn-lg"
              id="submitButton"
              type="submit"
              name="action"
              value="Login"
              style="background-color: #e9859e" />
          </div>
          <!-- Links -->
          <div class="d-flex justify-content-end mt-3">
            <a class="link-dark small me-2" href="#!">아이디 찾기</a>
            <a class="link-dark small" href="#!">비밀번호 찾기</a>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
