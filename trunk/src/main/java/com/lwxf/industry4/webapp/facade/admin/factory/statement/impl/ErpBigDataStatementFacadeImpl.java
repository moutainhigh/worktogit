package com.lwxf.industry4.webapp.facade.admin.factory.statement.impl;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.baseservice.cache.RedisUtils;
import com.lwxf.industry4.webapp.bizservice.company.CompanyService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.bizservice.statements.app.factory.dealerStatements.DealerStatementService;
import com.lwxf.industry4.webapp.bizservice.system.BasecodeService;
import com.lwxf.industry4.webapp.common.enums.order.OrderStatus;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.DateUtilsExt;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dao.company.DealerShopDao;
import com.lwxf.industry4.webapp.domain.dao.statements.factory.dealerStatement.DealerStatementDao;
import com.lwxf.industry4.webapp.domain.dao.statements.factory.dealerStatement.impl.DealerStatementDaoImpl;
import com.lwxf.industry4.webapp.domain.dto.company.CompanyDto;
import com.lwxf.industry4.webapp.domain.dto.dealer.DealerReportRankDto;
import com.lwxf.industry4.webapp.domain.dto.dealer.DealerReportSummaryDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByMonthDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByQuarterDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByWeekDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByYearDto;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.statement.DealerStatementFacade;
import com.lwxf.industry4.webapp.facade.admin.factory.statement.ErpBigDataStatementFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/30 0030 9:26
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("erpBigDataStatementFacade")
public class ErpBigDataStatementFacadeImpl extends BaseFacadeImpl implements ErpBigDataStatementFacade {

	@Resource(name = "dealerStatementDao")
	private DealerStatementDaoImpl dealerStatementDao;

    @Resource(name ="redisUtils")
	private RedisUtils redisUtils;

    /**
	 * 经销商报表
	 *
	 * 总经销商
	 * 经销商店铺类型
	 * 签约 意向 即将到期 已到期
	 * @return
	 */
    @Override
	public Map queryBigdData(String refresh) {
        Map<String,Object> queryBigdDataMap = new HashMap<>();
        String branchId=WebUtils.getCurrBranchId();
        Object queryBigdDataMapO = this.redisUtils.get("queryBigdDataMap");
        if(queryBigdDataMapO!=null && !"1".equals(refresh)){
            Map queryBigdDataMapR=(Map)queryBigdDataMapO;
            return queryBigdDataMapR;
        }
        Map map = dealerStatementDao.queryDealerData();//经销商数据
        List<Map> typeList=dealerStatementDao.findDealerNumGroupByType(branchId);
        map.put("typeList",typeList);
        queryBigdDataMap.put("hashMapDealerData",map);

        //生产数据统计
        //待生产 生产中 即将逾期 已逾期
        List list3 =  dealerStatementDao.queryProductData();
        queryBigdDataMap.put("queryProductData",list3);
        //自产  折线图
        List list2 = dealerStatementDao.queryOwnerStateData();
        queryBigdDataMap.put("queryOwnerStateData",list2);
        //外协 折线图
        List list1 =  dealerStatementDao.queryOutStateData();

        queryBigdDataMap.put("queryOutStateData",list1);
        //售后 折线图
        List list16 =  dealerStatementDao.queryAftererSaleStateData();
        queryBigdDataMap.put("queryOutStateData",list16);

        //库存统计
        List list4 = dealerStatementDao.queryRepertoryData();
        queryBigdDataMap.put("queryRepertoryData",list4);
        //订单总数
        List list5 = dealerStatementDao.queryTotalOrderData();
        queryBigdDataMap.put("queryTotalOrderData",list5);
        //订单饼状图
        List list6 = dealerStatementDao.queryOrderPieChartData();
        queryBigdDataMap.put("queryOrderPieChartData",list6);

        //自产
        List list7 = dealerStatementDao.queryOwnLineChartData();
        queryBigdDataMap.put("queryOwnLineChartData",list7);
        //外协
        List list8 = dealerStatementDao.queryOutOrderLineChartData();
        queryBigdDataMap.put("queryOutOrderLineChartData",list8);
        //售后
        List list9 = dealerStatementDao.queryAfterSalOrderLineChartData();
        queryBigdDataMap.put("queryAfterSalOrderLineChartData",list9);
        //本月 本年 去年本月 去年
        List list10 = dealerStatementDao.queryNowMonthYearOrderData();
        queryBigdDataMap.put("queryAfterSalOrderLineChartData",list10);
        //财务数据分析
        List list11 = dealerStatementDao.queryFinanceData();
        queryBigdDataMap.put("queryFinanceData",list11);
        //财务分析 饼状图 今年
        List list12 = dealerStatementDao.queryFinancePieChartYearData();
        queryBigdDataMap.put("queryFinancePieChartYearData",list12);
        //财务数据分析 本年 去年 本月 上个月
        List list13 = dealerStatementDao.queryFinanceTotalComparisonData();
        queryBigdDataMap.put("queryFinanceTotalComparisonData",list13);
        //员工信息统计 部门排序
        List list14 = dealerStatementDao.queryDeptMemberData();
        queryBigdDataMap.put("queryDeptMemberData",list14);
        // 财务分析 饼状图 今年
        List list15 = dealerStatementDao.queryFinancePieChartMonthData();
        queryBigdDataMap.put("queryFinancePieChartMonthData",list15);
        //查询代发货已发货
        MapContext deliverStatus=MapContext.newOne();
        deliverStatus.put("toDeliver", OrderStatus.TO_SHIPPED.getValue());
        deliverStatus.put("hasDeliver", OrderStatus.SHIPPED.getValue());
        List list17 = dealerStatementDao.queryDeliverData(deliverStatus);
        queryBigdDataMap.put("queryDeliverData",list17);
        //经销商排名
        List list18 = dealerStatementDao.queryDealerSellOrder();
        queryBigdDataMap.put("queryDealerSellOrder",list18);
        //
        List list19 = dealerStatementDao.queryNowYearData();
        queryBigdDataMap.put("queryNowYearData",list19);
        //
        List list20 = dealerStatementDao.queryLastYearData();
        queryBigdDataMap.put("queryLastYearData",list20);
        //查询自产 外协 售后总订单数
        List list21 = dealerStatementDao.queryTotalOrderByTypeData();
        queryBigdDataMap.put("queryTotalOrderByTypeData",list21);
        //发货物流公司排名
        List list22 = dealerStatementDao.queryLogisticsOrder();
        queryBigdDataMap.put("queryLogisticsOrder",list22);

        //产品总数
        List list23 = dealerStatementDao.queryOrderPieChartData();
        int total = 0;
        for (Object o:list23) {
            Map<String,Object> mapCount = (Map<String,Object>)o;
            int count =Integer.valueOf(mapCount.get("count")+"") ;
            total = total+count;
        }
        queryBigdDataMap.put("queryOrderProductTotal",total);




        this.redisUtils.set("queryBigdDataMap",queryBigdDataMap,60*60*12);
        return queryBigdDataMap;
	}



































}
