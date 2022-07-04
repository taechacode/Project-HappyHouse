<template>
  <div class="bv-example-row mt-3 text-center">
    <h3 class="underline-green">
      <b-icon icon="house-fill"></b-icon> Apt Search Service
    </h3>
    <b-row class="mt-3">
      <b-col cols="7" style="padding: 0px"><ka-kao-map /></b-col>
      <b-col cols="5">
        <apt-search-bar />
        <apt-list />
      </b-col>
    </b-row>
  </div>
</template>
<script>
import AptSearchBar from "@/components/apt/AptSearchBar.vue";
import AptList from "@/components/apt/AptList.vue";
import KaKaoMap from "@/components/apt/kakaoMap/KakaoMap.vue";

export default {
  name: "BoardView",
  components: {
    AptSearchBar,
    AptList,
    KaKaoMap,
  },
};
</script>
<style scoped>
.underline-green {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(67, 208, 97, 0.3) 30%
  );
}
</style>
