<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listArticle">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="deleteArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${article.no}.
          ${article.title}</h3><div><h6>${article.id}</div></h6>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { getNotice } from "@/api/notice.js";
import { mapActions, mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "NoticeDetail",
  data() {
    return {
      article: {},
    };
  },
  computed: {
    ...mapState(userStore, ["nowLoginUser"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    this.getArticle();
  },
  methods: {
    ...mapActions(userStore, ["refreshToken"]),
    async getArticle() {
      await getNotice(
        this.$route.params.no,
        ({ data }) => {
          this.article = data;
        },
        (error) => {
          if (error.response.status === 401) {
            console.log("토큰 재발급 필요");
            this.refreshToken(this.nowLoginUser.id);
          }
        },
      );
    },
    listArticle() {
      this.$router.push({ name: "NoticeList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "NoticeModify",
        params: { no: this.article.no },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        this.$router.replace({
          name: "NoticeDelete",
          params: { no: this.article.no },
        });
      }
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style></style>
