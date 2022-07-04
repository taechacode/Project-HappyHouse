<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>부동산뉴스</h3></b-alert>
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
              <b-th class="col-3">제목</b-th>
              <b-th class="col-3">내용</b-th>
              <b-th class="col-1">발행일자</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <news-list-item
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
      :total-rows="newsTotalCount"
      :per-page="perPage"
    ></b-pagination>
  </b-container>
</template>

<script>
// import { getList } from "@/api/notice.js";
import { listNews, listNewsTotalCount } from "@/api/news.js";
import NewsListItem from "@/components/news/item/NewsListItem";

export default {
  name: "NewsList",
  components: {
    NewsListItem,
  },
  data() {
    return {
      articles: [],
      perPage: 10,
      currentPage: 1,
      newsTotalCount: 0,
      tableFields: [
        {
          key: "no",
          label: "글번호",
          thStyle: { width: "50%" },
        },
      ],
    };
  },
  computed: {},
  watch: {
    currentPage: "getNews",
  },
  created() {
    this.initComponent();
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "NewsRegister" });
    },
    initComponent() {
      this.getNews();
      this.getNewsTotalCount();
    },
    getNews() {
      let param = {
        pg: this.currentPage,
        spp: this.perPage,
      };
      listNews(
        param,
        (response) => {
          this.articles = response.data;
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getNewsTotalCount() {
      listNewsTotalCount(
        (response) => {
          this.newsTotalCount = response.data;
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
