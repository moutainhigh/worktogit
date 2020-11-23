package com.lwxf.industry4.webapp.facade.smallProgram.smallProgramDept;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.org.Dept;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/10/19 0019 9:17
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface SpDeptFacade extends BaseFacade {
	RequestResult findDeptMemberList(String branchId);

	RequestResult addDeptMember(MapContext mapContext, StringBuffer pwd);

	RequestResult addDept(Dept dept);

	RequestResult moveDeptMember(MapContext mapContext);

	RequestResult deleteDept(String ids);
}
