package com.lwxf.industry4.webapp.bizservice.company;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyMemberOrder;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2020-03-24 14:53:39
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface CompanyMemberOrderService extends BaseService <CompanyMemberOrder, String> {

	PaginatedList<CompanyMemberOrder> selectByFilter(PaginatedFilter paginatedFilter);

    CompanyMemberOrder findByOrderIdAndType(String id, int value);
}