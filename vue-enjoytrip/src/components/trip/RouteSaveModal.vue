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
        <div class="modal-body" style="margin-left: 20px">
          <div class="container mt-5">
            <h2 class="text-center mb-5">My Route</h2>
            <div class="timeline-container" style="max-height: 60vh; overflow-y: auto">
              <ul class="timeline">
                <RouteSaveCard v-for="(item, index) in selectedPlaceList" :key="index" :place="item" :idx="index" />
              </ul>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button
            class="rounded border-0"
            @click="save"
            data-bs-dismiss="modal"
            style="background-color: #a6e3e9; color: white">
            Save
          </button>
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
import { useRouter } from "vue-router";
import Swal from "sweetalert2";

const router = useRouter();
const mapStore = useMapStore();
const memberStore = useMemberStore();

const { selectedPlaceList } = storeToRefs(mapStore);
const { userInfo } = memberStore;

const save = async () => {
  const routeInfo = {
    userId: userInfo.id,
    infoList: selectedPlaceList.value.map((item, index) => {
      return {
        sequence: index,
        content_id: item.content_id,
        description: item.description,
      };
    }),
  };

  await postRoute(routeInfo);

  Swal.fire({
    title: "루트 저장 성공!",
    text: "나의 일정에서 확인해보세요!",
    icon: "success",
    confirmButtonText: "닫기",
    confirmButtonColor: "#a6e3e9",
  });

  router.push({ name: "user-route" });
};

const share = async () => {
  console.log(selectedPlaceList.value);
};
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
</style>
