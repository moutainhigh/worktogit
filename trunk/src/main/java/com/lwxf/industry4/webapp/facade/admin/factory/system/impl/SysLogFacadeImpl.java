package com.lwxf.industry4.webapp.facade.admin.factory.system.impl;

import com.lwxf.industry4.webapp.bizservice.system.SysLogService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.entity.system.SysLog;
import com.lwxf.industry4.webapp.facade.admin.factory.system.SysLogFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("sysLogFacade")
public class SysLogFacadeImpl extends BaseFacadeImpl implements SysLogFacade {

    @Resource(name = "sysLogService")
    private SysLogService sysLogService;


    @Override
    public RequestResult selectSysLogList(Integer pageNum, Integer pageSize, MapContext params) {

        PaginatedFilter filter = PaginatedFilter.newOne();
        filter.setFilters(params);
        Pagination pagination = Pagination.newOne();
        pagination.setPageNum(pageNum);
        pagination.setPageSize(pageSize);
        filter.setPagination(pagination);
        PaginatedList<SysLog> list = this.sysLogService.selectByFilter(filter);
        return ResultFactory.generateRequestResult(list);
    }
}