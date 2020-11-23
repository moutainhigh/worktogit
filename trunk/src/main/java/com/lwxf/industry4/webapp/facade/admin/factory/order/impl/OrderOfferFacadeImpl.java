package com.lwxf.industry4.webapp.facade.admin.factory.order.impl;

import javax.annotation.Resource;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.bizservice.customorder.OfferItemService;
import com.lwxf.industry4.webapp.bizservice.customorder.OrderOfferService;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentFunds;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentStatus;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentTypeNew;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentWay;
import com.lwxf.industry4.webapp.common.enums.order.OrderStatus;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.uniquecode.UniquneCodeGenerator;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.customorder.OrderOfferDto;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentDto;
import com.lwxf.industry4.webapp.domain.dto.printTable.OfferPrintTableDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrder;
import com.lwxf.industry4.webapp.domain.entity.customorder.OfferItem;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderOffer;
import com.lwxf.industry4.webapp.domain.entity.financing.Payment;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.order.OrderOfferFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/7/8/008 16:48
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("orderOfferFacade")
public class OrderOfferFacadeImpl extends BaseFacadeImpl implements OrderOfferFacade {

	@Resource(name = "orderOfferService")
	private OrderOfferService orderOfferService;
	@Resource(name = "offerItemService")
	private OfferItemService offerItemService;
	@Resource(name = "customOrderService")
	private CustomOrderService customOrderService;
	@Resource(name = "paymentService")
	private PaymentService paymentService;

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addOffer(String orderId, OrderOfferDto orderOfferDto) {
		//判断订单是否存在
		CustomOrder customOrder = this.customOrderService.findById(orderId);
		if (customOrder == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		//判断此订单是否已存在报价 存在则不允许再次创建
		OrderOffer oldOrderOffer = this.orderOfferService.findByOrerId(orderId);
		if (oldOrderOffer == null) {
			//新增订单报价记录
			this.orderOfferService.add(orderOfferDto);
			//修改订单最终报价
			MapContext updateOrder = new MapContext();
			updateOrder.put(WebConstant.KEY_ENTITY_ID, orderId);
			updateOrder.put("factoryFinalPrice", orderOfferDto.getReceiptsPrice());
			this.customOrderService.updateByMapContext(updateOrder);
			for (OfferItem offerItem : orderOfferDto.getOfferItems()) {
				offerItem.setOfferId(orderOfferDto.getId());
				this.offerItemService.add(offerItem);
			}
			//修改订单支付记录信息
			//判断订单是未付款
			if (customOrder.getStatus() < OrderStatus.TO_PRODUCED.getValue()) {
				MapContext filter = new MapContext();
				filter.put("orderId", orderId);
				filter.put("funds", PaymentFunds.ORDER_FEE_CHARGE.getValue());
				PaymentDto paymentDto = this.paymentService.findByOrderIdAndFunds(filter);
				MapContext updatePayment = new MapContext();
				updatePayment.put(WebConstant.KEY_ENTITY_ID, paymentDto.getId());
				updatePayment.put("amount", orderOfferDto.getReceiptsPrice());
				this.paymentService.updateByMapContext(updatePayment);
			}


//			//支付记录信息生成
//			Payment payment = new Payment();
//			payment.setHolder("红田集团");
//			payment.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.PAYMENT_NO));
//			payment.setAmount(orderOfferDto.getReceiptsPrice());
//			payment.setCompanyId(customOrder.getCompanyId());
//			payment.setStatus(PaymentStatus.PENDING_PAYMENT.getValue());
//			payment.setCreated(DateUtil.getSystemDate());
//			payment.setCreator(WebUtils.getCurrUserId());
//			payment.setFunds(PaymentFunds.ORDER_FEE_CHARGE.getValue());
//			payment.setWay(PaymentWay.DEALER_ACCOUNT.getValue());
//			payment.setType(PaymentTypeNew.INCOME.getValue());
//			payment.setPayee("4j1u3r1efshq");
//			payment.setCustomOrderId(orderId);
//			payment.setBranchId(WebUtils.getCurrUser().getBranchId());
//			this.paymentService.add(payment);
		} else {
			//删除原先的报价条目
			this.offerItemService.deleteByOfferId(oldOrderOffer.getId());
			//删除订单报价
			this.orderOfferService.deleteById(oldOrderOffer.getId());
			//新增订单报价记录
			this.orderOfferService.add(orderOfferDto);
			//修改订单最终报价
			MapContext updateOrder = new MapContext();
			updateOrder.put(WebConstant.KEY_ENTITY_ID, orderId);
			updateOrder.put("factoryFinalPrice", orderOfferDto.getReceiptsPrice());
			this.customOrderService.updateByMapContext(updateOrder);
			for (OfferItem offerItem : orderOfferDto.getOfferItems()) {
				offerItem.setOfferId(orderOfferDto.getId());
				this.offerItemService.add(offerItem);
			}
			MapContext mapContext = new MapContext();
			mapContext.put("orderId", orderId);
			mapContext.put("funds", PaymentFunds.ORDER_FEE_CHARGE.getValue());
			PaymentDto paymentDto = this.paymentService.findByOrderIdAndFunds(mapContext);
			//如果订单未付款 则修改支付记录信息
			if (paymentDto.getStatus().equals(PaymentStatus.PENDING_PAYMENT.getValue())) {
				MapContext updatePayment = new MapContext();
				updatePayment.put(WebConstant.KEY_ENTITY_ID, paymentDto.getId());
				updatePayment.put("amount", orderOfferDto.getReceiptsPrice());
				this.paymentService.updateByMapContext(updatePayment);
			}
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateOffer(String orderId, MapContext update) {
		OrderOffer byOrerId = this.orderOfferService.findByOrerId(orderId);
		if (byOrerId == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		update.put(WebConstant.KEY_ENTITY_ID, byOrerId.getId());
		this.orderOfferService.updateByMapContext(update);
		MapContext mapContext = new MapContext();
		mapContext.put("orderId", orderId);
		mapContext.put("funds", PaymentFunds.ORDER_FEE_CHARGE.getValue());
		PaymentDto paymentDto = this.paymentService.findByOrderIdAndFunds(mapContext);
		BigDecimal receiptsPrice = this.orderOfferService.findByOrerId(orderId).getReceiptsPrice();
		//如果订单未付款 则修改支付记录信息
		if (paymentDto.getStatus().equals(PaymentStatus.PENDING_PAYMENT.getValue())) {
			MapContext updatePayment = new MapContext();
			updatePayment.put(WebConstant.KEY_ENTITY_ID, paymentDto.getId());
			updatePayment.put("amount", receiptsPrice);
			this.paymentService.updateByMapContext(updatePayment);
		}
		//修改订单最终报价
		MapContext updateOrder = new MapContext();
		updateOrder.put(WebConstant.KEY_ENTITY_ID, orderId);
		updateOrder.put("factoryFinalPrice", receiptsPrice);
		this.customOrderService.updateByMapContext(updateOrder);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addOfferItem(String orderId, OfferItem offerItem) {
		OrderOffer byOrerId = this.orderOfferService.findByOrerId(orderId);
		if (byOrerId == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		this.offerItemService.add(offerItem);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateOfferItem(String orderId, String itemId, MapContext update) {
		OrderOffer byOrerId = this.orderOfferService.findByOrerId(orderId);
		if (byOrerId == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		OfferItem byId = this.offerItemService.findById(itemId);
		if (byId == null || byId.getOfferId() != byOrerId.getId()) {
			return ResultFactory.generateResNotFoundResult();
		}
		update.put(WebConstant.KEY_ENTITY_ID, itemId);
		this.offerItemService.updateByMapContext(update);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteOfferItem(String orderId, String itemId) {
		OrderOffer byOrerId = this.orderOfferService.findByOrerId(orderId);
		if (byOrerId == null) {
			return ResultFactory.generateSuccessResult();
		}
		OfferItem byId = this.offerItemService.findById(itemId);
		if (byId == null || byId.getOfferId() != byOrerId.getId()) {
			return ResultFactory.generateSuccessResult();
		}
		this.offerItemService.deleteById(itemId);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult findOfferPrintTableInfo(String id) {
		OfferPrintTableDto offerPrintTableDto = this.customOrderService.findOfferPrintTableInfo(id);
		OrderOffer byOrerId = this.orderOfferService.findByOrerId(id);
		offerPrintTableDto.setOrderOffer(byOrerId);
		if(byOrerId!=null) {
			offerPrintTableDto.setOfferItemList(this.offerItemService.findByOfferId(byOrerId.getId()));
		}
		return ResultFactory.generateRequestResult(offerPrintTableDto);
	}
}
