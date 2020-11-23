package com.lwxf.industry4.webapp.bizservice.datachart;

import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderDemand;
import com.lwxf.mybatis.utils.MapContext;

/**
 * @author lyh on 2019/12/7
 */
public interface DataChartService {

    RequestResult findOrderChart(MapContext mapContent);

    RequestResult findOrderChartByseries(MapContext mapContent);

    RequestResult findOrderChartByDoor(MapContext mapContent);

    RequestResult findOrderChartByColor(MapContext mapContent);

    RequestResult findOrderChartByseriesFinance(MapContext mapContent);

    RequestResult findOrderProportionFinance(MapContext mapContent);

    RequestResult findImmediatelyPostponeOrder();

    RequestResult findStatusProductOrder(String status);

    RequestResult overdueOrderTrend(String queryDateMonth);

    RequestResult deliverGoodsNow();
    RequestResult deliverGoodsTrend(String queryDate);
    RequestResult findlogisticsNameCount(String queryDateStart,String queryDateEnd);


}
