# EnjoyTrip*Vue*구미*4반*허동원\_이성현

## 개발 환경, 사용언어 및 프레임워크

- Windows 11
- Springboot
- Vue.js

## 사용 라이브러리

- axios
- bootstrap
- vue draggable

```
npm i axios bootstrap
npm i -S vuedraggable@next
npm install --save @fortawesome/fontawesome-svg-core @fortawesome/free-solid-svg-icons @fortawesome/vue-fontawesome
npm i pinia-plugin-persistedstate
npm i jwt-decode
npm install sweetalert2
```

## DB 변경사항

05/19(일): resources/enjoytrip_sido_info.sql 실행 필요(모달창 DB 연동)  
05/20(월): resources/enjoytrip_route.sql 실행 필요 (경로 저장 DB 연동)

## log convention

```
함수명(실행 위치): 로그 내용
logging 대상 변수

console.log("writeArticle(board.js): 글작성 함수 정상 실행\n", response)
```
