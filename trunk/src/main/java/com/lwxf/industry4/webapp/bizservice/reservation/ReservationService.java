package com.lwxf.industry4.webapp.bizservice.reservation;



import java.util.List;

import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.dto.reservation.ReservationDto;
import com.lwxf.industry4.webapp.domain.entity.reservation.Reservation;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;


/**
 * 功能：
 * 
 * @author：zhangjiale(zjl869319827@outlook.com)
 * @created：2018-06-14 11:35:08
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface ReservationService extends BaseService <Reservation, String> {

	PaginatedList<Reservation> selectByFilter(PaginatedFilter paginatedFilter);

	int insert(Reservation reservation);

	Reservation selectById(String id);

	List<ReservationDto> findAllByUserid(String userId);


	PaginatedList<Reservation> selectReservationByStatusAndCompanyId(PaginatedFilter paginatedFilter);

	PaginatedList<Reservation> designerReservation(PaginatedFilter paginatedFilter);

	/**
	 * 获取预约总数
	 * @return
	 */
	Long findAllAmount();



}