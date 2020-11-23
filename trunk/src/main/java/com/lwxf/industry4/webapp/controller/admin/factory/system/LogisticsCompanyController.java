package com.lwxf.industry4.webapp.controller.admin.factory.system;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.system.LogisticsCompany;
import com.lwxf.industry4.webapp.facade.admin.factory.system.LogisticsCompanyFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/26/026 15:59
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value = "LogisticsCompanyController",tags = "F端后台管理接口:物流公司信息管理")
@RestController
@RequestMapping(value = "/api/f/company/logistics",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class LogisticsCompanyController {
	@Resource(name = "logisticsCompanyFacade")
	private LogisticsCompanyFacade logisticsCompanyFacade;

	/**
	 * 物流公司列表
	 * @return
	 */
	@ApiOperation(value = "物流公司列表",notes = "物流公司列表")
	@GetMapping
	private String findList(@RequestParam(required = false) String name,
							@RequestParam(required = false) String status,
			                @RequestParam(required = false) Integer pageNum,
							@RequestParam(required = false) Integer pageSize){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		String branchId= WebUtils.getCurrBranchId();
		MapContext mapContext=MapContext.newOne();
		if(LwxfStringUtils.isNoneBlank(name)){
			mapContext.put("name",name);
		}
		if(LwxfStringUtils.isNoneBlank(status)){
			mapContext.put("status",status);
		}
		return jsonMapper.toJson(this.logisticsCompanyFacade.findList(mapContext,branchId,pageNum,pageSize));
	}
	/**
	 * 物流公司详情
	 */
	@ApiOperation(value = "物流公司详情",notes = "物流公司详情",response = LogisticsCompany.class)
	@GetMapping("/{logisticId}")
	private String findLogisticInfo(@PathVariable String logisticId){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		RequestResult result=this.logisticsCompanyFacade.findLogisticInfoById(logisticId);
		return jsonMapper.toJson(result);
	}
	/**
	 * 添加物流公司
	 */
	@ApiOperation(value = "添加物流公司",notes = "添加物流公司",response = LogisticsCompany.class)
	@PostMapping
	private RequestResult addLogisticCompany(@RequestBody LogisticsCompany logisticsCompany){
		RequestResult result=this.logisticsCompanyFacade.addLogisticCompany(logisticsCompany);
		return result;
	}
	/**
	 * 修改物流公司
	 */
	@ApiOperation(value = "修改物流公司",notes = "修改物流公司",response = LogisticsCompany.class)
	@PutMapping("/{logisticId}")
	private RequestResult updateLogisticCompany(@PathVariable String logisticId, @RequestBody MapContext mapContext){
		RequestResult result=this.logisticsCompanyFacade.updateLogisticCompany(logisticId,mapContext);
		return result;
	}

	/**
	 * 删除物流公司
	 */
	@ApiOperation(value = "删除物流公司",notes = "删除物流公司")
	@DeleteMapping("/{logisticId}")
	private RequestResult deleteLogisticCompany(@PathVariable String logisticId){
		return this.logisticsCompanyFacade.deleteLogisticCompany(logisticId);
	}

}
