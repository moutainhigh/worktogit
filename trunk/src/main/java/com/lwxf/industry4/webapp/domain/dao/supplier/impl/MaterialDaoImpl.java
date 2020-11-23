package com.lwxf.industry4.webapp.domain.dao.supplier.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.supplier.MaterialDao;
import com.lwxf.industry4.webapp.domain.dto.supplier.MaterialDto;
import com.lwxf.industry4.webapp.domain.entity.supplier.Material;
import com.lwxf.industry4.webapp.domain.entity.supplier.Supplier;
import com.lwxf.mybatis.utils.MapContext;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-08-01 11:32:51
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("materialDao")
public class MaterialDaoImpl extends BaseDaoImpl<Material, String> implements MaterialDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<Material> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<Material> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public PaginatedList<MaterialDto> findMaterialList(PaginatedFilter paginatedFilter) {
		String sqlId=this.getNamedSqlId("findMaterialList");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<MaterialDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public MaterialDto findMaterialInfoById(String materialId) {
		String sqlId=this.getNamedSqlId("findMaterialInfoById");
		return this.getSqlSession().selectOne(sqlId,materialId);
	}

	@Override
	public List<Supplier> findSuppliersByMaterialId(String materialId) {
		String sqlId=this.getNamedSqlId("findSuppliersByMaterialId");
		return this.getSqlSession().selectList(sqlId,materialId);
	}

	@Override
	public List<MaterialDto> findAllMaterials(MapContext mapparam) {
		String sqlId=this.getNamedSqlId("findAllMaterials");
		return this.getSqlSession().selectList(sqlId,mapparam);
	}

	@Override
	public MaterialDto findByNameAndColorAndLvAndSize(String name, String color, Integer lv, String size) {
		String sqlId=this.getNamedSqlId("findByNameAndColorAndLvAndSize");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("name",name);
		mapContext.put("color",color);
		mapContext.put("materialLevel",lv);
		mapContext.put("materialSize",size);
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

}