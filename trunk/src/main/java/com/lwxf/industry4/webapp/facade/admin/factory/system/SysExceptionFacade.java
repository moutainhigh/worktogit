package com.lwxf.industry4.webapp.facade.admin.factory.system;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

public interface SysExceptionFacade extends BaseFacade {
    RequestResult  selectSysExceptionList(Integer pageNum, Integer pageSize, MapContext params);
}
