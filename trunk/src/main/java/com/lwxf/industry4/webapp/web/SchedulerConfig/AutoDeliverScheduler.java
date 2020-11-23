package com.lwxf.industry4.webapp.web.SchedulerConfig;

import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.bizservice.dispatch.DispatchBillService;
import com.lwxf.industry4.webapp.bizservice.system.BasecodeService;
import com.lwxf.industry4.webapp.common.enums.dispatch.DispatchBillStatus;
import com.lwxf.industry4.webapp.common.enums.order.OrderStatus;
import com.lwxf.industry4.webapp.common.utils.DateUtilsExt;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrder;
import com.lwxf.industry4.webapp.domain.entity.dispatch.DispatchBill;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import com.lwxf.mybatis.utils.DateUtil;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 功能：自动确认收货定时器
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/11/28 0028 9:23
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component
public class AutoDeliverScheduler {
	@Resource(name="customOrderService")
	private CustomOrderService customOrderService;
	@Resource(name = "basecodeService")
	private BasecodeService basecodeService;
	@Resource(name = "dispatchBillService")
	private DispatchBillService dispatchBillService;
	/**
	 * 自动确认收货
	 */
	@Scheduled(cron = "0 30 0 * * ?")//每天0:30触发一次
	private void autoDeliverScheduler(){
		String type="sureDeliverTime";
		List<Basecode> basecodes=this.basecodeService.findByType(type);
		MapContext mapContext=MapContext.newOne();
		MapContext map=MapContext.newOne();
		for(Basecode base:basecodes){
			String code=base.getCode();//branchId
			String value=base.getValue();//天数
			Integer days= Integer.valueOf(value);
			Date date=DateUtilsExt.getFrontDay(DateUtil.now(),days);//15天前的日期
			//订单自动确认收货
			Integer status= OrderStatus.SHIPPED.getValue();
			mapContext.put("branchId",code);
			mapContext.put("date",date);
			mapContext.put("status",status);
			List<CustomOrder> customOrders=this.customOrderService.findByBranchIdAndStatus(mapContext);
			for(CustomOrder order:customOrders){
				String id=order.getId();
				status=OrderStatus.END.getValue();
				Integer sureDeliverType=0;
				map.put("id",id);
				map.put("status",status);
				map.put("sureDeliverType",sureDeliverType);
				this.customOrderService.updateByMapContext(map);
			}
			//物流单自动确认收货
			MapContext ma=MapContext.newOne();
			ma.put("branchId",code);
			ma.put("date",date);
			ma.put("status", DispatchBillStatus.TRANSPORT.getValue());
			List<DispatchBill> dispatchBills=this.dispatchBillService.findListByTimeAndBranchId(ma);
			if(dispatchBills!=null&&dispatchBills.size()>0){
				for (DispatchBill bill:dispatchBills){
					MapContext billmap=MapContext.newOne();
					billmap.put("id",bill.getId());
					billmap.put("status",DispatchBillStatus.RECEIVED_GOODS.getValue());
					this.dispatchBillService.updateByMapContext(billmap);
				}
			}

		}


	}
}
