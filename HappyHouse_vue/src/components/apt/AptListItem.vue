<template>
  <b-tr
    class="m-2"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    @click="modalRender"
    :class="{ 'mouse-over-bgcolor': isColor }"
    style="height: 50px; cursor: pointer"
  >
    <b-td>{{ noParsing }}</b-td>
    <b-td>{{ apt.apartmentName }}</b-td>
    <b-td>{{ apt.dong }} {{ apt.jibun }}</b-td>
    <b-td>{{ apt.dealAmount }}만원</b-td>
    <b-td>{{ apt.dealYear }}.{{ apt.dealMonth }}.{{ apt.dealDay }}</b-td>
  </b-tr>
</template>

<script>
import AptDetail from "@/components/apt/AptDetail.vue";

export default {
  name: "AptListItem",
  data() {
    return {
      isColor: false,
    };
  },
  computed: {
    noParsing() {
      return this.apt.no.toString().substr(10);
    },
  },
  props: {
    apt: Object,
  },
  methods: {
    modalRender() {
      this.$modal.show(
        AptDetail,
        {
          modal: this.$modal,
          apt: this.apt,
        },
        {
          name: "aptdetailmodal",
          width: "750px",
          height: "600px",
          draggable: true,
        },
      );
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
