package com.ssafy.happyhouse.model.service;

import java.util.List;


import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.NoticeParameterDto;

public interface NoticeService {
	List<NoticeDto> getList();
	NoticeDto getArticle(int no);
	boolean doWrite(NoticeDto noticeDto);
	boolean doUpdate(NoticeDto noticeDto);
	boolean doDelete(int no);
	boolean doHit(int no);
	List<NoticeDto> listNotice(NoticeParameterDto noticeParameterDto);
	int listNoticeTotalCount();
}
