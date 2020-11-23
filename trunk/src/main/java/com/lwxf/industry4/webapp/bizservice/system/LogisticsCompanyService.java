package com.lwxf.industry4.webapp.bizservice.system;


import java.util.List;

import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.system.LcNameAndNum;
import com.lwxf.industry4.webapp.domain.entity.system.LogisticsCompany;
import com.lwxf.mybatis.utils.MapContext;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-20 15:10:29
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface LogisticsCompanyService extends BaseService <LogisticsCompany, String> {

	PaginatedList<LogisticsCompany> selectByFilter(PaginatedFilter paginatedFilter);

	List<LogisticsCompany> findAllNormalByBranchId(MapContext mapContext);

	List<LcNameAndNum> findLCNameAndNum(String beginTime,String endTime);

	LogisticsCompany findByNO(String no);

	LogisticsCompany findByLogisticId(String logisticId);
}