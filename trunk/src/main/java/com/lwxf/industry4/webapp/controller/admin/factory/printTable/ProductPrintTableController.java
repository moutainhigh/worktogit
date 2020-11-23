package com.lwxf.industry4.webapp.controller.admin.factory.printTable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.printTable.OrderPrintTableDto;
import com.lwxf.industry4.webapp.facade.admin.factory.dealer.OrderFacade;
import com.lwxf.industry4.webapp.facade.admin.factory.product.ProductFacade;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/7/11 0011 15:23
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController("ProductPrintTableController")
@RequestMapping(value = "/api/f/branchs", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
@Api(value = "ProductPrintTableController", tags = {"pc端后台接口:产品（定制/特供）打印信息管理"})
public class ProductPrintTableController {

	@Resource(name = "orderFacade")
	private OrderFacade orderFacade;
	@GetMapping("/productPrintTable/{orderProductId}")
	@ApiOperation(value = "产品打印信息管理",notes = "产品打印信息管理",response = OrderPrintTableDto.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderProductId",value = "订单产品id",dataType = "string",paramType = "path",required = true)
	})
	private String findProductPrintTable(@PathVariable String orderProductId){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		RequestResult result=this.orderFacade.findProductPrintTable(orderProductId);
		return jsonMapper.toJson(result);
	}
}
