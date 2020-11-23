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
import com.lwxf.industry4.webapp.domain.entity.reservation.ReservationDesignFile;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.reservation.ReservationDesignFileDao;
import com.lwxf.industry4.webapp.domain.entity.reservation.ReservationDesignFile;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-09-08 16:32:41
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("reservationDesignFileDao")
public class ReservationDesignFileDaoImpl extends BaseDaoImpl<ReservationDesignFile, String> implements ReservationDesignFileDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ReservationDesignFile> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<ReservationDesignFile> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public int deleteByDesRecordId(String designRecordId) {
		String sqlId = this.getNamedSqlId("deleteByDesRecordId");
		return this.sqlSession.delete(sqlId,designRecordId);
	}

	@Override
	public List<ReservationDesignFile> selectByResDesRecId(String designRecordId) {
		String sqlId = this.getNamedSqlId("selectByResDesRecId");
		return this.sqlSession.selectList(sqlId,designRecordId);
	}
}