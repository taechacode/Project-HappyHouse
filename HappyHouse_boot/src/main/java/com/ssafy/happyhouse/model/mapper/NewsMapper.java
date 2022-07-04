package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.NewsDto;
import com.ssafy.happyhouse.model.NewsParameterDto;

public interface NewsMapper {
	NewsDto getNews(int no);
	List<NewsDto> listNews(NewsParameterDto newsParameterDto);
	int listNewsTotalCount();
}
