<template>
  <div class="image-container" @mouseenter="isHover = true" @mouseleave="isHover = false">
    <img
      class="img-fluid rounded"
      src="https://a.cdn-hotels.com/gdcs/production123/d1230/d3ed36fc-c77c-4843-9b5e-7a5bfe80151d.jpg" />
    <div class="overlay rounded" id="custom-overay" :class="{ active: isHover }">
      <div class="text-center" style="font-size: 20px">
        <div>제주도 올레길</div>
        <div>제주도</div>
        <div>1</div>
        <div>보기</div>
      </div>
    </div>
    <div class="overlay" id="default-overay" :class="{ active: !isHover }">제주</div>
  </div>
</template>

<script setup>
import { ref } from "vue";

const isHover = ref(false);
</script>

<style scoped>
.image-container {
  position: relative;
  display: inline-block;
}

.img-fluid {
  width: 220px; /* 원하는 크기로 조절 */
  height: 200px;
  border-radius: 10px; /* 테두리를 둥글게 조절 */
}

.overlay {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: white;
  padding: 10px;
  font-size: 16px;
  opacity: 0;
  transition: opacity 0.3s ease-in-out;

  /* 수직 가운데 정렬을 위한 스타일 */
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

#default-overay {
  font-size: 20px;
}

#custom-overay {
  background-color: rgba(0, 0, 0, 0.5);
  width: 220px; /* 원하는 크기로 조절 */
  height: 200px;
  /* margin-top: 5px; */
}

.overlay.active {
  opacity: 1;
}
</style>
