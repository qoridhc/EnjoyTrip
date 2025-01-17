import { ref } from "vue";
import { defineStore } from "pinia";

export const useMenuStore = defineStore(
  "menuStore",
  () => {
    const menuList = ref([
      { name: "회원가입", show: true, routeName: "user-signup" },
      { name: "로그인", show: true, routeName: "user-login" },
      { name: "내정보", show: false, routeName: "user-mypage" },
      { name: "로그아웃", show: false, routeName: "user-logout" },
    ]);

    const changeMenuState = () => {
      menuList.value = menuList.value.map((item) => ({ ...item, show: !item.show }));
    };

    const logoutMenuState = () => {
      menuList.value[0].show = menuList.value[1].show = true;
      menuList.value[2].show = menuList.value[3].show = false;
    };

    return {
      menuList,
      changeMenuState,
      logoutMenuState,
    };
  },
  {
    persist: {
      storage: sessionStorage
    }
  }
);
