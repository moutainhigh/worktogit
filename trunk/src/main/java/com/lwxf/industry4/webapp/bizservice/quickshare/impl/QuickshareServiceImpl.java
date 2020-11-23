package com.lwxf.industry4.webapp.bizservice.quickshare.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.quickshare.QuickshareService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.quickshare.QuickshareDao;
import com.lwxf.industry4.webapp.domain.entity.quickshare.Quickshare;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-03-02 13:18:25
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("quickshareService")
public class QuickshareServiceImpl extends BaseServiceImpl<Quickshare, String, QuickshareDao> implements QuickshareService {


	@Resource

	@Override	public void setDao( QuickshareDao quickshareDao) {
		this.dao = quickshareDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<Quickshare> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}



}