package com.lwxf.industry4.webapp.controller.admin.factory.printTable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.printTable.OrderPrintTableDto;
import com.lwxf.industry4.webapp.facade.admin.factory.dealer.OrderFacade;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/7/11 0011 9:38
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController("OrderPrintTableController")
@RequestMapping(value = "/api/f/branchs", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
@Api(value = "OrderPrintTableController", tags = {"pc端后台接口:订单打印信息管理"})
public class OrderPrintTableController {

	@Resource(name = "orderFacade")
	private OrderFacade orderFacade;

	@GetMapping("/orderPrintTable/{orderId}")
	@ApiOperation(value = "订单打印信息接口",notes = "订单打印信息接口",response = OrderPrintTableDto.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderId",value = "订单id",dataType = "string",paramType = "path",required = true)
	})
  private String findOrderPrintTable(@PathVariable String orderId){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		RequestResult result=this.orderFacade.findOrderPrintTable(orderId);
		return jsonMapper.toJson(result);
  }
}
