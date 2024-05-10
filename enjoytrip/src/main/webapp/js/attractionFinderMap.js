// let region_codes = {
//     '서울': 1,
//     '인천': 2,
//     '대전': 3,
//     '대구': 4,
//     '광주': 5,
//     '부산': 6,
//     '울산': 7,
//     '세종시': 8,
//     '경기도': 9,
//     '강원도': 10,
//     '충북': 11,
//     '충남': 12,
//     '경북': 13,
//     '경남': 14,
//     '전북': 15,
//     '전남': 16,
//     '제주도': 17
// };
// (12:관광지, 14:문화시설, 15:축제공연행사, 25:여행코스, 28:레포츠, 32:숙박, 38:쇼핑, 39:음식점)
let type_codes = {
	관광지: 12,
	문화시설: 14,
	축제공연행사: 15,
	여행코스: 25,
	레포츠: 28,
	숙박: 32,
	쇼핑: 38,
	음식점: 39,
};

const serviceKey =
	"NFeUK2sQrnw6aQjhf%2FZuTt%2FtwlQdtXlZ46C%2F7sIf8l10cLdqCjSx8ADrEGlZyPslfjgX%2Bmehwtbng2SPeaxN3Q%3D%3D";

// 검색 버튼을 누르면..
// 지역, 유형, 검색어 얻기.
// 위 데이터를 가지고 공공데이터에 요청.
// 받은 데이터를 이용하여 화면 구성.
document.getElementById("btn-search").addEventListener("click", () => {
	//let baseUrl = `https://apis.data.go.kr/B551011/KorService1/`;
	// let searchUrl = `https://apis.data.go.kr/B551011/KorService1/searchKeyword1?serviceKey=${serviceKey}&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;
	// let searchUrl = `https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=${serviceKey}&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;

	//let queryString = `serviceKey=${serviceKey}&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;
	//   let areaCode = region_codes[document.getElementById("dropdown-btn-sido").text];
	//   let contentTypeId = type_codes[document.getElementById("dropdown-btn-type").text];
	let areaCode = document.getElementById("search-sido").value;
	let gugunCode = document.getElementById("search-gugun").value; //구, 군
	let keyword = document.getElementById("search-content-id").value;

	console.log(keyword);
	searchUrl = `http://localhost:8080/enjoytrip2/main?action=search&sido=${areaCode}&gugun=${gugunCode}`;
	if (12 <= keyword || keyword <= 25) searchUrl + `&keyword=${keyword}`;

	console.log(searchUrl);

	fetch(searchUrl)
		.then((response) => response.json())
		.then((data) => makeList(data));
});

var positions; // marker 배열.
function makeList(data) {
	console.log(data);

	document.querySelector("table").setAttribute("style", "display: ;");
	let trips = data.body;
	let tripList = ``;
	positions = [];
	trips.forEach((area) => {
		tripList += `
	<tr onclick="moveCenter(${area.mapy}, ${area.mapx});">
		<td><img src="${area.firstimage}" width="100px"></td>
		<td>${area.title}</td>
		<td>${area.addr1}</td>
		<td>${area.mapy}</td>
		<td>${area.mapx}</td>
	</tr>
	`;

		let markerInfo = {
			title: area.title,
			latlng: new kakao.maps.LatLng(area.mapy, area.mapx),
		};
		positions.push(markerInfo);
	});
	document.getElementById("trip-list").innerHTML = tripList;
	displayMarker();
}

// 카카오지도
var mapContainer = document.getElementById("map"), // 지도를 표시할 div
	mapOption = {
		center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
		level: 5, // 지도의 확대 레벨
	};

// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

function displayMarker() {
	// 마커 이미지의 이미지 주소입니다
	var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

	for (var i = 0; i < positions.length; i++) {
		// 마커 이미지의 이미지 크기 입니다
		var imageSize = new kakao.maps.Size(24, 35);

		// 마커 이미지를 생성합니다
		var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
			map: map, // 마커를 표시할 지도
			position: positions[i].latlng, // 마커를 표시할 위치
			title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
			image: markerImage, // 마커 이미지
		});
	}

	// 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다
	map.setCenter(positions[0].latlng);
}

function moveCenter(lat, lng) {
	map.setCenter(new kakao.maps.LatLng(lat, lng));
}

function changeSelect() {
	var select = document.querySelector("#search-sido");
	var sidoCode = select.options[select.selectedIndex].value;
	searchUrl = `http://localhost:8080/enjoytrip2/main?action=attraction-gugun&sido-code=${sidoCode}`;
	fetch(searchUrl)
	.then((response) => response.json())
	.then((data) => mmake(data));
}

function mmake(data) {
	let gugun = data.body;
	let select = document.querySelector("#search-gugun");
	let innerhtml = '<option select value="0">구, 군 선택 </option>';
	let idx = 0;
	gugun.forEach((g) => {
		innerhtml += `<option value=${++idx}>${g[idx]}</option>`;
	})
	select.innerHTML = innerhtml;
}


/*예전 시,도/ 구,군 버튼 셋팅 코드*//*
// index page 로딩 후 전국의 시도 설정.---------------
let areaUrl =
	"https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=" +
	serviceKey +
	"&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json";

// fetch(areaUrl, { method: "GET" }).then(function (response) { return response.json() }).then(function (data) { makeOption(data); });
let sql = "http://localhost:8080/enjoytrip2/main?action=getSido";
//let sql = "localhost:8080/main?action=getGuGun&sido=1";

fetch(sql, { method: "GET" })
	.then((response) => response.json())
	.then((data) => makeOption(data));

function makeOption(data) {
	console.log("makeOption");
	console.log(data);
	let areas = data.response.body.items.item;
	//   console.log(areas);
	let sel = document.getElementById("search-sido");
	areas.forEach((area) => {
		let opt = document.createElement("option");
		opt.setAttribute("value", area.code);
		opt.appendChild(document.createTextNode(area.name));
		sel.appendChild(opt);
	});
}

// --------구, 군 설정---------------
document.getElementById("search-sido").addEventListener("change", function(e) {
	let areaCode = this.value;
	if (areaCode != 0) {
		fetchGugun(areaCode);
	}
});

function fetchGugun(areaCode) {
	let gugunUrl =
		"https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=" +
		serviceKey +
		"&areaCode=" +
		areaCode +
		"&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json";
	fetch(gugunUrl, { method: "GET" })
		.then((response) => response.json())
		.then((data) => makeOption2(data));
}

function makeOption2(data) {
	let areas = data.response.body.items.item;
	let sel = document.getElementById("search-gugun");

	sel.innerHTML = '';
	areas.forEach((area) => {
		let opt = document.createElement("option");
		opt.setAttribute("value", area.code);
		opt.appendChild(document.createTextNode(area.name));
		sel.appendChild(opt);
	});
}*/