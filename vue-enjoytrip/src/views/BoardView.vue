<script setup>
import { useFooterStore } from '@/stores/footer';
import { onBeforeRouteLeave } from 'vue-router';

const footerStore = useFooterStore()
footerStore.isFixed = false;
footerStore.isTopFixed = false;

onBeforeRouteLeave(()=>{
    footerStore.isTopFixed = true;
})
</script>

<template>
    <router-view />
</template>

<style scoped></style>