package com.lwxf.industry4.webapp.controller.admin.factory.product;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.lwxf.industry4.webapp.common.constant.WebConstant;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.product.ProductColor;
import com.lwxf.industry4.webapp.facade.admin.factory.product.ProductColorFacade;
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
@RequestMapping(value = "/api/f/productcategories/{cid}/colors",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class ProductColorController {
	@Resource(name = "productColorFacade")
	private ProductColorFacade productColorFacade;

	/**
	 * 查询产品颜色列表 通过名称模糊查询 或者 产品分类查询 参数为null时查询全部
	 * @param name
	 * @param cid
	 * @return
	 */
	@GetMapping
	public RequestResult selectProductColorList(@RequestParam(required = false) String name, @PathVariable String cid){
		MapContext mapperFilter = this.createMapperFilter(name,cid);
		return this.productColorFacade.selectProductColorByFilter(mapperFilter);
	}

	/**
	 * 添加产品颜色
	 * @param productColor
	 * @return
	 */
	@PostMapping
	public RequestResult addProductColor(@RequestBody ProductColor productColor,@PathVariable String cid){
		productColor.setProductCategoryId(cid);
		RequestResult result = productColor.validateFields();
		if(result!=null){
			return result;
		}
		return this.productColorFacade.addProductColor(productColor);
	}

	/**
	 * 修改产品颜色信息
	 * @param mapContext
	 * @param id
	 * @return
	 */
	@PutMapping("{id}")
	public RequestResult updateByMapComtent(@RequestBody MapContext mapContext,@PathVariable String id,@PathVariable String cid){
		RequestResult result = ProductColor.validateFields(mapContext);
		if(result!=null){
			return result;
		}
		return this.productColorFacade.updateByMapComtent(mapContext,id,cid);
	}

	/**
	 * 删除颜色信息
	 * @param id
	 * @param cid
	 * @return
	 */
	@DeleteMapping("{id}")
	public RequestResult deleteById(@PathVariable String id,@PathVariable String cid){
		return this.productColorFacade.deleteById(id,cid);
	}
	private MapContext createMapperFilter(String name, String productCategoryId) {
		MapContext mapContext = new MapContext();
		if(name!=null){
			mapContext.put("name",name);
		}
		mapContext.put("productCategoryId",productCategoryId);
		return mapContext;
	}
}
