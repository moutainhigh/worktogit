package com.lwxf.industry4.webapp.bizservice.user;


import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.entity.company.StoreConfig;
import com.lwxf.industry4.webapp.domain.entity.user.UserAttention;
import com.lwxf.mybatis.utils.MapContext;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-09-13 18:07:19
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface UserAttentionService extends BaseService <UserAttention, String> {

	PaginatedList<UserAttention> selectByFilter(PaginatedFilter paginatedFilter);
	Boolean isExistByUserIdAndResourceId(String userId,String resoureceId);
	List<UserAttention> findByUserId(String userId);
	int deleteByUserIdAndResourceId(MapContext mapContext);
	Map<String,Long> isExistByUserIdAndResourceIds(String userId, List<StoreConfig> storeConfigs);
	Map<String,Long> isExistByUserIdAndResIds(String userId, List<?> list,String resId);
	Integer findCountByResIdAndType(String resourceId,Integer type);

}