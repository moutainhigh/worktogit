package com.lwxf.industry4.webapp.common.worker;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import org.apache.commons.collections4.CollectionUtils;

import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.baseservice.tsmanager.TSManagerEntity;
import com.lwxf.industry4.webapp.baseservice.tsmanager.TSManualData;
import com.lwxf.industry4.webapp.baseservice.tsmanager.base.AbstractAutowireWorker;
import com.lwxf.industry4.webapp.baseservice.tsmanager.base.AfterCommitEventListener;
import com.lwxf.industry4.webapp.baseservice.tsmanager.base.BeforeUpdateEventListener;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.activity.SystemActivityEvent;
import com.lwxf.industry4.webapp.common.enums.SQLType;
import com.lwxf.industry4.webapp.common.enums.user.UserType;
import com.lwxf.industry4.webapp.common.shiro.LwxfShiroRealm;
import com.lwxf.industry4.webapp.common.utils.LwxfAssert;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;
import com.lwxf.industry4.webapp.domain.entity.system.RolePermission;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;

import static com.lwxf.industry4.webapp.common.utils.WebUtils.getDataFromRequestMap;

/**
 * 功能：用户权限缓存清理worker
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-24 19:05:28
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("shiroCacheEvictWorker")
public class ShiroCacheEvictWorker extends AbstractAutowireWorker implements BeforeUpdateEventListener, AfterCommitEventListener {
	private static final String BE_EVICT_SHIRO_CACHE_USERS = "be_evict_shiro_cache_users";
	private static Logger logger = LoggerFactory.getLogger(ShiroCacheEvictWorker.class);
	private static Map<Class, IEvictCacheHandler> handlerMap = new HashMap<Class, IEvictCacheHandler>() {{
		put(CompanyEmployee.class, new CompanyEmployeeEvictCacheHandler());
		put(RolePermission.class, new RolePermissionEvictCacheHandler());
	}};
	@Resource(name = "shiroRealm")
	private LwxfShiroRealm shiroRealm;

	@Override
	public void beforeUpdate(TSManagerEntity tsManagerEntity, SQLType sqlType) {
		HttpServletRequest request = WebUtils.getHttpServletRequest();
		if(request == null){
			return;
		}
		// 非工厂web端，不做处理
		String reqPath = request.getServletPath();
		if(!reqPath.startsWith("/api/f/")){
			return;
		}

		// 清理逻辑
		TSManualData tsManualData = (TSManualData) getDataFromRequestMap(WebConstant.TSMANAGER_MANUAL_ACTION_FLAG);
		IEvictCacheHandler handler = handlerMap.get(null == tsManualData ? tsManagerEntity.getClazz() : tsManualData.getClazz());
		if (null == handler) {
			return;
		}

		Object params;
		if (null == tsManualData) {
			params = tsManagerEntity.getMapperParams();
		} else {
			params = tsManualData.getParams();
			sqlType = SystemActivityEvent.parseEventToSQLType(tsManualData.getEvent());
		}

		if (sqlType == SQLType.INSERT) {
			//新增组织时候不组装该消息
			if (WebUtils.requestMapContainsKey(WebConstant.MQ_ORG_ADD_IGNORE)) {
				return;
			}
			handler.doAdd(this.getParamsForInsertOrDelete(params));
		} else if (sqlType == SQLType.UPDATE) {
			handler.doUpdate(params);
		} else if (sqlType == SQLType.DELETE) {
			handler.doDelete(this.getParamsForInsertOrDelete(params));
		} else {
			LwxfAssert.isTrue(false, "这是数据的读操作，请检查代码");
		}
	}

	@Override
	public void afterCommit(List<TSManagerEntity> tsManagerEntitys) {
		Set<String> users = (Set<String>) getDataFromRequestMap(BE_EVICT_SHIRO_CACHE_USERS);
		if (CollectionUtils.isNotEmpty(users)) {
			users.stream().forEach((userId) -> {
				//判断账号如果是厂家的 就清除权限数据
				if(AppBeanInjector.userService.findById(userId).getType().equals(UserType.FACTORY.getValue())){
					shiroRealm.clearCachedAuthorizationInfo(userId);
				}
			});
		}
	}

	private Object getParamsForInsertOrDelete(Object params) {
		if (params instanceof Map) {
			Map map = (Map)params;
			if(map.containsKey(WebConstant.KEY_COMMON_LIST)){
				return ((Map) params).get(WebConstant.KEY_COMMON_LIST);
			}
		}
		return params;
	}

	interface IEvictCacheHandler {
		void doAdd(Object params);

		void doUpdate(Object params);

		void doDelete(Object params);

		default Set<String> getBeEvictUsers() {
			Set<String> users = (Set<String>) getDataFromRequestMap(BE_EVICT_SHIRO_CACHE_USERS);
			if (null == users) {
				users = new HashSet<>();
				WebUtils.putDataToRequestMap(BE_EVICT_SHIRO_CACHE_USERS, users);
			}
			return users;
		}

		default String getUpdateId(Map update, String key) {
			if (null == key) {
				key = "id";
			}
			Object idObj = update.get(key);
			if (null == idObj) {
				return null;
			}
			return idObj.toString();
		}
	}

	/**
	 * 公司员工的角色和状态发生变化时，清理该员工的权限缓存
	 */
	static class CompanyEmployeeEvictCacheHandler implements IEvictCacheHandler{
		@Override
		public void doAdd(Object params) {
		}

		@Override
		public void doUpdate(Object params) {
			Map update = (Map) params;
			String roleKey = "roleId";
			String statusKey = "status";
			String id = this.getUpdateId(update,null);
			if(update.containsKey(roleKey)||update.containsKey(statusKey)){
				String userId;
				if(LwxfStringUtils.isBlank(id)){
					userId = (String)update.get(WebConstant.KEY_ENTITY_USER_ID);
				}else{
					/**
					 * TODO 该查询可能会出现事物异常，如果出现异常，那么需要在facade里查出来，放到WebUtils.requestMap中，然后在这里取出来即可
					 * in ->>> WebUtils.putDataToRequestMap
					 * out ->>> WebUtils.getDataFromRequestMap
					 */
					CompanyEmployee epm = AppBeanInjector.companyEmployeeService.findById(id);
					userId = epm.getUserId();
				}
				this.getBeEvictUsers().add(userId);
			}
		}

		@Override
		public void doDelete(Object params) {

		}
	}

	/**
	 * 角色的权限发生变化时，清理与角色相关的用户的权限缓存
	 */
	static class RolePermissionEvictCacheHandler implements IEvictCacheHandler{
		@Override
		public void doAdd(Object params) {

		}

		/**
		 * TODO：目前仅是处理厂家后台的逻辑，APP权限设置加上后需要根据实际情况在这里做兼容操作
		 * 理想状态：APP设置角色权限时，手动事务的控制与厂家后台保持一致
		 * 当用户的权限信息进行单独设置时，需要监听EmployeePermission
		 * @param params
		 */
		@Override
		public void doUpdate(Object params) {
			String roleId = (String)params;
			if(LwxfStringUtils.isNotBlank(roleId)){
				List<CompanyEmployee> epms = AppBeanInjector.companyEmployeeService.findListByRoleId(roleId);
				Set<String> evitUsers = this.getBeEvictUsers();
				epms.stream().forEach(e ->{
					if(e.getStatus().intValue()==1){
						evitUsers.add(e.getUserId());
					}
				});
			}
		}

		@Override
		public void doDelete(Object params) {

		}
	}
}