package com.lwxf.industry4.webapp.facade.admin.factory.finance;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.utils.excel.impl.PaymentExcelParam;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/1/9/009 10:02
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface FinanceFacade extends BaseFacade {
	RequestResult findOrderFinanceList(MapContext mapContext,Integer pageNum,Integer pageSize);

	RequestResult findPurchaseList(MapContext mapContext, Integer pageNum, Integer pageSize);

	RequestResult findDealerPayList(MapContext mapContext, Integer pageNum, Integer pageSize);

	RequestResult updateFinancePayStatus(String id,Integer type);

	RequestResult findSupplierList(MapContext mapContext, Integer pageNum, Integer pageSize);

	RequestResult updateSupplier(String id, Integer status);

	RequestResult findAftersaleList(MapContext mapContext, Integer pageNum, Integer pageSize);

	RequestResult findDealerList(MapContext mapContext, Integer pageNum, Integer pageSize);

	RequestResult updateDealerPay(String id);

	RequestResult updateCoordinationPay(String id,MapContext mapContext);

	RequestResult updateCustomOrdersPay(String id, MapContext map);


	RequestResult findOrderFinanceInfo(String paymentId);

	RequestResult findPaymentInfo(String id);

	RequestResult findFinanceOverview();

	RequestResult uploadFiles(String id, List<MultipartFile> multipartFileList);

	RequestResult deleteFile(String fileId);

	RequestResult counterTrialOrdersPay(String id);

    RequestResult findDealerPaymentCountInfo(String timeType, String startTime, String endTime);

	RequestResult findOrderFinanceCountinfo(MapContext mapContext);


	RequestResult writePayemntList(MapContext mapContext, Integer pageNum, Integer pageSize, PaymentExcelParam paymentExcelParam, StringBuffer params);

	RequestResult fastQuitCustomOrdersPay(String id, MapContext map);
}
