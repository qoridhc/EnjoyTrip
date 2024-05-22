import { localAxios } from "@/util/http-commons";

const local = localAxios();

async function searchByKeyword(keyword, success, fail) {
  await local.get(`/attraction/search?keyword=${keyword}`).then(success).catch(fail);
}

async function searchByContentId(contentId, success, fail) {
  await local.get(`/attraction/search/content/${contentId}`).then(success).catch(fail);
}

async function postRoute(routeList, success, fail) {
  console.log("postRoute(api/map.js) - 루트 저장 post 데이터 확인\nrouteInfo : ", routeList);
  // await local.post("/attraction/route", routeInfo).then(success).catch(fail);

  await local.post(`/attraction/route`, routeList).then(success).catch(fail);
}

async function getRouteDetail(userId, success, fail) {
  await local.get(`/attraction/route?userId=${userId}`).then(success).catch(fail);
}

function getSharedRoute(page, pageSize, success, fail) {
  local.get(`/attraction/route/shared?page=${page}&pageSize=${pageSize}`)
  .then((response)=>{
    success(response.data)
  })
  .catch((error)=>{
    fail(error)
  });
}

async function searchBySidoCode(sido_code, success, fail){
  await local.get(`/attraction/search/sido/${sido_code}`).then(success).catch(fail);
}

export { searchByKeyword, postRoute, getRouteDetail, searchByContentId, getSharedRoute, searchBySidoCode };
