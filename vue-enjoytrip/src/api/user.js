import { localAxios } from "@/util/http-commons";
import axios from "axios";

const local = localAxios();

// 유저 로그인
async function userConfirm(param, success, fail) {
  await local
    .post("/user/login", null, {
      params: {
        id: param.id,
        pw: param.pw,
      },
    })
    .then(success)
    .catch(fail);
}

async function findById(userid, success, fail) {
  local.defaults.headers["Authorization"] = sessionStorage.getItem("accessToken");
  await local.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  local.defaults.headers["refreshToken"] = sessionStorage.getItem("refreshToken"); //axios header에 refresh-token 셋팅
  await local.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await local.get(`/user/logout/${userid}`).then(success).catch(fail);
}

function signup(user, success, fail){
  local.post(`/user/signup`, user)
  .then(success)
  .catch(fail)
}

function modifyUser(user, success, fail){
  local.post('/user/modify', user)
  .then((response)=>{
    success(response.data)
  })
  .catch((error)=>{
    fail(error)
  })
}

function deleteUser(id, success, fail){
  local.get(`/user/delete/${id}`)
  .then((response)=>{
    success(response)
  })
  .catch((error)=>{
    fail(error)
  })
}

export { userConfirm, findById, tokenRegeneration, logout, signup, modifyUser, deleteUser };
