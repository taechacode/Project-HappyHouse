package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.UserDto;

public interface UserMapper {
	int findUser(String id);
	int doSignUp(UserDto userDto);
	UserDto doLogin(UserDto userDto);
	List<UserDto> allUserInfo();
	UserDto userInfo(String id);
	int userUpdate(UserDto userDto);
	int userDelete(String id);
	public void saveRefreshToken(Map<String, String> map);
	public void deleteRefreshToken(Map<String, String> map);
	public String getRefreshToken(String id);
}
