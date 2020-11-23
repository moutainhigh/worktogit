package com.lwxf.industry4.webapp.web.SchedulerConfig;

import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentService;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentFunds;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentStatus;
import com.lwxf.industry4.webapp.common.enums.order.OrderStatus;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.financing.Payment;
import com.lwxf.mybatis.utils.MapContext;
import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 功能：废除超过30天未审核的订单
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2020-05-23 17:24
 * @version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component
public class AbolishPayment {

    @Value("${lwxf.company.id}")
    private String companyId;

    @Resource(name="customOrderService")
    private CustomOrderService customOrderService;
    @Resource(name = "paymentService")
    private PaymentService paymentService;



    @Scheduled(cron = "0 0 0 * * ?")//每天24点触发一次
    private void abolishPaymentOrder(){
        //查询超过30未审核的订单
        String branchId= companyId;
        Integer funds= PaymentFunds.ORDER_FEE_CHARGE.getValue();
        MapContext mapContext=MapContext.newOne();
        mapContext.put("branchId",branchId);
        mapContext.put("funds",funds);
        List<Payment> paymentList=this.paymentService.findByBranchIdAndFunds(mapContext);
        if(paymentList!=null&&paymentList.size()>0){
            for(Payment payment:paymentList) {
                System.out.println("AAAAAAA+++++++");
                MapContext paymentMap=MapContext.newOne();
                paymentMap.put("id",payment.getId());
                paymentMap.put("status", PaymentStatus.ABOLISH.getValue());
                this.paymentService.updateByMapContext(paymentMap);
                MapContext orderMap=MapContext.newOne();
                String orderId =payment.getCustomOrderId();
                orderMap.put("id",orderId);
                orderMap.put("status", OrderStatus.DELETE.getValue());
                this.customOrderService.updateByMapContext(orderMap);
            }
        }
    }


}
