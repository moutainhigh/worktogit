package com.lwxf.industry4.webapp.bizservice.customorder.impl;


import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderDesignService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.customorder.CustomOrderDesignDao;
import com.lwxf.industry4.webapp.domain.dao.customorder.CustomOrderFilesDao;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderDesignDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderDesign;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
@Service("customOrderDesignService")
public class CustomOrderDesignServiceImpl extends BaseServiceImpl<CustomOrderDesign, String, CustomOrderDesignDao> implements CustomOrderDesignService {


    @Resource

    @Override
    public void setDao(CustomOrderDesignDao customOrderDesignDao) {
        this.dao = customOrderDesignDao;
    }

    @Resource(name = "customOrderFilesDao")
    private CustomOrderFilesDao customOrderFilesDao;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public PaginatedList<CustomOrderDesign> selectByFilter(PaginatedFilter paginatedFilter) {
        //
        return this.dao.selectByFilter(paginatedFilter);
    }

    @Override
    public List<CustomOrderDesignDto> selectByOrderId(String orderId) {
        return this.dao.selectByOrderId(orderId);
    }

    @Override
    public int deleteByOrderId(String orderId) {
        return this.dao.deleteByOrderId(orderId);
    }

    @Override
    public CustomOrderDesignDto findOneByDesignId(String designId) {
        return this.dao.findOneByDesignId(designId);
    }

    @Override
    public List<CustomOrderDesignDto> findListByOrderId(String id) {
        List<CustomOrderDesignDto> listByOrderId = this.dao.findListByOrderId(id);
        for(CustomOrderDesignDto customOrderDesignDto:listByOrderId){
            customOrderDesignDto.setFileList(this.customOrderFilesDao.selectByOrderIdAndType(id, null, customOrderDesignDto.getId()));
        }
        return listByOrderId;
    }

    @Override
    public String findTimeByOrderId(String orderId) {
        return this.dao.findTimeByOrderId(orderId);
    }

    @Override
    public CustomOrderDesign findConfirmedByOrderId(String id) {
        return this.dao.findConfirmedByOrderId(id);
    }

    @Override
    public Map findByOrderIdAndStatus(String orderId, int status) {
        MapContext map = MapContext.newOne();
        map.put("orderId", orderId);
        map.put("status", status);
        return this.dao.findByOrderIdAndStatus(map);
    }


    @Override
    public PaginatedList<CustomOrderDesignDto> findListByFilter(PaginatedFilter paginatedFilter) {
        return this.dao.findListByFilter(paginatedFilter);
    }

    @Override
    public CustomOrderDesignDto findOneByProductId(String orderProductId) {
        return this.dao.findOneByProductId(orderProductId);
    }
}