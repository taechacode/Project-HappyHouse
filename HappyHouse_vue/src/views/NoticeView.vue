<template>
  <div id="homebody">
    <b-container
      style="
        background-color: white;
        z-index: 2;
        position: absolute;
        left: 50%;
        transform: translate(-50%, 0);
      "
      class="bv-example-row mt-5 text-center"
    >
      <h3 class="underline-hotpink pt-3">
        <b-icon icon="journals"></b-icon> Notice Service
      </h3>

      <router-view></router-view>
    </b-container>
    <div id="cover"></div>
  </div>
</template>

<script>
export default {
  name: "NoticeView",
};
</script>
<style scoped>
#homebody {
  position: relative;
  height: 1000px;
  background-image: url("@/assets/house.jpg");
  background-repeat: no-repeat;
  background-position: center center;
  background-attachment: scroll;
  background-size: cover;
}
#cover {
  position: absolute;
  height: 100%;
  width: 100%;
  opacity: 0.4;
  background-color: black;
  top: 0;
  left: 0;
  z-index: 1;
}

.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 27, 139, 0.3) 30%
  );
}
</style>
