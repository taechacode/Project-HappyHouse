package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="NewsParameterDto : 뉴스 파라미터 정보", description = "뉴스 글을 return 받기 위한 부가 정보")
public class NewsParameterDto {
	
	@ApiModelProperty(value="현재 페이지 번호")
	private int pg;
	
	@ApiModelProperty(value="한 페이지당 글 개수")
	private int spp;
	
	@ApiModelProperty(value="페이지 글 시작 번호")
	private int start;
	
	public int getPg() {
		return pg;
	}
	
	public void setPg(int pg) {
		pg = pg == 0 ? 1 : pg;
		this.pg = pg;
	}
	
	public int getSpp() {
		return spp;
	}
	
	public void setSpp(int spp) {
		this.spp = spp;
	}
	
	public int getStart() {
		return start;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
}
