package com.lwxf.industry4.webapp.bizservice.dealer.impl;


import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.dealer.DealerAccountLogService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.dealer.DealerAccountLogDao;
import com.lwxf.industry4.webapp.domain.dto.dealer.DealerAccountLogDto;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAccountLog;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-20 18:46:16
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("dealerAccountLogService")
public class DealerAccountLogServiceImpl extends BaseServiceImpl<DealerAccountLog, String, DealerAccountLogDao> implements DealerAccountLogService {


	@Resource

	@Override	public void setDao( DealerAccountLogDao dealerAccountLogDao) {
		this.dao = dealerAccountLogDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DealerAccountLog> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public PaginatedList<DealerAccountLog> findByCompanyIdAndCondition(PaginatedFilter paginatedFilter) {
		return this.dao.findByCompanyIdAndCondition(paginatedFilter);
	}

	@Override
	public DealerAccountLogDto findByLogId(String logId) {
		return this.dao.selectByLogId(logId);
	}

	@Override
	public DealerAccountLog findByOrderIdAndType(MapContext orderIdAndType) {
		return this.dao.findByOrderIdAndType(orderIdAndType);
	}
}