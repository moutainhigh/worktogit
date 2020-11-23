package com.lwxf.industry4.webapp.domain.dao.visitinfo;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.visitinfo.VisitInfo;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-11-26 15:32:34
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface VisitInfoDao extends BaseDao<VisitInfo, String> {

	PaginatedList<VisitInfo> selectByFilter(PaginatedFilter paginatedFilter);

}