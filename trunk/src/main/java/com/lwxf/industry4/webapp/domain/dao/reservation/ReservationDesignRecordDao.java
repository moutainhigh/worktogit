package com.lwxf.industry4.webapp.domain.dao.reservation;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.entity.reservation.ReservationDesignRecord;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.reservation.ReservationDesignRecord;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-09-08 16:32:41
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface ReservationDesignRecordDao extends BaseDao<ReservationDesignRecord, String> {

	PaginatedList<ReservationDesignRecord> selectByFilter(PaginatedFilter paginatedFilter);

	List<ReservationDesignRecord> selectByReservationId (String reservationId);

	int removeConfirmedByReservationId(String reservationId);

	List<String> selectPathByResDesId(String designId);
}