package com.lwxf.industry4.webapp.facade.admin.factory.system.impl;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.common.CityAreaService;
import com.lwxf.industry4.webapp.bizservice.company.DealerShippingLogisticsService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.bizservice.system.LogisticsCompanyService;
import com.lwxf.industry4.webapp.common.enums.LogisticsCompany.LogisticsCompanyStatus;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.aftersale.AftersaleDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderDto;
import com.lwxf.industry4.webapp.domain.entity.common.CityArea;
import com.lwxf.industry4.webapp.domain.entity.company.DealerShippingLogistics;
import com.lwxf.industry4.webapp.domain.entity.system.LogisticsCompany;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.admin.factory.system.LogisticsCompanyFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/26/026 16:02
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("logisticsCompanyFacade")
public class LogisticsCompanyFacadeImpl extends BaseFacadeImpl implements LogisticsCompanyFacade {

	@Resource(name = "logisticsCompanyService")
	private LogisticsCompanyService logisticsCompanyService;
	@Resource(name = "dealerShippingLogisticsService")
	private DealerShippingLogisticsService dealerShippingLogisticsService;
	@Resource(name = "customOrderService")
	private CustomOrderService customOrderService;
	@Resource(name = "cityAreaService")
	private CityAreaService cityAreaService;

	@Override
	public RequestResult findList(MapContext mapContext,String branchId, Integer pageNum, Integer pageSize) {
//		CustomOrderDto orderDto = this.customOrderService.findByOrderId(orderId);
//		DealerShippingLogistics dealerShippingLogistics = this.dealerShippingLogisticsService.findOneByCompanyId(orderDto.getCompanyId());
//		MapContext mapContext = MapContext.newOne();
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageSize(pageSize);
		pagination.setPageNum(pageNum);
		paginatedFilter.setPagination(pagination);
        mapContext.put("branchId",branchId);
		paginatedFilter.setFilters(mapContext);
		PaginatedList<LogisticsCompany> allNormal = this.logisticsCompanyService.selectByFilter(paginatedFilter);
//		List<LogisticsCompany> allNormal = this.logisticsCompanyService.findAllNormalByBranchId(mapContext);
//		if(dealerShippingLogistics!=null){
//			mapContext.put("default",dealerShippingLogistics.getLogisticsCompanyId());
//		}else{
//			if(allNormal!=null){
//				mapContext.put("default",allNormal.get(0).getId());
//			}else{
//				mapContext.put("default",null);
//			}
//		}
//		mapContext.put("logisticsCompanyList",allNormal);
		return ResultFactory.generateRequestResult(allNormal);
	}

	/**
	 * 查询物流公司详情
	 * @param logisticId
	 * @return
	 */
	@Override
	public RequestResult findLogisticInfoById(String logisticId) {
		LogisticsCompany logisticsCompany=this.logisticsCompanyService.findByLogisticId(logisticId);
		if(logisticsCompany==null){
			return ResultFactory.generateResNotFoundResult();
		}
		return ResultFactory.generateRequestResult(logisticsCompany);
	}

	/**
	 * 物流公司添加
	 * @param logisticsCompany
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addLogisticCompany(LogisticsCompany logisticsCompany) {
		String creator=WebUtils.getCurrUserId();
		String branchId=WebUtils.getCurrBranchId();
		Date created= DateUtil.getSystemDate();
		if(logisticsCompany.getStatus()==null||logisticsCompany.getStatus().toString().equals("")){
			logisticsCompany.setStatus(LogisticsCompanyStatus.NORMAL.getValue());
		}
		if(logisticsCompany.getCityAreaId().equals("")){
			logisticsCompany.setCityAreaId(null);
		}
		if(logisticsCompany.getNo().equals("")){
			logisticsCompany.setNo(null);
		}
		String no=logisticsCompany.getNo();
		if(no!=null) {
			LogisticsCompany logistics = this.logisticsCompanyService.findByNO(no);
			if (logistics != null) {
				return ResultFactory.generateErrorResult("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
			}
		}
		logisticsCompany.setBranchId(branchId);
		logisticsCompany.setCreated(created);
		logisticsCompany.setCreator(creator);
		this.logisticsCompanyService.add(logisticsCompany);
		return ResultFactory.generateRequestResult(logisticsCompany);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateLogisticCompany(String logisticId, MapContext mapContext) {
		if(mapContext.containsKey("cityAreaId")){
			if(mapContext.getTypedValue("cityAreaId",String.class).trim().equals("")){
				mapContext.remove("cityAreaId");
			}
		}
		//判断编号是否重复
		String no=mapContext.getTypedValue("no",String.class);
		if(no!=null){
			if(no.equals("")){
				mapContext.put("no",null);
			}else {
				LogisticsCompany logisticsCompany = this.logisticsCompanyService.findByNO(no);
				if (logisticsCompany != null) {
					if (!logisticsCompany.getId().equals(logisticId)) {
						return ResultFactory.generateErrorResult("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
					}
				}
			}
		}
		mapContext.put("id",logisticId);
		this.logisticsCompanyService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult(mapContext);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteLogisticCompany(String logisticId) {
		this.logisticsCompanyService.deleteById(logisticId);
		return ResultFactory.generateSuccessResult();
	}

}
