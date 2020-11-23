package com.lwxf.industry4.webapp.bizservice.dealer;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAccount;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-20 18:46:16
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface DealerAccountService extends BaseService <DealerAccount, String> {

	PaginatedList<DealerAccount> selectByFilter(PaginatedFilter paginatedFilter);

	List<DealerAccount> findByCompanIdAndNature(String companyId,Integer nature);

	DealerAccount findByCompanIdAndNatureAndType(String companyId,Integer nature,Integer type);

}