package com.lwxf.industry4.webapp.bizservice.dealerEmployee.impl;


import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.dealerEmployee.DealerEmployeeService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.dealerEmployee.DealerEmployeeDao;
import com.lwxf.industry4.webapp.domain.entity.dealerEmployee.DealerEmployee;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-12-20 16:35:37
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("dealerEmployeeService")
public class DealerEmployeeServiceImpl extends BaseServiceImpl<DealerEmployee, String, DealerEmployeeDao> implements DealerEmployeeService {



	@Resource

	@Override	public void setDao( DealerEmployeeDao dealerEmployeeDao) {
		this.dao = dealerEmployeeDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DealerEmployee> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}



}