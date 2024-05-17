<template>
  <div class="d-flex flex-wrap gap-3">
    <UserRouteCard v-for="i in 8" :key="i" />
  </div>
</template>

<script setup>
import UserRouteCard from "@/components/user/mypage/UserRouteCard.vue";
</script>

<style scoped></style>
