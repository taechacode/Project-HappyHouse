import { sidoList, gugunList, dongList, aptList, aptCount } from "@/api/apt.js";

const aptStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "시를 선택하세요." }], // 시도 select options
    guguns: [{ value: null, text: "구를 선택하세요." }], // 구군 select options
    dongs: [{ value: null, text: "동을 선택하세요." }], // 동 select options
    searchParam: {
      sido: "",
      gugun: "",
      dong: "",
      year: "",
      month: "",
      order: "",
      name: "",
      pg: "",
      cpp: "",
    },
    apts: [], // 아파트 리스트
    aptTotalCount: 0,
    getAptFlag: false, // map 초기화를 위한 변수
  },
  getters: {},
  mutations: {
    SET_PARAM: (state, param) => {
      state.searchParam = param;
    },
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido, text: sido });
      });
    },
    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun, text: gugun });
      });
    },
    SET_DONG_LIST: (state, dongs) => {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    SET_APT_LIST: (state, apts) => {
      apts.forEach((apt) => {
        state.apts.push(apt);
      });
      state.getAptFlag = true;
    },
    SET_APT_COUNT: (state, count) => {
      state.aptTotalCount = count;
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "시를 선택하세요." }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "구를 선택하세요." }];
    },
    CLEAR_DONG_LIST: (state) => {
      state.dongs = [{ value: null, text: "동을 선택하세요." }];
    },
    CLEAR_APT_LIST: (state) => {
      state.apts = [];
      state.getAptFlag = false;
    },
  },
  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getGugun: ({ commit }, code) => {
      gugunList(
        code,
        ({ data }) => {
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getDong: ({ commit }, params) => {
      dongList(
        params,
        ({ data }) => {
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getAptList: async ({ commit }, params) => {
      commit("SET_PARAM", params);
      await aptCount(
        params,
        ({ data }) => {
          commit("SET_APT_COUNT", data);
        },
        (error) => {
          console.log(error);
        },
      );
      await aptList(
        params,
        ({ data }) => {
          commit("SET_APT_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};

export default aptStore;
