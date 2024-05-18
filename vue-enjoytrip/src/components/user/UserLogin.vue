<script setup>
import { ref } from "vue";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";
import { useMemberStore } from "@/stores/member";
import axios from "axios";
import { useMenuStore } from "@/stores/menu";

const router = useRouter();

const memberStore = useMemberStore();

const { isLogin, isLoginError } = storeToRefs(memberStore);
const { userLogin, getUserInfo } = memberStore;
const { changeMenuState } = useMenuStore();

const loginUser = ref({
  id: "",
  pw: "",
});

const isSubmitting = ref(false);

const login = async () => {
  if (isSubmitting.value) return;

  // 로그인 시작, axios 요청
  isSubmitting.value = true;
  console.log("login(UserLogin.vue): 로그인 실행\nuser: ", loginUser.value);
  await userLogin(loginUser.value);

  // 토큰 설정
  let token = sessionStorage.getItem("accessToken");
  if (isLogin.value) {
    getUserInfo(token);
    changeMenuState();
    router.replace("/");
  }

  // 로그인 끝
  isSubmitting.value = false;
};
</script>

<template>
  <div>
    <div class="d-flex justify-content-center align-items-center" style="height: 100vh">
      <div class="col-lg-4">
        <form v-on:submit.prevent="login" id="signup" method="post" action="${root}/user/login">
          <!-- ID input -->
          <div class="form-floating mb-3">
            <input
              class="form-control"
              v-model="loginUser.id"
              placeholder="Enter your ID..."
              data-sb-validations="required" />
            <label for="id">아이디</label>
            <div class="invalid-feedback" data-sb-feedback="id:required">아이디를 입력하세요.</div>
          </div>
          <!-- Password input -->
          <div class="form-floating mb-3">
            <input
              class="form-control"
              type="password"
              v-model="loginUser.pw"
              @keyup.enter="login"
              placeholder="Enter your password..."
              data-sb-validations="required" />
            <label for="password">비밀번호</label>
            <div class="invalid-feedback" data-sb-feedback="password:required">비밀번호를 입력하세요.</div>
          </div>
          <!-- Submit button -->
          <div class="d-grid">
            <button class="btn btn-dark btn-lg" @click="login" :disabled="isSubmitting" style="background-color: #e9859e">로그인</button>
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
