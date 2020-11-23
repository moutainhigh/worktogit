package com.lwxf.industry4.webapp.facade.admin.factory.system;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import com.lwxf.industry4.webapp.domain.entity.system.SysMessage;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

public interface SysMessageFacade extends BaseFacade {

    RequestResult addMessage(SysMessage basecode);

    RequestResult sendMessage(String messageId);

    RequestResult findMessageById(String messageId);

    RequestResult myMessageList(String userId);

    RequestResult myMessage(String messageId,String userId);

    RequestResult findMessageByMap(MapContext mapContext,Integer pageNum,Integer pageSize);

    RequestResult updateMessage(String messageId, MapContext parmas);

    RequestResult delete(String id);
}
