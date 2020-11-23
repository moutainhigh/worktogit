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
 * @create：2019/7/13 0013 20:22
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController("DesignPrintTableController")
@RequestMapping(value = "/api/f/branchs", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
@Api(value = "DesignPrintTableController", tags = {"pc端后台接口:产品方案打印信息管理"})
public class DesignPrintTableController {
	@Resource(name = "orderFacade")
	private OrderFacade orderFacade;

	@GetMapping("/designPrintTable/{designId}")
	@ApiOperation(value = "产品设计打印信息管理",notes = "产品设计打印信息管理",response = OrderPrintTableDto.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "designId",value = "设计id",dataType = "string",paramType = "path",required = true)
	})
	private String findDesignPrintTable(@PathVariable String designId){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		RequestResult result=this.orderFacade.findDesignPrintTable(designId);
		return jsonMapper.toJson(result);
	}
}
