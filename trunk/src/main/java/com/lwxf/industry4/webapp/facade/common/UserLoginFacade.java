package com.lwxf.industry4.webapp.facade.common;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2019/3/29 9:31
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface UserLoginFacade extends BaseFacade {

    RequestResult userLogin(MapContext userMap);

    RequestResult userPasswordLogin(MapContext userMap);
}
