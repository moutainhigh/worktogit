package com.lwxf.industry4.webapp.bizservice.user.impl;


import java.util.List;
import java.util.Map;


import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.entity.company.StoreConfig;
import com.lwxf.industry4.webapp.bizservice.user.UserAttentionService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.user.UserAttentionDao;
import com.lwxf.industry4.webapp.bizservice.user.UserAttentionService;
import com.lwxf.industry4.webapp.domain.entity.user.UserAttention;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-09-13 18:07:19
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("userAttentionService")
public class UserAttentionServiceImpl extends BaseServiceImpl<UserAttention, String, UserAttentionDao> implements UserAttentionService {


	@Resource

	@Override	public void setDao( UserAttentionDao userAttentionDao) {
		this.dao = userAttentionDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<UserAttention> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public Boolean isExistByUserIdAndResourceId(String userId, String companyId) {
		return this.dao.isExistByUserIdAndResourceId(userId,companyId);
	}

	@Override
	public List<UserAttention> findByUserId(String userId) {
		return this.dao.findByUserId(userId);
	}

	@Override
	public int deleteByUserIdAndResourceId(MapContext mapContext) {
		return this.dao.deleteByUserIdAndResourceId(mapContext);
	}

	@Override
	public Map<String,Long> isExistByUserIdAndResourceIds(String userId,List<StoreConfig> storeConfigs) {
		return this.dao.isExistByUserIdAndResourceIds(userId,storeConfigs);
	}

	@Override
	public Map<String, Long> isExistByUserIdAndResIds(String userId, List<?> list, String resId) {
		return this.dao.isExistByUserIdAndResIds(userId,list,resId);
	}

	@Override
	public Integer findCountByResIdAndType(String resourceId, Integer type) {
		return this.dao.findCountByResIdAndType(resourceId,type);
	}
}