package com.lwxf.industry4.webapp.bizservice.contentmng;


import java.util.List;
import java.util.Map;

import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.contentmng.ContentsDto;
import com.lwxf.industry4.webapp.domain.entity.contentmng.Contents;
import com.lwxf.industry4.webapp.domain.entity.contentmng.ContentsContent;
import com.lwxf.mybatis.utils.MapContext;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-03-27 11:58:33
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface ContentsService extends BaseService <Contents, String> {

	PaginatedList<ContentsDto> selectByFilter(PaginatedFilter paginatedFilter);

	ContentsContent findContentMessage(String contentsId);

	ContentsDto findContentById(String code);

	List<ContentsDto> findTopContentsList(String typeId,String branchId);

	int add(ContentsDto contentsDto);

	List<Map>findByCodeAndStatus(MapContext map);

	PaginatedList<Map> findListByCodeAndStatus(PaginatedFilter paginatedFilter);

	Map findFByContentId (String id);
}