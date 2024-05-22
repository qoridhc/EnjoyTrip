<template>
  <div class="container-fluid d-flex flex-column min-vh-100">
    <div class="row flex-grow-1">
      <!-- RoutePlaceSelector.vue  -->
      <div class="col-md-3">
        <!-- Left Section -->
        <div class="border p-4 h-100">
          <div style="font-size: 25px; font-weight: bold">{{ routeStore.sido_name_kor }}</div>
          <div class="pt-2 pb-3" style="font-size: 16px; font-weight: bold">2024.05.22(수) ~ 2024.05.23(목)</div>
          <div class="search">
            <input @keyup.enter="searchPlaces" type="text" v-model="searchKeyword" value="이태원맛집"
              placeholder="장소명을 입력해 주세요" />
            <img class="pt-1" @click="searchPlaces()"
              src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png" />
          </div>
          <div class="mt-3" style="max-height: 70vh; overflow-y: auto">
            <draggable class="dragArea list-group" :list="searchPlaceList"
              :group="{ name: 'people', pull: 'clone', put: false }" :sort="false" item-key="name">
              <template #item="{ element, index }">
                <div class="list-group-item">
                  <PlaceCard :place="element" :key="index" @mouseover="markers[index].setMap(map)"
                    @mouseout="markers[index].setMap()" @click="addNewRoute(index, -1)">
                  </PlaceCard>
                </div>
              </template>
            </draggable>
          </div>
        </div>
      </div>

      <div class="col-md-3">
        <div class="mt-5 d-flex">
          <h4 class="me-auto">{{ selectedPlaceList.length }}</h4>
          <div class="border rounded p-1 me-2 custom-bg-color text-white" @click="getShortestPath()"
            style="font-size: 14px; height: 30px">
            최단경로
          </div>
          <div class="border rounded p-1 custom-bg-color text-white" @click="clearSelectedRoute()"
            style="font-size: 14px; height: 30px">
            초기화
          </div>
        </div>
        <div style="max-height: 80vh; overflow-y: auto; overflow-x: hidden">
          <draggable class="dragArea list-group" :list="selectedPlaceList" group="people" item-key="name"
            @add="(e) => addRouteByDrag(e)" @change="handleDrag">
            <template #item="{ element, index }">
              <div class="list-group-item">
                <RouteCard :place="element" :id="index + 1" :key="index" :move="moveRoute" @click="removeRoute(index)">
                </RouteCard>
              </div>
            </template>
          </draggable>
        </div>

        <div class="ms-auto mt-4 col-md-2 text-center border rounded p-1 me-2 custom-bg-color text-white"
          @click="saveRoute()" v-if="selectedPlaceList.length > 0" data-bs-toggle="modal"
          data-bs-target="#routeSaveModal" style="font-size: 14px; height: 30px">
          경로저장
        </div>

        <RouteSaveModal />
      </div>
      <!-- Right Section -->
      <div id="map" class="col"></div>
    </div>
  </div>
</template>

<script setup>
const props = defineProps({
  param: Object,
});

import PlaceCard from "@/components/trip/PlaceCard.vue";
import RouteCard from "@/components/trip/RouteCard.vue";
import RouteSaveModal from "@/components/trip/RouteSaveModal.vue";

import { onMounted, ref, onUpdated, watch } from "vue";
import draggable from "vuedraggable";
import { useMapStore } from "@/stores/map";
import { useMemberStore } from "@/stores/member";
import { useFooterStore } from "@/stores/footer";
import { useRouteStore } from "@/stores/route";

const routeStore = useRouteStore();

import { storeToRefs } from "pinia";

const mapStore = useMapStore();
const { searchPlaceList, userRouteList, selectedPlaceList } = storeToRefs(mapStore);
const { getPlaceByKeyword, getPlaceBySidoCode } = mapStore;
const { isChanged } = storeToRefs(mapStore);

const memberStore = useMemberStore();
const { userInfo } = memberStore;

const { VITE_KAKAO_MAP_SERVICE_KEY } = import.meta.env;

// footer 고정
useFooterStore().isFixed = false;

onMounted(async () => {
  window.kakao && window.kakao.maps ? initMap() : addScript();


  if (routeStore.sido_name_kor) {

    // 다른 페이지에서 맵으로 넘어올때 시도코드를 기반으로 자동 검색
    await getPlaceBySidoCode(routeStore.sido_code);

    // 다른 페이지에서 넘어올 때 저장된 여행지 리스트를 검색 결과에 추가해서 마커에 같이 표시하기
    // searchPlaceList.value.push(...selectedPlaceList.value)
    searchPlaceList.value = [...selectedPlaceList.value, ...searchPlaceList.value]

    // 검색 결과 기반으로 마커 표시
    displayMarkers();

    // 저장된 여행지 리스트를 기반으로 선 추가
    drawSelectedRouteLine()
  }
});

watch(isChanged, (newList, oldList) => {
  isChanged.value = false;
  if (selectedPlaceList.value.length > 0) drawSelectedRouteLine();
});

var map;

// default 검색
const searchKeyword = ref("");

// 검색 결과 담는 배열
var searchResult = ref([]);

// 생성된 마커들 담는 배열열
var markers = [];

// 초기 맵 생성
const initMap = () => {
  var container = document.getElementById("map");
  var options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 3,
  };
  map = new kakao.maps.Map(container, options);

  //마커추가하려면 객체를 아래와 같이 하나 만든다.
  var marker = new kakao.maps.Marker({
    position: map.getCenter(),
  });
  marker.setMap(map);
};

// kakao api 쓰기위한 초기 설정
const addScript = () => {
  const script = document.createElement("script");
  /* global kakao */
  script.onload = () => kakao.maps.load(this.initMap);
  script.src = `http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${VITE_KAKAO_MAP_SERVICE_KEY}&libraries=services`;
  document.head.appendChild(script);
};

// 검색어 기반으로 장소 검색
const searchPlaces = async () => {
  searchResult.value = [];
  if (!searchKeyword.value.replace(/^\s+|\s+$/g, "")) {
    alert("키워드를 입력해주세요!");
    return false;
  }

  await getPlaceByKeyword(searchKeyword.value, routeStore.sido_code);

  displayMarkers();

  drawSelectedRouteLine()
};

var positions = [];

const displayMarkers = () => {
  positions = [];

  const places = searchPlaceList.value;

  places.forEach((place) => {
    positions.push({ title: place.title, latlng: place.latlng });
  });

  // 기존에 생성된 마커가 있으면 마커 반복문 돌면서 지우기
  if (markers.length > 0) {
    markers.forEach((item) => {
      item.setMap(null);
    });
  }

  markers = [];

  positions.forEach((position, idx) => {
    const marker = new kakao.maps.Marker({
      map,
      position: position.latlng,
      title: position.title,
      // image: markerImage,
    });

    var content =
      '<div class="rounded bg-white overlay_info" style="position: relative; width:200px; white-space: nowrap; overflow: hidden;">';
    content +=
      '    <div style="background-color:#EE4E4E; font-size:13px; padding:4px; display: block; color: white; text-align: center">' +
      `${searchPlaceList.value[idx].title}` +
      "</div>";
    content += '    <div class="d-flex" style="width:200px">';
    content += `        <img class="p-2" src="${searchPlaceList.value[idx].first_image}" style="width:60px; height:60px" alt="">`;
    content += `        <div class="address pt-2 pe-2" style="font-size:12px; width:200px; white-space: pre-wrap;">${searchPlaceList.value[idx].addr1}</div>`;
    content += "    </div>";
    content += '            <div class="close" onclick="closeOverlay()" title="닫기"></div>';
    // content += `<div class="close" onclick="closeOverlay()" style="position: absolute; top: 5px; right: 5px; background-color: transparent; border: none; font-size: 16px; cursor: pointer;">&times;</div>`;
    content += "</div>";

    // 커스텀 오버레이를 생성합니다
    var customOverlay = new kakao.maps.CustomOverlay({
      position: position.latlng,
      content: content,
      xAnchor: 0.5, // 커스텀 오버레이의 x축 위치입니다. 1에 가까울수록 왼쪽에 위치합니다. 기본값은 0.5 입니다
      yAnchor: 1.1, // 커스텀 오버레이의 y축 위치입니다. 1에 가까울수록 위쪽에 위치합니다. 기본값은 0.5 입니다
    });

    kakao.maps.event.addListener(marker, "click", function () {
      customOverlay.setMap(map);
    });

    // kakao.maps.event.addListener(marker, "mouseover", function () {
    //   customOverlay.setMap(map);
    // });

    // kakao.maps.event.addListener(marker, "mouseout", function () {
    //   customOverlay.setMap();
    // });

    // 커스텀 오버레이를 지도에 표시합니다
    // customOverlay.setMap(map);

    // kakao.maps.event.addListener(marker, "mouseover", () => {
    //   infowindow.open(map, marker);
    // });

    // kakao.maps.event.addListener(marker, "mouseout", () => {
    //   infowindow.close(map, marker);
    // });

    markers.push(customOverlay);
  });

  const bounds = positions.reduce((bounds, position) => bounds.extend(position.latlng), new kakao.maps.LatLngBounds());
  var moveLatLon = new kakao.maps.LatLng(33.452613, 126.570888);
  // map.setCenter
  map.setBounds(bounds);
};

// // 인포 윈도우 생성
// const displayInfowindow = (marker, title) => {
//   // var content = '<div style="padding:5px;z-index:1;">' + title + "</div>";
//   // infowindow.setContent(content);
//   // infowindow.open(map, marker);
// };

var drawList = [], // 선을 그어야할 위치 좌표 배열
  polylines = []; // 선을 긋기위한 polyline 객체를 담은 배열

// 선택한 여행지 추가
const addNewRoute = (index, insertPos) => {
  markers[index].setMap(map);

  const selectedPlaceInfo = {
    content_id: searchPlaceList.value[index].content_id,
    title: searchPlaceList.value[index].title,
    addr1: searchPlaceList.value[index].addr1,
    latlng: searchPlaceList.value[index].latlng,
    first_image: searchPlaceList.value[index].first_image,
  };

  // PlaceCard를 클릭해서 추가한경우 -> 맨뒤에 push
  if (insertPos === -1) {
    // 같은 장소 중복 선택 방지
    const isDuplicated = selectedPlaceList.value.some(
      (iter) => iter.content_id === selectedPlaceInfo.content_id
    );

    if (isDuplicated) {
      alert("이미 선택한 장소입니다.");
      return;
    }

    selectedPlaceList.value.push(selectedPlaceInfo);
    drawList.push(searchPlaceList.value[index].latlng);

    // 지도에 표시할 선을 생성합니다
    var polyline = new kakao.maps.Polyline({
      path: drawList, // 선을 구성하는 좌표배열 입니다
      strokeWeight: 5, // 선의 두께 입니다
      strokeColor: "#FF204E", // 선의 색깔입니다
      strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
      strokeStyle: "solid", // 선의 스타일입니다
    });

    polyline.setMap(map);

    // 선 배열에 저장
    polylines.push(polyline);
  } else {

    // 같은 장소 중복 선택 방지
    for (let i = 0; i < selectedPlaceList.value.length; i++) {
      if (i === insertPos) continue;

      if (selectedPlaceList.value[i].content_id === selectedPlaceList.value[insertPos].content_id) {
        alert("이미 선택한 장소입니다.");
        selectedPlaceList.value.splice(insertPos, 1)
        break;
      }
    }

    // PlaceCard를 드래그해서 추가한경우
    // 이미 selectedPlaceList 배열에 추가되어있으므로 따로 insert해줄필요 X
    // drawSelectedRouteLine() 호출해서 다시 선 그어주기만 하면됨
    drawSelectedRouteLine();
  }
};

// 지도에 표시한 선을 초기화하고
// 선택 경로(selectedPlaceList) 기반으로 다시 선을 그어줌
const drawSelectedRouteLine = () => {
  polylines.forEach((polyline) => {
    polyline.setMap(null);
  });

  drawList = [];
  polylines = [];

  selectedPlaceList.value.forEach((item) => {
    drawList.push(item.latlng);

    // 지도에 표시할 선을 생성합니다
    var polyline = new kakao.maps.Polyline({
      path: drawList, // 선을 구성하는 좌표배열 입니다
      strokeWeight: 5, // 선의 두께 입니다
      strokeColor: "#FF204E", // 선의 색깔입니다
      strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
      strokeStyle: "solid", // 선의 스타일입니다
    });

    polyline.setMap(map);

    // 선 배열에 저장
    polylines.push(polyline);
  });
};

const saveRoute = async () => {
  // store/map.js - userRouteList에 현재 선택한 여행 경로 배열 저장 -> 전역 상태관리
  userRouteList.value = selectedPlaceList.value.map((e) => {
    return {
      ...e,
      description: "",
    };
  });
};

// selecteRouteList에서 index 번째 삭제
const removeRoute = (index) => {
  selectedPlaceList.value.splice(index, 1);

  drawSelectedRouteLine();
};

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
// const removeMarker = () => {
//   for (var i = 0; i < markers.length; i++) {
//     markers[i].setMap(null);
//   }
//   markers = [];
// };

// 선택한 경로 모두 초기화
const clearSelectedRoute = () => {
  polylines.forEach((polyline) => {
    polyline.setMap(null);
  });

  selectedPlaceList.value = [];
  drawList = [];
  polylines = [];
};

// 드래그로 여행지를 추가햇을때 콜백함수
const addRouteByDrag = (e) => {
  polylines.forEach((polyline) => {
    polyline.setMap(null);
  });

  // PlaceCard의 인덱스 (추가하려는 장소정보를 가진 배열의 인덱스)
  const preIndex = e.oldIndex;

  // RouteCard의 인덱스 (선택한 경로들을 담는 배열의 몇번째 순서에 새로운 장소를 추가할지 나타내는 인덱스)
  const addedIndex = e.newIndex;

  addNewRoute(preIndex, addedIndex);
};

const handleDrag = (e) => {
  // selectedRouteList 배열 내에서 순서 변화가 일어난 경우
  if (e.moved) {
    drawSelectedRouteLine();
  }
};

// ====== 최단 경로  구하는 로직 ======

const deg2rad = (deg) => {
  return deg * (Math.PI / 180);
};

const distance = (lat1, lon1, lat2, lon2) => {
  const R = 6371; // 지구 반지름 (단위: km)
  const dLat = deg2rad(lat2 - lat1);
  const dLon = deg2rad(lon2 - lon1);
  const a =
    Math.sin(dLat / 2) * Math.sin(dLat / 2) +
    Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
  const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
  const distance = R * c; // 두 지점 간의 거리 (단위: km)
  return distance;
};

let arr = [[]];
let dp = [];
let pos = [];
let visited = [];
let size = 0;

const getShortestPath = () => {
  for (let j = 0; j < polylines.length; j++) polylines[j].setMap(null);

  size = drawList.length;

  arr = Array.from(new Array(size), () => new Array(size).fill(Infinity));
  dp = Array.from({ length: size }, () => Infinity);
  visited = Array.from({ length: size }, () => 0);
  pos = Array.from({ length: size }, () => -1);

  for (let i = 0; i < size - 1; i++) {
    let pos1 = drawList[i];
    for (let j = i + 1; j < size; j++) {
      let pos2 = drawList[j];
      let dist = distance(pos1.La, pos1.Ma, pos2.La, pos2.Ma);

      arr[i][j] = dist;
      arr[j][i] = dist;
    }
  }
  dijkstra();
};

let shorteRouteList = [];

const dijkstra = () => {
  shorteRouteList = [];
  dp[0] = 0;
  for (let i = 0; i < size; i++) {
    let min = Infinity;
    let cur = -1;

    //현재 접점에서 미방문 접점 중 가장 가까운 곳 선택
    for (let j = 0; j < size; j++) {
      if (visited[j]) continue;
      if (min > dp[j]) {
        min = dp[j];
        cur = j;
      }
    }

    //방문 처리 및 최적화
    if (cur == -1) break;
    visited[cur] = true;

    //업데이트
    for (let j = 0; j < size; j++) {
      if (dp[j] > min + arr[cur][j]) dp[j] = min + arr[cur][j];
    }
  }

  for (let i = 0; i < size; i++) {
    let min = Infinity;
    let idx = -1;

    for (let j = 0; j < size; j++) {
      if (min > dp[j]) {
        min = dp[j];
        idx = j;
      }
    }

    if (idx !== -1) {
      drawList[i] = selectedPlaceList.value[idx].latlng;
      shorteRouteList.push(selectedPlaceList.value[idx]);
      dp[idx] = Infinity; // dp에서 해당 요소를 제거합니다.
    }
  }

  // 지도에 표시할 선을 생성합니다
  var polyline = new kakao.maps.Polyline({
    path: drawList, // 선을 구성하는 좌표배열 입니다
    strokeWeight: 5, // 선의 두께 입니다
    strokeColor: "#FF204E", // 선의 색깔입니다
    strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
    strokeStyle: "solid", // 선의 스타일입니다
  });

  selectedPlaceList.value = shorteRouteList;

  // 지도에 선을 표시합니다
  polyline.setMap(map);
  polylines.push(polyline);
};
</script>

<style scoped>
.container-fluid,
.row {
  height: 100%;
}

.search {
  position: relative;
  width: 300px;
}

input {
  width: 100%;
  border: 1px solid #bbb;
  border-radius: 8px;
  padding: 10px 12px;
  font-size: 14px;
}

img {
  position: absolute;
  width: 17px;
  top: 10px;
  right: 12px;
  margin: 0;
}

.custom-bg-color {
  background-color: #a6e3e9;
}
</style>
