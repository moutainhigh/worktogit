package com.lwxf.industry4.webapp.facade.admin.factory.aftersale;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.aftersale.AftersaleApply;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/1/8/008 10:03
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface AftersaleApplyFacade extends BaseFacade {
	RequestResult findListAftersale(MapContext mapContext,Integer pageNum,Integer pageSize);

	RequestResult addAftersale(String orderId, AftersaleApply aftersaleApply);

	RequestResult uploadImgFile(List<MultipartFile> multipartFileList,String id);

	RequestResult deleteFileImg(String id, String fileId);

	RequestResult updateAftersaleApply(String id, MapContext mapContext);

	RequestResult deleteAftersaleApply(String id);

	RequestResult findAftersalesPrintInfo(String id);

	RequestResult findAftersalesDetail(String id);

	RequestResult findAftersaleReport();
}
