<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>로그인</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-form-group label="아이디:" label-for="loginid">
              <b-form-input
                id="loginid"
                v-model="user.id"
                required
                placeholder="아이디 입력...."
                @keyup.enter="login"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="loginpwd">
              <b-form-input
                type="password"
                id="loginpwd"
                v-model="user.password"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="login"
              ></b-form-input>
            </b-form-group>
            <b-button type="button" variant="primary" class="m-1" @click="login"
              >로그인</b-button
            >
            <b-button
              type="button"
              variant="success"
              class="m-1"
              @click="movePage"
              >회원가입</b-button
            >
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapState } from "vuex";
const userStore = "userStore";

export default {
  name: "MemberLogin",
  data() {
    return {
      user: {
        id: null,
        password: null,
      },
    };
  },
  computed: {
    ...mapState(userStore, ["islogined"]),
  },
  methods: {
    ...mapActions(userStore, ["doLogin", "getUserInfo"]),
    async login() {
      await this.doLogin(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.islogined) {
        await this.getUserInfo(token);
        this.$router.push({ name: "home" });
      }
    },
    movePage() {
      this.$router.push({ name: "signUp" });
    },
  },
};
</script>

<style></style>
