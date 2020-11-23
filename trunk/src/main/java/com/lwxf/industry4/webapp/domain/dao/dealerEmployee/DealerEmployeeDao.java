package com.lwxf.industry4.webapp.domain.dao.dealerEmployee;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.dealerEmployee.DealerEmployee;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-12-20 16:35:37
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface DealerEmployeeDao extends BaseDao<DealerEmployee, String> {

	PaginatedList<DealerEmployee> selectByFilter(PaginatedFilter paginatedFilter);

}