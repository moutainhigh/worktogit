package com.lwxf.industry4.webapp.facade.reservation;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.reservation.Reservation;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 功能：
 *
 * @author：dell
 * @create：2018/6/16 11:53
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface ReservationFacade extends BaseFacade {

	/**
	 * 添加预约订单
	 * @param reservation
	 * @return
	 */
	RequestResult postReservation(Reservation reservation);

	RequestResult selectReservationByStatus(String companyId,Integer pageNumber,Integer pageSize);

	RequestResult designerReservation(String companyId, String designerId,Integer pageNumber,Integer pageSize);


	/**
	 * 修改
	 * @return
	 */
	RequestResult putReservation(String reservationId, MapContext update);

	//删除预约单
	RequestResult deleteReservationById(String id);

	RequestResult findReservationFilter(Integer pageNum, Integer pageSize, MapContext mapContext);

	RequestResult findAllUserId();

	/**
	 * 获取预约总数
	 * @return
	 */
	Long findAllAmount();

	/**
	 * 设计师抢单
	 */
	RequestResult designerGlomRes(String companyId,String reservationId,String designerId);

	RequestResult designerChangeStatus (String reservationId,Integer status);

}
