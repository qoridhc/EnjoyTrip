<template>
  <li class="timeline-item ms-3">
    <div class="timeline-badge" :style="{ backgroundColor: colorList[idx % 5] }">
      <img
        :src="contentTypeList.find((item) => item.content_type_id === props.place.content_type_id).content_img"
        alt="icon"
        style="width: 70%; padding-top: 6px" />
    </div>
    <div class="timeline-panel pb-3" style="width: 90%">
      <div class="timeline-heading">
        <h4 style="font-size: 18px; padding-top: 10px">{{ place.title }}</h4>
      </div>
      <div class="timeline-body d-flex">
        <div>
          <img class="rounded" :src="place.first_image" alt="Image" style="height: 100px; width: 100px" />
        </div>
        <textarea
          class="ms-2 border border-dark rounded ps-2 pt-1 flex-grow-1"
          v-model="inputText"
          maxlength="175"
          placeholder="선택한 여행지를 설명해주세요"
          style="resize: none"></textarea>
      </div>
    </div>
  </li>
</template>

<script setup>
const colorList = ["#7AB2B2", "#CA8787", "#40A578", "#AF8F6F", "#AD88C6"];

const props = defineProps({
  place: Object,
  idx: Number,
});

import { ref, watch } from "vue";
import { useMapStore } from "@/stores/map";
import { storeToRefs } from "pinia";
import { contentTypeList } from "@/util/constants";

const mapStore = useMapStore();
const { selectedPlaceList } = storeToRefs(mapStore);

const inputText = ref("");

watch(inputText, (newValue, oldValue) => {
  selectedPlaceList.value[props.idx].description = newValue;
});
</script>

<style scoped>
.timeline {
  position: relative;
  padding: 0;
  list-style: none;
}
.timeline::before {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 20px;
  width: 4px;
  margin-left: -2px;
  content: "";
  background-color: #e9ecef;
}
.timeline-item {
  position: relative;
  margin-bottom: 50px;
  padding-left: 40px;
}
.timeline-badge {
  position: absolute;
  top: 0;
  left: 0;
  width: 40px;
  height: 40px;
  margin-left: -20px;
  border-radius: 50%;
  text-align: center;
}
.timeline-badge img {
  width: 100%;
  height: auto;
  border-radius: 50%;
}
.timeline-panel {
  padding: 0 20px;
  background: #fff;
  border: 1px solid black;
  border-radius: 15px;
}
.timeline-panel img {
  width: 100%;
  height: auto;
  border-radius: 2px;
}

textarea:focus {
  outline: none;
}
</style>
