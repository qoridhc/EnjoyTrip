<script setup>
import { RouterLink, RouterView } from "vue-router";
import CommonHeader from "@/components/common/CommonHeader.vue";
import CommonFooter from "./components/common/CommonFooter.vue";
</script>

<template style="font-family: 'NotoSansKR-Regular'">
  <header>
    <div class="wrapper">
      <nav>
        <CommonHeader />
      </nav>
    </div>
  </header>

  <RouterView style="font-family: 'NotoSansKR-Regular'" />
  <CommonFooter />
</template>

<style scoped>
@font-face {
  font-family: "NotoSansKR-Regular";
  src: url("@/assets/fonts/NotoSansKR-Regular.ttf");
}
</style>
