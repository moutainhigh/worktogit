package com.lwxf.industry4.webapp.bizservice.notes.impl;


import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.notes.NotesService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.notes.NotesDao;
import com.lwxf.industry4.webapp.domain.entity.notes.Notes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2020-03-07 09:41:25
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("notesService")
public class NotesServiceImpl extends BaseServiceImpl<Notes, String, NotesDao> implements NotesService {


	@Resource

	@Override	public void setDao( NotesDao notesDao) {
		this.dao = notesDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<Notes> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public int insert(Notes entity) {
		return this.dao.insert(entity);
	}
}