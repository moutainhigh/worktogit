package com.lwxf.industry4.webapp.controller.admin.factory.printTable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.bizservice.customorder.OrderOfferService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.printTable.OfferPrintTableDto;
import com.lwxf.industry4.webapp.facade.admin.factory.order.OrderOfferFacade;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/7/18/018 16:42
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/api/f/offer", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
@Api(value = "OrderOfferPrintTableController", tags = {"pc端后台接口:订单报价打印信息管理"})
public class OrderOfferPrintTableController {


	@Resource(name = "orderOfferFacade")
	private OrderOfferFacade orderOfferFacade;


	@GetMapping("/print/{id}")
	@ApiOperation(value = "查询订单报价打印信息",notes = "查询订单报价打印信息",response = OfferPrintTableDto.class)
	private String findOfferPrintTableInfo(@PathVariable@ApiParam(value = "订单ID") String id){
		JsonMapper allToStringMapper = JsonMapper.createAllToStringMapper();
		return allToStringMapper.toJson(this.orderOfferFacade.findOfferPrintTableInfo(id));
	}


}
