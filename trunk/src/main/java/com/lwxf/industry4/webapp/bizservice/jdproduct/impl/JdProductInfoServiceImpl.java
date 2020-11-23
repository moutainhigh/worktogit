package com.lwxf.industry4.webapp.bizservice.jdproduct.impl;



import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.jdproduct.JdProductInfoService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.jdproduct.JdProductInfoDao;
import com.lwxf.industry4.webapp.domain.entity.jdproduct.JdProductInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-12-02 16:50:47
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("jdProductInfoService")
public class JdProductInfoServiceImpl extends BaseServiceImpl<JdProductInfo, String, JdProductInfoDao> implements JdProductInfoService {


	@Resource

	@Override	public void setDao( JdProductInfoDao jdProductInfoDao) {
		this.dao = jdProductInfoDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<JdProductInfo> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public int deleteByFetchingTime(Date fetchingTime) {
		return this.dao.deleteByFetchingTime(fetchingTime);
	}






















}