import { createRouter, createWebHistory } from "vue-router";
import MyRouteView from "@/views/MyRouteView.vue";
import HomeView from "@/views/HomeView.vue";
import UserView from "@/views/UserView.vue";
import BoardView from "@/views/BoardView.vue";

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
          component: () => import("@/components/board/BoardWrite.vue"),
        },
        {
          path: "modify/:articleNo",
          name: "board-modify",
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
      ],
    },
  ],
});
export default router;
