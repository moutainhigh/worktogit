package com.lwxf.industry4.webapp.bizservice.notes;


import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.entity.notes.Notes;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2020-03-07 09:41:25
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface NotesService extends BaseService <Notes, String> {

	PaginatedList<Notes> selectByFilter(PaginatedFilter paginatedFilter);
	int insert(Notes paginatedFilter);

}