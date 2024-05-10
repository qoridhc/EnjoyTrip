<script setup>
import RouteCard from "@/components/trip/RouteCard.vue";
</script>

<template>
  <div class="col-md-3">
    <!-- Left Section -->
    <div class="border p-4 h-100">
      <h3>부산</h3>
      <p>2024.05.10(금) ~ 2024.05.11(토)</p>
      <p>장소 선택</p>
      <div class="search">
        <input type="text" placeholder="장소명을 입력해 주세요" />
        <img src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png" />
      </div>
      <RouteCard v-for="i in 7" />
    </div>
  </div>
</template>

<style scoped>
.search {
  position: relative;
  width: 300px;
}

input {
  width: 100%;
  border: 1px solid #bbb;
  border-radius: 8px;
  padding: 10px 12px;
  font-size: 14px;
}

img {
  position: absolute;
  width: 17px;
  top: 10px;
  right: 12px;
  margin: 0;
}
</style>
