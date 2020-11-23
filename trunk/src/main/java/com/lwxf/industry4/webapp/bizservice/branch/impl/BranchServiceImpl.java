package com.lwxf.industry4.webapp.bizservice.branch.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.branch.BranchDto;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.branch.BranchDao;
import com.lwxf.industry4.webapp.bizservice.branch.BranchService;
import com.lwxf.industry4.webapp.domain.entity.branch.Branch;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@126.com)
 * @created：2019-06-05 10:33:25
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("branchService")
public class BranchServiceImpl extends BaseServiceImpl<Branch, String, BranchDao> implements BranchService {


	@Resource

	@Override	public void setDao( BranchDao branchDao) {
		this.dao = branchDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<Branch> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public PaginatedList<BranchDto> findListByFilter(PaginatedFilter paginatedFilter) {
		return this.dao.findListByFilter(paginatedFilter);
	}

	@Override
	public BranchDto findCurrentBranch() {
		return this.dao.findCurrentBranch();
	}

}