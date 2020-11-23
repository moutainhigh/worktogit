package com.lwxf.industry4.webapp.controller.admin.factory;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.baseservice.cache.RedisUtils;
import com.lwxf.industry4.webapp.bizservice.company.CompanyEmployeeService;
import com.lwxf.industry4.webapp.bizservice.user.UserRoleService;
import com.lwxf.industry4.webapp.common.constant.CommonConstant;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.user.UserState;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.session.MySessionContext;
import com.lwxf.industry4.webapp.common.shiro.ShiroUtil;
import com.lwxf.industry4.webapp.common.utils.UniqueKeyUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.config.RedisConfig;
import com.lwxf.industry4.webapp.domain.dao.company.CompanyEmployeeDao;
import com.lwxf.industry4.webapp.domain.dao.userRole.UserRoleDao;
import com.lwxf.industry4.webapp.domain.dto.user.LoginedUser;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;
import com.lwxf.industry4.webapp.domain.entity.system.Role;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.userRole.UserRole;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.user.UserFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.lwxf.industry4.webapp.facade.AppBeanInjector.i18nUtil;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/11/30/030 11:10
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value = "UserController",tags = {"用户相关接口"})
@RestController
@RequestMapping(value = "/api/f/users",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class UserController {
	@Resource(name = "userFacade")
	private UserFacade userFacade;
	@Resource(name = "userRoleService")
	private UserRoleService userRoleService;

	@Resource(name = "companyEmployeeService")
	private CompanyEmployeeService companyEmployeeService;

	@Resource(name = "companyEmployeeDao")
	private CompanyEmployeeDao companyEmployeeDao;

	@Resource(name = "userRoleDao")
	private UserRoleDao userRoleDao;
	@Resource(name = "redisUtils")
	private RedisUtils redisUtils;




	@PostMapping("getUserRole")
	@ApiOperation(value = "获取用户全部角色")
	public RequestResult getUserRole(){
		List<UserRole> allUserRole = userRoleDao.getAllUserRole(WebUtils.getCurrUserId());
		return ResultFactory.generateRequestResult(allUserRole);
	}

	@GetMapping("updateRole")
	@ApiOperation(value = "更新角色信息")
	public RequestResult updateRole(String roleId){
		String currUserId = WebUtils.getCurrUserId();
		MapContext mapContext = new MapContext();
		mapContext.put("userId",currUserId);
		mapContext.put("roleId",roleId);
		int i = companyEmployeeDao.updateRole(mapContext);
		//返回默认首页地址
		String homePage=AppBeanInjector.roleService.findById(roleId).getHomePage();
		if(homePage==null||homePage.equals("")){
			homePage=WebConstant.REDIRECT_PATH_FACTORY_ADMIN+"#/"+WebConstant.PAGE_FACTORY_HOME;
		}
  		return ResultFactory.generateRequestResult(homePage);
	}




	/**
	 * F端用户登录接口
	 * @param userMap
	 * @param request
	 * @return
	 */
	@PostMapping("login")
	public RequestResult userLogin(@RequestBody MapContext userMap, HttpServletRequest request){
		LoginedUser currUser = WebUtils.getCurrUser();
		String loginName = userMap.getTypedValue("loginName", String.class);
		User byLoginName = AppBeanInjector.userService.findByMobile(loginName);
		if(byLoginName==null){
			return ResultFactory.generateErrorResult(ErrorCodes.LOGIN_FAIL_90000, i18nUtil.getMessage("LOGIN_FAIL_90000"));
		}
		String userid=byLoginName.getId();
		//把ip放入redis
		Session session = SecurityUtils.getSubject().getSession();
		String sessionId = session.getId().toString();
		String singleSessionId = redisUtils.hGet(CommonConstant.SHIRO_SINGLE_KEY, userid)+"";
		if (StringUtils.isNotBlank(singleSessionId) && !sessionId.equals(singleSessionId)) {
			HttpSession singleSession = MySessionContext.getInstance().getSession(singleSessionId);
			MySessionContext.getInstance().DelSession(singleSession);
		}
//		HttpSession singleSession = MySessionContext.getInstance().getSession(singleSessionId);
//		System.out.println(singleSession);
		redisUtils.hPut(CommonConstant.SHIRO_SINGLE_KEY, userid, sessionId);
		if (currUser != null) {
			if((LwxfStringUtils.isEquals(loginName,currUser.getMobile(),true) || LwxfStringUtils.isEquals(loginName,currUser.getEmail(),true))){
				if(currUser.getState()==UserState.ENABLED.getValue() && currUser.getEnableLoginPc() != null && currUser.getEnableLoginPc().equals(WebConstant.ENABLE)) {
					CompanyEmployee employee = currUser.getCompanyEmployee();
					boolean isMember = null == employee || !employee.getCompanyId().equals(WebUtils.getCurrCompanyId());
					RequestResult result = ResultFactory.generateSuccessResult();
					if (isMember) {
						result.put("go", WebConstant.REDIRECT_PATH_404);
					} else {
						//查询首页路径
						Role byId = AppBeanInjector.roleService.findById(employee.getRoleId());
						String homePage=byId.getHomePage();
						if(homePage==null||homePage.equals("")){
							result.put("go", WebConstant.REDIRECT_PATH_FACTORY_ADMIN+"#/"+WebConstant.PAGE_FACTORY_HOME);
						}else {
							result.put("go", homePage);
						}
						result.put(WebConstant.KEY_ENTITY_COMPANY_ID, employee.getCompanyId());
						//result.put("go", WebConstant.REDIRECT_PATH_FACTORY_ADMIN);
					}
					return result;
				}
			}else{
				ShiroUtil.logoutCurrUser();
			}
		}
		return this.userFacade.toFactoryLogin(userMap,request);
	}

	/**
	 * 修改用户密码
	 * @param mapContext
	 * @return
	 */
	@PutMapping("password")
	public RequestResult updateUserPassword(@RequestBody MapContext mapContext){
		return this.userFacade.updateUserPassword(mapContext);
	}
	/**
	 * 修改用户密码
	 * @param userId
	 * @return
	 */
	@PutMapping("restPassword")
	public RequestResult restPassword(@RequestParam String userId){
		return this.userFacade.restPassword(userId);
	}
	/**
	 * 修改当前登录用户信息
 	 * @param mapContext
	 * @return
	 */
	@PutMapping("info")
	public RequestResult updateUserInfo(@RequestBody MapContext mapContext){
		RequestResult result = User.validateFields(mapContext);
		if (result!=null){
			return result;
		}
		return this.userFacade.updateUser(mapContext);
	}

	@GetMapping("info")
	public RequestResult findUserInfo(){
		return this.userFacade.findUserInfo(WebUtils.getCurrUserId());
	}



}
