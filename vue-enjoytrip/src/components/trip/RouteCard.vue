<script setup>
const props = defineProps({
  place: Object,
  id: Number,
});

// console.log(props.place);
</script>

<template>
  <div
    class="container pb-2 pt-2 me-5"
    @click="$emit('click')"
    @mouseover="$emit('mouseover', id)"
    @mouseout="$emit('mouseout')">
    <div class="row justify-content-center align-items-center">
      <div
        class="col-md-1 me-3 rounded-circle custom-bg-color text-center text-light"
        style="line-height: 30px; width: 30px">
        {{ id }}
      </div>

      <div class="col-md-10 border rounded pt-3">
        <div class="row">
          <div class="col-md-3 bg-">
            <img id="thumbnail" class="img-fluid rounded" :src="place.first_image" alt="" />
          </div>
          <div class="col-md-8 pb-3" style="font-size: 13px; overflow: hidden">
            <div class="pt-1" style="font-weight: bold; color: black">{{ place.title }}</div>
            <div class="pt-1 pb-2" style="color: gray; overflow: hidden; white-space: nowrap; text-overflow: ellipsis">
              명소 {{ place.addr1 }}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <hr style="margin-top: 0px; margin-bottom: 0px; width: 0" />
</template>

<style scoped>
#thumbnail {
  width: 60px;
  height: 60px;
}

.custom-bg-color {
  background-color: #a6e3e9;
}
</style>
