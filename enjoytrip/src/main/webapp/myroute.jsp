<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ddc364239cacaf34eca092d84b347a73libraries=services"></script>
<%@ include file="header.jsp" %>

        <div class="col-md-6" style="margin: auto; margin-top: 20px">
            <div class="alert mt-2 text-center fw-bold pw" role="alert" style="border: 1px solid gray;">나만의 여행 경로</div>

            <button onclick="getShortestPath()">최단 경로 찾기</button>
            <!-- 버튼 만들어서 post 요청 -->
            <!-- onClick 메서드 정의:  placeArr를 Json 데이터로 만들어서 Post 요청-->
            <!-- kakao map start -->
            <div id="menu_wrap1"
                style="height: 460px; margin-left: 200px; margin-top: 350px; opacity: 0.7; color: black">
                <div class="option">
                    <div>
                        <form>나만의 여행 경로</form>
                    </div>
                </div>
                <hr>
                <ol id="myRouteList1" style=""></ol>
                <div id="pagination1"></div>
            </div>
            <div class="map_wrap">
                <div id="map" style="width: 100%; height: 500px; position: relative; overflow: hidden;"></div>

                <div id="menu_wrap" class="bg_white" style="margin-left: 700px; height: 500px;">
                    <div class="option">
                        <div>
                            <form onsubmit="searchPlaces(); return false;">
                                키워드 : <input type="text" value="이태원 맛집" id="keyword" size="15">
                                <button type="submit" id="btn-search">검색하기</button>
                            </form>
                        </div>
                    </div>
                    <hr>
                    <ul id="placesList"></ul>
                    <div id="pagination"></div>
                </div>
                <div id="menu_wrap" class="bg_white">
                    <div class="option" >
                        <div style = "height : 30px">
                            <form>내 여행 경로</form>
                        </div>
                    </div>
                    <hr>
                    <ol id="myRouteList" style=""></ol>
                    <div id="pagination"></div>
                </div>
            </div>
        </div>

        <!-- Footer-->
        <footer class="py-4 fixed-bottom mt-auto" style="background-color: #f2d9d6">
            <div class="container px-5">
                <div class="row align-items-center justify-content-between flex-column flex-sm-row">
                    <div class="col-auto">
                        <div class="small m-0 ">Copyright &copy; SSAFY Heodongwon
                            LeeSeongHyeon</div>
                    </div>
                    <div class="col-auto">
                        <a class="link-secondary small" href="#!">Privacy</a> <span class=" mx-1">&middot;</span> <a
                            class="link-secondary small" href="#!">Terms</a> <span class=" mx-1">&middot;</span> <a
                            class="link-secondary small" href="#!">Contact</a>
                    </div>
                </div>
            </div>
        </footer>
        <script type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=773d23c4f5a4f8688d1d15740383ddd7&libraries=services"></script>
        <script>

            // 마커를 담을 배열입니다
            var markers = [];

            // 지도를 표시할 div 
            var mapContainer = document.getElementById('map'),
                mapOption = {
                    center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표 
                    level: 3  												// 지도의 확대 레벨
                };

            // 지도를 생성합니다    
            var map = new kakao.maps.Map(mapContainer, mapOption);

            // 장소 검색 객체를 생성합니다
            var ps = new kakao.maps.services.Places();

            // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
            var infowindow = new kakao.maps.InfoWindow({
                zIndex: 1
            });

            var edges = [];

            // 키워드로 장소를 검색합니다
            searchPlaces();

            // 키워드 검색을 요청하는 함수입니다
            function searchPlaces() {

                var keyword = document.getElementById('keyword').value;

                if (!keyword.replace(/^\s+|\s+$/g, '')) {
                    alert('키워드를 입력해주세요!');
                    return false;
                }

                // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
                ps.keywordSearch(keyword, placesSearchCB);
            }

            // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
            function placesSearchCB(data, status, pagination) {
                if (status === kakao.maps.services.Status.OK) {

                    // 정상적으로 검색이 완료됐으면
                    // 검색 목록과 마커를 표출합니다
                    displayPlaces(data);

                    // 페이지 번호를 표출합니다
                    displayPagination(pagination);

                } else if (status === kakao.maps.services.Status.ZERO_RESULT) {

                    alert('검색 결과가 존재하지 않습니다.');
                    return;

                } else if (status === kakao.maps.services.Status.ERROR) {

                    alert('검색 결과 중 오류가 발생했습니다.');
                    return;

                }
            }

            // 선을 구성하는 좌표 배열입니다. 이 좌표들을 이어서 선을 표시합니다
            var testPath = [];

            let currIdx = 1;

            // 유저가 클릭한 장소 배열
            let placeArr = [[]];

            let polylines = [];

            // 검색 결과 목록과 마커를 표출하는 함수입니다
            function displayPlaces(places) {
            	
            	console.log(places);
            	
                var listEl = document.getElementById('placesList'), menuEl = document
                    .getElementById('menu_wrap'), fragment = document
                        .createDocumentFragment(), bounds = new kakao.maps.LatLngBounds(), listStr = '';

                // 검색 결과 목록에 추가된 항목들을 제거합니다
                removeAllChildNods(listEl);

                // 지도에 표시되고 있는 마커를 제거합니다
                removeMarker();

                for (var i = 0; i < places.length; i++) {
                    // 마커를 생성하고 지도에 표시합니다
                    var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x), marker = addMarker(
                        placePosition, i), itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

                    // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
                    // LatLngBounds 객체에 좌표를 추가합니다
                    bounds.extend(placePosition);

                    let currPos = new kakao.maps.LatLng(places[i].y, places[i].x);

                    // 마커와 검색결과 항목에 mouseover 했을때
                    // 해당 장소에 인포윈도우에 장소명을 표시합니다
                    // mouseout 했을 때는 인포윈도우를 닫습니다
                    (function (marker, title, currPos) {

                        kakao.maps.event.addListener(marker, 'mouseover', function () {
                            displayInfowindow(marker, title);
                        });

                        kakao.maps.event.addListener(marker, 'mouseout', function () {
                            infowindow.close();
                        });

                        itemEl.onmouseover = function () {
                            displayInfowindow(marker, title);
                        };

                        itemEl.onmouseout = function () {
                            infowindow.close();
                        };

                        itemEl.addEventListener("click", function () {

                            const routeList = document.querySelector("#myRouteList");

                            placeArr.push({ title, currPos });
                            
                            console.log({title,currPos});

                            const li = document.createElement("li");
                            const textNode = document.createTextNode(title);
                            li.style.padding = "10px";
                            li.style.margin = "10px";
                            li.style.border = "1px solid gray";
                            li.style.borderRadius = "10px";

                            li.appendChild(textNode);

                            routeList.appendChild(li);
                            testPath.push(currPos);

                            // 지도에 표시할 선을 생성합니다
                            var polyline = new kakao.maps.Polyline({
                                path: testPath, // 선을 구성하는 좌표배열 입니다
                                strokeWeight: 5, // 선의 두께 입니다
                                strokeColor: '#FF204E', // 선의 색깔입니다
                                strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
                                strokeStyle: 'solid' // 선의 스타일입니다
                            });

                            // 지도에 선을 표시합니다 
                            polyline.setMap(map);

                            // 선 배열에 저장
                            polylines.push(polyline);
                        })

                    })(marker, places[i].place_name, new kakao.maps.LatLng(places[i].y, places[i].x));

                    fragment.appendChild(itemEl);
                }

                // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
                listEl.appendChild(fragment);
                menuEl.scrollTop = 0;

                // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
                map.setBounds(bounds);

            }

            // 검색결과 항목을 Element로 반환하는 함수입니다
            function getListItem(index, places) {

                var el = document.createElement('li'), itemStr = '<span class="markerbg marker_'
                    + (index + 1)
                    + '"></span>'
                    + '<div class="info">'
                    + '   <h5>'
                    + places.place_name + '</h5>';

                if (places.road_address_name) {
                    itemStr += '    <span>' + places.road_address_name + '</span>'
                        + '   <span class="jibun gray">' + places.address_name
                        + '</span>';
                } else {
                    itemStr += '    <span>' + places.address_name + '</span>';
                }

                itemStr += '  <span class="tel">' + places.phone + '</span>' + '</div>';

                el.innerHTML = itemStr;
                el.className = 'item';

                return el;
            }

            // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
            function addMarker(position, idx, title) {
                var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
                    imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
                    imgOptions = {
                        spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
                        spriteOrigin: new kakao.maps.Point(0, (idx * 46) + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
                        offset: new kakao.maps.Point(13, 37)
                        // 마커 좌표에 일치시킬 이미지 내에서의 좌표
                    }, markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize,
                        imgOptions), marker = new kakao.maps.Marker({
                            position: position, // 마커의 위치
                            image: markerImage
                        });

                marker.setMap(map); // 지도 위에 마커를 표출합니다
                markers.push(marker); // 배열에 생성된 마커를 추가합니다

                return marker;
            }

            // 지도 위에 표시되고 있는 마커를 모두 제거합니다
            function removeMarker() {
                for (var i = 0; i < markers.length; i++) {
                    markers[i].setMap(null);
                }
                markers = [];
            }

            // 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
            function displayPagination(pagination) {
                var paginationEl = document.getElementById('pagination'), fragment = document
                    .createDocumentFragment(), i;

                // 기존에 추가된 페이지번호를 삭제합니다
                while (paginationEl.hasChildNodes()) {
                    paginationEl.removeChild(paginationEl.lastChild);
                }

                for (i = 1; i <= pagination.last; i++) {
                    var el = document.createElement('a');
                    el.href = "#";
                    el.innerHTML = i;

                    if (i === pagination.current) {
                        el.className = 'on';
                    } else {
                        el.onclick = (function (i) {
                            return function () {
                                pagination.gotoPage(i);
                            }
                        })(i);
                    }

                    fragment.appendChild(el);
                }
                paginationEl.appendChild(fragment);
            }

            // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
            // 인포윈도우에 장소명을 표시합니다
            function displayInfowindow(marker, title) {
                var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

                infowindow.setContent(content);
                infowindow.open(map, marker);
            }

            // 검색결과 목록의 자식 Element를 제거하는 함수입니다
            function removeAllChildNods(el) {
                while (el.hasChildNodes()) {
                    el.removeChild(el.lastChild);
                }
            }

            function distance(lat1, lon1, lat2, lon2) {
                const R = 6371; // 지구 반지름 (단위: km)
                const dLat = deg2rad(lat2 - lat1);
                const dLon = deg2rad(lon2 - lon1);
                const a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                    Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                    Math.sin(dLon / 2) * Math.sin(dLon / 2);
                const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
                const distance = R * c; // 두 지점 간의 거리 (단위: km)
                return distance;
            }

            function deg2rad(deg) {
                return deg * (Math.PI / 180);
            }

            let arr = [[]];
            let dp = [];
            let pos = [];
            let visited = [];
            let size = 0;

            function getShortestPath() {
            	
            	for (let j = 0; j < polylines.length; j++)
                    polylines[j].setMap(null);
            	
                size = testPath.length;

                arr = Array.from(new Array(size), () => new Array(size).fill(Infinity))
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
                
                console.log(arr);
                dijkstra();
            }

            function dijkstra() {
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
                        if (dp[j] > min + arr[cur][j])
                            dp[j] = min + arr[cur][j];
                    }
                }

                let ol = document.getElementById('myRouteList1');

                for (let i = 0; i < size; i++) {
                    let min = Infinity;
                    let idx = -1;

                    for (let j = 0; j < size; j++) {
                        if (min > dp[j]) {
                            min = dp[j];
                            idx = j;
                        }
                    }
                    delete dp[idx++];

                    //선
                    testPath[i] = placeArr[idx].currPos;
                    
                    //출력
                    let li = document.createElement('li');
                    const textNode = document.createTextNode(placeArr[idx].title);
                    li.style.padding = "10px";
                    li.style.margin = "10px";
                    li.style.border = "1px solid gray";
                    li.style.borderRadius = "10px";
                    li.appendChild(textNode);
                    ol.append(li);
                }
                // 지도에 표시할 선을 생성합니다
                var polyline = new kakao.maps.Polyline({
                    path: testPath, // 선을 구성하는 좌표배열 입니다
                    strokeWeight: 5, // 선의 두께 입니다
                    strokeColor: '#FF204E', // 선의 색깔입니다
                    strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
                    strokeStyle: 'solid' // 선의 스타일입니다
                });

                // 지도에 선을 표시합니다 
                polyline.setMap(map);
                polylines.push(polyline);
            }
        </script>

        </body>

        </html>