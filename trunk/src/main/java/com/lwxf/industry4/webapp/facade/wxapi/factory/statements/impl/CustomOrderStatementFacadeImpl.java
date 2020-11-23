package com.lwxf.industry4.webapp.facade.wxapi.factory.statements.impl;

import com.lwxf.industry4.webapp.bizservice.customorder.OrderProductService;
import com.lwxf.industry4.webapp.bizservice.statements.app.factory.orderStatements.CustomOrderStatementService;
import com.lwxf.industry4.webapp.common.enums.order.OrderProductType;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.DateUtilsExt;
import com.lwxf.industry4.webapp.domain.dto.customorder.OrderProductDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByMonthDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByQuarterDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByWeekDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByYearDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrder;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.wxapi.factory.statements.CustomOrderStatementFacade;
import com.lwxf.mybatis.utils.DateUtil;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component("wxCustomOrderStatementFacade")
public class CustomOrderStatementFacadeImpl extends BaseFacadeImpl implements CustomOrderStatementFacade {

    @Resource(name = "customOrderStatementService")
    private CustomOrderStatementService customOrderStatementService;
    @Resource(name = "orderProductService")
    private OrderProductService orderProductService;

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult selectByfilter(Date StartDate,Date endDate,MapContext map) {
        MapContext mapRes = MapContext.newOne();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        MapContext value = MapContext.newOne();
        if(StartDate!=null && endDate!=null){
            List<Date> list = DateUtilsExt.findDates(StartDate,endDate);
            if(list!=null&&list.size()>0){
                for(Date date: list){
                    map.put("created",date);
                    value.put(sdf.format(date),customOrderStatementService.selectByfilter(map));
                }
            }
        }
        mapRes.put("chart1",value);
        return ResultFactory.generateRequestResult(mapRes);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult countOrderByWeek(MapContext map) {
        MapContext mapRes = MapContext.newOne();
        CountByWeekDto dto = customOrderStatementService.countOrderByWeek(map);
        List<MapContext> listRes = new ArrayList<MapContext>();
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
        return ResultFactory.generateRequestResult(mapRes);
    }

    @Override
    public RequestResult countOrderByMonth(MapContext map) {
        MapContext mapRes = MapContext.newOne();
        CountByMonthDto dto = customOrderStatementService.countOrderByMonth(map);
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
        return ResultFactory.generateRequestResult(mapRes);
    }

    @Override
    public RequestResult countOrderByQuarter(MapContext map) {
        MapContext mapRes = MapContext.newOne();
        CountByQuarterDto dto = customOrderStatementService.countOrderByQuarter(map);
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
        return ResultFactory.generateRequestResult(mapRes);
    }

    @Override
    public RequestResult countOrderByYear(MapContext map) {
        MapContext mapRes = MapContext.newOne();
        CountByYearDto dto = customOrderStatementService.countOrderByYear(map);
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
        return ResultFactory.generateRequestResult(mapRes);
    }

    /**
     * 订单月份汇总统计列表
     * @param year
     * @param branchId
     * @return
     */
    @Override
    public RequestResult findOrderCountList(String year, String branchId) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM");
        String startTime=null;
        String endTime=null;
        ArrayList<Date> dates = new ArrayList<Date>();
        if(year==null||year.equals("")){
            startTime=DateUtilsExt.getNowYear().toString()+"-"+1;
            endTime=dateFormat.format(DateUtil.now());
        }else {
            startTime=year+"-"+1;
            endTime=year+"-"+12;
        }
        try {
            Date start=dateFormat.parse(startTime);
            Date end=dateFormat.parse(endTime);
            dates=DateUtilsExt.getMonthBetween(start,end);//两个日期内的月份集合
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ArrayList result=new ArrayList();
        for(int i=dates.size();i>0;i--){//日期循环查询
            Date date=dates.get(i-1);
            MapContext map=MapContext.newOne();
            map.put("branchId",branchId);
            map.put("date",date);
            MapContext orderCount=this.customOrderStatementService.selectOrderCount(map);//查询订单总数和订单金额
            MapContext produceCount=this.customOrderStatementService.selectProduceCount(map);//查询外协总数和外协总金额
            MapContext afterCount=this.customOrderStatementService.selectAfterCount(map);//查询售后总数
            orderCount.put("produceCount",produceCount.getTypedValue("produceCount",String.class));
            orderCount.put("produceAmount",produceCount.getTypedValue("produceAmount",String.class));
            MapContext mapContext=MapContext.newOne();
            mapContext.put("date",dateFormat.format(date));
            mapContext.put("orderCount",orderCount);
            mapContext.put("afterCount",afterCount);
            result.add(mapContext);
        }
        return ResultFactory.generateRequestResult(result);
    }

    @Override
    public RequestResult findMonthOrderCountInfo(String monthTime, String branchId) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        List list=new ArrayList();
        //查询橱柜、衣柜、五金、样块数量
        MapContext map=this.customOrderStatementService.findCountByBidAndType(branchId,monthTime);
        //某月所有日期
        List<Date> daysOfMonth = DateUtilsExt.getDaysOfMonth(monthTime);
        for(Date date:daysOfMonth){
            MapContext count=MapContext.newOne();
            MapContext mapContext=MapContext.newOne();
            mapContext.put("branchId",branchId);
            mapContext.put("date",date);
            //下单趋势和金额
            MapContext mapContext1=this.customOrderStatementService.selectOrderCountByDay(mapContext);
            count.put("orderCount",mapContext1.getTypedValue("orderCount",String.class));
            count.put("orderAmount",mapContext1.getTypedValue("orderAmount",String.class));
            //橱柜趋势和金额
            mapContext.put("orderProductType", OrderProductType.CUPBOARD.getValue());
            MapContext mapContext2=this.customOrderStatementService.selectOrderCountByDay(mapContext);
            count.put("chuguiCount",mapContext2.getTypedValue("orderCount",String.class));
            count.put("chuguiAmount",mapContext2.getTypedValue("orderAmount",String.class));
            //衣柜趋势和金额
            mapContext.put("orderProductType",OrderProductType.WARDROBE.getValue());
            MapContext mapContext3=this.customOrderStatementService.selectOrderCountByDay(mapContext);
            count.put("yiguiCount",mapContext3.getTypedValue("orderCount",String.class));
            count.put("yiguiAmount",mapContext3.getTypedValue("orderAmount",String.class));
            //产品数量
            Integer productNum=this.customOrderStatementService.findProductNumByBidAndTime(mapContext);
            count.put("productNum",productNum);
            count.put("date",dateFormat.format(date));
            list.add(count);
        }
        //经销商订单数据统计
        List<MapContext> list1=this.customOrderStatementService.findDealerByTime(branchId,monthTime);//查询下单的经销商列表
        for(MapContext mapContext:list1){
            String companyId=mapContext.getTypedValue("dealerId",String.class);
            List<CustomOrder> list2=this.customOrderStatementService.findOrderCOuntByCidAndTime(companyId,monthTime);
            BigDecimal amount=new BigDecimal("0");
            Integer productCount=0;
            for(CustomOrder order:list2){
                if(order.getFactoryFinalPrice()!=null&&!order.getFactoryFinalPrice().equals("")){
                    amount=amount.add(order.getFactoryFinalPrice());
                }
                String orderId=order.getId();
                List<OrderProductDto> listByOrderId = this.orderProductService.findListByOrderId(orderId);
                productCount=productCount+listByOrderId.size();
            }
            mapContext.put("orderNum",list2.size());
            mapContext.put("orderAmount",amount);
            mapContext.put("productNum",productCount);
        }
        MapContext result=MapContext.newOne();
        result.put("dealer",list1);
        result.put("orderList",list);
        result.put("yuanbing",map);
        return ResultFactory.generateRequestResult(result);
    }
}
