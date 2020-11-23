package com.lwxf.industry4.webapp.facade.quickshare;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.industry4.webapp.common.result.RequestResult;

/**
 * 功能：
 *
 * @author：dell
 * @create：2018/7/6 14:25
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface MicroblogPraiseFacade extends BaseFacade {

	RequestResult addMicroblogPraise(String microblogId);

	RequestResult deleteMicroblogPraises(String microblogId);
}
