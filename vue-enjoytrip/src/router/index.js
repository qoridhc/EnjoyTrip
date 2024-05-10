import { createRouter, createWebHistory } from "vue-router";
import MyRouteView from "@/views/MyRouteView.vue";
import HomeView from "@/views/HomeView.vue";

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
  ],
});

export default router;
