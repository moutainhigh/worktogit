package com.lwxf.industry4.webapp.facade.admin.factory.system.impl;

import com.lwxf.industry4.webapp.bizservice.system.SysSmsService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.dto.system.SysSmsDto;
import com.lwxf.industry4.webapp.domain.entity.system.SysSms;
import com.lwxf.industry4.webapp.facade.admin.factory.system.SysSmsFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 功能：
 *
 * @author：DJL
 * @create：2019/11/20 16:26
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("sysSmsFacade")
public class SysSmsFacadeImpl extends BaseFacadeImpl implements SysSmsFacade {

    @Resource(name = "sysSmsService")
    private SysSmsService sysSmsService;

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult add(SysSms sysSms) {
        this.sysSmsService.add(sysSms);
        return ResultFactory.generateSuccessResult();
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult update(MapContext mapContext, String id) {
        SysSms sysSms = this.sysSmsService.findById(id);
        if (null == sysSms) {
            return ResultFactory.generateResNotFoundResult();
        }
        mapContext.put(WebConstant.KEY_ENTITY_ID,id);
        this.sysSmsService.updateByMapContext(mapContext);
        return ResultFactory.generateRequestResult(this.sysSmsService.findById(id));
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult delete(String id) {
        this.sysSmsService.deleteById(id);
        return ResultFactory.generateSuccessResult();
    }

    @Override
    public RequestResult selectSysSmsDtoList(Integer pageNum, Integer pageSize, MapContext params) {
        PaginatedFilter filter = PaginatedFilter.newOne();
        filter.setFilters(params);
        Pagination pagination = Pagination.newOne();
        pagination.setPageNum(pageNum);
        if (null == pageSize) {
            pageSize = this.sysSmsService.count();
        }
        pagination.setPageSize(pageSize);
        filter.setPagination(pagination);
        PaginatedList<SysSmsDto> list = this.sysSmsService.selectDtoByFilter(filter);
        return ResultFactory.generateRequestResult(list);
    }
}
