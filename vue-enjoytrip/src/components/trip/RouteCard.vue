<script setup>
const props = defineProps({
  place: Object,
  id: Number,
});

console.log(props.place.title);
</script>

<template>
  <div class="container pb-2 pt-2 me-5">
    <div class="row justify-content-center align-items-center">
      <div class="col-md-1 me-3 rounded-circle custom-bg-color text-center text-light" style="line-height: 30px">
        {{ id }}
      </div>

      <div class="col-md-10 border rounded pt-3">
        <div class="row">
          <div class="col-md-3 bg-">
            <img
              class="img-fluid rounded"
              src="https://mblogthumb-phinf.pstatic.net/MjAyMjA3MDNfNjQg/MDAxNjU2ODI0NDY3MjQ1.NvuXIg9IlYq-1V0hawiNbQLx0I4gP2hk-0kHniUswCsg.n7KXh7VY9b8vOGtpPdmn-YQ7foxHFzTFyUOSuUgc5Dgg.JPEG.effy0424/1656823763773.jpg?type=w800"
              alt="" />
          </div>
          <div class="col-md-8 pb-3" style="font-size: 13px; overflow: hidden">
            <div class="pt-1" style="font-weight: bold; color: black">{{ place.title }}</div>
            <div class="pt-1 pb-2" style="color: gray; overflow: hidden; white-space: nowrap; text-overflow: ellipsis">
              명소 {{ place.address }}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
img {
  max-width: 110%;
  height: 70%;
}

.custom-bg-color {
  background-color: #a6e3e9;
}
</style>
