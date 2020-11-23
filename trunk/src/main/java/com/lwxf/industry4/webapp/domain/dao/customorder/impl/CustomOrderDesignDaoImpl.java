package com.lwxf.industry4.webapp.domain.dao.customorder.impl;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.customorder.CustomOrderDesignDao;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderDesignDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderDesign;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-04 17:48:28
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("customOrderDesignDao")
public class CustomOrderDesignDaoImpl extends BaseDaoImpl<CustomOrderDesign, String> implements CustomOrderDesignDao {

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public PaginatedList<CustomOrderDesign> selectByFilter(PaginatedFilter paginatedFilter) {
        String sqlId = this.getNamedSqlId("selectByFilter");
        //
        //  过滤查询参数
        PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
        PageList<CustomOrderDesign> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
        return this.toPaginatedList(pageList);
    }


    @Override
    public List<CustomOrderDesignDto> selectByOrderId(String orderId) {
        String sqlId = this.getNamedSqlId("selectByOrderId");
        return this.getSqlSession().selectList(sqlId, orderId);
    }

    @Override
    public int deleteByOrderId(String orderId) {
        String sqlId = this.getNamedSqlId("deleteByOrderId");
        return this.getSqlSession().delete(sqlId, orderId);
    }

    @Override
    public CustomOrderDesignDto findOneByDesignId(String designId) {
        String sqlId = this.getNamedSqlId("findOneByDesignId");
        return this.getSqlSession().selectOne(sqlId, designId);
    }

    @Override
    public List<CustomOrderDesignDto> findListByOrderId(String id) {
        String sqlId = this.getNamedSqlId("findListByOrderId");
        return this.getSqlSession().selectList(sqlId, id);
    }

    @Override
    public String findTimeByOrderId(String orderId) {
        String sqlId = this.getNamedSqlId("findTimeByOrderId");
        return this.getSqlSession().selectOne(sqlId, orderId);
    }

    @Override
    public CustomOrderDesign findConfirmedByOrderId(String id) {
        String sqlId = this.getNamedSqlId("findConfirmedByOrderId");
        return this.getSqlSession().selectOne(sqlId, id);
    }

    @Override
    public Map findByOrderIdAndStatus(MapContext map) {
        String sqlId = this.getNamedSqlId("findByOrderIdAndStatus");
        return this.getSqlSession().selectOne(sqlId, map);
    }



    @Override
    public PaginatedList<CustomOrderDesignDto> findListByFilter(PaginatedFilter paginatedFilter) {
        String sqlId = this.getNamedSqlId("findListByFilter");
        //
        //  过滤查询参数
        PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
        PageList<CustomOrderDesignDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
        return this.toPaginatedList(pageList);
    }

    @Override
    public CustomOrderDesignDto findOneByProductId(String orderProductId) {
        String sqlId = this.getNamedSqlId("findOneByProductId");
        return this.getSqlSession().selectOne(sqlId, orderProductId);
    }
}