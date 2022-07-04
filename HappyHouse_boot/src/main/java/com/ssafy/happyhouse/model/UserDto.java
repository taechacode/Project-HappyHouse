package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "UserDto", description = "회원 정보")
@Data
public class UserDto {
	@ApiModelProperty(value = "아이디, PK")
	private String id;
	@ApiModelProperty(value = "비밀번호")
	private String password;
	@ApiModelProperty(value = "이름")
	private String name;
	@ApiModelProperty(value = "이메일")
	private String email;
	@ApiModelProperty(value = "전화번호")
	private String phone;
	@ApiModelProperty(value = "관심 지역")
	private String area;
	@ApiModelProperty(value = "권한 레벨")
	private String level;

}
