package com.lwxf.industry4.webapp.domain.dao.design;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.design.SchemeContentDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.design.SchemeContent;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-03-15 10:08:11
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface SchemeContentDao extends BaseDao<SchemeContent, String> {

	PaginatedList<SchemeContent> selectByFilter(PaginatedFilter paginatedFilter);

    List<SchemeContentDto> findBySchemeId(String id);
}