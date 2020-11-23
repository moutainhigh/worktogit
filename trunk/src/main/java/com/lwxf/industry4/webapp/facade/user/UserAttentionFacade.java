package com.lwxf.industry4.webapp.facade.user;


import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.user.UserAttention;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/9/18/018 11:25
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface UserAttentionFacade extends BaseFacade {
	RequestResult addOrDeleteAttrent(UserAttention userAttention);
	RequestResult findByUserId(String userId);
}
