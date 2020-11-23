package com.lwxf.industry4.webapp.domain.dao.dealer;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.dealer.DealerAccountLogDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAccountLog;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-20 18:46:16
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface DealerAccountLogDao extends BaseDao<DealerAccountLog, String> {

	PaginatedList<DealerAccountLog> selectByFilter(PaginatedFilter paginatedFilter);

	PaginatedList<DealerAccountLog> findByCompanyIdAndCondition (PaginatedFilter paginatedFilter);

	DealerAccountLogDto selectByLogId(String logId);


    DealerAccountLog findByOrderIdAndType(MapContext orderIdAndType);
}