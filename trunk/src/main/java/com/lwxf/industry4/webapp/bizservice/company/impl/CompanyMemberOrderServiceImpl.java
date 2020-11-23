package com.lwxf.industry4.webapp.bizservice.company.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.company.CompanyMemberOrderDao;
import com.lwxf.industry4.webapp.bizservice.company.CompanyMemberOrderService;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyMemberOrder;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2020-03-24 14:53:39
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("companyMemberOrderService")
public class CompanyMemberOrderServiceImpl extends BaseServiceImpl<CompanyMemberOrder, String, CompanyMemberOrderDao> implements CompanyMemberOrderService {


	@Resource

	@Override	public void setDao( CompanyMemberOrderDao companyMemberOrderDao) {
		this.dao = companyMemberOrderDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CompanyMemberOrder> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public CompanyMemberOrder findByOrderIdAndType(String id, int value) {
		return this.dao.findByOrderIdAndType(id,value);
	}

}