package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.NewsDto;
import com.ssafy.happyhouse.model.NewsParameterDto;
import com.ssafy.happyhouse.model.service.NewsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/news")
public class NewsController {
	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);
	
	@Autowired
	private NewsService newsService;
	
	@ApiOperation(value = "뉴스를 가져온다.", response = NewsDto.class)
	@GetMapping("{no}")
	public ResponseEntity<NewsDto> getNews(@PathVariable int no){
		logger.debug("get article");
		return new ResponseEntity<NewsDto>(newsService.getNews(no), HttpStatus.OK);
	}
	
	@ApiOperation(value = "뉴스의 전체 목록을 반환한다.", response = NewsDto.class)
	@GetMapping("/list.do")
	public ResponseEntity<List<NewsDto>> listNews(
			@ApiParam(value="게시글을 얻기 위한 부가 정보", required=true) NewsParameterDto newsParameterDto )
					throws Exception {
		logger.debug("listNotice - 호출 : ");
		return new ResponseEntity<List<NewsDto>>(newsService.listNews(newsParameterDto), HttpStatus.OK);
	}

	@ApiOperation(value = "뉴스의 전체 건수를 반환한다.  ", response = List.class)
	@GetMapping("/count.do")
	public ResponseEntity<Integer> listNewsTotalCount() throws Exception {
		logger.debug("getArticleTotalCount - 호출");
		return new ResponseEntity<Integer>(newsService.listNewsTotalCount(), HttpStatus.OK);
	}

}
