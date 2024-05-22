<script setup>
import ShareMain from '@/components/share/ShareMain.vue';
import { useFooterStore } from '@/stores/footer';
import { onBeforeRouteLeave } from 'vue-router';
const footerStore = useFooterStore()

footerStore.isFixed = false
footerStore.isTopFixed = false;

onBeforeRouteLeave(()=>{
    footerStore.isTopFixed = true;
})

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
