package com.lwxf.industry4.webapp.bizservice.aftersale.impl;


import javax.annotation.Resource;

import java.util.*;

import com.lwxf.industry4.webapp.domain.dto.aftersale.*;
import org.springframework.stereotype.Service;
import com.lwxf.industry4.webapp.bizservice.aftersale.AftersaleApplyService;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dao.aftersale.AftersaleApplyDao;
import com.lwxf.industry4.webapp.domain.dao.aftersale.AftersaleApplyFilesDao;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderDto;
import com.lwxf.industry4.webapp.domain.dto.printTable.AftersalesPrintTableDto;
import com.lwxf.industry4.webapp.domain.dto.printTable.OrderPrintTableDto;
import com.lwxf.industry4.webapp.domain.entity.aftersale.AftersaleApply;
import com.lwxf.industry4.webapp.domain.entity.aftersale.AftersaleApplyFiles;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-01-02 20:27:26
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("aftersaleApplyService")
public class AftersaleApplyServiceImpl extends BaseServiceImpl<AftersaleApply, String, AftersaleApplyDao> implements AftersaleApplyService {


	@Resource
	@Override	public void setDao( AftersaleApplyDao aftersaleApplyDao) {
		this.dao = aftersaleApplyDao;
	}

	@Resource(name = "aftersaleApplyFilesDao")
	private AftersaleApplyFilesDao aftersaleApplyFilesDao;
	@Resource(name = "aftersaleApplyDao")
	private AftersaleApplyDao aftersaleApplyDao;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<AftersaleDto> selectByFilter(PaginatedFilter paginatedFilter) {
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public PaginatedList<AftersaleDto> selectDtoByFilter(PaginatedFilter paginatedFilter) {
		return this.dao.selectDtoByFilter(paginatedFilter) ;
	}

	@Override
	public AftersaleDto findAftersaleMessage(String aftersaleId) {
		return this.dao.findAftersaleMessage(aftersaleId);
	}

	@Override
	public PaginatedList<MapContext> findByFilter(PaginatedFilter paginatedFilter) {
		PaginatedList<MapContext> filter = this.dao.findByFilter(paginatedFilter);
		for(MapContext mapContext :filter.getRows()){
			mapContext.put("aftersaleApplyFilesList",this.aftersaleApplyFilesDao.findFilesByAfterId(mapContext.getTypedValue("id",String.class)));
		}
		return filter;
	}

	@Override
	public AftersaleDto findOneById(String id) {
		AftersaleDto aftersaleDto = this.dao.findOneById(id);
		if(aftersaleDto!=null){
			List<AftersaleApplyFiles> filesList=this.aftersaleApplyFilesDao.findFilesByAfterId(id);
			for(AftersaleApplyFiles aftersaleApplyFiles:filesList){
				String path=aftersaleApplyFiles.getPath();
				String fullPath= WebUtils.getDomainUrl()+path;
				aftersaleApplyFiles.setFullPath(fullPath);
			}
			aftersaleDto.setAftersaleApplyFilesList(filesList);
		}
		return aftersaleDto;
	}

	@Override
	public Integer findCountByCidAndType(MapContext mapContext) {
		return this.dao.findCountByCidAndType(mapContext);
	}

	@Override
	public List<AftersaleApply> findAftersaleListByCid(MapContext mapContext) {
		return this.dao.findAftersaleListByCid(mapContext);
	}



	@Override
	public Integer findCountByOederIdAndType(Integer orderType, List orderIds) {
		return this.dao.findCountByOederIdAndType(orderType,orderIds);
	}

	@Override
	public Integer findCountByCidAndStatus(MapContext mapContext) {
		return this.dao.findCountByCidAndStatus(mapContext);
	}

	@Override
	public List<DateNum> findAftersaleByDate(MapContext mapContext) {
		return this.dao.findAftersaleByDate(mapContext);
	}

	@Override
	public Integer findFactoryAftersaleApply(MapContext mapContext) {
		return this.dao.findFactoryAftersaleApply(mapContext);
	}

	@Override
	public Integer findTodayCount() {
		return this.dao.findTodayCount();
	}

	@Override
	public Integer findThisWeekCount() {
		return this.dao.findThisWeekCount();
	}

	@Override
	public Integer findThisMonthCount() {
		return this.dao.findThisMonthCount();
	}

	@Override
	public List<Map> findAftersaleByOrderId(String orderId) {
		return this.dao.findAftersaleByOrderId(orderId);
	}

	@Override
	public PaginatedList<MapContext> findAftersaleApplyList(PaginatedFilter paginatedFilter) {
		return this.dao.findAftersaleApplyList(paginatedFilter);
	}

	@Override
	public List<MapContext> findAftersaleProductByorderId(String resultOrderId) {
		return this.dao.findAftersaleProductByorderId(resultOrderId);
	}

	@Override
	public AftersaleOrderDto findOrderBaseInfoByOrderId(MapContext params) {
		return this.dao.findOrderBaseInfoByOrderId(params);
	}

	@Override
	public String getAftersaleOrderNo(String orderId,String orderNo) {
		    List<Integer> list=new ArrayList();
			String aftersaleOrderNo="";
			String orderNoValue="H"+orderNo;
			List<AftersaleApply> aftersaleApplyList= this.aftersaleApplyDao.findByOrderId(orderId);
			if(aftersaleApplyList==null||aftersaleApplyList.size()==0){
				aftersaleOrderNo=orderNoValue+"-"+"01";
			}else {
				for(AftersaleApply aftersaleApply:aftersaleApplyList) {
					String no = aftersaleApply.getNo();
					int a = no.length();
					String no1 = no.substring(a - 2, a);//取编号最后两位
					Integer b = Integer.valueOf(no1);//转类型
					list.add(b);
				}
				//利用函数方法给数组排序
				Collections.sort(list);//数组排序
				Integer c=list.get(list.size()-1);//取最后一个值
				Integer d=c+1;
				if (d < 10) {
					aftersaleOrderNo = orderNoValue + "-" + "0" + d.toString();
				} else {
					aftersaleOrderNo = orderNoValue + "-" + d.toString();
				}
			}
			//判断当前操作的资源是否被占用
			AftersaleApply aftersaleApply1=this.aftersaleApplyDao.findByAftersaleNo(aftersaleOrderNo);
			if(aftersaleApply1!=null){
				return null;
			}
			return aftersaleOrderNo;
		}

	@Override
	public List<AftersaleApply> findAftersaleListByOrderId(String orderId) {
		return this.dao.findAftersaleListByOrderId(orderId);
	}

	@Override
	public int deleteByResultOrderId(String orderId) {
		return this.dao.deleteByResultOrderId(orderId);
	}

	@Override
	public Map<String, Long> countAftersale(String branchId) {
		return this.dao.countAftersale(branchId);
	}

	@Override
	public PaginatedList<MapContext> findWxAftersaleApplyList(PaginatedFilter paginatedFilter) {
		return this.dao.findWxAftersaleApplyList(paginatedFilter);
	}

	@Override
	public MapContext countAftersaleForPageIndex(String branchId) {
		return this.dao.countAftersaleForPageIndex(branchId);
	}

	@Override
	public AftersalesPrintTableDto findAftersalesPrintInfo(String id) {
		return this.dao.findAftersalesPrintInfo(id);
	}

	@Override
	public OrderPrintTableDto findOrderPrintTable(MapContext mapContext) {
		return this.dao.findOrderPrintTable(mapContext);
	}

	@Override
	public AftersaleReportDto findRepoart() {
		return this.dao.findAftersaleReport();
	}

	@Override
	public PaginatedList<CustomOrderDto> findListByPaginateFilter(PaginatedFilter paginatedFilter) {
		return this.dao.findListByPaginateFilter(paginatedFilter);
	}

	@Override
	public List<AftersaleApply> findByOrderIdAndProductId(String parentId, String afterProductId) {
		return this.dao.findByOrderIdAndProductId(parentId,afterProductId);
	}
}


