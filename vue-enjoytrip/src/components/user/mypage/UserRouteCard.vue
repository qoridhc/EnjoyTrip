<template>
  <router-link :to="{ name: 'myroute' }">
    <div
      class="image-container me-2 mb-2"
      @click="moveMyRoute()"
      @mouseenter="isHover = true"
      @mouseleave="isHover = false">
      <img class="img-fluid rounded" :src="currRouteInfo.first_image" />
      <div class="overlay rounded" id="custom-overay" :class="{ active: isHover }">
        <div class="text-center" style="font-size: 20px">
          <div style="font-size: 16px">{{ currRouteInfo.title }}</div>
          <div style="font-size: 16px">{{ sidoName }}</div>
          <div>{{ idx + 1 }}</div>
          <div>보기</div>
        </div>
      </div>
      <div class="overlay" id="default-overay" :class="{ active: !isHover }">{{ sidoName }}</div>
    </div>
  </router-link>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { storeToRefs } from "pinia";
import { useMapStore } from "@/stores/map";
import { searchByContentId } from "@/api/map";
import { attractionList } from "@/util/constants.js";
import { useRouteStore } from "@/stores/route";

const routeStore = useRouteStore();
const mapStore = useMapStore();

const { selectedPlaceList } = storeToRefs(mapStore);

const currRouteInfo = ref({});
const sidoName = ref("");
const isHover = ref(false);

const props = defineProps({
  route: Object,
  idx: Number,
});

const moveMyRoute = async () => {
  selectedPlaceList.value = [];

  routeStore.sido_code = currRouteInfo.value.sido_code;
  routeStore.sido_name_kor = sidoName.value;

  for (const element of props.route.infoList) {
    await searchByContentId(element.content_id, (res) => {
      const selectedPlaceInfo = {
        content_id: res.data.content_id,
        title: res.data.title,
        addr1: res.data.addr1,
        currPos: new kakao.maps.LatLng(res.data.latitude, res.data.longitude),
        first_image: res.data.first_image,
        description: element.description,
      };

      selectedPlaceList.value.push(selectedPlaceInfo);
    });
  }
};

onMounted(async () => {
  const contentId = props.route.infoList[0].content_id;

  await searchByContentId(contentId, (res) => {
    currRouteInfo.value = res.data;

    sidoName.value = attractionList.find((item) => item.sido_code === currRouteInfo.value.sido_code)?.sido_name;
  });
});
</script>

<style scoped>
.image-container {
  position: relative;
  display: inline-block;
}

.img-fluid {
  width: 220px; /* 원하는 크기로 조절 */
  height: 200px;
  border-radius: 10px; /* 테두리를 둥글게 조절 */
}

.overlay {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: white;
  padding: 10px;
  font-size: 16px;
  opacity: 0;
  transition: opacity 0.3s ease-in-out;

  /* 수직 가운데 정렬을 위한 스타일 */
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

#default-overay {
  font-size: 20px;
}

#custom-overay {
  background-color: rgba(0, 0, 0, 0.5);
  width: 220px; /* 원하는 크기로 조절 */
  height: 200px;
  /* margin-top: 5px; */
}

.overlay.active {
  opacity: 1;
}
</style>
