package com.lwxf.industry4.webapp.facade.admin.factory.contentmng;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.contentmng.ContentsType;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

public interface ContentTypeFacade extends BaseFacade {

    RequestResult addContentType(ContentsType contentType);

    RequestResult findContentTypes();

    RequestResult updateContentType(MapContext mapContext);

    RequestResult deleteContentType(String id);

    RequestResult findByContentId(String contentId);

}
