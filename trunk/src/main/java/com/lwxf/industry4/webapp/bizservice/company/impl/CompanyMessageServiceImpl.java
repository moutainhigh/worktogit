package com.lwxf.industry4.webapp.bizservice.company.impl;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.company.CompanyMessageService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.company.CompanyMessageDao;
import com.lwxf.industry4.webapp.domain.dto.company.CompanyMessageDto;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import java.util.List;

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
@Service("companyMessageService")
public class CompanyMessageServiceImpl extends BaseServiceImpl<CompanyMessage, String, CompanyMessageDao> implements CompanyMessageService {

	@Resource
	@Override	public void setDao( CompanyMessageDao companyMessageDao) {
		this.dao = companyMessageDao;
	}

	@Override
	public PaginatedList<CompanyMessage> selectByFilter(PaginatedFilter paginatedFilter) {
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public PaginatedList<CompanyMessageDto> selectDtoByFilter(PaginatedFilter paginatedFilter) {
		return this.dao.selectDtoByFilter(paginatedFilter) ;
	}

	@Override
	public PaginatedList<CompanyMessageDto> findMessagesByFromuser(PaginatedFilter paginatedFilter) {
		return this.dao.findMessagesByFromuser(paginatedFilter);
	}

}