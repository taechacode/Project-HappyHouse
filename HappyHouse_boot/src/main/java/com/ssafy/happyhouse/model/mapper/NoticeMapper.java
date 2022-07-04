package com.ssafy.happyhouse.model.mapper;

import java.util.List;


import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.NoticeParameterDto;

public interface NoticeMapper {
	List<NoticeDto> getList();
	NoticeDto getArticle(int no);
	int doWrite(NoticeDto boardDto);
	int doUpdate(NoticeDto boardDto);
	int doDelete(int no);
	int doHit(int no);
	List<NoticeDto> listNotice(NoticeParameterDto noticeParameterDto);
	int listNoticeTotalCount();
}
