<template>
  <div class="container-fluid d-flex flex-column min-vh-100">
    <div class="row flex-grow-1">
      <!-- RoutePlaceSelector.vue  -->
      <div class="col-md-3">
        <!-- Left Section -->
        <div class="border p-4 h-100">
          <div style="font-size: 25px; font-weight: bold">부산</div>
          <div class="pt-2 pb-3" style="font-size: 16px; font-weight: bold">2024.05.10(금) ~ 2024.05.11(토)</div>
          <div class="search">
            <input
              @keyup.enter="searchPlaces"
              type="text"
              v-model="searchKeyword"
              value="이태원맛집"
              placeholder="장소명을 입력해 주세요" />
            <img
              class="pt-1"
              @click="searchPlaces()"
              src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png" />
          </div>
          <div class="mt-3" style="max-height: 70vh; overflow-y: auto">
            <draggable
              class="dragArea list-group"
              :list="searchResult"
              :group="{ name: 'people', pull: 'clone', put: false, move: null }"
              :sort="false"
              @change="log"
              item-key="name">
              <template #item="{ element, index }">
                <div class="list-group-item">
                  <PlaceCard
                    :place="element"
                    :key="index"
                    @mouseover="displayInfowindow(markers[index], positions[index].title)"
                    @mouseout="infowindow.close()"
                    @click="addRoute(index)">
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
          <div
            class="border rounded p-1 me-2 custom-bg-color text-white"
            @click="getShortestPath()"
            style="font-size: 14px; height: 30px">
            최단경로
          </div>
          <div
            class="border rounded p-1 custom-bg-color text-white"
            @click="clearSelectedRoute()"
            style="font-size: 14px; height: 30px">
            초기화
          </div>
        </div>
        <div style="max-height: 80vh; overflow-y: auto; overflow-x: hidden">
          <draggable class="dragArea list-group" :list="selectedPlaceList" group="people" @change="log" item-key="name">
            <template #item="{ element, index }">
              <div class="list-group-item">
                <RouteCard :place="element" :id="index + 1" :key="index" @click="removeRoute(index)"></RouteCard>
              </div>
            </template>
          </draggable>
        </div>
        <div
          class="ms-auto mt-4 col-md-2 text-center border rounded p-1 me-2 custom-bg-color text-white"
          @click="saveRoute()"
          style="font-size: 14px; height: 30px">
          경로저장
        </div>
      </div>
      <!-- Right Section -->
      <div id="map" class="col"></div>
    </div>
  </div>
</template>

<script setup>
import axios from "axios";
// import cheerio from "cheerio";

import PlaceCard from "@/components/trip/PlaceCard.vue";
import RouteCard from "@/components/trip/RouteCard.vue";

import { onMounted, ref } from "vue";
import draggable from "vuedraggable";

const { VITE_KAKAO_MAP_SERVICE_KEY } = import.meta.env;

onMounted(() => {
  console.log("Mounted");
  window.kakao && window.kakao.maps ? initMap() : addScript();
});

var map, ps, infowindow;

// default 검색
var searchKeyword = ref("이태원맛집");

// 검색 결과 담는 배열
/*
searchResult[idx].value :
{
  address_name: "서울 용산구 이태원동 34-149"
  category_group_code: "FD6"
  category_group_name: "음식점"
  category_name: "음식점 > 분식"
  distance: ""id: "1952478679"
  phone: "02-790-7300"
  place_name: "명동교자 이태원점"
  place_url: "http://place.map.kakao.com/1952478679"
  road_address_name: "서울 용산구 녹사평대로 136"
  x: "126.990949104616"
  y: "37.5308561175718"
}
*/
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

  // 장소 검색 객체를 생성합니다
  ps = new kakao.maps.services.Places();

  // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
  infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

  searchPlaces();
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
const searchPlaces = () => {
  var keyword = searchKeyword.value;

  if (!keyword.replace(/^\s+|\s+$/g, "")) {
    alert("키워드를 입력해주세요!");
    return false;
  }

  // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
  ps.keywordSearch(keyword, placesSearchCB);
};

// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
const placesSearchCB = (data, status) => {
  if (status === kakao.maps.services.Status.OK) {
    // 정상적으로 검색이 완료됐으면
    // 검색 목록과 마커를 표출합니다
    searchResult.value = data;

    // Naver Api를 활용해서 장소 제목에 맞는 이미지를 배열에 푸시
    getImg();

    displayMarkers(data);
  } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
    alert("검색 결과가 존재하지 않습니다.");
    return;
  } else if (status === kakao.maps.services.Status.ERROR) {
    alert("검색 결과 중 오류가 발생했습니다.");
    return;
  }
};

// Naver Api로 장소 title을 검색해서 이미지 불러오기
const getImg = async () => {
  for (let i = 0; i < searchResult.value.length; i++) {
    const resp = await getNaver(i);

    searchResult.value[i].imgUrl = resp;
  }
};

// 확장자가 이미지파일(.jpg, .png ... )로 끝나는지 확인
// http://post ... 주소는 이미지 못불러오므로 걸러줌
const isValidImageUrl = (url) => {
  // 시작 주소가 post인지 확인 (네이버 포스트 이미지는 못불러 오므로 걸러줌)
  const postPattern = /^http:\/\/post\./i;

  return url.match(/\.(jpeg|jpg|gif|png)/) != null && !postPattern.test(url);
};

const getNaver = async (i) => {
  return axios
    .get("/v1/search/image?", {
      params: {
        query: searchResult.value[i].place_name,
        display: 4,
      },
      headers: {
        "X-Naver-Client-Id": "4xqEe9q8UpnLzt_zPegz",
        "X-Naver-Client-Secret": "oCZfB1x_b_",
      }, // 인증 헤더 추가
    })
    .then((res) => {
      const ret = res.data.items[0].link;
      for (let i = 1; i < 4; i++) {
        const url = res.data.items[i].link;
        if (isValidImageUrl(url)) return url;
      }
      return ret;
    })
    .catch((err) => console.error(err));
};

//  {title: '명동교자 이태원점', latlng: qa}
var positions = [];

const displayMarkers = (places) => {
  positions = [];

  places.forEach((place) => {
    positions.push({ title: place.place_name, latlng: new kakao.maps.LatLng(place.y, place.x) });
  });

  // 기존에 생성된 마커가 있으면 마커 반복문 돌면서 지우기
  if (markers.length > 0) {
    markers.forEach((item) => {
      item.setMap(null);
    });
  }

  markers = [];

  const imgSrc = "/src/assets/img/like.png";
  const imgSize = new kakao.maps.Size(24, 35);
  // const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

  positions.forEach((position) => {
    // 인포 윈도우 생성
    const infowindow = new kakao.maps.InfoWindow({
      removable: true,
      content: `<div style="padding:5px;">${position.title}</div>`,
    });

    const marker = new kakao.maps.Marker({
      map,
      position: position.latlng,
      title: position.title,
      // image: markerImage,
    });

    kakao.maps.event.addListener(marker, "click", () => {
      infowindow.open(map, marker);
    });

    kakao.maps.event.addListener(marker, "mouseover", () => {
      infowindow.open(map, marker);
    });

    kakao.maps.event.addListener(marker, "mouseout", () => {
      infowindow.close(map, marker);
    });

    markers.push(marker);
  });

  const bounds = positions.reduce((bounds, position) => bounds.extend(position.latlng), new kakao.maps.LatLngBounds());
  map.setBounds(bounds);
};

// 인포 윈도우 생성
const displayInfowindow = (marker, title) => {
  var content = '<div style="padding:5px;z-index:1;">' + title + "</div>";

  infowindow.setContent(content);
  infowindow.open(map, marker);
};

var selectedPlaceList = ref([]);

var testPath = [],
  polylines = [];

// 선택한 여행지 추가
const addRoute = (index) => {
  console.log("selectedRoute : ", selectedPlaceList.value);
  const selectedPlaceInfo = {
    place_name: searchResult.value[index].place_name,
    address_name: searchResult.value[index].address_name,
    currPos: new kakao.maps.LatLng(searchResult.value[index].y, searchResult.value[index].x),
    imgUrl: searchResult.value[index].imgUrl,
  };

  // 같은 장소 중복 선택 방지
  const isDuplicated = selectedPlaceList.value.some(
    (iter) => JSON.stringify(iter) === JSON.stringify(selectedPlaceInfo)
  );

  if (isDuplicated) {
    alert("이미 선택한 장소입니다.");
    return;
  }

  selectedPlaceList.value.push(selectedPlaceInfo);

  testPath.push(new kakao.maps.LatLng(searchResult.value[index].y, searchResult.value[index].x));

  // 지도에 표시할 선을 생성합니다
  var polyline = new kakao.maps.Polyline({
    path: testPath, // 선을 구성하는 좌표배열 입니다
    strokeWeight: 5, // 선의 두께 입니다
    strokeColor: "#FF204E", // 선의 색깔입니다
    strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
    strokeStyle: "solid", // 선의 스타일입니다
  });

  polyline.setMap(map);

  // 선 배열에 저장
  polylines.push(polyline);
};

const saveRoute = () => {};

const removeRoute = (index) => {
  console.log(index);
  selectedPlaceList.value.splice(index, 1);
};

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
// const removeMarker = () => {
//   for (var i = 0; i < markers.length; i++) {
//     markers[i].setMap(null);
//   }
//   markers = [];
// };

const clearSelectedRoute = () => {
  polylines.forEach((polyline) => {
    polyline.setMap(null);
  });

  selectedPlaceList.value = [];
  testPath = [];
  polylines = [];
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

  size = testPath.length;

  arr = Array.from(new Array(size), () => new Array(size).fill(Infinity));
  dp = Array.from({ length: size }, () => Infinity);
  visited = Array.from({ length: size }, () => 0);
  pos = Array.from({ length: size }, () => -1);

  for (let i = 0; i < size - 1; i++) {
    let pos1 = testPath[i];
    for (let j = i + 1; j < size; j++) {
      let pos2 = testPath[j];
      let dist = distance(pos1.La, pos1.Ma, pos2.La, pos2.Ma);

      arr[i][j] = dist;
      arr[j][i] = dist;
    }
  }
  dijkstra();
};

const dijkstra = () => {
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
      testPath[i] = selectedPlaceList.value[idx].currPos;
      dp[idx] = Infinity; // dp에서 해당 요소를 제거합니다.
    }
  }
  // 지도에 표시할 선을 생성합니다
  var polyline = new kakao.maps.Polyline({
    path: testPath, // 선을 구성하는 좌표배열 입니다
    strokeWeight: 5, // 선의 두께 입니다
    strokeColor: "#FF204E", // 선의 색깔입니다
    strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
    strokeStyle: "solid", // 선의 스타일입니다
  });

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
