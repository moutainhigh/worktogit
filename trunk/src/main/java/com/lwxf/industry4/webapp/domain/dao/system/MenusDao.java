package com.lwxf.industry4.webapp.domain.dao.system;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.system.Menus;


/**
 * 功能：
 * 
 * @author：dongshibo(zjl869319827@outlook.com)
 * @created：2018-12-07 14:10:47
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface MenusDao extends BaseDao<Menus, String> {

	PaginatedList<Menus> selectByFilter(PaginatedFilter paginatedFilter);

	List<Menus> findAll();

	List<Menus> findListByMapContext(MapContext mapContext);

	Menus findOneByKey(String key);

	List<Menus> findByParentId(String id);

	int deleteRolePermissionByKey(String key);

	int deleteEmployeePermissionByKey(String key);

	int updateNameByLikeKey(String newName, String key);

	List<Menus> findListByLikeKey(String id);

	List<Menus> findAllByTypeAndDisabled(int type, Boolean disabled);
}