package com.lwxf.industry4.webapp.domain.dao.notes;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.notes.Notes;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2020-03-07 09:41:25
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface NotesDao extends BaseDao<Notes, String> {

	PaginatedList<Notes> selectByFilter(PaginatedFilter paginatedFilter);

}