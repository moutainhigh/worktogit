package com.lwxf.industry4.webapp.domain.dao.visitinfo;




import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.visitinfo.VisitInfoFiles;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-11-26 11:42:07
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface VisitInfoFilesDao extends BaseDao<VisitInfoFiles, String> {

	PaginatedList<VisitInfoFiles> selectByFilter(PaginatedFilter paginatedFilter);

}