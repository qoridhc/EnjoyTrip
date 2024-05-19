import { ref, computed } from "vue";
import { defineStore } from "pinia";
import { searchByKeyword } from "@/api/map";

import { httpStatusCode } from "@/util/http-status";

export const useMapStore = defineStore("mapStore", () => {
  const searchPlaceList = ref([]);
  const userRouteList = ref([]);

  const getPlaceByKeyword = async (keyword) => {
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

  return { getPlaceByKeyword, searchPlaceList, userRouteList };
});
