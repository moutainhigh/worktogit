package com.lwxf.industry4.webapp.bizservice.contentmng;


import java.util.List;


import com.lwxf.industry4.webapp.bizservice.base.BaseNoIdService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.contentmng.ContentsContent;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-03-27 11:58:33
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface ContentsContentService extends BaseNoIdService<ContentsContent> {

	PaginatedList<ContentsContent> selectByFilter(PaginatedFilter paginatedFilter);

}