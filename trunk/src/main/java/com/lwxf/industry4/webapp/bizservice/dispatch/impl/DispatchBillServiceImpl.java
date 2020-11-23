package com.lwxf.industry4.webapp.bizservice.dispatch.impl;


import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.dispatch.DispatchBillService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dao.common.UploadFilesDao;
import com.lwxf.industry4.webapp.domain.dao.dispatch.DispatchBillDao;
import com.lwxf.industry4.webapp.domain.dao.dispatch.DispatchBillItemDao;
import com.lwxf.industry4.webapp.domain.dao.warehouse.FinishedStockItemDao;
import com.lwxf.industry4.webapp.domain.dto.dispatch.DispatchBillDto;
import com.lwxf.industry4.webapp.domain.dto.dispatch.DispatchBillItemDto;
import com.lwxf.industry4.webapp.domain.dto.dispatch.DispatchBillPlanItemDto;
import com.lwxf.industry4.webapp.domain.dto.printTable.DispatchPrintTableDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockDto;
import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.industry4.webapp.domain.entity.dispatch.DispatchBill;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-20 15:10:29
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("dispatchBillService")
public class DispatchBillServiceImpl extends BaseServiceImpl<DispatchBill, String, DispatchBillDao> implements DispatchBillService {


	@Resource

	@Override	public void setDao( DispatchBillDao dispatchBillDao) {
		this.dao = dispatchBillDao;
	}

	@Resource(name = "finishedStockItemDao")
	private FinishedStockItemDao finishedStockItemDao;
	@Resource(name = "dispatchBillItemDao")
	private DispatchBillItemDao dispatchBillItemDao;
	@Resource(name = "uploadFilesDao")
	private UploadFilesDao uploadFilesDao;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DispatchBillDto> selectByFilter(PaginatedFilter paginatedFilter) {
		PaginatedList<DispatchBillDto> paginatedList = this.dao.selectByFilter(paginatedFilter);
//		for (DispatchBillDto dispatchBill:paginatedList.getRows()){
//			dispatchBill.setDispatchBillItemDtoList(this.dao.findListByDispatchId(dispatchBill.getId()));
//			dispatchBill.setUploadFiles(this.uploadFilesDao.findByResourceId(dispatchBill.getId()));
//		}
		return  paginatedList;
	}

	@Override
	public List<DispatchBillDto> findDispatchsByOrderId(String orderId) {
		return this.dao.findDispatchsByOrderId(orderId);
	}

	@Override
	public int deleteDispatchBillAndItemById(String id) {
		List<DispatchBillItemDto> dispatchBillItemDtos = this.dao.findItemListById(id);
		for(DispatchBillItemDto dispatchBillItemDto:dispatchBillItemDtos){
			MapContext mapContext = MapContext.newOne();
			mapContext.put(WebConstant.KEY_ENTITY_ID,dispatchBillItemDto.getFinishedStockItemId());
			mapContext.put("shipped",false);
			mapContext.put("delivered",null);
			mapContext.put("deliverer",null);
			this.finishedStockItemDao.updateByMapContext(mapContext);
			this.dispatchBillItemDao.deleteById(dispatchBillItemDto.getId());
		}
		this.dao.deleteById(id);
		return 0;
	}

	@Override
	public DispatchBillDto findDispatchsBillById(String dispatchId) {
		return this.dao.findDispatchsBillById(dispatchId);
	}

	@Override
	public List<MapContext> findDispatchInfoForOrder(String orderId) {
		return this.dao.findDispatchInfoForOrder(orderId);
	}

	@Override
	public int findYSHItemCount(String orderId) {
		return this.dao.findYSHItemCount(orderId);
	}

	@Override
	public String findTimeByOrderId(String orderId) {
		return this.dao.findTimeByOrderId(orderId);
	}

	@Override
	public PaginatedList<DispatchBillPlanItemDto> findDispathcBillList(PaginatedFilter paginatedFilter) {
		return this.dao.findDispathcBillList(paginatedFilter);
	}

	@Override
	public List<DispatchBill> findDispatchListByOrderId(String orderId) {
		return this.dao.findDispatchListByOrderId(orderId);
	}

	@Override
	public List<Map> findDispatchList(String resultOrderId) {
		List<Map> dispatchBillList=this.dao.findDispatchList(resultOrderId);
		Integer resourceType= UploadResourceType.DISPATCH_BILL.getType();
		if(dispatchBillList.size()>0&&dispatchBillList!=null) {
			for (Map dispatchBill:dispatchBillList) {
			String	resourceId=dispatchBill.get("dispatchBillId").toString();
			String belongId=dispatchBill.get("dispatchBillId").toString();
				List<UploadFiles> dispatchBillFiles = this.uploadFilesDao.findListByBelongIdAndResourceIdAndResourceType(belongId,resourceId,resourceType);
				for(UploadFiles uploadFiles:dispatchBillFiles){
					String fullPath= WebUtils.getDomainUrl()+uploadFiles.getPath();
					uploadFiles.setFullPath(fullPath);
				}
				dispatchBill.put("dispatchBillFiles", dispatchBillFiles);

			}
		}
		return dispatchBillList;
	}

	@Override
	public List<Map> findDispatchListByFinishedItemId(List itemids) {
		return this.dao.findDispatchListByFinishedItemId(itemids);
	}

	@Override
	public List<FinishedStockDto> findFinishedItemTypeByDispatchId(String dispatchBillId, List itemids) {
		return this.dao.findFinishedItemTypeByDispatchId(dispatchBillId,itemids);
	}

	@Override
	public DispatchPrintTableDto findDispatchPrintInfo(String id) {
		return this.dao.findDispatchPrintInfo(id);
	}

	@Override
	public DispatchBillDto findDispatchPrintInfoById(String dispatchBillId) {
		return this.dao.findDispatchPrintInfoById(dispatchBillId);
	}

	@Override
	public List<MapContext> findDispatchsInfoByOrderId(String id) {
		return this.dao.findDispatchsInfoByOrderId(id);
	}

	@Override
	public List<DispatchBill> findListByTimeAndBranchId(MapContext ma) {
		return this.dao.findListByTimeAndBranchId(ma);
	}

	@Override
	public List<Map> findFactoryDispatchsByOrderId(String orderId) {
		return this.dao.findFactoryDispatchsByOrderId(orderId);
	}
}