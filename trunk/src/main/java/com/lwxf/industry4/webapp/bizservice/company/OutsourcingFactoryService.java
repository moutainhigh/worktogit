package com.lwxf.industry4.webapp.bizservice.company;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.company.OutsourcingFactory;


/**
 * 功能：
 * 
 * @author：dongshibo(F_baisi@163.com)
 * @created：2019-06-01 17:02:04
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface OutsourcingFactoryService extends BaseService <OutsourcingFactory, String> {

	PaginatedList<OutsourcingFactory> selectByFilter(PaginatedFilter paginatedFilter);

}