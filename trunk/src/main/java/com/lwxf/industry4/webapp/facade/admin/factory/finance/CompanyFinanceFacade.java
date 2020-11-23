package com.lwxf.industry4.webapp.facade.admin.factory.finance;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.company.CompanyPaymentInfoDto;
import com.lwxf.industry4.webapp.domain.entity.financing.Payment;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CompanyFinanceFacade extends BaseFacade {

    RequestResult findCompanyFinanceList(MapContext mapContext, Integer pageNum, Integer pageSize);

    RequestResult findAccountListInfo(MapContext mapContext, Integer pageNum, Integer pageSize);

    RequestResult editCompanyPayment(MapContext mapContext);

    RequestResult getCompanyFinanceById(String paymentId);

    RequestResult addCompanyPayment(CompanyPaymentInfoDto companyPaymentInfoDto);

    RequestResult uploadImage(String userId, String companyId, List<MultipartFile> multipartFile);

    RequestResult deleteById(String paymentSimpleId);

    RequestResult countPaymentForPageIndex();

    RequestResult findCompanyCapitalCount(MapContext mapContext);
}
