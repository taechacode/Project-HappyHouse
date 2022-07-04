<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원 관리</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table-simple hover responsive>
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>ID</b-th>
              <b-th>이름</b-th>
              <b-th>이메일</b-th>
              <b-th>전화번호</b-th>
              <b-th>권한 레벨</b-th>
              <b-th></b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <member-list-item
              v-for="user in users"
              :key="user.id"
              v-bind="user"
            />
          </tbody>
        </b-table-simple>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import MemberListItem from "@/components/user/item/MemberListItem.vue";
import { getAllUser } from "@/api/user.js";

export default {
  name: "MemberAdmin",
  components: {
    MemberListItem,
  },
  data() {
    return {
      users: [],
    };
  },
  created() {
    getAllUser(
      ({ data }) => {
        this.users = data;
      },
      (error) => {
        console.log(error);
      },
    );
  },
  methods: {},
};
</script>

<style></style>
