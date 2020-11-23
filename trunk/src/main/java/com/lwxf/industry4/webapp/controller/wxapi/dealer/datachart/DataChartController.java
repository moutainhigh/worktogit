package com.lwxf.industry4.webapp.controller.wxapi.dealer.datachart;

import ch.qos.logback.classic.Logger;
import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.bizservice.datachart.DataChartService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.order.OrderProductType;
import com.lwxf.industry4.webapp.common.enums.order.OrderType;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.*;
import com.lwxf.industry4.webapp.config.LoggerBuilder;
import com.lwxf.industry4.webapp.domain.dao.product.ProductDao;
import com.lwxf.industry4.webapp.domain.dao.system.BasecodeDao;
import com.lwxf.industry4.webapp.domain.dto.aftersale.AftersaleDto;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.mybatis.utils.DateUtil;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;


@Api(value = "DataChartController", tags = {"B端微信小程序接口:经销商首页报表数据"})
@RestController
@RequestMapping(value = "/wxdealer/DataChartController", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class DataChartController {


    @Resource(name = "dataChartService")
    private DataChartService dataChartService;

    @Resource(name = "productDao")
    private ProductDao productDao;

    @Resource(name = "basecodeDao")
    private BasecodeDao basecodeDao;
    @Resource(name = "customOrderService")
    private CustomOrderService customOrderService;


    /**
     *今日待发货/今日已发货完成/今日发货
     */
    @ApiOperation(value = "今日待发货/今日已发货完成/今日发货", notes = "今日待发货/今日已发货完成/今日发货")
    @GetMapping("/deliverGoodsNow")
    public RequestResult deliverGoodsNow() {
        RequestResult orderChart = dataChartService.deliverGoodsNow();
        return orderChart;
    }


    /**
     * 物流发货趋势
     */
    @ApiOperation(value = "物流发货趋势", notes = "物流发货趋势")
    @GetMapping("/deliverGoodsTrend")
    public RequestResult deliverGoodsTrend(String queryDate) {
        RequestResult orderChart = dataChartService.deliverGoodsTrend(queryDate);
        return orderChart;
    }



    /**
     * 物流公司发货排行查询
     */
    @ApiOperation(value = "物流公司发货排行查询", notes = "物流公司发货排行查询")
    @GetMapping("/findlogisticsNameCount")
    public RequestResult findlogisticsNameCount(String queryDateStart,String queryDateEnd) {
        RequestResult orderChart = dataChartService.findlogisticsNameCount(queryDateStart,queryDateEnd);
        return orderChart;
    }



    /**
     *逾期订单趋势图
     */
    @ApiOperation(value = "逾期订单趋势图", notes = "逾期订单趋势图")
    @GetMapping("/overdueOrderTrend")
    public RequestResult overdueOrderTrend(String queryDateMonth) {

        RequestResult orderChart = dataChartService.overdueOrderTrend(queryDateMonth);
        return orderChart;
    }




    /**
     *订单生产状态统计
     */
    @ApiOperation(value = "订单生产状态占比", notes = "订单生产状态占比")
    @GetMapping("/findImmediatelyPostponeOrderStatus")
    public RequestResult findImmediatelyPostponeOrderStatus(String status) {
        RequestResult orderChart = dataChartService.findStatusProductOrder(status);
        return orderChart;
    }
    /**
     *订单生产状态统计
     */
    @ApiOperation(value = "订单生产状态统计", notes = "订单生产状态统计")
    @GetMapping("/findImmediatelyPostponeOrder")
    public RequestResult findImmediatelyPostponeOrder() {
        RequestResult orderChart = dataChartService.findImmediatelyPostponeOrder();
        return orderChart;
    }

    /**
     *订单财务 日订单明细统计
     */
    @ApiOperation(value = "本日订单财务明细统计", notes = "本日订单财务明细统计")
    @GetMapping("/orderProductFinanceParticulars")
    public RequestResult orderProductFinanceParticulars() {
        String yyyyMMdd = LwxfDateUtils.getSdfDate(new Date(), "yyyyMMdd");
        MapContext mapContent = new MapContext();
        mapContent.put("queryStartDate", yyyyMMdd);
        mapContent.put("queryEndDate", yyyyMMdd);
        RequestResult orderChart = dataChartService.findOrderChart(mapContent);
        return orderChart;
    }


    @ApiOperation(value = "年度订单财务明细统计", notes = "年度订单财务明细统计")
    @GetMapping("/twelveMonthFinanceParticulars")
    public RequestResult twelveMonthFinanceParticulars() {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String companyId = mapInfo.get("companyId") == null ? null : mapInfo.get("companyId").toString();
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        if (uid == null) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        ArrayList<Map<String, Object>> am = new ArrayList<>();
        Date date = new Date();
        int daysOfMonth = LwxfDateUtils.getnowYear(date);
        List<Basecode> orderProductType = basecodeDao.findByType("orderProductType");
        for (int i = 0; i < daysOfMonth; i++) {
            Map orderChart = new HashMap<>();
            String yyyyMM = LwxfDateUtils.getSdfDateMonthBefore(date, "yyyyMM", i);
            orderChart.put("yyyyMM", yyyyMM);
            MapContext mapContent = new MapContext();
            mapContent.put("queryStartDate", yyyyMM);
            mapContent.put("queryEndDate", yyyyMM);
            List<Map<String, Object>> orderChart1 = productDao.findOrderChart(mapContent);
            for (Basecode b : orderProductType) {
                String code = b.getCode();
                String value = b.getValue();
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("code", code);
                hashMap.put("name", value);
                hashMap.put("totalMoney","0");
                hashMap.put("totalUnits","0");
                orderChart.put("_"+code,hashMap);
                for (int j = 0; j < orderChart1.size(); j++) {
                    Map<String, Object> map = orderChart1.get(j);
                    if (map != null && code.equals(map.get("code"))) {
                        hashMap.putAll(orderChart1.get(j));
                    }
                }
            }
            //添加外协
            HashMap outsourceInfo = new HashMap();

            MapContext mapContext = new MapContext();
            mapContext.put("queryStartDate", yyyyMM);
            mapContext.put("queryEndDate", yyyyMM);
            mapContext.put("isCoordination", 1);//需要外协 外协单数
            List<Map<String, Object>> outsourceOrderChartTmp = productDao.findOrderChartTotal(mapContext);//外协总单数
            Integer lastOutsourcetalUnits = Integer.valueOf(outsourceOrderChartTmp.get(0).get("totalUnits") + "");//外协总单数
            if(lastOutsourcetalUnits == 0 || lastOutsourcetalUnits == null){
                outsourceInfo.put("outsourcetotalUnits",0);
                outsourceInfo.put("outsourcetotalMoney",0);
            }else{
                Double totalMoney = Double.valueOf(outsourceOrderChartTmp.get(0).get("totalMoney") + "");//外协总单数
                outsourceInfo.put("outsourcetotalUnits",lastOutsourcetalUnits);
                outsourceInfo.put("outsourcetotalMoney",  LwxfNumberUtils.fomatNumberTwoDigits(totalMoney));
            }
            orderChart.putAll(outsourceInfo);

            //总订单数
            MapContext mapContent2 = new MapContext();
            mapContent2.put("queryStartDate", yyyyMM);
            mapContent2.put("queryEndDate", yyyyMM);
            List<Map<String, Object>> orderChartTotal = productDao.findOrderChartTotal(mapContent2);

            Double totalUnits = Double.valueOf(orderChartTotal.get(0).get("totalUnits") + "");
            Double totalMoney = 0d;
            if(totalUnits != 0){
                totalMoney = Double.valueOf(orderChartTotal.get(0).get("totalMoney") + "");
            }
            orderChart.put("totalMoney",LwxfNumberUtils.fomatNumberTwoDigits(totalMoney));
            orderChart.put("totalUnits",totalUnits);
            am.add(orderChart);
        }
        return ResultFactory.generateRequestResult(am);
    }


    /**
     * 年度财务统计
     *
     * @return
     */
    @ApiOperation(value = "年度财务统计", notes = "年度财务统计")
    @GetMapping("/twelveMonthFinance")
    public RequestResult twelveMonthFinance() {

        ArrayList l = new ArrayList();
        int nowMonth = LwxfDateUtils.getnowYear(new Date());
        for(int i=0;i<nowMonth;i++){
            //Date date, String dsdfStr,int forwardNum
            String yyyyMMdd = LwxfDateUtils.getSdfDateMonthBefore(new Date(), "yyyyMM", i);

            //本月数
            String nowMothStr = yyyyMMdd;
            HashMap<Object, Object> nowMoth = new HashMap<>();
            MapContext mapContext = new MapContext();
            mapContext.put("queryStartDate", nowMothStr);
            mapContext.put("queryEndDate", nowMothStr);

            List<Map<String, Object>> orderChartTmp = productDao.findOrderChartTotal(mapContext);
            double totalUnits = Double.valueOf(orderChartTmp.get(0).get("totalUnits") + "");//月总单数
            double totalMoney = 0f;
            if (totalUnits != 0) {
                totalMoney = Double.valueOf(orderChartTmp.get(0).get("totalMoney") + "");//月总金额
            }
            MapContext mapContext1 = new MapContext();


            Integer waiCount = this.customOrderService.findCoordinationCountByTime(mapContext); // 外协数量
            Double waiMoney = this.customOrderService.findCoordinationMoneyByTime(mapContext); // 外协金额

            double dayInt = LwxfDateUtils.getnowMonth(new Date());//本月天数
            double monthAvgOrderNum = totalUnits / dayInt; //日均单数
            double monthAvgOrderTotalMoney = totalMoney / dayInt;//日均金额
            double orderSingleMoney = totalMoney / totalUnits;//月单值
            nowMoth.put("Date",yyyyMMdd);
            nowMoth.put("totalUnits", totalUnits);//总单数
            nowMoth.put("totalMoney",   LwxfNumberUtils.fomatNumberTwoDigits(totalMoney));//订单总金额
            nowMoth.put("monthAvgOrderNum", LwxfNumberUtils.fomatNumberTwoDigits(monthAvgOrderNum)); //日均单数
            nowMoth.put("monthAvgOrderTotalMoney", LwxfNumberUtils.fomatNumberTwoDigits(monthAvgOrderTotalMoney));//日均金额
            nowMoth.put("orderSingleMoney", LwxfNumberUtils.fomatNumberTwoDigits(orderSingleMoney)); //日均单数


            mapContext.put("status", "0");//payment 付款状态 未付款
            List<Map<String, Object>> orderChartTmpNoAudit = productDao.findOrderChartTotal(mapContext);
            Integer totalUnitsNoAudit = Integer.valueOf(orderChartTmpNoAudit.get(0).get("totalUnits") + "");//月总未审核单数
            Double totalMoneyNoAudit = 0d;
            if (totalUnitsNoAudit != 0) {
                totalMoneyNoAudit = Double.valueOf(orderChartTmpNoAudit.get(0).get("totalMoney") + "");//月总未审核金额
            }

            //本月已审核
            Double totalUnitsAudit = totalUnits - totalUnitsNoAudit;//月总审核单数
            Double totalMoneyAudit = totalMoney - totalMoneyNoAudit;//月总审核金额

            mapContext.put("isCoordination", "1");//需要外协 外协单数
            List<Map<String, Object>> outsourceOrderChartTmp = productDao.findOrderChartTotal(mapContext);//外协总单数
            Integer outsourcetalUnits = Integer.valueOf(outsourceOrderChartTmp.get(0).get("totalUnits") + "");//外协总单数

            nowMoth.put("outsourcetalUnits", waiCount);//外协单数
            nowMoth.put("totalUnitsAudit", totalUnitsAudit);//月总审核单数
            nowMoth.put("totalMoneyAudit",  LwxfNumberUtils.fomatNumberTwoDigits(totalMoneyAudit));//月总审核金额
            nowMoth.put("totalUnitsNoAudit", totalUnitsNoAudit);//月总未审核金额
            nowMoth.put("totalMoneyNoAudit",  LwxfNumberUtils.fomatNumberTwoDigits(totalMoneyNoAudit));//月总未审核金额


            //上月数据
            String lastMothStr = LwxfDateUtils.getSdfDateMonthBefore(new Date(), "yyyyMM");//往前推一个月
            String lastlastMothStrOneDay = LwxfDateUtils.getSdfDateMonthBefore(new Date(), "yyyyMM") ;//往前推一个月的月份第一天


            HashMap<Object, Object> lastNowMoth = new HashMap<>();
            MapContext lastmapContext = new MapContext();
            lastmapContext.put("queryStartDate", lastlastMothStrOneDay);
            lastmapContext.put("queryEndDate", lastMothStr);
            List<Map<String, Object>> lastorderChartTmp = productDao.findOrderChartTotal(lastmapContext);
            Double lastTotalUnits = Double.valueOf(lastorderChartTmp.get(0).get("totalUnits") + "");//月总单数
            Double lastTotalMoney = 0d;
            if (lastTotalUnits != 0) {//不存在的情况  TODO
                lastTotalMoney = Double.valueOf(lastorderChartTmp.get(0).get("totalMoney") + "");//月总金额
            }

            int lastdayInt = LwxfDateUtils.getDaysOfMonth(LwxfDateUtils.getDateMonthBefore(new Date(), 1));//上个月天数
            Double lastmonthAvgOrderNum = lastTotalUnits / lastdayInt; //日均单数
            Double lastmonthAvgOrderTotalMoney = lastTotalMoney / lastdayInt;//日均金额
            Double lastorderSingleMoney = lastTotalMoney / lastTotalUnits;//月单值

            lastNowMoth.put("lastTotalUnits", lastTotalUnits);//总单数
            lastNowMoth.put("lastTotalMoney",   LwxfNumberUtils.fomatNumberTwoDigits(lastTotalMoney));//订单总金额
            if(lastmonthAvgOrderNum != 0){
                lastNowMoth.put("monthAvgOrderNum", LwxfNumberUtils.fomatNumberTwoDigits(lastmonthAvgOrderNum)); //日均单数
            }else{
                lastNowMoth.put("monthAvgOrderNum", "0"); //日均单数
            }
            lastNowMoth.put("monthAvgOrderTotalMoney", LwxfNumberUtils.fomatNumberTwoDigits(lastmonthAvgOrderTotalMoney));//日均金额
            lastNowMoth.put("orderSingleMoney", LwxfNumberUtils.fomatNumberTwoDigits(lastorderSingleMoney)); //日均单数

            lastmapContext.put("isCoordination", "1");//需要外协 外协单数
            List<Map<String, Object>> lastOutsourceOrderChartTmp = productDao.findOrderChartTotal(lastmapContext);//外协总单数
            Integer lastOutsourcetalUnits = Integer.valueOf(lastOutsourceOrderChartTmp.get(0).get("totalUnits") + "");//外协总单数
            lastNowMoth.put("lastOutsourcetalUnits", lastOutsourcetalUnits);//外协单数

            mapContext.put("status", "0");//payment 付款状态 未付款
            List<Map<String, Object>> lastOrderChartTmpNoAudit = productDao.findOrderChartTotal(mapContext);
            Integer lastTotalUnitsNoAudit = Integer.valueOf(lastOrderChartTmpNoAudit.get(0).get("totalUnits") + "");//月总未审核单数
            double lastTotalMoneyNoAudit = 0f;

            if (lastTotalUnitsNoAudit != 0) {
                lastTotalMoneyNoAudit = Float.valueOf(lastOrderChartTmpNoAudit.get(0).get("totalMoney") + "");//月总未审核金额
            }

            Double lastTotalUnitsAudit = totalUnits - lastTotalUnitsNoAudit;//月总审核单数
            Double lastTotalMoneyAudit = totalMoney - lastTotalMoneyNoAudit;//月总审核金额

            lastNowMoth.put("lastTotalUnitsAudit",   lastTotalUnitsNoAudit);//月总审核单数
            lastNowMoth.put("lastTotalMoneyAudit",    LwxfNumberUtils.fomatNumberTwoDigits(lastTotalMoneyNoAudit));//月总审核金额
            lastNowMoth.put("lastTotalUnitsNoAudit",    LwxfNumberUtils.fomatNumberTwoDigits(lastTotalUnitsAudit) );//月总未审核金额
            lastNowMoth.put("lastTotalMoneyNoAudit",   LwxfNumberUtils.fomatNumberTwoDigits(lastTotalMoneyAudit));//月总未审核金额


            //本月同比新增
            Double riseUnitsNowMonth = totalUnits - lastTotalUnits;//新增订单数
            Double riseTotalMoneyNowMonth = totalMoney - lastTotalMoney;//新增总金额
            Double riseRateUnitsNowMont = 0d;
            if(lastTotalUnits == 0){
                riseRateUnitsNowMont = 100d;
            }else{
                riseRateUnitsNowMont = riseUnitsNowMonth / lastTotalUnits * 100;//新增订单数比率
            }

            Double riseRateTotalMoneyNowMont = riseTotalMoneyNowMonth / totalMoney * 100;//新增金额比率
            nowMoth.put("riseUnitsNowMonth", riseUnitsNowMonth);//新增订单数
            nowMoth.put("riseTotalMoneyNowMonth",   LwxfNumberUtils.fomatNumberTwoDigits(riseTotalMoneyNowMonth));//新增金额
            nowMoth.put("riseRateUnitsNowMont",  LwxfNumberUtils.fomatNumberTwoDigits( riseRateUnitsNowMont));//新增订单数比率
            nowMoth.put("riseRateTotalMoneyNowMont",LwxfNumberUtils.fomatNumberTwoDigits(riseRateTotalMoneyNowMont));//新增金额比率


            //上上月数据
            String lastlastMothStr = LwxfDateUtils.getSdfDateMonthBefore(new Date(), "yyyyMMdd", 2);//往前推两个月
            String lastMothStrOneDay = LwxfDateUtils.getSdfDateMonthBefore(new Date(), "yyyyMM", 2) + "01";//往前推两个月的月份第一天


            HashMap<Object, Object> lastlastNowMoth = new HashMap<>();
            MapContext lastlastmapContext = new MapContext();
            lastlastmapContext.put("queryStartDate", lastMothStrOneDay);
            lastlastmapContext.put("queryEndDate", lastlastMothStr);
            List<Map<String, Object>> lastlastorderChartTmp = productDao.findOrderChartTotal(lastlastmapContext);
            Double lastlastTotalUnits = Double.valueOf(lastlastorderChartTmp.get(0).get("totalUnits") + "");//月总单数
            Double lastlastTotalMoney = 0d;
            if (lastlastTotalUnits != 0) {
                lastlastTotalMoney = Double.valueOf(lastlastorderChartTmp.get(0).get("totalMoney") + "");//月总金额

            }
            int lastlastdayInt = LwxfDateUtils.getDaysOfMonth(LwxfDateUtils.getDateMonthBefore(new Date(), 2));//上上月天数

            //本月同比新增
            Double lastRiseUnitsNowMonth = lastTotalUnits - lastlastTotalUnits;//新增订单数
            Double lastRiseTotalMoneyNowMonth = lastTotalMoney - lastlastTotalMoney;//新增总金额
            Double lastRiseRateUnitsNowMonth = 0d;
            if (lastTotalUnitsAudit != 0) {
                lastRiseRateUnitsNowMonth = lastRiseUnitsNowMonth / lastTotalUnitsAudit * 100;//新增订单数比率
            } else {
                lastRiseRateUnitsNowMonth = 100d;
            }

            Double lastRiseRateTotalMoneyNowMonth = lastRiseTotalMoneyNowMonth / lastTotalMoneyAudit * 100;//新增金额比率
            lastNowMoth.put("riseUnitsNowMonth", lastRiseUnitsNowMonth);//新增订单数
            lastNowMoth.put("riseTotalMoneyNowMonth",   LwxfNumberUtils.fomatNumberTwoDigits(lastRiseTotalMoneyNowMonth));//新增金额
            lastNowMoth.put("riseRateUnitsNowMont", LwxfNumberUtils.fomatNumberTwoDigits(lastRiseRateUnitsNowMonth));//新增订单数比率
            lastNowMoth.put("lastriseRateTotalMoneyNowMont", LwxfNumberUtils.fomatNumberTwoDigits(lastRiseRateTotalMoneyNowMonth));//新增金额比率

            Double lastavgOrderMoney = 0d;
            if(lastTotalUnits !=0){
                lastavgOrderMoney = lastTotalMoney /lastTotalUnits;
            }
            lastNowMoth.put("lastavgOrderMoney",  LwxfNumberUtils.fomatNumberTwoDigits(lastavgOrderMoney));

            Double avgOrderMoney = 0d;
            if(totalUnits !=0){
                avgOrderMoney = totalMoney /totalUnits;
            }
            lastNowMoth.put("lastavgOrderMoney",LwxfNumberUtils.fomatNumberTwoDigits(avgOrderMoney));
            l.add(nowMoth);
           // l.add(lastNowMoth);
        }
        return ResultFactory.generateRequestResult(l);
    }


    /**
     * 财务报表
     *
     * @return
     */
    @ApiOperation(value = "两个日/月/年对比", notes = "两个日/月/年对比")
    @GetMapping("/twoDayCompara")
    public RequestResult twoDayCompara(String type) {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        if (uid == null) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        ArrayList l = new ArrayList();

        if (type != null && type.equals("3")) {//年
            String queryEndDate = LwxfDateUtils.getSdfDate(new Date(), "yyyy");
            //本年数据
            HashMap<Object, Object> nowYear = new HashMap<>();
            String queryStartDate = (Integer.valueOf(queryEndDate)) + "";
            MapContext mapContext = new MapContext();
            mapContext.put("queryStartDate", queryStartDate);
            mapContext.put("queryEndDate", queryStartDate);

            List<Map<String, Object>> orderChartTmp = productDao.findOrderChartTotal(mapContext);
            double totalUnits = Double.valueOf(orderChartTmp.get(0).get("totalUnits") + "");
            Double totalMoney = Double.valueOf(orderChartTmp.get(0).get("totalMoney") + "");
            int monthInt = LwxfDateUtils.getMonthInt(new Date());//本年月份数
            double monthAvgOrderNum = totalUnits / monthInt; //月均单数
            Double monthAvgOrderTotalMoney = totalMoney / monthInt;//订单单值
            Double orderSingleMoney = totalMoney / totalUnits;

            mapContext.put("isCoordination", 1);//需要外协 外协单数
            List<Map<String, Object>> outsourceOrderChartTmp = productDao.findOrderChartTotal(mapContext);//外协总单数
            float outsourcetalUnits = Float.valueOf(outsourceOrderChartTmp.get(0).get("totalUnits") + "");//外协总单数
            nowYear.put("outsourcetalUnits", outsourcetalUnits);//外协单数

            nowYear.put("totalUnits", totalUnits);//总单数
            nowYear.put("totalMoney",   LwxfNumberUtils.fomatNumberTwoDigits(totalMoney));//订单总金额
            nowYear.put("Date", queryStartDate);//日期
            nowYear.put("monthAvgOrderNum", LwxfNumberUtils.fomatNumberTwoDigits(monthAvgOrderNum));//月均单数
            nowYear.put("monthAvgOrderTotalMoney", LwxfNumberUtils.fomatNumberTwoDigits(monthAvgOrderTotalMoney));//月均金额
            nowYear.put("orderSingleMoney", LwxfNumberUtils.fomatNumberTwoDigits(orderSingleMoney));//订单单值
            //去年数据
            HashMap<Object, Object> lastYear = new HashMap<>();
            String lastYearStr = (Integer.valueOf(queryEndDate) - 1) + "";
            MapContext lastmapContext = new MapContext();
            lastmapContext.put("queryStartDate", lastYearStr);
            lastmapContext.put("queryEndDate", lastYearStr);

            List<Map<String, Object>> lastorderChartTmp = productDao.findOrderChartTotal(lastmapContext);

            Double lasttotalUnits = Double.valueOf(lastorderChartTmp.get(0).get("totalUnits") + "");
            if (lasttotalUnits == 0) {
                lastYear.put("isNULL", "1");//去年没有数据
                lastYear.put("lastriseTotalUnits", "0");
                lastYear.put("lastriseTotalMoney",   "0");
                lastYear.put("lastriseTotalMoneyRate",  "0");
                lastYear.put("lastriseTotalUnitsRate",  "0");
                lastYear.put("lasttotalUnits", "0");//总单数
                lastYear.put("lasttotalMoney",   "0");//订单总金额
                lastYear.put("Date", "0");//日期
                lastYear.put("lastmonthAvgOrderNum", "0");//月均单数
                lastYear.put("lastmonthAvgOrderTotalMoney", "0");//月均金额
                lastYear.put("lastorderSingleMoney", "0");//订单单值


                nowYear.put("riseTotalUnits", "100");
                nowYear.put("riseTotalMoney", "100");
                nowYear.put("riseTotalMoneyRate", "100");
                nowYear.put("riseTotalUnitsRate", "100");

            } else {
                Double lasttotalMoney = Double.valueOf(lastorderChartTmp.get(0).get("totalMoney") + "");
                int lastmonthInt = 12;//去年月份数
                Double lastmonthAvgOrderNum = lasttotalUnits / lastmonthInt; //月均单数
                Double lastmonthAvgOrderTotalMoney = lasttotalMoney / lastmonthInt;//月均金额
                Double lastorderSingleMoney = lasttotalMoney / lasttotalUnits;//订单单值

                lastYear.put("lasttotalUnits", lasttotalUnits);//总单数
                lastYear.put("lasttotalMoney",   LwxfNumberUtils.fomatNumberTwoDigits(lasttotalMoney));//订单总金额
                lastYear.put("Date", queryStartDate);//日期
                lastYear.put("lastmonthAvgOrderNum", LwxfNumberUtils.fomatNumberTwoDigits(lastmonthAvgOrderNum));//月均单数
                lastYear.put("lastmonthAvgOrderTotalMoney", LwxfNumberUtils.fomatNumberTwoDigits(lastmonthAvgOrderTotalMoney));//月均金额
                lastYear.put("lastorderSingleMoney", LwxfNumberUtils.fomatNumberTwoDigits(lastorderSingleMoney));//订单单值


                //前年数据
                HashMap<Object, Object> lastlastYear = new HashMap<>();
                String lastlastYearStr = (Integer.valueOf(lastYearStr) - 1) + "";
                MapContext lastlastmapContext = new MapContext();
                mapContext.put("queryStartDate", lastlastYearStr);
                mapContext.put("queryEndDate", lastlastYearStr);

                List<Map<String, Object>> lastlastorderChartTmp = productDao.findOrderChartTotal(mapContext);
                Integer lastlasttotalUnits = Integer.valueOf(lastlastorderChartTmp.get(0).get("totalUnits") + "");
                Integer lastlasttotalMoney = 0;
                if(lastlasttotalUnits != 0){
                    lastlasttotalMoney = Integer.valueOf(lastlastorderChartTmp.get(0).get("totalMoney") + "");
                }

                //本年增长数据
                Double riseTotalUnits = totalUnits - lasttotalUnits ;//同比新增订单数
                Double riseTotalMoney = totalMoney - lasttotalMoney  ;//同比新增金额
                Double riseTotalMoneyRate = riseTotalMoney / lasttotalMoney * 100;//同比新增订单数量比
                Double riseTotalUnitsRate = riseTotalUnits / lasttotalUnits * 100;//同比新增金额比
                nowYear.put("riseTotalUnits", riseTotalUnits);
                nowYear.put("riseTotalMoney",   LwxfNumberUtils.fomatNumberTwoDigits(riseTotalMoney));
                nowYear.put("riseTotalMoneyRate",  LwxfNumberUtils.fomatNumberTwoDigits(riseTotalMoneyRate));
                nowYear.put("riseTotalUnitsRate", LwxfNumberUtils.fomatNumberTwoDigits(riseTotalUnitsRate));

                //去年增长数据
                Double lastriseTotalUnits = lastlasttotalUnits - lasttotalUnits;//同比新增订单数
                Double lastriseTotalMoney = lastlasttotalMoney - lasttotalMoney;//同比新增金额
                Double lastriseTotalMoneyRate = lastriseTotalMoney / lastlasttotalMoney * 100;//同比新增订单数量比
                Double lastriseTotalUnitsRate = lastriseTotalUnits / lastlasttotalUnits * 100;//同比新增金额比
                lastYear.put("lastriseTotalUnits", lastriseTotalUnits);
                lastYear.put("lastriseTotalMoney",   LwxfNumberUtils.fomatNumberTwoDigits(lastriseTotalMoney));
                lastYear.put("lastriseTotalMoneyRate",  LwxfNumberUtils.fomatNumberTwoDigits(lastriseTotalMoneyRate));
                lastYear.put("lastriseTotalUnitsRate",  LwxfNumberUtils.fomatNumberTwoDigits(lastriseTotalUnitsRate));
            }
            l.add(nowYear);
            l.add(lastYear);
        } else if (type != null && type.equals("2")) {//月份同比
            String queryEndDate = LwxfDateUtils.getSdfDate(new Date(), "yyyyMMdd");
            String queryEndDate2 = LwxfDateUtils.getSdfDate(new Date(), "yyyyMM");

            //本月数
            String nowMothStr = queryEndDate;
            HashMap<Object, Object> nowMoth = new HashMap<>();
            MapContext mapContext = new MapContext();
            mapContext.put("queryStartDate", queryEndDate2);
            mapContext.put("queryEndDate", queryEndDate2);

            List<Map<String, Object>> orderChartTmp = productDao.findOrderChartTotal(mapContext);
            double totalUnits = Double.valueOf(orderChartTmp.get(0).get("totalUnits") + "");//月总单数
            double totalMoney = 0f;
            if (totalUnits != 0) {
                totalMoney = Double.valueOf(orderChartTmp.get(0).get("totalMoney") + "");//月总金额
            }
            MapContext mapContext1 = new MapContext();


            Integer waiCount = this.customOrderService.findCoordinationCountByTime(mapContext); // 外协数量
            Double waiMoney = this.customOrderService.findCoordinationMoneyByTime(mapContext); // 外协金额

            double dayInt = LwxfDateUtils.getnowMonth(new Date());//本月天数
            double monthAvgOrderNum = totalUnits / dayInt; //日均单数
            double monthAvgOrderTotalMoney = totalMoney / dayInt;//日均金额
            double orderSingleMoney = totalMoney / totalUnits;//月单值
            nowMoth.put("totalUnits", totalUnits);//总单数
            nowMoth.put("totalMoney",   LwxfNumberUtils.fomatNumberTwoDigits(totalMoney));//订单总金额
            nowMoth.put("monthAvgOrderNum", LwxfNumberUtils.fomatNumberTwoDigits(monthAvgOrderNum)); //日均单数
            nowMoth.put("monthAvgOrderTotalMoney", LwxfNumberUtils.fomatNumberTwoDigits(monthAvgOrderTotalMoney));//日均金额
            nowMoth.put("orderSingleMoney", LwxfNumberUtils.fomatNumberTwoDigits(orderSingleMoney)); //日均单数


            mapContext.put("status", "0");//payment 付款状态 未付款
            List<Map<String, Object>> orderChartTmpNoAudit = productDao.findOrderChartTotal(mapContext);
            Integer totalUnitsNoAudit = Integer.valueOf(orderChartTmpNoAudit.get(0).get("totalUnits") + "");//月总未审核单数
            Double totalMoneyNoAudit = 0d;
            if (totalUnitsNoAudit != 0) {
                totalMoneyNoAudit = Double.valueOf(orderChartTmpNoAudit.get(0).get("totalMoney") + "");//月总未审核金额
            }

            //本月已审核
            Double totalUnitsAudit = totalUnits - totalUnitsNoAudit;//月总审核单数
            Double totalMoneyAudit = totalMoney - totalMoneyNoAudit;//月总审核金额

            mapContext.put("isCoordination", "1");//需要外协 外协单数
            List<Map<String, Object>> outsourceOrderChartTmp = productDao.findOrderChartTotal(mapContext);//外协总单数
            Integer outsourcetalUnits = Integer.valueOf(outsourceOrderChartTmp.get(0).get("totalUnits") + "");//外协总单数

            nowMoth.put("outsourcetalUnits", waiCount);//外协单数
            nowMoth.put("totalUnitsAudit", totalUnitsAudit);//月总审核单数
            nowMoth.put("totalMoneyAudit",  LwxfNumberUtils.fomatNumberTwoDigits(totalMoneyAudit));//月总审核金额
            nowMoth.put("totalUnitsNoAudit", totalUnitsNoAudit);//月总未审核金额
            nowMoth.put("totalMoneyNoAudit",  LwxfNumberUtils.fomatNumberTwoDigits(totalMoneyNoAudit));//月总未审核金额


            //上月数据
            String lastMothStr = LwxfDateUtils.getSdfDateMonthBefore(new Date(), "yyyyMM");//往前推一个月
            String lastlastMothStrOneDay = LwxfDateUtils.getSdfDateMonthBefore(new Date(), "yyyyMM") ;//往前推一个月的月份第一天


            HashMap<Object, Object> lastNowMoth = new HashMap<>();
            MapContext lastmapContext = new MapContext();
            lastmapContext.put("queryStartDate", lastlastMothStrOneDay);
            lastmapContext.put("queryEndDate", lastMothStr);
            List<Map<String, Object>> lastorderChartTmp = productDao.findOrderChartTotal(lastmapContext);
            Double lastTotalUnits = Double.valueOf(lastorderChartTmp.get(0).get("totalUnits") + "");//月总单数
            Double lastTotalMoney = 0d;
            if (lastTotalUnits != 0) {//不存在的情况  TODO
                lastTotalMoney = Double.valueOf(lastorderChartTmp.get(0).get("totalMoney") + "");//月总金额
            }

            int lastdayInt = LwxfDateUtils.getDaysOfMonth(LwxfDateUtils.getDateMonthBefore(new Date(), 1));//上个月天数
            Double lastmonthAvgOrderNum = lastTotalUnits / lastdayInt; //日均单数
            Double lastmonthAvgOrderTotalMoney = lastTotalMoney / lastdayInt;//日均金额
            Double lastorderSingleMoney = lastTotalMoney / lastTotalUnits;//月单值

            lastNowMoth.put("lastTotalUnits", lastTotalUnits);//总单数
            lastNowMoth.put("lastTotalMoney",   LwxfNumberUtils.fomatNumberTwoDigits(lastTotalMoney));//订单总金额
            if(lastmonthAvgOrderNum != 0){
                lastNowMoth.put("monthAvgOrderNum", LwxfNumberUtils.fomatNumberTwoDigits(lastmonthAvgOrderNum)); //日均单数
            }else{
                lastNowMoth.put("monthAvgOrderNum", "0"); //日均单数
            }
            lastNowMoth.put("monthAvgOrderTotalMoney", LwxfNumberUtils.fomatNumberTwoDigits(lastmonthAvgOrderTotalMoney));//日均金额
            lastNowMoth.put("orderSingleMoney", LwxfNumberUtils.fomatNumberTwoDigits(lastorderSingleMoney)); //日均单数

            lastmapContext.put("isCoordination", "1");//需要外协 外协单数
            List<Map<String, Object>> lastOutsourceOrderChartTmp = productDao.findOrderChartTotal(lastmapContext);//外协总单数
            Integer lastOutsourcetalUnits = Integer.valueOf(lastOutsourceOrderChartTmp.get(0).get("totalUnits") + "");//外协总单数
            lastNowMoth.put("lastOutsourcetalUnits", lastOutsourcetalUnits);//外协单数

            mapContext.put("status", "0");//payment 付款状态 未付款
            List<Map<String, Object>> lastOrderChartTmpNoAudit = productDao.findOrderChartTotal(mapContext);
            Integer lastTotalUnitsNoAudit = Integer.valueOf(lastOrderChartTmpNoAudit.get(0).get("totalUnits") + "");//月总未审核单数
            double lastTotalMoneyNoAudit = 0f;

            if (lastTotalUnitsNoAudit != 0) {
                lastTotalMoneyNoAudit = Float.valueOf(lastOrderChartTmpNoAudit.get(0).get("totalMoney") + "");//月总未审核金额
            }

            Double lastTotalUnitsAudit = totalUnits - lastTotalUnitsNoAudit;//月总审核单数
            Double lastTotalMoneyAudit = totalMoney - lastTotalMoneyNoAudit;//月总审核金额

            lastNowMoth.put("lastTotalUnitsAudit",   lastTotalUnitsNoAudit);//月总审核单数
            lastNowMoth.put("lastTotalMoneyAudit",    LwxfNumberUtils.fomatNumberTwoDigits(lastTotalMoneyNoAudit));//月总审核金额
            lastNowMoth.put("lastTotalUnitsNoAudit",    LwxfNumberUtils.fomatNumberTwoDigits(lastTotalUnitsAudit) );//月总未审核金额
            lastNowMoth.put("lastTotalMoneyNoAudit",   LwxfNumberUtils.fomatNumberTwoDigits(lastTotalMoneyAudit));//月总未审核金额


            //本月同比新增
            Double riseUnitsNowMonth = totalUnits - lastTotalUnits;//新增订单数
            Double riseTotalMoneyNowMonth = totalMoney - lastTotalMoney;//新增总金额
            Double riseRateUnitsNowMont = 0d;
            if(lastTotalUnits == 0){
                 riseRateUnitsNowMont = 100d;
            }else{
                 riseRateUnitsNowMont = riseUnitsNowMonth / lastTotalUnits * 100;//新增订单数比率
            }

            Double riseRateTotalMoneyNowMont = riseTotalMoneyNowMonth / totalMoney * 100;//新增金额比率
            nowMoth.put("riseUnitsNowMonth", riseUnitsNowMonth);//新增订单数
            nowMoth.put("riseTotalMoneyNowMonth",   LwxfNumberUtils.fomatNumberTwoDigits(riseTotalMoneyNowMonth));//新增金额
            nowMoth.put("riseRateUnitsNowMont",  LwxfNumberUtils.fomatNumberTwoDigits( riseRateUnitsNowMont));//新增订单数比率
            nowMoth.put("riseRateTotalMoneyNowMont",LwxfNumberUtils.fomatNumberTwoDigits(riseRateTotalMoneyNowMont));//新增金额比率


            //上上月数据
            String lastlastMothStr = LwxfDateUtils.getSdfDateMonthBefore(new Date(), "yyyyMMdd", 2);//往前推两个月
            String lastMothStrOneDay = LwxfDateUtils.getSdfDateMonthBefore(new Date(), "yyyyMM", 2) + "01";//往前推两个月的月份第一天


            HashMap<Object, Object> lastlastNowMoth = new HashMap<>();
            MapContext lastlastmapContext = new MapContext();
            lastlastmapContext.put("queryStartDate", lastMothStrOneDay);
            lastlastmapContext.put("queryEndDate", lastlastMothStr);
            List<Map<String, Object>> lastlastorderChartTmp = productDao.findOrderChartTotal(lastlastmapContext);
            Double lastlastTotalUnits = Double.valueOf(lastlastorderChartTmp.get(0).get("totalUnits") + "");//月总单数
            Double lastlastTotalMoney = 0d;
            if (lastlastTotalUnits != 0) {
                lastlastTotalMoney = Double.valueOf(lastlastorderChartTmp.get(0).get("totalMoney") + "");//月总金额

            }
            int lastlastdayInt = LwxfDateUtils.getDaysOfMonth(LwxfDateUtils.getDateMonthBefore(new Date(), 2));//上上月天数

            //本月同比新增
            Double lastRiseUnitsNowMonth = lastTotalUnits - lastlastTotalUnits;//新增订单数
            Double lastRiseTotalMoneyNowMonth = lastTotalMoney - lastlastTotalMoney;//新增总金额
            Double lastRiseRateUnitsNowMonth = 0d;
            if (lastTotalUnitsAudit != 0) {
                lastRiseRateUnitsNowMonth = lastRiseUnitsNowMonth / lastTotalUnitsAudit * 100;//新增订单数比率
            } else {
                lastRiseRateUnitsNowMonth = 100d;
            }

            Double lastRiseRateTotalMoneyNowMonth = lastRiseTotalMoneyNowMonth / lastTotalMoneyAudit * 100;//新增金额比率
            lastNowMoth.put("riseUnitsNowMonth", lastRiseUnitsNowMonth);//新增订单数
            lastNowMoth.put("riseTotalMoneyNowMonth",   LwxfNumberUtils.fomatNumberTwoDigits(lastRiseTotalMoneyNowMonth));//新增金额
            lastNowMoth.put("riseRateUnitsNowMont", LwxfNumberUtils.fomatNumberTwoDigits(lastRiseRateUnitsNowMonth));//新增订单数比率
            lastNowMoth.put("lastriseRateTotalMoneyNowMont", LwxfNumberUtils.fomatNumberTwoDigits(lastRiseRateTotalMoneyNowMonth));//新增金额比率

            Double lastavgOrderMoney = 0d;
            if(lastTotalUnits !=0){
                lastavgOrderMoney = lastTotalMoney /lastTotalUnits;
            }
            lastNowMoth.put("lastavgOrderMoney",  LwxfNumberUtils.fomatNumberTwoDigits(lastavgOrderMoney));

            Double avgOrderMoney = 0d;
            if(totalUnits !=0){
                avgOrderMoney = totalMoney /totalUnits;
            }
            lastNowMoth.put("lastavgOrderMoney",LwxfNumberUtils.fomatNumberTwoDigits(avgOrderMoney));
            nowMoth.put("date", LwxfDateUtils.getSdfDate(new Date(), "yyyy.MM"));
            lastNowMoth.put("date", LwxfDateUtils.getSdfDate(LwxfDateUtils.getDateMonthBefore(new Date(), 1), "yyy.MM") );
            l.add(nowMoth);
            l.add(lastNowMoth);
        } else if (type != null && type.equals("1")) {//天 对比数据
            //今天数据
            String nowMothStr = LwxfDateUtils.getSdfDate(DateUtil.now(), "yyyyMMdd");
            HashMap<Object, Object> nowday = new HashMap<>();
             MapContext mapContext = new MapContext();
            mapContext.put("queryStartDate", nowMothStr);
            mapContext.put("queryEndDate", nowMothStr);
            List<Map<String, Object>> orderChartTmp = productDao.findOrderChartTotal(mapContext);
            Integer dayTotalUnits = Integer.valueOf(orderChartTmp.get(0).get("totalUnits") + "");//今天总单数
            Double dayTotalMoney = 0d;
            if (dayTotalUnits != 0) {
                dayTotalMoney = Double.valueOf(orderChartTmp.get(0).get("totalMoney") + "");//今天总金额
            }

            nowday.put("dayTotalUnits", dayTotalUnits);//总单数
            nowday.put("dayTotalMoney",   LwxfNumberUtils.fomatNumberTwoDigits(dayTotalMoney));//订单总金额

            mapContext.put("status", "0");//payment 付款状态 未付款
            List<Map<String, Object>> orderChartTmpNoAudit = productDao.findOrderChartTotal(mapContext);
            Double totalUnitsNoAudit = Double.valueOf(orderChartTmpNoAudit.get(0).get("totalUnits") + "");//未审核单数
            Double totalMoneyNoAudit = 0d;
            if (totalUnitsNoAudit != 0) {
                totalMoneyNoAudit = Double.valueOf(orderChartTmpNoAudit.get(0).get("totalMoney") + "");//未审核金额
            }
            //今天
            Double totalUnitsAudit = dayTotalUnits - totalUnitsNoAudit;//审核单数
            Double totalMoneyAudit = dayTotalMoney - totalMoneyNoAudit;//审核金额

            mapContext.put("status", null);//payment 付款状态 未付款
            mapContext.put("isCoordination", "1");//需要外协 外协单数
            List<Map<String, Object>> outsourceOrderChartTmp = productDao.findOrderChartTotal(mapContext);//外协总单数
            Integer outsourcetalUnits = Integer.valueOf(outsourceOrderChartTmp.get(0).get("totalUnits") + "");//外协总单数
            nowday.put("outsourcetalUnits", outsourcetalUnits);//外协单数
            nowday.put("totalUnitsAudit", totalUnitsAudit);//月总审核单数
            nowday.put("totalMoneyAudit", totalMoneyAudit);//月总审核金额
            nowday.put("totalUnitsNoAudit", totalUnitsNoAudit);//月总未审核金额
            nowday.put("totalMoneyNoAudit", totalMoneyNoAudit);//月总未审核金额

            //昨天数据
            String lastDayStr = LwxfDateUtils.getSdfDateDayBefore(new Date(), "yyyyMMdd", 1);
            HashMap<Object, Object> lastDay = new HashMap<>();
            MapContext lastDaymapContext = new MapContext();
            lastDaymapContext.put("queryStartDate", lastDayStr);
            lastDaymapContext.put("queryEndDate", lastDayStr);
            List<Map<String, Object>> LastDayOrderChartTmp = productDao.findOrderChartTotal(lastDaymapContext);
            Double lastDayTotalUnits = Double.valueOf(LastDayOrderChartTmp.get(0).get("totalUnits") + "");//月总单数
            Double lastDayTotalMoney = 0d;
            if (lastDayTotalUnits != 0) {
                lastDayTotalMoney = Double.valueOf(LastDayOrderChartTmp.get(0).get("totalMoney") + "");//月总金额

            }

            lastDay.put("lastDayTotalUnits", lastDayTotalUnits);//总单数
            lastDay.put("lastDayTotalMoney", lastDayTotalMoney);//订单总金额

            lastDaymapContext.put("status", "0");//payment 付款状态 未付款
            List<Map<String, Object>> lastOrderChartTmpNoAudit = productDao.findOrderChartTotal(lastDaymapContext);
            Double lastTotalUnitsNoAudit = Double.valueOf(lastOrderChartTmpNoAudit.get(0).get("totalUnits") + "");//未审核单数
            Double lastTotalMoneyNoAudit = 0d;
            if (lastTotalUnitsNoAudit != 0) {
                lastTotalMoneyNoAudit = Double.valueOf(lastOrderChartTmpNoAudit.get(0).get("totalMoney") + "");//未审核金额

            }

            //昨天已审核
            Double lastTotalUnitsAudit = lastDayTotalUnits - lastTotalUnitsNoAudit;//审核单数
            Double lastTotalMoneyAudit = lastDayTotalMoney - lastTotalMoneyNoAudit;//审核金额
            lastDay.put("lastTotalUnitsAudit", lastTotalUnitsAudit);
            lastDay.put("lastTotalMoneyAudit",   LwxfNumberUtils.fomatNumberTwoDigits(lastTotalMoneyAudit));
            lastDaymapContext.put("status", null);//payment 付款状态 未付款
            lastDaymapContext.put("isCoordination", "1");//需要外协 外协单数
            List<Map<String, Object>> lastOutsourceOrderChartTmp = productDao.findOrderChartTotal(lastDaymapContext);//外协总单数
            Integer lastOutsourcetalUnits = Integer.valueOf(lastOutsourceOrderChartTmp.get(0).get("totalUnits") + "");//外协总单数
            lastDay.put("outsourcetalUnits", outsourcetalUnits);//外协单数
            lastDay.put("totalUnitsAudit", lastTotalUnitsAudit);//昨天审核单数
            lastDay.put("totalMoneyAudit",   LwxfNumberUtils.fomatNumberTwoDigits(lastTotalMoneyAudit));//昨天审核金额
            lastDay.put("totalUnitsNoAudit", lastTotalUnitsNoAudit);//昨天未审核单数
            lastDay.put("totalMoneyNoAudit",   LwxfNumberUtils.fomatNumberTwoDigits(lastTotalMoneyNoAudit));//昨天未审核金额

            //当月增长数据
            Double riseTotalUnits = dayTotalUnits - lastDayTotalUnits;//当日增长数量
            Double riseRateTotalUnits = 0d;
            if (lastDayTotalUnits != 0) {
                riseRateTotalUnits = riseTotalUnits / lastDayTotalUnits * 100;//当日增长率
            } else {
                riseRateTotalUnits = 100d;
            }
            Double riseTotalMoney = Double.valueOf(dayTotalMoney - lastDayTotalMoney);
            Double riseRateTotalMoney = 100d;
            if(dayTotalMoney!=0 && riseTotalMoney !=0){
                riseRateTotalMoney = riseTotalMoney / dayTotalMoney * 100;
            }

            nowday.put("riseTotalUnits", Double.valueOf(riseTotalUnits));
            nowday.put("riseRateTotalUnits", LwxfNumberUtils.fomatNumberTwoDigits(riseRateTotalUnits));
            nowday.put("riseTotalMoney",   LwxfNumberUtils.fomatNumberTwoDigits(riseTotalMoney));
            nowday.put("riseRateTotalMoney", LwxfNumberUtils.fomatNumberTwoDigits(riseRateTotalMoney));

            //前天数据
            String lastlastDayStr = LwxfDateUtils.getSdfDateDayBefore(new Date(), "yyyyMMdd", 2);
            HashMap<Object, Object> lastlastDay = new HashMap<>();
            MapContext lastlastDaymapContext = new MapContext();
            lastlastDaymapContext.put("queryStartDate", lastlastDayStr);
            lastlastDaymapContext.put("queryEndDate", lastlastDayStr);
            List<Map<String, Object>> lastlastDayOrderChartTmp = productDao.findOrderChartTotal(lastlastDaymapContext);
            Double lastlastDayTotalUnits = Double.valueOf(lastlastDayOrderChartTmp.get(0).get("totalUnits") + "");//月总单数
            Double lastlastDayTotalMoney = 0d;
            if (lastlastDayTotalUnits != 0) {
                lastlastDayTotalMoney = Double.valueOf(lastlastDayOrderChartTmp.get(0).get("totalMoney") + "");//月总金额

            }


            //昨天增长数据
            Double lastriseTotalUnits = lastDayTotalUnits - lastlastDayTotalUnits;//昨天增长数量
            Double lastriseRateTotalUnits = 0d;
            if (lastlastDayTotalUnits != 0) {
                lastriseRateTotalUnits = lastriseTotalUnits / lastlastDayTotalUnits * 100;//昨天增长率

            } else {
                lastriseRateTotalUnits = 100d;
            }
            Double lastriseTotalMoney = lastlastDayTotalMoney - lastDayTotalMoney;//
            Double lastriseRateTotalMoney = lastriseRateTotalUnits / lastlastDayTotalMoney * 100;
            lastDay.put("lastriseTotalUnits", lastriseTotalUnits);
            lastDay.put("lastriseRateTotalUnits", LwxfNumberUtils.fomatNumberTwoDigits(lastriseRateTotalUnits));
            lastDay.put("lastriseTotalMoney",   LwxfNumberUtils.fomatNumberTwoDigits(lastriseTotalMoney));
            lastDay.put("lastriseRateTotalMoney", LwxfNumberUtils.fomatNumberTwoDigits(lastriseRateTotalMoney));
            Double avgOrderMoney = 0d;
            if(dayTotalUnits !=0){
                avgOrderMoney = dayTotalMoney /dayTotalUnits;
            }
            nowday.put("avgOrderMoney",LwxfNumberUtils.fomatNumberTwoDigits(avgOrderMoney));

            Double lastavgOrderMoney = 0d;
            if(lastDayTotalUnits !=0){
                lastavgOrderMoney = lastDayTotalMoney /= lastDayTotalUnits;
            }
            lastDay.put("lastavgOrderMoney", LwxfNumberUtils.fomatNumberTwoDigits(lastavgOrderMoney));
            nowday.put("date",LwxfDateUtils.getSdfDate(DateUtil.now(), "yyyy.MM.dd"));
            l.add(nowday);
            lastDay.put("date",LwxfDateUtils.getSdfDateDayBefore(new Date(),"yyyy.MM.dd",1 ));
            l.add(lastDay);
        }
        return ResultFactory.generateRequestResult(l);
    }


    @ApiOperation(value = "当月/当年统计", notes = "当月/当年统计")
    @GetMapping("/monthYearRegional")
    public RequestResult monthYearRegional(String queryStartDate, String queryEndDate) {
        WebUtils.getCurrCompanyId();
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        String companyId = mapInfo.get("companyId") == null ? null : mapInfo.get("companyId").toString();
        if (uid == null) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        //company_id 经销商id
        MapContext mapContext = new MapContext();
        mapContext.put("queryStartDate", queryStartDate);
        mapContext.put("queryEndDate", queryEndDate);
        mapContext.put("companyId", companyId);
        return this.dataChartService.findOrderChart(mapContext);
    }

//    @ApiOperation(value = "当月/当年统计", notes = "当月/当年统计")
//    @GetMapping("/DateRegional")
//    public RequestResult DateRegional(String queryStartDate,String queryEndDate) {
//        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
//        MapContext mapInfo = LoginUtil.checkLogin(atoken);
//        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
//        String companyId = mapInfo.get("companyId") == null ? null : mapInfo.get("companyId").toString();
//        if (uid == null) {
//            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
//        }
//        MapContext mapContext = new MapContext();
//        mapContext.put("queryEndDate",queryEndDate);
//        mapContext.put("queryStartDate",queryStartDate);
//        mapContext.put("companyId",companyId);
//        return this.dataChartService.findOrderChart(mapContext);
//    }

    @ApiOperation(value = "系列查询", notes = "系列查询")
    @GetMapping("/findOrderChartByseries")
    public RequestResult findOrderChartByseries(String orderProduct, String queryStartDate, String queryEndDate) {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        String companyId = mapInfo.get("companyId") == null ? null : mapInfo.get("companyId").toString();
        if (uid == null) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        MapContext mapContext = new MapContext();
        mapContext.put("orderProduct", orderProduct);
        mapContext.put("companyId", companyId);
        mapContext.put("queryStartDate", queryStartDate);
        mapContext.put("queryEndDate", queryEndDate);
        return this.dataChartService.findOrderChartByseries(mapContext);
    }


    @ApiOperation(value = "门型统计查询", notes = "门型统计查询")
    @GetMapping("/findOrderChartByDoor")
    public RequestResult findOrderChartByDoor(String orderProduct, String queryStartDate, String queryEndDate) {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String companyId = mapInfo.get("companyId") == null ? null : mapInfo.get("companyId").toString();
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        if (uid == null) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        MapContext mapContext = new MapContext();
        mapContext.put("orderProduct", orderProduct);
        mapContext.put("companyId", companyId);
        mapContext.put("queryStartDate", queryStartDate);
        mapContext.put("queryEndDate", queryEndDate);
        return this.dataChartService.findOrderChartByDoor(mapContext);
    }


    @ApiOperation(value = "F端门型统计查询", notes = "F端门型统计查询")
    @GetMapping("/findOrderChartByDoorf")
    public RequestResult findOrderChartByDoorf(String queryStartDate) {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String companyId = mapInfo.get("companyId") == null ? null : mapInfo.get("companyId").toString();
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        if (uid == null) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        MapContext mapContext = new MapContext();
        mapContext.put("queryStartDate", queryStartDate);
        mapContext.put("queryEndDate", queryStartDate);
        return this.dataChartService.findOrderChartByDoor(mapContext);
    }

    @ApiOperation(value = "柜体颜色统计查询", notes = "柜体颜色统计查询")
    @GetMapping("/findOrderChartByColor")
    public RequestResult findOrderChartByColor(String orderProduct, String queryStartDate, String queryEndDate) {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String companyId = mapInfo.get("companyId") == null ? null : mapInfo.get("companyId").toString();
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        if (uid == null) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        MapContext mapContext = new MapContext();
        mapContext.put("orderProduct", orderProduct);
        mapContext.put("companyId", companyId);
        mapContext.put("queryStartDate", queryStartDate);
        mapContext.put("queryEndDate", queryEndDate);
        return this.dataChartService.findOrderChartByColor(mapContext);
    }

    @ApiOperation(value = "F端门板颜色统计查询", notes = "F端门板颜色统计查询")
    @GetMapping("/findOrderChartByColorF")
    public RequestResult findOrderChartByColorF(String queryStartDate) {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        if (uid == null) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        MapContext mapContext = new MapContext();
        mapContext.put("queryStartDate", queryStartDate);
        mapContext.put("queryEndDate", queryStartDate);
        return this.dataChartService.findOrderChartByColor(mapContext);
    }

    @ApiOperation(value = "F端本月订单财务统计", notes = "F端本月订单财务统计")
    @GetMapping("/findOrderFinancialStatisticsF")
    public RequestResult findOrderFinancialStatisticsF() {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        if (uid == null) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        ArrayList l = new ArrayList();

        int daysOfMonth = LwxfDateUtils.getnowMonth(new Date());
        Date date = new Date();
        for (int i = 0; i < daysOfMonth; i++) {
            String queryEndDate = LwxfDateUtils.getSdfDateDayBefore(date, "yyyyMMdd", i);
            Date dateMonthBefore = LwxfDateUtils.getDateMonthBefore(date, i);
            MapContext mapContext1 = new MapContext();
            mapContext1.put("startTime",dateMonthBefore);
            mapContext1.put("endTime",dateMonthBefore);
            Integer waiCount = this.customOrderService.findCoordinationCountByTime(mapContext1); // 外协数量
            Double waiMoney = this.customOrderService.findCoordinationMoneyByTime(mapContext1); // 外协金额

            //本月数
            String nowMothStr = queryEndDate;
            HashMap<Object, Object> nowMoth = new HashMap<>();
            nowMoth.put("queryEndDate", queryEndDate);
            MapContext mapContext = new MapContext();
            mapContext.put("queryStartDate", nowMothStr);
            mapContext.put("queryEndDate", nowMothStr);
             List<Map<String, Object>> orderChartTmp = productDao.findOrderChartTotal(mapContext);
            Double totalUnits = Double.valueOf(orderChartTmp.get(0).get("totalUnits") + "");//月总单数
            Double totalMoney = 0d;
            if (totalUnits != 0) {
                totalMoney = Double.valueOf(orderChartTmp.get(0).get("totalMoney") + "");//月总金额
            }
            int dayInt = LwxfDateUtils.getDaysOfMonth(new Date());//本月天数
            Double monthAvgOrderNum = totalUnits / dayInt; //日均单数
            Double monthAvgOrderTotalMoney = totalMoney / dayInt;//日均金额
            Double orderSingleMoney = totalMoney / totalUnits;//月单值
            nowMoth.put("totalUnits", totalUnits );//总单数
            nowMoth.put("totalMoney",   LwxfNumberUtils.fomatNumberTwoDigits(totalMoney));//订单总金额
            nowMoth.put("monthAvgOrderNum", LwxfNumberUtils.fomatNumberTwoDigits(monthAvgOrderNum)); //日均单数
            nowMoth.put("monthAvgOrderTotalMoney", LwxfNumberUtils.fomatNumberTwoDigits(monthAvgOrderTotalMoney));//日均金额
            nowMoth.put("orderSingleMoney", LwxfNumberUtils.fomatNumberTwoDigits(orderSingleMoney)); //日均单数
            mapContext.put("status", "0");//payment 付款状态 未付款
            List<Map<String, Object>> orderChartTmpNoAudit = productDao.findOrderChartTotal(mapContext);
            Double totalUnitsNoAudit = Double.valueOf(orderChartTmpNoAudit.get(0).get("totalUnits") + "");//月总未审核单数
            Double totalMoneyNoAudit = 0d;
            if (totalUnitsNoAudit != 0) {
                totalMoneyNoAudit = Double.valueOf(orderChartTmpNoAudit.get(0).get("totalMoney") + "");//月总未审核金额
            }
            //本月已审核
            Double totalUnitsAudit = totalUnits - totalUnitsNoAudit;//月总审核单数
            Double totalMoneyAudit = totalMoney - totalMoneyNoAudit;//月总审核金额
            mapContext.put("isCoordination", "1");//需要外协 外协单数
            nowMoth.put("outsourcetalUnits", waiCount);//外协单数
            nowMoth.put("outsourcetalTotalMoney",  LwxfNumberUtils.fomatNumberTwoDigits(waiMoney));//外协金额
            nowMoth.put("totalUnitsAudit", totalUnitsAudit );//月总审核单数
            nowMoth.put("totalMoneyAudit",   LwxfNumberUtils.fomatNumberTwoDigits(totalMoneyAudit));//月总审核金额
            nowMoth.put("totalUnitsNoAudit",   LwxfNumberUtils.fomatNumberTwoDigits(totalUnitsNoAudit));//月总未审核金额
            nowMoth.put("totalMoneyNoAudit",   LwxfNumberUtils.fomatNumberTwoDigits(totalMoneyNoAudit));//月总未审核金额
            Double avgOrderMoney = 0d;
            if(totalUnits !=0){
                 avgOrderMoney = totalMoney /totalUnits;
            }
            nowMoth.put("avgOrderMoney",LwxfNumberUtils.fomatNumberTwoDigits(avgOrderMoney));
            //上月数据
            //String lastMothStr = LwxfDateUtils.getSdfDateMonthBefore(new Date(), "yyyyMMdd");//往前推一个月

            String lastlastMothStrOneDay = LwxfDateUtils.getSdfDateDayBefore(new Date(), "yyyyMMdd",i+1);//往前推一个月的月份第一天
            HashMap<Object, Object> lastNowMoth = new HashMap<>();
            MapContext lastmapContext = new MapContext();
            lastmapContext.put("queryStartDate", lastlastMothStrOneDay);
            lastmapContext.put("queryEndDate", lastlastMothStrOneDay);
            List<Map<String, Object>> lastorderChartTmp = productDao.findOrderChartTotal(lastmapContext);
            Integer lastTotalUnits = Integer.valueOf(lastorderChartTmp.get(0).get("totalUnits") + "");//月总单数
            Double lastTotalMoney = 0d;
            if (lastTotalUnits != 0) {//不存在的情况  TODO
                lastTotalMoney = Double.valueOf(lastorderChartTmp.get(0).get("totalMoney") + "");//月总金额
            }
            int lastdayInt = LwxfDateUtils.getDaysOfMonth(LwxfDateUtils.getDateMonthBefore(new Date(), 1));//上个月天数
            double lastmonthAvgOrderNum = lastTotalUnits / lastdayInt; //日均单数
            Double lastmonthAvgOrderTotalMoney = lastTotalMoney / lastdayInt;//日均金额
            Double lastorderSingleMoney = lastTotalMoney / lastTotalUnits;//月单值
            lastNowMoth.put("lastTotalUnits", lastTotalUnits);//总单数
            lastNowMoth.put("lastTotalMoney",   LwxfNumberUtils.fomatNumberTwoDigits(lastTotalMoney));//订单总金额
            lastNowMoth.put("lastmonthAvgOrderNum", lastmonthAvgOrderNum); //日均单数
            lastNowMoth.put("lastmonthAvgOrderTotalMoney", LwxfNumberUtils.fomatNumberTwoDigits(lastmonthAvgOrderTotalMoney));//日均金额
            lastNowMoth.put("orderSingleMoney", LwxfNumberUtils.fomatNumberTwoDigits(lastorderSingleMoney)); //日均单数
            lastmapContext.put("isCoordination", "1");//需要外协 外协单数
            List<Map<String, Object>> lastOutsourceOrderChartTmp = productDao.findOrderChartTotal(lastmapContext);//外协总单数
            Integer lastOutsourcetalUnits = Integer.valueOf(lastOutsourceOrderChartTmp.get(0).get("totalUnits") + "");//外协总单数
            lastNowMoth.put("lastOutsourcetalUnits", lastOutsourcetalUnits);//外协单数
            lastNowMoth.put("lastOutsourcetalTotalMoney", lastOutsourcetalUnits);//外协单数

            mapContext.put("status", "0");//payment 付款状态 未付款
            List<Map<String, Object>> lastOrderChartTmpNoAudit = productDao.findOrderChartTotal(mapContext);
            Double lastTotalUnitsNoAudit = Double.valueOf(lastOrderChartTmpNoAudit.get(0).get("totalUnits") + "");//月总未审核单数
            Double lastTotalMoneyNoAudit = 0d;
            if (lastTotalUnitsNoAudit != 0) {
                lastTotalMoneyNoAudit = Double.valueOf(lastOrderChartTmpNoAudit.get(0).get("totalMoney") + "");//月总未审核金额
            }
            Double lastTotalUnitsAudit = totalUnits - lastTotalUnitsNoAudit;//月总审核单数
            Double lastTotalMoneyAudit = totalMoney - lastTotalMoneyNoAudit;//月总审核金额
            lastNowMoth.put("lastTotalUnitsAudit",   LwxfNumberUtils.fomatNumberTwoDigits(lastTotalUnitsNoAudit));//月总审核单数
            lastNowMoth.put("lastTotalMoneyAudit",   LwxfNumberUtils.fomatNumberTwoDigits(lastTotalMoneyNoAudit));//月总审核金额
            lastNowMoth.put("lastTotalUnitsNoAudit",   LwxfNumberUtils.fomatNumberTwoDigits(lastTotalUnitsAudit));//月总未审核金额
            lastNowMoth.put("lastTotalMoneyNoAudit",   LwxfNumberUtils.fomatNumberTwoDigits(lastTotalMoneyAudit));//月总未审核金额
            Double lastavgOrderMoney = 0d;
            if(lastTotalUnits !=0){
                lastavgOrderMoney = lastTotalMoney /lastTotalUnits;
            }
            lastNowMoth.put("lastavgOrderMoney",LwxfNumberUtils.fomatNumberTwoDigits(lastavgOrderMoney));
            //本月同比新增
            Double riseUnitsNowMonth = totalUnits - lastTotalUnits;//新增订单数
            Double riseTotalMoneyNowMonth = totalMoney - lastTotalMoney;//新增总金额
            Double riseRateUnitsNowMont = 100d;
            if(lastTotalUnits != 0){
                riseRateUnitsNowMont = riseUnitsNowMonth / lastTotalUnits * 100;//新增订单数比率
            }
            Double riseRateTotalMoneyNowMont = riseTotalMoneyNowMonth / totalMoney * 100;//新增金额比率
            nowMoth.put("riseUnitsNowMonth", riseUnitsNowMonth);//新增订单数
            nowMoth.put("riseTotalMoneyNowMonth",   LwxfNumberUtils.fomatNumberTwoDigits(riseTotalMoneyNowMonth));//新增金额
            nowMoth.put("riseRateUnitsNowMont", LwxfNumberUtils.fomatNumberTwoDigits(riseRateUnitsNowMont));//新增订单数比率
            nowMoth.put("riseRateTotalMoneyNowMont", LwxfNumberUtils.fomatNumberTwoDigits(riseRateTotalMoneyNowMont));//新增金额比率
            l.add(nowMoth);
        }

        return ResultFactory.generateRequestResult(l);
    }

    @ApiOperation(value = "F端本月订单财务统计", notes = "F端本月订单财务统计")
    @GetMapping("/findOrderFinanceStatistics")
    public RequestResult findOrderFinanceStatistics() {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String companyId = mapInfo.get("companyId") == null ? null : mapInfo.get("companyId").toString();
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        if (uid == null) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        ArrayList<Map<String, Object>> am = new ArrayList<>();
        Date date = new Date();
        int daysOfMonth = LwxfDateUtils.getnowMonth(date);
        List<Basecode> orderProductType = basecodeDao.findByType("orderProductType");
        for (int i = 0; i < daysOfMonth; i++) {
            Map orderChart = new HashMap<>();
            String yyyyMMdd = LwxfDateUtils.getSdfDateDayBefore(date, "yyyyMMdd", i);
            orderChart.put("yyyyMMdd", yyyyMMdd);
            MapContext mapContent = new MapContext();
            mapContent.put("queryStartDate", yyyyMMdd);
            mapContent.put("queryEndDate", yyyyMMdd);
            List<Map<String, Object>> orderChart1 = productDao.findOrderChart(mapContent);
            for (Basecode b : orderProductType) {
                String code = b.getCode();
                String value = b.getValue();
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("code", code);
                hashMap.put("name", value);
                hashMap.put("totalMoney","0");
                hashMap.put("totalUnits","0");
                orderChart.put("_"+code,hashMap);
                for (int j = 0; j < orderChart1.size(); j++) {
                    Map<String, Object> map = orderChart1.get(j);
                    if (map != null && code.equals(map.get("code"))) {
                        hashMap.putAll(orderChart1.get(j));
                    }
                }
            }
            //添加外协
            HashMap outsourceInfo = new HashMap();

            MapContext mapContext = new MapContext();
            mapContext.put("queryStartDate", yyyyMMdd);
            mapContext.put("queryEndDate", yyyyMMdd);
            mapContext.put("isCoordination", 1);//需要外协 外协单数
            List<Map<String, Object>> outsourceOrderChartTmp = productDao.findOrderChartTotal(mapContext);//外协总单数
            Integer outsourcetotalUnits = Integer.valueOf(outsourceOrderChartTmp.get(0).get("totalUnits") + "");//外协总单数
            if(outsourcetotalUnits == 0 || outsourcetotalUnits == null){
                outsourceInfo.put("outsourcetotalUnits",0);
                outsourceInfo.put("outsourcetotalMoney",0);
            }else{
                Double totalMoney = Double.valueOf(outsourceOrderChartTmp.get(0).get("totalMoney") + "");//外协总单数
                outsourceInfo.put("outsourcetotalUnits",outsourcetotalUnits);
                outsourceInfo.put("outsourcetotalMoney",  LwxfNumberUtils.fomatNumberTwoDigits(totalMoney));
            }
            orderChart.putAll(outsourceInfo);

            //总订单数
            MapContext mapContent2 = new MapContext();
            mapContent2.put("queryStartDate", yyyyMMdd);
            mapContent2.put("queryEndDate", yyyyMMdd);
            List<Map<String, Object>> orderChartTotal = productDao.findOrderChartTotal(mapContent2);

            Double totalUnits = Double.valueOf(orderChartTotal.get(0).get("totalUnits") + "");
            Double totalMoney = 0d;
            if(totalUnits != 0){
                totalMoney = Double.valueOf(orderChartTotal.get(0).get("totalMoney") + "");
            }
        orderChart.put("totalMoney",LwxfNumberUtils.fomatNumberTwoDigits(totalMoney));
        orderChart.put("totalUnits",totalUnits);
        am.add(orderChart);
        }
        return ResultFactory.generateRequestResult(am);
    }

    @ApiOperation(value = "b端本月数据分析", notes = "b端本月数据分析")
    @GetMapping("/findFinanceAnalysisMonthB")
    public RequestResult findFinanceAnalysisMonthB(){
        Date date2 = com.lwxf.commons.utils.DateUtil.getSystemDate(); // 今天
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String branchId = mapInfo.get("branchId") == null ? null : mapInfo.get("branchId").toString();
        Date date1 = DateUtilsExt.getBeginDayOfMonth(); // 月初开始时间
        //当月产品统计
        MapContext params = MapContext.newOne();
        params.put("startTime", date1);
        params.put("endTime", date2);
        params.put("branchId", branchId);
        List<MapContext> products = new ArrayList<>();
        int[] typeList=new int[]{0,1,4,5};
        for(int i=0;i<typeList.length;i++) {
            params.put("productType",typeList[i]);
            MapContext map=this.customOrderService.findOrderCountByProductType(params);
            products.add(map);
        }
        //数据分析（订单分析）
        List orderAnalysis = new ArrayList();
        params.put("orderType",OrderType.NORMALORDER.getValue());
        //卖的最好的产品
        MapContext theBestProduct = this.customOrderService.findSaleBestProduct(params);
        //卖的最好的橱柜系列
        params.put("type", OrderProductType.CUPBOARD.getValue());
        MapContext theBestCupboardSeries = this.customOrderService.findSaleBestSeries(params);
        //卖的最好的衣柜系列
        params.put("type", OrderProductType.WARDROBE.getValue());
        MapContext theBestWardrobeSeries = this.customOrderService.findSaleBestSeries(params);
        params.remove("type");
        //卖的最好的门板颜色
        MapContext theSaleBestDoorClolor = this.customOrderService.findTheSaleBestDoorClolor(params);
        //本月单值最高的订单
        Map<String, Object> maxMoneyOrder = productDao.findMaxMoneyOrder();
        HashMap<String, Object> objectObjectHashMap1 = new HashMap<>();
        objectObjectHashMap1.put("count",maxMoneyOrder.get("totalMoney"));
        objectObjectHashMap1.put("name",maxMoneyOrder.get("no"));
        //卖的最好的门型
        MapContext theSaleBestDoor = this.customOrderService.findTheSaleBestDoor(params);
        orderAnalysis.add(theBestProduct);
        orderAnalysis.add(theBestCupboardSeries);
        orderAnalysis.add(theBestWardrobeSeries);
        orderAnalysis.add(theSaleBestDoorClolor);
        orderAnalysis.add(objectObjectHashMap1);
        objectObjectHashMap.put("orderAnalysis",orderAnalysis);


        return ResultFactory.generateRequestResult(objectObjectHashMap);
    }



    @ApiOperation(value = "F端本月数据分析", notes = "F端本月数据分析")
    @GetMapping("/findFinanceAnalysisMonth")
    public RequestResult findFinanceAnalysisMonth() {
        Date date2 = com.lwxf.commons.utils.DateUtil.getSystemDate(); // 今天
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String branchId = mapInfo.get("branchId") == null ? null : mapInfo.get("branchId").toString();
        Date date1 = DateUtilsExt.getBeginDayOfMonth(); // 月初开始时间
        //当月产品统计
        MapContext params = MapContext.newOne();
        params.put("startTime", date1);
        params.put("endTime", date2);
        params.put("branchId", branchId);
        List<MapContext> products = new ArrayList<>();
        int[] typeList=new int[]{0,1,4,5};
        for(int i=0;i<typeList.length;i++) {
            params.put("productType",typeList[i]);
            MapContext map=this.customOrderService.findOrderCountByProductType(params);
            products.add(map);
        }
        //当月产品门型统计
        List<MapContext> doorCount = this.customOrderService.findOrderCountByDoor(params);
        //当月门板颜色统计
        List<MapContext> doorcolorCount = this.customOrderService.findOrderCountByDoorcolor(params);

        //数据分析（订单分析）
        List orderAnalysis = new ArrayList();
        params.put("orderType",OrderType.NORMALORDER.getValue());
        //卖的最好的产品
        MapContext theBestProduct = this.customOrderService.findSaleBestProduct(params);
        //卖的最好的橱柜系列
        params.put("type", OrderProductType.CUPBOARD.getValue());
        MapContext theBestCupboardSeries = this.customOrderService.findSaleBestSeries(params);
        //卖的最好的衣柜系列
        params.put("type", OrderProductType.WARDROBE.getValue());
        MapContext theBestWardrobeSeries = this.customOrderService.findSaleBestSeries(params);
        params.remove("type");
        //卖的最好的门板颜色
        MapContext theSaleBestDoorClolor = this.customOrderService.findTheSaleBestDoorClolor(params);
        //卖的最好的门型
        MapContext theSaleBestDoor = this.customOrderService.findTheSaleBestDoor(params);
        orderAnalysis.add(theBestProduct);
        orderAnalysis.add(theBestCupboardSeries);
        orderAnalysis.add(theBestWardrobeSeries);
        orderAnalysis.add(theSaleBestDoorClolor);
        orderAnalysis.add(theSaleBestDoor);
        objectObjectHashMap.put("orderAnalysis",orderAnalysis);

        //数据分析（售后分析）
        //售后总数
        MapContext mapContext=MapContext.newOne();
        mapContext.put("branchId",branchId);
        mapContext.put("startTime", DateUtilsExt.getFirstDayOfMonth(DateUtil.now()));
        mapContext.put("endTime", DateUtil.now());
        mapContext.put("orderType", OrderType.SUPPLEMENTORDER.getValue());
        Integer afterCountProducts = this.customOrderService.findAftersaleCountByTime(mapContext);
        //产生售后最多的产品
        MapContext theafterMore = this.customOrderService.findSaleBestProduct(mapContext);
        //产生售后最多的橱柜系列
        mapContext.put("type", OrderProductType.CUPBOARD.getValue());
        MapContext theafterCupboardSeries = this.customOrderService.findSaleBestSeries(mapContext);
        //产生售后最多的衣柜系列
        mapContext.put("type", OrderProductType.WARDROBE.getValue());
        MapContext theafterWardrobeSeries = this.customOrderService.findSaleBestSeries(mapContext);
        mapContext.remove("type");
        //产生售后最多的门板颜色
        MapContext theafterBestDoorClolor = this.customOrderService.findTheSaleBestDoorClolor(mapContext);
        //产生售后最多的门型
        MapContext theafterBestDoor = this.customOrderService.findTheSaleBestDoor(mapContext);
        //产生售后比例最多的产品
        if(afterCountProducts==0){
            afterCountProducts=1;
        }
        MapContext afterProductProportion=MapContext.newOne();
        if(theafterMore!=null) {
            afterProductProportion.put("name", theafterMore.getTypedValue("name", String.class));
            String productProportion = (theafterMore.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
            afterProductProportion.put("count", productProportion);
        }
        //产生售后比例最多的橱柜系列
        MapContext afterCSeriesProportion=MapContext.newOne();
        if(theafterMore!=null&&theafterCupboardSeries!=null) {
            afterCSeriesProportion.put("name", theafterCupboardSeries.getTypedValue("name", String.class));
            String CSeriesProportion = (theafterCupboardSeries.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
            afterCSeriesProportion.put("count", CSeriesProportion);
        }
        //产生售后比例最多的衣柜系列
        MapContext afterBSeriesProportion=MapContext.newOne();
        if(theafterMore!=null&&theafterWardrobeSeries!=null) {
            afterBSeriesProportion.put("name", theafterWardrobeSeries.getTypedValue("name", String.class));
            String BSeriesProportion = (theafterWardrobeSeries.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
            afterBSeriesProportion.put("count", BSeriesProportion);
        }
        //产生售后比例最多的门板颜色
        MapContext afterDoorcolorProportion=MapContext.newOne();
        if(theafterMore!=null&&theafterBestDoorClolor!=null) {
            afterDoorcolorProportion.put("name", theafterBestDoorClolor.getTypedValue("name", String.class));
            String doorcolorProportion = (theafterBestDoorClolor.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
            afterDoorcolorProportion.put("count", doorcolorProportion);
        }
        //产生售后比例最多的门型
        MapContext afterdoorProportion=MapContext.newOne();
        if(theafterMore!=null&&theafterBestDoor!=null) {
            afterdoorProportion.put("name", theafterBestDoor.getTypedValue("name", String.class));
            String doorProportion = (theafterBestDoor.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
            afterdoorProportion.put("count", doorProportion);
        }
        List aftersaleAnalysis=new ArrayList();
        aftersaleAnalysis.add(theafterMore);
        aftersaleAnalysis.add(theafterCupboardSeries);
        aftersaleAnalysis.add(theafterWardrobeSeries);
        aftersaleAnalysis.add(theafterBestDoorClolor);
        aftersaleAnalysis.add(theafterBestDoor);
        aftersaleAnalysis.add(afterProductProportion);
        aftersaleAnalysis.add(afterCSeriesProportion);
        aftersaleAnalysis.add(afterBSeriesProportion);
        aftersaleAnalysis.add(afterDoorcolorProportion);
        aftersaleAnalysis.add(afterdoorProportion);
        objectObjectHashMap.put("aftersaleAnalysis",aftersaleAnalysis);
        //数据分析（经销商分析）
        List dealereAnalysis=new ArrayList();
        MapContext mapContext1 = new MapContext();
        //下单最多的经销商
        mapContext1.put("orderType",OrderType.NORMALORDER.getValue());
        mapContext1.put("startTime", date1);
        mapContext1.put("endTime", date2);
        mapContext1.put("branchId",branchId);
        MapContext placeOrderMost=this.customOrderService.findPlaceOrderMost(mapContext1);
        //下单金额最多的经销商
        MapContext placeOrderMoneyMost=this.customOrderService.findPlaceOrderMoneyMost(mapContext1);
        //申请售后最多的经销商
        mapContext.put("orderType",OrderType.SUPPLEMENTORDER.getValue());
        MapContext placeAfterOrderMost=this.customOrderService.findPlaceOrderMost(mapContext);
        dealereAnalysis.add(placeOrderMost);
        dealereAnalysis.add(placeOrderMoneyMost);
        dealereAnalysis.add(placeAfterOrderMost);
        objectObjectHashMap.put("dealereAnalysis",dealereAnalysis);

        return ResultFactory.generateRequestResult(objectObjectHashMap);
    }


    @ApiOperation(value = "F端本年数据分析", notes = "F端本年数据分析")
    @GetMapping("/findFinanceAnalysisYear")
    public RequestResult findFinanceAnalysisYear() {
        Date date2 = com.lwxf.commons.utils.DateUtil.getSystemDate(); // 今天
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String branchId = mapInfo.get("branchId") == null ? null : mapInfo.get("branchId").toString();
        Date date1 = DateUtilsExt.getBeginDayOfMonth(); // 月初开始时间
        //年产品统计
        MapContext params = MapContext.newOne();
        params.put("branchId", branchId);
        params.put("startTime", DateUtilsExt.getFirstDayOfYear(com.lwxf.mybatis.utils.DateUtil.now()));
        params.put("endTime",  com.lwxf.mybatis.utils.DateUtil.now());
        //数据分析（订单分析）
        List orderAnalysis = new ArrayList();
        params.put("orderType",OrderType.NORMALORDER.getValue());
        //卖的最好的产品
        MapContext theBestProduct = this.customOrderService.findSaleBestProduct(params);
        //卖的最好的橱柜系列
        params.put("type", OrderProductType.CUPBOARD.getValue());
        MapContext theBestCupboardSeries = this.customOrderService.findSaleBestSeries(params);
        //卖的最好的衣柜系列
        params.put("type", OrderProductType.WARDROBE.getValue());
        MapContext theBestWardrobeSeries = this.customOrderService.findSaleBestSeries(params);
        params.remove("type");
        //卖的最好的门板颜色
        MapContext theSaleBestDoorClolor = this.customOrderService.findTheSaleBestDoorClolor(params);
        //卖的最好的门型
        MapContext theSaleBestDoor = this.customOrderService.findTheSaleBestDoor(params);
        orderAnalysis.add(theBestProduct);
        orderAnalysis.add(theBestCupboardSeries);
        orderAnalysis.add(theBestWardrobeSeries);
        orderAnalysis.add(theSaleBestDoorClolor);
        orderAnalysis.add(theSaleBestDoor);
        objectObjectHashMap.put("orderAnalysis",orderAnalysis);
        //数据分析（售后分析）
        //售后总数
        MapContext mapContext=MapContext.newOne();
        mapContext.put("branchId",branchId);
        mapContext.put("startTime", DateUtilsExt.getFirstDayOfYear(com.lwxf.mybatis.utils.DateUtil.now()));
        mapContext.put("endTime", com.lwxf.mybatis.utils.DateUtil.now());
        mapContext.put("orderType", OrderType.SUPPLEMENTORDER.getValue());
        Integer afterCountProducts = this.customOrderService.findAftersaleCountByTime(mapContext);
        //产生售后最多的产品
        MapContext theafterMore = this.customOrderService.findSaleBestProduct(mapContext);
        //产生售后最多的橱柜系列
        mapContext.put("type", OrderProductType.CUPBOARD.getValue());
        MapContext theafterCupboardSeries = this.customOrderService.findSaleBestSeries(mapContext);
        //产生售后最多的衣柜系列
        mapContext.put("type", OrderProductType.WARDROBE.getValue());
        MapContext theafterWardrobeSeries = this.customOrderService.findSaleBestSeries(mapContext);
        mapContext.remove("type");
        //产生售后最多的门板颜色
        MapContext theafterBestDoorClolor = this.customOrderService.findTheSaleBestDoorClolor(mapContext);
        //产生售后最多的门型
        MapContext theafterBestDoor = this.customOrderService.findTheSaleBestDoor(mapContext);
        //产生售后比例最多的产品
        if(afterCountProducts==0){
            afterCountProducts=1;
        }
        MapContext afterProductProportion=MapContext.newOne();
        if(theafterMore!=null) {
            afterProductProportion.put("name", theafterMore.getTypedValue("name", String.class));
            String productProportion = (theafterMore.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
            afterProductProportion.put("count", productProportion);
        }
        //产生售后比例最多的橱柜系列
        MapContext afterCSeriesProportion=MapContext.newOne();
        if(theafterMore!=null&&theafterCupboardSeries!=null) {
            afterCSeriesProportion.put("name", theafterCupboardSeries.getTypedValue("name", String.class));
            String CSeriesProportion = (theafterCupboardSeries.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
            afterCSeriesProportion.put("count", CSeriesProportion);
        }
        //产生售后比例最多的衣柜系列
        MapContext afterBSeriesProportion=MapContext.newOne();
        if(theafterMore!=null&&theafterWardrobeSeries!=null) {
            afterBSeriesProportion.put("name", theafterWardrobeSeries.getTypedValue("name", String.class));
            String BSeriesProportion = (theafterWardrobeSeries.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
            afterBSeriesProportion.put("count", BSeriesProportion);
        }
        //产生售后比例最多的门板颜色
        MapContext afterDoorcolorProportion=MapContext.newOne();
        if(theafterMore!=null&&theafterBestDoorClolor!=null) {
            afterDoorcolorProportion.put("name", theafterBestDoorClolor.getTypedValue("name", String.class));
            String doorcolorProportion = (theafterBestDoorClolor.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
            afterDoorcolorProportion.put("count", doorcolorProportion);
        }
        //产生售后比例最多的门型
        MapContext afterdoorProportion=MapContext.newOne();
        if(theafterMore!=null&&theafterBestDoor!=null) {
            afterdoorProportion.put("name", theafterBestDoor.getTypedValue("name", String.class));
            String doorProportion = (theafterBestDoor.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
            afterdoorProportion.put("count", doorProportion);
        }
        List aftersaleAnalysis=new ArrayList();
        aftersaleAnalysis.add(theafterMore);
        aftersaleAnalysis.add(theafterCupboardSeries);
        aftersaleAnalysis.add(theafterWardrobeSeries);
        aftersaleAnalysis.add(theafterBestDoorClolor);
        aftersaleAnalysis.add(theafterBestDoor);
        aftersaleAnalysis.add(afterProductProportion);
        aftersaleAnalysis.add(afterCSeriesProportion);
        aftersaleAnalysis.add(afterBSeriesProportion);
        aftersaleAnalysis.add(afterDoorcolorProportion);
        aftersaleAnalysis.add(afterdoorProportion);
        objectObjectHashMap.put("aftersaleAnalysis",aftersaleAnalysis);
        //数据分析（经销商分析）
        List dealereAnalysis=new ArrayList();
        //下单最多的经销商
        mapContext.put("orderType",OrderType.NORMALORDER.getValue());
        MapContext placeOrderMost=this.customOrderService.findPlaceOrderMost(mapContext);
        //下单金额最多的经销商
        MapContext placeOrderMoneyMost=this.customOrderService.findPlaceOrderMoneyMost(mapContext);
        //申请售后最多的经销商
        mapContext.put("orderType",OrderType.SUPPLEMENTORDER.getValue());
        MapContext placeAfterOrderMost=this.customOrderService.findPlaceOrderMost(mapContext);
        dealereAnalysis.add(placeOrderMost);
        dealereAnalysis.add(placeOrderMoneyMost);
        dealereAnalysis.add(placeAfterOrderMost);
        objectObjectHashMap.put("dealereAnalysis",dealereAnalysis);

        return ResultFactory.generateRequestResult(objectObjectHashMap);
    }






    @ApiOperation(value = "F端本日数据分析", notes = "F端本日数据分析")
    @GetMapping("/findFinanceAnalysisDay")
    public RequestResult findFinanceAnalysisDay() {
        Date date1 = DateUtilsExt.getBeginDayOfMonth(); // 月初开始时间
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String branchId = mapInfo.get("branchId") == null ? null : mapInfo.get("branchId").toString();
        Date date =  new Date();
        HashMap<Object, Object> result = new HashMap<>();
        MapContext params = MapContext.newOne();
        params.put("branchId", branchId);
        params.put("startTime", LwxfDateUtils.getSdfDate(date,"yyyy-MM-dd"));
        params.put("endTime",  LwxfDateUtils.getSdfDate(date,"yyyy-MM-dd"));
        List<MapContext> products = new ArrayList<>();
        int[] typeList=new int[]{0,1,4,5};
        for(int i=0;i<typeList.length;i++) {
            params.put("productType",typeList[i]);
            MapContext map=this.customOrderService.findOrderCountByProductType(params);
            products.add(map);
        }
        //当日产品门型统计
        List<MapContext> doorCount = this.customOrderService.findOrderCountByDoor(params);
        //当日门板颜色统计
        List<MapContext> doorcolorCount = this.customOrderService.findOrderCountByDoorcolor(params);
        //当日柜体颜色统计
        //List<MapContext> colorCount=this.customOrderService.findOrderCountBycolor(params);
        //数据分析（订单分析）
        List orderAnalysis = new ArrayList();
        params.put("orderType", OrderType.NORMALORDER.getValue());
        //卖的最好的产品
        MapContext theBestProduct = this.customOrderService.findSaleBestProduct(params);
        //卖的最好的橱柜系列
        params.put("type", OrderProductType.CUPBOARD.getValue());
        MapContext theBestCupboardSeries = this.customOrderService.findSaleBestSeries(params);
        //卖的最好的衣柜系列
        params.put("type", OrderProductType.WARDROBE.getValue());
        MapContext theBestWardrobeSeries = this.customOrderService.findSaleBestSeries(params);
        //卖的最好的门板颜色
        MapContext theSaleBestDoorClolor = this.customOrderService.findTheSaleBestDoorClolor(params);
        //卖的最好的门型
        MapContext theSaleBestDoor = this.customOrderService.findTheSaleBestDoor(params);
        orderAnalysis.add(theBestProduct);
        orderAnalysis.add(theBestCupboardSeries);
        orderAnalysis.add(theBestWardrobeSeries);
        orderAnalysis.add(theSaleBestDoorClolor);
        orderAnalysis.add(theSaleBestDoor);
        result.put("orderAnalysis",orderAnalysis);
        //数据分析（售后分析）
        //售后总数
        MapContext mapContext=MapContext.newOne();
        mapContext.put("branchId",branchId);
        mapContext.put("startTime", LwxfDateUtils.getSdfDate(date,"yyyy-MM-dd"));
        mapContext.put("endTime", LwxfDateUtils.getSdfDate(date,"yyyy-MM-dd"));
        mapContext.put("orderType", OrderType.SUPPLEMENTORDER.getValue());
        Integer afterCountProducts = this.customOrderService.findAftersaleCountByTime(mapContext);
        //产生售后最多的产品
        MapContext theafterMore = this.customOrderService.findSaleBestProduct(mapContext);
        //产生售后最多的橱柜系列
        mapContext.put("type", OrderProductType.CUPBOARD.getValue());
        MapContext theafterCupboardSeries = this.customOrderService.findSaleBestSeries(mapContext);
        //产生售后最多的衣柜系列
        mapContext.put("type", OrderProductType.WARDROBE.getValue());
        MapContext theafterWardrobeSeries = this.customOrderService.findSaleBestSeries(mapContext);
        mapContext.remove("type");
        //产生售后最多的门板颜色
        MapContext theafterBestDoorClolor = this.customOrderService.findTheSaleBestDoorClolor(mapContext);
        //产生售后最多的门型
        MapContext theafterBestDoor = this.customOrderService.findTheSaleBestDoor(mapContext);
        //产生售后比例最多的产品
        if(afterCountProducts==0){
            afterCountProducts=1;
        }
        MapContext afterProductProportion=MapContext.newOne();
        if(theafterMore!=null) {
            afterProductProportion.put("name", theafterMore.getTypedValue("name", String.class));
            String productProportion = (theafterMore.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
            afterProductProportion.put("count", productProportion);
        }
        //产生售后比例最多的橱柜系列
        MapContext afterCSeriesProportion=MapContext.newOne();
        if(theafterMore!=null&&theafterCupboardSeries!=null) {
            afterCSeriesProportion.put("name", theafterCupboardSeries.getTypedValue("name", String.class));
            String CSeriesProportion = (theafterCupboardSeries.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
            afterCSeriesProportion.put("count", CSeriesProportion);
        }
        //产生售后比例最多的衣柜系列
        MapContext afterBSeriesProportion=MapContext.newOne();
        if(theafterMore!=null&&theafterWardrobeSeries!=null) {
            afterBSeriesProportion.put("name", theafterWardrobeSeries.getTypedValue("name", String.class));
            String BSeriesProportion = (theafterWardrobeSeries.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
            afterBSeriesProportion.put("count", BSeriesProportion);
        }
        //产生售后比例最多的门板颜色
        MapContext afterDoorcolorProportion=MapContext.newOne();
        if(theafterMore!=null&&theafterBestDoorClolor!=null) {
            afterDoorcolorProportion.put("name", theafterBestDoorClolor.getTypedValue("name", String.class));
            String doorcolorProportion = (theafterBestDoorClolor.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
            afterDoorcolorProportion.put("count", doorcolorProportion);
        }
        //产生售后比例最多的门型
        MapContext afterdoorProportion=MapContext.newOne();
        if(theafterMore!=null&&theafterBestDoor!=null) {
            afterdoorProportion.put("name", theafterBestDoor.getTypedValue("name", String.class));
            String doorProportion = (theafterBestDoor.getTypedValue("count", Integer.class) / afterCountProducts) * 100 + "%";
            afterdoorProportion.put("count", doorProportion);
        }
        List aftersaleAnalysis=new ArrayList();
        aftersaleAnalysis.add(theafterMore);
        aftersaleAnalysis.add(theafterCupboardSeries);
        aftersaleAnalysis.add(theafterWardrobeSeries);
        aftersaleAnalysis.add(theafterBestDoorClolor);
        aftersaleAnalysis.add(theafterBestDoor);
        aftersaleAnalysis.add(afterProductProportion);
        aftersaleAnalysis.add(afterCSeriesProportion);
        aftersaleAnalysis.add(afterBSeriesProportion);
        aftersaleAnalysis.add(afterDoorcolorProportion);
        aftersaleAnalysis.add(afterdoorProportion);
        //数据分析（经销商分析）
        result.put("aftersaleAnalysis",aftersaleAnalysis);
        MapContext mapContext1 = new MapContext();
        mapContext1.put("startTime", LwxfDateUtils.getSdfDate(date,"yyyy-MM-dd"));
        mapContext1.put("endTime", DateUtil.now());
        mapContext1.put("branchId",branchId);
        List dealereAnalysis = new ArrayList();
        //下单最多的经销商
        mapContext.put("orderType", OrderType.NORMALORDER.getValue());
        MapContext placeOrderMost = this.customOrderService.findPlaceOrderMost(mapContext1);
        //下单金额最多的经销商
        MapContext placeOrderMoneyMost = this.customOrderService.findPlaceOrderMoneyMost(mapContext1);
        //申请售后最多的经销商
        mapContext.put("orderType", OrderType.SUPPLEMENTORDER.getValue());
        MapContext placeAfterOrderMost = this.customOrderService.findPlaceOrderMost(mapContext1);
        dealereAnalysis.add(placeOrderMost);
        dealereAnalysis.add(placeOrderMoneyMost);
        dealereAnalysis.add(placeAfterOrderMost);
        result.put("dealereAnalysis",dealereAnalysis);
        return ResultFactory.generateRequestResult(result);
    }


    @ApiOperation(value = "当日/当月/当年产品系列财务明细统计", notes = "当日/当月/当年产品系列财务明细统计")
    @GetMapping("/findOrderChartByseriesFinance")
    public RequestResult findOrderChartByseriesFinance(String orderProduct, String queryStartDate, String queryEndDate) {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String companyId = mapInfo.get("companyId") == null ? null : mapInfo.get("companyId").toString();
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        if (uid == null) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        MapContext mapContext = new MapContext();
        mapContext.put("orderProduct", orderProduct);
        mapContext.put("queryStartDate", queryStartDate);
        mapContext.put("queryEndDate", queryEndDate);
        return this.dataChartService.findOrderChartByseriesFinance(mapContext);
    }

    @ApiOperation(value = "当日/当月/当年产品占比统计", notes = "当日/当月/当年产品占比统计")
    @GetMapping("/findOrderProportionFinance")
    public RequestResult findOrderProportionFinance(String queryStartDate) {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String companyId = mapInfo.get("companyId") == null ? null : mapInfo.get("companyId").toString();
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        if (uid == null) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        MapContext mapContext = new MapContext();
        mapContext.put("queryStartDate", queryStartDate);
        mapContext.put("queryEndDate", queryStartDate);
        return this.dataChartService.findOrderProportionFinance(mapContext);
    }


}
