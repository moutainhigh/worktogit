package com.lwxf.industry4.webapp.controller.admin.factory.customOrder;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.order.OrderAccountLogType;
import com.lwxf.industry4.webapp.common.enums.order.OrderStatus;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderAccountLog;
import com.lwxf.industry4.webapp.facade.admin.factory.order.OrderAccountLogFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：订单报价审核
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/3/5/005 15:20
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/api/f/accounts",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class OrderAccountLogController {
	@Resource(name = "orderAccountLogFacade")
	private OrderAccountLogFacade orderAccountLogFacade;

	/**
	 * 查询需要审货款的订单列表
	 * @param no
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping("price")
	private RequestResult findOrderPriceList(@RequestParam(required = false) String no,@RequestParam(required = false,defaultValue = "1")Integer pageNum,@RequestParam(required = false,defaultValue = "10")Integer pageSize,
											 @RequestParam(required = false) String customerTel){
//		MapContext mapContext = this.createMapContext(no,Arrays.asList(OrderStatus.FACTORY_CONFIRMED_FPROCE.getValue(),OrderStatus.DEALER_CONFIRMED_FPRICE.getValue()),customerTel);
		return this.orderAccountLogFacade.findOrderList(null,pageNum,pageSize);
	}
	/**
	 * 查询需要审设计费的订单列表
	 * @param no
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/designfee")
	private RequestResult findOrderDesignFeeList(@RequestParam(required = false) String no,@RequestParam(required = false,defaultValue = "1")Integer pageNum,@RequestParam(required = false,defaultValue = "10")Integer pageSize,
												 @RequestParam(required = false) String customerTel){
//		MapContext mapContext = this.createMapContext(no,Arrays.asList(OrderStatus.TO_ADD_DESIGNFEE.getValue(),OrderStatus.TO_BE_CONFIRMED_DESIGNFEE.getValue()),customerTel);
		return this.orderAccountLogFacade.findOrderList(null,pageNum,pageSize);
	}

	/**
	 * 修改货款报价信息
	 * @param id 订单主键ID
	 * @param orderAccountLog  修改报价的日志记录表 存放备注以及现价
	 * @return
	 */
	@PutMapping("/price/{id}")
	private RequestResult updateOrderPriceAccount(@PathVariable String id,@RequestBody OrderAccountLog orderAccountLog){
		return this.orderAccountLogFacade.updateOrderAccount(id,OrderAccountLogType.CARGO.getValue(),orderAccountLog);
	}
	/**
	 * 修改设计费报价信息
	 * @param id 订单主键ID
	 * @param orderAccountLog  修改报价的日志记录表 存放备注以及现价
	 * @return
	 */
	@PutMapping("/designfee/{id}")
	private RequestResult updateOrderDesignFeeAccount(@PathVariable String id,@RequestBody OrderAccountLog orderAccountLog){
		return this.orderAccountLogFacade.updateOrderAccount(id,OrderAccountLogType.DESIGN.getValue(),orderAccountLog);
	}

	private MapContext createMapContext(String no,List<Integer> status,String customerTel) {
		MapContext mapContext = new MapContext();
		if(no!=null&&!no.trim().equals("")){
			mapContext.put(WebConstant.STRING_NO,no);
		}
		if(status!=null&&status.size()!=0){
			mapContext.put(WebConstant.KEY_ENTITY_STATUS, status);
		}
		if(customerTel!=null){
			mapContext.put("customerTel",customerTel);
		}
		return mapContext;
	}
}
