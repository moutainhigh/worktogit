package com.lwxf.industry4.webapp.facade.admin.factory.product.impl;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lwxf.industry4.webapp.bizservice.product.*;
import com.lwxf.industry4.webapp.bizservice.warehouse.StorageService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.warehouse.StorageDto;
import com.lwxf.industry4.webapp.domain.entity.product.*;
import com.lwxf.industry4.webapp.domain.entity.warehouse.Storage;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.admin.factory.product.ProductCategoryFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/1/001 11:00
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("productCategoryFacade")
public class ProductCategoryFacadeImpl extends BaseFacadeImpl implements ProductCategoryFacade {
	@Resource(name = "productCategoryService")
	private ProductCategoryService productCategoryService;
	@Resource(name = "productColorService")
	private ProductColorService productColorService;
	@Resource(name = "productMaterialService")
	private ProductMaterialService productMaterialService;
	@Resource(name = "productService")
	private ProductService productService;
	@Resource(name = "productSpecService")
	private ProductSpecService productSpecService;
	@Resource(name = "storageService")
	private StorageService storageService;
	@Override
	public RequestResult findAll() {
		return ResultFactory.generateRequestResult(this.productCategoryService.findAllByBranchId(WebUtils.getCurrBranchId()));
	}

	@Override
	public RequestResult selectProductCategoryByFilter(MapContext mapContext) {
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,WebUtils.getCurrBranchId());
		return ResultFactory.generateRequestResult(this.productCategoryService.selectProductCategoryByFilter(mapContext));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addProductCategory(ProductCategory productCategory) {
		//企业id
		String branchId= WebUtils.getCurrBranchId();
		MapContext key = new MapContext();
		key.put("key", productCategory.getKey());
		key.put(WebConstant.KEY_ENTITY_BRANCH_ID,branchId);
		List<ProductCategory> keyProducts = this.productCategoryService.selectProductCategoryByFilter(key);
		//如果key重复则返回 不允许重复错误
		if(keyProducts.size()!=0){
			HashMap<String, String> result = new HashMap<>();
			result.put("key",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,result);
		}
		ProductCategory nameProducts = this.productCategoryService.selectProductCategoryByName(productCategory.getName(),branchId);
		//如果name重复则返回 不允许重复错误
		if(nameProducts!=null){
			HashMap<String, String> result = new HashMap<>();
			result.put("name",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,result);
		}
		productCategory.setBranchId(branchId);
		this.productCategoryService.add(productCategory);
		return ResultFactory.generateRequestResult(productCategory);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateByMapContent(MapContext mapContext,String id) {
		//企业id
		String branchId= WebUtils.getCurrBranchId();
		if(!this.productCategoryService.isExist(id)){
			return ResultFactory.generateResNotFoundResult();
		}
		String key = (String) mapContext.get("key");
		if(key!=null){
			if(key.trim().equals("")){
				mapContext.put("key",null);
			}else {
				MapContext map = new MapContext();
				map.put("key", key);
				map.put(WebConstant.KEY_ENTITY_BRANCH_ID,branchId);
				List<ProductCategory> keyProducts = this.productCategoryService.selectProductCategoryByFilter(map);
				//如果key重复则返回 不允许重复错误
				if (keyProducts.size() != 0 && !keyProducts.get(0).getId().equals(id)) {
					HashMap<String, String> result = new HashMap<>();
					result.put("key", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
					return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, result);
				}
			}
		}
		String name = (String) mapContext.get("name");
		if(name!=null){
			ProductCategory nameProducts = this.productCategoryService.selectProductCategoryByName(name,branchId);
			//如果name重复则返回 不允许重复错误
			if(nameProducts!=null&&!nameProducts.getId().equals(id)){
				HashMap<String, String> result = new HashMap<>();
				result.put("name",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
				return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,result);
			}
		}
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		this.productCategoryService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult(mapContext);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteById(String id) {
		if(!this.productCategoryService.isExist(id)){
			return ResultFactory.generateSuccessResult();
		}
		List<ProductColor> productColors = this.productColorService.selectByCategoryId(id);
		//判断是否被产品颜色表所引用 被引用 不允许删除
		if(productColors!=null&&productColors.size()!=0){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RES_BE_IN_USE_10006,AppBeanInjector.i18nUtil.getMessage("BIZ_RES_BE_IN_USE_10006"));
		}
		List<ProductMaterial> productMaterial = this.productMaterialService.selectByCategoryId(id);
		//判断是否被产品材质表所引用 被引用 不允许删除
		if(productMaterial!=null&&productMaterial.size()!=0){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RES_BE_IN_USE_10006,AppBeanInjector.i18nUtil.getMessage("BIZ_RES_BE_IN_USE_10006"));
		}
		List<Product> products = this.productService.selectByCategoryId(id);
		//判断是否被产品表所引用 被引用 不允许删除
		if(products!=null&&products.size()!=0){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RES_BE_IN_USE_10006,AppBeanInjector.i18nUtil.getMessage("BIZ_RES_BE_IN_USE_10006"));
		}
		List<ProductSpec> productSpecs= this.productSpecService.selectByCategoryId(id);
		//判断是否被产品规格表所引用 被引用 不允许删除
		if(productSpecs!=null&&productSpecs.size()!=0){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RES_BE_IN_USE_10006,AppBeanInjector.i18nUtil.getMessage("BIZ_RES_BE_IN_USE_10006"));
		}
		MapContext mapContext = new MapContext();
		mapContext.put("productCategoryId",id);
		PaginatedFilter paginatedFilter = PaginatedFilter.newOne();
		paginatedFilter.setFilters(mapContext);
		PaginatedList<StorageDto> storages = this.storageService.selectByFilter(paginatedFilter);
		if(storages.getRows()!=null&&storages.getRows().size()!=0){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RES_BE_IN_USE_10006,AppBeanInjector.i18nUtil.getMessage("BIZ_RES_BE_IN_USE_10006"));
		}
		this.productCategoryService.deleteById(id);
		return ResultFactory.generateSuccessResult();
	}
}
