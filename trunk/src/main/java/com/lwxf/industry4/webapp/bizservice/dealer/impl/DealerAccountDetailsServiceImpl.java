package com.lwxf.industry4.webapp.bizservice.dealer.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.dealer.DealerAccountDetailsDao;
import com.lwxf.industry4.webapp.bizservice.dealer.DealerAccountDetailsService;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAccountDetails;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-24 18:25:22
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("dealerAccountDetailsService")
public class DealerAccountDetailsServiceImpl extends BaseServiceImpl<DealerAccountDetails, String, DealerAccountDetailsDao> implements DealerAccountDetailsService {


	@Resource

	@Override	public void setDao( DealerAccountDetailsDao dealerAccountDetailsDao) {
		this.dao = dealerAccountDetailsDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DealerAccountDetails> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<DealerAccountDetails> findByAccountId(String accountId) {
		return this.dao.findByAccountId(accountId);
	}
}