package com.lwxf.industry4.webapp.facade.admin.factory.aftersale;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.aftersale.AftersaleDtoForAdd;
import com.lwxf.industry4.webapp.domain.dto.customorder.ProduceOrderDto;
import com.lwxf.industry4.webapp.domain.entity.aftersale.AftersaleApply;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AfterSaleApplyV2Facade extends BaseFacade {

    RequestResult findFactoryAftersaleApply(String companyId);

    RequestResult factoryAftersaleApplyInfo(String aftersaleApplyId);

    RequestResult findDealersList(MapContext params);

    RequestResult findCustomerList(String dealerId);

    RequestResult findAftersaleApplyList(Integer pageNum, Integer pageSize, MapContext mapContext);

    RequestResult addFactoryAftersaleRequest(AftersaleApply aftersaleApply);

    RequestResult addFactoryAftersale(AftersaleDtoForAdd aftersaleApply);

    RequestResult handleFactoryAftersale(MapContext mapContext);

    RequestResult updateAftersaleStatus(String aftersaleId, String status);

    RequestResult deleteAftersale(String aftersaleId);

    RequestResult countAftersaleForPageIndex();

    RequestResult addFiles(List<MultipartFile> multipartFiles);
    RequestResult editAftersaleApply(MapContext mapContext);

	RequestResult addProduceAftersaleOrder(String id, ProduceOrderDto produceOrderDto, List<String> fileIds);

    RequestResult findAftersaleProduct(String id);

    RequestResult findAftersaleOrderList(MapContext mapContext, Integer pageNum, Integer pageSize);
}
