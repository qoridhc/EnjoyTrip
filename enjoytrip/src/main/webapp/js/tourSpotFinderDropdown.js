let base_url = "https://apis.data.go.kr/B551011/KorService1/";
let service_key =
  "0MlE28SieNgmrLZpEwDl0Bfu6gXN7RP9h0cLJHTSKw2QN1r0xcz6vIHPN6OvctkkTHnq9uN8EWrFAPhU63L2SQ==";

// dropdown
// let region_codes = {
//   서울: 1,
//   인천: 2,
//   대전: 3,
//   대구: 4,
//   광주: 5,
//   부산: 6,
//   울산: 7,
//   세종시: 8,
//   경기도: 9,
//   강원도: 10,
//   충북: 11,
//   충남: 12,
//   경북: 13,
//   경남: 14,
//   전북: 15,
//   전남: 16,
//   제주도: 17,
// };

function register_dropdown_sido() {
  // 드롭다운 리스트 등록(시, 도)
  let dropdown = document.querySelector("#dropdown-sido");

  let region_codes = data.response.body.items.item;

  let innerhtml = ``;
  console.log(region_codes);
  for (let region in region_codes) {
    // innerhtml += `<li><a class="dropdown-item dropdown-item-sido" value=${region_codes[region]}>${region}</a></li>`;
    innerhtml += `<li><a class="dropdown-item dropdown-item-sido" value=${region.code}>${region.name}</a></li>`;
  }
  dropdown.innerHTML = innerhtml;

  // 드롭다운 리스트 버튼 이벤트 리스터 등록(시, 도)
  let dropdown_item = document.querySelectorAll(".dropdown-item.dropdown-item-sido");
  let dropdown_btn = document.querySelector("#dropdown-btn-sido");
  dropdown_item.forEach((item) => {
    item.addEventListener("click", function () {
      //텍스트 변경
      dropdown_btn.text = this.text;

      //구군 리스트 등록
      register_dropdown_gugun(parseInt(this.attributes["value"]["value"]));
    });
  });
}

function register_dropdown_gugun(region_code) {
  //api 호출
  const request_url = `${base_url}areaCode1?numOfRows=100&pageNo=1&MobileOS=WIN&MobileApp=MobileApp&areaCode=${region_code}&_type=json&serviceKey=${service_key}`;

  //const response
  fetch(request_url)
    .then((response) => response.json())
    .then((json) => {
      //드롭다운 리스트 등록(구, 군)
      let dropdown = document.querySelector("#dropdown-gugun");
      let innerhtml = ``;
      let items = json["response"]["body"]["items"]["item"];

      items.forEach((item) => {
        innerhtml += `<li><a class="dropdown-item dropdown-item-gugun" value=${item["rnum"]}>${item["name"]}</a></li>`;
      });

      dropdown.innerHTML = innerhtml;

      // 드롭다운 리스트 버튼 이벤트 리스터 등록(군, 구)
      let dropdown_item = document.querySelectorAll(".dropdown-item.dropdown-item-gugun");
      let dropdown_btn = document.querySelector("#dropdown-btn-gugun");
      dropdown_item.forEach((item) => {
        item.addEventListener("click", function () {
          //텍스트 변경
          dropdown_btn.text = this.text;
        });
      });
    });
}

function register_dropdown_type() {
  // 드롭다운 리스트 버튼 이벤트 리스터 등록(시, 도)

  // console.log("start");

  let dropdown_item = document.querySelectorAll(".dropdown-item.dropdown-item-type");
  let dropdown_btn = document.querySelector("#dropdown-btn-type");

  dropdown_item.forEach((item) => {
    item.addEventListener("click", function () {
      //텍스트 변경
      dropdown_btn.text = this.text;
    });
  });
}

register_dropdown_sido();
register_dropdown_type();
