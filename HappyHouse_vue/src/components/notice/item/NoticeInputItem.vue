<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group
          id="id-group"
          label="작성자:"
          label-for="id"
          description="작성자를 입력하세요."
        >
          <b-form-input
            id="id"
            :disabled="isWriterid"
            v-model="article.id"
            type="text"
            required
            placeholder="작성자 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="subject-group"
          label="제목:"
          label-for="title"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="title"
            v-model="article.title"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="article.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-button
          type="submit"
          variant="primary"
          class="m-1"
          v-if="this.type === 'register'"
          >글작성</b-button
        >
        <b-button type="submit" variant="primary" class="m-1" v-else
          >글수정</b-button
        >
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { mapActions, mapState } from "vuex";
import { getNotice, modifyNotice, registerNotice } from "@/api/notice";

const userStore = "userStore";

export default {
  name: "NoticeInputItem",
  data() {
    return {
      article: {
        no: 0,
        id: "",
        title: "",
        content: "",
      },
      isWriterid: false,
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(userStore, ["nowLoginUser"]),
  },
  created() {
    if (this.type === "modify") {
      getNotice(
        this.$route.params.no,
        ({ data }) => {
          this.article = data;
        },
        (error) => {
          console.log(error);
          if (error.response.status === 401) {
            console.log("토큰 재발급 필요");
            this.refreshToken(this.nowLoginUser.id);
          }
        },
      );
      this.isWriterid = true;
    }
    if (this.type === "register") {
      this.article.id = this.nowLoginUser.id;
      this.isWriterid = true;
    }
  },
  methods: {
    ...mapActions(userStore, ["refreshToken"]),
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.article.id &&
        ((msg = "작성자 입력해주세요"), (err = false), this.$refs.id.focus());
      err &&
        !this.article.title &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err &&
        !this.article.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) {
        alert(msg);
        this.$router.push({ name: "NoticeView" });
      } else
        this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.no = 0;
      this.article.title = "";
      this.article.content = "";
      this.$router.push({ name: "NoticeList" });
    },
    registArticle() {
      const params = {
        id: this.article.id,
        title: this.article.title,
        content: this.article.content,
      };

      registerNotice(
        params,
        ({ data }) => {
          let msg = "등록 처리 중 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          if (error.response.status === 401) {
            console.log("토큰 재발급 필요");
            this.refreshToken(this.nowLoginUser.id);
          }
        },
      );
    },
    modifyArticle() {
      const params = {
        no: this.article.no,
        writer_id: this.article.id,
        title: this.article.title,
        content: this.article.content,
      };
      modifyNotice(
        params,
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.$router.push({
            name: "NoticeDetail",
            params: { no: this.article.no },
          });
        },
        (error) => {
          if (error.response.status === 401) {
            console.log("토큰 재발급 필요");
            this.refreshToken(this.nowLoginUser.id);
          }
        },
      );
    },
    moveList() {
      this.$router.push({ name: "NoticeList" });
    },
  },
};
</script>

<style></style>
