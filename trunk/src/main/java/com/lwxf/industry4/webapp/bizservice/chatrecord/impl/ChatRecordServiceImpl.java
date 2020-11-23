package com.lwxf.industry4.webapp.bizservice.chatrecord.impl;


import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.chatrecord.ChatRecordService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.chatrecord.ChatRecordDao;
import com.lwxf.industry4.webapp.domain.entity.chatrecord.ChatRecord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-12-05 18:42:40
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("chatRecordService")
public class ChatRecordServiceImpl extends BaseServiceImpl<ChatRecord, String, ChatRecordDao> implements ChatRecordService {


	@Resource

	@Override	public void setDao( ChatRecordDao chatRecordDao) {
		this.dao = chatRecordDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ChatRecord> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

}