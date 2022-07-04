<template>
  <b-tr>
    <b-td>{{ no }}</b-td>
    <b-th class="text-left">
      <router-link :to="{ name: 'NoticeDetail', params: { no: no } }">{{
        title
      }}</router-link>
    </b-th>
    <b-td>{{ hit }}</b-td>
    <b-td>{{ id }}</b-td>
    <b-td>{{ regdate | toDateFormat }}</b-td>
  </b-tr>
</template>

<script>
import { mapState } from "vuex";
import moment from "moment";

const userStore = "userStore";

export default {
  name: "NoticeListItem",
  props: {
    no: Number,
    id: String,
    title: String,
    hit: Number,
    regdate: String,
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  filters: {
    toDateFormat: function (regtime) {
      return moment(regtime).format("YYYY-MM-DD");
    },
  },
};
</script>

<style></style>
