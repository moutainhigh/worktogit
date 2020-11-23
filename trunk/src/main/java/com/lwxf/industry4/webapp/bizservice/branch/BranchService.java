package com.lwxf.industry4.webapp.bizservice.branch;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.branch.BranchDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.branch.Branch;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@126.com)
 * @created：2019-06-05 10:33:25
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface BranchService extends BaseService <Branch, String> {

	PaginatedList<Branch> selectByFilter(PaginatedFilter paginatedFilter);

	PaginatedList<BranchDto> findListByFilter(PaginatedFilter paginatedFilter);

    BranchDto findCurrentBranch();

}