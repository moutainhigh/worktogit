package com.lwxf.industry4.webapp.bizservice.finished.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.finished.FinishedProductSeriesDao;
import com.lwxf.industry4.webapp.bizservice.finished.FinishedProductSeriesService;
import com.lwxf.industry4.webapp.domain.entity.finished.FinishedProductSeries;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-18 19:52:24
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("finishedProductSeriesService")
public class FinishedProductSeriesServiceImpl extends BaseServiceImpl<FinishedProductSeries, String, FinishedProductSeriesDao> implements FinishedProductSeriesService {


	@Resource

	@Override	public void setDao( FinishedProductSeriesDao finishedProductSeriesDao) {
		this.dao = finishedProductSeriesDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<FinishedProductSeries> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<FinishedProductSeries> findAll() {
		return this.dao.findAll();
	}
}