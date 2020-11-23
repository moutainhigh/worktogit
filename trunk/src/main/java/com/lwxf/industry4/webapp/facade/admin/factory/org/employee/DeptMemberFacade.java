package com.lwxf.industry4.webapp.facade.admin.factory.org.employee;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/11/011 11:37
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface DeptMemberFacade extends BaseFacade {
	RequestResult findDeptList(String deptId, MapContext mapContext,Integer pageNum,Integer pageSize);

	RequestResult addDeptMember(MapContext mapContext,String deptIds,StringBuffer pwd);

	RequestResult deleteDeptMember(String id, String eid);
	//RequestResult
}
