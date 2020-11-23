package com.lwxf.industry4.webapp.bizservice.chatrecord;


import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.entity.chatrecord.ChatRecord;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-12-05 18:42:40
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface ChatRecordService extends BaseService <ChatRecord, String> {

	PaginatedList<ChatRecord> selectByFilter(PaginatedFilter paginatedFilter);

}