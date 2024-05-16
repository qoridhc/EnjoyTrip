<script setup>
import { useFooterStore } from '@/stores/footer';
useFooterStore().isFixed = true
</script>

<template>
  <div class="container text-center mt-3">
    <router-view></router-view>
  </div>
</template>

<style></style>
