package com.lwxf.industry4.webapp.controller.admin.factory.product;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.product.ProductMaterial;
import com.lwxf.industry4.webapp.facade.admin.factory.product.ProductMaterialFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/1/001 10:53
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/api/f/productcategories/{cid}/materials",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class ProductMaterialController {
	@Resource(name = "productMaterialFacade")
	private ProductMaterialFacade productMaterialFacade;

	/**
	 * 通过分类ID 查询板材列表 或者 通过名称模糊查询
	 * @param cid
	 * @param name
	 * @return
	 */
	@GetMapping
	public RequestResult selectProductMaterialList(@PathVariable String cid, @RequestParam(required = false) String name){
		return this.productMaterialFacade.selectProductMaterialList(cid,name);
	}

	/**
	 * 新增产品材质
	 * @param productMaterial
	 * @param cid
	 * @return
	 */
	@PostMapping
	public RequestResult addProductMaterial(@RequestBody ProductMaterial productMaterial,@PathVariable String cid){
		productMaterial.setProductCategoryId(cid);
		RequestResult result = productMaterial.validateFields();
		if(result!=null){
			return result;
		}
		return this.productMaterialFacade.addProductMaterial(productMaterial);
	}

	/**
	 * 修改产品材质信息
	 * @param update
	 * @param cid
	 * @param id
	 * @return
	 */
	@PutMapping("{id}")
	public RequestResult updateProductMaterial(@RequestBody MapContext update,@PathVariable String cid,@PathVariable String id){
		RequestResult result = ProductMaterial.validateFields(update);
		if(result!=null){
			return result;
		}
		return this.productMaterialFacade.updateProductMaterial(update,cid,id);
	}

	/**
	 * 删除分类下的材质
	 * @param cid
	 * @param id
	 * @return
	 */
	@DeleteMapping("{id}")
	public RequestResult deleteById(@PathVariable String cid,@PathVariable String id){
		return this.productMaterialFacade.deleteById(cid,id);
	}
}
