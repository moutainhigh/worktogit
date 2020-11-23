package com.lwxf.industry4.webapp.controller.admin.factory.customOrder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.customorder.OrderOfferDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.OfferItem;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderOffer;
import com.lwxf.industry4.webapp.facade.admin.factory.order.OrderOfferFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/7/8/008 16:44
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value = "orderOfferController",tags = {"订单报价管理接口"})
@RestController
@RequestMapping(value = "/api/f/offer")
public class OrderOfferController {

	@Resource(name = "orderOfferFacade")
	private OrderOfferFacade orderOfferFacade;

	@ApiOperation(value = "(真实的)修改报价信息",notes="(真实的)修改报价信息")
	@PostMapping("/{orderId}/offer")
	private RequestResult addOffer(@PathVariable@ApiParam(value = "订单主键ID") String orderId, @RequestBody@ApiParam(value = "报价信息")OrderOfferDto orderOfferDto){
		orderOfferDto.setOrderId(orderId);
		RequestResult result = orderOfferDto.validateFields();
		if(result!=null){
			return result;
		}
		return this.orderOfferFacade.addOffer(orderId,orderOfferDto);
	}

	@ApiOperation(value = "(虚假的)修改报价信息",notes="(虚假的)修改报价信息")
	@PutMapping("/{orderId}/offer")
	private RequestResult updateOffer(@PathVariable@ApiParam(value = "订单主键ID") String orderId,@RequestBody@ApiParam(value = "报价信息")MapContext update){
		RequestResult result = OrderOffer.validateFields(update);
		if(result==null){
			return result;
		}
		return this.orderOfferFacade.updateOffer(orderId,update);
	}
	@ApiOperation(value = "新增报价条目",notes="新增报价条目")
	@PostMapping("/{orderId}/offeritem")
	private RequestResult addOfferItem(@PathVariable@ApiParam(value = "订单主键ID") String orderId,@RequestBody@ApiParam(value = "报价信息")OfferItem offerItem){
		RequestResult result = offerItem.validateFields();
		if(result==null){
			return result;
		}
		return this.orderOfferFacade.addOfferItem(orderId,offerItem);
	}
	@ApiOperation(value = "修改报价条目",notes="修改报价条目")
	@PutMapping("/{orderId}/offeritem/{itemId}")
	private RequestResult updateOfferItem(@PathVariable@ApiParam(value = "订单主键ID") String orderId,@PathVariable@ApiParam(value = "条目主键ID") String itemId,@RequestBody@ApiParam(value = "条目信息")MapContext update){
		RequestResult result = OfferItem.validateFields(update);
		if(result==null){
			return result;
		}
		return this.orderOfferFacade.updateOfferItem(orderId,itemId,update);
	}
	@ApiOperation(value = "删除报价条目",notes="删除报价条目")
	@DeleteMapping("/{orderId}/offeritem/{itemId}")
	private RequestResult deleteOfferItem(@PathVariable@ApiParam(value = "订单主键ID") String orderId,@PathVariable@ApiParam(value = "条目主键ID") String itemId){
		return this.orderOfferFacade.deleteOfferItem(orderId,itemId);
	}
}
