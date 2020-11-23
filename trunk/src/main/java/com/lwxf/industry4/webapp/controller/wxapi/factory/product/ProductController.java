package com.lwxf.industry4.webapp.controller.wxapi.factory.product;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.product.ProductDto;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.wxapi.factory.product.ProductFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/6/15/015 18:10
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value = "wxFProductController",tags = {"F端微信小程序接口:产品信息"})
@RestController("wxFProductController")
@RequestMapping(value = "/wxapi/f/products",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class ProductController {

	@Resource(name = "wxFProductFacade")
	private ProductFacade productFacade;


	/**
	 * 查询产品列表接口
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping
	@ApiOperation(value = "查询产品列表接口",response = ProductDto.class)
	private RequestResult findProductList(@RequestParam(required = false,defaultValue = "1") Integer pageNum,@RequestParam(required = false,defaultValue = "10") Integer pageSize){
		String atoken=WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		if(uid==null){
			return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		return this.productFacade.findProductList(pageNum,pageSize,uid);
	}

	/**
	 * 查询产品详情
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	@ApiOperation(value = "查询产品详情",response = ProductDto.class)
	private String findProductInfo(@PathVariable String id){
		JsonMapper jsonMapper = new JsonMapper();
		return jsonMapper.toJson(this.productFacade.findProductInfo(id));
	}

	/**
	 * 修改产品是否推荐
	 * @param id
	 * @param lowerShelf
	 * @return
	 */
	@PutMapping("/{id}/{lowerShelf}")
	@ApiOperation(value = "修改产品是否推荐")
	private RequestResult updateLowerShelf(@PathVariable@ApiParam(value = "产品ID") String id, @PathVariable@ApiParam(value = "0 不推荐 1 推荐") Integer lowerShelf){
		return this.productFacade.updateLowerShelf(id,lowerShelf);
	}

}
