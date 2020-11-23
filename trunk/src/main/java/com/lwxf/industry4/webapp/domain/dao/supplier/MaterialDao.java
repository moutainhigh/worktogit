package com.lwxf.industry4.webapp.domain.dao.supplier;


import sun.awt.SunHints;

import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.supplier.MaterialDto;
import com.lwxf.industry4.webapp.domain.entity.supplier.Supplier;
import com.lwxf.mybatis.annotation.Param;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.supplier.Material;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-08-01 11:32:51
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface MaterialDao extends BaseDao<Material, String> {

	PaginatedList<Material> selectByFilter(PaginatedFilter paginatedFilter);

	PaginatedList<MaterialDto> findMaterialList(PaginatedFilter paginatedFilter);

	MaterialDto findMaterialInfoById(String materialId);

	List<Supplier> findSuppliersByMaterialId(String materialId);

	List<MaterialDto> findAllMaterials(MapContext mapparam);

	MaterialDto findByNameAndColorAndLvAndSize(String name, String color, Integer lv, String size);
}