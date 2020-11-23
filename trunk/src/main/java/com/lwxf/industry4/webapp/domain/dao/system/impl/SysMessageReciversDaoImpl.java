package com.lwxf.industry4.webapp.domain.dao.system.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.domain.dto.system.SysMessageReciversDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.system.SysMessageReciversDao;
import com.lwxf.industry4.webapp.domain.entity.system.SysMessageRecivers;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@126.com)
 * @created：2019-12-09 16:51:47
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("sysMessageReciversDao")
public class SysMessageReciversDaoImpl extends BaseDaoImpl<SysMessageRecivers, String> implements SysMessageReciversDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<SysMessageRecivers> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<SysMessageRecivers> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<SysMessageReciversDto> selectMessageByUserId(String userId) {
			String sqlId = this.getNamedSqlId("SysMessageReciversDto");
			return this.getSqlSession().selectList(sqlId,userId);
	}

	@Override
	public SysMessageRecivers selectByUserIdAndMessageId(String messageId, String userId) {
		String sqlId = this.getNamedSqlId("SysMessageReciversDto");
		MapContext map = MapContext.newOne();
		map.put("messageId",messageId);
		map.put("userId",userId);
		return this.getSqlSession().selectOne(sqlId,map);
	}
}