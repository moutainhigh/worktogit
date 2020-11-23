package com.lwxf.industry4.webapp.controller.admin.factory.printTable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.printTable.CoordinationPrintTableDto;
import com.lwxf.industry4.webapp.facade.admin.factory.order.ProduceOrderFacade;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/7/13/013 14:50
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/api/f/coordination", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
@Api(value = "CoordinationPrintTableController", tags = {"pc端后台接口:外协单打印信息管理"})
public class CoordinationPrintTableController {

	@Resource(name="produceOrderFacade")
	private ProduceOrderFacade produceOrderFacade;

	@ApiOperation(value = "外协单打印数据",notes = "外协单打印数据",response = CoordinationPrintTableDto.class)
	@GetMapping("/{id}")
	private RequestResult findCoordinationPrintInfo(@PathVariable String id){
		return this.produceOrderFacade.findCoordinationPrintInfo(id);
	}

}
