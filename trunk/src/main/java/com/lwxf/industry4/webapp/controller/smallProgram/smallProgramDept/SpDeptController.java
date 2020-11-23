package com.lwxf.industry4.webapp.controller.smallProgram.smallProgramDept;

import io.rong.models.response.TokenResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.baseservice.rongcloud.RongCloudUtils;
import com.lwxf.industry4.webapp.baseservice.sms.yunpian.SmsUtil;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.dto.UserInfoObj;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.org.Dept;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.system.RoleFacade;
import com.lwxf.industry4.webapp.facade.smallProgram.smallProgramDept.SpDeptFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/10/19 0019 9:14
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value = "SpDeptController", tags = {"小程序F端接口: 部门/员工管理"})
@RestController(value = "SpDeptController")
@RequestMapping(value = "/spapi/f/depts", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class SpDeptController {

	@Resource(name = "spDeptFacade")
	private SpDeptFacade spDeptFacade;
	@Resource(name = "roleFacade")
	private RoleFacade roleFacade;


	@GetMapping
	@ApiOperation(value = "通讯录部门员工列表", notes = "通讯录部门员工列表")
	public String findDeptMemberList() {
		String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String branchId = mapInfo.get("branchId") == null ? null : mapInfo.get("branchId").toString();
		JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
		RequestResult result = this.spDeptFacade.findDeptMemberList(branchId);
		return jsonMapper.toJson(result);
	}

	@PostMapping("/deptMembers")
	@ApiOperation(value = "添加员工", notes = "添加员工")
	public String addDeptMember(@RequestBody MapContext mapContext) {
		JsonMapper jsonMapper = new JsonMapper();
		StringBuffer pwd = new StringBuffer();
		RequestResult requestResult = this.spDeptFacade.addDeptMember(mapContext, pwd);
		//注册成功后给用户发短信
		if (Integer.parseInt((String) requestResult.get("code")) == (200)) {
			SmsUtil.sendDealerInfoMessage(mapContext.getTypedValue("mobile", String.class), mapContext.getTypedValue("name", String.class), pwd.toString());
		}
		//处理融云token
		UserInfoObj data = (UserInfoObj) requestResult.getData();
		User user = null;
		if (data != null) {
			user = data.getUser();
			TokenResult tokenResult = RongCloudUtils.registerUser(user);
			if (tokenResult != null) {
				String token = tokenResult.getToken();
				AppBeanInjector.userThirdInfoFacade.updateRongToken(user.getId(), token);
				UserThirdInfo userThirdInfo = data.getUserThirdInfo();
				userThirdInfo.setRongcloudToken(token);
				return jsonMapper.toJson(ResultFactory.generateRequestResult(AppBeanInjector.deptService.findOneByUserId(user.getId())));
			}
		}
		return jsonMapper.toJson(requestResult);
	}

	@PostMapping
	@ApiOperation(value = "添加部门", notes = "添加部门")
	public String addDept(@RequestBody @ApiParam(value = "部门信息") Dept dept) {
		String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String cid = mapInfo.get("companyId") == null ? null : mapInfo.get("companyId").toString();
		String branchId = mapInfo.get("branchId") == null ? null : mapInfo.get("branchId").toString();
		JsonMapper jsonMapper = new JsonMapper();
		dept.setCompanyId(cid);
		dept.setBranchId(branchId);
		RequestResult result = dept.validateFields();
		if (result != null) {
			return jsonMapper.toJson(result);
		}
		return jsonMapper.toJson(this.spDeptFacade.addDept(dept));
	}

	@PutMapping
	@ApiOperation(value = "批量移动员工", notes = "批量移动员工")
	public RequestResult moveDeptMember(@RequestBody MapContext mapContext) {
		return this.spDeptFacade.moveDeptMember(mapContext);
	}

	@DeleteMapping
	@ApiOperation(value = "批量删除部门", notes = "批量删除部门")
	public RequestResult deleteDept(@RequestParam @ApiParam(value = "部门ids（逗号分隔）") String ids) {
		return this.spDeptFacade.deleteDept(ids);
	}

	/**
	 * 根据类型查询角色列表
	 *
	 * @param type
	 * @return
	 */
	@GetMapping("/roles")
	@ApiOperation(value = "角色列表 ", notes = "角色列表")
	private RequestResult findRolesByType(@RequestParam(required = false) @ApiParam(value = "0-工厂角色 1-经销商角色") Integer type) {
		return this.roleFacade.findListByType(type, null);
	}
}
