package com.lwxf.industry4.webapp.domain.dao.evaluate;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.evaluate.EvaluateInfoFiles;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-11-29 16:35:04
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface EvaluateInfoFilesDao extends BaseDao<EvaluateInfoFiles, String> {

	PaginatedList<EvaluateInfoFiles> selectByFilter(PaginatedFilter paginatedFilter);

}