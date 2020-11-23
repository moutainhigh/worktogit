package com.lwxf.industry4.webapp.domain.dao.user.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.user.UserScheduleDao;
import com.lwxf.industry4.webapp.domain.entity.user.UserSchedule;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-01-14 11:55:53
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("userScheduleDao")
public class UserScheduleDaoImpl extends BaseDaoImpl<UserSchedule, String> implements UserScheduleDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<UserSchedule> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<UserSchedule> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<UserSchedule> findUserSchedule(String userId, String time) {
		String sqlId = this.getNamedSqlId("findUserSchedule");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("userId",userId);
		mapContext.put("time",time);
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public UserSchedule findScheduleByUidAndTime(String userId, String scheduleTime) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("userId",userId);
		mapContext.put("time",scheduleTime);

		String sqlId=this.getNamedSqlId("findScheduleByUidAndTime");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

}