package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.NoticeParameterDto;
import com.ssafy.happyhouse.model.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeMapper noticeMapper;
	
	@Override
	public List<NoticeDto> getList() {
		return noticeMapper.getList();
	}

	@Override
	public NoticeDto getArticle(int no) {
		return noticeMapper.getArticle(no);
	}

	@Override
	@Transactional
	public boolean doWrite(NoticeDto noticeDto) {
		return noticeMapper.doWrite(noticeDto) == 1;
	}

	@Override
	@Transactional
	public boolean doUpdate(NoticeDto noticeDto) {
		return noticeMapper.doUpdate(noticeDto) == 1;
	}

	@Override
	@Transactional
	public boolean doDelete(int no) {
		return noticeMapper.doDelete(no) == 1;
	}

	@Override
	@Transactional
	public boolean doHit(int no) {
		return noticeMapper.doHit(no) == 1;
	}

	@Override
	@Transactional
	public List<NoticeDto> listNotice(NoticeParameterDto noticeParameterDto) {
		int start = noticeParameterDto.getPg() == 0 ? 0 : (noticeParameterDto.getPg() - 1) * noticeParameterDto.getSpp();
		noticeParameterDto.setStart(start);
		return noticeMapper.listNotice(noticeParameterDto);
	}

	@Override
	@Transactional
	public int listNoticeTotalCount() {
		return noticeMapper.listNoticeTotalCount();
	}
		
}
