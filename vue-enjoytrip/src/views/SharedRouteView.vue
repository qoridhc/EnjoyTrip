<script setup>
import ShareMain from '@/components/share/ShareMain.vue';
import { useFooterStore } from '@/stores/footer';
useFooterStore().isFixed = false
</script>

<template>
  <div class="container text-center">
    <ShareMain />
  </div>
</template>

<style scoped>
.container.text-center{
  margin-top: 60px;
}
</style>
