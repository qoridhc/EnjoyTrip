<script setup>
import { ref } from "vue";
import HomeMainModal from "./HomeMainModal.vue";
import HomeMainCollapse from "./HomeMainCollapse.vue";

const isShow = ref(false);
const closeModal = () => {
  isShow.value = false;
};
const openModal = () => {
  console.log("openModal(HomeMainText.vue): 부모 open");
  isShow.value = true;
}
</script>

<template>
  <div class="my-5 text-start">
    <p class="fw-bolder mb-1 main-text">여행을 긍정적으로</p>
    <p class="fw-bolder mb-4 main-text">시간을 효율적으로</p>
    <p class="fw-bold mb-5 pb-2 main-text-sm">Enjoy Trip으로 행복한 여행을 계획하고 공유하세요</p>

    <p>
      <button
        class="btn btn-dark btn-lg px-4 me-sm-3"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#startInputCollapse"
        aria-expanded="false"
        aria-controls="startInputCollapse">
        시작하기
      </button>
    </p>
    <HomeMainCollapse @open="openModal" />
    <HomeMainModal v-if="isShow" @close="closeModal" />
  </div>
</template>


<style scoped>
.main-text {
  font-size: 3.5rem;
}

.main-text-sm {
  font-size: 1.3rem;
  color: rgba(0, 0, 0, 0.742);
}

.me-6 {
  margin-right: 60px;
}
</style>
