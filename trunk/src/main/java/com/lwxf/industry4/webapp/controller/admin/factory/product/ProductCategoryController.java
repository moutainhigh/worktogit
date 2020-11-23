package com.lwxf.industry4.webapp.controller.admin.factory.product;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.product.ProductCategory;
import com.lwxf.industry4.webapp.facade.admin.factory.product.ProductCategoryFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：产品分类
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/1/001 10:52
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/api/f/productcategories",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class ProductCategoryController {
	@Resource(name = "productCategoryFacade")
	private ProductCategoryFacade productCategoryFacade;

	/**
	 * 查询产品分类列表
	 * @param name
	 * @param key
	 * @return
	 */
	@GetMapping
	public RequestResult selectProductCategoryList(@RequestParam(required = false) String name,@RequestParam(required = false) String key){
		MapContext mapperFilter = this.createMapperFilter(name, key);
		if(mapperFilter.size()!=0){
			return this.productCategoryFacade.selectProductCategoryByFilter(mapperFilter);
		}
		return this.productCategoryFacade.findAll();
	}

	/**
	 * 添加产品分类
	 * @param productCategory
	 * @return
	 */
	@PostMapping
	public RequestResult addProductCategory(@RequestBody ProductCategory productCategory){
		RequestResult result = productCategory.validateFields();
		if(result!=null){
			return result;
		}
		return this.productCategoryFacade.addProductCategory(productCategory);
	}

	/**
	 * 修改产品分类信息
	 * @param update
	 * @param id
	 * @return
	 */
	@PutMapping("{id}")
	public RequestResult updateByMapContent(@RequestBody MapContext update,@PathVariable String id){
		RequestResult result = ProductCategory.validateFields(update);
		if (result!=null){
			return result;
		}
		return this.productCategoryFacade.updateByMapContent(update,id);
	}

	/**
	 * 删除产品分类
	 * @param id
	 * @return
	 */
	@DeleteMapping("{id}")
	public RequestResult deleteById(@PathVariable String id){
		return this.productCategoryFacade.deleteById(id);
	}

	private MapContext createMapperFilter(String name,String key){
		MapContext mapContext = new MapContext();
		if(name!=null&&!name.trim().equals("")){
			mapContext.put("name",name);
		}
		if(key!=null&&!key.trim().equals("")){
			mapContext.put("key",key);
		}
		return mapContext;
	}
}
