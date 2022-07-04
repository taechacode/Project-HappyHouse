<template>
  <div v-if="apts && apts.length != 0" class="bv-example-row mt-3">
    <b-table-simple hover responsive>
      <b-thead head-variant="dark">
        <b-tr>
          <b-th @click="searchBy('basic')" style="cursor: pointer">번호</b-th>
          <b-th @click="searchBy('name')" style="cursor: pointer">아파트</b-th>
          <b-th @click="searchBy('jibun')" style="cursor: pointer">주소</b-th>
          <b-th @click="searchBy('amount')" style="cursor: pointer"
            >거래금액</b-th
          >
          <b-th @click="searchBy('date')" style="cursor: pointer"
            >거래일자</b-th
          >
        </b-tr>
      </b-thead>
      <tbody>
        <apt-list-item v-for="(apt, index) in apts" :key="index" :apt="apt" />
      </tbody>
    </b-table-simple>
    <b-pagination
      align="center"
      v-model="currentPage"
      :total-rows="totalRows"
      :per-page="perPage"
    ></b-pagination>
  </div>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>아파트 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import AptListItem from "@/components/apt/AptListItem.vue";
import { mapActions, mapMutations, mapState } from "vuex";

const aptStore = "aptStore";

export default {
  name: "AptList",
  components: {
    AptListItem,
  },
  data() {
    return {
      perPage: 8,
      currentPage: 1,
    };
  },
  computed: {
    ...mapState(aptStore, ["apts", "searchParam", "aptTotalCount"]),
    totalRows() {
      return this.aptTotalCount;
    },
  },
  watch: {
    currentPage: function () {
      this.searchBy(this.searchParam.order);
    },
  },
  methods: {
    ...mapActions(aptStore, ["getAptList"]),
    ...mapMutations(aptStore, ["CLEAR_APT_LIST"]),
    searchBy(order) {
      this.CLEAR_APT_LIST();
      this.searchParam.order = order;
      this.searchParam.cpp = this.currentPage;
      this.searchParam.pg = this.perPage;
      this.getAptList(this.searchParam);
    },
  },
};
</script>

<style scoped></style>
