<template>
  <div class="container-fluid d-flex flex-column min-vh-100">
    <div class="row flex-grow-1">
      <!-- RoutePlaceSelector.vue  -->
      <div class="col-md-3">
        <!-- Left Section -->
        <div class="border p-4 h-100">
          <div style="font-size: 25px; font-weight: bold">부산</div>
          <div class="pt-2 pb-3" style="font-size: 16px; font-weight: bold">2024.05.10(금) ~ 2024.05.11(토)</div>
          <!-- <div class="pt-2 pb-2">장소 선택</div> -->
          <div class="search">
            <input
              @keyup.enter="searchPlaces"
              type="text"
              v-model="searchKeyword"
              value="이태원맛집"
              placeholder="장소명을 입력해 주세요" />
            <img
              @click="searchPlaces()"
              src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png" />
          </div>
          <!-- displayInfowindow(makers[index], searchPlace.title) -->
          <!-- displayInfowindow(makers[$event], searchResult[$event].place_name) -->
          <div class="mt-3" style="max-height: 70vh; overflow-y: auto">
            <RouteCard
              :place="searchPlace"
              v-for="(searchPlace, index) in searchResult"
              :key="index"
              @mouseover="displayInfowindow(markers[index], positions[index].title)"
              @mouseout="infowindow.close()">
              {{ searchPlace }}
            </RouteCard>
          </div>
        </div>
      </div>

      <!-- KaKaoMap.vue -->
      <!-- <div class="col-md-2">22</div> -->
      <!-- Right Section -->
      <!-- <KakaoMap class="h-100" /> -->
      <div id="map" class="col-md-9"></div>
    </div>
  </div>
</template>

<script setup>
// import RoutePlaceSelector from "@/components/trip/RoutePlaceSelector.vue";
import RouteCard from "@/components/trip/RouteCard.vue";
import { onMounted, ref } from "vue";

onMounted(() => {
  window.kakao && window.kakao.maps ? initMap() : addScript();
});

var map, ps, infowindow;

var markers = [];

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

const addScript = () => {
  const script = document.createElement("script");
  /* global kakao */
  script.onload = () => kakao.maps.load(this.initMap);
  script.src =
    "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=ddc364239cacaf34eca092d84b347a73&libraries=services";
  document.head.appendChild(script);
};

var searchKeyword = ref("이태원맛집");
var searchResult = ref([]);

const searchPlaces = () => {
  var keyword = searchKeyword.value;

  console.log(keyword);

  if (!keyword.replace(/^\s+|\s+$/g, "")) {
    alert("키워드를 입력해주세요!");
    return false;
  }

  // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
  ps.keywordSearch(keyword, placesSearchCB);
};

// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
const placesSearchCB = (data, status, pagination) => {
  if (status === kakao.maps.services.Status.OK) {
    // 정상적으로 검색이 완료됐으면
    // 검색 목록과 마커를 표출합니다

    searchResult.value = data;

    displayMarkers(data);

    // displayPlaces(data);
    // // 페이지 번호를 표출합니다
    // displayPagination(pagination);
  } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
    alert("검색 결과가 존재하지 않습니다.");
    return;
  } else if (status === kakao.maps.services.Status.ERROR) {
    alert("검색 결과 중 오류가 발생했습니다.");
    return;
  }
};

var positions = [];

const displayMarkers = (places) => {
  console.log("displaceMarkers");
  // const bounds = new kakao.maps.LatLngBounds();

  console.log(places);

  positions = [];

  places.forEach((place) => {
    positions.push({ title: place.place_name, latlng: new kakao.maps.LatLng(place.y, place.x) });
  });

  console.log("forEach 이후");
  console.log(positions);

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

  // displayInfowindow(markers[0], positions[0].title);
};

const displayInfowindow = (marker, title) => {
  console.log("실행");
  console.log(marker);
  console.log(title);

  var content = '<div style="padding:5px;z-index:1;">' + title + "</div>";

  infowindow.setContent(content);
  infowindow.open(map, marker);
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
</style>
