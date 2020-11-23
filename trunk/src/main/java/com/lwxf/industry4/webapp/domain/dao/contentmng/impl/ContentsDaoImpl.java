package com.lwxf.industry4.webapp.domain.dao.contentmng.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.contentmng.ContentsDto;
import com.lwxf.industry4.webapp.domain.entity.contentmng.ContentsContent;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.contentmng.ContentsDao;
import com.lwxf.industry4.webapp.domain.entity.contentmng.Contents;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-03-27 11:58:33
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("contentsDao")
public class ContentsDaoImpl extends BaseDaoImpl<Contents, String> implements ContentsDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ContentsDto> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<ContentsDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}



	@Override
	public ContentsDto findByContentId(String contentId) {
		String sqlId=this.getNamedSqlId("findByContentId");
		return this.getSqlSession().selectOne(sqlId,contentId);
	}

	public ContentsContent findContentMessage(String contentsId) {

		String sqlId=this.getNamedSqlId("findContentMessage");
		return this.getSqlSession().selectOne(sqlId,contentsId);
	}

	@Override
	public List<Map> findByCodeAndStatus(MapContext map) {
		String sqlId=this.getNamedSqlId("findByCodeAndStatus");
		return this.getSqlSession().selectList(sqlId,map);
	}

	@Override
	public PaginatedList<Map> findListByCodeAndStatus(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findListByCodeAndStatus");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<Map> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}
	@Override
	public List<ContentsDto> findTopContentsList(String typeId,String branchId) {
		String sqlId=this.getNamedSqlId("findTopContentsList");
		MapContext mapContext = new MapContext();
		mapContext.put("typeId",typeId);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,branchId);
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public Map findFByContentId(String id) {
		String sqlId = this.getNamedSqlId("findFByContentId");
		return this.getSqlSession().selectOne(sqlId,id);
	}
}