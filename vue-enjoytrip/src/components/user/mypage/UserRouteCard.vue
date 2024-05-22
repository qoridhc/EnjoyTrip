<template>
  <router-link :to="{ name: 'myroute' }">
    <div
      class="image-container me-2 mb-2"
      @click="moveMyRoute()"
      @mouseenter="isHover = true"
      @mouseleave="isHover = false">
      <img class="img-fluid rounded" :src="currRouteInfo.first_image" />

      <div class="overlay rounded" id="custom-overlay" :class="{ active: isHover }">
        <div class="overlay-content">
          <div class="text-center">
            <div class="overlay-title">{{ currRouteInfo.title }}</div>
          </div>
          <div class="button-container">
            <button class="w-50 btn btn-primary" @click.prevent="share">공유하기</button>
            <button class="w-50 btn btn-danger" @click.prevent="remove">삭제하기</button>
          </div>
        </div>
      </div>
      <div class="overlay" id="default-overlay" :class="{ active: !isHover }">{{ sidoName }}</div>
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
import { deleteRoute, shareRoute } from "@/api/route";
import { useRouter } from "vue-router";

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

const emit = defineEmits(['remove'])
const router = useRouter()

// 처음 실행
onMounted(async () => {
  const contentId = props.route.infoList[0].content_id;

  await searchByContentId(contentId, (res) => {
    currRouteInfo.value = res.data;
    sidoName.value = attractionList.find((item) => item.sido_code === currRouteInfo.value.sido_code)?.sido_name;
  });
});

// overlay 클릭
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
        latlng: new kakao.maps.LatLng(res.data.latitude, res.data.longitude),
        first_image: res.data.first_image,
        description: element.description,
      };

      selectedPlaceList.value.push(selectedPlaceInfo);
    });
  }

  mapStore.isChanged = true;
};

// 버튼 클릭
function share(){
  console.log("share(UserRouteCard): 공유하기 버튼 클릭")
  shareRoute(
    props.route.route_id,
    function(){
      emit('remove', props.route.route_id)
    },
    function(error){
      console.log("remove(UserRouteCard): 공유하기 실패\nerror: ", error)
    }
  )
}

function remove(){
  deleteRoute(
    props.route.route_id,
    function(){
      emit('remove', props.route.route_id)
    },
    function(error){
      console.log("remove(UserRouteCard): 삭제하기 실패\nerror: ", error)
    }
  )
}
</script>

<style scoped>
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

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

#default-overlay {
  font-size: 20px;
}

#custom-overlay {
  background-color: rgba(0, 0, 0, 0.5);
  width: 220px;
  height: 200px;
}

.overlay.active {
  opacity: 1;
}

.overlay-content {
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100%;
}

.overlay-title {
  font-size: 16px;
  margin-bottom: 10px;
  width: 220px;
}

.button-container {
  position: absolute;
  bottom: 0; 
  display: flex;
  justify-content: space-between;
  width: 100%;
}
</style>
