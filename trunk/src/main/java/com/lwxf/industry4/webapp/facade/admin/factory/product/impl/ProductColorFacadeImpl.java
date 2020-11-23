package com.lwxf.industry4.webapp.facade.admin.factory.product.impl;

import javax.annotation.Resource;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lwxf.industry4.webapp.bizservice.product.ProductCategoryService;
import com.lwxf.industry4.webapp.bizservice.product.ProductColorService;
import com.lwxf.industry4.webapp.bizservice.product.ProductService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.dto.product.ProductColorDto;
import com.lwxf.industry4.webapp.domain.entity.product.Product;
import com.lwxf.industry4.webapp.domain.entity.product.ProductColor;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.admin.factory.product.ProductColorFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/1/001 15:35
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("productColorFacade")
public class ProductColorFacadeImpl extends BaseFacadeImpl implements ProductColorFacade {
	@Resource(name = "productColorService")
	private ProductColorService productColorService;
	@Resource(name = "productService")
	private ProductService productService;
	@Resource(name = "productCategoryService")
	private ProductCategoryService productCategoryService;
	@Override
	public RequestResult selectProductColorByFilter(MapContext mapContext) {
		return ResultFactory.generateRequestResult(this.productColorService.selectProductColorByFilter(mapContext));
	}

	@Override
	public RequestResult findAll() {
		return ResultFactory.generateRequestResult(this.productColorService.findAll());
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addProductColor(ProductColor productColor) {
		if(!this.productCategoryService.isExist(productColor.getProductCategoryId())){
			return ResultFactory.generateResNotFoundResult();
		}
		//判断当前分类下 名称是否重复
		if(this.productColorService.selectByCategoryIdAndNameAndId(productColor.getProductCategoryId(),productColor.getName(),null)){
			MapContext mapContext = new MapContext();
			mapContext.put("name",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,mapContext);
		}
		this.productColorService.add(productColor);
		ProductColorDto productColorDto = new ProductColorDto();
		productColorDto.clone(productColor);
		productColorDto.setProductCategoryName(this.productCategoryService.findById(productColor.getProductCategoryId()).getName());
		return ResultFactory.generateRequestResult(productColorDto);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateByMapComtent(MapContext mapContext, String id,String cid) {
		if(!this.productColorService.isExistCidAndId(cid,id)){
			return ResultFactory.generateResNotFoundResult();
		}
		String name = (String) mapContext.get("name");
		//判断名称是否重复
		if(name!=null&&!this.productColorService.isExistNameAndNoIdAndCid(name,id,cid)){
			mapContext.put(WebConstant.KEY_ENTITY_ID,id);
			this.productColorService.updateByMapContext(mapContext);
			return ResultFactory.generateRequestResult(this.productColorService.findById(id));
		}
		MapContext result = MapContext.newOne();
		result.put("name",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
		return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,result);
	}


	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteById(String id,String cid) {
		//判断该数据是否存在 不存在就直接返回成功
		if(!this.productColorService.isExistCidAndId(cid,id)){
			return ResultFactory.generateSuccessResult();
		}
		//判断该颜色是否被产品资源引用
		List<Product> products = this.productService.selectByColorId(id);
		if(products!=null&&products.size()!=0){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RES_BE_IN_USE_10006,AppBeanInjector.i18nUtil.getMessage("BIZ_RES_BE_IN_USE_10006"));
		}
		this.productColorService.deleteById(id);
		return ResultFactory.generateSuccessResult();
	}
}
