package com.lwxf.industry4.webapp.facade.quickshare.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lwxf.industry4.webapp.bizservice.quickshare.MicroblogCommentService;
import com.lwxf.industry4.webapp.bizservice.quickshare.MicroblogService;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.LwxfAssert;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;
import com.lwxf.industry4.webapp.domain.entity.quickshare.MicroblogComment;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.quickshare.MicroblogCommentFacade;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.quickshare.MicroblogCommentService;
import com.lwxf.industry4.webapp.bizservice.quickshare.MicroblogService;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.facade.quickshare.MicroblogCommentFacade;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2018/7/9 9:46
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component(value = "microblogCommentFacade")
public class MicroblogCommentFacadeImpl extends BaseFacadeImpl implements MicroblogCommentFacade {
	@Resource(name = "microblogCommentService")
	private MicroblogCommentService microblogCommentService;
	@Resource(name = "microblogService")
	private MicroblogService microblogService;
	@Resource(name ="userService" )
	private UserService userService;

	/**
	 * 添加评论
	 * @param microblogComment
	 * @param blogId
	 * @return
	 */
	@Override
	@Transactional
	public RequestResult addMicroblogComment(MicroblogComment microblogComment, String blogId) {
		microblogComment.setMicroblogId(blogId);
		microblogComment.setCreated(DateUtil.getSystemDate());
		microblogComment.setCreator(WebUtils.getCurrUserId());
		microblogComment.setDisabled(true);
		RequestResult result = microblogComment.validateFields();
		if(null != result){
			return result;
		}
		this.microblogCommentService.add(microblogComment);
		return ResultFactory.generateRequestResult(microblogComment);
	}


	/**
	 * 删除快享帖子评论
	 * @param id
	 * @return
	 */
	@Override
	@Transactional
	public RequestResult delMicroblogCommentById(String id, String commentId) {
		LwxfAssert.notNull(null,"该方法还没实现");
		/*User user = WebUtils.getCurrUser();
		MicroblogComment comment = this.microblogCommentService.findById(commentId);
		if(null == comment){
			return ResultFactory.generateSuccessResult();
		}
		if(!comment.getMicroblogId().equals(id)){
			return ResultFactory.generateResNotFoundResult();
		}
		CompanyEmployee employee = AppBeanInjector.companyEmployeeService.selectByCompanyIdAndUserId(AppBeanInjector.configuration.getCompanyId(),user.getId());
		if(null == employee){
			if(!comment.getCreator().equals(WebUtils.getCurrUserId())){
				 return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NO_PERMISSION_10003,AppBeanInjector.i18nUtil.getMessage("BIZ_NO_PERMISSION_10003"));
			}
		}
		this.microblogCommentService.updateByParentId(commentId);
		this.microblogCommentService.deleteById(commentId);*/
		return ResultFactory.generateSuccessResult();
	}
}
