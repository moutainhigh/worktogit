package com.lwxf.industry4.webapp.facade.admin.factory.common;

import org.springframework.web.multipart.MultipartFile;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.version.UpdateVersion;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/5/13/013 9:56
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface FUpdateVersionFacade extends BaseFacade {
	RequestResult findVersionList(MapContext mapContext, Integer pageNum, Integer pageSize);

	RequestResult addUpdateVersion(UpdateVersion updateVersion);

	RequestResult uploadFiles(String id, MultipartFile multipartFile);

	RequestResult updateVersion(String id, MapContext mapContext);

	RequestResult deleteVersion(String id);
}
