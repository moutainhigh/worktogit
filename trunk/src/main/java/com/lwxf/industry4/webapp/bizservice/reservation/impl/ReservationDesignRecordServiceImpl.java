package com.lwxf.industry4.webapp.bizservice.reservation.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.reservation.ReservationDesignRecordDao;
import com.lwxf.industry4.webapp.bizservice.reservation.ReservationDesignRecordService;
import com.lwxf.industry4.webapp.domain.entity.reservation.ReservationDesignRecord;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-09-08 16:32:41
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("reservationDesignRecordService")
public class ReservationDesignRecordServiceImpl extends BaseServiceImpl<ReservationDesignRecord, String, ReservationDesignRecordDao> implements ReservationDesignRecordService {


	@Resource

	@Override	public void setDao( ReservationDesignRecordDao reservationDesignRecordDao) {
		this.dao = reservationDesignRecordDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ReservationDesignRecord> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<ReservationDesignRecord> selectByReservationId(String reservationId) {
		return this.dao.selectByReservationId(reservationId);
	}

	@Override
	public int removeConfirmedByReservationId(String reservationId) {
		return this.dao.removeConfirmedByReservationId(reservationId);
	}

	@Override
	public List<String> selectPathByResDesId(String designId) {
		return this.dao.selectPathByResDesId(designId);
	}
}