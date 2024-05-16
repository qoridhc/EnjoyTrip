import { localAxios } from "@/util/http-commons";
import axios from "axios";

const local = localAxios();

async function searchByKeyword(keyword, success, fail) {
  console.log(keyword);

  await local.get(`/attraction/search?keyword=${keyword}`).then(success).catch(fail);
}

export { searchByKeyword };
