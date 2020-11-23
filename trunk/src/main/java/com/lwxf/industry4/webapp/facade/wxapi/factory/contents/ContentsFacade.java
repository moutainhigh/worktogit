package com.lwxf.industry4.webapp.facade.wxapi.factory.contents;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.mybatis.utils.MapContext;

public interface ContentsFacade {

    RequestResult findContents(MapContext mapContext, Integer pageNum, Integer pageSize);

    RequestResult findByContentId(String contentId);
}
