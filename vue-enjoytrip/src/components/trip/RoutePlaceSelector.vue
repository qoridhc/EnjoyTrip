<script setup>
import RouteCard from "@/components/trip/PlaceCard.vue";
</script>

<template>
  <div class="col-md-3">
    <!-- Left Section -->
    <div class="border p-4 h-100">
      <div style="font-size: 25px; font-weight: bold">부산</div>
      <div class="pt-2 pb-3" style="font-size: 16px; font-weight: bold">2024.05.10(금) ~ 2024.05.11(토)</div>
      <!-- <div class="pt-2 pb-2">장소 선택</div> -->
      <div class="search">
        <input type="text" placeholder="장소명을 입력해 주세요" />
        <img src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png" />
      </div>
      <div class="mt-3">
        <RouteCard v-for="i in 7" />
      </div>
    </div>
  </div>

  <div class="col-md-2">22</div>
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
