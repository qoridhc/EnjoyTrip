<script setup>
import { ref } from "vue";
import { signup } from "@/api/user";
import { useRouter } from "vue-router";
import Swal from "sweetalert2";

const user = ref({});
const isFinish = ref(true);
const emailValid = ref(true);
const router = useRouter();

function submit() {
  console.log("submit(UserSignup.vue): 회원 정보\nuser: ", user.value);
  const result = validationCheck();
  if (result) {
    isFinish.value = true;

    Swal.fire({
      title: result,
      text: "확인하고 다시 시도해주세요",
      icon: "error",
      confirmButtonColor: "#EE4E4E",
      confirmButtonText: "닫기",
    });

    return;
  }

  signup(
    user.value,
    function (response) {
      console.log("submit(UserSignup.vue): 회원가입 결과 확인\nresponse: ", response);
      Swal.fire({
        title: "회원가입 성공!",
        text: "성공적으로 가입되었습니다.",
        icon: "success",
        confirmButtonText: "닫기",
        confirmButtonColor: "#a6e3e9",
      });
      if (response.data === "회원가입 성공") {
        router.push({ name: "Home" });
      }
    },
    function (error) {
      console.log("submit(UserSignup.vue): 회원가입 실패\nresponse: ", error);
    }
  );
}

function validationCheck() {
  emailValid.value = validateEmail(user.value.email);

  if (!user.value.name || !user.value.id || !user.value.pw || !user.value.pwCheck || !user.value.email) {
    console.log("validationCheck(UserSignup.vue): 항목 부재\nuser:", user.value);
    return "모든 내용을 입력해 주십시오";
  }
  if (user.value.pw !== user.value.pwCheck) {
    console.log("validationCheck(UserSignup.vue): 비밀번호 불일치\nuser:", user.value);
    return "비밀번호가 다릅니다.";
  }
  if (!emailValid.value) {
    console.log("validationCheck(UserSignup.vue): 유효하지 않은 이메일\nuser:", user.value);
    return "유효하지 않은 이메일입니다.";
  }
  return "";
}

function validateEmail(email) {
  const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return re.test(email);
}
</script>

<template>
  <div class="d-flex flex-column">
    <div class="flex-shrink-0 container px-5 py-5">
      <div class="py-5 px-4 px-md-5 mb-5">
        <!-- title -->
        <h1 class="fw-bolder text-center mb-5">회원가입</h1>

        <div class="row gx-5 justify-content-center">
          <div class="col-lg-8 col-xl-6">
            <form>
              <!-- Name input-->
              <div class="form-floating mb-3">
                <input class="form-control" id="name" type="text" v-model="user.name" />
                <label for="name">이름</label>

                <!-- 미입력시 -->
                <div class="invalid-feedback">A name is required.</div>
              </div>

              <!-- ID input -->
              <div class="form-floating mb-3">
                <input class="form-control" id="id" type="text" v-model="user.id" />
                <label for="name">아이디</label>

                <!-- 미입력시 -->
                <div class="invalid-feedback">ID is required.</div>
              </div>

              <!-- PWD input -->
              <div class="form-floating mb-3">
                <input class="form-control" id="pwd" type="password" v-model="user.pw" />
                <label for="name">비밀번호</label>

                <!-- 미입력시 -->
                <div class="invalid-feedback">A password is required.</div>
              </div>

              <!-- PWD recheck input -->
              <div class="form-floating mb-3">
                <input class="form-control" id="pwCheck" type="password" v-model="user.pwCheck" />
                <label for="name">비밀번호 확인</label>

                <!-- 미입력시 -->
                <div class="invalid-feedback">Password is not valid.</div>
              </div>

              <!-- Email address input-->
              <div class="form-floating mb-3">
                <input class="form-control" id="email" type="email" v-model="user.email" />
                <label for="email">이메일</label>

                <!-- 미입력, 양식 오류시 -->
                <div class="invalid-feedback" v-if="!user.email">An email is required.</div>
                <div class="invalid-feedback" v-if="!emailValid">Email is not valid.</div>
              </div>

              <!-- Submit Button-->
              <div class="d-grid">
                <input class="btn btn-lg btn-dark" @click.prevent="submit" type="submit" />
              </div>
            </form>
            <div v-if="!isFinish">모든 항목을 입력해 주십시오.</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
@import "@/assets/css/styles.css";
</style>
