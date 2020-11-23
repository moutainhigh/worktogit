package com.lwxf.industry4.webapp.controller.wxapi.factory.dealer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.FileMimeTypeUtil;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.company.CompanyDtoForApp;
import com.lwxf.industry4.webapp.domain.dto.company.WxCompanyDto;
import com.lwxf.industry4.webapp.domain.entity.company.Company;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.statement.DealerStatementFacade;
import com.lwxf.industry4.webapp.facade.wxapi.factory.dealer.DealerFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：F端微信小程序 经销商管理
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/6 0006 14:47
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value = "wxDealerController",tags ={"F端微信小程序接口: 经销商管理"} )
@RestController(value = "wxDealerController")
@RequestMapping(value = "/wxapi/f", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class DealerController {
	@Resource(name = "wxDealerFacade")
	private DealerFacade dealerFacade;
	private DealerStatementFacade dealerStatementFacade;

	/**
	 * 查询经销商列表接口
	 * @param pageNum
	 * @param pageSize
	 * @param cityId
	 * @param type
	 * @param status0
	 * @param status1
	 * @param condation
	 * @return
	 */
	@GetMapping("/branchs/dealers")
	@ApiOperation(value = "查询经销商列表接口",notes = "查询经销商列表接口",response = CompanyDtoForApp.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum",value = "页码",dataType = "integer",paramType = "query"),
			@ApiImplicitParam(name = "pageSize",value = "页数",dataType = "integer",paramType = "query"),
			@ApiImplicitParam(name = "condation",value = "经销商公司名称,负责人姓名",dataType = "string",paramType = "query"),
			@ApiImplicitParam(name = "cityId",value = "地区id ",dataType = "string",paramType = "query"),
			@ApiImplicitParam(name = "status0",value = "首页入口：状态 0 - 意向经销商；1 - 签约经销商；从意向经销商入口进传0 ,从签约进传1",required = true,dataType = "string",paramType = "query"),
			@ApiImplicitParam(name = "status1",value = "列表页：状态 0 - 意向经销商；1 - 签约经销商；",dataType = "string",paramType = "query"),
			@ApiImplicitParam(name = "type",value = "经销商类型 1：直营店；2：店中店；3：专卖店；4：加盟店；",dataType = "String",paramType = "query")
	})
	public String findWxDealers(@RequestParam(required = false) Integer pageNum,
								@RequestParam(required = false) Integer pageSize,
								@RequestParam(required = false) String cityId,
								@RequestParam(required = false) String type,
								@RequestParam String status0,
								@RequestParam(required = false) String status1,
								@RequestParam(required = false) String condation){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		String atoken= WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		String branchid =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
		MapContext params=MapContext.newOne();
		String status=status0;
		if(LwxfStringUtils.isNotBlank(status1)){
			status=status1;
		}
		params.put("status",status);

		if(LwxfStringUtils.isNotBlank(cityId)){
			params.put("cityId",cityId);
		}
		if(LwxfStringUtils.isNotBlank(type)){
			params.put("type",type);
		}
		if(LwxfStringUtils.isNotBlank(condation)){
			params.put("condation",condation);
		}
		RequestResult result=this.dealerFacade.findWxDealers(branchid,pageNum,pageSize,params);
		return jsonMapper.toJson(result);
	}

	/**
	 * 经销商详情查询接口
	 * @param dealerId
	 * @return
	 */
	@ApiOperation(value = "经销商详情查询接口",notes = "经销商详情查询接口",response = WxCompanyDto.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "dealerId",value = "经销商公司id",dataType = "string",paramType = "path",required = true)
	})
	@GetMapping("/branchs/dealers/{dealerId}")
	private String findWxDealerInfo(@PathVariable String dealerId){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		String atoken= WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		String branchid =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();

		RequestResult result=this.dealerFacade.findWxDealerInfo(branchid,dealerId);
		return jsonMapper.toJson(result);
	}

	/**
	 * 添加经销商
	 * @param company
	 * @return
	 */
	@ApiOperation(value = "经销商添加接口",notes = "经销商添加接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "status",value = "经销商类型 0意向 1签约",paramType = "path",required = true),
			@ApiImplicitParam(name = "fileId",value = "封面图片id  ",dataType = "string",paramType = "query")

	})
	@PostMapping("/branchs/dealers/{status}")
	public String addWxDealer(@PathVariable Integer status,
			                @RequestParam(required = false) String fileId,
							@RequestBody Company company){
		JsonMapper jsonMapper = new JsonMapper();
		String atoken= WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		String branchid =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
		company.setBranchId(branchid);
		RequestResult requestResult = this.dealerFacade.addWxDealer(company,fileId,uid,status);
		return jsonMapper.toJson(requestResult);

	}

	/**
	 * 添加店铺封面
	 * @param multipartFile
	 * @return
	 */
	@ApiOperation(value = "添加店铺封面",notes = "添加店铺封面")
	@PostMapping("/branchs/dealers/coverImage")
	public RequestResult addDealerShopCoverImage(@RequestBody MultipartFile multipartFile){
		Map<String, Object> errorInfo = new HashMap<>();
		if (multipartFile==null){
			errorInfo.put("multipartFile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (!FileMimeTypeUtil.isLegalImageType(multipartFile)) {
			errorInfo.put("multipartFile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_ILLEGAL_ARGUMENT"));
		}
		if (multipartFile.getSize() > 1024L * 1024L * AppBeanInjector.configuration.getUploadBackgroundMaxsize()) {
			return ResultFactory.generateErrorResult(com.lwxf.commons.exception.ErrorCodes.BIZ_FILE_SIZE_LIMIT_10031, LwxfStringUtils.format(AppBeanInjector.i18nUtil.getMessage("BIZ_FILE_SIZE_LIMIT_10031"), AppBeanInjector.configuration.getUploadBackgroundMaxsize()));
		}
		if (errorInfo.size() > 0) {
			return ResultFactory.generateErrorResult(com.lwxf.commons.exception.ErrorCodes.VALIDATE_ERROR, errorInfo);
		}

		return this.dealerFacade.addDealerShopCoverImage(multipartFile);
	}

	/**
	 * 修改经销商信息
	 * @return
	 */
	@PutMapping("/branchs/dealers/{dealerId}")
	@ApiOperation(value = "经销商修改接口",notes = "经销商修改接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "dealerId",value = "经销商id",dataType = "string",paramType = "path",required = true)
	})
	public RequestResult updateWxDealerInfo(@PathVariable String dealerId,
											@RequestBody MapContext mapContext){
		String atoken= WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		if(uid==null){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		return this.dealerFacade.updateWxDealerInfo(dealerId,mapContext);

	}
	@ApiOperation(value = "经销商详情数据统计", notes = "经销商详情数据统计")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "dealerId", value = "经销商id", dataType = "string", paramType = "query", required = true),
			@ApiImplicitParam(name = "yearTime", value = "年", dataType = "string", paramType = "query", required = false)

	})
	@GetMapping("/dealerStatements/infoCount")
	private String companyOrderRankingList(@RequestParam(required = false) String dealerId,
										   @RequestParam(required = false) String yearTime) {
		JsonMapper jsonMapper = new JsonMapper();
		RequestResult result = this.dealerFacade.companyOrderInfoCount(dealerId,yearTime);
		return jsonMapper.toJson(result);
	}
}
