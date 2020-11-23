package com.lwxf.industry4.webapp.domain.dao.company;


import java.util.List;

import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.company.CompanyMessageDto;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyMessage;
import com.lwxf.mybatis.utils.MapContext;


/**
 * 功能：
 * 
 * @author：3965488@qq.com
 * @created：2019-06-14 16:34:59
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface CompanyMessageDao extends BaseDao<CompanyMessage, String> {

	PaginatedList<CompanyMessage> selectByFilter(PaginatedFilter paginatedFilter);

	PaginatedList<CompanyMessageDto> selectDtoByFilter(PaginatedFilter paginatedFilter);

	PaginatedList<CompanyMessageDto> findMessagesByFromuser(PaginatedFilter paginatedFilter);
}