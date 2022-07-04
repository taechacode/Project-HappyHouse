package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.NoticeParameterDto;
import com.ssafy.happyhouse.model.service.NoticeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/notice")
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private NoticeService noticeService;
	
	@ApiOperation(value = "게시글을 가져온다.", response = NoticeDto.class)
	@GetMapping("{no}")
	public ResponseEntity<NoticeDto> getArticle(@PathVariable int no){
		logger.debug("get article");
		noticeService.doHit(no);
		return new ResponseEntity<NoticeDto>(noticeService.getArticle(no), HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시글을 작성한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> doWrite(@RequestBody NoticeDto boardDto){
		logger.debug("write article");
		if(noticeService.doWrite(boardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "게시글을  수정한다.", response = String.class)
	@PutMapping("{no}")
	public ResponseEntity<String> doUpdate(@RequestBody NoticeDto boardDto){
		logger.debug("update article");
		if(noticeService.doUpdate(boardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "게시글을  삭제한다.", response = String.class)
	@DeleteMapping("{no}")
	public ResponseEntity<String> doDelete(@PathVariable int no){
		logger.debug("delete article");
		if(noticeService.doDelete(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "게시글의 전체 목록을 반환한다.", response = NoticeDto.class)
	@GetMapping("/list.do")
	public ResponseEntity<List<NoticeDto>> listNotice(
			@ApiParam(value="게시글을 얻기 위한 부가 정보", required=true) NoticeParameterDto noticeParameterDto )
					throws Exception {
		logger.debug("listNotice - 호출 : ");
		return new ResponseEntity<List<NoticeDto>>(noticeService.listNotice(noticeParameterDto), HttpStatus.OK);
	}

	@ApiOperation(value = "게시글의 전체 건수를 반환한다.  ", response = List.class)
	@GetMapping("/count.do")
	public ResponseEntity<Integer> listNoticeTotalCount() throws Exception {
		logger.debug("getArticleTotalCount - 호출");
		return new ResponseEntity<Integer>(noticeService.listNoticeTotalCount(), HttpStatus.OK);
	}
}
