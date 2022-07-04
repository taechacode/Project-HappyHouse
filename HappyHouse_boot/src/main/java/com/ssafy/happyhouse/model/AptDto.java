package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

//참조=> https://goldenrod-paddleboat-545.notion.site/760cc1862be042709f2a22b3c7625fd6
@ApiModel(value = "AptDto", description = "아파트 정보, 거래 정보")
@Data
public class AptDto {
	// houseinfo
	@ApiModelProperty(value = "아파트 코드, PK, housedeal FK")
	private long aptCode;
	@ApiModelProperty(value = "건축 년도")
	private int buildYear;
	@ApiModelProperty(value = "도로명")
	private String roadName;
	@ApiModelProperty(value = "도로명 건물 본번호 코드")
	private String roadNameBonbun;
	@ApiModelProperty(value = "도로명 건물 부번호 코드")
	private String roadNameBubun;
	@ApiModelProperty(value = "도로명 일련번호 코드")
	private String roadNameSeq;
	@ApiModelProperty(value = "도로명 지상지하 코드")
	private String roadNameBasementCode;
	@ApiModelProperty(value = "도로명 코드")
	private String roadNameCode;
	@ApiModelProperty(value = "법정동")
	private String dong;
	@ApiModelProperty(value = "법정동 본번 코드")
	private String bonbun;
	@ApiModelProperty(value = "법정동 부번 코드")
	private String bubun;
	@ApiModelProperty(value = "법정동 시군구 코드")
	private String sigunguCode;
	@ApiModelProperty(value = "법정동 읍면동 코드")
	private String eumyndongCode;
	@ApiModelProperty(value = "법정동 지번 코드")
	private String dongCode;
	@ApiModelProperty(value = "????")
	private String landCode;
	@ApiModelProperty(value = "아파트 이름")
	private String apartmentName;
	@ApiModelProperty(value = "지번")
	private String jibun;
	@ApiModelProperty(value = "위도")
	private String lng;
	@ApiModelProperty(value = "경도")
	private String lat;
	
	// housedeal
	@ApiModelProperty(value = "거래 번호, PK")
	private long no;
	@ApiModelProperty(value = "거래 금액")
	private String dealAmount;
	@ApiModelProperty(value = "거래 년")
	private int dealYear;
	@ApiModelProperty(value = "거래 월")
	private int dealMonth;
	@ApiModelProperty(value = "거래 일")
	private int dealDay;
	@ApiModelProperty(value = "면적")
	private String area;
	@ApiModelProperty(value = "층")
	private String floor;
	@ApiModelProperty(value = "해제 여부")
	private String cancelDealType;
}
