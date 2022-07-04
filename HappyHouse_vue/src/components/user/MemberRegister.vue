<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원 가입</h3></b-alert>
      </b-col>
    </b-row>
    <member-input-item type="register" />
  </b-container>
</template>

<script>
import MemberInputItem from "@/components/user/item/MemberInputItem.vue";

export default {
  name: "MemberRegister",
  components: {
    MemberInputItem,
  },
};
</script>

<style></style>
