package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.AptDto;
import com.ssafy.happyhouse.model.DongCode;
import com.ssafy.happyhouse.model.service.AptService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/apt")
public class AptController {
	private static final Logger logger = LoggerFactory.getLogger(AptController.class);
	
	@Autowired
	private AptService aptService;
	
	@ApiOperation(value = "전국 시도 정보를 반환한다.", response = List.class)
	@GetMapping("/sido.do")
	public ResponseEntity<List<String>> getSido(){
		logger.debug("get Sido");
		return new ResponseEntity<List<String>>(aptService.getSido(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "선택 시도의 구군 정보를 반환한다.", response = List.class)
	@GetMapping("/gugun.do")
	public ResponseEntity<List<String>> getGugun(String sido){
		logger.debug("get Gugun");
		return new ResponseEntity<List<String>>(aptService.getGugun(sido), HttpStatus.OK);
	}
	
	@ApiOperation(value = "선택 구군의 동 정보를 반환한다.", response = List.class)
	@GetMapping("/dong.do")
	public ResponseEntity<List<DongCode>> getDong(String sido, String gugun){
		logger.debug("get Dong");
		return new ResponseEntity<List<DongCode>>(aptService.getDong(sido, gugun), HttpStatus.OK);
	}
	
	@ApiOperation(value = "아파트 목록을 반환한다.", response = List.class)
	@GetMapping("/list.do")
	public ResponseEntity<List<AptDto>> getAptList(String dong, String year, String month, String name, String order, int pg, int cpp){
		logger.debug("get AptList");
		return new ResponseEntity<List<AptDto>>(aptService.getAptList(dong, year, month, name, order, pg, cpp), HttpStatus.OK);
	}
	
	@ApiOperation(value = "아파트 목록을 반환한다.", response = List.class)
	@GetMapping("/count.do")
	public ResponseEntity<Integer> getAptCount(String dong, String year, String month, String name, String order){
		logger.debug("get AptList");
		return new ResponseEntity<Integer>(aptService.getAptCount(dong, year, month, name, order), HttpStatus.OK);
	}
	
	@ApiOperation(value = "동 코드로 지역 정보를 반환한다.", response = DongCode.class)
	@GetMapping("/area.do")
	public ResponseEntity<DongCode> getArea(String dong){
		logger.debug("get Area");
		return new ResponseEntity<DongCode>(aptService.getArea(dong), HttpStatus.OK);
	}
}
