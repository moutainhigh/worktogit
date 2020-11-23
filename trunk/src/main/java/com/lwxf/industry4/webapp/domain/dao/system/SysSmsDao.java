package com.lwxf.industry4.webapp.domain.dao.system;

import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.system.SysSmsDto;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.system.SysSms;


/**
 * 功能：
 * 
 * @author：DJL(yuuyoo@163.com)
 * @created：2019-11-20 17:16:51
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface SysSmsDao extends BaseDao<SysSms, String> {

	Integer count();

	PaginatedList<SysSmsDto> selectDtoByFilter(PaginatedFilter paginatedFilter);

}