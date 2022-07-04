<template>
  <b-tr>
    <b-td>{{ id }}</b-td>
    <b-td>{{ name }} </b-td>
    <b-td>{{ email }}</b-td>
    <b-td>{{ phone }}</b-td>
    <b-td>{{ level }}</b-td>
    <b-td
      ><router-link :to="{ name: 'manage', params: { id: id } }">
        수정</router-link
      ></b-td
    >
  </b-tr>
</template>

<script>
export default {
  name: "MemberListItem",
  props: {
    id: String,
    name: String,
    email: String,
    phone: String,
    area: String,
    level: String,
  },
  filters: {},
};
</script>

<style></style>
