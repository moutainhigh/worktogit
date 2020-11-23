package com.lwxf.industry4.webapp.bizservice.warehouse.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.bizservice.warehouse.FinishedStockItemService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.storage.FinishedStockStatus;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockItemDto;
import com.lwxf.industry4.webapp.domain.entity.warehouse.FinishedStockItem;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.warehouse.FinishedStockDao;
import com.lwxf.industry4.webapp.bizservice.warehouse.FinishedStockService;
import com.lwxf.industry4.webapp.domain.entity.warehouse.FinishedStock;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-17 11:00:40
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("finishedStockService")
public class FinishedStockServiceImpl extends BaseServiceImpl<FinishedStock, String, FinishedStockDao> implements FinishedStockService {


	@Resource

	@Override	public void setDao( FinishedStockDao finishedStockDao) {
		this.dao = finishedStockDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<FinishedStockDto> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		PaginatedList<FinishedStockDto> paginatedList = this.dao.selectByFilter(paginatedFilter);
		for(FinishedStockDto finishedStockDto:paginatedList.getRows()){
			finishedStockDto.setFinishedStockItemDtos(this.dao.findListByFinishedStockId(finishedStockDto.getId()));
		}
		return paginatedList;
	}

	@Override
	public List<FinishedStockItemDto> findListByFinishedStockId(String id) {
		return this.dao.findListByFinishedStockId(id);
	}

	@Override
	public int updatePackagesById(String id) {
		MapContext mapContext = MapContext.newOne();
		mapContext.put("packages",this.dao.findListByFinishedStockId(id).size());
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		return this.updateByMapContext(mapContext);
	}

	@Override
	public FinishedStockDto findOneById(String id) {
		FinishedStockDto stockDto = this.dao.findOneById(id);
		stockDto.setFinishedStockItemDtos(this.dao.findListByFinishedStockId(id));
		return stockDto;
	}


	@Override
	public List<FinishedStockItem> findUnshippedListById(String id) {
		return this.dao.findUnshippedListById(id);
	}

	@Override
	public FinishedStock findByOrderId(String orderId) {
		return this.dao.findByOrderId(orderId);
	}

	@Override
	public List<FinishedStock> findListByStorageId(String id) {
		return this.dao.findListByStorageId(id);
	}

	@Override
	public List<FinishedStock> findListByOrderId(String orderId) {
		return this.dao.findListByOrderId(orderId);
	}

	@Override
	public PaginatedList<MapContext> findDispathcBillList(PaginatedFilter paginatedFilter) {

		return this.dao.findDispathcBillList(paginatedFilter);
	}

	@Override
	public int deleteByOrderId(String orderId) {
		return this.dao.deleteByOrderId(orderId);
	}

	@Override
	public List<FinishedStockDto> findWxFinishedList(String orderId) {
		List<FinishedStockDto> finishedStockDtoList=this.dao.findWxFinishedList(orderId);
		for(FinishedStockDto finishedStockDto:finishedStockDtoList){
			finishedStockDto.setFinishedStockItemDtos(this.dao.findListByFinishedStockId(finishedStockDto.getId()));
		}
		return finishedStockDtoList;
	}

	@Override
	public MapContext findCountByBranchId(String branchId) {
		return this.dao.findCountByBranchId(branchId);
	}

	@Override
	public Map findMapByOrderId(String orderId) {
		return this.dao.findMapByOrderId(orderId);
	}
}