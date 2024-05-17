import { localAxios } from "@/util/http-commons";
import axios from "axios";
const local = localAxios();

async function searchByKeyword(keyword, success, fail) {
  await local.get(`/attraction/search?keyword=${keyword}`).then(success).catch(fail);
}

async function postRoute(routeList, success, fail) {
  console.log("postRoute(api/map.js) - 루트 저장 확인 \n param : ", routeList);

  const routeInfo = {
    userId: "ssafy",
    contentInfos: routeList,
  };
  console.log("routeInfo : ", JSON.stringify(routeInfo));

  // await local.post("/attraction/route", routeInfo).then(success).catch(fail);

  await local.post(`/attraction/route`, routeInfo).then(success).catch(fail);
}

export { searchByKeyword, postRoute };
