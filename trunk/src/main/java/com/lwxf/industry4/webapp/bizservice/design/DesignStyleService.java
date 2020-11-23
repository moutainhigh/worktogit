package com.lwxf.industry4.webapp.bizservice.design;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.design.DesignStyle;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-03 15:53:46
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface DesignStyleService extends BaseService <DesignStyle, String> {

	PaginatedList<DesignStyle> selectByFilter(PaginatedFilter paginatedFilter);

	List<DesignStyle> findAllDesignStyle();

	List<DesignStyle> findListByFilter(MapContext mapContext);

	DesignStyle selectOneByName(String name,String branchId);
}