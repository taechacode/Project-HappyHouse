<template>
  <b-container id="searchBarbg" class="pt-4 pb-4">
    <b-row class="m-1 text-center"
      ><b-col
        ><b-form-select
          v-model="sidoCode"
          :options="sidos"
          @change="gugunList"
          id="sidoSelect"
        ></b-form-select></b-col
      ><b-col>
        <b-form-select
          v-model="gugunCode"
          :options="guguns"
          @change="dongList"
          id="gugunSelect"
        ></b-form-select></b-col
      ><b-col
        ><b-form-select
          v-model="dongCode"
          :options="dongs"
        ></b-form-select></b-col
    ></b-row>

    <b-row class="m-1 mt-3 text-center"
      ><b-col>
        <b-form-select v-model="year" :options="yearOptions"></b-form-select>
      </b-col>
      <b-col>
        <b-form-select
          v-model="month"
          :options="monthOptions"
        ></b-form-select></b-col
      ><b-col
        ><b-input v-model="name" placeholder="아파트 이름"></b-input></b-col
    ></b-row>
    <b-row class="mt-4 text-center">
      <b-col></b-col>
      <b-col
        ><b-button
          style="background-color: #636567"
          @click="searchApt"
          class="col-8"
          >조회</b-button
        ></b-col
      >
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import { areaInfo } from "@/api/apt.js";

const aptStore = "aptStore";
const userStore = "userStore";

export default {
  name: "AptSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      year: "2015",
      month: "1",
      name: null,
      yearOptions: [
        { value: "2015", text: "2015년" },
        { value: "2016", text: "2016년" },
        { value: "2017", text: "2017년" },
        { value: "2018", text: "2018년" },
        { value: "2019", text: "2019년" },
        { value: "2020", text: "2020년" },
        { value: "2021", text: "2021년" },
        { value: "2022", text: "2022년" },
      ],
      monthOptions: [
        { value: "1", text: "1월" },
        { value: "2", text: "2월" },
        { value: "3", text: "3월" },
        { value: "4", text: "4월" },
        { value: "5", text: "5월" },
        { value: "6", text: "6월" },
        { value: "7", text: "7월" },
        { value: "8", text: "8월" },
        { value: "9", text: "9월" },
        { value: "10", text: "10월" },
        { value: "11", text: "11월" },
        { value: "12", text: "12월" },
      ],
    };
  },
  computed: {
    ...mapState(aptStore, ["sidos", "guguns", "dongs", "apts"]),
    ...mapState(userStore, ["nowLoginUser"]),
  },
  created() {
    this.CLEAR_APT_LIST();
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_DONG_LIST();
    this.getSido();

    if (this.nowLoginUser.area != null) {
      this.getArea();
    }
  },
  methods: {
    ...mapActions(aptStore, ["getSido", "getGugun", "getDong", "getAptList"]),
    ...mapMutations(aptStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_APT_LIST",
    ]),
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      const params = {
        sido: this.sidoCode,
        gugun: this.gugunCode,
      };
      if (this.gugunCode) this.getDong(params);
    },
    async searchApt() {
      this.CLEAR_APT_LIST();

      if (this.dongCode && this.year && this.month) {
        const params = {
          sido: this.sidoCode,
          gugun: this.gugunCode,
          dong: this.dongCode,
          year: this.year,
          month: this.month,
          name: this.name,
          order: "basic",
          pg: "8",
          cpp: "1",
        };
        await this.getAptList(params);
      }
    },
    async getArea() {
      await areaInfo(
        this.nowLoginUser.area,
        ({ data }) => {
          this.sidoCode = data.sidoName;
          this.gugunCode = data.gugunName;
          this.dongCode = data.dongCode;

          this.getGugun(this.sidoCode);
          const params = {
            sido: this.sidoCode,
            gugun: this.gugunCode,
          };
          this.getDong(params);

          this.year = "2022";
          this.month = "4";

          this.searchApt();
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};
</script>

<style scoped>
b-col {
  padding: 0;
}
b-form-select {
  padding: 0;
  text-align: center;
}
#searchBarbg {
  background-color: #343a40;
}
</style>
