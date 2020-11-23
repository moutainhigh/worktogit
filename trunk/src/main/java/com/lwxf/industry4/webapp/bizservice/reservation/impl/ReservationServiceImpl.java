package com.lwxf.industry4.webapp.bizservice.reservation.impl;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.reservation.ReservationDto;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.reservation.ReservationDao;
import com.lwxf.industry4.webapp.bizservice.reservation.ReservationService;
import com.lwxf.industry4.webapp.domain.entity.reservation.Reservation;


/**
 * 功能：
 * 
 * @author：zhangjiale(zjl869319827@outlook.com)
 * @created：2018-06-14 11:35:08
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("reservationService")
public class ReservationServiceImpl extends BaseServiceImpl<Reservation, String, ReservationDao> implements ReservationService {


	@Resource(name = "reservationDao")
	@Override
	public void setDao( ReservationDao reservationDao) {
		this.dao = reservationDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<Reservation> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public int insert(Reservation reservation) {
		return this.dao.insert(reservation);
	}

	@Override
	public Reservation selectById(String id) {
		return this.dao.selectById(id);
	}


	@Override
	public List<ReservationDto> findAllByUserid(String userId) {
		return this.dao.findAllByUserid(userId);
	}

	@Override
	public Long findAllAmount() {
		return this.dao.findAllAmount();
	}

	@Override
	public PaginatedList<Reservation> selectReservationByStatusAndCompanyId(PaginatedFilter paginatedFilter) {
		return this.dao.selectReservationByStatusAndCompanyId(paginatedFilter);
	}

	@Override
	public PaginatedList<Reservation> designerReservation(PaginatedFilter paginatedFilter) {

		return this.dao.designerReservation(paginatedFilter);
	}
}