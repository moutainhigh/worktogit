package com.lwxf.industry4.webapp.facade.wxapi.factory.statements.impl;

import com.lwxf.industry4.webapp.bizservice.statements.app.factory.paymentStatement.PaymentStatementService;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.DateUtilsExt;
import com.lwxf.industry4.webapp.domain.dto.statement.*;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.wxapi.factory.statements.PaymentStatementFacade;
import com.lwxf.mybatis.utils.MapContext;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

@Component("wxpaymentStatementFacade")
public class PaymentStatementFacadeImpl  extends BaseFacadeImpl implements PaymentStatementFacade {

    @Resource(name = "paymentStatementService")
    private PaymentStatementService paymentStatementService;

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
                    value.put(sdf.format(date),paymentStatementService.selectByfilter(map));
                }
            }
        }
        mapRes.put("chart1",value);

        if(!map.get("countType").toString().equals("4"))
        {
            List<MapContext> chart2List = new ArrayList<>();
            List<PaymentCountByFunds> list = paymentStatementService.selectFundsStatementByFilter(map);
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
}
