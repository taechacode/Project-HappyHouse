<template>
  <b-container>
    <b-row class="p-3" style="background-color: black; opacity: 0.8">
      <b-col
        ><h4 style="color: white">상세 정보: {{ apt.no }}</h4></b-col
      >
    </b-row>
    <b-row class="m-1 mt-4">
      <b-col>
        <b-alert show variant="primary"
          >아파트 이름 : {{ apt.apartmentName }}
        </b-alert>
      </b-col>
    </b-row>
    <b-row class="m-1">
      <b-col>
        <b-alert show variant="secondary"
          >주소 : {{ this.searchParam.sido }} {{ this.searchParam.gugun }}
          {{ apt.dong }} {{ apt.jibun }}</b-alert
        >
      </b-col>
    </b-row>
    <b-row class="m-1">
      <b-col>
        <b-alert show variant="info"
          >거래 금액 :
          {{ (parseInt(apt.dealAmount.replace(",", "")) * 10000) | price }}원
        </b-alert>
      </b-col>
    </b-row>
    <b-row class="m-1">
      <b-col>
        <b-alert show variant="warning"
          >거래 일자 : {{ apt.dealYear }}년 {{ apt.dealMonth }}월
          {{ apt.dealDay }}일</b-alert
        >
      </b-col>
    </b-row>
    <b-row class="m-1">
      <b-col>
        <b-alert show variant="success">층 : {{ apt.floor }}층</b-alert>
      </b-col>
    </b-row>
    <b-row class="m-1">
      <b-col>
        <b-alert show variant="danger">면적 : {{ apt.area }}m^2</b-alert>
      </b-col>
    </b-row>
    <b-row class="m-1">
      <b-col>
        <b-alert show variant="dark">건축 년도 : {{ apt.buildYear }}년</b-alert>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";

const aptStore = "aptStore";

export default {
  name: "AptDetail",
  data() {
    return {};
  },
  props: {
    apt: Object,
  },
  computed: {
    ...mapState(aptStore, ["searchParam"]),
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style></style>
