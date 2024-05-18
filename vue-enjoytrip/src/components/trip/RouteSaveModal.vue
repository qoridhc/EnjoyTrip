<template>
  <div
    class="modal fade"
    id="routeSaveModal"
    tabindex="-1"
    aria-labelledby="routeSaveModalLabel"
    aria-hidden="true"
    data-bs-backdrop="false">
    <div class="modal-dialog modal-lg">
      <!-- Add 'modal-lg' class here -->
      <div class="modal-content">
        <!-- Add a custom class here -->
        <div class="modal-header">
          <h5 class="modal-title" id="routeSaveModalLabel">나의 일정</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="container mt-5">
            <h2 class="text-center mb-5">My Route</h2>
            <ul class="timeline">
              <RouteSaveCard v-for="(item, index) in userRouteList" :key="index" :place="item" :idx="index" />
            </ul>
          </div>
        </div>
        <div class="modal-footer">
          <button @click="save" type="button" class="btn" style="background-color: #a6e3e9; color: white">Save</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import RouteSaveCard from "./RouteSaveCard.vue";
import { useMapStore } from "@/stores/map";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";
import { postRoute } from "@/api/map";

const mapStore = useMapStore();
const memberStore = useMemberStore();

const { userRouteList } = storeToRefs(mapStore);
const { userInfo } = memberStore;

// const props = defineProps({
//   place: Object,
// });

const save = async () => {
  const routeInfo = {
    userId: userInfo.id,
    infoList: userRouteList.value.map((item, index) => {
      return {
        sequence: index,
        content_id: item.content_id,
        description: item.description,
      };
    }),
  };

  // console.log("save(RouteSaveModal.vue): 루트 저장 post 데이터 확인\nrouteInfo", routeInfo);
  await postRoute(routeInfo);
};
</script>

<!-- export default defineComponent({
  components: {
    TimelineItem,
  },
  setup() {
    const timelineItems = ref([
      {
        title: "제주여행",
        description: "음식점",
        imageSrc: "jeju1.jpg",
        badgeIcon: "icon1.png",
      },
      {
        title: "장생포 고래바다여행선",
        description: "관광지",
        imageSrc: "whale.jpg",
        badgeIcon: "icon2.png",
      },
      {
        title: "제주 추억여행",
        description: "숙박",
        imageSrc: "jeju2.jpg",
        badgeIcon: "icon3.png",
      },
    ]);

    return {
      timelineItems,
    };
  },
}); -->

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
</style>
