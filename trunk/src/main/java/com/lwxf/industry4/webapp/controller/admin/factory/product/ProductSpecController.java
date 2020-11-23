package com.lwxf.industry4.webapp.controller.admin.factory.product;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.product.ProductSpec;
import com.lwxf.industry4.webapp.facade.admin.factory.product.ProductSpecFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/1/001 10:55
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/api/f/productcategories/{cid}/specs")
public class ProductSpecController {
	@Resource(name = "productSpecFacade")
	private ProductSpecFacade productSpecFacade;
	/**
	 * 通过分类ID 查询规格列表 或者 通过名称模糊查询
	 * @param cid
	 * @param name
	 * @return
	 */
	@GetMapping
	public RequestResult selectProductSpecList(@PathVariable String cid, @RequestParam(required = false) String name){
		return this.productSpecFacade.selectProductSpecList(cid,name);
	}

	/**
	 * 新增产品规格
	 * @param productSpec
	 * @param cid
	 * @return
	 */
	@PostMapping
	public RequestResult addProductSpec(@RequestBody ProductSpec productSpec, @PathVariable String cid){
		productSpec.setProductCategoryId(cid);
		RequestResult result = productSpec.validateFields();
		if(result!=null){
			return result;
		}
		return this.productSpecFacade.addProductSpec(productSpec);
	}

	/**
	 * 修改产品规格信息
	 * @param update
	 * @param cid
	 * @param id
	 * @return
	 */
	@PutMapping("{id}")
	public RequestResult updateProductSpec(@RequestBody MapContext update, @PathVariable String cid, @PathVariable String id){
		RequestResult result = ProductSpec.validateFields(update);
		if(result!=null){
			return result;
		}
		return this.productSpecFacade.updateProductSpec(update,cid,id);
	}

	/**
	 * 删除分类下的材质
	 * @param cid
	 * @param id
	 * @return
	 */
	@DeleteMapping("{id}")
	public RequestResult deleteById(@PathVariable String cid,@PathVariable String id){
		return this.productSpecFacade.deleteById(cid,id);
	}
}
