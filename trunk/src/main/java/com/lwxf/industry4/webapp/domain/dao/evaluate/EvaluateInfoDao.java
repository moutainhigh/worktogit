package com.lwxf.industry4.webapp.domain.dao.evaluate;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.evaluate.EvaluateInfo;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-11-29 14:39:15
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface EvaluateInfoDao extends BaseDao<EvaluateInfo, String> {

	PaginatedList<EvaluateInfo> selectByFilter(PaginatedFilter paginatedFilter);

}