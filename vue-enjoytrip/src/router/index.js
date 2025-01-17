import { createRouter, createWebHistory } from "vue-router";
import MyRouteView from "@/views/MyRouteView.vue";
import HomeView from "@/views/HomeView.vue";
import UserView from "@/views/UserView.vue";
import BoardView from "@/views/BoardView.vue";

import { storeToRefs } from "pinia";

import { useMemberStore } from "@/stores/member";
import { useMenuStore } from "@/stores/menu";
import SharedRouteView from "@/views/SharedRouteView.vue";

const onlyAuthUser = async (to, from, next) => {
  const memberStore = useMemberStore();
  const { userInfo, isValidToken, isLogin } = storeToRefs(memberStore);
  const { getUserInfo } = memberStore;

  let token = sessionStorage.getItem("accessToken");

  if (userInfo.value != null && token) {
    await getUserInfo(token);
  }
  if (!isValidToken.value || userInfo.value === null) {
    isValidToken.value = isLogin.value = false;
    userInfo.value = null;
    useMenuStore().logoutMenuState();

    next({ name: "user-login" });
  } else {
    next();
  }
};

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "Home",
      component: HomeView,
    },
    {
      path: "/myroute",
      name: "myroute",
      component: MyRouteView,
      beforeEnter: onlyAuthUser,
      props: true,
    },
    {
      path: "/board",
      name: "board",
      component: BoardView,
      redirect: { name: "board-list" },
      children: [
        {
          path: "list",
          name: "board-list",
          component: () => import("@/components/board/BoardList.vue"),
        },
        {
          path: "view/:articleNo",
          name: "board-detail",
          component: () => import("@/components/board/BoardDetail.vue"),
        },
        {
          path: "write",
          name: "board-write",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/board/BoardWrite.vue"),
        },
        {
          path: "modify/:articleNo",
          name: "board-modify",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/board/BoardModify.vue"),
        },
      ],
    },
    {
      path: "/user",
      name: "user",
      component: UserView,
      children: [
        {
          path: "login",
          name: "user-login",
          component: () => import("@/components/user/UserLogin.vue"),
        },
        {
          path: "signup",
          name: "user-signup",
          component: () => import("@/components/user/UserSignup.vue"),
        },
        {
          path: "update",
          name: "user-update",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/user/UserUpdate.vue"),
        },
        {
          path: "mypage",
          name: "user-mypage",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/user/mypage/UserMyPage.vue"),
          redirect: {name: 'user-route'},
          children: [
            {
              path: "",
              name: "user-route",
              component: () => import("@/components/user/mypage/UserRoute.vue"),
            },
            {
              path: "",
              name: "user-shared-route",
              component: () => import("@/components/user/mypage/UserSharedRoute.vue"),
            },
          ],
        },
      ],
    },
    {
      path: "/share",
      name: "shared-route",
      component: SharedRouteView,
    },
  ],
});
export default router;
