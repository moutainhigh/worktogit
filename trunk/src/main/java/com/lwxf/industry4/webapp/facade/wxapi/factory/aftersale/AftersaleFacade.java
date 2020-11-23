package com.lwxf.industry4.webapp.facade.wxapi.factory.aftersale;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.mybatis.utils.MapContext;

public interface AftersaleFacade {

    RequestResult findAftersaleApplyList(String branchId,Integer pageNum, Integer pageSize, MapContext mapContext);

    RequestResult factoryAftersaleApplyInfo(String aftersaleApplyId);

    RequestResult viewIndex(String branchId);
}
