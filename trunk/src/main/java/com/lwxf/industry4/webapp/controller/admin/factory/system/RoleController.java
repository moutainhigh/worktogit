package com.lwxf.industry4.webapp.controller.admin.factory.system;

import javax.annotation.Resource;

import java.util.List;

import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dao.system.RoleDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.system.PermissionShow;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.system.Role;
import com.lwxf.industry4.webapp.domain.entity.system.RolePermission;
import com.lwxf.industry4.webapp.facade.admin.factory.system.RoleFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/14/014 15:41
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value = "RoleController",tags = {"F端后台管理接口:用户角色管理"})
@RestController
@RequestMapping(value = "/api/f/roles",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class RoleController {
	@Resource(name = "roleFacade")
	private RoleFacade roleFacade;

	@Resource(name = "roleDao")
	private RoleDao roleDao;

//	/**
//	 * 查询工厂全部角色
//	 * @return
//	 */
//	@GetMapping
//	private RequestResult findAllRoles(){
//		return this.roleFacade.findAllRoles();
//	}


	/**
	 *
	 * @return
	 */
	@ApiOperation(value = "根据类型查询角色",notes = "根据类型查询角色" )
	@GetMapping("/findDealRolesByType")
	private RequestResult findDealRolesByType(Integer type){
		String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		String branchId = "";
		if (atoken != null) {
			MapContext mapInfo = LoginUtil.checkLogin(atoken);
			branchId = mapInfo.get("branchId") == null ? null : mapInfo.get("branchId").toString();
		}

		PaginatedFilter paginatedFilter = new PaginatedFilter();
		MapContext mapContext = new MapContext();
		mapContext.put("type",type);
		mapContext.put("branchId",branchId);
		paginatedFilter.setFilters(mapContext);
        PaginatedList<Role> rolePaginatedList = roleDao.selectByFilter(paginatedFilter);

        return ResultFactory.generateRequestResult(rolePaginatedList);
	}

	/**
	 * 根据类型查询角色列表
	 * @param type
	 * @return
	 */
	@ApiOperation(value = "根据类型查询角色列表",notes = "根据类型查询角色列表" )
	@GetMapping
	private RequestResult findRolesByType(@RequestParam(required = false) Integer type){
		return this.roleFacade.findListByType(type,null);
	}

	/**
	 * 根据字典code查询部门下的人员
	 * @return
	 */
	@ApiOperation(value = "根据字典code查询部门下的人员",notes = "根据字典code查询部门下的人员" )
	@GetMapping("/code")
	private RequestResult findRolesByType(){
		return this.roleFacade.findListByBaseCode();
	}
	/**
	 * 根据角色查询角色下员工
	 */
	@ApiOperation(value = "根据角色查询角色下员工 接单员key-",notes = "根据角色查询角色下员工" )
	@GetMapping("/key")
	private RequestResult findRolesByRolekey(){
		return this.roleFacade.findRolesByRolekey();
	}
	/**
	 * 根据角色查询角色下员工
	 */
	@ApiOperation(value = "根据角色查询角色下员工 接单员key-",notes = "根据角色查询角色下员工" )
	@GetMapping("/key/{key}")
	private RequestResult findRolesByRolekeyold(){
		return this.roleFacade.findRolesByRolekey();
	}
	/**
	 * 新增角色
	 * @param role
	 * @return
	 */
	@ApiOperation(value = "新增角色",notes = "新增角色" )
	@PostMapping
	private RequestResult addRoles(@RequestBody Role role){
		RequestResult result = role.validateFields();
		if(result!=null){
			return result;
		}
		return this.roleFacade.addRoles(role);
	}

	/**
	 * 修改角色信息(名称 以及 是否是管理权限)
	 * @param mapContext
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "修改角色信息(名称 以及 是否是管理权限)",notes = "修改角色信息(名称 以及 是否是管理权限)" )
	@PutMapping("{id}")
	private RequestResult updataRoles(@RequestBody MapContext mapContext,@PathVariable String id){
		RequestResult result = Role.validateFields(mapContext);
		if(result!=null){
			return result;
		}
		return this.roleFacade.updataRoles(mapContext,id);
	}

	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "删除角色",notes = "删除角色" )
	@DeleteMapping("{id}")
	private RequestResult deleteRoles(@PathVariable String id){
		return this.roleFacade.deleteRoles(id);
	}

	/**
	 * 查询角色下的全部权限
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查询角色下的全部权限",notes = "查询角色下的全部权限" )
	@GetMapping("{id}/permissions")
	private RequestResult findRolePermission(@PathVariable String id){
		return this.roleFacade.findRolePermission(id);
	}

	/**
	 * 修改角色下权限信息
	 * @param id
	 * @param rolePermissions
	 * @return
	 */
	@ApiOperation(value = "修改角色下权限信息",notes = "修改角色下权限信息" )
	@PutMapping("{id}/permissions")
	private RequestResult updateRoleMenus(@PathVariable String id, @RequestBody List<RolePermission> rolePermissions){
		for(RolePermission rolePermission: rolePermissions){
			rolePermission.setRoleId(id);
			if(rolePermission.getShow()==null||rolePermission.getShow().equals("")) {
				rolePermission.setShow(PermissionShow.SHOW.getValue());
			}
			RequestResult result = rolePermission.validateFields();
			if(result!=null){
				return result;
			}
		}
		return this.roleFacade.updateRoleMenus(id,rolePermissions);
	}

	/**
	 * 根据分类查询全部按钮菜单
	 * @param type
	 * @return
	 */
	@ApiOperation(value = "根据分类查询全部按钮菜单",notes = "根据分类查询全部按钮菜单" )
	@GetMapping("all")
	private RequestResult findAllByType(@RequestParam Integer type){
		return this.roleFacade.findAllByType(type);
	}

	//个人使用 请勿调用
	@PutMapping("defalut")
	private RequestResult settingDefault(){
		return this.roleFacade.settingDefault();
	}
}
