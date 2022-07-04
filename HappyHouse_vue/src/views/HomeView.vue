<template>
  <div id="homebody">
    <b-container class="text-center" id="content">
      <b-row>
        <b-col></b-col>
        <b-col cols="10"
          ><b-row style="height: 200px"></b-row>
          <b-row>
            <b-col></b-col>

            <b-col cols="5"
              ><div>
                <p style="color: white; font-size: 4rem; font-weight: bold">
                  HAPPY HOUSE
                </p>
                <p style="color: white; font-size: 2rem; font-weight: bold">
                  행복한 우리집
                </p>
              </div></b-col
            >

            <b-col></b-col> </b-row
        ></b-col>
        <b-col></b-col>
      </b-row>
    </b-container>
    <div id="cover"></div>
  </div>
</template>

<script>
export default {
  name: "HomeView",
  props: {
    msg: String,
  },
};
</script>

<style scoped>
#content {
  position: absolute;
  z-index: 2;
  left: 50%;
  transform: translate(-50%, 0);
}

#homebody {
  position: relative;
  height: 648px;
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
  opacity: 0.3;
  background-color: black;
  top: 0;
  left: 0;
  z-index: 1;
}
</style>
