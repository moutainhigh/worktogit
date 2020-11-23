package com.lwxf.industry4.webapp.domain.dao.chatrecord;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.chatrecord.ChatRecord;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-12-05 15:21:33
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface ChatRecordDao extends BaseDao<ChatRecord, String> {

	PaginatedList<ChatRecord> selectByFilter(PaginatedFilter paginatedFilter);



}