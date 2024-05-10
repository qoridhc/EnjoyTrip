<script setup>
import KakaoMap from "@/components/trip/KakaoMap.vue";
import RoutePlaceSelector from "@/components/trip/RoutePlaceSelector.vue";
import RouterPlaceSelector from "@/components/trip/RoutePlaceSelector.vue";
</script>

<template>
  <div class="container-fluid d-flex flex-column min-vh-100">
    <div class="row flex-grow-1">
      <RoutePlaceSelector />
      <div class="col-md-9">
        <!-- Right Section -->
        <KakaoMap class="h-100" />
      </div>
    </div>
  </div>
  <!-- <RouterPlaceSelector class="col-2 bg-info"></RouterPlaceSelector>
    <div>RouteView</div>
  </div> -->
</template>

<style scoped>
.container-fluid,
.row {
  height: 100%;
}
</style>
