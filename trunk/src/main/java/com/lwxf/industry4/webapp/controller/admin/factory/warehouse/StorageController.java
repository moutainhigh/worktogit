package com.lwxf.industry4.webapp.controller.admin.factory.warehouse;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.product.ProductCategoryType;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.warehouse.Storage;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.warehouse.StorageFacade;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/13/013 15:31
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/api/f/storages",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class StorageController {
	@Resource(name = "storageFacade")
	private StorageFacade storageFacade;

	/**
	 * 新增仓库
	 * @param storage
	 * @return
	 */
	@PostMapping
	private RequestResult addStorage(@RequestBody Storage storage){
		storage.setCreated(DateUtil.getSystemDate());
		storage.setCreator(WebUtils.getCurrUserId());
		RequestResult result = storage.validateFields();
		if (result!=null){
			return result;
		}
		return this.storageFacade.addStorage(storage);
	}

	/**
	 * 获取仓库列表
	 * @param name
	 * @param pageNum
	 * @param pageSize
	 * @param purchase 是否是采购使用
	 * @return
	 */
	@GetMapping
	private String findStorageList(@RequestParam(required = false) String name,
										  @RequestParam(required = false) String categoryId,
										  @RequestParam(required = false)Integer pageNum,
										  @RequestParam(required = false) Integer pageSize,
										  @RequestParam(required = false) Boolean purchase){

		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		if(null == pageSize){
			pageSize = AppBeanInjector.configuration.getPageSizeLimit();
		}
		if(null == pageNum){
			pageNum = 1;
		}
		MapContext mapContext = this.createMapContent(name,categoryId,purchase);
		return jsonMapper.toJson(this.storageFacade.findStorageList(mapContext,pageNum,pageSize));
	}

	private MapContext createMapContent(String name,String categoryId,Boolean purchase) {
		MapContext mapContext = new MapContext();
		if(name==null||name.trim().equals("")){
			mapContext.put(WebConstant.KEY_ENTITY_NAME,null);
		}else{
			mapContext.put(WebConstant.KEY_ENTITY_NAME,name);
		}
		if(categoryId==null||categoryId.trim().equals("")||categoryId.equals("all")){
			mapContext.put("productCategoryId",null);
		}else{
			mapContext.put("productCategoryId",categoryId);
		}
		if(purchase!=null){
			mapContext.put("types",Arrays.asList(ProductCategoryType.RAW_MATERIAL,ProductCategoryType.TAILINGS,ProductCategoryType.WASTE,ProductCategoryType.WITHDRAWAL,ProductCategoryType.RETURN_GOODS));
		}
		return mapContext;
	}

	/**
	 * 修改仓库信息
	 * @param id
	 * @param mapContext
	 * @return
	 */
	@PutMapping("{id}")
	private RequestResult updateStorage(@PathVariable String id,@RequestBody MapContext mapContext){
		RequestResult result = Storage.validateFields(mapContext);
		if(result!=null){
			return result;
		}
		return this.storageFacade.updateStorage(id,mapContext);
	}

	/**
	 * 删除仓库
	 * @param id
	 * @return
	 */
	@DeleteMapping("{id}")
	private RequestResult deleteById(@PathVariable String id){
		return this.storageFacade.deleteById(id);
	}

	/**
	 * 查询全部产品
	 * @return
	 */
	@GetMapping("products")
	private RequestResult findAllProduct(){
		return this.storageFacade.findAllProduct();
	}
}
