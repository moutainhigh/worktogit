package com.lwxf.industry4.webapp.facade.branch;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.branch.Branch;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/6/5/005 9:50
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface BranchFacade extends BaseFacade {
	RequestResult findBranchList(MapContext mapContext,Integer pageNum,Integer pageSize);

	RequestResult addBrandInfo(Branch branch);

	RequestResult branchValidityEncryption(String branchId,MapContext branch);

	RequestResult updateBranch(MapContext mapContext, String id);

	RequestResult findCurrentBranch();

	RequestResult enableRemind(String branchId);

	RequestResult getExpireMessage();

	RequestResult toExaminePlan(MapContext mapContext);
}
