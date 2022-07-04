import { login, getInfo, refresh, deleteRefresh } from "@/api/user";

import jwt_decode from "jwt-decode";

const userStore = {
  namespaced: true,
  state: {
    nowLoginUser: {
      id: "",
      password: "",
      name: "",
      email: "",
      phone: "",
      area: "",
      level: "",
    },
    islogined: false,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.nowLoginUser;
    },
    loginflag: function (state) {
      return state.islogined;
    },
  },
  mutations: {
    SET_LOGIN: (state, is) => {
      state.islogined = is;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.islogined = true;
      state.nowLoginUser = userInfo;
    },
    CLEAR_USER_INFO: (state) => {
      state.nowLoginUser = {
        id: "",
        password: "",
        name: "",
        email: "",
        phone: "",
        area: "",
        level: "",
      };
    },
  },
  actions: {
    async doLogin({ commit }, user) {
      await login(
        JSON.stringify(user),
        (response) => {
          if (response.data.message === "success") {
            let acToken = response.data["access-token"];
            let refToken = response.data["refresh-token"];
            commit("SET_LOGIN", true);
            sessionStorage.setItem("access-token", acToken);
            sessionStorage.setItem("refresh-token", refToken);
          } else {
            commit("SET_LOGIN", false);
            alert("id/password를 확인하세요");
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    async getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);

      await getInfo(
        decode_token.id,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userInfo);
          } else {
            console.log("회원 정보가 없습니다.");
          }
        },
        (error) => {
          console.log(error);
          if (error.response.status === 401) {
            console.log("토큰 재발급 필요");
            this.refreshToken(decode_token.id);
          }
        },
      );
    },
    async userLogout({ commit }, id) {
      deleteRefresh(
        id,
        (response) => {
          if (response.data.message === "success") {
            sessionStorage.removeItem("access-token");
            sessionStorage.removeItem("refresh-token");
            commit("CLEAR_USER_INFO");
            commit("SET_LOGIN", false);
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    async refreshToken({ commit }, id) {
      await refresh(
        id,
        (response) => {
          if (response.data.message === "success") {
            let acToken = response.data["access-token"];
            sessionStorage.setItem("access-token", acToken);
            location.reload();
          }
        },
        (error) => {
          if (error.response.status === 401) {
            deleteRefresh(
              id,
              (response) => {
                if (response.data.message === "success") {
                  console.log("리프레시 토큰 제거 성공");
                } else {
                  console.log("리프레시 토큰 제거 실패");
                }
                alert("다시 로그인 하세요.");
                commit("SET_LOGIN", false);
              },
              (error2) => {
                console.log(error2);
              },
            );
          }
        },
      );
    },
  },
};

export default userStore;
