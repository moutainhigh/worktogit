package com.lwxf.industry4.webapp.domain.dao.contentmng;


import java.util.List;
import java.util.Map;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.contentmng.ContentsDto;
import com.lwxf.industry4.webapp.domain.entity.contentmng.ContentsContent;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.contentmng.Contents;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-03-27 11:58:33
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface ContentsDao extends BaseDao<Contents, String> {

	PaginatedList<ContentsDto> selectByFilter(PaginatedFilter paginatedFilter);

	ContentsDto findByContentId(String contentId);

	ContentsContent findContentMessage(String contentsId);

	List<ContentsDto> findTopContentsList(String typeId,String branchId);

    List<Map> findByCodeAndStatus(MapContext map);

    PaginatedList<Map> findListByCodeAndStatus(PaginatedFilter paginatedFilter);

    Map findFByContentId (String id);

}