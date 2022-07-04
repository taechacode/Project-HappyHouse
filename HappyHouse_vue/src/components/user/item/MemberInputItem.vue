<template>
  <b-row>
    <b-col></b-col>
    <b-col cols="8">
      <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
        <b-form class="text-left">
          <b-form-group
            label="아이디:"
            label-for="userid"
            v-if="this.type === 'register'"
          >
            <b-form-input
              id="userid"
              v-model="user.id"
              required
              placeholder="아이디 입력...."
            ></b-form-input>
          </b-form-group>
          <b-form-group label="비밀번호:" label-for="userpwd">
            <b-form-input
              type="password"
              id="userpwd"
              v-model="user.password"
              required
              placeholder="비밀번호 입력...."
            ></b-form-input>
          </b-form-group>
          <b-form-group
            label="비밀번호 확인:"
            label-for="checkpwd"
            v-if="this.type === 'register'"
          >
            <b-form-input
              type="password"
              id="checkpwd"
              v-model="user.checkpassword"
              required
              placeholder="비밀번호 재입력...."
            ></b-form-input>
          </b-form-group>
          <b-form-group label="이름:" label-for="username">
            <b-form-input
              id="username"
              v-model="user.name"
              required
              placeholder="이름 입력...."
            ></b-form-input>
          </b-form-group>
          <b-form-group label="이메일:" label-for="useremail">
            <b-form-input
              id="useremail"
              v-model="user.email"
              required
              placeholder="ex)aaa@ssafy.com"
            ></b-form-input>
          </b-form-group>
          <b-form-group label="전화번호:" label-for="userphone">
            <b-form-input
              id="userphone"
              v-model="user.phone"
              required
              placeholder="ex)000-0000-0000"
            ></b-form-input>
          </b-form-group>
          <b-form-group label="관심지역:" label-for="userarea">
            <b-row>
              <b-col cols="4">
                <b-form-select
                  id="sido"
                  v-model="sidoCode"
                  :options="sidos"
                  @change="gugunList"
                ></b-form-select>
              </b-col>
              <b-col cols="4">
                <b-form-select
                  id="gugun"
                  v-model="gugunCode"
                  :options="guguns"
                  @change="dongList"
                ></b-form-select>
              </b-col>
              <b-col cols="4">
                <b-form-select
                  id="userarea"
                  v-model="user.area"
                  :options="dongs"
                ></b-form-select>
              </b-col>
            </b-row>
          </b-form-group>
          <b-form-group
            label="권한:"
            label-for="userlevel"
            v-if="nowLoginUser.level === 'admin'"
          >
            <b-form-select v-model="user.level" id="userlevel">
              <b-form-select-option value="admin">관리자</b-form-select-option>
              <b-form-select-option value="general">일반</b-form-select-option>
            </b-form-select>
          </b-form-group>
          <b-row class="m-1" v-if="this.type === 'register'">
            <b-button
              type="button"
              variant="primary"
              class="m-1"
              @click="doSignUp"
              >회원가입</b-button
            >
            <b-button
              type="button"
              variant="success"
              class="m-1"
              @click="movePage"
              >취소</b-button
            >
          </b-row>
          <b-row class="m-1" v-else>
            <b-button type="button" variant="dark" class="m-1" @click="doModify"
              >수정</b-button
            >
            <b-button
              type="button"
              variant="danger"
              class="m-1"
              @click="doDelete"
              >회원 탈퇴</b-button
            >
          </b-row>
        </b-form>
      </b-card>
    </b-col>
    <b-col></b-col>
  </b-row>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";
import { delUser, findUser, getInfo, modify, signUp } from "@/api/user";
import { areaInfo } from "@/api/apt.js";

const userStore = "userStore";
const aptStore = "aptStore";

export default {
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      user: {
        id: "",
        password: "",
        checkpassword: "",
        name: "",
        email: "",
        phone: "",
        area: null,
        level: "general",
      },
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(userStore, ["nowLoginUser"]),
    ...mapState(aptStore, ["sidos", "guguns", "dongs"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_DONG_LIST();

    if (this.type === "info") {
      this.user = this.nowLoginUser;
      this.getArea();
    } else if (this.type === "manage") {
      this.getUserInfoById();
    } else {
      this.getSido();
    }
  },
  methods: {
    ...mapActions(userStore, ["userLogout", "refreshToken"]),
    ...mapActions(aptStore, ["getSido", "getGugun", "getDong"]),
    ...mapMutations(aptStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
    ]),
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      const params = {
        sido: this.sidoCode,
        gugun: this.gugunCode,
      };
      if (this.gugunCode) this.getDong(params);
    },
    async getUserInfoById() {
      await getInfo(
        this.$route.params.id,
        ({ data }) => {
          this.user = data.userInfo;
          this.getArea();
        },
        (error) => {
          console.log(error);
          if (error.response.status === 401) {
            console.log("토큰 재발급 필요");
            this.refreshToken(this.nowLoginUser.id);
          }
        },
      );
    },
    async doSignUp() {
      let validation = this.isVaild();
      if (validation) {
        await signUp(
          this.user,
          (response) => {
            if (response.status === 200) {
              alert("회원 가입 완료!");
              this.$router.push({ name: "signIn" });
            } else {
              alert("회원 가입 도중 문제가 발생했습니다.");
            }
          },
          (error) => {
            console.log(error);
          },
        );
      }
    },
    movePage() {
      this.$router.push({ name: "signIn" });
    },
    async doModify() {
      let validation = this.emailCheck() & this.phoneCheck();
      if(validation) {
        await modify(
          this.user,
          (response) => {
            if (response.status === 200) {
              alert("회원 정보 수정 완료!");
              if (this.nowLoginUser.level === "admin") {
                this.$router.push({ name: "admin" });
              }
            } else {
              alert("회원 정보 수정 도중 문제가 발생했습니다.");
            }
          },
          (error) => {
            console.log(error);
            if (error.response.status === 401) {
              console.log("토큰 재발급 필요");
              this.refreshToken(this.nowLoginUser.id);
            }
          },
        );
      }
    },
    async doDelete() {
      if (confirm("정말 탈퇴하시겠습니까?")) {
        await delUser(
          this.user.id,
          (response) => {
            if (response.status === 200) {
              alert("회원 탈퇴 완료!");
              if (this.nowLoginUser.level === "admin") {
                this.$router.push({ name: "admin" });
              } else {
                this.userLogout(this.user.id);
                this.$router.push({ name: "signIn" });
              }
            } else {
              alert("회원 탈퇴 도중 문제가 발생했습니다.");
            }
          },
          (error) => {
            console.log(error);
            if (error.response.status === 401) {
              console.log("토큰 재발급 필요");
              this.refreshToken(this.nowLoginUser.id);
            }
          },
        );
      }
    },
    async idCheck() {
      if (this.user.id.length >= 5) {
        await findUser(this.user.id, ({ data }) => {
          if (data) {
            return true;
          } else {
            alert("사용할 수 없는 아이디입니다.");
            return false;
          }
        }),
          (error) => {
            console.log(error);
          };
      } else {
        alert("아이디는 5자 이상 필수입니다.");
        return false;
      }
    },
    passwordCheck() {
      if (this.user.password === this.user.checkpassword) {
        return true;
      } else {
        alert("비밀번호가 다릅니다.");
        return false;
      }
    },
    emailCheck() {
      var emailRule =
        /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

      if (!emailRule.test(this.user.email)) {
        alert("이메일 형식을 지켜주세요.");
        return false;
      }

      return true;
    },
    phoneCheck() {
      var phoneRule = /^\d{3}-\d{3,4}-\d{4}$/;

      if (!phoneRule.test(this.user.phone)) {
        alert("전화번호 형식을 지켜주세요.");
        return false;
      }

      return true;
    },
    isVaild() {
      if (
        this.idCheck() &&
        this.passwordCheck() &&
        this.emailCheck() &&
        this.phoneCheck()
      ) {
        return true;
      }
      return false;
    },
    async getArea() {
      if (this.user.area != null) {
        await areaInfo(
          this.user.area,
          ({ data }) => {
            this.sidoCode = data.sidoName;
            this.gugunCode = data.gugunName;

            this.getSido();
            this.getGugun(this.sidoCode);
            const params = {
              sido: this.sidoCode,
              gugun: this.gugunCode,
            };
            this.getDong(params);
          },
          (error) => {
            console.log(error);
          },
        );
      } else {
        this.getSido();
      }
    },
  },
};
</script>

<style></style>
