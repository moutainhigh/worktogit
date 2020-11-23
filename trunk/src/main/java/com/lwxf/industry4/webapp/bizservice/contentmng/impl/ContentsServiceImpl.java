package com.lwxf.industry4.webapp.bizservice.contentmng.impl;


import java.util.List;
import java.util.Map;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.common.utils.UniqueKeyUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dao.contentmng.ContentsContentDao;
import com.lwxf.industry4.webapp.domain.entity.contentmng.ContentsContent;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.contentmng.ContentsDto;
import com.lwxf.industry4.webapp.domain.entity.contentmng.ContentsContent;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.contentmng.ContentsDao;
import com.lwxf.industry4.webapp.bizservice.contentmng.ContentsService;
import com.lwxf.industry4.webapp.domain.entity.contentmng.Contents;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-03-27 11:58:33
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("contentsService")
public class ContentsServiceImpl extends BaseServiceImpl<Contents, String, ContentsDao> implements ContentsService {



	@Resource
	@Override	public void setDao( ContentsDao contentsDao) {
		this.dao = contentsDao;
	}

	@Resource(name = "contentsContentDao")
	private ContentsContentDao contentsContentDao;


	public int add(ContentsDto contents) {
		String id = UniqueKeyUtil.getStringId();
		contents.setCreator(WebUtils.getCurrUserId());
		contents.setCreated(DateUtil.getSystemDate());
		contents.setId(id);
		ContentsContent c = new ContentsContent();
		c.setContentsId(id);
		if(contents.getContent()!=null){
			c.setContent(contents.getContent());
		}else {
			c.setContent("");
		}
		Contents content = contents;
		int res =this.dao.insert(content);
		contentsContentDao.insert(c);
		return res;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ContentsDto> selectByFilter(PaginatedFilter paginatedFilter) {
		return this.dao.selectByFilter(paginatedFilter) ;
	}


	@Override
	public int updateByMapContext(MapContext mapContext) {
		MapContext contentMap = MapContext.newOne();
		if(mapContext.get("content")!=null && !mapContext.get("content").equals("")) {
			contentMap.put("contentsId", mapContext.get("id"));
			contentMap.put("content", mapContext.get("content"));
			contentsContentDao.updateByMapContext(contentMap);
		}
		return this.dao.updateByMapContext(mapContext);
	}

	public ContentsContent findContentMessage(String contentsId) {
		return this.dao.findContentMessage(contentsId);
	}

	@Override
	public List<Map> findByCodeAndStatus(MapContext map) {
		return this.dao.findByCodeAndStatus(map);
	}

	@Override
	public PaginatedList<Map> findListByCodeAndStatus(PaginatedFilter paginatedFilter) {
		return this.dao.findListByCodeAndStatus(paginatedFilter);
	}
	@Override
	public List<ContentsDto> findTopContentsList(String typeId,String branchId) {
		return this.dao.findTopContentsList(typeId,branchId);
	}


	@Override
	public ContentsDto findContentById(String contentId) {
		return this.dao.findByContentId(contentId);
	}

	public int addContent(ContentsDto contentsDto) {
		Contents content = contentsDto;
		return this.dao.insert(content);
	}
	@Override
	public int deleteById(String id) {
		//删除文章关联表
		MapContext map = MapContext.newOne();
		map.put("contentsId",id);
		contentsContentDao.deleteByMapContext(map);
		return this.dao.deleteById(id);
	}

	@Override
	public Map findFByContentId(String id) {
		return this.dao.findFByContentId(id);
	}
}