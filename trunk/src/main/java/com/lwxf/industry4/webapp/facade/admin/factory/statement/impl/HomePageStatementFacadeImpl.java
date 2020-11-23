package com.lwxf.industry4.webapp.facade.admin.factory.statement.impl;

import com.lwxf.industry4.webapp.bizservice.company.CompanyEmployeeService;
import com.lwxf.industry4.webapp.bizservice.company.CompanyService;
import com.lwxf.industry4.webapp.bizservice.customer.CompanyCustomerService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.bizservice.customorder.OrderProductService;
import com.lwxf.industry4.webapp.bizservice.dealer.DealerAccountService;
import com.lwxf.industry4.webapp.bizservice.dept.DeptMemberService;
import com.lwxf.industry4.webapp.bizservice.statements.app.factory.orderStatements.CustomOrderStatementService;
import com.lwxf.industry4.webapp.bizservice.statements.app.factory.produce.ProduceStatementService;
import com.lwxf.industry4.webapp.bizservice.system.BasecodeService;
import com.lwxf.industry4.webapp.common.enums.company.CompanyStatus;
import com.lwxf.industry4.webapp.common.enums.company.DealerAccountNature;
import com.lwxf.industry4.webapp.common.enums.company.DealerAccountType;
import com.lwxf.industry4.webapp.common.enums.order.OrderStatus;
import com.lwxf.industry4.webapp.common.enums.order.OrderType;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.DateUtilsExt;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.company.CompanyDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderDto;
import com.lwxf.industry4.webapp.domain.entity.customer.CompanyCustomer;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAccount;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import com.lwxf.industry4.webapp.facade.admin.factory.statement.HomePageStatementFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.DateUtil;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2020-03-12 15:36
 * @version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component(value = "homePageStatementFacade")
public class HomePageStatementFacadeImpl extends BaseFacadeImpl implements HomePageStatementFacade {
    @Resource(name = "companyService")
    private CompanyService companyService;
    @Resource(name = "customOrderService")
    private CustomOrderService customOrderService;
    @Resource(name = "companyEmployeeService")
    private CompanyEmployeeService companyEmployeeService;
    @Resource(name = "deptMemberService")
    private DeptMemberService deptMemberService;
    @Resource(name = "produceStatementService")
    private ProduceStatementService produceStatementService;
    @Resource(name = "orderProductService")
    private OrderProductService orderProductService;
    @Resource(name = "basecodeService")
    private BasecodeService basecodeService;
    @Resource(name = "companyCustomerService")
    private CompanyCustomerService companyCustomerService;
    @Resource(name = "dealerAccountService")
    private DealerAccountService dealerAccountService;
    @Resource(name = "customOrderStatementService")
    private CustomOrderStatementService customOrderStatementService;

    @Override
    public RequestResult findHomePageStatement(String startTime, String endTime, String type) {
        String branchId = WebUtils.getCurrBranchId();
        MapContext result = MapContext.newOne();
        //经销商数量
        MapContext dealer = MapContext.newOne();
        Integer allDealerCount = this.companyService.findAllDealerCount(branchId);
        List<MapContext> statusList=new ArrayList<>();
        //签约经销商
        MapContext contractList = this.companyService.findDealerCountByStatus(branchId, CompanyStatus.NORMAL.getValue());
        //意向经销商
        MapContext intentionList = this.companyService.findDealerCountByStatus(branchId, CompanyStatus.INTENTION.getValue());
        statusList.add(contractList);
        statusList.add(intentionList);
        dealer.put("allCount", allDealerCount);
        dealer.put("statusList", statusList);
        //订单数量
        MapContext orderNum = MapContext.newOne();
        Integer allOrderCount = this.customOrderService.findAllOrderCount(branchId);
        List<MapContext> typeList = new ArrayList<>();
        List<Basecode> orderProductType = this.basecodeService.findByTypeAndDelFlag("orderProductType", 0);
        for(Basecode basecode:orderProductType){
            String productType = basecode.getCode();
           MapContext map= this.customOrderService.selectOrderProductTypeCount(branchId,productType);
           typeList.add(map);
        }

        orderNum.put("count", allOrderCount);
        orderNum.put("typeList", typeList);
        //员工数量
        MapContext employeeCount = MapContext.newOne();
        List<Map> allEmployeesByCid = this.companyEmployeeService.findAllEmployeesByBid(branchId);
        List<MapContext> deptEmployees = this.deptMemberService.findEmployeeListGroupByDept(branchId);
        employeeCount.put("count", allEmployeesByCid.size());
        employeeCount.put("deptEmployees", deptEmployees);
        //订单走势
        MapContext params = MapContext.newOne();
        Date startDate = new Date();
        Date endDate = new Date();
        if (type != null && !type.equals("")) {
            if (type.equals("1")) {
                startDate = DateUtilsExt.getFirstDayOfMonth(DateUtil.now());
                endDate = DateUtil.now();
            } else {
                startDate = DateUtilsExt.getFirstDayOfYear(DateUtil.now());
                endDate = DateUtil.now();
            }
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            if (startTime != null && !startTime.equals("")) {
                startDate = format.parse(startTime);
            }
            if (endTime != null && !endTime.equals("")) {
                endDate = format.parse(endTime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        params.put("branchId", branchId);
        params.put("type", type);
        List<Date> dates = new ArrayList<>();
        if (type != null && type.equals("2")) {
            try {
                dates = DateUtilsExt.getMonthBetween(startDate, endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            dates = DateUtilsExt.findDates(startDate, endDate);
        }
        List<MapContext> orderTrends = new ArrayList();
        for (Date date : dates) {
            params.put("date", date);
            MapContext orderTrend = this.customOrderService.findOrderTrendByTime(params);
            if (type != null && type.equals("2")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
                orderTrend.put("dateTime", dateFormat.format(date));
            }else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
                orderTrend.put("dateTime", dateFormat.format(date));
            }
            orderTrends.add(orderTrend);
        }
        //产品占比
        MapContext map=MapContext.newOne();
        map.put("branchId",branchId);
        List<MapContext> products = this.orderProductService.findProductNumGroupByType(map);
        //生产统计
        MapContext mapContext = MapContext.newOne();
        mapContext.put("branchId", branchId);
        MapContext produceStatus = this.produceStatementService.orderProduceCount(mapContext);
        //经销商排行
        result.put("dealer", dealer);
        result.put("orderNum", orderNum);
        result.put("employeeCount", employeeCount);
        result.put("orderTrends", orderTrends);
        result.put("products", products);
        result.put("produceStatus", produceStatus);
        return ResultFactory.generateRequestResult(result);
    }

    @Override
    public RequestResult findDealerHomePageStatement(String startTime, String endTime, String type) {
        MapContext result = MapContext.newOne();
        String dealerId=WebUtils.getCurrCompanyId();
        //终端客户数量
        List<Map> companyCustomer = this.companyCustomerService.findCompanyCustomer(dealerId);
        //订单数量
       Integer allOrderCount = this.customOrderService.findAllDealerOrderCount(dealerId);
       //员工数量
        List<Map> allEmployeesByCid = this.companyEmployeeService.findAllEmployeesByCid(dealerId);
        //订单走势
        MapContext params = MapContext.newOne();
        Date startDate = new Date();
        Date endDate = new Date();
        if (type != null && !type.equals("")) {
            if (type.equals("1")) {
                startDate = DateUtilsExt.getFirstDayOfMonth(DateUtil.now());
                endDate = DateUtil.now();
            } else {
                startDate = DateUtilsExt.getFirstDayOfYear(DateUtil.now());
                endDate = DateUtil.now();
            }
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            if (startTime != null && !startTime.equals("")) {
                startDate = format.parse(startTime);
            }
            if (endTime != null && !endTime.equals("")) {
                endDate = format.parse(endTime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        params.put("companyId", dealerId);
        params.put("type", type);
        List<Date> dates = new ArrayList<>();
        if (type != null && type.equals("2")) {
            try {
                dates = DateUtilsExt.getMonthBetween(startDate, endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            dates = DateUtilsExt.findDates(startDate, endDate);
        }
        List<MapContext> orderTrends = new ArrayList();
        for (Date date : dates) {
            params.put("date", date);
            MapContext orderTrend = this.customOrderService.findDealerOrderTrendByTime(params);
            if (type != null && type.equals("2")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
                orderTrend.put("dateTime", dateFormat.format(date));
            }else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
                orderTrend.put("dateTime", dateFormat.format(date));
            }
            orderTrends.add(orderTrend);
        }
        //产品总览
        MapContext map=MapContext.newOne();
        map.put("companyId",dealerId);
        List<MapContext> products = this.orderProductService.findProductNumGroupByType(map);

        //返回结果
        if(companyCustomer!=null) {
            result.put("customerNum", companyCustomer.size());
        }else {
            result.put("customerNum", 0);
        }
        result.put("orderNum",allOrderCount);
        if(allEmployeesByCid!=null){
            result.put("employeeNum",allEmployeesByCid.size());
        }else {
            result.put("employeeNum",0);
        }
       result.put("orderTrends",orderTrends);
        result.put("products",products);
        return ResultFactory.generateRequestResult(result);
    }

    @Override
    public RequestResult findDealerHomePageStatementV2(String orderStartTime, String orderEndTime, String orderType, String payStartTime, String payEndTime, String payType) {
        String companyId=WebUtils.getCurrCompanyId();
        CompanyDto companyById = this.companyService.findCompanyById(companyId);
        if(companyById==null){
            return ResultFactory.generateResNotFoundResult();
        }
        //经销商账户余额
        DealerAccount dealerAccount = this.dealerAccountService.findByCompanIdAndNatureAndType(companyId, DealerAccountNature.PUBLIC.getValue(), DealerAccountType.FREE_ACCOUNT.getValue());
        BigDecimal balance = dealerAccount.getBalance();//可用余额
        //订单数量
        MapContext mapContext=MapContext.newOne();
        mapContext.put("companyId",companyId);
        List<CustomOrderDto> byCompanyIdAndStatus = this.customOrderService.findByCompanyIdAndStatus(mapContext);
        Integer orderNum=0;
        if(byCompanyIdAndStatus!=null){
            orderNum=byCompanyIdAndStatus.size();
        }
        //终端客户
        List<CompanyCustomer> customerListByCid = this.companyCustomerService.findCustomerListByCid(companyId);
        Integer customerNum=0;
        if(customerListByCid!=null){
            customerNum=customerListByCid.size();
        }
        //待传单数量
        Integer toBeSendNum=0;
        mapContext.put("state", 0);
        List<CustomOrderDto> toBeSend = this.customOrderService.findByCompanyIdAndStatus(mapContext);
        if(toBeSend!=null){
            toBeSendNum=toBeSend.size();
        }
        mapContext.remove("state");
        //待支付
        Integer toBePayNum=0;
        mapContext.put("status", OrderStatus.TO_PAID.getValue());
        List<CustomOrderDto> toBePay = this.customOrderService.findByCompanyIdAndStatus(mapContext);
        if(toBeSend!=null){
            toBePayNum=toBePay.size();
        }
        //待发货
        Integer toBeDeliverNum=0;
        mapContext.put("status", OrderStatus.TO_SHIPPED.getValue());
        List<CustomOrderDto> toBeDeliver = this.customOrderService.findByCompanyIdAndStatus(mapContext);
        if(toBeSend!=null){
            toBeDeliverNum=toBeDeliver.size();
        }
        //售后
        Integer aftersaleNum=0;
        mapContext.remove("status");
        mapContext.put("type", OrderType.SUPPLEMENTORDER.getValue());
        List<CustomOrderDto> aftersale = this.customOrderService.findByCompanyIdAndStatus(mapContext);
        if(toBeSend!=null){
            aftersaleNum=aftersale.size();
        }
        //本月订单总数和上月订单数//本周订单数和上周订单数
        MapContext mapContext1=MapContext.newOne();
        mapContext1.put("companyId",companyId);
        mapContext1.put("type",OrderType.NORMALORDER.getValue());
        Map countmap=customOrderStatementService.findDealerOrderCountsByTime(mapContext1);
        //本周，本月，本年 以及自定义日期订单数据
        MapContext params = MapContext.newOne();
        params.put("companyId",WebUtils.getCurrCompanyId());
        Map times = this.getTimes(orderType, orderStartTime, orderEndTime);
        Date startDate=(Date) times.get("startTime");
        Date endDate=(Date) times.get("endTime");
        List<Date> betweenDate=new ArrayList<>();
        SimpleDateFormat format=new SimpleDateFormat("MM-dd");
        if(orderType!=null&&orderType.equals("3")){
            format=new SimpleDateFormat("MM");
            params.put("timeType",orderType);
            try {
                 betweenDate = DateUtilsExt.getMonthBetween(startDate, endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else {
            betweenDate = DateUtilsExt.findDates(startDate, endDate);

        }
        List orderCount=new ArrayList();
        for(Date date:betweenDate){
            params.put("date",date);
            Map count=this.customOrderStatementService.selectDealerOrderCountByDay(params);
            Map map=new HashMap();
            map.put("date",format.format(date));
            map.put("count",count.get("orderNum"));
            orderCount.add(map);
        }

        //本周，本月，本年 以及自定义日期订单财务数据
        MapContext paramsTwo = MapContext.newOne();
        paramsTwo.put("companyId",WebUtils.getCurrCompanyId());
        Map timesTwo = this.getTimes(payType, payStartTime, payEndTime);
        Date payStartDate=(Date) timesTwo.get("startTime");
        Date payEndDate=(Date) timesTwo.get("endTime");
        List<Date> betweenDateTwo=new ArrayList<>();
        SimpleDateFormat formatTwo=new SimpleDateFormat("MM-dd");
        if(payType!=null&&payType.equals("3")){
            formatTwo=new SimpleDateFormat("MM");
            paramsTwo.put("timeType",orderType);
            try {
                betweenDateTwo = DateUtilsExt.getMonthBetween(payStartDate, payEndDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else {
            betweenDateTwo = DateUtilsExt.findDates(payStartDate, payEndDate);

        }
        List orderAmount=new ArrayList();
        for(Date date:betweenDateTwo){
            paramsTwo.put("date",date);
            Map count=this.customOrderStatementService.selectDealerOrderCountByDay(paramsTwo);
            Map map=new HashMap();
            map.put("date",formatTwo.format(date));
            map.put("count",count.get("orderAmount"));
            orderAmount.add(map);
        }
        Map result=new HashMap();
        result.put("balance",balance);
        result.put("orderNum",orderNum);
        result.put("customerNum",customerNum);
        result.put("toBeSendNum",toBeSendNum);
        result.put("toBePayNum",toBePayNum);
        result.put("toBeDeliverNum",toBeDeliverNum);
        result.put("aftersaleNum",aftersaleNum);
        result.put("countmap",countmap);
        result.put("orderCount",orderCount);
        result.put("orderAmount",orderAmount);
        return ResultFactory.generateRequestResult(result);
    }
    public Map getTimes(String timeType,String start,String end){

        Date startDate = new Date();
        Date endDate = new Date();
        if(timeType==null||timeType.equals("")){
            if(!(start==null||start.equals("")||end==null||end.equals(""))){
                SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                try {
                    startDate= dateFormat.parse(start);
                    endDate=dateFormat.parse(end);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else {//如果为空，则默认展示本周数据
                startDate = DateUtilsExt.getBeginDayOfWeek();
                endDate = new Date();
            }
        }else {
            if(timeType.equals("1")){//本周
                startDate = DateUtilsExt.getBeginDayOfWeek();
            }else if(timeType.equals("2")){//本月
                startDate = DateUtilsExt.getBeginDayOfMonth();
            }else if(timeType.equals("3")){//本年
                startDate=DateUtilsExt.getFirstDayOfYear(new Date());
            }
            endDate=new Date();
        }
        Map map=new HashMap();
        map.put("startTime",startDate);
        map.put("endTime",endDate);
        return map;

    }
}
