<script setup>
const props = defineProps({
  place: Object,
});

// console.log("routecard");
</script>

<template>
  <div class="container" @mouseover="$emit('mouseover', idx)" @mouseout="$emit('mouseout')" @click="$emit('click')">
    <div class="row">
      <div class="col-md-4 d-flex align-items-center justify-content-center">
        <img class="img-fluid rounded" :src="place.imgUrl" alt="" />
      </div>

      <div class="col" style="font-size: 13px">
        <div class="pt-3" style="font-weight: bold; color: black">{{ place.place_name }}</div>
        <div class="pt-1 pb-2" style="color: gray">명소 {{ place.address_name }}</div>
        <div class="d-flex align-items-center mb-3">
          <img src="/src/assets/img/like.png" style="width: 12px" />
          <div class="ps-2 pe-2">123</div>
          <img src="/src/assets/img/star.png" style="width: 15px" />
          <div class="ps-2 pe-2">251</div>
        </div>
      </div>
    </div>
  </div>
  <hr style="margin-top: 0px; margin-bottom: 0px" />
</template>

<style scoped>
img {
  max-width: 110%;
  height: 70%;
}
</style>
