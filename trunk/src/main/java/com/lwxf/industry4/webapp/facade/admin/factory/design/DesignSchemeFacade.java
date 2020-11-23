package com.lwxf.industry4.webapp.facade.admin.factory.design;

import org.springframework.web.multipart.MultipartFile;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.design.DesignScheme;
import com.lwxf.industry4.webapp.domain.entity.design.SchemeContent;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/2/26/026 9:59
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface DesignSchemeFacade extends BaseFacade {
	RequestResult findDesignSchemeList(MapContext mapContext,Integer pageNum,Integer pageSize);

	RequestResult addDesignScheme(DesignScheme designScheme);

	RequestResult uploadDesignSchemeFile(String id,Integer type, MultipartFile multipartFile,String contentId);

	RequestResult updataDesignScheme(String id, MapContext mapContext);

	RequestResult addDesignContent(String id, SchemeContent schemeContent);

	RequestResult updateSchemeContent(String id, String contentId, MapContext mapContext);

	RequestResult findDesignSchemeInfo(String id);

	RequestResult deleteDesignSchemeContentFile(String id, String contentId);

	RequestResult deleteDesignSchemeContent(String id, String contentId);

	RequestResult designSchemeExamine(String id, MapContext mapContext);

	RequestResult updateDesignSchemeStatus(String id,Integer status);
}
