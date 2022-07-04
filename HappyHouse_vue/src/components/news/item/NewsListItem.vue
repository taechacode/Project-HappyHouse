<template>
  <b-tr>
    <b-td>{{ no }}</b-td>
    <b-th class="text-left">
      <a v-bind:href="this.url" target="_blank">{{ title }}</a>
    </b-th>
    <b-td>{{ dsc }}</b-td>
    <b-td>{{ regdate | toDateFormat }}</b-td>
  </b-tr>
</template>

<script>
import { mapState } from "vuex";
import moment from "moment";

const userStore = "userStore";

export default {
  name: "NewsListItem",
  props: {
    no: Number,
    title: String,
    url: String,
    dsc: String,
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
