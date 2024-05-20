<script setup>
import { ref, computed, defineEmits } from "vue";
import { useRouteStore } from "@/stores/route";

const routeStore = useRouteStore();
const emit = defineEmits(["open"]);

import { attractionList } from "@/util/constants.js";

const inputRoute = ref("");
const filteredAttractionList = computed(() => {
  const searchTerm = inputRoute.value.toLowerCase();
  return attractionList.filter((attraction) => attraction.sido_name.toLowerCase().includes(searchTerm));
});

const openModal = (sido) => {
  emit("open");
  routeStore.sido_code = sido.sido_code;
  routeStore.sido_name_kor = attractionList.find((item) => item.sido_code === routeStore.sido_code)?.sido_name;
};
</script>

<template>
  <div class="collapse w-100" id="startInputCollapse">
    <div class="input-group">
      <input
        type="text"
        class="form-control border border-dark rounded-pill me-3 custom-input"
        placeholder="어디로 여행을 떠나시나요?"
        v-model="inputRoute" />
    </div>
    <ul v-if="inputRoute" class="list-group position-absolute">
      <li
        v-for="(sido, index) in filteredAttractionList"
        :key="index"
        class="list-group-item"
        @click.prevent="openModal(sido)">
        {{ sido.sido_name }}
      </li>
    </ul>
  </div>
</template>

<style scoped>
.custom-input {
  padding-left: 20px;
  height: 50px;
  font-size: 1.2em; /* 텍스트 크기 조정 */
}
.btn {
  border: 1px solid black;
}
.list-group {
  max-height: 200px;
  overflow-y: auto;
  width: 26.6%;
  background-color: #f8f9fa;
  border: 1px solid #ddd;
  border-radius: 0.25rem;
  margin-top: 5px;
  padding: 0;
}
.list-group-item {
  border: none;
  padding: 10px 15px;
  background-color: #f8f9fa;
}
</style>
