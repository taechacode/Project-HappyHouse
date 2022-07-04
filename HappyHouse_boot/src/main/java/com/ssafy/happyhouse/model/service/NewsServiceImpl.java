package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.NewsDto;
import com.ssafy.happyhouse.model.NewsParameterDto;
import com.ssafy.happyhouse.model.mapper.NewsMapper;

@Service
public class NewsServiceImpl implements NewsService{
	
	@Autowired
	private NewsMapper newsMapper;
	
	@Override
	public NewsDto getNews(int no) {
		return newsMapper.getNews(no);
	}
	
	@Override
	@Transactional
	public List<NewsDto> listNews(NewsParameterDto newsParameterDto) {
		int start = newsParameterDto.getPg() == 0 ? 0 : (newsParameterDto.getPg() - 1) * newsParameterDto.getSpp();
		newsParameterDto.setStart(start);
		return newsMapper.listNews(newsParameterDto);
	}

	@Override
	@Transactional
	public int listNewsTotalCount() {
		return newsMapper.listNewsTotalCount();
	}
		
}
