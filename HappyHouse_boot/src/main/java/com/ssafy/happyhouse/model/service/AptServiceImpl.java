package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.AptDto;
import com.ssafy.happyhouse.model.DongCode;
import com.ssafy.happyhouse.model.mapper.AptMapper;

@Service
public class AptServiceImpl implements AptService {

	@Autowired
	private AptMapper aptMapper;

	@Override
	public List<String> getSido() {
		return aptMapper.getSido();
	}

	@Override
	public List<String> getGugun(String sido) {
		return aptMapper.getGugun(sido);
	}

	@Override
	public List<DongCode> getDong(String sido, String gugun) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("sido", sido);
		map.put("gugun", gugun);
		return aptMapper.getDong(map);
	}

	@Override
	public List<AptDto> getAptList(String dong, String year, String month, String name, String order, int pg, int cpp) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dong", dong);
		map.put("year", year);
		map.put("month", month);
		
		if(name==null) {
			map.put("name", "");
		}else {
			map.put("name", name);
		}

		if (order.equals("name")) {
			map.put("order", "i.apartmentName");
		} else if (order.equals("amount")) {
			map.put("order", "cast(replace(d.dealAmount, ',', '') as unsigned)");
		} else if (order.equals("date")) {
			map.put("order", "d.dealDay");
		} else if (order.equals("jibun")) {
			map.put("order", "i.jibun");
		} else {
			map.put("order", "d.no");
		}
		
		int start = cpp == 0 ? 0 : (cpp - 1) * pg;

		map.put("start", new Integer(start));
		map.put("pg", new Integer(pg));

		return aptMapper.getAptList(map);
	}

	@Override
	public int getAptCount(String dong, String year, String month, String name, String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dong", dong);
		map.put("year", year);
		map.put("month", month);
		
		if(name==null) {
			map.put("name", "");
		}else {
			map.put("name", name);
		}
		
		map.put("order", "d.no");
		
		return aptMapper.getAptCount(map);
	}

	@Override
	public DongCode getArea(String dong) {
		return aptMapper.getArea(dong);
	}

}
