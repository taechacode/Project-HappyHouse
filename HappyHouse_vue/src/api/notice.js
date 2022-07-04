import http from "./http";

async function getNotice(no, success, fail) {
  http.defaults.headers["access-token"] =
    sessionStorage.getItem("access-token");
  await http.get(`/notice/${no}`).then(success).catch(fail);
}

async function listNotice(param, success, fail) {
  await http
    .get(`/notice/list.do`, { params: param })
    .then(success)
    .catch(fail);
}

async function listNoticeTotalCount(success, fail) {
  await http.get(`/notice/count.do`).then(success).catch(fail);
}

async function registerNotice(params, success, fail) {
  http.defaults.headers["access-token"] =
    sessionStorage.getItem("access-token");
  await http.post(`/notice`, params).then(success).catch(fail);
}

async function modifyNotice(params, success, fail) {
  http.defaults.headers["access-token"] =
    sessionStorage.getItem("access-token");
  await http.put(`/notice/${params.no}`, params).then(success).catch(fail);
}

async function deleteNotice(no, success, fail) {
  http.defaults.headers["access-token"] =
    sessionStorage.getItem("access-token");
  await http.delete(`/notice/${no}`).then(success).catch(fail);
}

export {
  listNotice,
  listNoticeTotalCount,
  deleteNotice,
  getNotice,
  registerNotice,
  modifyNotice,
};
