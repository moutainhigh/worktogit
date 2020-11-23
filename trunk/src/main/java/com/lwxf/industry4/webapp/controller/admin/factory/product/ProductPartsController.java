package com.lwxf.industry4.webapp.controller.admin.factory.product;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.product.ProductPartsDto;
import com.lwxf.industry4.webapp.domain.entity.product.ProductParts;
import com.lwxf.industry4.webapp.facade.admin.factory.product.ProductPartsFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：产品部件管理
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/8/30 0030 10:52
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value = "ProductPartsController", tags = {"pc端后台接口:产品部件管理"})
@RestController
@RequestMapping(value = "/api/f/productparts",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class ProductPartsController {
	@Resource(name = "productPartsFacade")
	private ProductPartsFacade productPartsFacade;

	@ApiOperation(value = "产品部件生产方式信息列表",notes = "",response = ProductPartsDto.class)
	@GetMapping
	private String findProducts(){
		String branchId= WebUtils.getCurrBranchId();
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		return jsonMapper.toJson(this.productPartsFacade.findProducts(branchId));
	}
	@ApiOperation(value = "添加部件",notes = "",response = ProductPartsDto.class)
	@PostMapping
	private RequestResult addProductParts(@RequestBody ProductParts productParts){
		String branchId= WebUtils.getCurrBranchId();
//		mapContext.put("branchId",branchId);
		return this.productPartsFacade.addProductParts(productParts);

	}
	@ApiOperation(value = "修改部件",notes = "",response = ProductPartsDto.class)
     @PutMapping("/{partsId}")
    private RequestResult updateProductParts(@PathVariable String partsId,@RequestBody MapContext mapContext){
		return this.productPartsFacade.updateProductParts(partsId,mapContext);
	}
	@ApiOperation(value = "删除部件",notes = "",response = ProductPartsDto.class)
	@DeleteMapping("/{partsId}")
	private RequestResult deleteProductParts(@PathVariable String partsId){
		return this.productPartsFacade.deleteProductParts(partsId);
	}

}
