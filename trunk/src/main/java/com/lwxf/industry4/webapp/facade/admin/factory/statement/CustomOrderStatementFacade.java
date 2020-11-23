package com.lwxf.industry4.webapp.facade.admin.factory.statement;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

import java.util.Date;


public interface CustomOrderStatementFacade extends BaseFacade {


//    RequestResult  selectByfilter(Date startTime, Date endTime, MapContext map,Integer dateType);
//
//    RequestResult  countOrderByWeek(MapContext map);
//
//    RequestResult  countOrderByMonth(MapContext map);
//
//    RequestResult  countOrderByQuarter(MapContext map);
//
//    RequestResult  countOrderByYear(MapContext map);
//

//
    RequestResult countCorporatePartners(String startDateTime, String endDateTime, MapContext mapContext);

	RequestResult countSalemanAchievements(String startDateTime, String endDateTime, MapContext mapContext);
    RequestResult countOrderSeries(String startDateTime,String endDateTime, MapContext mapContext);

	RequestResult selectOrderCount(String branchId, String yearTime);

	RequestResult selectOrderMonthCount(String branchId, String monthTime);

	RequestResult selectcoordinationMonthCount(String branchId, String monthTime);


	RequestResult coordinationOrderRanking(String startTimeTime, String endTimeTime, MapContext mapContext);

	RequestResult selectcoordinationInfoCount(String coordinationName,String yearTime);

	RequestResult countOrderDeptAchievements( MapContext mapContext);


	RequestResult countUserAchievements(MapContext mapContext, Integer pageNum, Integer pageSize);

	RequestResult newOrderCount(String branchId);

	RequestResult newOrderCountByMonth(String branchId);

	RequestResult newOrderCountByYear(String branchId);

	RequestResult homeOrderCount(String branchId);

	RequestResult myOrderCount(String branchId, String companyId, String userId);
}
