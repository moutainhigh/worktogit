package com.lwxf.industry4.webapp.facade.admin.factory.statement.impl;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.bizservice.statements.app.factory.paymentStatement.PaymentStatementService;
import com.lwxf.industry4.webapp.bizservice.system.BasecodeService;
import com.lwxf.industry4.webapp.common.enums.order.OrderProductType;
import com.lwxf.industry4.webapp.common.enums.order.OrderType;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.DateUtilsExt;
import com.lwxf.industry4.webapp.common.utils.NumberUtilsExt;
import com.lwxf.industry4.webapp.domain.dto.statement.*;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.admin.factory.statement.PaymentStatementFacade;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.math.BigDecimal.ROUND_HALF_DOWN;


@Component("paymentStatementFacade")
public class PaymentStatementFacadeImpl extends BaseFacadeImpl implements PaymentStatementFacade {

    @Resource(name = "paymentStatementService")
    private PaymentStatementService paymentStatementService;
    @Resource(name = "customOrderService")
    private CustomOrderService customOrderService;
    @Resource(name = "basecodeService")
    private BasecodeService basecodeService;

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult selectByfilter(Date StartDate,Date endDate,MapContext map,Integer dateType) {
        MapContext mapRes = MapContext.newOne();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        MapContext value = MapContext.newOne();
        List<MapContext> listRes = new ArrayList<>();
        if(dateType==null){
            dateType=0;
        }
        if(dateType==0){ //按日统计
            if(StartDate!=null && endDate!=null) {
                List<Date> list = DateUtilsExt.findDates(StartDate, endDate);
                if (list != null && list.size() > 0) {
                    SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd");
                    for (Date date : list) {
                        map.put("created", sdf.format(date));
                        MapContext mapValue = MapContext.newOne();
                        mapValue.put("date",sdf1.format(date));
                        mapValue.put("value",paymentStatementService.selectByfilter(map));
                        listRes.add(mapValue);
                    }
                }
            }
        }else if(dateType==1){ //按月统计
            SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyy-MM");
            SimpleDateFormat sdfParam = new SimpleDateFormat("yyyy-MM-dd");
            if(StartDate!=null && endDate!=null){
                List<Date> list = null;
                try {
                    list = DateUtilsExt.getMonthBetween(StartDate,endDate);
                    if(list!=null&&list.size()>0){
                        for(Date date: list){
                            map.put("beginDate",sdfParam.format(DateUtilsExt.getFirstDayOfMonth(date)));
                            map.put("endDate",sdfParam.format(DateUtilsExt.getLastDayOfMonth(date)));
                            MapContext mapValue = MapContext.newOne();
                            mapValue.put("date",sdfMonth.format(date));
                            mapValue.put("value",paymentStatementService.selectByfilter(map));
                            listRes.add(mapValue);
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }else if(dateType==2){ //按年统计
            SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyy");
            SimpleDateFormat sdfParam = new SimpleDateFormat("yyyy-MM-dd");
            if(StartDate!=null && endDate!=null){
                List<Date> list = null;
                try {
                    list = DateUtilsExt.getYearBetween(StartDate,endDate);
                    if(list!=null&&list.size()>0){
                        for(Date date: list){
                            map.put("beginDate",sdfParam.format(DateUtilsExt.getFirstDayOfYear(date)));
                            map.put("endDate",sdfParam.format(DateUtilsExt.getLastDayOfYear(date)));
                            MapContext mapValue = MapContext.newOne();
                            mapValue.put("date",sdfMonth.format(date));
                            mapValue.put("value",paymentStatementService.selectByfilter(map));
                            listRes.add(mapValue);
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        mapRes.put("chart1",value);
        if(!map.get("countType").toString().equals("4"))
        {
            List<MapContext> chart2List = new ArrayList<>();
            List<PaymentCountByFunds> list2 = paymentStatementService.selectFundsStatementByFilter(map);
            for(PaymentCountByFunds obj : list2){
                MapContext m = MapContext.newOne();
                m.put("name",obj.getFundsName());
                m.put("value",obj.getAmount());
                chart2List.add(m);
            }
            mapRes.put("chart2",chart2List);
        }
        return ResultFactory.generateRequestResult(mapRes);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult countPaymentByWeek(MapContext map) {
        MapContext mapRes = MapContext.newOne();
        CountByWeekDto dto = paymentStatementService.countPaymentByWeek(map);
        MapContext mapChart1 = MapContext.newOne();
        try {
            if (dto != null) {
                Class cls = dto.getClass();
                Field[] fields = cls.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    Field f = fields[i];
                    f.setAccessible(true);
                    mapChart1.put(f.getName(), f.get(dto));
                }
            }
        }catch(IllegalAccessException ex) {
                ex.printStackTrace();
        }
        mapRes.put("chart1",mapChart1);
        if(!map.get("countType").toString().equals("4"))
        {
            List<MapContext> chart2List = new ArrayList<>();
            List<PaymentCountByFunds> list = paymentStatementService.countFundsStatementByWeek(map);
            for(PaymentCountByFunds obj : list){
                MapContext m = MapContext.newOne();
                m.put("name",obj.getFundsName());
                m.put("value",obj.getAmount());
                chart2List.add(m);
            }
            mapRes.put("chart2",chart2List);
        }
        return ResultFactory.generateRequestResult(mapRes);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult countPaymentByMonth(MapContext map) {
        MapContext mapRes = MapContext.newOne();
        CountByMonthDto dto = paymentStatementService.countPaymentByMonth(map);
        MapContext mapChart1 = MapContext.newOne();
        try {
            if (dto != null) {
                Class cls = dto.getClass();
                Field[] fields = cls.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    Field f = fields[i];
                    f.setAccessible(true);
                    mapChart1.put(f.getName(), f.get(dto));
                }
            }
        }catch(IllegalAccessException ex) {
            ex.printStackTrace();
        }
        mapRes.put("chart1",mapChart1);
        if(!map.get("countType").toString().equals("4"))
        {
            List<MapContext> chart2List = new ArrayList<>();
            List<PaymentCountByFunds> list = paymentStatementService.countFundsStatementByMonth(map);
            for(PaymentCountByFunds obj : list){
                MapContext m = MapContext.newOne();
                m.put("name",obj.getFundsName());
                m.put("value",obj.getAmount());
                chart2List.add(m);
            }
            mapRes.put("chart2",chart2List);
        }
        return ResultFactory.generateRequestResult(mapRes);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult countPaymentByQuarter(MapContext map) {
        MapContext mapRes = MapContext.newOne();
        CountByQuarterDto dto = paymentStatementService.countPaymentByQuarter(map);
        MapContext mapChart1 = MapContext.newOne();
        try {
            if (dto != null) {
                Class cls = dto.getClass();
                Field[] fields = cls.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    Field f = fields[i];
                    f.setAccessible(true);
                    mapChart1.put(f.getName(), f.get(dto));
                }
            }
        }catch(IllegalAccessException ex) {
            ex.printStackTrace();
        }
        mapRes.put("chart1",mapChart1);
        if(!map.get("countType").toString().equals("4"))
        {
            List<MapContext> chart2List = new ArrayList<>();
            List<PaymentCountByFunds> list = paymentStatementService.countFundsStatementByQuarter(map);
            for(PaymentCountByFunds obj : list){
                MapContext m = MapContext.newOne();
                m.put("name",obj.getFundsName());
                m.put("value",obj.getAmount());
                chart2List.add(m);
            }
            mapRes.put("chart2",chart2List);
        }
        return ResultFactory.generateRequestResult(mapRes);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult countPaymentByYear(MapContext map) {
        MapContext mapRes = MapContext.newOne();
        CountByYearDto dto = paymentStatementService.countPaymentByYear(map);
        MapContext mapChart1 = MapContext.newOne();
        try {
            if (dto != null) {
                Class cls = dto.getClass();
                Field[] fields = cls.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    Field f = fields[i];
                    f.setAccessible(true);
                    mapChart1.put(f.getName(), f.get(dto));
                }
            }
        }catch(IllegalAccessException ex) {
            ex.printStackTrace();
        }
        mapRes.put("chart1",mapChart1);
        if(!map.get("countType").toString().equals("4"))
        {
            List<MapContext> chart2List = new ArrayList<>();
            List<PaymentCountByFunds> list = paymentStatementService.countFundsStatementByYear(map);
            for(PaymentCountByFunds obj : list){
                MapContext m = MapContext.newOne();
                m.put("name",obj.getFundsName());
                m.put("value",obj.getAmount());
                chart2List.add(m);
            }
            mapRes.put("chart2",chart2List);
        }
        return ResultFactory.generateRequestResult(mapRes);
    }

    @Override
    public RequestResult findTwoDaysContrast(MapContext mapContext) {
        List<FinanceReportOrderDto> dtoList = paymentStatementService.selectTwoDaysContrast(mapContext);
        if (dtoList != null && dtoList.size() > 0) {
            // 计算环比新增数据
            for (int i = 0; i < dtoList.size() - 1; i++) {
                FinanceReportOrderDto dto = dtoList.get(i); // 第一条数据
                dto.setOrderTotal(dto.getHasApproveOrderTotal() + dto.getNoApproveOrderTotal());
                dto.setOrderMoneyTotal(dto.getHasApproveOrderMoneyTotal().add(dto.getNoApproveOrderMoneyTotal()));
                FinanceReportOrderDto nextDto = dtoList.get(i+1); // 下一条数据
                nextDto.setOrderTotal(nextDto.getHasApproveOrderTotal() + nextDto.getNoApproveOrderTotal());
                nextDto.setOrderMoneyTotal(nextDto.getHasApproveOrderMoneyTotal().add(nextDto.getNoApproveOrderMoneyTotal()));
                dto.setOrderTotalChainNum(dto.getOrderTotal() - nextDto.getOrderTotal()); // 环比新增单量
                dto.setOrderTotalChainPercent(NumberUtilsExt.percent(dto.getOrderTotalChainNum(), nextDto.getOrderTotal())); // 环比新增单量比
                dtoList.set(i, dto);
            }

            // 去除最后面多余的数据
            dtoList.remove(dtoList.size() - 1);
        }
        return ResultFactory.generateRequestResult(dtoList);
    }

    @Override
    public RequestResult findTwoMonthContrast(MapContext mapContext) {
        List<FinanceReportOrderDto> dtoList = paymentStatementService.selectTwoMonthContrast(mapContext);
        if (dtoList != null && dtoList.size() > 0) {
            // 计算环比新增数据
            for (int i = 0; i < dtoList.size() - 1; i++) {
                FinanceReportOrderDto dto = dtoList.get(i); // 第一条数据
                dto.setOrderTotal(dto.getHasApproveOrderTotal() + dto.getNoApproveOrderTotal());
                dto.setOrderMoneyTotal(dto.getHasApproveOrderMoneyTotal().add(dto.getNoApproveOrderMoneyTotal()));
                // 日均单量
                String dateStr = dto.getReportDate();
                int days = 1;
                if (i == 0) {
                    // 当月天数
                    days = DateUtilsExt.getDiffDays(DateUtilsExt.getBeginDayOfMonth(), DateUtil.getSystemDate());
                } else {
                    // 指定月天数
                    days = DateUtilsExt.getDaysOfMonth(dateStr).size();
                }
                dto.setOrderTotalAverageOfDay(dto.getOrderTotal() / days); // 日均订单量
                dto.setOrderMoneyAverageOfDay(dto.getOrderMoneyTotal().divide(new BigDecimal(days), 0, ROUND_HALF_DOWN)); // 日均订单金额
                if (dto.getOrderTotal().equals(new Integer(0))) {
                    dto.setOrderMoneyAverage(new BigDecimal(0));
                } else {
                    dto.setOrderMoneyAverage(dto.getOrderMoneyTotal().divide(new BigDecimal(dto.getOrderTotal()), 0, ROUND_HALF_DOWN)); // 订单单值
                }


                // 上个月数据处理
                FinanceReportOrderDto nextDto = dtoList.get(i+1); // 下一条数据
                nextDto.setOrderTotal(nextDto.getHasApproveOrderTotal() + nextDto.getNoApproveOrderTotal());
                nextDto.setOrderMoneyTotal(nextDto.getHasApproveOrderMoneyTotal().add(nextDto.getNoApproveOrderMoneyTotal()));
                dto.setOrderTotalChainNum(dto.getOrderTotal() - nextDto.getOrderTotal()); // 环比新增单量
                dto.setOrderTotalChainPercent(NumberUtilsExt.percent(dto.getOrderTotalChainNum(), nextDto.getOrderTotal())); // 环比新增单量比
                dto.setOrderMoneyToalChainNum(dto.getOrderMoneyTotal().subtract(nextDto.getOrderMoneyTotal())); // 环比新增金额
                BigDecimal divide = new BigDecimal(1);
                if (!nextDto.getOrderMoneyTotal().equals(new BigDecimal(0))) {
                    divide = dto.getOrderMoneyToalChainNum().divide(nextDto.getOrderMoneyTotal(), 0, ROUND_HALF_DOWN);
                } else {
                    divide = dto.getOrderMoneyToalChainNum().divide(new BigDecimal(1), 0, ROUND_HALF_DOWN);
                }
                DecimalFormat nf = (DecimalFormat) NumberFormat.getPercentInstance();
                nf.applyPattern("00%");
                dto.setOrderMoneyToalChainPercent(nf.format(divide.doubleValue())); // 转为百分比

                dtoList.set(i, dto);
            }

            // 去除最后面多余的数据
            dtoList.remove(dtoList.size() - 1);
        }
        return ResultFactory.generateRequestResult(dtoList);
    }

    @Override
    public RequestResult newOrderCount(String branchId) {
        MapContext result = MapContext.newOne();

        //两日订单量对比
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date1 = calendar.getTime();//当天
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DATE) - 2);//前二天
        Date date3 = calendar.getTime();
        List<Date> dates = DateUtilsExt.findDates(date3, date1);

        List list = new ArrayList<>();
        Integer first = 0;
        for (Date date : dates) {
            MapContext mapContext = MapContext.newOne();
            mapContext.put("branchId", branchId);
            mapContext.put("startTime", date);
            mapContext.put("endTime", date);
            //前二天订单数
            Integer count = this.customOrderService.findOrderCountByTime(mapContext);
            //前二天订单金额
            Double money = this.customOrderService.findOrderMoneyByTime(mapContext);
            //已审核订单数
            Integer auditCount = this.customOrderService.findAuditCountByTime(mapContext);
            //已审核订单数金额
            Double auditMoney = this.customOrderService.findAuditMoneyByTime(mapContext);
            //未审核订单数
            Integer noAuditCount = this.customOrderService.findnoAuditCountByTime(mapContext);
            //未审核订单金额
            Double noAuditMoney = this.customOrderService.findnoAuditMoneyByTime(mapContext);

            // 订单单值
            String averageMoney = "0.00";
            if (count != 0) {
                averageMoney = String.format("%,.2f", (double)money / count);
            }

            String chainRatio = "";
            String chainRatioPercentage = "";
                chainRatio = String.valueOf(count - first);
                if (first == 0) {
//                    chainRatioPercentage = ((count - first)*100 / 1) + "%";
                    chainRatioPercentage = "100%";
                } else {
                    chainRatioPercentage = ((count - first)*100 / first) + "%";
                }
            first = count;
            //前二天外协订单数
//            mapContext.put("coordination", 1);
//            Integer waiCount = this.customOrderService.findOrderCountByTime(mapContext);
            Integer waiCount = this.customOrderService.findCoordinationCountByTime(mapContext); // 外协数量
            MapContext mapContext1 = MapContext.newOne();
            mapContext1.put("date", dateFormat.format(date)); // 日期
            mapContext1.put("orderNum", count); // 订单数量
            mapContext1.put("orderMoney", String.format("%,.2f", money)); // 订单金额
            mapContext1.put("auditCount", auditCount); // 已审核订单数量
            mapContext1.put("auditMoney", String.format("%,.2f", auditMoney)); // 已审核订单金额
            mapContext1.put("noAuditCount", noAuditCount); // 未审核订单数量
            mapContext1.put("noAuditMoney", String.format("%,.2f", noAuditMoney)); // 未审核订单金额
            mapContext1.put("waiOrderNum", waiCount); // 外协数量
            mapContext1.put("chainRatio", chainRatio); // 环比新增单量
            mapContext1.put("chainRatioPercentage", chainRatioPercentage); // 环比新增单量比
            mapContext1.put("averageMoney", averageMoney); // 订单单值
            list.add(mapContext1);
        }
        //当日产品系列明细统计
        Integer deFlag = 0;
        String seriesType = "orderProductSeries";
        //查询产品系列
        List<Basecode> basecodes = this.basecodeService.findByTypeAndDelFlag(seriesType, deFlag);
        List productTypelist=new ArrayList();
        //各产品订单总数及近30天未打款数
        //查询产品分类
        List<Basecode> orderProductType = this.basecodeService.findByTypeAndDelFlag("orderProductType", 0);
        if(orderProductType!=null&&orderProductType.size()>0) {
            for (Basecode type : orderProductType) {
                MapContext productMap = MapContext.newOne();
                productMap.put("name", type.getValue());
                Integer productType = Integer.valueOf(type.getCode());
                //橱柜
                List list0 = new ArrayList();
                //橱柜订单总下单数和近30天未打款数量
                MapContext mapCon = MapContext.newOne();
                mapCon.put("branchId", branchId);
                mapCon.put("startTime", date1);
                mapCon.put("endTime", date1);
                mapCon.put("type", productType);
                MapContext cupboardCount = this.customOrderService.findCupboardOrWardrobeCountByTime(mapCon);
                cupboardCount.put("seriesName", type.getValue()+"总量");
                cupboardCount.put("payMoney", String.format("%,.2f", cupboardCount.getTypedValue("payMoney", BigDecimal.class)));
                cupboardCount.put("nopayMoney", String.format("%,.2f", cupboardCount.getTypedValue("nopayMoney", BigDecimal.class)));
                list0.add(cupboardCount);
                //橱柜系列产品数和下单数和近30天未打款数量
                for (Basecode basecode : basecodes) {
                    MapContext mapContext = MapContext.newOne();
                    mapContext.put("branchId", branchId);
                    mapContext.put("startTime", date1);
                    mapContext.put("endTime", date1);
                    mapContext.put("type", productType);
                    Integer code = Integer.valueOf(basecode.getCode());
                    mapContext.put("series", code);
                    //今日下单数
                    MapContext payNum = this.customOrderService.countOrderSeriesByDay(mapContext);
                    //近30天未付款数
                    Calendar calendar0 = Calendar.getInstance();
                    calendar.set(Calendar.DAY_OF_MONTH, calendar0.get(Calendar.DATE) - 29);//前三十天
                    Date start = calendar.getTime();
                    mapContext.put("startTime", start);
                    mapContext.put("endTime", date1);
                    MapContext nopayNum = this.customOrderService.countNopayOrderSeriesByDay(mapContext);
                    MapContext map = MapContext.newOne();
                    map.put("seriesName", basecode.getValue());
                    map.put("payNum", payNum.getTypedValue("count", Integer.class));
                    map.put("payMoney", String.format("%,.2f", payNum.getTypedValue("productPrice", BigDecimal.class)));
                    map.put("nopayNum", nopayNum.getTypedValue("count", Integer.class));
                    map.put("nopayMoney", String.format("%,.2f", nopayNum.getTypedValue("productPrice", BigDecimal.class)));
                    list0.add(map);
                }
                productMap.put("list",list0);
                productTypelist.add(productMap);
            }
        }
//        //衣柜订单总下单数和近30天未打款数量
//        mapCon.put("type", 1);
//        MapContext wardrobeCount = this.customOrderService.findCupboardOrWardrobeCountByTime(mapCon);
//        wardrobeCount.put("payMoney", String.format("%,.2f", wardrobeCount.getTypedValue("payMoney", BigDecimal.class)));
//        wardrobeCount.put("nopayMoney", String.format("%,.2f", wardrobeCount.getTypedValue("nopayMoney", BigDecimal.class)));
//        //衣柜
//        List list1 = new ArrayList();
//        wardrobeCount.put("seriesName", "衣柜总量");
//        list1.add(wardrobeCount);
//        for (Basecode basecode : basecodes) {
//            MapContext mapContext = MapContext.newOne();
//            mapContext.put("branchId", branchId);
//            mapContext.put("startTime", date1);
//            mapContext.put("endTime", date1);
//            Integer productType = 1;
//            mapContext.put("type", productType);
//            Integer code = Integer.valueOf(basecode.getCode());
//            mapContext.put("series", code);
//            //今日下单数
//            MapContext payNum = this.customOrderService.countOrderSeriesByDay(mapContext);
//            //近30天未付款数
//            Calendar calendar0 = Calendar.getInstance();
//            calendar.set(Calendar.DAY_OF_MONTH, calendar0.get(Calendar.DATE) - 29);//前三十天
//            Date start = calendar.getTime();
//            mapContext.put("startTime", start);
//            mapContext.put("endTime", date1);
//            MapContext nopayNum = this.customOrderService.countNopayOrderSeriesByDay(mapContext);
//            MapContext map = MapContext.newOne();
//            map.put("seriesName", basecode.getValue());
//            map.put("payNum", payNum.getTypedValue("count", Integer.class));
//            map.put("payMoney", String.format("%,.2f", payNum.getTypedValue("productPrice", BigDecimal.class)));
//            map.put("nopayNum", nopayNum.getTypedValue("count", Integer.class));
//            map.put("nopayMoney", String.format("%,.2f", nopayNum.getTypedValue("productPrice", BigDecimal.class)));
//            list1.add(map);
//        }
        //当日产品统计
        MapContext params = MapContext.newOne();
        params.put("branchId", branchId);
        params.put("startTime", date1);
        params.put("endTime", date1);
        List<MapContext> products = new ArrayList<>();
 //       int[] typeList=new int[]{0,1,4,5};
        if(orderProductType!=null&&orderProductType.size()>0) {
            for (Basecode basecode : orderProductType) {
                params.put("productType", basecode.getCode());
                MapContext map = this.customOrderService.findOrderCountByProductType(params);
                products.add(map);
            }
        }
        //当日产品门型统计
        List<MapContext> doorCount = this.customOrderService.findOrderCountByDoor(params);
        //当日门板颜色统计
        List<MapContext> doorcolorCount = this.customOrderService.findOrderCountByDoorcolor(params);
        //当日柜体颜色统计
        //List<MapContext> colorCount=this.customOrderService.findOrderCountBycolor(params);


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
        //数据分析（售后分析）
        //售后总数
        MapContext mapContext=MapContext.newOne();
        mapContext.put("branchId",branchId);
        mapContext.put("startTime", date1);
        mapContext.put("endTime", date1);
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

        result.put("contrast", list); // 两日对比
        result.put("productTypelist", productTypelist);
 //       result.put("yiguiSeries", list1);
        result.put("products", products);
        result.put("doorCount", doorCount);
        result.put("doorcolorCount", doorcolorCount);
        result.put("orderAnalysis", orderAnalysis);
        result.put("aftersaleAnalysis", aftersaleAnalysis);
        result.put("dealereAnalysis", dealereAnalysis);

        return ResultFactory.generateRequestResult(result);
    }

    @Override
    public RequestResult newOrderMonthCount(String branchId) {
//        MapContext maptemp = MapContext.newOne();
//        maptemp.put("branchId", branchId);
//        // 获取当月
//        String nowDate = DateUtil.dateTimeToString(DateUtil.getSystemDate(), "yyyy-MM-dd");
//        // 获取上个月
//        String lastDate = DateUtil.dateTimeToString(DateUtilsExt.getBeginDayOfLastMonth(), "yyyy-MM-dd");
//        // 获取上上个月
//        Calendar c = Calendar.getInstance();
//        c.setTime(new Date());
//        c.add(Calendar.MONTH, -2);
//        String lastBeforedDate = DateUtil.dateTimeToString(c.getTime(), "yyyy-MM-dd");
//        String[] dateList1 = {nowDate, lastDate, lastBeforedDate};
//        maptemp.put("dateList", dateList1);
//        // 两月对比数据
//        List<FinanceReportOrderDto> monthDtoList = paymentStatementService.selectTwoMonthContrast(maptemp);
//        if (monthDtoList != null && monthDtoList.size() > 0) {
//            // 计算环比新增数据
//            for (int i = 0; i < monthDtoList.size() - 1; i++) {
//                FinanceReportOrderDto dto = monthDtoList.get(i); // 第一条数据
//                dto.setOrderTotal(dto.getHasApproveOrderTotal() + dto.getNoApproveOrderTotal());
//                dto.setOrderMoneyTotal(dto.getHasApproveOrderMoneyTotal().add(dto.getNoApproveOrderMoneyTotal()));
//                // 日均单量
//                String dateStr = dto.getReportDate();
//                int days = 1;
//                if (i == 0) {
//                    // 当月天数
//                    days = DateUtilsExt.getDiffDays(DateUtilsExt.getBeginDayOfMonth(), DateUtil.getSystemDate());
//                } else {
//                    // 指定月天数
//                    days = DateUtilsExt.getDaysOfMonth(dateStr).size();
//                }
//                dto.setOrderTotalAverageOfDay(dto.getOrderTotal() / days); // 日均订单量
//                dto.setOrderMoneyAverageOfDay(dto.getOrderMoneyTotal().divide(new BigDecimal(days), 0, ROUND_HALF_DOWN)); // 日均订单金额
//                if (dto.getOrderTotal().equals(new Integer(0))) {
//                    dto.setOrderMoneyAverage(new BigDecimal(0));
//                } else {
//                    dto.setOrderMoneyAverage(dto.getOrderMoneyTotal().divide(new BigDecimal(dto.getOrderTotal()), 0, ROUND_HALF_DOWN)); // 订单单值
//                }
//
//
//                // 上个月数据处理
//                FinanceReportOrderDto nextDto = monthDtoList.get(i+1); // 下一条数据
//                nextDto.setOrderTotal(nextDto.getHasApproveOrderTotal() + nextDto.getNoApproveOrderTotal());
//                nextDto.setOrderMoneyTotal(nextDto.getHasApproveOrderMoneyTotal().add(nextDto.getNoApproveOrderMoneyTotal()));
//                dto.setOrderTotalChainNum(dto.getOrderTotal() - nextDto.getOrderTotal()); // 环比新增单量
//                dto.setOrderTotalChainPercent(NumberUtilsExt.percent(dto.getOrderTotalChainNum(), nextDto.getOrderTotal())); // 环比新增单量比
//                dto.setOrderMoneyToalChainNum(dto.getOrderMoneyTotal().subtract(nextDto.getOrderMoneyTotal())); // 环比新增金额
//                BigDecimal divide = new BigDecimal(0);
//                if (nextDto.getOrderMoneyTotal().equals(new BigDecimal("0")) || nextDto.getOrderMoneyTotal().equals(new BigDecimal("0.00"))) {
//                    divide = dto.getOrderMoneyToalChainNum().divide(new BigDecimal(1), 0, ROUND_HALF_DOWN);
//                } else {
//                    divide = dto.getOrderMoneyToalChainNum().divide(nextDto.getOrderMoneyTotal(), 0, ROUND_HALF_DOWN);
//                }
//                DecimalFormat nf = (DecimalFormat) NumberFormat.getPercentInstance();
//                nf.applyPattern("00%");
//                dto.setOrderMoneyToalChainPercent(nf.format(divide.doubleValue())); // 转为百分比
//
//                monthDtoList.set(i, dto);
//            }
//
//            // 去除最后面多余的数据
//            monthDtoList.remove(monthDtoList.size() - 1);
//        }
//
//
//
//
//
//
//
//        MapContext result = MapContext.newOne();
//        MapContext mapContext1 = MapContext.newOne();
//        mapContext1.put("brandId", branchId);
//        Date date1 = DateUtilsExt.getBeginDayOfMonth(); // 月初开始时间
//        Date date2 = DateUtil.getSystemDate(); // 今天
//        // 当月日期集合
//        List<String> dateList = DateUtilsExt.getListDayDate(DateUtilsExt.getDayBegin(), -(Integer.valueOf(DateUtil.dateTimeToString(DateUtil.getSystemDate(), "d")) + 1));
//        Collections.reverse(dateList); // 逆序
//        mapContext1.put("dateList", dateList);
//        List<FinanceReportOrderDto> dtoList = paymentStatementService.selectTwoDaysContrast(mapContext1);
//        // 本月订单统计 计算
//        if (dtoList != null && dtoList.size() > 0) {
//            // 计算环比新增数据
//            for (int i = 0; i < dtoList.size() - 1; i++) {
//                FinanceReportOrderDto dto = dtoList.get(i); // 第一条数据
//                dto.setOrderTotal(dto.getHasApproveOrderTotal() + dto.getNoApproveOrderTotal());
//                dto.setOrderMoneyTotal(dto.getHasApproveOrderMoneyTotal().add(dto.getNoApproveOrderMoneyTotal()));
//                FinanceReportOrderDto nextDto = dtoList.get(i+1); // 下一条数据
//                nextDto.setOrderTotal(nextDto.getHasApproveOrderTotal() + nextDto.getNoApproveOrderTotal());
//                nextDto.setOrderMoneyTotal(nextDto.getHasApproveOrderMoneyTotal().add(nextDto.getNoApproveOrderMoneyTotal()));
//                dto.setOrderTotalChainNum(dto.getOrderTotal() - nextDto.getOrderTotal()); // 环比新增单量
//                dto.setOrderTotalChainPercent(NumberUtilsExt.percent(dto.getOrderTotalChainNum(), nextDto.getOrderTotal())); // 环比新增单量比
//                if (!dto.getOrderTotal().equals(new Integer("0"))) {
//                    dto.setOrderMoneyAverage(dto.getOrderMoneyTotal().divide(new BigDecimal(dto.getOrderTotal()), 0, ROUND_HALF_DOWN)); // 订单单值
//                } else {
//                    dto.setOrderMoneyAverage(new BigDecimal(0));
//                }
//
//                dtoList.set(i, dto);
//            }
//
//            // 去除最后面多余的数据
//            dtoList.remove(dtoList.size() - 1);
//        }
        MapContext result = MapContext.newOne();
        List<Date> dates = DateUtilsExt.findDates(DateUtilsExt.getFirstDayOfMonth(com.lwxf.mybatis.utils.DateUtil.now()), com.lwxf.mybatis.utils.DateUtil.now());
        //两月订单量对比
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date1 = calendar.getTime();//当月
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 2);//前二月
        Date date3 = calendar.getTime();
        List list = new ArrayList<>();
        try {
            ArrayList<Date> monthBetween = DateUtilsExt.getMonthBetween(date3, date1);
            Integer first = 0;
            for (Date date : monthBetween) {
                Date firstDayOfMonth = DateUtilsExt.getFirstDayOfMonth(date);
                Date lastDayOfMonth = DateUtilsExt.getLastDayOfMonth(date);
                MapContext mapContext = MapContext.newOne();
                mapContext.put("branchId", branchId);
                mapContext.put("startTime", firstDayOfMonth);
                mapContext.put("endTime", lastDayOfMonth);
                //订单数
                Integer count = this.customOrderService.findOrderCountByTime(mapContext);
                Double money = this.customOrderService.findOrderMoneyByTime(mapContext);
                //已审核订单数
                Integer auditCount = this.customOrderService.findAuditCountByTime(mapContext);
                //已审核订单数金额
                Double auditMoney = this.customOrderService.findAuditMoneyByTime(mapContext);
                //未审核订单数
                Integer noAuditCount = this.customOrderService.findnoAuditCountByTime(mapContext);
                //未审核订单金额
                Double noAuditMoney = this.customOrderService.findnoAuditMoneyByTime(mapContext);

                // 订单单值
                String averageMoney = "0.00";
                if (count != 0) {
                    averageMoney = String.format("%,.2f", (double) money / count);
                }

                //日均单量
                String averagePerDay="";
                //日均金额
                String averageMonePerDay="";
                //如果是本月，查出本日的日期
                Calendar calendar1=Calendar.getInstance();
                Integer day=calendar1.get(Calendar.DAY_OF_MONTH);
                Integer month=calendar1.get(Calendar.MONTH);
                Calendar calendar2=Calendar.getInstance();
                calendar2.setTime(date);
                Integer year=calendar2.get(Calendar.YEAR);
                Integer month2=calendar2.get(Calendar.MONTH);
                if(month==month2){
                    averagePerDay=String.format("%,.2f",(double)count/day);
                    averageMonePerDay=String.format("%,.2f",(double)money/day);
                }else {
                    day=DateUtilsExt.getDaysOfMonth(year+"-"+month2).size();
                    averagePerDay=String.format("%,.2f",(double)count/day);
                    averageMonePerDay=String.format("%,.2f",(double)money/day);
                }
                //同比新增数(去年同月份的订单数)
                Calendar calendar3=Calendar.getInstance();
                calendar3.add(Calendar.YEAR,-1);
                Date date2=calendar3.getTime();
                Date firstDayOfMontho = DateUtilsExt.getFirstDayOfMonth(date2);
                Date lastDayOfMontho = DateUtilsExt.getLastDayOfMonth(date2);
                mapContext.put("startTime", firstDayOfMontho);
                mapContext.put("endTime", lastDayOfMontho);
                Integer count0 = this.customOrderService.findOrderCountByTime(mapContext);
                Double money0 = this.customOrderService.findOrderMoneyByTime(mapContext);
                String yearOnYear="";
                String moneyYearOnYear=""; // 同比新增金额
                String yearOnYearPercentage="";
                String moneyYearOnYearPercentage=""; // 同比新增金额比
                //同比新增比
                yearOnYear = String.valueOf(count - count0);
                if (count0 == 0) {
//                    yearOnYearPercentage = ((count - count0) *100/ 1) + "%";
                    yearOnYearPercentage = "100%";
                } else {
                    yearOnYearPercentage = ((count - count0) *100/ count0)+ "%";
                }
                moneyYearOnYear = String.format("%,.2f", money - money0);
                if (money0 == 0) {
//                    moneyYearOnYearPercentage = ((money - money0) *100/ 1) + "%";
                    moneyYearOnYearPercentage = "100%";
                } else {
                    moneyYearOnYearPercentage = ((money - money0) *100/ money0)+ "%";
                }


                //环比新增数和新增比
                String chainRatio = "";
                String chainRatioPercentage = "";
                chainRatio = String.valueOf(count - first);
                if (first == 0) {
//                    chainRatioPercentage = ((count - first)*100 / 1) + "%";
                    chainRatioPercentage = "100%";
                } else {
                    chainRatioPercentage = ((count - first) *100/ first) + "%";
                }
                first = count;
                //前二月外协订单数
                mapContext.put("startTime", firstDayOfMonth);
                mapContext.put("endTime", lastDayOfMonth);
//                mapContext.put("coordination", 1);
//                Integer waiCount = this.customOrderService.findOrderCountByTime(mapContext);
                Integer waiCount = this.customOrderService.findCoordinationCountByTime(mapContext); // 外协数量
                MapContext mapContext1 = MapContext.newOne();
                mapContext1.put("date", dateFormat.format(date));
                mapContext1.put("orderNum", count); // 订单总数量
                mapContext1.put("orderMoney", String.format("%,.2f", money)); // 订单总金额
                mapContext1.put("auditCount", auditCount); // 已审核订单数量
                mapContext1.put("auditMoney", String.format("%,.2f", auditMoney)); // 已审核订单金额
                mapContext1.put("noAuditCount", noAuditCount); // 未审核订单数量
                mapContext1.put("noAuditMoney", String.format("%,.2f", noAuditMoney)); // 未审核订单金额
                mapContext1.put("averagePerDay", averagePerDay); // 日均单值
                mapContext1.put("yearOnYear", yearOnYear); // 同比新增订单数
                mapContext1.put("yearOnYearPercentage", yearOnYearPercentage); // 同比新增订单比
                mapContext1.put("chainRatio", chainRatio);// 环比新增单量
                mapContext1.put("chainRatioPercentage", chainRatioPercentage);// 环比新增单量比
                mapContext1.put("waiOrderNum", waiCount); // 外协单数量
                mapContext1.put("averageMoney", averageMoney); // 订单单值
                mapContext1.put("averageMonePerDay", averageMonePerDay); // 日均金额
                mapContext1.put("moneyYearOnYear", moneyYearOnYear); // 同比新增订单金额
                mapContext1.put("moneyYearOnYearPercentage", moneyYearOnYearPercentage); // 同比新增订单金额比
                list.add(mapContext1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //本月订单财务统计（表格数据）
        List newList=new ArrayList();
        Integer first = 0;
        for (Date date : dates) {
            MapContext mapContext = MapContext.newOne();
            mapContext.put("branchId", branchId);
            mapContext.put("startTime", date);
            mapContext.put("endTime", date);
            //订单数
            Integer count = this.customOrderService.findOrderCountByTime(mapContext);
            Double money = this.customOrderService.findOrderMoneyByTime(mapContext);
            //已审核订单数
            Integer auditCount = this.customOrderService.findAuditCountByTime(mapContext);
            Double auditMoney = this.customOrderService.findAuditMoneyByTime(mapContext);
            //未审核订单数
            Integer noAuditCount = this.customOrderService.findnoAuditCountByTime(mapContext);
            Double noAuditMoney = this.customOrderService.findnoAuditMoneyByTime(mapContext);
            String chainRatio = "";
            String chainRatioPercentage = "";
            chainRatio = String.valueOf(count - first);
            if (first == 0) {
//                chainRatioPercentage = ((count - first)*100 / 1) + "%";
                chainRatioPercentage = "100%";
            } else {
                chainRatioPercentage = ((count - first)*100 / first) + "%";
            }
            first = count;
            // 订单单值
            String averageMoney = "0.00";
            if (count != 0) {
                averageMoney = String.format("%,.2f", (double) money / count);
            }

            //外协订单数
            mapContext.put("coordination", 1);
//            Integer waiCount = this.customOrderService.findOrderCountByTime(mapContext);
            Integer waiCount = this.customOrderService.findCoordinationCountByTime(mapContext); // 外协数量
            MapContext mapContext1 = MapContext.newOne();
            mapContext1.put("date", format.format(date));
            mapContext1.put("orderNum", count); // 订单总数量
            mapContext1.put("orderMoney", String.format("%,.2f", money)); // 订单金额
            mapContext1.put("auditCount", auditCount); // 已审核订单数量
            mapContext1.put("auditMoney", String.format("%,.2f", auditMoney)); // 已审核订单金额
            mapContext1.put("noAuditCount", noAuditCount); // 未审核订单数量
            mapContext1.put("noAuditMoney", String.format("%,.2f", noAuditMoney)); // 未审核订单金额
            mapContext1.put("waiOrderNum", waiCount);// 外协单数量
            mapContext1.put("chainRatio", chainRatio);// 环比新增单量
            mapContext1.put("chainRatioPercentage", chainRatioPercentage);// 环比新增单量比
            mapContext1.put("averageMoney", averageMoney); // 订单单值
            newList.add(mapContext1);
        }

        //当月产品系列明细统计
        Integer deFlag = 0;
        String seriesType = "orderProductSeries";
        //查询产品系列
        List<Basecode> basecodes = this.basecodeService.findByTypeAndDelFlag(seriesType, deFlag);
        List productTypelist=new ArrayList();
        //各产品订单总数及近30天未打款数
        //查询产品分类
        List<Basecode> orderProductType = this.basecodeService.findByTypeAndDelFlag("orderProductType", 0);
        if(orderProductType!=null&&orderProductType.size()>0) {
            for (Basecode type : orderProductType) {
                MapContext productMap=MapContext.newOne();
                productMap.put("name",type.getValue());
                Integer productType =Integer.valueOf(type.getCode());
        //橱柜
        List list0 = new ArrayList();
        //橱柜订单总下单数和近30天未打款数量
        MapContext mapCon = MapContext.newOne();
        mapCon.put("branchId", branchId);
        mapCon.put("startTime", DateUtilsExt.getFirstDayOfMonth(com.lwxf.mybatis.utils.DateUtil.now()));
        mapCon.put("endTime", com.lwxf.mybatis.utils.DateUtil.now());
        mapCon.put("type", productType);
        MapContext cupboardCount = this.customOrderService.findCupboardOrWardrobeCountByTime(mapCon);
        cupboardCount.put("seriesName", type.getValue()+"总量");
        cupboardCount.put("payMoney", String.format("%,.2f", cupboardCount.getTypedValue("payMoney", BigDecimal.class)));
        cupboardCount.put("nopayMoney", String.format("%,.2f", cupboardCount.getTypedValue("nopayMoney", BigDecimal.class)));
        list0.add(cupboardCount);
        //橱柜系列产品数和下单数和近30天未打款数量
        for (Basecode basecode : basecodes) {
            MapContext mapContext = MapContext.newOne();
            mapContext.put("branchId", branchId);
            mapContext.put("startTime", DateUtilsExt.getFirstDayOfMonth(com.lwxf.mybatis.utils.DateUtil.now()));
            mapContext.put("endTime", com.lwxf.mybatis.utils.DateUtil.now());
            mapContext.put("type", productType);
            Integer code = Integer.valueOf(basecode.getCode());
            mapContext.put("series", code);
            //今日下单数
            MapContext payNum = this.customOrderService.countOrderSeriesByDay(mapContext);
            //近30天未付款数
            Calendar calendartemp = Calendar.getInstance();
            Calendar calendar0 = Calendar.getInstance();
            calendartemp.set(Calendar.DAY_OF_MONTH, calendar0.get(Calendar.DATE) - 29);//前三十天
            Date start = calendartemp.getTime();
            mapContext.put("startTime", start);
            mapContext.put("endTime", com.lwxf.mybatis.utils.DateUtil.now());
            MapContext nopayNum = this.customOrderService.countNopayOrderSeriesByDay(mapContext);
            MapContext map = MapContext.newOne();
            map.put("seriesName", basecode.getValue());
            map.put("payNum", payNum.getTypedValue("count", Integer.class));
            map.put("payMoney", String.format("%,.2f", payNum.getTypedValue("productPrice", BigDecimal.class)));
            map.put("nopayNum", nopayNum.getTypedValue("count", Integer.class));
            map.put("nopayMoney", String.format("%,.2f", nopayNum.getTypedValue("productPrice", BigDecimal.class)));
            list0.add(map);
        }
                productMap.put("list",list0);
                productTypelist.add(productMap);
            }
        }
//                //衣柜订单总下单数和近30天未打款数量
//                mapCon.put("type", 1);
//                MapContext wardrobeCount = this.customOrderService.findCupboardOrWardrobeCountByTime(mapCon);
//                wardrobeCount.put("payMoney", String.format("%,.2f", wardrobeCount.getTypedValue("payMoney", BigDecimal.class)));
//                wardrobeCount.put("nopayMoney", String.format("%,.2f", wardrobeCount.getTypedValue("nopayMoney", BigDecimal.class)));
//        //衣柜
//        List list1 = new ArrayList();
//        wardrobeCount.put("seriesName", "衣柜总量");
//        list1.add(wardrobeCount);
//        for (Basecode basecode : basecodes) {
//            MapContext mapContext = MapContext.newOne();
//            mapContext.put("branchId", branchId);
//            mapContext.put("startTime", DateUtilsExt.getFirstDayOfMonth(com.lwxf.mybatis.utils.DateUtil.now()));
//            mapContext.put("endTime", com.lwxf.mybatis.utils.DateUtil.now());
//            Integer productType = 1;
//            mapContext.put("type", productType);
//            Integer code = Integer.valueOf(basecode.getCode());
//            mapContext.put("series", code);
//            //今日下单数
//            MapContext payNum = this.customOrderService.countOrderSeriesByDay(mapContext);
//            //近30天未付款数
//            Calendar calendartemp = Calendar.getInstance();
//            Calendar calendar0 = Calendar.getInstance();
//            calendartemp.set(Calendar.DAY_OF_MONTH, calendar0.get(Calendar.DATE) - 29);//前三十天
//            Date start = calendartemp.getTime();
//            mapContext.put("startTime", start);
//            mapContext.put("endTime", com.lwxf.mybatis.utils.DateUtil.now());
//            MapContext nopayNum = this.customOrderService.countNopayOrderSeriesByDay(mapContext);
//            MapContext map = MapContext.newOne();
//            map.put("seriesName", basecode.getValue());
//            map.put("payNum", payNum.getTypedValue("count", Integer.class));
//            map.put("payMoney", String.format("%,.2f", payNum.getTypedValue("productPrice", BigDecimal.class)));
//            map.put("nopayNum", nopayNum.getTypedValue("count", Integer.class));
//            map.put("nopayMoney", String.format("%,.2f", nopayNum.getTypedValue("productPrice", BigDecimal.class)));
//            list1.add(map);
//        }

        //当月产品统计
        MapContext params = MapContext.newOne();
        params.put("branchId", branchId);
        params.put("startTime", DateUtilsExt.getFirstDayOfMonth(com.lwxf.mybatis.utils.DateUtil.now()));
        params.put("endTime", com.lwxf.mybatis.utils.DateUtil.now());
        List<MapContext> products = new ArrayList<>();
        //       int[] typeList=new int[]{0,1,4,5};
        if(orderProductType!=null&&orderProductType.size()>0) {
            for (Basecode basecode : orderProductType) {
                params.put("productType", basecode.getCode());
                MapContext map = this.customOrderService.findOrderCountByProductType(params);
                products.add(map);
            }
        }
        //当月产品明细统计
        List productsBydateList=new ArrayList<>();
        List title=new ArrayList();
        Map map0=new HashMap();
        map0.put("title","日期");
        map0.put("key","date");
        title.add(map0);
        Map map1=new HashMap();
        map1.put("title","订单数量");
        map1.put("key","OrderCount");
        title.add(map1);
        Map map2=new HashMap();
        map2.put("title","订单金额");
        map2.put("key","orderMoney");
        title.add(map2);
        Map map3=new HashMap();
        map3.put("title","外协单数量");
        map3.put("key","waiCount");
        title.add(map3);
        Map map4=new HashMap();
        map4.put("title","外协单金额");
        map4.put("key","waiMoney");
        title.add(map4);
        Map map5=new HashMap();
        map5.put("title","延期预警订单数量");
        map5.put("key","delayWarningCount");
        title.add(map5);
        Map map6=new HashMap();
        map6.put("title","延期订单数量");
        map6.put("key","delayCount");
        title.add(map6);
        if(orderProductType!=null&&orderProductType.size()>0){
            for(Basecode basecode:orderProductType) {
                Map newMap=new HashMap();
                newMap.put("title",basecode.getValue()+"数量");
                newMap.put("key",basecode.getCode());
                title.add(newMap);
                Map newMap1=new HashMap();
                newMap1.put("title",basecode.getValue()+"金额");
                newMap1.put("key","M"+basecode.getCode());
                title.add(newMap1);
            }
        }
        for(Date date:dates){
            MapContext mapCont=MapContext.newOne();
            params.put("startTime", date);
            params.put("endTime",  date);
            Integer orderCountByTime = this.customOrderService.findOrderCountByTime(params);
            Double orderMoneyByTime = this.customOrderService.findOrderMoneyByTime(params);
            if(orderProductType!=null&&orderProductType.size()>0){
                for(Basecode basecode:orderProductType) {
                    params.put("productType", basecode.getCode());
                    MapContext map = this.customOrderService.findOrderCountByProductType(params);
                    mapCont.put(basecode.getCode(),map.getTypedValue("count",Integer.class));
                    mapCont.put("M"+basecode.getCode(),String.format("%,.2f", map.getTypedValue("orderPrice",BigDecimal.class)));
                }
            }
            // 查询延期预警和延期订单数量
            params.remove("productType");
            Integer delayCount = this.customOrderService.findDelayOrderCountByTime(params);
            Integer delayWarningCount = this.customOrderService.findDelayWarningOrderCountByTime(params);
            Integer waiCount = this.customOrderService.findCoordinationCountByTime(params); // 外协数量
            Double waiMoney = this.customOrderService.findCoordinationMoneyByTime(params); // 外协金额
            params.remove("coordination");
            mapCont.put("date",format.format(date)); // 日期时间
            mapCont.put("OrderCount",orderCountByTime); // 订单数据
            mapCont.put("orderMoney", String.format("%,.2f", orderMoneyByTime)); // 订单金额
            mapCont.put("waiCount",waiCount); // 外协订单数量
            mapCont.put("waiMoney",String.format("%,.2f", waiMoney)); // 外协订单金额
            mapCont.put("delayWarningCount", delayWarningCount); // 延期预警订单数量
            mapCont.put("delayCount", delayCount); // 延期订单数量
            productsBydateList.add(mapCont);
        }
        params.put("startTime", DateUtilsExt.getFirstDayOfMonth(com.lwxf.mybatis.utils.DateUtil.now()));
        params.put("endTime",  com.lwxf.mybatis.utils.DateUtil.now());
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
        //数据分析（售后分析）
        //售后总数
        MapContext mapContext=MapContext.newOne();
        mapContext.put("branchId",branchId);
        mapContext.put("startTime", date1);
        mapContext.put("endTime", date1);
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

        result.put("contrast", list); // 两月对比数据
        result.put("orderCount", newList); // 本月订单统计
        result.put("productTypelist", productTypelist); // 橱柜系列
        result.put("products", products); // 本月产品占比统计数据
        result.put("doorCount", doorCount); // 本月产品门型财务统计
        result.put("doorcolorCount", doorcolorCount); // 本月门板颜色财务统计
        result.put("orderAnalysis", orderAnalysis); // 本月数据分析
        result.put("productsBydateList", productsBydateList); // 本月订单财务明细统计数据
        result.put("aftersaleAnalysis", aftersaleAnalysis);
        result.put("dealereAnalysis", dealereAnalysis);
        result.put("title", title); // 产品明细标题
        return ResultFactory.generateRequestResult(result);

    }

    @Override
    public RequestResult newOrderCountByYear(String branchId) {
        MapContext result = MapContext.newOne();
        //获取月份集合
        List<Date> dates = new ArrayList<>();
        try {
            dates=DateUtilsExt.getMonthBetween(DateUtilsExt.getFirstDayOfYear(com.lwxf.mybatis.utils.DateUtil.now()), com.lwxf.mybatis.utils.DateUtil.now());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //两年订单量对比
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        Calendar calendar = Calendar.getInstance();
        Date date1 = calendar.getTime();//当年
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 2);//前年
        Date date3 = calendar.getTime();
        List list = new ArrayList<>();
        try {
            List<Date> yearBetween = DateUtilsExt.getYearBetween(date3, date1);
            Integer first = 0;
            Double firstMoney = 0d;
            for (Date date : yearBetween) {
                Date firstDayOfMonth = DateUtilsExt.getFirstDayOfYear(date);
                Date lastDayOfMonth = DateUtilsExt.getLastDayOfYear(date);
                MapContext mapContext = MapContext.newOne();
                mapContext.put("branchId", branchId);
                mapContext.put("startTime", firstDayOfMonth);
                mapContext.put("endTime", lastDayOfMonth);
                //订单数
                Integer count = this.customOrderService.findOrderCountByTime(mapContext);
                Double money = this.customOrderService.findOrderMoneyByTime(mapContext); // 订单金额
//				//已审核订单数
//				Integer auditCount = this.customOrderService.findAuditCountByTime(mapContext);
//				//未审核订单数
//				Integer noAuditCount = this.customOrderService.findnoAuditCountByTime(mapContext);
                //月均量
                Calendar calendar1=Calendar.getInstance();
                calendar1.setTime(date);
                Calendar calendar2=Calendar.getInstance();
                Integer monthNum=1;
                if(calendar1.get(Calendar.YEAR)==calendar2.get(Calendar.YEAR)){
                    monthNum=calendar2.get(Calendar.MONTH)+1;
                }else {
                    monthNum=12;
                }
                String monthlyaverage=String.format("%,.2f",(double)count/monthNum);
                String monthMoneyaverage = String.format("%,.2f",(double)money/monthNum); // 月金额
                String chainRatio = "";
                String moneyChainRatio = ""; // 同比新增总金额
                String chainRatioPercentage = "";
                String moneyChainRatioPercentage = ""; // 同比新增订单金额比
                    chainRatio = String.valueOf(count - first);
                    if (first == 0) {
//                        chainRatioPercentage = ((count - first)*100 / 1) + "%";
                        chainRatioPercentage = "100%";
                    } else {
                        chainRatioPercentage = ((count - first)*100 / first) + "%";
                    }

                // 金额计算
                    moneyChainRatio = String.format("%,.2f",(money - firstMoney));
                    if (firstMoney == 0) {
//                        moneyChainRatioPercentage = ((money - firstMoney)*100 / 1) + "%";
                        moneyChainRatioPercentage = "100%";
                    } else {
                        moneyChainRatioPercentage = Math.round((money - firstMoney)*100 / first) + "%";
                    }
                first = count;
                firstMoney = money;
                // 订单单值
                String averageMoney = "0.00";
                if (count != 0) {
                    averageMoney = String.format("%,.2f", (double) money / count);
                }

                //外协订单数
//                mapContext.put("coordination", 1);
//                Integer waiCount = this.customOrderService.findOrderCountByTime(mapContext);
                Integer waiCount = this.customOrderService.findCoordinationCountByTime(mapContext); // 外协数量
                MapContext mapContext1 = MapContext.newOne();
                mapContext1.put("date", dateFormat.format(date)); // 日期时间
                mapContext1.put("orderNum", count); // 订单总数量
                mapContext1.put("orderMoney", String.format("%,.2f", money)); // 订单总金额
                mapContext1.put("orderaverage", averageMoney); // 订单单值
//				mapContext1.put("auditCount", auditCount);
//				mapContext1.put("noAuditCount", noAuditCount);
                mapContext1.put("monthlyaverage", monthlyaverage); // 月均订单数量
                mapContext1.put("monthMoneyaverage", monthMoneyaverage); // 月均订单金额
                mapContext1.put("waiOrderNum", waiCount); // 外协单数量
                mapContext1.put("chainRatio", chainRatio); // 同比新增订单数量
                mapContext1.put("moneyChainRatio", moneyChainRatio); // 同比新增订单金额
                mapContext1.put("chainRatioPercentage", chainRatioPercentage); // 同比新增订单比
                mapContext1.put("moneyChainRatioPercentage", moneyChainRatioPercentage); // 同比新增订单金额比
                list.add(mapContext1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //本年月份订单统计（表格数据）
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        List newList=new ArrayList();
        Integer first = 0;
        Double firstMoney = 0d;
        for (Date date : dates) {
            MapContext mapContext = MapContext.newOne();
            mapContext.put("branchId", branchId);
            mapContext.put("startTime", DateUtilsExt.getFirstDayOfMonth(date));
            mapContext.put("endTime", DateUtilsExt.getLastDayOfMonth(date));
            //订单数
            Integer count = this.customOrderService.findOrderCountByTime(mapContext);
            Double money = this.customOrderService.findOrderMoneyByTime(mapContext); // 订单金额
            //已审核订单数
            Integer auditCount = this.customOrderService.findAuditCountByTime(mapContext);
            Double auditMoney = this.customOrderService.findAuditMoneyByTime(mapContext); // 已审核订单金额
            //未审核订单数
            Integer noAuditCount = this.customOrderService.findnoAuditCountByTime(mapContext);
            Double noAuditMoney = this.customOrderService.findnoAuditMoneyByTime(mapContext); // 未审核订单金额
            //同比新增数(去年同月份的订单数)
            Calendar calendar3=Calendar.getInstance();
            calendar3.add(Calendar.YEAR,-1);
            Date date2=calendar3.getTime();
            Date firstDayOfMonth = DateUtilsExt.getFirstDayOfMonth(date2);
            Date lastDayOfMonth = DateUtilsExt.getLastDayOfMonth(date2);
            mapContext.put("startTime", firstDayOfMonth);
            mapContext.put("endTime", lastDayOfMonth);
            //去年同期订单数 金额
            Integer count0 = this.customOrderService.findOrderCountByTime(mapContext);
            Double money0 = this.customOrderService.findOrderMoneyByTime(mapContext);
            String yearOnYear=""; // 同比新增订单数
            String yearOnYearPercentage=""; // 同比新增订单数比
            String coevalRatioMoney = ""; // 同比新增订单金额
            yearOnYear = String.valueOf(count - count0);
            coevalRatioMoney = String.format("%,.2f",(money - money0));
            if (count0 == 0) {
//                yearOnYearPercentage = ((count - count0)*100 / 1) + "%";
                yearOnYearPercentage = "100%";
            } else {
                yearOnYearPercentage = ((count - count0)*100 / count0) + "%";
            }

            String chainRatio = "";
            String moneyChainRatio = ""; // 环比新增金额
            String chainRatioPercentage = "";
            String moneyChainRatioPercentage = ""; // 环比新增金额比
                chainRatio = String.valueOf(count - first);
                if (first == 0) {
//                    chainRatioPercentage = ((count - first)*100 / 1) + "%";
                    chainRatioPercentage = "100%";
                } else {
                    chainRatioPercentage = ((count - first)*100 / first) + "%";
                }

            // 金额计算
                moneyChainRatio = String.format("%,.2f",(money - firstMoney));
                if (firstMoney == 0) {
//                    moneyChainRatioPercentage = ((money - firstMoney)*100 / 1) + "%";
                    moneyChainRatioPercentage = "100%";
                } else {
                    moneyChainRatioPercentage = ((money - firstMoney)*100 / first) + "%";
                }
            first = count;
            firstMoney = money;

            // 订单单值
            String averageMoney = "0.00";
            if (count != 0) {
                averageMoney = String.format("%,.2f", (double) money / count);
            }

            // 日均量
            String orderaverageOfDay = "";
            int daysOfMonth = 1;
            // 判断是否是当月
            boolean flag = DateUtil.dateTimeToString(DateUtil.getSystemDate(), "MM").equals(DateUtil.dateTimeToString(date, "MM"));
            if (flag) {
                // 当月天数
//                List<String> dateList = DateUtilsExt.getListDayDate(DateUtilsExt.getDayBegin(), -(Integer.valueOf(DateUtil.dateTimeToString(DateUtil.getSystemDate(), "d")) + 1));
//                if (dateList != null && dateList.size() > 0) {
//                    daysOfMonth = dateList.size() - 1;
//                }
                daysOfMonth = Integer.valueOf(DateUtil.dateTimeToString(DateUtil.getSystemDate(), "d"));
            } else {
                List<Date> dateList = DateUtilsExt.getDaysOfMonth(DateUtil.dateTimeToString(date, "yyyy-MM"));
                if (dateList != null && dateList.size() > 0) {
                    daysOfMonth = dateList.size();
                }
            }
            orderaverageOfDay = String.format("%,.2f", (double) count / daysOfMonth);

            //外协订单数
//            mapContext.put("coordination", 1);
//            Integer waiCount = this.customOrderService.findOrderCountByTime(mapContext);
            Integer waiCount = this.customOrderService.findCoordinationCountByTime(mapContext); // 外协数量
            MapContext mapContext1 = MapContext.newOne();
            mapContext1.put("date", format.format(date));
            mapContext1.put("orderNum", count);// 订单总数量
            mapContext1.put("orderMoney", String.format("%,.2f", money)); // 订单总金额
            mapContext1.put("orderaverage", averageMoney); // 订单单值
            mapContext1.put("orderaverageOfDay", orderaverageOfDay); // 日均单数
            mapContext1.put("auditCount", auditCount);// 已审核订单数量
            mapContext1.put("auditMoney", String.format("%,.2f", auditMoney));// 已审核订单金额
            mapContext1.put("noAuditCount", noAuditCount); // 未审核订单数量
            mapContext1.put("noAuditMoney", String.format("%,.2f", noAuditMoney)); // 未审核订单金额
            mapContext1.put("waiOrderNum", waiCount);
            mapContext1.put("chainRatio", chainRatio);// 环比新增订单数量
            mapContext1.put("moneyChainRatio", moneyChainRatio); // 环比新增订单金额
            mapContext1.put("chainRatioPercentage", chainRatioPercentage);// 环比新增订单比
            mapContext1.put("moneyChainRatioPercentage", moneyChainRatioPercentage); // 环比新增订单金额比
            mapContext1.put("coevalRatio", yearOnYear); // 同比新增订单数
            mapContext1.put("coevalRatioMoney", coevalRatioMoney); // 同比新增订单金额
            newList.add(mapContext1);
        }
        //订单总数和外协单数据对比明细（折线图数据）
//		List orderAndCoordination=new ArrayList();
//		for(Date date:dates){
//			MapContext mapContext = MapContext.newOne();
//			mapContext.put("branchId", branchId);
//			mapContext.put("startTime", DateUtilsExt.getFirstDayOfMonth(date));
//			mapContext.put("endTime", DateUtilsExt.getLastDayOfMonth(date));
//			//订单数
//			Integer count = this.customOrderService.findOrderCountByTime(mapContext);
//			//外协订单数
//			mapContext.put("coordination", 1);
//			Integer waiCount = this.customOrderService.findOrderCountByTime(mapContext);
//			MapContext orderAndWaiXie=MapContext.newOne();
//			orderAndWaiXie.put("count",count);
//			orderAndWaiXie.put("waiCount",waiCount);
//			orderAndCoordination.add(orderAndWaiXie);
//		}
        //产品系列明细统计
        // 产品系列明细统计
        Integer deFlag = 0;
        String seriesType = "orderProductSeries";
        //查询产品系列
        List<Basecode> basecodes = this.basecodeService.findByTypeAndDelFlag(seriesType, deFlag);
        List productTypelist=new ArrayList();
        //各产品订单总数及近30天未打款数
        //查询产品分类
        List<Basecode> orderProductType = this.basecodeService.findByTypeAndDelFlag("orderProductType", 0);
        if(orderProductType!=null&&orderProductType.size()>0) {
            for (Basecode type : orderProductType) {
                MapContext productMap = MapContext.newOne();
                productMap.put("name", type.getValue());
                Integer productType = Integer.valueOf(type.getCode());
        //橱柜
        List list0 = new ArrayList();
        //橱柜订单总下单数和近30天未打款数量
        MapContext mapCon = MapContext.newOne();
        mapCon.put("branchId", branchId);
        mapCon.put("startTime", DateUtilsExt.getFirstDayOfYear(com.lwxf.mybatis.utils.DateUtil.now()));
        mapCon.put("endTime", com.lwxf.mybatis.utils.DateUtil.now());
        mapCon.put("type", productType);
        MapContext cupboardCount = this.customOrderService.findCupboardOrWardrobeCountByTime(mapCon);
        cupboardCount.put("seriesName", type.getValue()+"总量");
        cupboardCount.put("payMoney", String.format("%,.2f", cupboardCount.getTypedValue("payMoney", BigDecimal.class)));
        cupboardCount.put("nopayMoney", String.format("%,.2f", cupboardCount.getTypedValue("nopayMoney", BigDecimal.class)));
        list0.add(cupboardCount);
        //橱柜系列产品数和下单数和近30天未打款数量
        for (Basecode basecode : basecodes) {
            MapContext mapContext = MapContext.newOne();
            mapContext.put("branchId", branchId);
            mapContext.put("startTime", DateUtilsExt.getFirstDayOfYear(com.lwxf.mybatis.utils.DateUtil.now()));
            mapContext.put("endTime",  com.lwxf.mybatis.utils.DateUtil.now());
            mapContext.put("type", productType);
            Integer code = Integer.valueOf(basecode.getCode());
            mapContext.put("series", code);
            //今年下单数
            MapContext payNum = this.customOrderService.countOrderSeriesByDay(mapContext);
            //近30天未付款数
            Calendar calendar0 = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, calendar0.get(Calendar.DATE) - 29);//前三十天
            Date start = calendar.getTime();
            mapContext.put("startTime", start);
            mapContext.put("endTime", com.lwxf.mybatis.utils.DateUtil.now());
            MapContext nopayNum = this.customOrderService.countNopayOrderSeriesByDay(mapContext);
            MapContext map = MapContext.newOne();
            map.put("seriesName", basecode.getValue()); // 系列名称
            map.put("payNum", payNum.getTypedValue("count", Integer.class)); // 已下单数量
            map.put("payMoney", String.format("%,.2f", payNum.getTypedValue("productPrice", BigDecimal.class))); // 已下单金额
            map.put("nopayNum", nopayNum.getTypedValue("count", Integer.class)); // 30天未打款数据
            map.put("nopayMoney", String.format("%,.2f", nopayNum.getTypedValue("productPrice", BigDecimal.class))); // 30天未打款金额
            list0.add(map);
        }
                productMap.put("list",list0);
                productTypelist.add(productMap);
            }
        }
//                //衣柜订单总下单数和近30天未打款数量
//                mapCon.put("type", 1);
//                MapContext wardrobeCount = this.customOrderService.findCupboardOrWardrobeCountByTime(mapCon);
//                wardrobeCount.put("payMoney", String.format("%,.2f", wardrobeCount.getTypedValue("payMoney", BigDecimal.class)));
//                wardrobeCount.put("nopayMoney", String.format("%,.2f", wardrobeCount.getTypedValue("nopayMoney", BigDecimal.class)));
//        //衣柜
//        List list1 = new ArrayList();
//        wardrobeCount.put("seriesName", "衣柜总量");
//        list1.add(wardrobeCount);
//        for (Basecode basecode : basecodes) {
//            MapContext mapContext = MapContext.newOne();
//            mapContext.put("branchId", branchId);
//            mapContext.put("startTime", DateUtilsExt.getFirstDayOfYear(com.lwxf.mybatis.utils.DateUtil.now()));
//            mapContext.put("endTime",  com.lwxf.mybatis.utils.DateUtil.now());
//            Integer productType = 1;
//            mapContext.put("type", productType);
//            Integer code = Integer.valueOf(basecode.getCode());
//            mapContext.put("series", code);
//            //下单数
//            MapContext payNum = this.customOrderService.countOrderSeriesByDay(mapContext);
//            //近30天未付款数
//            Calendar calendar0 = Calendar.getInstance();
//            calendar.set(Calendar.DAY_OF_MONTH, calendar0.get(Calendar.DATE) - 29);//前三十天
//            Date start = calendar.getTime();
//            mapContext.put("startTime", start);
//            mapContext.put("endTime", com.lwxf.mybatis.utils.DateUtil.now());
//            MapContext nopayNum = this.customOrderService.countNopayOrderSeriesByDay(mapContext);
//            MapContext map = MapContext.newOne();
//            map.put("seriesName", basecode.getValue());
//            map.put("payNum", payNum.getTypedValue("count", Integer.class));
//            map.put("payMoney", String.format("%,.2f", payNum.getTypedValue("productPrice", BigDecimal.class)));
//            map.put("nopayNum", nopayNum.getTypedValue("count", Integer.class));
//            map.put("nopayMoney", String.format("%,.2f", nopayNum.getTypedValue("productPrice", BigDecimal.class)));
//            list1.add(map);
//        }
        //年产品统计
        MapContext params = MapContext.newOne();
        params.put("branchId", branchId);
        params.put("startTime", DateUtilsExt.getFirstDayOfYear(com.lwxf.mybatis.utils.DateUtil.now()));
        params.put("endTime",  com.lwxf.mybatis.utils.DateUtil.now());
        List<MapContext> products = new ArrayList<>();
        //       int[] typeList=new int[]{0,1,4,5};
        if(orderProductType!=null&&orderProductType.size()>0) {
            for (Basecode basecode : orderProductType) {
                params.put("productType", basecode.getCode());
                MapContext map = this.customOrderService.findOrderCountByProductType(params);
                products.add(map);
            }
        }
        //年产品月份明细统计
        List productsBydateList=new ArrayList<>();
        List title=new ArrayList();
        Map map0=new HashMap();
        map0.put("title","日期");
        map0.put("key","date");
        title.add(map0);
        Map map1=new HashMap();
        map1.put("title","订单数量");
        map1.put("key","OrderCount");
        title.add(map1);
        Map map2=new HashMap();
        map2.put("title","订单金额");
        map2.put("key","orderMoney");
        title.add(map2);
        Map map3=new HashMap();
        map3.put("title","外协单数量");
        map3.put("key","waiCount");
        title.add(map3);
        Map map4=new HashMap();
        map4.put("title","外协单金额");
        map4.put("key","waiMoney");
        title.add(map4);
        Map map5=new HashMap();
        map5.put("title","延期预警订单数量");
        map5.put("key","delayWarningCount");
        title.add(map5);
        Map map6=new HashMap();
        map6.put("title","延期订单数量");
        map6.put("key","delayCount");
        title.add(map6);
        if(orderProductType!=null&&orderProductType.size()>0){
            for(Basecode basecode:orderProductType) {
                Map newMap=new HashMap();
                newMap.put("title",basecode.getValue()+"数量");
                newMap.put("key",basecode.getCode());
                title.add(newMap);
                Map newMap1=new HashMap();
                newMap1.put("title",basecode.getValue()+"金额");
                newMap1.put("key","M"+basecode.getCode());
                title.add(newMap1);
            }
        }
        Integer second=0;
        for(Date date:dates){
            MapContext mapCont=MapContext.newOne();
            MapContext mapContextYear=MapContext.newOne();
            mapContextYear.put("branchId", branchId);
            mapContextYear.put("startTime", DateUtilsExt.getFirstDayOfMonth(date));
            mapContextYear.put("endTime",  DateUtilsExt.getLastDayOfMonth(date));
            Integer orderCountByTime = this.customOrderService.findOrderCountByTime(mapContextYear);
            Double orderMoneyByTime = this.customOrderService.findOrderMoneyByTime(mapContextYear);
            if(orderProductType!=null&&orderProductType.size()>0){
                for(Basecode basecode:orderProductType) {
                    mapContextYear.put("productType", basecode.getCode());
                    MapContext map = this.customOrderService.findOrderCountByProductType(mapContextYear);
                    mapCont.put(basecode.getCode(),map.getTypedValue("count",Integer.class));
                    mapCont.put("M"+basecode.getCode(),String.format("%,.2f", map.getTypedValue("orderPrice",BigDecimal.class)));
                }
            }
            // 查询延期预警和延期订单数量
            mapContextYear.remove("productType");
            Integer delayCount = this.customOrderService.findDelayOrderCountByTime(mapContextYear);
            Integer delayWarningCount = this.customOrderService.findDelayWarningOrderCountByTime(mapContextYear);
            // 外协单数量 金额
//            mapContextYear.put("coordination", 1);
            Integer waiCount = this.customOrderService.findCoordinationCountByTime(mapContextYear); // 外协数量
            Double waiMoney = this.customOrderService.findCoordinationMoneyByTime(mapContextYear);
            mapCont.put("date",format.format(date)); // 日期时间
            mapCont.put("OrderCount",orderCountByTime); // 订单数据
            mapCont.put("orderMoney", String.format("%,.2f", orderMoneyByTime)); // 订单金额
            mapCont.put("waiCount",waiCount); // 外协订单数量
            mapCont.put("waiMoney",String.format("%,.2f", waiMoney)); // 外协订单金额
            mapCont.put("delayWarningCount", delayWarningCount); // 延期预警订单数量
            mapCont.put("delayCount", delayCount); // 延期订单数量
            productsBydateList.add(mapCont);
        }
        //当月产品门型统计
        List<MapContext> doorCount = this.customOrderService.findOrderCountByDoor(params);
        //当月门板颜色统计
        List<MapContext> doorcolorCount = this.customOrderService.findOrderCountByDoorcolor(params);
        //当月柜体颜色统计
        //List<MapContext> colorCount=this.customOrderService.findOrderCountBycolor(params);


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

        result.put("contrast", list);
        result.put("formCount",newList);
        //result.put("orderAndCoordination", orderAndCoordination);
        result.put("productTypelist", productTypelist);
        result.put("title", title);
        result.put("productsBydateList", productsBydateList);
        result.put("products", products);
        result.put("doorCount", doorCount);
        result.put("doorcolorCount", doorcolorCount);
        result.put("orderAnalysis", orderAnalysis);
        result.put("aftersaleAnalysis", aftersaleAnalysis);
        result.put("dealereAnalysis", dealereAnalysis);
        return ResultFactory.generateRequestResult(result);
    }


}
