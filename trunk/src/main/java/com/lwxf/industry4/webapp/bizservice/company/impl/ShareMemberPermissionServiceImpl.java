package com.lwxf.industry4.webapp.bizservice.company.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.company.ShareMemberPermissionDao;
import com.lwxf.industry4.webapp.bizservice.company.ShareMemberPermissionService;
import com.lwxf.industry4.webapp.domain.entity.company.ShareMemberPermission;


/**
 * 功能：
 * 
 * @author：dongshibo(F_baisi@163.com)
 * @created：2018-12-05 11:24:08
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("shareMemberPermissionService")
public class ShareMemberPermissionServiceImpl extends BaseServiceImpl<ShareMemberPermission, String, ShareMemberPermissionDao> implements ShareMemberPermissionService {


	@Resource

	@Override	public void setDao( ShareMemberPermissionDao shareMemberPermissionDao) {
		this.dao = shareMemberPermissionDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ShareMemberPermission> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

}