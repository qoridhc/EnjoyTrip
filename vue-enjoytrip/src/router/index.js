import { createRouter, createWebHistory } from "vue-router";
import MyRouteView from "@/views/MyRouteView.vue";
import HomeView from "@/views/HomeView.vue";
import LoginView from "@/views/LoginView.vue";

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
      path: "/user",
      name: "user",
      children: [
        {
          path: "login",
          name: "login",
          component: LoginView,
        },
      ],
    },
  ],
});

export default router;
