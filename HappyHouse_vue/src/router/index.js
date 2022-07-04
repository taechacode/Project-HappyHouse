import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "@/views/HomeView.vue";

import NoticeView from "@/views/NoticeView.vue";
import NoticeList from "@/components/notice/NoticeList.vue";
import NoticeRegister from "@/components/notice/NoticeRegister.vue";
import NoticeDetail from "@/components/notice/NoticeDetail.vue";
import NoticeModify from "@/components/notice/NoticeModify.vue";
import NoticeDelete from "@/components/notice/NoticeDelete.vue";

import store from "@/store/index";

Vue.use(VueRouter);

const isLogined = (to, from, next) => {
  const check = store.getters["userStore/loginflag"];
  if (check) {
    next();
  } else {
    alert("로그인이 필요한 페이지입니다.");
    next({ name: "signIn" });
  }
};

const isAdmin = (to, from, next) => {
  const check = store.getters["userStore/checkUserInfo"];
  if (check.level === "admin") {
    next();
  } else {
    alert("권한이 없습니다.");
    next({ name: "NoticeList" });
  }
};

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/MemberView.vue"),
    children: [
      {
        path: "signin",
        name: "signIn",
        component: () => import("@/components/user/MemberLogin.vue"),
      },
      {
        path: "signup",
        name: "signUp",
        component: () => import("@/components/user/MemberRegister.vue"),
      },
      {
        path: "info/:id",
        name: "info",
        beforeEnter: isLogined,
        component: () => import("@/components/user/MemberInfo.vue"),
      },
      {
        path: "admin",
        name: "admin",
        beforeEnter: isLogined,
        component: () => import("@/components/user/MemberAdmin.vue"),
      },
      {
        path: "manage/:id",
        name: "manage",
        beforeEnter: isLogined,
        component: () => import("@/components/user/MemberManage.vue"),
      },
    ],
  },
  {
    path: "/notice",
    name: "NoticeView",
    component: NoticeView,
    redirect: "/notice/list",
    children: [
      {
        path: "list",
        name: "NoticeList",
        component: NoticeList,
      },
      {
        path: "write",
        name: "NoticeRegister",
        beforeEnter: isAdmin,
        component: NoticeRegister,
      },
      {
        path: "detail/:no",
        name: "NoticeDetail",
        beforeEnter: isLogined,
        component: NoticeDetail,
      },
      {
        path: "modify/:no",
        name: "NoticeModify",
        beforeEnter: isAdmin,
        component: NoticeModify,
      },
      {
        path: "delete/:no",
        name: "NoticeDelete",
        beforeEnter: isAdmin,
        component: NoticeDelete,
      },
    ],
  },
  {
    path: "/apt",
    name: "apt",
    beforeEnter: isLogined,
    component: () => import("@/views/AptView.vue"),
  },
  {
    path: "/news",
    name: "news",
    component: () => import("@/views/NewsView.vue"),
    redirect: "/news/list",
    children: [
      {
        path: "list",
        name: "NewsList",
        component: () => import("@/components/news/NewsList.vue"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
