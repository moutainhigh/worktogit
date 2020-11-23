package com.lwxf.industry4.webapp.facade.wxapi.dealer.company.impl;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.company.CompanyMessageService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyMessage;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.wxapi.dealer.company.CompanyMessagefacade;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component(value = "wxDealerCompanyMessageFacade")
public class CompanyMessageFacadeImpl  extends BaseFacadeImpl implements CompanyMessagefacade {

    @Resource(name = "companyMessageService")
    private CompanyMessageService companyMessageService;

    @Override
    public RequestResult findCompanyMessageList(MapContext mapContext, Integer pageNum, Integer pageSize) {
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        Pagination pagination = new Pagination();
        pagination.setPageNum(pageNum);
        pagination.setPageSize(pageSize);
        paginatedFilter.setPagination(pagination);
        mapContext.put(WebConstant.KEY_ENTITY_STATUS, 0);
        paginatedFilter.setFilters(mapContext);
        return ResultFactory.generateRequestResult(this.companyMessageService.findMessagesByFromuser(paginatedFilter));
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult sendCompanyMessage(CompanyMessage cmpanyMessage) {

        return ResultFactory.generateRequestResult(this.companyMessageService.add(cmpanyMessage));
    }
}
