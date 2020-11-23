package com.lwxf.industry4.webapp.facade.admin.factory.order.impl;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderLogService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderRemindService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderTimeService;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentService;
import com.lwxf.industry4.webapp.bizservice.system.RoleService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.customorder.CustomOrderRemindStatus;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentFunds;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentStatus;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentTypeNew;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentWay;
import com.lwxf.industry4.webapp.common.enums.order.OrderStage;
import com.lwxf.industry4.webapp.common.enums.order.OrderStatus;
import com.lwxf.industry4.webapp.common.enums.payment.PaymentResourceType;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.uniquecode.UniquneCodeGenerator;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderLogDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderRemindDto;
import com.lwxf.industry4.webapp.domain.entity.branch.Branch;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrder;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderLog;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderRemind;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderTime;
import com.lwxf.industry4.webapp.domain.entity.financing.Payment;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.dealer.OrderFacade;
import com.lwxf.industry4.webapp.facade.admin.factory.dealer.impl.OrderFacadeImpl;
import com.lwxf.industry4.webapp.facade.admin.factory.order.CustomOrderRemindFacade;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 功能：
 *
 * @author：DJL
 * @create：2020/1/6 11:16
 * @version：2020 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("customOrderRemindFacade")
public class CustomOrderRemindFacadeImpl implements CustomOrderRemindFacade {

    @Resource(name = "customOrderRemindService")
    private CustomOrderRemindService customOrderRemindService;
    @Resource(name = "customOrderService")
    private CustomOrderService customOrderService;
    @Resource(name = "orderFacade")
    private OrderFacade orderFacade;
    @Resource(name = "paymentService")
    private PaymentService paymentService;
    @Resource(name = "roleService")
    private RoleService roleService;
    @Resource(name = "customOrderLogService")
    private CustomOrderLogService customOrderLogService;
    @Resource(name = "customOrderTimeService")
    private CustomOrderTimeService customOrderTimeService;

    @Override
    public RequestResult selectByCondition(Integer pageNum, Integer pageSize, MapContext params, String uid, String bid, String cid) {
        PaginatedFilter filter = PaginatedFilter.newOne();
        Pagination pagination = Pagination.newOne();
        pagination.setPageSize(pageSize);
        pagination.setPageNum(pageNum);
        filter.setPagination(pagination);
        filter.setFilters(params);
        PaginatedList<CustomOrderRemindDto> paginatedList = this.customOrderRemindService.selectDtoByFilter(filter);
        if(paginatedList.getRows().size()>0){
            for (CustomOrderRemindDto remindDto : paginatedList.getRows()) {
                String orderId = remindDto.getCustomOrderId();
                CustomOrderTime customOrderTime=this.customOrderTimeService.findMaxByOrderId(orderId);//查询总工期最长的数据
                String pressForMoneyTime="";//催款工期
                Date commited=DateUtil.getSystemDate();
                    if (remindDto.getCommitTime() != null && !remindDto.getCommitTime().equals("")) {
                        commited=  remindDto.getCommitTime();
                    }
                    Integer orderRemind=1;
                    if(customOrderTime!=null){
                        orderRemind=    customOrderTime.getOrderRemind();
                    }
                pressForMoneyTime = OrderFacadeImpl.getTimeInfo(remindDto.getCreated(), commited, orderRemind);
                remindDto.setPressForMoneyTime(pressForMoneyTime);
            }
        }
        return ResultFactory.generateRequestResult(paginatedList);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult addCustomOrderRemind(CustomOrderRemind customOrderRemind) {
        this.customOrderRemindService.add(customOrderRemind);
        return ResultFactory.generateSuccessResult();
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult updateCustomOrderRemind(String orderId, MapContext mapContext) {
        // 判断订单是否存在
        CustomOrderDto customOrderDto = this.customOrderService.findByOrderId(orderId);
        if (null == customOrderDto) {
            return ResultFactory.generateResNotFoundResult();
        }
        // 判断催款单是否存在
        CustomOrderRemind customOrderRemind = this.customOrderRemindService.selectByOrderId(orderId);
        if (null == customOrderRemind) {
            return ResultFactory.generateResNotFoundResult();
        }

        mapContext.put(WebConstant.KEY_ENTITY_ID, customOrderRemind.getId());
        this.customOrderRemindService.updateByMapContext(mapContext);

        return ResultFactory.generateSuccessResult();
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult receiveRemindOrder(String id) {
        // 判断催款单是否存在
        CustomOrderRemind customOrderRemind = this.customOrderRemindService.findById(id);
        if (null == customOrderRemind) {
            return ResultFactory.generateResNotFoundResult();
        }

        // 判断催款人与当前登录用户是否一致
        String currUserId = WebUtils.getCurrUserId();
        String receiver = customOrderRemind.getReceiver();
        if (null == receiver || !receiver.equals(currUserId)) {
            return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020, AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
        }

        MapContext mapContext = MapContext.newOne();
        mapContext.put(WebConstant.KEY_ENTITY_ID, id);
        mapContext.put(WebConstant.KEY_ENTITY_STATUS, CustomOrderRemindStatus.REMINDING.getValue());
        mapContext.put("receiptTime", DateUtil.getSystemDate());
        this.customOrderRemindService.updateByMapContext(mapContext);
        CustomOrder byId = this.customOrderService.findById(customOrderRemind.getCustomOrderId());
        //记录操作日志
        CustomOrderLog log = new CustomOrderLog();
        log.setCreated(new Date());
        log.setCreator(WebUtils.getCurrUserId());
        log.setName("催款");
        log.setStage(OrderStage.PRESS_FOR_MONEY.getValue());
        log.setContent("订单：" + byId.getNo()+"由"+AppBeanInjector.userService.findByUserId(currUserId).getName()+"开始催款");
        log.setCustomOrderId(byId.getId());
        customOrderLogService.add(log);

        return ResultFactory.generateSuccessResult();
    }

    @Override
    @Transactional(value = "transactionManager")
    public void createRemindOrder(CustomOrder customOrder) {
        // 判断催款单是否存在
        CustomOrderRemind customOrderRemind = this.customOrderRemindService.selectByOrderId(customOrder.getId());
        if (null == customOrderRemind) {
            // 生成催款单
            CustomOrderRemind remind = new CustomOrderRemind();
            remind.setBranchId(customOrder.getBranchId());
            remind.setCustomOrderId(customOrder.getId());
            // 保存订单的下单人
            remind.setCreator(customOrder.getPlaceOrder());
            remind.setCreated(DateUtil.getSystemDate());
            remind.setStatus(CustomOrderRemindStatus.TO_REMIND.getValue());
            this.customOrderRemindService.add(remind);
        } else {
            // 催款单存在
            MapContext param = MapContext.newOne();
            param.put(WebConstant.KEY_ENTITY_ID, customOrderRemind.getId());
            param.put(WebConstant.KEY_ENTITY_STATUS, CustomOrderRemindStatus.TO_REMIND.getValue());
            param.put(WebConstant.KEY_ENTITY_CREATED, DateUtil.getSystemDate());
            param.put("creator", customOrder.getPlaceOrder());
            this.customOrderRemindService.updateByMapContext(param);
        }

        // 订单状态改成催款中
        MapContext param = MapContext.newOne();
        param.put(WebConstant.KEY_ENTITY_ID, customOrder.getId());
        param.put(WebConstant.KEY_ENTITY_STATUS, OrderStatus.REMINDING.getValue());
        this.customOrderService.updateByMapContext(param);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult completeRemindOrder(String id) {
        CustomOrderRemind customOrderRemind = this.customOrderRemindService.findById(id);
        if (null == customOrderRemind) {
            return ResultFactory.generateResNotFoundResult();
        }
        String customOrderId = customOrderRemind.getCustomOrderId();
        CustomOrderDto customOrderDto = this.customOrderService.findByOrderId((customOrderId));
        if (null == customOrderDto) {
            return ResultFactory.generateResNotFoundResult();
        }
        // 判断催款人与当前登录用户是否一致
        String currUserId = WebUtils.getCurrUserId();
        String receiver = customOrderRemind.getReceiver();
        if (null == receiver || !receiver.equals(currUserId)) {
            return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020, AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
        }
        // 修改催款单
        MapContext param = MapContext.newOne();
        param.put(WebConstant.KEY_ENTITY_ID, id);
        param.put(WebConstant.KEY_ENTITY_STATUS, CustomOrderRemindStatus.END.getValue());
        Date nowDate = DateUtil.getSystemDate();
        param.put("commitTime", nowDate);
        Date created = customOrderRemind.getCreated(); // 催款单创建时间
        long startTime = created.getTime();
        long nowTime = DateUtil.getSystemDate().getTime();
        long diff = (nowTime - startTime);
        long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
        long nh = 1000 * 60 * 60;//一小时的毫秒数
        long nm = 1000 * 60;//一分钟的毫秒数
        long day = diff / nd;//计算用时多少天
        long hour = diff % nd / nh;//计算用时多少小时
        long min = diff % nd % nh / nm;//计算用时多少分钟
        String timeConsuming = day + "天" + hour + "小时" + min + "分钟";
        param.put("timeConsuming", timeConsuming);
        this.customOrderRemindService.updateByMapContext(param);

        // 修改订单状态
        MapContext orderParam = MapContext.newOne();
        orderParam.put(WebConstant.KEY_ENTITY_ID, customOrderId);
        orderParam.put(WebConstant.KEY_ENTITY_STATUS, OrderStatus.TO_PAID.getValue());
        orderParam.put("commitTime", DateUtil.getSystemDate());
        this.customOrderService.updateByMapContext(orderParam);

        //新建订单货款信息记录
        CustomOrderDto byOrderId = this.customOrderService.findByOrderId(customOrderId);
        String userId = WebUtils.getCurrUserId();
        String userName = AppBeanInjector.userService.findByUserId(userId).getName();
        if(byOrderId.getPaymentWithholding()==1){
            //新建经销商订单货款信息记录，金额为0
            Payment payment = new Payment();
            payment.setHolder(userName);
            payment.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.PAYMENT_NO));
            payment.setAmount(new BigDecimal("0"));
            payment.setCompanyId(byOrderId.getCompanyId());
            payment.setStatus(PaymentStatus.PENDING_PAYMENT.getValue());
            payment.setCreated(DateUtil.getSystemDate());
            payment.setCreator(WebUtils.getCurrUserId());
            payment.setFunds(PaymentFunds.ORDER_FEE_CHARGE.getValue());
            payment.setWay(PaymentWay.DEALER_ACCOUNT.getValue());
            payment.setType(PaymentTypeNew.CHARGEBACK.getValue());
            payment.setFlag(0);
            payment.setCustomOrderId(byOrderId.getId());
            payment.setBranchId(WebUtils.getCurrUser().getBranchId());
            payment.setResourceType(PaymentResourceType.ORDER.getValue());
            this.paymentService.add(payment);
            //新建代扣款经销商货款信息记录
            payment = new Payment();
            payment.setHolder(userName);
            payment.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.PAYMENT_NO));
            payment.setAmount(byOrderId.getFactoryFinalPrice());
            payment.setCompanyId(byOrderId.getWithholdingCompanyId());
            payment.setStatus(PaymentStatus.PENDING_PAYMENT.getValue());
            payment.setCreated(DateUtil.getSystemDate());
            payment.setCreator(WebUtils.getCurrUserId());
            payment.setFunds(PaymentFunds.PAYMENT_WITHHOLDING_CHARGE.getValue());
            payment.setWay(PaymentWay.DEALER_ACCOUNT.getValue());
            payment.setType(PaymentTypeNew.CHARGEBACK.getValue());
            payment.setFlag(0);
            payment.setCustomOrderId(byOrderId.getId());
            payment.setBranchId(WebUtils.getCurrUser().getBranchId());
            payment.setResourceType(PaymentResourceType.ORDER.getValue());
            this.paymentService.add(payment);
        }else {
            Payment payment = new Payment();
            payment.setHolder(userName);
            payment.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.PAYMENT_NO));
            payment.setAmount(byOrderId.getFactoryFinalPrice());
            payment.setCompanyId(byOrderId.getCompanyId());
            payment.setStatus(PaymentStatus.PENDING_PAYMENT.getValue());
            payment.setCreated(DateUtil.getSystemDate());
            payment.setCreator(WebUtils.getCurrUserId());
            payment.setFunds(PaymentFunds.ORDER_FEE_CHARGE.getValue());
            payment.setWay(PaymentWay.DEALER_ACCOUNT.getValue());
            payment.setType(PaymentTypeNew.CHARGEBACK.getValue());
            payment.setFlag(0);
            //payment.setPayee("4j1u3r1efshq");
            payment.setCustomOrderId(byOrderId.getId());
            payment.setBranchId(WebUtils.getCurrUser().getBranchId());
            payment.setResourceType(PaymentResourceType.ORDER.getValue());
            this.paymentService.add(payment);
        }
        //记录操作日志
        CustomOrderLog log = new CustomOrderLog();
        log.setCreated(new Date());
        log.setCreator(WebUtils.getCurrUserId());
        log.setName("催款");
        log.setStage(OrderStage.PRESS_FOR_MONEY.getValue());
        log.setContent("订单：" + byOrderId.getNo()+"由"+AppBeanInjector.userService.findByUserId(currUserId).getName()+"完成催款");
        log.setCustomOrderId(byOrderId.getId());
        customOrderLogService.add(log);

        return ResultFactory.generateSuccessResult();
    }

    @Override
    public RequestResult selectByOrderId(String orderId) {
        PaginatedFilter filter = PaginatedFilter.newOne();
        Pagination pagination = Pagination.newOne();
        pagination.setPageSize(1);
        pagination.setPageNum(1);
        filter.setPagination(pagination);
        MapContext params = MapContext.newOne();
        params.put("customOrderId", orderId);
        filter.setFilters(params);
        PaginatedList<CustomOrderRemindDto> paginatedList = this.customOrderRemindService.selectDtoByFilter(filter);
        CustomOrderRemindDto dto = null;
        if (paginatedList != null && paginatedList.getRows() != null && paginatedList.getRows().size() > 0) {
            dto = paginatedList.getRows().get(0);
        }
        return ResultFactory.generateRequestResult(dto);
    }

    @Override
    public CustomOrderRemindDto selectDtoByOrderId(String orderId) {
        return this.customOrderRemindService.selectDtoByOrderId(orderId);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult updateBack(String orderId) {
        CustomOrder byId = this.customOrderService.findById(orderId);
        if(byId==null){
            return ResultFactory.generateResNotFoundResult();
        }
        CustomOrderRemind customOrderRemind = this.customOrderRemindService.selectByOrderId(orderId);
        if(customOrderRemind==null){
            return ResultFactory.generateResNotFoundResult();
        }
        this.customOrderRemindService.deleteById(customOrderRemind.getId());
        MapContext mapContext=MapContext.newOne();
        mapContext.put("id",orderId);
        mapContext.put("status",OrderStatus.TO_QUOTED.getValue());
        this.customOrderService.updateByMapContext(mapContext);
        //删除催款日志
        List<CustomOrderLogDto> byOrderIdAndState = this.customOrderLogService.findByOrderIdAndState(orderId, OrderStage.PRESS_FOR_MONEY.getValue());
        if(byOrderIdAndState.size()>0) {
            for (CustomOrderLogDto dto : byOrderIdAndState) {
                this.customOrderLogService.deleteById(dto.getId());
            }
        }
        return ResultFactory.generateSuccessResult();
    }

    @Override
    public RequestResult saveRemindOrderReceive(String orderId, MapContext mapContext) {
        // 判断订单是否存在
        CustomOrderDto customOrderDto = this.customOrderService.findByOrderId(orderId);
        if (null == customOrderDto) {
            return ResultFactory.generateResNotFoundResult();
        }

        // 当订单状态为催款中，催款单已经生成，只需要修改催款人
        if (customOrderDto.getStatus().equals(OrderStatus.REMINDING.getValue())) {
            MapContext map = MapContext.newOne();
            // 查询催款单
            CustomOrderRemind customOrderRemind = customOrderRemindService.selectByOrderId(customOrderDto.getId());
            if (null == customOrderRemind) {
                return ResultFactory.generateResNotFoundResult();
            }
            map.put(WebConstant.KEY_ENTITY_ID, customOrderRemind.getId());
            map.put("receiver", mapContext.getTypedValue("receiver", String.class));
            this.customOrderRemindService.updateByMapContext(map);

            return ResultFactory.generateSuccessResult();
        }


        // 当订单状态为待报价时，催款单只能为草稿状态
        if (customOrderDto.getStatus().equals(OrderStatus.TO_QUOTED.getValue())) {
            // 判断催款单是否存在
            CustomOrderRemind customOrderRemind = this.customOrderRemindService.selectByOrderId(orderId);
            if (null == customOrderRemind) {
                // 生成催款单
                CustomOrderRemind remind = new CustomOrderRemind();
                remind.setBranchId(customOrderDto.getBranchId());
                remind.setCustomOrderId(customOrderDto.getId());
                remind.setReceiver(mapContext.getTypedValue("receiver", String.class));
                // 此时订单未提交到财务，还是在待报价状态，保存为草稿状态，草稿状态的催款单不能在列表中显示
                remind.setStatus(CustomOrderRemindStatus.DRAFT.getValue());
                this.customOrderRemindService.add(remind);
            } else {
                // 修改催款单, 修改催款人
                MapContext param = MapContext.newOne();
                param.put(WebConstant.KEY_ENTITY_ID, customOrderRemind.getId());
                param.put("receiver", mapContext.getTypedValue("receiver", String.class));
                this.customOrderRemindService.updateByMapContext(param);
            }
        }

        return ResultFactory.generateSuccessResult();


    }

}
