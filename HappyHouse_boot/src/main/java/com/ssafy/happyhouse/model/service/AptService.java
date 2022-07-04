package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.AptDto;
import com.ssafy.happyhouse.model.DongCode;

public interface AptService {
	List<String> getSido();
	List<String> getGugun(String sido);
	List<DongCode> getDong(String sido, String gugun);
	List<AptDto> getAptList(String dong, String year, String month, String name, String order, int pg, int cpp);
	int getAptCount(String dong, String year, String month, String name, String order);
	DongCode getArea(String dong);
}
