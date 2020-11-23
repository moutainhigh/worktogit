package com.lwxf.industry4.webapp.domain.dao.dealer;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAccountDetails;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-24 18:25:22
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface DealerAccountDetailsDao extends BaseDao<DealerAccountDetails, String> {

	PaginatedList<DealerAccountDetails> selectByFilter(PaginatedFilter paginatedFilter);

	List<DealerAccountDetails> findByAccountId(String accountId);

}