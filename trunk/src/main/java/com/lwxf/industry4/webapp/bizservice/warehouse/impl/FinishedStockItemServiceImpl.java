package com.lwxf.industry4.webapp.bizservice.warehouse.impl;


import javax.annotation.Resource;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lwxf.industry4.webapp.common.enums.storage.FinishedStockStatus;
import org.springframework.stereotype.Service;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderProcessService;
import com.lwxf.industry4.webapp.bizservice.warehouse.FinishedStockItemService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.customorder.CustomOrderFilesType;
import com.lwxf.industry4.webapp.common.enums.order.CustomOrderProcessStatus;
import com.lwxf.industry4.webapp.common.enums.order.CustomOrderProcessType;
import com.lwxf.industry4.webapp.common.enums.order.OrderStatus;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dao.customorder.CustomOrderDao;
import com.lwxf.industry4.webapp.domain.dao.customorder.CustomOrderFilesDao;
import com.lwxf.industry4.webapp.domain.dao.customorder.CustomOrderProcessDao;
import com.lwxf.industry4.webapp.domain.dao.warehouse.FinishedStockItemDao;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockItemDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderProcess;
import com.lwxf.industry4.webapp.domain.entity.warehouse.FinishedStock;
import com.lwxf.industry4.webapp.domain.entity.warehouse.FinishedStockItem;
import com.lwxf.mybatis.utils.MapContext;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-17 11:00:40
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("finishedStockItemService")
public class FinishedStockItemServiceImpl extends BaseServiceImpl<FinishedStockItem, String, FinishedStockItemDao> implements FinishedStockItemService {


	@Resource

	@Override	public void setDao( FinishedStockItemDao finishedStockItemDao) {
		this.dao = finishedStockItemDao;
	}

	@Resource(name = "customOrderDao")
	private CustomOrderDao customOrderDao;

	@Resource(name = "customOrderProcessDao")
	private CustomOrderProcessDao customOrderProcessDao;

	@Resource(name = "customOrderProcessService")
	private CustomOrderProcessService customOrderProcessService;

	@Resource(name = "customOrderFilesDao")
	private CustomOrderFilesDao customOrderFilesDao;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<FinishedStockItem> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}


	@Override
	public List<FinishedStockItem> findByDispatchId(String dispatchId) {
		return this.dao.findByDispatchId(dispatchId);
	}

	@Override
	public List<FinishedStockItem> findListByBarcodes(Set set) {
		return this.dao.findListByBarcodes(set);
	}

	@Override
	public FinishedStockItemDto findOneById(String id) {
		return this.dao.findOneById(id);
	}

	@Override
	public int deleteByFinishedStockId(String id) {
		return this.dao.deleteByFinishedStockId(id);
	}

	@Override
	public int updateOrderStatusByFinishedStock(FinishedStock finishedStock) {
		List<FinishedStockItem> finishedStockItems = this.dao.findListByInAndId(1,finishedStock.getId());
		//判断此时是否成品库单下已存在入库的条目 不存在 则代表第一次入库
		if(finishedStockItems!=null&&finishedStockItems.size()==0){
//			//创建订单生产信息表 当头一条条目入库时 修改待入库状态为完成 新建已入库状态
//			CustomOrderProcess customOrderProcess = new CustomOrderProcess();
//			customOrderProcess.setBeginTime(DateUtil.getSystemDate());
//			customOrderProcess.setOperator(WebUtils.getCurrUserId());
//			customOrderProcess.setOrderId(finishedStock.getOrderId());
//			customOrderProcess.setType(CustomOrderProcessType.WAREHOUSING.getValue());
//			customOrderProcess.setStatus(CustomOrderProcessStatus.HAVE_IN_HAND.getValue());
//			this.customOrderProcessService.add(customOrderProcess);
			//修改上一步订单信息表信息
//			this.customOrderProcessService.updateStatusByOrderIdAndType(finishedStock.getOrderId(),CustomOrderProcessType.PENDING_STORAGE.getValue());
			//已出现入库条目 因此 修改订单信息为待配送
			MapContext mapContext = MapContext.newOne();
			mapContext.put(WebConstant.KEY_ENTITY_ID,finishedStock.getOrderId());
			mapContext.put(WebConstant.KEY_ENTITY_STATUS,OrderStatus.TO_SHIPPED.getValue());
			this.customOrderDao.updateByMapContext(mapContext);
			//如果成品库单的总包数和已入库的条目数差为1 则修改已入库状态为完成
//			if(finishedStock.getPackages()-finishedStockItems.size()==1){
//				//当全部入库时 修改已入库状态为完成
//				this.customOrderProcessDao.updateStatusByOrderIdAndType(finishedStock.getOrderId(),CustomOrderProcessType.WAREHOUSING.getValue());
//			}
			return 1;
//		}else if(finishedStockItems!=null&&finishedStockItems.size()!=0){
			//当成品库下条目入库数不为0 的时候 判断是否成品库单的总包数和已入库的条目数差为1 则修改已入库状态为完成
//			if(finishedStock.getPackages()-finishedStockItems.size()==1){
//				//当全部入库时 修改已入库状态为完成
//				this.customOrderProcessDao.updateStatusByOrderIdAndType(finishedStock.getOrderId(),CustomOrderProcessType.WAREHOUSING.getValue());
//			}
//			return 1;
		}
		return 0;
	}

	@Override
	public Integer findByFinishedStockId(MapContext params) {
		return this.dao.findByFinishedStockId(params);
	}

	@Override
	public List<FinishedStockItemDto> findListByFinishedStockId(String finishedStockId) {
		return this.dao.findListByFinishedStockId(finishedStockId);
	}


	@Override
	public List<Map> findBydispatchBillId(String dispatchId) {
		return this.dao.findBydispatchBillId(dispatchId);
	}

	@Override
	public List<FinishedStockItemDto> findByOrderIdAndType(MapContext orderIdAndType) {
		return this.dao.findByOrderIdAndType(orderIdAndType);
	}

	@Override
	public PaginatedList<FinishedStockItemDto> findListByFilter(PaginatedFilter paginatedFilter) {
		PaginatedList<FinishedStockItemDto> listByFilter = this.dao.findListByFilter(paginatedFilter);
		for(FinishedStockItemDto finishedStockItemDto :listByFilter.getRows()){
			finishedStockItemDto.setFileList(this.customOrderFilesDao.selectByOrderIdAndType(finishedStockItemDto.getOrderId(),CustomOrderFilesType.ORDER_PACKAGE.getValue(),finishedStockItemDto.getId()));
		}
		return listByFilter;
	}

	@Override
	public int updateShippedByIds(List itemIds) {
		return this.dao.updateShippedByIds(itemIds);
	}

	@Override
	public MapContext findByFinishedStockitemId(String finishedStockitemId) {
		return this.dao.findByFinishedStockitemId(finishedStockitemId);
	}

	@Override
	public PaginatedList<MapContext> findListByOrderId(PaginatedFilter paginatedFilter) {
		return this.dao.findListByOrderId(paginatedFilter);
	}

	@Override
	public String findOrderIdByFinishedItemId(String finishedStockItemId) {
		return this.dao.findOrderIdByFinishedItemId(finishedStockItemId);
	}

	@Override
	public MapContext findOneByFinishedStockitemId(String finishedStockItemId) {
		return this.dao.findOneByFinishedStockitemId(finishedStockItemId);
	}

	@Override
	public Map findByDispatchBillItemId(String dispatchBillItemId) {
		return this.dao.findByDispatchBillItemId(dispatchBillItemId);
	}

	@Override
	public Integer findNumByCreated(String beginTime, String endTime, String day,Integer isIn,String delivered) {
		MapContext params = MapContext.newOne();
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		params.put("created", day);
		params.put("isIn", isIn);
		params.put("delivered", delivered);
		return this.dao.findNumByCreated(params);
	}

	@Override
	public Integer findFininshedstockStatementByDateAndIsin(Date date, Integer isIn) {
		MapContext params = MapContext.newOne();
		params.put("created", date);
		params.put("isIn", isIn);
		return this.dao.findFininshedstockStatementByDateAndIsin(params);
	}

	@Override
	public Integer findCountByTimeAndType(Date sTime, Date eTime, int type,Integer isIn) {
		MapContext params = MapContext.newOne();
		params.put("sTime", sTime);
		params.put("eTime", eTime);
		params.put("type",type);
		params.put("isIn",isIn);
		return this.dao.findCountByTimeAndType(params);
	}

	@Override
	public PaginatedList<Map<String,Object>> findListMapByFilter(PaginatedFilter paginatedFilter) {
		return this.dao.findListMapByFilter(paginatedFilter);
	}

	@Override
	public int deleteByOrderId(String orderId) {
		return this.dao.deleteByOrderId(orderId);
	}

	@Override
	public PaginatedList<MapContext> findFinishedStockNos(PaginatedFilter paginatedFilter) {
		return this.dao.findFinishedStockNos(paginatedFilter);
	}
}