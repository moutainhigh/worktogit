package com.lwxf.industry4.webapp.bizservice.dealer.impl;


import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.dealer.DealerAccountService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.dealer.DealerAccountDao;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAccount;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-20 18:46:16
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("dealerAccountService")
public class DealerAccountServiceImpl extends BaseServiceImpl<DealerAccount, String, DealerAccountDao> implements DealerAccountService {


	@Resource

	@Override	public void setDao( DealerAccountDao dealerAccountDao) {
		this.dao = dealerAccountDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DealerAccount> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<DealerAccount> findByCompanIdAndNature(String companyId, Integer nature) {
		return this.dao.findByCompanIdAndNature(companyId, nature);
	}

	@Override
	public DealerAccount findByCompanIdAndNatureAndType(String companyId, Integer nature, Integer type) {
		return this.dao.findByCompanIdAndNatureAndType(companyId, nature, type);
	}
}