package com.lwxf.industry4.webapp.controller.smallProgram.smallProgramSearch;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.facade.smallProgram.smallProgramSearch.SpSearchFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/10/19 0019 14:10
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value = "SpSearchController",tags ={"小程序F端接口: 搜索管理接口"} )
@RestController(value = "SpSearchController")
@RequestMapping(value = "/spapi/f/searchs", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class SpSearchController {
       @Resource(name = "spSearchFacade")
	   private SpSearchFacade spSearchFacade;

	/**
	 * 经销商、订单、员工搜索接口
	 */
	@GetMapping("/{searchType}")
	@ApiOperation(value = "经销商、订单、员工搜索接口",notes = "经销商、订单、员工搜索接口")
	private String  FuzzyQuery(@PathVariable@ApiParam(value = "查询经销商传1，员工传2，订单传3")String searchType,
							   @RequestParam(required = false)@ApiParam(value = "模糊查询条件") String searchMessage,
							   @RequestParam(required = false)@ApiParam(value = "页面大小")Integer pageSize,
							   @RequestParam(required = false)@ApiParam(value = "页码")Integer pageNum){
		String atoken= WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String branchId =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		RequestResult result=this.spSearchFacade.FuzzyQuery(searchType,branchId,searchMessage,pageSize,pageNum);
		return jsonMapper.toJson(result);
	}
}
