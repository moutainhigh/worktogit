package com.lwxf.industry4.webapp.facade.wxapi.factory.statements.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.wxapi.factory.statements.StatementFacade;

@Component("wxStatementFacade")
public class StatementFacadeImpl  extends BaseFacadeImpl implements StatementFacade {

    @Resource(name = "customOrderService")
    private CustomOrderService customOrderService;


    @Override
    public RequestResult viewStatement(String branchId) {
        return ResultFactory.generateRequestResult(customOrderService.statementWxFactory(branchId));
    }


}
