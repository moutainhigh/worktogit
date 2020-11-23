package com.lwxf.industry4.webapp.bizservice.evalate.impl;


import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.evalate.EvaluateInfoService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.evaluate.EvaluateInfoDao;
import com.lwxf.industry4.webapp.domain.entity.evaluate.EvaluateInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-11-29 14:39:15
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("evaluateInfoService")
public class EvaluateInfoServiceImpl extends BaseServiceImpl<EvaluateInfo, String, EvaluateInfoDao> implements EvaluateInfoService {


	@Resource

	@Override	public void setDao( EvaluateInfoDao evaluateInfoDao) {
		this.dao = evaluateInfoDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<EvaluateInfo> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

}