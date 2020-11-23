package com.lwxf.industry4.webapp.facade.wxapi.factory.statements;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

import java.util.Date;


public interface CustomOrderStatementFacade extends BaseFacade {

    RequestResult  selectByfilter(Date startTime,Date endTime,MapContext map);

    RequestResult  countOrderByWeek(MapContext map);

    RequestResult  countOrderByMonth(MapContext map);

    RequestResult  countOrderByQuarter(MapContext map);

    RequestResult  countOrderByYear(MapContext map);

	RequestResult findOrderCountList(String year, String branchId);

    RequestResult findMonthOrderCountInfo(String monthTime, String branchId);
}
