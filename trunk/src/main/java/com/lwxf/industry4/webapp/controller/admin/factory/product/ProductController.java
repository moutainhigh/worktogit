package com.lwxf.industry4.webapp.controller.admin.factory.product;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.FileMimeTypeUtil;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.product.ProductStatus;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.product.Product;
import com.lwxf.industry4.webapp.facade.admin.factory.product.ProductFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/1/001 10:56
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/api/f/products", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class ProductController {
	@Resource(name = "productFacade")
	private ProductFacade productFacade;

	/**
	 * 多条件查询 其中名称查询为模糊查询
	 *
	 * @param categoryId
	 * @param name
	 * @param model
	 * @param status
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping
	private String findProductList(@RequestParam(required = false) String categoryId, @RequestParam(required = false) String name,
								   @RequestParam(required = false) String model,
								   @RequestParam(required = false) Integer status,
								   @RequestParam(required = false) Integer pageNum,
								   @RequestParam(required = false) Integer pageSize) {
		if (null == pageSize) {
			pageSize = AppBeanInjector.configuration.getPageSizeLimit();
		}
		if (null == pageNum) {
			pageNum = 1;
		}
		MapContext mapContext = this.createMapContent(categoryId, name, model, status,null,null,null,null);
		RequestResult result = this.productFacade.findProductList(mapContext, pageNum, pageSize);
		JsonMapper jsonMapper = new JsonMapper();
		return jsonMapper.toJson(result);
	}

	/**
	 * 新增产品
	 *
	 * @param product
	 * @return
	 */
	@PostMapping
	private String addProduct(@RequestBody Product product) {
		JsonMapper jsonMapper = new JsonMapper();
		product.setStatus(ProductStatus.ENABLE.getValue());
		product.setCreated(DateUtil.getSystemDate());
		product.setCreator(WebUtils.getCurrUserId());
		product.setBranchId(WebUtils.getCurrBranchId());
		RequestResult result = product.validateFields();
		if (result != null) {
			return jsonMapper.toJson(result);
		}
		RequestResult result1 = this.productFacade.addProduct(product);
		return jsonMapper.toJson(result1);
	}

	/**
	 * 修改产品信息
	 *
	 * @param id
	 * @param mapContext
	 * @return
	 */
	@PutMapping("{id}")
	private String updateProduct(@RequestBody MapContext mapContext, @PathVariable String id) {
		JsonMapper jsonMapper = new JsonMapper();
		RequestResult result = Product.validateFields(mapContext);
		if (result != null) {
			return jsonMapper.toJson(result);
		}
		RequestResult result1 = this.productFacade.updateProduct(mapContext, id);
		return jsonMapper.toJson(result1);
	}

	/**
	 * 通过类型 区分上传不同的图片资源
	 * @param id
	 * @param type
	 * @param multipartFileList
	 * @return
	 */
	@PostMapping("{id}/files/{type}")
	private RequestResult uploadFileImg(@PathVariable String id, @PathVariable Integer type, @RequestBody List<MultipartFile> multipartFileList) {
		Map<String, String> result = new HashMap<>();
		if(multipartFileList==null||multipartFileList.size()==0){
			result.put("file", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, result);
		}
		for(MultipartFile multipartFile:multipartFileList){
			if (multipartFile == null) {
				result.put("file", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			} else if (!FileMimeTypeUtil.isLegalImageType(multipartFile)) {
				result.put("file", AppBeanInjector.i18nUtil.getMessage("VALIDATE_ILLEGAL_ARGUMENT"));
			} else if (multipartFile.getSize() > 1024 * 1024 * AppBeanInjector.configuration.getUploadBackgroundMaxsize()) {
				return ResultFactory.generateErrorResult(ErrorCodes.BIZ_FILE_SIZE_LIMIT_10031, LwxfStringUtils.format(AppBeanInjector.i18nUtil.getMessage("BIZ_FILE_SIZE_LIMIT_10031"), AppBeanInjector.configuration.getUploadBackgroundMaxsize()));
			}
			if (result.size() > 0) {
				return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, result);
			}
		}
		return this.productFacade.uploadFileImg(id,type,multipartFileList);
	}

	/**
	 * 通过不同条件查询提示资源列表
	 * @param resources
	 * @param productCategoryId
	 * @param productMaterial
	 * @param productColor
	 * @param productSpec
	 * @param series
	 * @return
	 */
	@GetMapping("/{resources}")
	private RequestResult findResourcesList(@PathVariable String resources,@RequestParam(required = false) String productCategoryId,
											@RequestParam(required = false)String productMaterial,@RequestParam(required = false)String productColor,
											@RequestParam(required = false)String productSpec,@RequestParam(required = false)String series){
		MapContext mapContent = this.createMapContent(productCategoryId, null, null, null, productMaterial, productColor, productSpec, series);
		mapContent.put("resources",resources);
		return this.productFacade.findResourcesList(mapContent);
	}

	/**
	 * 查询产品详情 产品信息 以及 图片信息
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}/info")
	private RequestResult findProductInfo(@PathVariable String id){
		return this.productFacade.findProductInfo(id);
	}

	/**
	 * 删除图片资源
	 * @param productId
	 * @param fileId
	 * @return
	 */
	@DeleteMapping("/{productId}/files/{fileId}")
	private RequestResult deleteFile(@PathVariable String productId,@PathVariable String fileId){
		return this.productFacade.deleteFile(productId,fileId);
	}

	private MapContext createMapContent(String categoryId, String name, String model, Integer status,String productMaterial,String productColor,String productSpec,String series) {
		MapContext mapContext = MapContext.newOne();
		if (categoryId != null) {
			mapContext.put(WebConstant.KEY_ENTITY_CATEGORY_ID, categoryId);
		}
		if (name != null && !name.trim().equals("")) {
			mapContext.put(WebConstant.KEY_ENTITY_NAME, name);
		}
		if (model != null) {
			mapContext.put("model", model);
		}
		if (status == null || status == -1) {
			mapContext.put(WebConstant.KEY_ENTITY_STATUS, null);
		} else {
			mapContext.put(WebConstant.KEY_ENTITY_STATUS, status);
		}
		if (productMaterial != null && !productMaterial.trim().equals("")) {
			mapContext.put("productMaterial", productMaterial);
		}
		if (productColor != null && !productColor.trim().equals("")) {
			mapContext.put("productColor", productColor);
		}
		if (productSpec != null && !productSpec.trim().equals("")) {
			mapContext.put("productSpec", productSpec);
		}
		if (series != null && !series.trim().equals("")) {
			mapContext.put("series", series);
		}
		return mapContext;
	}

}
