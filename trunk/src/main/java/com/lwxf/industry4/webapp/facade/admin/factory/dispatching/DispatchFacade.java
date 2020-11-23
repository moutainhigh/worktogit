package com.lwxf.industry4.webapp.facade.admin.factory.dispatching;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.dispatch.DispatchBillDto;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/25/025 13:56
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface DispatchFacade extends BaseFacade {

	RequestResult findDispatchList(MapContext mapContext, Integer pageNum, Integer pageSize);

	RequestResult addDispatch(DispatchBillDto dispatchBillDto);

	RequestResult deleteById(String id);

//	RequestResult deliverById(MapContext mapContext, String id);
	RequestResult updateDispatch(MapContext mapContext, String cid);

	RequestResult uploadFiles(String id, List<MultipartFile> multipartFileList);

	RequestResult deleteFile(String fileId);

	RequestResult findDispatchPrintInfo(String id);

	RequestResult findDispatchBillCount(String branchId);

	RequestResult findDispatchBillInfo(String dispatchBillId);
}
