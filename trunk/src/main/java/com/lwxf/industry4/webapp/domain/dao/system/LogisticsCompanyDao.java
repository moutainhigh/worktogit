package com.lwxf.industry4.webapp.domain.dao.system;


import java.util.List;

import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.dto.system.LcNameAndNum;
import com.lwxf.industry4.webapp.domain.entity.system.LogisticsCompany;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.mybatis.utils.MapContext;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-20 15:10:29
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface LogisticsCompanyDao extends BaseDao<LogisticsCompany, String> {

	PaginatedList<LogisticsCompany> selectByFilter(PaginatedFilter paginatedFilter);

	List<LogisticsCompany> findAllNormalByBranchId(MapContext mapContext);

	List<LcNameAndNum> findLCNameAndNum(MapContext parmas);

	LogisticsCompany findByNO(String no);

	LogisticsCompany findByLogisticId(String logisticId);
}