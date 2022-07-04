package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.UserDto;

public interface UserService {
	boolean findUser(String id);
	boolean doSignUp(UserDto userDto);
	UserDto doLogin(UserDto userDto);
	List<UserDto> allUserInfo();
	UserDto userInfo(String id);
	boolean userUpdate(UserDto userDto);
	boolean userDelete(String id);
	void saveRefreshToken(String id, String refreshToken);
	String getRefreshToken(String id);
	void deleRefreshToken(String id);
}
