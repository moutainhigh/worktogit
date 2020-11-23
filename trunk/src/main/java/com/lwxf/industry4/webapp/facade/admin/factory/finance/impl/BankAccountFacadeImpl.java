package com.lwxf.industry4.webapp.facade.admin.factory.finance.impl;

import com.lwxf.industry4.webapp.bizservice.financing.BankAccountService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.financing.BankAccount;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.finance.BankAccountFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Component("bankAccountFacade")
public class BankAccountFacadeImpl extends BaseFacadeImpl implements BankAccountFacade {

    @Resource(name = "bankAccountService")
    private BankAccountService bankAccountService;

    @Override
    public RequestResult selectByFilter(MapContext mapContext, Integer pageNum, Integer pageSize) {
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        Pagination pagination = new Pagination();
        pagination.setPageNum(pageNum);
        pagination.setPageSize(pageSize);
        paginatedFilter.setPagination(pagination);
        mapContext.put(WebConstant.KEY_ENTITY_STATUS, 0);
        paginatedFilter.setFilters(mapContext);
        List<Map<String,String>> sort = new ArrayList<Map<String, String>>();
        Map<String,String> orderNum = new HashMap<String, String>();
        orderNum.put("order_num","asc");
        sort.add(orderNum);
        paginatedFilter.setSorts(sort);
        return ResultFactory.generateRequestResult(this.bankAccountService.selectByFilter(paginatedFilter));
    }

    @Override
    public RequestResult selectByFilter(MapContext mapContext) {
        mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,WebUtils.getCurrBranchId());
        return ResultFactory.generateRequestResult(this.bankAccountService.selectByFilter(mapContext));
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult addBankAccount(BankAccount bankAccount) {
        String code=bankAccount.getCode();
        if(code!=null||!code.equals("")) {
            BankAccount bank =  this.bankAccountService.findByCode(code);
            if(bank!=null){
                return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_NOT_ALLOWED_REPEAT, AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
            }
        }
        bankAccount.setBranchId(WebUtils.getCurrBranchId());
        this.bankAccountService.add(bankAccount);
        return ResultFactory.generateRequestResult(bankAccount);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult deleteBankAccount(String bankAccountId) {
        return ResultFactory.generateRequestResult(this.bankAccountService.deleteById(bankAccountId));
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult updateBankAccount(String bankAccountId, MapContext map) {
        map.put("id",bankAccountId);
        return ResultFactory.generateRequestResult(this.bankAccountService.updateByMapContext(map));
    }

    @Override
    public RequestResult viewBankAccountInfo(String id) {
        return ResultFactory.generateRequestResult(this.bankAccountService.findById(id));
    }
}
