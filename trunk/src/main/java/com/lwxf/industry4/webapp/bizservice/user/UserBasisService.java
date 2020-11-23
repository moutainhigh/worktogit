package com.lwxf.industry4.webapp.bizservice.user;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.user.UserBasis;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-20 18:56:02
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface UserBasisService extends BaseService <UserBasis, String> {

	PaginatedList<UserBasis> selectByFilter(PaginatedFilter paginatedFilter);

	UserBasis findByUserId(String userId);
}