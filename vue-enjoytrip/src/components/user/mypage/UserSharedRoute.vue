<script setup>
import UserRouteCard from "@/components/user/mypage/UserRouteCard.vue";

import { onMounted } from "vue";
import { storeToRefs } from "pinia";
import { useMapStore } from "@/stores/map";
import { useMemberStore } from "@/stores/member";

const mapStore = useMapStore();
const memberStore = useMemberStore();

const { userInfo } = memberStore;
const { fetchGetRouteDetail } = mapStore;
const { userRouteDetailList } = storeToRefs(useMapStore());

const getRouteDetail = async () => {
  const userId = userInfo.id;
  await fetchGetRouteDetail(userId);
  userRouteDetailList.value = userRouteDetailList.value.filter((routeDetail) => routeDetail.shared == true )
};

getRouteDetail();
</script>

<template>
  <div class="d-flex flex-wrap gap-3">
    <div v-if="userRouteDetailList.length > 0">
      <UserRouteCard v-for="(route, index) in userRouteDetailList" :route="route" :idx="index" :key="index">
      </UserRouteCard>
    </div>
  </div>
</template>

<style scoped></style>
