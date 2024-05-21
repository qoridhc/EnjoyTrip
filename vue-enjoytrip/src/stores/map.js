import { ref, computed } from "vue";
import { defineStore } from "pinia";
import { searchByKeyword, getRouteDetail } from "@/api/map";

import { httpStatusCode } from "@/util/http-status";

export const useMapStore = defineStore("mapStore", () => {
  const searchPlaceList = ref([]); // 검색 결과값을 리턴하는 배열
  const selectedPlaceList = ref([]); // 유저가 저장한 경로를 담는 배열
  const userRouteList = ref([]); // 저장 경로 post 요청을 하기 위한 데이터를 담는 배열
  const userRouteDetailList = ref([]); // 저장한 경로 detail GET 결과를 담는 배열
  const currSidoCode = ref("");

  const getPlaceByKeyword = async (keyword) => {
    searchPlaceList.value = [];
    await searchByKeyword(keyword, (response) => {
      if (response.status === httpStatusCode.OK) {
        searchPlaceList.value = response.data;

        searchPlaceList.value = searchPlaceList.value.map((item) => {
          const { name, latitude, longitude, ...rest } = item; // name 속성을 제외한 나머지 속성들을 rest에 할당
          return { ...rest, latlng: new kakao.maps.LatLng(item.latitude, item.longitude) }; // newName 속성 추가
        });
      }
    });
    // await searchByKeyword(keyword).then((res) => console.log(res));
  };

  const fetchGetRouteDetail = async (userId) => {
    await getRouteDetail(
      userId,
      (response) => {
        if (response.status === httpStatusCode.OK) {
          userRouteDetailList.value = response.data;
        }
      },
      (error) => {
        console.log(error);
      }
    );

    // await searchByKeyword(keyword).then((res) => console.log(res));
  };

  return {
    getPlaceByKeyword,
    searchPlaceList,
    selectedPlaceList,
    userRouteList,
    fetchGetRouteDetail,
    userRouteDetailList,
    currSidoCode,
  };
});
