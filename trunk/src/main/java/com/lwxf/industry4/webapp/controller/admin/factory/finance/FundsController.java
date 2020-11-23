package com.lwxf.industry4.webapp.controller.admin.factory.finance;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.financing.FundsDto;
import com.lwxf.industry4.webapp.domain.entity.financing.Funds;
import com.lwxf.industry4.webapp.facade.admin.factory.finance.FundsFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/9/25 0025 14:15
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/api/f/funds", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
@Api(value = "FinanceController", tags = "财务科目管理")
public class FundsController {

	@Resource(name = "fundsFacade")
	private FundsFacade fundsFacade;

	@GetMapping
	@ApiOperation(value = "财务科目列表",notes = "",response = FundsDto.class)
	public String findFundsList(@RequestParam(required = false) String way,
								@RequestParam(required = false) String type){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		String branchId= WebUtils.getCurrBranchId();
		MapContext mapContext=MapContext.newOne();
		mapContext.put("branchId",branchId);
		mapContext.put("way",way);
		mapContext.put("type",type);
		RequestResult result=this.fundsFacade.findFundsList(mapContext);
		return jsonMapper.toJson(result);
	}
	@PostMapping
	@ApiOperation(value = "财务科目添加",notes = "",response = FundsDto.class)
	public RequestResult addFunds(@RequestBody Funds funds){
		return this.fundsFacade.addFunds(funds);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "财务科目修改",notes = "",response = FundsDto.class)
	public RequestResult updateFunds(@PathVariable String id,@RequestBody MapContext mapContext){
		mapContext.put("id",id);
		return this.fundsFacade.updateFunds(mapContext);
	}
	@DeleteMapping("/{id}")
	@ApiOperation(value = "财务科目删除",notes = "",response = FundsDto.class)
	public RequestResult deleteFunds(@PathVariable String id){
		return this.fundsFacade.deleteFunds(id);
	}

}
