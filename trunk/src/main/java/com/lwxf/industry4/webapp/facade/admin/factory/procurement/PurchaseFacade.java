package com.lwxf.industry4.webapp.facade.admin.factory.procurement;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.procurement.PurchaseRequestDto;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/17/017 11:15
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface PurchaseFacade extends BaseFacade {

	RequestResult findRequestList(MapContext mapContext, Integer pageNum, Integer pageSize);

	RequestResult addPurchaseRequest(PurchaseRequestDto purchaseRequestDto);

	RequestResult updatePurchaseRequest(String purchaseRequestId, PurchaseRequestDto requestDto);

	RequestResult updatePurchaseAuditStatus(String purchaseRequestId,MapContext mapContext);

	RequestResult updatePurchaseRequestStatus(String purchaseRequestId);

	RequestResult updatePurchaseProduct(String purchaseRequestId, PurchaseRequestDto requestDto);

	RequestResult findAllSupplierList(MapContext mapContext);

	RequestResult endPurchaseRequest(String purchaseRequestId, MapContext mapContext);
}
