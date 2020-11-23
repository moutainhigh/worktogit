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

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/7/18/018 14:33
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/api/f/orderproduct", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
@Api(value = "OrderProductPrintTableController", tags = {"pc端后台接口:成品单打印信息管理"})
public class OrderProductPrintTableController {


	@Resource(name = "orderFacade")
	private OrderFacade orderFacade;

	@GetMapping("/{id}")
	@ApiOperation(value = "成品单打印信息接口",notes = "成品单打印信息接口",response = OrderPrintTableDto.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id",value = "订单id",dataType = "string",paramType = "path",required = true)
	})
	private String findOrderProductPrintTable(@PathVariable String id){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		RequestResult result=this.orderFacade.findOrderProductPrintTable(id);
		return jsonMapper.toJson(result);
	}
}
