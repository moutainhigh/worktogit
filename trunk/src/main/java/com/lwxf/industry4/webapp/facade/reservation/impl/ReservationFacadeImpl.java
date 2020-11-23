package com.lwxf.industry4.webapp.facade.reservation.impl;

import com.lwxf.industry4.webapp.bizservice.reservation.ReservationService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.reservation.ReservationStatus;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.LwxfAssert;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;
import com.lwxf.industry4.webapp.domain.entity.reservation.Reservation;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.reservation.ReservationFacade;
import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author：dell
 * @create：2018/6/16 11:55
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("reservationFacade")
public class ReservationFacadeImpl extends BaseFacadeImpl implements ReservationFacade {

	@Resource(name = "reservationService")
	private ReservationService reservationService;

	@Override
	@Transactional
	public RequestResult postReservation(Reservation reservation) {
		//TODO 增加公司id
		reservation.setCompanyId(WebUtils.getCurrCompanyId());
		reservation.setCreated(DateUtil.getSystemDate());
		reservation.setUserId(WebUtils.getCurrUserId());
		reservation.setStatus(0);
		reservationService.add(reservation);
		return ResultFactory.generateRequestResult(reservation);
	}


	@Override
	@Transactional
	public RequestResult putReservation(String reservationId, MapContext update) {
		Reservation reservation = this.reservationService.findById(reservationId);
		if (null == reservation) {
			return ResultFactory.generateResNotFoundResult();
		}
		update.put(WebConstant.KEY_ENTITY_ID, reservationId);
		this.reservationService.updateByMapContext(update);
		return ResultFactory.generateSuccessResult();
	}


	@Override
	@Transactional
	public RequestResult deleteReservationById(String id) {
		Reservation verify = this.reservationService.selectById(id);
		CompanyEmployee employee = WebUtils.getCurrUser().getCompanyEmployee();
		boolean isMember = null == employee || !employee.getCompanyId().equals(WebUtils.getCurrCompanyId());
		if (isMember) {
			if (!verify.getUserId().equals(WebUtils.getCurrUserId())) {
				return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RES_NOT_FOUND_10001, AppBeanInjector.i18nUtil.getMessage("BIZ_RES_NOT_FOUND_10001"));
			}
		}
		if (verify.getStatus().byteValue() != ReservationStatus.UNTREATED.getValue() && verify.getStatus().byteValue() != ReservationStatus.CANCELLED.getValue()) {
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NO_PERMISSION_10003, AppBeanInjector.i18nUtil.getMessage("BIZ_NO_PERMISSION_10003"));
		}
		this.reservationService.deleteById(id);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult findReservationFilter(Integer pageNum, Integer pageSize, MapContext mapContext) {
		PaginatedFilter filter = PaginatedFilter.newOne();
		filter.setFilters(mapContext);
		Pagination pagination = Pagination.newOne();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		filter.setPagination(pagination);
		List<Map<String,String>> sort = new ArrayList<Map<String, String>>();
		Map<String,String> forMap =  new HashMap<String, String>();
		forMap.put("created","desc");
		sort.add(forMap);
		filter.setSorts(sort);
		PaginatedList<Reservation> list = this.reservationService.selectByFilter(filter);
		return ResultFactory.generateRequestResult(list);
	}

	@Override
	public RequestResult findAllUserId() {
		return ResultFactory.generateRequestResult(this.reservationService.findAllByUserid(WebUtils.getCurrUserId()));
	}

	@Override
	public Long findAllAmount() {
		return this.reservationService.findAllAmount();
	}

	@Override
	public RequestResult selectReservationByStatus(String companyId,Integer pageNumber,Integer pageSize) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		MapContext mapContext = MapContext.newOne();
		mapContext.put(WebConstant.KEY_ENTITY_COMPANY_ID,companyId);
		paginatedFilter.setFilters(mapContext);
		Pagination pagination = new Pagination();
		pagination.setPageSize(pageSize);
		pagination.setPageNum(pageNumber);
		paginatedFilter.setPagination(pagination);



		return ResultFactory.generateRequestResult(this.reservationService.selectReservationByStatusAndCompanyId(paginatedFilter));
	}

	@Override
	public RequestResult designerReservation(String companyId, String designerId,Integer pageNumber,Integer pageSize) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		MapContext mapContext = MapContext.newOne();
		mapContext.put(WebConstant.KEY_ENTITY_COMPANY_ID,companyId);
		mapContext.put("designerId",designerId);
		paginatedFilter.setFilters(mapContext);
		Pagination pagination = new Pagination();
		pagination.setPageSize(pageSize);
		pagination.setPageNum(pageNumber);
		paginatedFilter.setPagination(pagination);
		return ResultFactory.generateRequestResult(this.reservationService.designerReservation(paginatedFilter));
	}

	/**
	 * 设计师抢单
	 *
	 * @param companyId
	 * @param reservationId
	 * @param designerId
	 * @return
	 */
	@Override
    @Transactional
	public RequestResult designerGlomRes(String companyId, String reservationId, String designerId) {
		Integer identity = 1;
		//如果为空代表不是公司旗下的人员
		LwxfAssert.notNull(null,"该处还没实现");
		/*CompanyShareMember companyShareMember = this.companyShareMemberService.selectByCompanyIdAndIdentityAndUserId(companyId, identity, designerId);
		if (null == companyShareMember) {
			return ResultFactory.generateResNotFoundResult();
		}*/
		//不是公司下的预约单
		Reservation reservation = this.reservationService.findById(reservationId);
		if (!companyId.equals(reservation.getCompanyId())) {
			return ResultFactory.generateResNotFoundResult();
		}
		/*//判断是不是本人登录
		if (designerId.equals(WebUtils.getCurrUserId())) {
			return ResultFactory.generateResNotFoundResult();
		}*/
		MapContext mapContext = MapContext.newOne();
		mapContext.put(WebConstant.KEY_ENTITY_ID, reservationId);
		mapContext.put("designer", designerId);
		mapContext.put(WebConstant.KEY_ENTITY_STATUS,1);
		this.reservationService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult("抢单成功！");
	}


	@Override
    @Transactional
	public RequestResult designerChangeStatus(String reservationId,Integer status) {
		MapContext mapContext = MapContext.newOne();
		if(status>4){
			return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.BIZ_NO_PERMISSION_10003,AppBeanInjector.i18nUtil.getMessage("BIZ_NO_PERMISSION_10003"));
		}
		mapContext.put(WebConstant.KEY_ENTITY_ID,reservationId);
		mapContext.put(WebConstant.KEY_ENTITY_STATUS,status);
		this.reservationService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult("修改成功");
	}


}
