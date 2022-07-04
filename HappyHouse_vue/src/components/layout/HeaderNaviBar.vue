<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-navbar-brand href="#">
        <router-link to="/">
          <img
            src="@/assets/happyhouse.png"
            class="d-inline-block align-middle"
            width="100px"
            alt="ssafy"
          />
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#"
            ><router-link :to="{ name: 'home' }" class="link"
              ><b-icon icon="house" font-scale="1.5"></b-icon> 홈</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'NoticeView' }" class="link"
              ><b-icon icon="journal" font-scale="1.5"></b-icon>
              공지사항</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'apt' }" class="link"
              ><b-icon icon="building" font-scale="1.5"></b-icon>
              아파트정보</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'news' }" class="link"
              ><b-icon icon="newspaper" font-scale="1.5"></b-icon>
              부동산뉴스</router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto" id="dropdown2" v-if="islogined">
          <b-nav-text class="m-auto"
            >{{ nowLoginUser.name }}님 환영합니다</b-nav-text
          >
          <b-nav-item-dropdown right>
            <template #button-content>
              <b-icon icon="people" font-scale="2"></b-icon>
            </template>
            <b-dropdown-item href="#" v-if="nowLoginUser.level === 'admin'"
              ><router-link :to="{ name: 'admin' }" class="link"
                ><b-icon icon="people-fill"></b-icon> 회원 관리</router-link
              ></b-dropdown-item
            >
            <b-dropdown-item href="#"
              ><router-link
                :to="{ name: 'info', params: { id: nowLoginUser.id } }"
                class="link"
                ><b-icon icon="person-circle"></b-icon> 회원정보</router-link
              ></b-dropdown-item
            >
            <b-dropdown-item href="#" @click="logout"
              ><b-icon icon="key"></b-icon> 로그아웃</b-dropdown-item
            >
          </b-nav-item-dropdown>
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto" id="dropdown1" v-else>
          <b-nav-text class="m-auto">로그인 해주세요</b-nav-text>
          <b-nav-item-dropdown right>
            <template #button-content>
              <b-icon icon="people" font-scale="2"></b-icon>
            </template>
            <b-dropdown-item href="#"
              ><router-link :to="{ name: 'signUp' }" class="link"
                ><b-icon icon="person-circle"></b-icon> 회원가입</router-link
              ></b-dropdown-item
            >
            <b-dropdown-item href="#"
              ><router-link :to="{ name: 'signIn' }" class="link"
                ><b-icon icon="key"></b-icon> 로그인</router-link
              ></b-dropdown-item
            >
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const userStore = "userStore";

export default {
  name: "HeaderNaviBar",
  computed: {
    ...mapState(userStore, ["nowLoginUser", "islogined"]),
  },
  methods: {
    ...mapActions(userStore, ["userLogout"]),
    logout() {
      this.userLogout(this.nowLoginUser.id);
      this.$router.push({ name: "signIn" });
    },
  },
};
</script>

<style scoped>
#nav-collapse a {
  color: rgba(255, 245, 238, 0.504);
}

#dropdown1 a {
  color: deepskyblue;
}

#dropdown2 a {
  color: deepskyblue;
}
</style>
