<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>공지사항</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="articles.length">
        <b-table-simple
          hover
          responsive
          :per-page="perPage"
          :current-page="currentPage"
        >
          <b-thead head-variant="dark">
            <b-tr>
              <b-th class="col-1">글번호</b-th>
              <b-th class="col-5">제목</b-th>
              <b-th class="col-1">조회수</b-th>
              <b-th class="col-2">작성자</b-th>
              <b-th class="col-3">작성일</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <notice-list-item
              v-for="(article, no) in articles"
              :key="no"
              v-bind="article"
            />
          </tbody>
        </b-table-simple>
      </b-col>
    </b-row>
    <b-pagination
      align="center"
      v-model="currentPage"
      :total-rows="noticeTotalCount"
      :per-page="perPage"
    ></b-pagination>
  </b-container>
</template>

<script>
// import { getList } from "@/api/notice.js";
import { listNotice, listNoticeTotalCount } from "@/api/notice.js";
import NoticeListItem from "@/components/notice/item/NoticeListItem";

export default {
  name: "NoticeList",
  components: {
    NoticeListItem,
  },
  data() {
    return {
      articles: [],
      perPage: 10,
      currentPage: 1,
      noticeTotalCount: 0,
    };
  },
  computed: {},
  watch: {
    currentPage: "getNotice",
  },
  created() {
    this.initComponent();
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "NoticeRegister" });
    },
    initComponent() {
      this.getNotice();
      this.getNoticeTotalCount();
    },
    getNotice() {
      let param = {
        pg: this.currentPage,
        spp: this.perPage,
      };
      listNotice(
        param,
        (response) => {
          this.articles = response.data;
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getNoticeTotalCount() {
      listNoticeTotalCount(
        (response) => {
          this.noticeTotalCount = response.data;
        },
        (error) => {
          console.log(error);
          return 0;
        },
      );
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
