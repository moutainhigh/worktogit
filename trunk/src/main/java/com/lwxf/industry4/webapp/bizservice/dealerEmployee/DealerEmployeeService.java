package com.lwxf.industry4.webapp.bizservice.dealerEmployee;


import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.entity.dealerEmployee.DealerEmployee;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-12-20 16:35:37
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface DealerEmployeeService extends BaseService <DealerEmployee, String> {

	PaginatedList<DealerEmployee> selectByFilter(PaginatedFilter paginatedFilter);

}