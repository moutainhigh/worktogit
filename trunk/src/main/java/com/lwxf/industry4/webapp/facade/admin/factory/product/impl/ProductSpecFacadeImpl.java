package com.lwxf.industry4.webapp.facade.admin.factory.product.impl;

import javax.annotation.Resource;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lwxf.industry4.webapp.bizservice.product.ProductCategoryService;
import com.lwxf.industry4.webapp.bizservice.product.ProductService;
import com.lwxf.industry4.webapp.bizservice.product.ProductSpecService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.dto.product.ProductSpecDto;
import com.lwxf.industry4.webapp.domain.entity.product.Product;
import com.lwxf.industry4.webapp.domain.entity.product.ProductSpec;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.admin.factory.product.ProductSpecFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/6/006 11:31
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("productSpecFacade")
public class ProductSpecFacadeImpl extends BaseFacadeImpl implements ProductSpecFacade {
	@Resource(name = "productSpecService")
	private ProductSpecService productSpecService;
	@Resource(name = "productCategoryService")
	private ProductCategoryService productCategoryService;
	@Resource(name = "productService")
	private ProductService productService;
	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteById(String cid, String id) {
		//判断分类是否存在
		if (!this.productCategoryService.isExist(cid)){
			return ResultFactory.generateResNotFoundResult();
		}
		//判断是否被产品引用
		List<Product> products = this.productService.selectBySpecId(id);
		if(products!=null&&products.size()!=0){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RES_BE_IN_USE_10006,AppBeanInjector.i18nUtil.getMessage("BIZ_RES_BE_IN_USE_10006"));
		}
		this.productSpecService.deleteById(id);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateProductSpec(MapContext update, String cid, String id) {
		//判断分类是否存在
		if(!this.productCategoryService.isExist(cid)){
			return ResultFactory.generateResNotFoundResult();
		}
		//判断该条数据是否存在
		if(!this.productSpecService.isExist(id)){
			return ResultFactory.generateResNotFoundResult();
		}
		ProductSpec productSpec = new ProductSpec(id, update.getTypedValue("name", String.class), update.getTypedValue("notes", String.class), cid);
		//判断要修改的名称是否存在 不允许重复
		if(this.productSpecService.isExistByProductSpec(productSpec)){
			MapContext mapContext = MapContext.newOne();
			mapContext.put("name",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,mapContext);
		}
		update.put(WebConstant.KEY_ENTITY_ID,id);
		this.productSpecService.updateByMapContext(update);
		return ResultFactory.generateRequestResult(this.productSpecService.findById(id));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addProductSpec(ProductSpec productSpec) {
		//判断分类是否存在
		if(!this.productCategoryService.isExist(productSpec.getProductCategoryId())){
			return ResultFactory.generateResNotFoundResult();
		}
		//判断分类下该种规格是否已存在
		if(this.productSpecService.isExistByProductSpec(productSpec)){
			MapContext result = MapContext.newOne();
			result.put("name",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,result);
		}
		this.productSpecService.add(productSpec);
		ProductSpecDto productSpecDto = new ProductSpecDto();
		productSpecDto.clone(productSpec);
		productSpecDto.setProductCategoryName(this.productCategoryService.findById(productSpec.getProductCategoryId()).getName());
		return ResultFactory.generateRequestResult(productSpecDto);
	}

	@Override
	public RequestResult selectProductSpecList(String cid, String name) {
		return ResultFactory.generateRequestResult(this.productSpecService.selectProductSpecList(cid,name));
	}
}
