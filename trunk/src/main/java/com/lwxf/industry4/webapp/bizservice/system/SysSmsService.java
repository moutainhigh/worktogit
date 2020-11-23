package com.lwxf.industry4.webapp.bizservice.system;

import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.dto.system.SysSmsDto;
import com.lwxf.industry4.webapp.domain.entity.system.SysSms;


/**
 * 功能：
 * 
 * @author：DJL(yuuyoo@163.com)
 * @created：2019-11-20 17:16:51
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface SysSmsService extends BaseService <SysSms, String> {

	Integer count();

	PaginatedList<SysSmsDto> selectDtoByFilter(PaginatedFilter paginatedFilter);

}