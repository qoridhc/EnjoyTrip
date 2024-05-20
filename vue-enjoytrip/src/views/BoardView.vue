<script setup>
import { useFooterStore } from '@/stores/footer';
useFooterStore().isFixed = false
</script>

<template>
    <router-view />
</template>

<style scoped></style>