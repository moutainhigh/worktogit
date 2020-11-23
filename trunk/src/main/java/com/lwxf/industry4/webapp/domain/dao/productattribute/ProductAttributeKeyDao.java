package com.lwxf.industry4.webapp.domain.dao.productattribute;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.dto.productattribute.ProductAttributeKeyDto;
import com.lwxf.industry4.webapp.domain.entity.productattribute.ProductAttributeKey;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.mybatis.utils.MapContext;

import java.util.List;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2020-07-29 10:28:04
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface ProductAttributeKeyDao extends BaseDao<ProductAttributeKey, String> {

	PaginatedList<ProductAttributeKey> selectByFilter(PaginatedFilter paginatedFilter);

    List<ProductAttributeKeyDto> findListBybranchId(MapContext mapContext);
}