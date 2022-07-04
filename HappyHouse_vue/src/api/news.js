import http from "./http";

async function getNews(no, success, fail) {
  http.defaults.headers["access-token"] =
    sessionStorage.getItem("access-token");
  await http.get(`/news/${no}`).then(success).catch(fail);
}

async function listNews(param, success, fail) {
  await http.get(`/news/list.do`, { params: param }).then(success).catch(fail);
}

async function listNewsTotalCount(success, fail) {
  await http.get(`/news/count.do`).then(success).catch(fail);
}

export { listNews, listNewsTotalCount, getNews };
