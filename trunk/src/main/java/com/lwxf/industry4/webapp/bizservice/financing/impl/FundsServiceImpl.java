package com.lwxf.industry4.webapp.bizservice.financing.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.financing.FundsDto;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.financing.FundsDao;
import com.lwxf.industry4.webapp.bizservice.financing.FundsService;
import com.lwxf.industry4.webapp.domain.entity.financing.Funds;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-09-25 09:25:44
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("fundsService")
public class FundsServiceImpl extends BaseServiceImpl<Funds, String, FundsDao> implements FundsService {


	@Resource

	@Override	public void setDao( FundsDao fundsDao) {
		this.dao = fundsDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<Funds> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<FundsDto> findFundsList(MapContext mapContext) {
		return this.dao.findFundsList(mapContext);
	}

	@Override
	public List<FundsDto> findByParentId(String id) {
		return this.dao.findByParentId(id);
	}

}