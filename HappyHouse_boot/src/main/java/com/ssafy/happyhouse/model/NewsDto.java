package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "NewsDto", description = "뉴스")
@Data
public class NewsDto {
	@ApiModelProperty(value = "뉴스 번호, PK")
	private int no;
	@ApiModelProperty(value = "뉴스 제목")
	private String title;
	@ApiModelProperty(value = "뉴스 주소")
	private String url;
	@ApiModelProperty(value = "뉴스 내용")
	private String dsc;
	@ApiModelProperty(value = "발행 일자")
	private String regdate;
}
