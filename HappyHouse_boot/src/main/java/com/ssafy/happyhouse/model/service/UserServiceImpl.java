package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public boolean findUser(String id) {
		return userMapper.findUser(id) == 0;
	}

	@Override
	@Transactional
	public boolean doSignUp(UserDto userDto) {
		return userMapper.doSignUp(userDto) == 1;
	}

	@Override
	public UserDto doLogin(UserDto userDto) {
		if(userDto.getId()==null || userDto.getPassword()==null) {
			return null;
		}
		return userMapper.doLogin(userDto);
	}

	@Override
	public List<UserDto> allUserInfo() {
		return userMapper.allUserInfo();
	}

	@Override
	public UserDto userInfo(String id) {
		return userMapper.userInfo(id);
	}

	@Override
	@Transactional
	public boolean userUpdate(UserDto userDto) {
		return userMapper.userUpdate(userDto) == 1;
	}

	@Override
	@Transactional
	public boolean userDelete(String id) {
		return userMapper.userDelete(id) == 1;
	}

	@Override
	@Transactional
	public void saveRefreshToken(String id, String refreshToken) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("token", refreshToken);
		userMapper.saveRefreshToken(map);
	}

	@Override
	public String getRefreshToken(String id) {
		return userMapper.getRefreshToken(id);
	}

	@Override
	@Transactional
	public void deleRefreshToken(String id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("token", null);
		userMapper.deleteRefreshToken(map);
	}

}
