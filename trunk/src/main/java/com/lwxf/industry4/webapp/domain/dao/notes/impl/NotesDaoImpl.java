package com.lwxf.industry4.webapp.domain.dao.notes.impl;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.notes.NotesDao;
import com.lwxf.industry4.webapp.domain.entity.notes.Notes;
import org.springframework.stereotype.Repository;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2020-03-07 09:41:25
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("notesDao")
public class NotesDaoImpl extends BaseDaoImpl<Notes, String> implements NotesDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<Notes> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<Notes> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

}