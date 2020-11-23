package com.lwxf.industry4.webapp.bizservice.design;


import java.util.List;
import java.util.Map;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.design.DesignSchemeDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.design.DesignScheme;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-03 15:53:46
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface DesignSchemeService extends BaseService <DesignScheme, String> {

	PaginatedList<DesignScheme> selectByFilter(PaginatedFilter paginatedFilter);
	PaginatedList<DesignSchemeDto> selectByCondition(PaginatedFilter paginatedFilter);

	Integer findCountByDesigner (String designer);

	PaginatedList<DesignSchemeDto> findListByFilter(PaginatedFilter paginatedFilter);

	DesignSchemeDto findOneById(String id);

	List<Map<String,String>>findStyleAndCountByDesigner(String designer);

	List<DesignSchemeDto> findByDesignerAndStatus(MapContext map);

	Map<String,String> selectBySchemeId(String id);

	PaginatedList<Map> findListBydesigner(PaginatedFilter filter);

    PaginatedList<Map> findList(PaginatedFilter filter);
}