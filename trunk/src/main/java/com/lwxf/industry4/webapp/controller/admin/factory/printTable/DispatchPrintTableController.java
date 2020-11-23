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
import com.lwxf.industry4.webapp.domain.dto.printTable.DispatchPrintTableDto;
import com.lwxf.industry4.webapp.facade.admin.factory.dispatching.DispatchFacade;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/7/13/013 9:32
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/api/f/dispatchs", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
@Api(value = "DispatchPrintTableController", tags = {"pc端后台接口:发货单打印信息管理"})
public class DispatchPrintTableController {

	@Resource(name = "dispatchFacade")
	private DispatchFacade dispatchFacade;


	@GetMapping("/{id}")
	@ApiOperation(value = "获取发货单打印信息",response = DispatchPrintTableDto.class)
	private RequestResult findDispatchPrintInfo(@PathVariable String id){
		return this.dispatchFacade.findDispatchPrintInfo(id);
	}
}
