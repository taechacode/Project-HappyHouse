import http from "./http";

async function login(user, success, fail) {
  await http.post(`/user/login.do`, user).then(success).catch(fail);
}

async function findUser(id, success, fail) {
  await http.get(`/user/find.do/${id}`).then(success).catch(fail);
}

async function getInfo(id, success, fail) {
  http.defaults.headers["access-token"] =
    sessionStorage.getItem("access-token");
  await http.get(`/user/${id}`).then(success).catch(fail);
}

async function signUp(user, success, fail) {
  await http.post(`/user/signup.do`, user).then(success).catch(fail);
}

async function modify(user, success, fail) {
  http.defaults.headers["access-token"] =
    sessionStorage.getItem("access-token");
  await http.put(`/user/${user.id}`, user).then(success).catch(fail);
}

async function delUser(id, success, fail) {
  http.defaults.headers["access-token"] =
    sessionStorage.getItem("access-token");
  await http.delete(`/user/${id}`).then(success).catch(fail);
}

async function getAllUser(success, fail) {
  http.defaults.headers["access-token"] =
    sessionStorage.getItem("access-token");
  await http.get(`/user/list`).then(success).catch(fail);
}

async function refresh(id, success, fail) {
  http.defaults.headers["refresh-token"] =
    sessionStorage.getItem("refresh-token");
  await http.post(`user/refresh.do/${id}`).then(success).catch(fail);
}

async function deleteRefresh(id, success, fail) {
  await http.put(`user/logout.do/${id}`).then(success).catch(fail);
}

export {
  login,
  findUser,
  getInfo,
  signUp,
  modify,
  delUser,
  getAllUser,
  refresh,
  deleteRefresh,
};
