package com.lwxf.industry4.webapp.bizservice.warehouse.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.warehouse.StorageOutputInItemDao;
import com.lwxf.industry4.webapp.domain.dto.warehouse.StorageOutputInDto;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.warehouse.StorageOutputInDao;
import com.lwxf.industry4.webapp.bizservice.warehouse.StorageOutputInService;
import com.lwxf.industry4.webapp.domain.entity.warehouse.StorageOutputIn;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-20 10:16:05
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("storageOutputInService")
public class StorageOutputInServiceImpl extends BaseServiceImpl<StorageOutputIn, String, StorageOutputInDao> implements StorageOutputInService {


	@Resource

	@Override	public void setDao( StorageOutputInDao storageOutputInDao) {
		this.dao = storageOutputInDao;
	}

	@Resource(name = "storageOutputInItemDao")
	private StorageOutputInItemDao storageOutputInItemDao;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<StorageOutputIn> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public PaginatedList<StorageOutputInDto> findListByPaginateFilter(PaginatedFilter paginatedFilter) {
		PaginatedList<StorageOutputInDto> paginatedList= this.dao.findListByPaginateFilter(paginatedFilter);
		for(StorageOutputInDto storageOutputInDto:paginatedList.getRows()){
			storageOutputInDto.setStorageOutputInItemList(this.storageOutputInItemDao.findListByStorageOutputInId(storageOutputInDto.getId()));
		}
		return paginatedList;
	}

	@Override
	public StorageOutputIn findOneByNo(String no,String branchId) {
		return this.dao.findOneByNo(no,branchId);
	}

}