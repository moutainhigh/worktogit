package com.lwxf.industry4.webapp.domain.dao.company;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.company.OutsourcingFactory;


/**
 * 功能：
 * 
 * @author：dongshibo(F_baisi@163.com)
 * @created：2019-06-01 17:02:04
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface OutsourcingFactoryDao extends BaseDao<OutsourcingFactory, String> {

	PaginatedList<OutsourcingFactory> selectByFilter(PaginatedFilter paginatedFilter);

}