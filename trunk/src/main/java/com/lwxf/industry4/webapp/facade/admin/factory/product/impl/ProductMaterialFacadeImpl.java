package com.lwxf.industry4.webapp.facade.admin.factory.product.impl;

import javax.annotation.Resource;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lwxf.industry4.webapp.bizservice.product.ProductCategoryService;
import com.lwxf.industry4.webapp.bizservice.product.ProductMaterialService;
import com.lwxf.industry4.webapp.bizservice.product.ProductService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.dto.product.ProductMaterialDto;
import com.lwxf.industry4.webapp.domain.entity.product.Product;
import com.lwxf.industry4.webapp.domain.entity.product.ProductMaterial;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.admin.factory.product.ProductMaterialFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/3/003 14:48
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("productMaterialFacade")
public class ProductMaterialFacadeImpl extends BaseFacadeImpl implements ProductMaterialFacade {
	@Resource(name = "productMaterialService")
	private ProductMaterialService productMaterialService;
	@Resource(name = "productCategoryService")
	private ProductCategoryService productCategoryService;
	@Resource(name = "productService")
	private ProductService productService;
	@Override
	public RequestResult selectProductMaterialList(String cid, String name) {
		return ResultFactory.generateRequestResult(this.productMaterialService.selectProductMaterialList(cid,name));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addProductMaterial(ProductMaterial productMaterial) {
		//判断分类是否存在
		if(!this.productCategoryService.isExist(productMaterial.getProductCategoryId())){
			return ResultFactory.generateResNotFoundResult();
		}
		//判断分类下该种材质是否已存在
		if(this.productMaterialService.isExistByProductMaterial(productMaterial)){
			MapContext result = MapContext.newOne();
			result.put("name",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,result);
		}
		this.productMaterialService.add(productMaterial);
		ProductMaterialDto productMaterialDto = new ProductMaterialDto();
		productMaterialDto.clone(productMaterial);
		productMaterialDto.setProductCategoryName(this.productCategoryService.findById(productMaterial.getProductCategoryId()).getName());
		return ResultFactory.generateRequestResult(productMaterialDto);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateProductMaterial(MapContext update, String cid,String id) {
		//判断分类是否存在
		if(!this.productCategoryService.isExist(cid)){
			return ResultFactory.generateResNotFoundResult();
		}
		//判断该条数据是否存在
		if(!this.productMaterialService.isExist(id)){
			return ResultFactory.generateResNotFoundResult();
		}
		ProductMaterial productMaterial = new ProductMaterial(id, update.getTypedValue("name", String.class), update.getTypedValue("notes", String.class), cid);
		//判断要修改的名称是否存在 不允许重复
		if(this.productMaterialService.isExistByProductMaterial(productMaterial)){
			MapContext mapContext = MapContext.newOne();
			mapContext.put("name",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,mapContext);
		}
		update.put(WebConstant.KEY_ENTITY_ID,id);
		this.productMaterialService.updateByMapContext(update);
		return ResultFactory.generateRequestResult(this.productMaterialService.findById(id));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteById(String cid, String id) {
		//判断分类是否存在
		if (!this.productCategoryService.isExist(cid)){
			return ResultFactory.generateResNotFoundResult();
		}
		//判断是否被产品引用
		List<Product> products = this.productService.selectByMaterialId(id);
		if(products!=null&&products.size()!=0){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RES_BE_IN_USE_10006,AppBeanInjector.i18nUtil.getMessage("BIZ_RES_BE_IN_USE_10006"));
		}
		this.productMaterialService.deleteById(id);
		return ResultFactory.generateSuccessResult();
	}
}
