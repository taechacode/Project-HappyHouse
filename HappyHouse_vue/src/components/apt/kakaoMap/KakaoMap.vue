<template>
  <div style="padding: 0">
    <div id="map" style="position: relative; overflow: hidden"></div>
    <ul id="category">
      <li @click="findUtil($event)" :class="btnColor(0)" id="btn0">
        <img src="@/assets/SW8.png" style="width: 30px; height: 30px" /> 지하철
      </li>
      <li @click="findUtil($event)" :class="btnColor(1)" id="btn1">
        <img src="@/assets/HP8.png" style="width: 30px; height: 30px" /> 병원
      </li>
      <li @click="findUtil($event)" :class="btnColor(2)" id="btn2">
        <img src="@/assets/BK9.png" style="width: 30px; height: 30px" /> 은행
      </li>
      <li @click="findUtil($event)" :class="btnColor(3)" id="btn3">
        <img src="@/assets/MT1.png" style="width: 30px; height: 30px" /> 마트
      </li>
      <li @click="findUtil($event)" :class="btnColor(4)" id="btn4">
        <img src="@/assets/SC4.png" style="width: 30px; height: 30px" /> 학교
      </li>
    </ul>
  </div>
</template>

<script>
import { MAP_KEY } from "@/config/index";
import { mapState } from "vuex";

const aptStore = "aptStore";

export default {
  name: "KaKaoMap",
  data() {
    return {
      map: null, // 지도 객체
      markers: [], // 편의시설 마커
      aptmarkers: [], // 아파트 마커
      isActive: [false, false, false, false, false], // 버튼 클릭 여부
      utils: ["SW8", "HP8", "BK9", "MT1", "SC4"], // 편의시설 검색 코드
    };
  },
  computed: {
    ...mapState(aptStore, ["apts", "getAptFlag", "searchParam"]),
    btnColor: function () {
      // 클릭된 버튼 표시
      return (idx) => (this.isActive[idx] ? "on" : "");
    },
  },
  watch: {
    getAptFlag: function () {
      // 아파트 목록을 가져올 때마다 마커 리로드
      this.setMarkerOnMap();
    },
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      // 맵을 불러온 적이 있는 경우
      this.initMap();
    } else {
      const script = document.createElement("script");
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${MAP_KEY}&libraries=services&autoload=false`;
      /*global kakao */
      script.addEventListener("load", () => {
        kakao.maps.load(this.initMap);
      });
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      // 맵 생성
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567),
        level: 4,
      };
      this.map = new kakao.maps.Map(container, options);
    },
    setMarkerOnMap() {
      this.removeAptMarkers();

      // 아파트 마커 표시
      this.aptmarkers = [];
      const geocoder = new kakao.maps.services.Geocoder();

      this.apts.forEach((apt) => {
        var searchWord = `${this.searchParam.sido} ${this.searchParam.gugun} ${apt.dong} ${apt.jibun}`;

        geocoder.addressSearch(searchWord, (result, status) => {
          if (status === kakao.maps.services.Status.OK) {
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            const imgSrc = require(`@/assets/apart.png`),
              imgSize = new kakao.maps.Size(40, 40),
              imgOption = { offset: new kakao.maps.Point(27, 69) };

            const markerImg = new kakao.maps.MarkerImage(
              imgSrc,
              imgSize,
              imgOption,
            );

            var marker = new kakao.maps.Marker({
              position: coords,
              image: markerImg,
            });

            this.aptmarkers.push(marker);
            marker.setMap(this.map);

            const infowindow = new kakao.maps.InfoWindow({
              zIndex: 1,
              content:
                '<div style="padding:5px;font-size:12px;">' +
                apt.apartmentName +
                "</div>",
            });

            kakao.maps.event.addListener(marker, "mouseover", () => {
              infowindow.open(this.map, marker);
            });
            kakao.maps.event.addListener(marker, "mouseout", () => {
              infowindow.close(this.map, marker);
            });

            this.map.setCenter(coords);
          }
        });
      });
    },
    displayMarker(place) {
      //편의시설 마커 표시
      const imgSrc = require(`@/assets/${place.category_group_code}.png`),
        imgSize = new kakao.maps.Size(30, 30),
        imgOption = { offset: new kakao.maps.Point(27, 69) };

      const markerImg = new kakao.maps.MarkerImage(imgSrc, imgSize, imgOption);

      const marker = new kakao.maps.Marker({
        map: this.map,
        position: new kakao.maps.LatLng(place.y, place.x),
        image: markerImg,
      });

      const infowindow = new kakao.maps.InfoWindow({
        zIndex: 1,
        content:
          '<div style="padding:5px;font-size:12px;">' +
          place.place_name +
          "</div>",
      });

      kakao.maps.event.addListener(marker, "mouseover", () => {
        infowindow.open(this.map, marker);
      });
      kakao.maps.event.addListener(marker, "mouseout", () => {
        infowindow.close(this.map, marker);
      });

      marker.setMap(this.map);
      this.markers.push(marker);
    },
    removeMarkers() {
      // 마커 제거
      this.markers.forEach((item) => {
        item.setMap(null);
      });
    },
    removeAptMarkers() {
      // 아파트 마커 제거
      this.aptmarkers.forEach((item) => {
        item.setMap(null);
      });
    },
    findUtil(event) {
      // 편의시설 버튼 클릭 시 이벤트
      const id = event.target.id.charAt(3);
      this.removeMarkers();

      if (!this.isActive[id]) {
        for (let i = 0; i < 5; i++) {
          this.$set(this.isActive, i, false);
        }
        this.$set(this.isActive, id, true);

        const ps = new kakao.maps.services.Places(this.map);
        ps.categorySearch(this.utils[id], this.placesSearchCB, {
          useMapBounds: true,
        });
      } else {
        this.$set(this.isActive, id, false);
      }
    },
    // 키워드로 장소 검색
    placesSearchCB(data, status) {
      if (status === kakao.maps.services.Status.OK) {
        for (let i = 0; i < data.length; i++) {
          this.displayMarker(data[i]);
        }
      }
    },
  },
};
</script>

<style scoped>
#map {
  margin: auto;
  width: 100%;
  height: 700px;
  border: 1px black solid;
}
#category {
  position: absolute;
  top: 20px;
  left: 20px;
  border-radius: 5px;
  border: 1px solid #909090;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
  background: #fff;
  overflow: hidden;
  z-index: 2;
  padding: 0;
  font-size: 15px;
  font-weight: bold;
  width: fit-content;
  height: fit-content;
}
#category li {
  float: left;
  list-style: none;
  width: 100px;
  height: 40px;
  border-right: 1px solid #acacac;
  padding: 6px 0;
  text-align: center;
  cursor: pointer;
}
#category li.on {
  background: rgb(160, 158, 160);
}
#category li:hover {
  background: #ffe6e6;
  border-left: 1px solid #acacac;
  margin-left: -1px;
}
#category li:last-child {
  margin-right: 0;
  border-right: 0;
}
</style>
