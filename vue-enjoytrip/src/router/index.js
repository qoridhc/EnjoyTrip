import { createRouter, createWebHistory } from "vue-router";
import MyRouteView from "@/views/MyRouteView.vue";
import HomeView from "@/views/HomeView.vue";
import LoginView from "@/views/LoginView.vue";
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
      redirect: { name: 'board-list' },
      children: [
        {
          path: "list",
          name: "board-list",
          component: () => import("@/components/board/BoardList.vue"),
        },
      ]
    },
    {
      path: "/user",
      name: "user",
      children: [
        {
          path: "login",
          name: "login",
          component: LoginView,
        },
        {
          path: "signup",
          name: "signup",
          component: () => import("@/components/user/UserSignup.vue"),
        },
      ],
    },
  ],
});

export default router;
