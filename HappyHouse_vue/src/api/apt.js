import http from "./http";

async function sidoList(success, fail) {
  await http.get(`/apt/sido.do`).then(success).catch(fail);
}

async function gugunList(sido, success, fail) {
  const params = { sido: sido };
  await http.get(`/apt/gugun.do`, { params }).then(success).catch(fail);
}

async function dongList(params, success, fail) {
  await http.get(`/apt/dong.do`, { params }).then(success).catch(fail);
}

async function aptList(params, success, fail) {
  await http.get(`/apt/list.do`, { params }).then(success).catch(fail);
}

async function aptCount(params, success, fail) {
  await http.get(`/apt/count.do`, { params }).then(success).catch(fail);
}

async function areaInfo(dong, success, fail) {
  const params = { dong: dong };
  await http.get(`/apt/area.do`, { params }).then(success).catch(fail);
}

export { sidoList, gugunList, dongList, aptList, aptCount, areaInfo };
