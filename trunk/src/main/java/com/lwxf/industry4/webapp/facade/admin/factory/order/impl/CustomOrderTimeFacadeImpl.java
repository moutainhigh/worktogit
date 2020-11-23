package com.lwxf.industry4.webapp.facade.admin.factory.order.impl;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderRemindService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderTimeService;
import com.lwxf.industry4.webapp.bizservice.system.BasecodeService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.order.OrderStatus;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderRemindDto;
import com.lwxf.industry4.webapp.domain.entity.branch.Branch;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrder;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderRemind;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderTime;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;

import com.lwxf.industry4.webapp.facade.admin.factory.order.CustomOrderTimeFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/9/3 0003 16:41
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component(value = "customOrderTimeFacade")
public class CustomOrderTimeFacadeImpl extends BaseFacadeImpl implements CustomOrderTimeFacade {
    @Resource(name = "customOrderTimeService")
    private CustomOrderTimeService customOrderTimeService;
    @Resource(name = "basecodeService")
    private BasecodeService basecodeService;
    @Resource(name = "customOrderService")
    private CustomOrderService customOrderService;
    @Resource(name = "customOrderRemindService")
    private CustomOrderRemindService customOrderRemindService;
    @Override
    public RequestResult selectCustoOrderTime(MapContext mapContext) {
        List<MapContext> list=this.customOrderTimeService.selectList(mapContext);
        return ResultFactory.generateRequestResult(list);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult addCustoOrderTime(MapContext mapContext) {
        if(mapContext.getTypedValue("productType",Integer.class)==99){
            Integer series=mapContext.getTypedValue("productSeries",Integer.class);
            MapContext mapContext1=MapContext.newOne();
            mapContext1.put("productSeries",series);
            mapContext1.put("branchId",WebUtils.getCurrBranchId());
            List<CustomOrderTime> bySeries = this.customOrderTimeService.findBySeries(mapContext1);
            for(CustomOrderTime orderTime:bySeries){
                this.customOrderTimeService.deleteById(orderTime.getId());
            }
            //查询所有产品
            List<Basecode> orderProductType = this.basecodeService.findByTypeAndDelFlag("orderProductType", 0);
            //查询所有部件
            List<Basecode> produceOrderType = this.basecodeService.findByTypeAndDelFlag("produceOrderType", 0);
            if(orderProductType.size()>0) {
                if(produceOrderType.size()>0) {
                    for (Basecode basecode0 : orderProductType) {
                        Integer productType =Integer.valueOf( basecode0.getCode());
                        for(Basecode basecode1:produceOrderType){
                            Integer produceType = Integer.valueOf(basecode1.getCode());
                            CustomOrderTime customOrderTime=new CustomOrderTime();
                            customOrderTime.setBranchId(WebUtils.getCurrBranchId());
                            customOrderTime.setProductType(productType);
                            customOrderTime.setProductSeries(mapContext.getTypedValue("productSeries",Integer.class));
                            customOrderTime.setProduceType(produceType);
                            if(mapContext.containsKey("orderTime")){
                                customOrderTime.setOrderTime(mapContext.getTypedValue("orderTime",Integer.class));
                            }
                            if(mapContext.containsKey("receiveTime")){
                                customOrderTime.setReceiveTime(mapContext.getTypedValue("receiveTime",Integer.class));
                            }
                            if(mapContext.containsKey("orderRemind")){
                                customOrderTime.setOrderRemind(mapContext.getTypedValue("orderRemind",Integer.class));
                            }
                            if(mapContext.containsKey("auditTime")){
                                customOrderTime.setAuditTime(mapContext.getTypedValue("auditTime",Integer.class));
                            }
                            if(mapContext.containsKey("inputTime")){
                                customOrderTime.setInputTime(mapContext.getTypedValue("inputTime",Integer.class));
                            }
                            if(mapContext.containsKey("produceTime")){
                                customOrderTime.setProduceTime(mapContext.getTypedValue("produceTime",Integer.class));
                            }else {
                                customOrderTime.setProduceTime(99);
                            }
                            if(mapContext.containsKey("deliverTime")){
                                customOrderTime.setDeliverTime(mapContext.getTypedValue("deliverTime",Integer.class));
                            }
                            if(mapContext.containsKey("allCost")){
                                customOrderTime.setAllCost(mapContext.getTypedValue("allCost",Integer.class));
                            }else {
                                customOrderTime.setAllCost(99);
                            }
                            if(mapContext.containsKey("orderType")){
                                customOrderTime.setOrderType(mapContext.getTypedValue("orderType",Integer.class));
                            }
                            this.customOrderTimeService.add(customOrderTime);
                        }
                    }
                }
            }
        }else {
            MapContext mapContext1=MapContext.newOne();
            mapContext1.put("productType",mapContext.getTypedValue("productType",Integer.class));
            mapContext1.put("produceType",mapContext.getTypedValue("produceType",Integer.class));
            mapContext1.put("productSeries",mapContext.getTypedValue("productSeries",Integer.class));
            mapContext1.put("branchId",WebUtils.getCurrBranchId());
            CustomOrderTime byTypeAndSeries = this.customOrderTimeService.findByTypeAndSeries(mapContext1);
            if(byTypeAndSeries!=null){//如果存在，则修改原记录
                mapContext.put("id",byTypeAndSeries.getId());
                this.customOrderTimeService.updateByMapContext(mapContext);
            }else {
                CustomOrderTime customOrderTime=new CustomOrderTime();
                customOrderTime.setBranchId(WebUtils.getCurrBranchId());
                customOrderTime.setProductType(mapContext.getTypedValue("productType",Integer.class));
                customOrderTime.setProductSeries(mapContext.getTypedValue("productSeries",Integer.class));
                customOrderTime.setProduceType(mapContext.getTypedValue("produceType",Integer.class));
                if(mapContext.containsKey("orderTime")){
                    customOrderTime.setOrderTime(mapContext.getTypedValue("orderTime",Integer.class));
                }
                if(mapContext.containsKey("produceTime")){
                    customOrderTime.setProduceTime(mapContext.getTypedValue("produceTime",Integer.class));
                }else {
                    customOrderTime.setProduceTime(99);
                }
                if(mapContext.containsKey("deliverTime")){
                    customOrderTime.setDeliverTime(mapContext.getTypedValue("deliverTime",Integer.class));
                }
                if(mapContext.containsKey("allCost")){
                    customOrderTime.setAllCost(mapContext.getTypedValue("allCost",Integer.class));
                }else {
                    customOrderTime.setAllCost(99);
                }
                if(mapContext.containsKey("receiveTime")){
                    customOrderTime.setReceiveTime(mapContext.getTypedValue("receiveTime",Integer.class));
                }
                if(mapContext.containsKey("orderRemind")){
                    customOrderTime.setOrderRemind(mapContext.getTypedValue("orderRemind",Integer.class));
                }
                if(mapContext.containsKey("auditTime")){
                    customOrderTime.setAuditTime(mapContext.getTypedValue("auditTime",Integer.class));
                }
                if(mapContext.containsKey("inputTime")){
                    customOrderTime.setInputTime(mapContext.getTypedValue("inputTime",Integer.class));
                }
                if(mapContext.containsKey("orderType")){
                    customOrderTime.setOrderType(mapContext.getTypedValue("orderType",Integer.class));
                }
                this.customOrderTimeService.add(customOrderTime);
            }
        }

        return ResultFactory.generateSuccessResult();
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult editCustoOrderTime(MapContext mapContext) {
        this.customOrderTimeService.updateByMapContext(mapContext);
        return ResultFactory.generateSuccessResult();
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult deleteCustoOrderTime(String id) {
        this.customOrderTimeService.deleteById(id);
        return ResultFactory.generateSuccessResult();
    }

    @Override
    public RequestResult findOrderTimelist(MapContext mapContext, Integer pageNum, Integer pageSize) {
        //查询是否开启催款功能
        Branch branch = AppBeanInjector.branchService.findById(WebUtils.getCurrBranchId());
        Integer enableRemind = branch.getEnableRemind();
        PaginatedFilter filter = PaginatedFilter.newOne();
        Pagination pagination = Pagination.newOne();
        pagination.setPageSize(pageSize);
        pagination.setPageNum(pageNum);
        filter.setPagination(pagination);
        filter.setFilters(mapContext);
        Map<String, String> created = new HashMap<String, String>();
        created.put(WebConstant.KEY_ENTITY_CREATED, "desc");
        List sort = new ArrayList();
        sort.add(created);
        filter.setSorts(sort);
        PaginatedList<CustomOrderDto> paginatedList = this.customOrderService.findListByPaginateFilter(filter);
        if(paginatedList!=null&&paginatedList.getRows().size()>0) {
            for (CustomOrderDto order : paginatedList.getRows()) {
                String orderId=order.getId();
                CustomOrderTime customOrderTime=this.customOrderTimeService.findMaxByOrderId(orderId);//查询总工期最长的数据
                //订单编号
                String orderNo=order.getNo();
                //判断是工厂下单还是经销商传单
                Integer orderSource = order.getOrderSource();
                Date receiveTimeStart=new Date();//接单起始时间
                if(orderSource==null||orderSource==0){
                    receiveTimeStart=order.getCreated();
                }else {
                    receiveTimeStart=order.getLeafletTime();
                }
                //接单工期
                Date receiptTime = order.getReceiptTime();
                Integer receiveTimeDate=1;
                if(customOrderTime!=null) {
                    receiveTimeDate = customOrderTime.getReceiveTime();
                }
                String receiveInfo="";
                if(receiptTime==null||receiptTime.equals("")){
                    receiptTime=DateUtil.getSystemDate();
                }
                if(receiveTimeStart!=null&&!receiveTimeStart.equals("")) {
                    receiveInfo = CustomOrderTimeFacadeImpl.getTimeInfo(receiveTimeStart, receiptTime, receiveTimeDate);
                }
                //接单员
                String receiver=order.getReceiverName();

                //查询是否有催款环节
                CustomOrderRemind customOrderRemind=null;
                if(enableRemind==1){
                    customOrderRemind = this.customOrderRemindService.selectByOrderId(orderId);
                }
                //下单工期
                String placeTimeInfo="";
                receiptTime = order.getReceiptTime();
                Date commitTime = order.getCommitTime();
                Integer orderTime=1;
                if(customOrderTime!=null) {
                     orderTime = customOrderTime.getOrderTime();
                }
                if(customOrderRemind!=null){
                    commitTime=customOrderRemind.getCreated();
                }
                if(commitTime==null||commitTime.equals("")){
                    commitTime=DateUtil.getSystemDate();
                }
                if(receiptTime!=null&&!receiptTime.equals("")) {
                    placeTimeInfo = CustomOrderTimeFacadeImpl.getTimeInfo(receiptTime, commitTime,orderTime );
                }
                //下单员
                String placeOrder=order.getPlaceOrderName();

                String pressForMoneyTime="";//催款工期
                String pressName="";//催款人
                 if(customOrderRemind!=null) {
                     Integer orderRemind=1;
                     if(customOrderTime!=null) {
                        orderRemind = customOrderTime.getOrderRemind();
                     }
                        if (customOrderRemind.getCommitTime() != null && !customOrderRemind.getCommitTime().equals("")) {
                            pressForMoneyTime = CustomOrderTimeFacadeImpl.getTimeInfo(customOrderRemind.getCreated(), customOrderRemind.getCommitTime(),orderRemind );
                        }
                     String rece= customOrderRemind.getReceiver();
                        if(rece!=null&&!rece.equals("")) {
                            pressName = AppBeanInjector.userService.findByUserId(rece).getName();
                        }

                }
               //审核工期
                String auditInfo="";
                 String auditName="";
                Date commitTimeT = order.getCommitTime();
                Date payTime=order.getPayTime();
                Integer auditTime=1;
                if(customOrderTime!=null) {
                   auditTime = customOrderTime.getAuditTime();
                }
                if(customOrderRemind!=null){
                    commitTimeT=customOrderRemind.getCommitTime();
                }
                if(order.getPayTime()==null||order.getPayTime().equals("")){
                    payTime=DateUtil.getSystemDate();
                }
                if(commitTimeT!=null&&!commitTimeT.equals("")){
                    auditInfo=CustomOrderTimeFacadeImpl.getTimeInfo(commitTimeT,payTime,auditTime);
                }
                 auditName=order.getAuditorName();

                 //生产发货工期
                String produceAndDeliveryInfo="";
                Date documentaryTime = payTime;//财务审核时间
                Date deliveryDate = order.getDeliveryDate();//发货时间
                Integer produceTime=15;
                if(customOrderTime!=null){
                    produceTime= customOrderTime.getProduceTime();
                }
                if(deliveryDate==null||deliveryDate.equals("")){
                    deliveryDate=DateUtil.getSystemDate();
                }
                if(documentaryTime!=null&&!documentaryTime.equals("")){
                    produceAndDeliveryInfo=CustomOrderTimeFacadeImpl.getTimeInfo(documentaryTime,deliveryDate,produceTime);
                }
                order.setReceiveInfo(receiveInfo);
                order.setPlaceTimeInfo(placeTimeInfo);
                order.setPressForMoneyTime(pressForMoneyTime);
                order.setPressName(pressName);
                order.setAuditInfo(auditInfo);
                order.setProduceAndDeliveryInfo(produceAndDeliveryInfo);

            }
        }
        return ResultFactory.generateRequestResult(paginatedList);
    }

    private static String getTimeInfo(Date startTime, Date endTime, Integer date) {
        long startTimeValue = startTime.getTime();
        long endTimeValue=endTime.getTime();
        long diff = (endTimeValue - startTimeValue);
        long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
        long nh = 1000 * 60 * 60;//一小时的毫秒数
        long nm = 1000 * 60;//一分钟的毫秒数
        long day = diff / nd;//计算用时多少天
        long hour = diff % nd / nh;//计算用时多少小时
        long min = diff % nd % nh / nm;//计算用时多少分钟
        String timeConsuming="";
        if((int)day<date) {
            timeConsuming = "累计用时"+day + "天" + hour + "小时" + min + "分钟";
        }else {
            timeConsuming = "累计用时"+day + "天" + hour + "小时" + min + "分钟"+"("+"延期"+((int)day-date) + "天" + hour + "小时" + min + "分钟"+")";
        }
        return timeConsuming;
    }


}
