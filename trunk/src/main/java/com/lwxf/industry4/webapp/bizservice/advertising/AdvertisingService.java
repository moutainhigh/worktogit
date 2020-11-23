package com.lwxf.industry4.webapp.bizservice.advertising;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.advertising.Advertising;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-03 13:31:23
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface AdvertisingService extends BaseService <Advertising, String> {

	PaginatedList<Advertising> selectByFilter(PaginatedFilter paginatedFilter);

	List<Advertising> findByPosition(Integer position);

}