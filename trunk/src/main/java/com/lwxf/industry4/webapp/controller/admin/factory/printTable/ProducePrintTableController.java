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
 * @create：2019/7/15 0015 9:59
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController("ProducePrintTableController")
@RequestMapping(value = "/api/f/branchs", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
@Api(value = "ProducePrintTableController", tags = {"pc端后台接口:产品生产单（门板/柜体）打印信息管理"})
public class ProducePrintTableController {
	@Resource(name = "orderFacade")
	private OrderFacade orderFacade;

	@GetMapping("/producePrintTable/{produceId}")
	@ApiOperation(value = "产品生产单打印信息管理",notes = "产品生产单打印信息管理",response = OrderPrintTableDto.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "produceId",value = "生产单id",dataType = "string",paramType = "path",required = true)

	})
	private String findProducePrintTable(@PathVariable String produceId){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		RequestResult result=this.orderFacade.findProducePrintTable(produceId);
		return jsonMapper.toJson(result);
	}
}
