package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.AptDto;
import com.ssafy.happyhouse.model.DongCode;

public interface AptMapper {
	List<String> getSido();
	List<String> getGugun(String sido);
	List<DongCode> getDong(Map<String, String> map);
	List<AptDto> getAptList(Map<String, Object> map);
	int getAptCount(Map<String, Object> map);
	DongCode getArea(String dong);
}
