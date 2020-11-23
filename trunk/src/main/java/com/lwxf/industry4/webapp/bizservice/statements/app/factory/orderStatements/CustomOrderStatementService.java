package com.lwxf.industry4.webapp.bizservice.statements.app.factory.orderStatements;

import com.lwxf.industry4.webapp.bizservice.base.BaseNoIdService;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByMonthDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByQuarterDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByWeekDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByYearDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrder;
import com.lwxf.mybatis.utils.MapContext;

import java.util.List;
import java.util.Map;

public interface CustomOrderStatementService  extends BaseNoIdService {

      Integer selectByfilter(MapContext map);

      CountByWeekDto countOrderByWeek(MapContext map);

      CountByMonthDto countOrderByMonth(MapContext map);

      CountByQuarterDto countOrderByQuarter(MapContext map);

      CountByYearDto countOrderByYear(MapContext map);

	List<MapContext> findCorporates(MapContext mapContext);

      MapContext findByCorporateNameAndDate(MapContext mapContext);

      MapContext countSalemanAchievements(MapContext mapContext);

      List<MapContext> findCorporatesByBranchId(String currBranchId);

      Integer findAllOrderCountByBidAndType(MapContext mapContext);

	MapContext selectOrderCount(MapContext map);

      MapContext selectProduceCount(MapContext map);

      MapContext selectAfterCount(MapContext map);

      MapContext findCountByBidAndType(String branchId, String monthTime);

      MapContext selectOrderCountByDay(MapContext mapContext);

      List<MapContext> findDealerByTime(String branchId, String monthTime);

      List<CustomOrder> findOrderCOuntByCidAndTime(String companyId, String monthTime);


      Integer findProductNumByBidAndTime(MapContext mapContext);


      MapContext homeOrderCount(MapContext mapContext);

      MapContext myOrderCount(MapContext mapContext);

      MapContext placeOrderCount(MapContext mapContext);

      MapContext merchandiserOrderCount(MapContext mapContext);

      MapContext findOrderProductTypeCount(MapContext mapContext);

      MapContext dispatchOrderCountByDay(MapContext mapContext);

      MapContext findOrderProductStockCount(MapContext mapContext);

      MapContext findAfterOrderCount(MapContext mapContext);


      Map findDealerOrderCountsByTime(MapContext mapContext1);

      Map selectDealerOrderCountByDay(MapContext params);
}
