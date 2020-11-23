package com.lwxf.industry4.webapp.domain.dao.chatrecord.impl;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.chatrecord.ChatRecordDao;
import com.lwxf.industry4.webapp.domain.entity.chatrecord.ChatRecord;
import org.springframework.stereotype.Repository;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-12-05 15:21:33
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("chatRecordDao")
public class ChatRecordDaoImpl extends BaseDaoImpl<ChatRecord, String> implements ChatRecordDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ChatRecord> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<ChatRecord> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

}