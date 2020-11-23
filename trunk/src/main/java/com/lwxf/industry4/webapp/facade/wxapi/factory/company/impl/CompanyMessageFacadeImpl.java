package com.lwxf.industry4.webapp.facade.wxapi.factory.company.impl;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.company.CompanyMessageService;
import com.lwxf.industry4.webapp.bizservice.company.CompanyService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.company.CompanyMessageDto;
import com.lwxf.industry4.webapp.domain.entity.company.Company;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyMessage;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.wxapi.factory.company.CompanyMessageFacade;
import com.lwxf.mybatis.utils.MapContext;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component(value = "wxCompanyMessageFacade")
public class CompanyMessageFacadeImpl extends BaseFacadeImpl implements CompanyMessageFacade {
    @Resource(name = "companyMessageService")
    private CompanyMessageService companyMessageService;
    @Resource(name = "companyService")
    private CompanyService companyService;

    @Override
    public RequestResult findCompanyMessageList(MapContext mapContext, Integer pageNum, Integer pageSize) {
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        Pagination pagination = new Pagination();
        pagination.setPageNum(pageNum);
        pagination.setPageSize(pageSize);
        paginatedFilter.setPagination(pagination);
        mapContext.put(WebConstant.KEY_ENTITY_STATUS, 0);
        //mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
        paginatedFilter.setFilters(mapContext);
        return ResultFactory.generateRequestResult(this.companyMessageService.selectDtoByFilter(paginatedFilter));
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult sendCompanyMessage(CompanyMessage cmpanyMessage) {

        return ResultFactory.generateRequestResult(this.companyMessageService.add(cmpanyMessage));
    }

    @Override
    public RequestResult getCompanyList(MapContext map) {
        List<Company> list =companyService.findAllCompany(map);
        return ResultFactory.generateRequestResult(list);
    }

    @Override
    public RequestResult findMessagesByFromuser(Integer pageNum, Integer pageSize,MapContext map) {
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        Pagination pagination = new Pagination();
        pagination.setPageNum(pageNum);
        pagination.setPageSize(pageSize);
        paginatedFilter.setPagination(pagination);
        paginatedFilter.setFilters(map);
        PaginatedList<CompanyMessageDto> companyMessageDtos=this.companyMessageService.findMessagesByFromuser(paginatedFilter);
        return ResultFactory.generateRequestResult(companyMessageDtos);
    }
}
