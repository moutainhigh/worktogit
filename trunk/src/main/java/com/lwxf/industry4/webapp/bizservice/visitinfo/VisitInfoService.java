package com.lwxf.industry4.webapp.bizservice.visitinfo;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.visitinfo.VisitInfo;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-11-26 15:32:34
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface VisitInfoService extends BaseService <VisitInfo, String> {

	PaginatedList<VisitInfo> selectByFilter(PaginatedFilter paginatedFilter);

	int insertVisitInfo( VisitInfo visitInfo);

}