package com.lwxf.industry4.webapp.bizservice.supplier.impl;


import javax.annotation.Resource;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.supplier.MaterialService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
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
@Service("materialService")
public class MaterialServiceImpl extends BaseServiceImpl<Material, String, MaterialDao> implements MaterialService {


	@Resource

	@Override	public void setDao( MaterialDao materialDao) {
		this.dao = materialDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<Material> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public PaginatedList<MaterialDto> findMaterialList(PaginatedFilter paginatedFilter) {
		return this.dao.findMaterialList(paginatedFilter);
	}

	@Override
	public MaterialDto findMaterialInfoById(String materialId) {
		return this.dao.findMaterialInfoById(materialId);
	}

	@Override
	public List<Supplier> findSuppliersByMaterialId(String materialId) {
		return this.dao.findSuppliersByMaterialId(materialId);
	}

	@Override
	public List<MaterialDto> findAllMaterials(MapContext mapparam) {
		return this.dao.findAllMaterials(mapparam);
	}

	@Override
	public MaterialDto findByNameAndColorAndLvAndSize(String name, String color, Integer lv, String size) {
		return this.dao.findByNameAndColorAndLvAndSize(name,color,lv,size);
	}

}