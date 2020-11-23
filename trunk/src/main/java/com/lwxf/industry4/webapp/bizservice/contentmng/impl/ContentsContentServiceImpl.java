package com.lwxf.industry4.webapp.bizservice.contentmng.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lwxf.industry4.webapp.bizservice.base.BaseNoIdServiceImpl;
import com.lwxf.industry4.webapp.bizservice.contentmng.ContentsContentService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.contentmng.ContentsContentDao;
import com.lwxf.industry4.webapp.domain.entity.contentmng.ContentsContent;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-03-27 11:58:33
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("contentsContentService")
public class ContentsContentServiceImpl extends BaseNoIdServiceImpl<ContentsContent, ContentsContentDao> implements ContentsContentService {


	@Resource

	@Override	public void setDao( ContentsContentDao contentsContentDao) {
		this.dao = contentsContentDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ContentsContent> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

}