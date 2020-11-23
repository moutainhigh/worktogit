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
import com.lwxf.industry4.webapp.domain.dao.reservation.ReservationPayedRecordDao;
import com.lwxf.industry4.webapp.bizservice.reservation.ReservationPayedRecordService;
import com.lwxf.industry4.webapp.domain.entity.reservation.ReservationPayedRecord;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-09-08 16:32:41
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("reservationPayedRecordService")
public class ReservationPayedRecordServiceImpl extends BaseServiceImpl<ReservationPayedRecord, String, ReservationPayedRecordDao> implements ReservationPayedRecordService {


	@Resource

	@Override	public void setDao( ReservationPayedRecordDao reservationPayedRecordDao) {
		this.dao = reservationPayedRecordDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ReservationPayedRecord> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

}