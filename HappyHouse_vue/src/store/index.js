import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

import userStore from "@/store/modules/userStore";
import noticeStore from "@/store/modules/noticeStore";
import aptStore from "@/store/modules/aptStore";
import newsStore from "./modules/newsStore";

const store = new Vuex.Store({
  modules: {
    userStore,
    noticeStore,
    aptStore,
    newsStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});

export default store;
