package com.lwxf.industry4.webapp.facade.quickshare.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.quickshare.MicroblogPraiseService;
import com.lwxf.industry4.webapp.bizservice.quickshare.MicroblogService;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.quickshare.MicroblogPraise;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.quickshare.MicroblogPraiseFacade;
import com.lwxf.industry4.webapp.bizservice.quickshare.MicroblogPraiseService;
import com.lwxf.industry4.webapp.bizservice.quickshare.MicroblogService;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.facade.quickshare.MicroblogPraiseFacade;

/**
 * 功能：
 *
 * @author：dell
 * @create：2018/7/6 14:26
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("microblogPraiseFacade")
public class MicroblogPraiseFacadeImpl extends BaseFacadeImpl implements MicroblogPraiseFacade {

	@Resource(name = "microblogService")
	private MicroblogService microblogService;
	@Resource(name = "microblogPraiseService")
	private MicroblogPraiseService microblogPraiseService;
	@Resource(name = "userService")
	private UserService userService;

	@Override
	@Transactional
	public RequestResult addMicroblogPraise(String microblogId) {
		if(this.microblogService.findById(microblogId).getStatus() == 0){
			return ResultFactory.generateResNotFoundResult();
		}
		MicroblogPraise microblogPraise = new MicroblogPraise();
		microblogPraise.setMicroblogId(microblogId);
		microblogPraise.setCreated(DateUtil.getSystemDate());
		microblogPraise.setMemberId(WebUtils.getCurrUserId());
		this.microblogPraiseService.add(microblogPraise);
		return ResultFactory.generateRequestResult(microblogPraise);
	}

	@Override
	@Transactional
	public RequestResult deleteMicroblogPraises(String microblogId) {
		this.microblogPraiseService.deleteByMicroblog(microblogId);
		return ResultFactory.generateSuccessResult();
	}
}
