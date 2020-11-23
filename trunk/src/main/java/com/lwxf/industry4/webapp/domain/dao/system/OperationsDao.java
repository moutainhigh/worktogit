package com.lwxf.industry4.webapp.domain.dao.system;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.system.Operations;


/**
 * 功能：
 * 
 * @author：dongshibo(zjl869319827@outlook.com)
 * @created：2018-12-07 14:10:48
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface OperationsDao extends BaseDao<Operations, String> {

	PaginatedList<Operations> selectByFilter(PaginatedFilter paginatedFilter);

	List<Operations> findAll();

	List<Operations> findListByMapContext(MapContext mapContext);

	Operations findOneByName(String name);

	Operations findOneByKey(String key);

	List<Operations> findAllByTypes(List<Integer> types);
}