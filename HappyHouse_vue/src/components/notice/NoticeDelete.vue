<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글목록</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col><b-alert show variant="danger">삭제처리중...</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { deleteNotice } from "@/api/notice.js";
import { mapActions, mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "NoticeDelete",
  created() {
    deleteNotice(
      this.$route.params.no,
      ({ data }) => {
        let msg = "삭제 처리 중 문제가 발생했습니다.";
        if (data === "success") {
          msg = "삭제가 완료되었습니다.";
        }
        alert(msg);
        this.$router.push({ name: "NoticeList" });
      },
      (error) => {
        if (error.response.status === 401) {
          console.log("토큰 재발급 필요");
          this.refreshToken(this.nowLoginUser.id);
        }
      },
    );
  },
  computed: {
    ...mapState(userStore, ["nowLoginUser"]),
  },
  methods: {
    ...mapActions(userStore, ["refreshToken"]),
  },
};
</script>

<style></style>
