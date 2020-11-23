package com.lwxf.industry4.webapp.domain.dao.jdproduct;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.jdproduct.JdProductInfo;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;

import java.util.Date;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-12-02 16:50:47
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface JdProductInfoDao extends BaseDao<JdProductInfo, String> {

	PaginatedList<JdProductInfo> selectByFilter(PaginatedFilter paginatedFilter);

	int deleteByFetchingTime(Date fetchingTime);
}