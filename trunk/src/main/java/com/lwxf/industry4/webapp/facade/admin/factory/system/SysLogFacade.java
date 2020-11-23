package com.lwxf.industry4.webapp.facade.admin.factory.system;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

public interface SysLogFacade extends BaseFacade {
    RequestResult  selectSysLogList(Integer pageNum, Integer pageSize, MapContext params);
}
