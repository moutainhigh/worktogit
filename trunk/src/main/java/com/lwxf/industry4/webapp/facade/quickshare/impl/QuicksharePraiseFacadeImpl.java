package com.lwxf.industry4.webapp.facade.quickshare.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.bizservice.quickshare.QuicksharePraiseService;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.entity.quickshare.QuicksharePraise;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.quickshare.QuicksharePraiseFacade;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/3/6 0006 15:08
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("quicksharePraiseFacade")
public class QuicksharePraiseFacadeImpl extends BaseFacadeImpl implements QuicksharePraiseFacade {
	@Resource(name = "quicksharePraiseService")
	private QuicksharePraiseService quicksharePraiseService;
    @Resource(name="userService")
	private UserService userService;
	/**
	 * 点赞/取消点赞
	 * @param quickshareId
	 * @param request
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateQuicksharePraise(String quickshareId, HttpServletRequest request) {
		String memberId=request.getHeader("X-UID");
		if(memberId.equals("")||memberId==null){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_REQUEST_PARAM_ERROR_10000, AppBeanInjector.i18nUtil.getMessage("BIZ_REQUEST_PARAM_ERROR_10000"));
		}
		QuicksharePraise quicksharePraise=this.quicksharePraiseService.selectByMemberIdAndQuickshareId(memberId,quickshareId);
		if(quicksharePraise==null){
			QuicksharePraise quicksharePra=new QuicksharePraise();
			quicksharePra.setQuickshareId(quickshareId);
			quicksharePra.setMemberId(memberId);
			quicksharePra.setCreated(DateUtil.getSystemDate());
			this.quicksharePraiseService.add(quicksharePra);

		}else {
			this.quicksharePraiseService.deleteByQuiIdAndUid(quickshareId,memberId);
		}
		User user=this.userService.findByUserId(memberId);
		String userAvatar=user.getAvatar();
		return ResultFactory.generateRequestResult(userAvatar);
	}
}
