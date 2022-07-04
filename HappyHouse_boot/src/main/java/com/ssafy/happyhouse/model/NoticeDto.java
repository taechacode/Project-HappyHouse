package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "NoticeDto", description = "게시글")
@Data
public class NoticeDto {
	@ApiModelProperty(value = "글 번호, PK")
	private int no;
	@ApiModelProperty(value = "글 제목")
	private String title;
	@ApiModelProperty(value = "글 내용")
	private String content;
	@ApiModelProperty(value = "조회수")
	private int hit;
	@ApiModelProperty(value = "작성 일자")
	private String regdate;
	@ApiModelProperty(value = "작성자 아이디, FK")
	private String id;
}
