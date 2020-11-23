package com.lwxf.industry4.webapp.controller.admin.factory.system;

import javax.annotation.Resource;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.domain.dto.supplier.SupplierDtoFowWx;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.system.MenusDisabled;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.system.Menus;
import com.lwxf.industry4.webapp.facade.admin.factory.system.MenusFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/1/3/003 16:03
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@Api(value="MenusController",tags={"F端后台管理接口:菜单管理"})
@RequestMapping(value = "/api/f/menus",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class MenusController {
	@Resource(name = "menusFacade")
	private MenusFacade menusFacade;

	/**
	 * 查询菜单列表
	 * @param name
	 * @param type
	 * @param key
	 * @return
	 */
	@ApiOperation(value = "查询菜单列表", notes = "")
	@GetMapping
	private String findList(@RequestParam(required = false) String name,@RequestParam(required = false) Integer type,@RequestParam(required = false) String key,@RequestParam(required = false) Boolean disabled){
		MapContext mapContext = this.createMapContent(name,type,key,disabled);
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		return jsonMapper.toJson(this.menusFacade.findList(mapContext));
	}

	/**
	 * 查询权限菜单列表
	 * @param name
	 * @param type
	 * @param key
	 * @return
	 */
	@ApiOperation(value = "查询权限菜单列表", notes = "")
	@GetMapping("/findPessionList")
	private RequestResult findPessionList(@RequestParam(required = false) String name,@RequestParam(required = false) Integer type,@RequestParam(required = false) String key,@RequestParam(required = false) Boolean disabled){
 		MapContext mapContext = this.createMapContent(name,type,key,disabled);
		return this.menusFacade.findListPession(mapContext);
	}

	/**
	 * 新增菜单
	 * @param menus
	 * @return
	 */
	@ApiOperation(value = "新增菜单", notes = "")
	@PostMapping
	private RequestResult addMenus(@RequestBody Menus menus){
		menus.setRelevantResource(0);
		menus.setDisabled(MenusDisabled.UNDISABLED.getValue());
		RequestResult result = menus.validateFields();
		if(result!=null){
			return result;
		}
		return this.menusFacade.addMenus(menus);
	}

	/**
	 * 修改菜单信息
	 * @param mapContext
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改菜单信息", notes = "")
	@PutMapping("{id}")
	private RequestResult updateMenus(@RequestBody MapContext mapContext,@PathVariable String id){
		RequestResult result = Menus.validateFields(mapContext);
		if(result!=null){
			return result;
		}
		return this.menusFacade.updateMenus(mapContext,id);
	}

	/**
	 * 删除菜单
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "删除菜单", notes = "")
	@DeleteMapping("{id}")
	private RequestResult deleteMenus(@PathVariable String id){
		return this.menusFacade.deleteMenus(id);
	}


	private MapContext createMapContent(String name, Integer type, String key,Boolean disabled) {
		MapContext mapContext = new MapContext();
		if(name!=null&&!name.trim().equals("")){
			mapContext.put(WebConstant.KEY_ENTITY_NAME,name);
		}
		if(type!=null&&type!=-1){
			mapContext.put("type",type);
		}
		if(key!=null&&!key.trim().equals("")){
			mapContext.put("key",key);
		}
		if(disabled!=null){
			mapContext.put("disabled",disabled);
		}
		return mapContext;
	}

}
