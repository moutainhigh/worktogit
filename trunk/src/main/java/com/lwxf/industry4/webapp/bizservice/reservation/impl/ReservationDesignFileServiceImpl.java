package com.lwxf.industry4.webapp.bizservice.reservation.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.bizservice.reservation.ReservationDesignFileService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.reservation.ReservationDesignFileDao;
import com.lwxf.industry4.webapp.bizservice.reservation.ReservationDesignFileService;
import com.lwxf.industry4.webapp.domain.entity.reservation.ReservationDesignFile;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-09-08 16:32:41
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("reservationDesignFileService")
public class ReservationDesignFileServiceImpl extends BaseServiceImpl<ReservationDesignFile, String, ReservationDesignFileDao> implements ReservationDesignFileService {


	@Resource

	@Override	public void setDao( ReservationDesignFileDao reservationDesignFileDao) {
		this.dao = reservationDesignFileDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ReservationDesignFile> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public int deleteByDesRecordId(String designRecordId) {
		return this.dao.deleteByDesRecordId(designRecordId);
	}

	@Override
	public List<ReservationDesignFile> selectByResDesRecId(String ResDesRecId) {
		return this.dao.selectByResDesRecId(ResDesRecId);
	}
}