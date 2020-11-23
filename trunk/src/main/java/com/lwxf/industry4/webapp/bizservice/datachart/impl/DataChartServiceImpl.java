package com.lwxf.industry4.webapp.bizservice.datachart.impl;

import com.lwxf.industry4.webapp.bizservice.datachart.DataChartService;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.LwxfDateUtils;
import com.lwxf.industry4.webapp.common.utils.LwxfNumberUtils;
import com.lwxf.industry4.webapp.domain.dao.product.ProductDao;
import com.lwxf.industry4.webapp.domain.dao.system.BasecodeDao;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author lyh on 2019/12/7
 */
@Service("dataChartService")
public class DataChartServiceImpl implements DataChartService {


    @Resource(name = "productDao")
    private ProductDao productDao;

    @Resource(name = "basecodeDao")
    private BasecodeDao basecodeDao;

    @Override
    public RequestResult findlogisticsNameCount(String queryDateStart, String queryDateEnd) {
        List<Map<String, Object>> maps = productDao.findlogisticsNameCount(queryDateStart, queryDateEnd);
       if(maps.size() == 0){
           return ResultFactory.generateRequestResult(maps);
       }

        Map<String, Object> maxLogisticsCountMap = new HashMap<>();
        for (Map<String, Object> m:maps) {
            Integer logisticsCount = Integer.valueOf(m.get("logisticsCount")+"");
            Object logisticsCount1 = maxLogisticsCountMap.get("logisticsCount");
            Integer maxLogisticsCount =0;
            if(logisticsCount1 !=null){
                maxLogisticsCount = Integer.valueOf(logisticsCount1+"");
            }
            if(maxLogisticsCount<logisticsCount){
                maxLogisticsCountMap = m;
            }
        }
        Double maxLogisticsCount = Double.valueOf(maxLogisticsCountMap.get("logisticsCount")+"");
        for (Map<String, Object> s:maps) {
            Double logisticsCount = Double.valueOf(s.get("logisticsCount")+"");
            Double rate = 0d;
            if(logisticsCount !=0&&maxLogisticsCount !=0){
                rate = logisticsCount/maxLogisticsCount;
            }
            s.put("rate",LwxfNumberUtils.fomatNumberTwoDigits(rate));
        }
        return ResultFactory.generateRequestResult(maps);
    }

    /**
     * 近三十天发货趋势
     * @return
     */
    @Override
    public RequestResult deliverGoodsTrend(String queryDateStr) {
        Date queryDate = LwxfDateUtils.getDateBySdf(queryDateStr+"01", "yyyyMMdd");
        int daysOfMonth = LwxfDateUtils.getDaysOfMonth(queryDate);
        ArrayList<Map<String,Object>> result = new ArrayList<>();
        for (int i=0;i<=daysOfMonth-1;i++){
            String yyyyMMddd = LwxfDateUtils.getSdfDateDayBefore(queryDate, "yyyyMMdd", -i);
            String MMddd = LwxfDateUtils.getSdfDateDayBefore(queryDate, "MM-dd", -i);
            HashMap<String, Object> hashMap = new HashMap<>();
            Integer statusProductCount = productDao.findStatusProductCount("5", yyyyMMddd, yyyyMMddd);
            hashMap.put("nowDate",MMddd);
            hashMap.put("statusProductCount",statusProductCount);
            result.add(hashMap);
        }
        return ResultFactory.generateRequestResult(result);
    }

    @Override
    public RequestResult deliverGoodsNow() {
        //查询待发货
        Integer statusProductCount = productDao.findStatusProductCount("4", "", "");

        //查询今日发货
        Integer deliverGoods = productDao.findStatusProductCount("5", LwxfDateUtils.getSdfDate(new Date(),"yyyyMMdd"), LwxfDateUtils.getSdfDate(new Date(),"yyyyMMdd"));
        Map<String,Object> map = new HashMap<>();
        map.put("statusProductCount",statusProductCount);
        map.put("deliverGoods",deliverGoods);
        return ResultFactory.generateRequestResult(map);
    }

    @Override
    public RequestResult overdueOrderTrend(String queryDateMonth) {

        List<Map<String, Object>> maps = productDao.overdueOrderTrend(queryDateMonth);
        int daysOfMonth = LwxfDateUtils.getDaysOfMonth(new Date());
        List<Map<String, Object>> result =new ArrayList<>();
        for (int i=1;i<daysOfMonth+1;i++){
            String tmpDateStr = "";
            if(i<10){
                 tmpDateStr = queryDateMonth+"0"+i;
            }else {
                tmpDateStr = queryDateMonth+i;
            }
            Boolean flag=false;
            for (Map<String, Object> m:maps){
                String estimatedDeliveryDate = m.get("estimatedDeliveryDate")+"";
                if(estimatedDeliveryDate.equals(tmpDateStr)){
                    flag=true;
                    result.add(m);
                    break;
                }
            }
            if(!flag){
                HashMap<String, Object> objectObjectHashMap = new HashMap<>();
                objectObjectHashMap.put("estimatedDeliveryDate",tmpDateStr);
                objectObjectHashMap.put("totalUnits","0");
                result.add(objectObjectHashMap);
            }

        }

        return ResultFactory.generateRequestResult(result);
    }

    @Override
    public RequestResult findStatusProductOrder(String status) {
        //查询生产即将逾期
        if (status.equals("A")) {
            String yyyyMMdd = LwxfDateUtils.getSdfDate(new Date(), "yyyyMMdd");
            List<Map<String, Object>> statusProductOrderOverdue1 = productDao.findStatusProductOrderOverdue(yyyyMMdd);

            String yyyyMMdd3 = LwxfDateUtils.getSdfDateDayBefore(new Date(), "yyyyMMdd", 3);
            List<Map<String, Object>> statusProductOrderOverdue = productDao.findStatusProductOrderOverdue(yyyyMMdd3);

            for (Map<String, Object> m:statusProductOrderOverdue){
                for (Map<String, Object> m2:statusProductOrderOverdue1){
                    String code = m2.get("code")+"";
                    String code2 = m.get("code")+"";
                    if(code.equals(code2)){
                        Object totalUnits = m.get("totalUnits");
                        Object totalUnits2 = m2.get("totalUnits");
                        if(totalUnits!=null){
                            Double aDouble = Double.valueOf(totalUnits + "");
                            Double aDouble2 = Double.valueOf(totalUnits2 + "");
                            m.put("totalUnits",aDouble2-aDouble2);
                        }
                    }
                }


            }



            return ResultFactory.generateRequestResult(statusProductOrderOverdue);
        }
        if ("B".equals(status)) {
            //已逾期
            String yyyyMMdd = LwxfDateUtils.getSdfDate(new Date(), "yyyyMMdd");
            List<Map<String, Object>> statusProductOrderOverdue1 = productDao.findStatusProductOrderOverdue(yyyyMMdd);
            return ResultFactory.generateRequestResult(statusProductOrderOverdue1);
        }
        List<Map<String, Object>> statusProductOrder = productDao.findStatusProductOrder(status);
        return ResultFactory.generateRequestResult(statusProductOrder);
    }

    /**
     * 查询即将延期的订单
     */
    public RequestResult findImmediatelyPostponeOrder() {

        HashMap<String, Object> hashMap = new HashMap<>();
        //延期订单个数
        String yyyyMMdd = LwxfDateUtils.getSdfDate(new Date(), "yyyyMMdd");
        MapContext mapContext = new MapContext();
        mapContext.put("deliveryDate", yyyyMMdd);
        List<Map<String, Object>> orderDeliveryDate = productDao.findOrderDeliveryDate(mapContext);
        int postponeOrderSize = orderDeliveryDate.size();
        hashMap.put("postponeOrderSize", postponeOrderSize);
        //即将延期订单个数
        String yyyyMMdd1 = LwxfDateUtils.getSdfDateDayBefore(new Date(), "yyyyMMdd", -3);
        MapContext mapContext2 = new MapContext();
        mapContext2.put("deliveryDate", yyyyMMdd1);
        List<Map<String, Object>> orderDeliveryDate2 = productDao.findOrderDeliveryDate(mapContext2);
        int ImmediatelyPostponeOrdeSize = orderDeliveryDate2.size()-postponeOrderSize;
        hashMap.put("ImmediatelyPostponeOrdeSize", ImmediatelyPostponeOrdeSize);

        //生产中
        MapContext mapContext3 = new MapContext();
        mapContext3.put("deliveryDate", yyyyMMdd);
        mapContext3.put("status", 3);
        List<Map<String, Object>> orderDeliveryDate3 = productDao.findOrderDeliveryDate(mapContext3);
        int productSize = orderDeliveryDate3.size();
        hashMap.put("productSize", productSize);

        //待生产
        MapContext mapContext4 = new MapContext();
        mapContext4.put("deliveryDate", yyyyMMdd);
        mapContext4.put("status", 1);
        List<Map<String, Object>> orderDeliveryDate4 = productDao.findOrderDeliveryDate(mapContext4);
        int waitProductSize = orderDeliveryDate4.size();
        hashMap.put("waitProductSize", waitProductSize);

        return ResultFactory.generateRequestResult(hashMap);


    }

    /**
     * 当月数据
     *
     * @return
     */
    @Override
    public RequestResult findOrderChart(MapContext mapContent) {
        List<Map<String, Object>> orderChartTmp = productDao.findOrderChart(mapContent);
        int totalUnits = 0;
        Double totalMoney = new Double(0);
        List<Basecode> orderProductType = basecodeDao.findByType("orderProductType");
        List<Map<String, Object>> orderChart = new ArrayList<>();
        for (Basecode b : orderProductType) {
            String code = b.getCode();
            String value = b.getValue();
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("code", code);
            hashMap.put("value", value);
            for (Map<String, Object> o : orderChartTmp) {
                String codetmp = o.get("code") + "";
                if (codetmp.equals(code)) {
                    hashMap.put("dataMap", o);
                }

            }
            orderChart.add(hashMap);
        }
        for (Map<String, Object> o : orderChartTmp) {
            totalUnits = totalUnits + Integer.valueOf(o.get("totalUnits") + "");
            String totalMoneyStr = o.get("totalMoney") + "";
            Double totalMoneyTmp = new Double(totalMoneyStr);
            totalMoney = totalMoney+totalMoneyTmp;
        }
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("code", "a");
        objectObjectHashMap.put("orderProductName", "总数");
        objectObjectHashMap.put("totalUnits", totalUnits);
        objectObjectHashMap.put("totalMoney", LwxfNumberUtils.fomatNumberTwoDigits(totalMoney));
        orderChart.add(objectObjectHashMap);
        return ResultFactory.generateRequestResult(orderChart);
    }

    @Override
    public RequestResult findOrderChartByseries(MapContext mapContent) {
        List<Map<String, Object>> orderChartByseries = productDao.findOrderChartByseries(mapContent);
        double totalmaney = 0d;//总金额
        for (Map<String, Object> map : orderChartByseries) {
            String totalMoney = map.get("totalMoney") + "";
            map.put("totalMoney", totalMoney);
        }
        return ResultFactory.generateRequestResult(orderChartByseries);
    }

    @Override
    public RequestResult findOrderChartByDoor(MapContext mapContent) {
        List<Map<String, Object>> orderChartByDoor = productDao.findOrderChartByDoor(mapContent);
        for (Map<String, Object> map : orderChartByDoor) {
            Object totalMoney1 = map.get("totalMoney");
            String totalMoney = "0";
            if(totalMoney1 != null){
                totalMoney = map.get("totalMoney") + "";
            }

            map.put("totalMoney", LwxfNumberUtils.fomatNumberTwoDigits(Double.valueOf(totalMoney)));
        }
        return ResultFactory.generateRequestResult(orderChartByDoor);
    }

    @Override
    public RequestResult findOrderChartByColor(MapContext mapContent) {
        List<Map<String, Object>> orderChartByColor = productDao.findOrderChartByColor(mapContent);
        for (Map<String, Object> order : orderChartByColor) {
            Object totalMoney1 = order.get("totalMoney");
            String totalMoney = "0";
            if(totalMoney1 != null){
                totalMoney = order.get("totalMoney") + "";
            }
            String s = LwxfNumberUtils.fomatNumberTwoDigits(Double.valueOf(totalMoney));
            order.put("totalMoney", s);
        }
        return ResultFactory.generateRequestResult(orderChartByColor);
    }

    @Override
    public RequestResult findOrderChartByseriesFinance(MapContext mapContent) {
        List<Map<String, Object>> orderChartByseriesNow = productDao.findOrderChartByseries(mapContent);
        //添加近30日下单量 近30日下单金额
        String queryEndDate = LwxfDateUtils.getSdfDate(new Date(), "yyyyMMdd");
        String queryStartDate = LwxfDateUtils.getSdfDateDayBefore(new Date(), "yyyyMMdd", 30);
        mapContent.put("queryEndDate", queryEndDate);
        mapContent.put("queryStartDate", queryStartDate);
        List<Map<String, Object>> orderChartByseries30 = productDao.findOrderChartByseries(mapContent);
        List<Map<String, Object>> orderChart1 = productDao.findOrderChart(mapContent);
        List<Basecode> orderProductSeries = basecodeDao.findByType("orderProductSeries");
        List<Map<String, Object>> orderChart = new ArrayList<>();
        HashMap Moneyocbn = new HashMap();
        orderChart.add(Moneyocbn);
        for (Basecode b : orderProductSeries) {
            String code = b.getCode();
            String value = b.getValue();
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("code", code);
            hashMap.put("value", value);


            Map<String, Object> ocbn2 = new HashMap<>();
            ocbn2.put("series", code);
            ocbn2.put("value", value);
            ocbn2.put("code", code);
            ocbn2.put("totalUnits", "0");
            ocbn2.put("totalMoney", "0");
            hashMap.put("data", ocbn2);
            Double totalMoneyocbn = 0d;
            Double totalUnitsocbn = 0d;
            Double totalMoneyocbn30 = 0d;
            Double totalUnitsocbn30 = 0d;

            for (Map<String, Object> ocbn : orderChartByseriesNow) {
                Double totalUnits = Double.valueOf(ocbn.get("totalUnits") + "");
                Object totalMoney1 = ocbn.get("totalMoney");
                if(totalMoney1==null){
                    continue;
                }
                Double totalMoney = Double.valueOf(totalMoney1+ "");
                totalMoneyocbn = totalMoneyocbn + totalMoney;
                totalUnitsocbn = totalUnitsocbn + totalUnits;
                String value1 = ocbn.get("value") + "";
                if (value.equals(value1)) {
                    hashMap.put("data", ocbn);
                }
            }
            hashMap.put("data2", ocbn2);
            Moneyocbn.put("totalMoneyocbn", LwxfNumberUtils.fomatNumberTwoDigits(totalMoneyocbn));
            Moneyocbn.put("totalUnitsocbn", totalUnitsocbn);


            for (Map<String, Object> ocbn : orderChartByseries30) {
                Double totalMoney = Double.valueOf(ocbn.get("totalMoney") + "");
                totalMoneyocbn30 = totalMoneyocbn30 + totalMoney;
                Double totalUnits = Double.valueOf(ocbn.get("totalUnits") + "");
                totalUnitsocbn30 = totalUnitsocbn30 + totalUnits;
                String value1 = ocbn.get("value") + "";
                if (value.equals(value1)) {
                    hashMap.put("data2", ocbn);
                }
            }
            Moneyocbn.put("totalMoneyocbn30", LwxfNumberUtils.fomatNumberTwoDigits(totalMoneyocbn30));
            Moneyocbn.put("totalUnitsocbn30", totalUnitsocbn30);

            orderChart.add(hashMap);
        }


        return ResultFactory.generateRequestResult(orderChart);
    }


    @Override
    public RequestResult findOrderProportionFinance(MapContext mapContent) {
        List<Map<String, Object>> orderChartTmp = productDao.findOrderChart(mapContent);
        double totalUnits = 0;
        BigDecimal totalMoney = new BigDecimal(0);
        List<Basecode> orderProductType = basecodeDao.findByType("orderProductType");
        List<Map<String, Object>> orderChart = new ArrayList<>();
        for (Basecode b : orderProductType) {
            String code = b.getCode();
            String value = b.getValue();
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("code", code);
            hashMap.put("value", value);
            orderChart.add(hashMap);
            for (Map<String, Object> o : orderChartTmp) {
                if (code.equals(o.get("code") + "")) {
                    hashMap.putAll(o);
                }
            }

        }
        for (Map<String, Object> o : orderChartTmp) {
            totalUnits = totalUnits + Double.valueOf(o.get("totalUnits") + "");

        }
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("code", "a");
        objectObjectHashMap.put("orderProductName", "总数");
        objectObjectHashMap.put("totalUnits", totalUnits);
        orderChart.add(objectObjectHashMap);

        for (Map<String, Object> o : orderChart) {
            if (o.get("totalUnits") == null) {
                o.put("productRate", "0");
                o.put("totalUnits", "0");
                continue;
            }
            double totalUnits1 = Double.valueOf(o.get("totalUnits") + "");//订单数
            double productRate = 100 * totalUnits1 / totalUnits;
            o.put("productRate", Math.round(productRate));
            o.put("totalUnits", totalUnits1);
        }
        return ResultFactory.generateRequestResult(orderChart);


    }


}
