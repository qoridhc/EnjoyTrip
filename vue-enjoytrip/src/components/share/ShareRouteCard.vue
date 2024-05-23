<script setup>
import CommonSpinner from "../common/CommonSpinner.vue";

import { attractionList } from "@/util/constants";
import { useMapStore } from "@/stores/map";
import { searchByContentId } from "@/api/map";
import { useRouteStore } from "@/stores/route";
import { useRouter } from "vue-router";

import { storeToRefs } from "pinia";
import { onMounted } from "vue";
import { ref } from "vue";

// 에러처리(로딩 기다리기)
const isShow = ref(false);

// 카드 표지 정보
const sharedPlaceInfo = ref({});

// props
const props = defineProps({
  place: Object,
});

// hover
const isHover = ref(false);

// pinia, map 이동
const mapStore = useMapStore();
const { selectedPlaceList } = storeToRefs(mapStore);

// pinia, map 이동(검색시 sidoName 적용)
const routeStore = useRouteStore();
const router = useRouter();

onMounted(async () => {
  await searchByContentId(
    props.place.infoList[0].content_id,
    (res) => {
      sharedPlaceInfo.value = {
        content_id: res.data.content_id,
        title: res.data.title.split("(")[0],
        addr1: res.data.addr1,
        latlng: new kakao.maps.LatLng(res.data.latitude, res.data.longitude),
        first_image: res.data.first_image,
        description: props.place.infoList[0].description,
        sidoName: attractionList.find((item) => item.sido_code === res.data.sido_code)?.sido_name,
        content_type_id: res.data.content_type_id,
      };
      isShow.value = true;
    },
    function (error) {
      console.log(error);
    }
  );
});

const moveSharedRoute = async () => {
  selectedPlaceList.value = [];
  routeStore.sido_name_kor = sharedPlaceInfo.value.sidoName;
  routeStore.sido_code = attractionList.find((item) => item.sido_name === routeStore.sido_name_kor)?.sido_code;

  for (const info of props.place.infoList) {
    await searchByContentId(info.content_id, function (res) {
      const selectedPlaceInfo = {
        content_id: res.data.content_id,
        title: res.data.title,
        addr1: res.data.addr1,
        latlng: new kakao.maps.LatLng(res.data.latitude, res.data.longitude),
        first_image: res.data.first_image,
        description: info.description,
        content_type_id: res.data.content_type_id,
      };
      selectedPlaceList.value.push(selectedPlaceInfo);
    });
  }
  router.push({ name: "myroute" });
};
</script>

<template>
  <div
    class="col-xl-3 col-lg-4 col-md-6 col-sm-10 my-5 gx-4 gy-5"
    v-if="isShow"
    @click="moveSharedRoute()"
    @mouseenter="isHover = true"
    @mouseleave="isHover = false">
    <div class="card shadow-sm border-0">
      <img :src="sharedPlaceInfo.first_image" class="card-img-top" alt="Place Image" />
      <div class="card-body">
        <p class="text-muted mb-2 text-small">{{ sharedPlaceInfo.sidoName }}</p>
        <h5 class="card-title">{{ sharedPlaceInfo.title }}</h5>
        <p class="card-text text-muted">{{ place.userId }}</p>
      </div>

      <div class="overlay rounded" id="custom-overay" :class="{ active: isHover }">
        <div class="text-center" style="font-size: 20px">
          <div>보기</div>
        </div>
      </div>
    </div>
  </div>

  <CommonSpinner class="col-xl-3 col-lg-4 col-md-6 col-sm-10 my-5 gx-4 gy-5" v-if="!isShow" />
</template>

<style scoped>
.card-title {
  line-height: 0.1;
  margin: 0.8rem 0;
}

.card-img-top {
  max-height: 200px;
}

a {
  text-decoration: none;
}

.image-container {
  position: relative;
  display: inline-block;
}

.img-fluid {
  width: 220px;
  height: 200px;
  border-radius: 10px;
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
  width: 300px;
  height: 307.59px;
  cursor: pointer;
}

.overlay.active {
  opacity: 1;
}
</style>
