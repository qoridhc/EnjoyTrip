<template>
  <div class="" style="height: 15vh"></div>
  <div class="profile-container text-center">
    <img class="img-fluid" src="@/assets/img/like.png" />
    <h4 class="pt-2">userName</h4>
    <div class="pt-2" style="font-size: 14px; color: blue">프로필 관리</div>
  </div>
  <div id="route-container" class="text-start mx-auto pt-3" style="height: 30vh; padding: 10px; width: 80%">
    <h3 style="font-weight: 600">나의 일정</h3>
    <div class="d-flex">
      <div>
        <router-link :to="{ name: 'user-route' }" class="pe-2" :class="{ active: isActive }" @click="isActive = true">
          전체 일정
        </router-link>

        <router-link :to="{ name: 'user-shared-route' }" :class="{ active: !isActive }" @click="isActive = false">
          공유된 일정
        </router-link>
      </div>
    </div>
    <RouterView class="pt-4"></RouterView>
  </div>
</template>

<script setup>
import { ref } from "vue";

const isActive = ref(true);
</script>

<style scoped>
.profile-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.img-fluid {
  width: 50px;
  height: 50px;
  object-fit: contain;
}

#route-container {
  width: 70%;
  padding: 10px;
}

.active {
  /* font-weight: bold; */
  color: #71c9ce;
}

a {
  color: rgb(187, 185, 185);
  text-decoration: none; /* 밑줄 없애기 */
}
</style>
