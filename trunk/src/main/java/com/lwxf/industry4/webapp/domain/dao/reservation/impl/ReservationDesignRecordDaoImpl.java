package com.lwxf.industry4.webapp.domain.dao.reservation.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.entity.reservation.ReservationDesignRecord;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.reservation.ReservationDesignRecordDao;
import com.lwxf.industry4.webapp.domain.entity.reservation.ReservationDesignRecord;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-09-08 16:32:41
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("reservationDesignRecordDao")
public class ReservationDesignRecordDaoImpl extends BaseDaoImpl<ReservationDesignRecord, String> implements ReservationDesignRecordDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ReservationDesignRecord> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<ReservationDesignRecord> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<ReservationDesignRecord> selectByReservationId(String reservationId) {
		String sqlId = this.getNamedSqlId("selectByReservationId");
		return this.getSqlSession().selectList(sqlId,reservationId);
	}

	@Override
	public int removeConfirmedByReservationId(String reservationId) {
		String sqlId = this.getNamedSqlId("removeConfirmedByReservationId");
		return this.getSqlSession().update(sqlId,reservationId);
	}

	@Override
	public List<String> selectPathByResDesId(String designId) {
		String sqlId = this.getNamedSqlId("selectPathByResDesId");
		return this.getSqlSession().selectList(sqlId,designId);
	}
}