package com.lwxf.industry4.webapp.facade.admin.factory.finance;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.financing.BankAccount;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

public interface BankAccountFacade  extends BaseFacade {

    RequestResult selectByFilter(MapContext mapContext, Integer pageNum, Integer pageSize);

    RequestResult selectByFilter(MapContext mapContext);

    RequestResult addBankAccount(BankAccount bankAccount);

    RequestResult deleteBankAccount(String bankAccountId);

    RequestResult updateBankAccount(String bankAccountId, MapContext map);

    RequestResult viewBankAccountInfo(String id);

}
