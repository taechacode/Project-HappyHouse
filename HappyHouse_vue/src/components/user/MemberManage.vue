<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show
          ><h3>{{ this.$route.params.id }}님의 회원 정보</h3></b-alert
        >
      </b-col>
    </b-row>
    <member-input-item type="manage" />
  </b-container>
</template>

<script>
import MemberInputItem from "@/components/user/item/MemberInputItem.vue";

export default {
  name: "MemberManage",
  components: {
    MemberInputItem,
  },
};
</script>

<style></style>
