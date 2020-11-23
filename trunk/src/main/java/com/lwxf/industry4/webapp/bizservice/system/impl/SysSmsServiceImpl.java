package com.lwxf.industry4.webapp.bizservice.system.impl;

import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.system.SysSmsDto;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.system.SysSmsDao;
import com.lwxf.industry4.webapp.bizservice.system.SysSmsService;
import com.lwxf.industry4.webapp.domain.entity.system.SysSms;


/**
 * 功能：
 * 
 * @author：DJL(yuuyoo@163.com)
 * @created：2019-11-20 17:16:51
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("sysSmsService")
public class SysSmsServiceImpl extends BaseServiceImpl<SysSms, String, SysSmsDao> implements SysSmsService {


	@Resource

	@Override	public void setDao( SysSmsDao sysSmsDao) {
		this.dao = sysSmsDao;
	}


	@Override
	public Integer count() {
		return this.dao.count();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<SysSmsDto> selectDtoByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectDtoByFilter(paginatedFilter) ;
	}

}