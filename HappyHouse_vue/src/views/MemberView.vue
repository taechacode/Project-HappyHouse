<template>
  <div id="homebody">
    <b-container id="content" class="bv-example-row mt-5 text-center">
      <h3 class="underline-steelblue pt-3">
        <b-icon icon="person-lines-fill"></b-icon> Member Service
      </h3>
      <router-view></router-view>
      <div style="height: 100px"></div>
    </b-container>
    <div id="cover"></div>
  </div>
</template>

<script>
export default {
  name: "MemberView",
};
</script>

<style scoped>
#content {
  background-color: white;
  position: absolute;
  z-index: 2;
  left: 50%;
  transform: translate(-50%, 0);
}

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
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
</style>
