package com.lwxf.industry4.webapp.common.enums.permission;

import java.util.List;

import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.shiro.ShiroUtil;
import com.lwxf.commons.utils.LwxfStringUtils;

/**
 * 功能：权限验证辅助类
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-07-06 10:05
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public final class PermissionIndex {
	private PermissionIndex(){
	}

	/**
	 * 1. 用户角色权限串前缀
	 */
	public static final String PERMISSION_USER_ROLE_PREFIX = "ur{0}:";

	/**
	 * 2. 用户身份权限串前缀
	 */
	public static final String PERMISSION_USER_IDENTITY_PREFIX = "ui{0}:";

	/**
	 * 1. 用户角色权限定义
	 */
	public  enum  UserRolePermssion{
		CLERK(0),
		MANAGER(1),
		SHOPKEEPER(2),
		CUSTOMER_SERVICE(3);
		int index;
		UserRolePermssion(int index){
			this.index = index;
		}

		public int getIndex(){
			return this.index;
		}

		/**
		 * 获取用户的权限串
		 * @param permissionIndexs：用户所具有的权限项索引
		 * @return
		 */
		public static String getPermissionStr(List<Integer> permissionIndexs){
			return PermissionIndex.getPermissionStr(values().length,permissionIndexs);
		}
	}

	/**
	 * 1. 用户身份权限定义
	 */
	public  enum  UserIdentityPermssion{
		HOME_ADVISOR(0), 	// 家居顾问
		DESIGNER(1), 		// 设计师
		ERECTOR(2);			// 安装工
		int index;
		UserIdentityPermssion(int index){
			this.index = index;
		}

		public int getIndex(){
			return this.index;
		}

		/**
		 * 获取用户的权限串
		 * @param permissionIndexs：用户所具有的权限项索引
		 * @return
		 */
		public static String getPermissionStr(List<Integer> permissionIndexs){
			return PermissionIndex.getPermissionStr(values().length,permissionIndexs);
		}
	}

	/**
	 * 根据权限项长度生成用户权限串
	 * @param permLen：权限项的长度
	 * @param permissionIndexs：用户所具有的权限项索引
	 * @return
	 */
	private static String getPermissionStr(int permLen,List<Integer> permissionIndexs){
		String[] permStr = new String[permLen];
		int i;
		for(i=0;i<permLen;i++){
			permStr[i] = WebConstant.STRING_ZERO;
		}
		for (Integer permIdx:permissionIndexs){
			permStr[permIdx.intValue()]=WebConstant.STRING_ONE;
		}
		return LwxfStringUtils.arrayJoin(permStr,"");
	}
}
