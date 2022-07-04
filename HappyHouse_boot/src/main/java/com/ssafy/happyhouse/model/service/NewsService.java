package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.NewsDto;
import com.ssafy.happyhouse.model.NewsParameterDto;

public interface NewsService {
	NewsDto getNews(int no);
	List<NewsDto> listNews(NewsParameterDto newsParameterDto);
	int listNewsTotalCount();
}
