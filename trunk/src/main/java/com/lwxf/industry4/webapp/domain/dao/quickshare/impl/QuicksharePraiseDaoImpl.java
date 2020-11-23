package com.lwxf.industry4.webapp.domain.dao.quickshare.impl;


import java.util.List;
import java.util.Map;
import java.util.Set;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDaoImpl;
import com.lwxf.industry4.webapp.domain.dto.quickshare.QuicksharePraiseDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.quickshare.QuicksharePraiseDao;
import com.lwxf.industry4.webapp.domain.entity.quickshare.QuicksharePraise;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-03-02 13:25:55
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("quicksharePraiseDao")
public class QuicksharePraiseDaoImpl extends BaseNoIdDaoImpl<QuicksharePraise> implements QuicksharePraiseDao {


	@Override
	public QuicksharePraise selectByMemberIdAndQuickshareId(String memberId, String quickshareId) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("memberId",memberId);
		mapContext.put("quickshareId",quickshareId);
		String sqlId=this.getNamedSqlId("selectByMemberIdAndQuickshareId");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<QuicksharePraise> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination());
		PageList<QuicksharePraise> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<QuicksharePraiseDto> selectByQuickshareId(String quickshareId) {
		String sqlId=this.getNamedSqlId("selectByQuickshareId");
		return this.getSqlSession().selectList(sqlId,quickshareId);
	}

	@Override
	public void deleteQuiPraByQuiId(String quickshareId) {
		String sqlId=this.getNamedSqlId("deleteQuiPraByQuiId");
		this.getSqlSession().selectList(sqlId,quickshareId);
	}

	@Override
	public void deleteByQuiIdAndUid(String quickshareId, String memberId) {
		String sqlId=this.getNamedSqlId("deleteByQuiIdAndUid");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("quickshareId",quickshareId);
		mapContext.put("memberId",memberId);
		 this.getSqlSession().delete(sqlId,mapContext);
	}

	@Override
	public List<QuicksharePraise> findByQsIds(Set<String> quickshareIds) {
		String sqlId=this.getNamedSqlId("findByQsIds");
		return this.getSqlSession().selectList(sqlId,quickshareIds);
	}

}