<template>
  <div id="app">
    <navi-bar />
    <router-view />
  </div>
</template>

<script>
import NaviBar from "@/components/layout/HeaderNaviBar.vue";

export default {
  name: "App",
  components: {
    NaviBar,
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Jua&family=Noto+Sans+KR:wght@300&display=swap");

* {
  font-family: "Jua", sans-serif;
}

th,
td,
th > a {
  font-family: "Noto Sans KR", sans-serif;
  font-size: 16.5px;
}

a {
  text-decoration: none;
}

a:hover {
  text-decoration: none;
  color: chartreuse;
}

a.router-link-exact-active {
  color: #3aa4ca;
}
</style>
