<script setup>
import { useMenuStore } from "@/stores/menu";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";

const menuStore = useMenuStore();
const memberStore = useMemberStore();

// 반응형을 유지하면서 스토어에서 속성을 추출하려면, storeToRefs()를 사용
// https://pinia.vuejs.kr/core-concepts/
const { menuList } = storeToRefs(menuStore);
const { changeMenuState } = menuStore;

const { userLogout } = memberStore;

const logout = () => {
  userLogout();
  changeMenuState();
};
</script>

<template>
  <nav class="fixed-top navbar navbar-light navbar-expand-lg" style="font-family: 'NotoSansKR-Regular'">
    <div class="container px-5">
      <router-link class="navbar-brand" :to="{ name: 'Home' }">Enjoy Trip</router-link>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
          <li class="nav-item"><router-link class="nav-link" :to="{ name: 'board' }">여행정보공유</router-link></li>
          <li class="nav-item">
            <router-link class="nav-link" :to="{ name: 'shared-route' }">플랜 둘러보기</router-link>
          </li>

          <template v-for="menu in menuList" :key="menu.routeName">
            <template v-if="menu.show">
              <template v-if="menu.routeName === 'user-logout'">
                <li class="nav-item">
                  <router-link to="/" @click.prevent="logout" class="nav-link">{{ menu.name }}</router-link>
                </li>
              </template>

              <template v-else>
                <li class="nav-item">
                  <router-link :to="{ name: menu.routeName }" class="nav-link">{{ menu.name }}</router-link>
                </li>
              </template>
            </template>
          </template>
        </ul>
      </div>
    </div>
  </nav>
</template>

<style scoped></style>
