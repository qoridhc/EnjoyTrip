<script setup>
import UserRouteCard from "@/components/user/mypage/UserRouteCard.vue";
import CommonSpinner from "@/components/common/CommonSpinner.vue";

import { onMounted, ref } from "vue";
import { storeToRefs } from "pinia";
import { useMapStore } from "@/stores/map";
import { useMemberStore } from "@/stores/member";

const mapStore = useMapStore();
const memberStore = useMemberStore();

const { userInfo } = memberStore;
const { fetchGetRouteDetail } = mapStore;
const { userRouteDetailList } = storeToRefs(useMapStore());
const isShow = ref(false)
const key = ref(0)

onMounted( async ()=>{
  isShow.value = false

  const userId = userInfo.id;
  await fetchGetRouteDetail(userId);
  userRouteDetailList.value = userRouteDetailList.value.filter((routeDetail) => routeDetail.shared == true)
  
  isShow.value = true
})

function removeRoute(route_id){
  userRouteDetailList.value.splice(
    userRouteDetailList.value.findIndex((route)=> route.route_id === route_id),
    1
  )
  key.value++;
}
</script>

<template>
  <div class="d-flex flex-wrap gap-3" style="max-height: 50vh; overflow-y: auto" :key="key">
    <div v-if="isShow">
      <UserRouteCard v-for="(route, index) in userRouteDetailList" :route="route" :idx="index" :key="index"
        @remove="removeRoute"
      >
      </UserRouteCard>
    </div>
    <CommonSpinner v-if="!isShow" />
  </div>
</template>

<style scoped></style>
