package com.lwxf.industry4.webapp.bizservice.contentmng;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.contentmng.ContentsDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.contentmng.ContentsType;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-03-27 11:58:34
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface ContentsTypeService extends BaseService <ContentsType, String> {

	PaginatedList<ContentsType> selectByFilter(PaginatedFilter paginatedFilter);

	List<ContentsType> findContentsTypeListByBranchId(String branchId);

	ContentsType findContentsListByCode(String typeIdOrCode);

	String addContentType(ContentsType contentType);
}