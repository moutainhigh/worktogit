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
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/7/13 0013 11:59
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController("DemandPrintTableController")
@RequestMapping(value = "/api/f/branchs", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
@Api(value = "DemandPrintTableController", tags = {"pc端后台接口:产品需求打印信息管理"})
public class DemandPrintTableController {
	@Resource(name = "orderFacade")
	private OrderFacade orderFacade;

	@GetMapping("/demandPrintTable/{demandId}")
	@ApiOperation(value = "产品需求打印信息接口",notes = "产品需求打印信息接口",response = OrderPrintTableDto.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "demandId",value = "需求表id",dataType = "string",paramType = "path",required = true)
	})
	private String findDemandPrintTable(@PathVariable String demandId){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		RequestResult result=this.orderFacade.findDemandPrintTable(demandId);
		return jsonMapper.toJson(result);
	}
}
