import { createRouter, createWebHistory } from "vue-router";
import MyRouteView from "@/views/MyRouteView.vue";
import HomeView from "@/views/HomeView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      // path: "/myroute",
      name: "Home",
      component: HomeView,
    },
    {
      path: "/myroute",
      // path: "/myroute",
      name: "myroute",
      component: MyRouteView,
    },
  ],
});

export default router;
