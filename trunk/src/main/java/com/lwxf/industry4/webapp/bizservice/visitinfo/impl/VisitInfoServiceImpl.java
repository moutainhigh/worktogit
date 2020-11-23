package com.lwxf.industry4.webapp.bizservice.visitinfo.impl;


import java.util.List;
import java.util.Map;


import com.lwxf.industry4.webapp.bizservice.visitinfo.VisitInfoService;
import com.lwxf.industry4.webapp.domain.dao.visitinfo.VisitInfoDao;
import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.entity.visitinfo.VisitInfo;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-11-26 15:32:34
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("visitInfoService")
public class VisitInfoServiceImpl extends BaseServiceImpl<VisitInfo, String, VisitInfoDao> implements VisitInfoService {


	@Resource

	@Override	public void setDao( VisitInfoDao visitInfoDao) {
		this.dao = visitInfoDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<VisitInfo> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public int insertVisitInfo(VisitInfo visitInfo) {
		return this.add(visitInfo);
	}
























}