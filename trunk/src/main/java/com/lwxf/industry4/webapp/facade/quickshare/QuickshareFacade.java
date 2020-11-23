package com.lwxf.industry4.webapp.facade.quickshare;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.quickshare.Quickshare;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/3/2 0002 14:16
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */

public interface QuickshareFacade extends BaseFacade{

	RequestResult findQuickshareList(Integer pageNum, Integer pageSize, MapContext mapContext, HttpServletRequest request);

	RequestResult uploadQuickshareImage(List<MultipartFile> files,HttpServletRequest request,String quickshareId);

	RequestResult addQuickshareAndImage(Quickshare quickshare,String userId);

	RequestResult deleteQuickshare(String quickshareId, HttpServletRequest request);
}
