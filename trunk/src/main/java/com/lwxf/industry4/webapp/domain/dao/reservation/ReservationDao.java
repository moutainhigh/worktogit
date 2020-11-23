package com.lwxf.industry4.webapp.domain.dao.reservation;


import java.util.List;

import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.reservation.ReservationDto;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.reservation.ReservationDto;
import com.lwxf.industry4.webapp.domain.entity.reservation.Reservation;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.reservation.Reservation;
import com.lwxf.mybatis.utils.MapContext;


/**
 * 功能：
 * 
 * @author：zhangjiale(zjl869319827@outlook.com)
 * @created：2018-06-14 11:35:08
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface ReservationDao extends BaseDao<Reservation, String> {

	PaginatedList<Reservation> selectByFilter(PaginatedFilter paginatedFilter);

	List<ReservationDto> findAllByUserid(String userId);

	/**
	 * 获取预约总数
	 * @return
	 */
	Long findAllAmount();

	PaginatedList<Reservation> selectReservationByStatusAndCompanyId(PaginatedFilter paginatedFilter);

	PaginatedList<Reservation> designerReservation(PaginatedFilter paginatedFilter);
}