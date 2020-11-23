package com.lwxf.industry4.webapp.domain.dao.design;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.design.DesignSchemeFiles;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-03 15:53:46
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface DesignSchemeFilesDao extends BaseDao<DesignSchemeFiles, String> {

	PaginatedList<DesignSchemeFiles> selectByFilter(PaginatedFilter paginatedFilter);

	List<String> findByPath(List<String> uploadFilesList);

	int updateAllTempByDesignId(String id);

	int updateFilesList(String id, List imgIds);

	int deleteBySchemeId(String schemeId);

	List<DesignSchemeFiles> findBySchemeId(String schemeId);

    List<String> findByResouceId(String resourceId);

	List<DesignSchemeFiles> findListByResourceIdAndType(String id, Integer type);
}