package com.lwxf.industry4.webapp.bizservice.system;


import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import com.lwxf.industry4.webapp.domain.entity.system.Menus;
import com.lwxf.mybatis.utils.MapContext;
import java.util.List;

/**
 * 功能：
 * 
 * @author：zhangxiaolin(3965488@qq.com)
 * @created：2019-05-04 10:55:31
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface BasecodeService extends BaseService<Basecode, String> {

	PaginatedList<Basecode> selectByFilter(PaginatedFilter paginatedFilter);

	List<Basecode> selectByFilter(MapContext paginatedFilter);

	List<Basecode> findAll();

	Basecode findByTypeAndValue(String type, String value);

	List<Basecode> findByType(String type);

	Basecode findByTypeAndCode(String typeString, String type);

	List<Basecode> findByTypeAndDelFlag(String type, Integer delFlag);

	Basecode findByTypeAndValueAndDelFlag(MapContext mapContext);
}