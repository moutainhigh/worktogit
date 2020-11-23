package com.lwxf.industry4.webapp.facade.user.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lwxf.industry4.webapp.bizservice.user.UserAttentionService;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.bizservice.user.UserThirdInfoService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.user.UserAttentionType;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.LwxfAssert;
import com.lwxf.industry4.webapp.domain.entity.user.UserAttention;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.user.UserAttentionFacade;
import com.lwxf.industry4.webapp.bizservice.user.UserAttentionService;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.bizservice.user.UserThirdInfoService;
import com.lwxf.industry4.webapp.common.enums.user.UserAttentionType;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.facade.user.UserAttentionFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/9/18/018 11:26
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("userAttentionFacade")
public class UserAttentionFacadeImpl extends BaseFacadeImpl implements UserAttentionFacade {
	private Logger logger = LoggerFactory.getLogger(UserAttentionFacadeImpl.class);
	@Resource(name = "userAttentionService")
	private UserAttentionService userAttentionService;

	@Resource(name = "userThirdInfoService")
	private UserThirdInfoService userThirdInfoService;

	@Resource(name = "userService")
	private UserService userService;
	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addOrDeleteAttrent(UserAttention userAttention) {
		MapContext mapContext = MapContext.newOne();
		//如果资源存在 则是取消关注 不存在 则是关注
		if(this.userAttentionService.isExistByUserIdAndResourceId(userAttention.getUserId(),userAttention.getResourceId())){
			mapContext.put(WebConstant.KEY_ENTITY_USER_ID,userAttention.getUserId());
			mapContext.put("resourceId",userAttention.getResourceId());
			this.userAttentionService.deleteByUserIdAndResourceId(mapContext);
			//相对应资源的粉丝数减少
			if(userAttention.getResourceType().equals(UserAttentionType.COMPANY.getValue())){
				LwxfAssert.notNull(null,"该发方法还没实现");
				//this.companyService.closeFollowers(userAttention.getResourceId());
			}else{
				this.userService.closeFollowers(userAttention.getResourceId());
			}
			return ResultFactory.generateSuccessResult();
		}
		//判断关注资源是否真实存在
		/*if(!this.userService.isExist(userAttention.getResourceId())&&!this.companyService.isExist(userAttention.getResourceId())){
			MapContext result = MapContext.newOne();
			result.put("resourceId",AppBeanInjector.i18nUtil.getMessage("VALIDATE_ILLEGAL_ARGUMENT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,result);
		}*/
		//判断用户是否真实存在
		if(!this.userService.isExist(userAttention.getUserId())){
			MapContext result = MapContext.newOne();
			result.put(WebConstant.KEY_ENTITY_USER_ID,AppBeanInjector.i18nUtil.getMessage("VALIDATE_ILLEGAL_ARGUMENT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,result);
		}
		//判断是否重复关注
		if(this.userAttentionService.isExistByUserIdAndResourceId(userAttention.getUserId(),userAttention.getResourceId())){
			return ResultFactory.generateSuccessResult();
		}
		this.userAttentionService.add(userAttention);
		//相对应资源的粉丝数增加
		if(userAttention.getResourceType().equals(UserAttentionType.COMPANY.getValue())){
			LwxfAssert.notNull(null,"该方法还没实现");
			//this.companyService.addFollowers(userAttention.getResourceId());
		}else{
			this.userService.addFollowers(userAttention.getResourceId());
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult findByUserId(String userId) {
		return ResultFactory.generateRequestResult(this.userAttentionService.findByUserId(userId));
	}
}
