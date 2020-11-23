package com.lwxf.industry4.webapp.facade.wxapi.dealer.user.impl;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import org.apache.shiro.crypto.hash.Md5Hash;

import com.lwxf.commons.constant.CommonConstant;
import com.lwxf.industry4.webapp.bizservice.company.CompanyService;
import com.lwxf.industry4.webapp.bizservice.contentmng.ContentsService;
import com.lwxf.industry4.webapp.bizservice.dept.DeptService;
import com.lwxf.industry4.webapp.bizservice.user.UserBasisService;
import com.lwxf.industry4.webapp.bizservice.user.UserExtraService;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.bizservice.user.UserThirdInfoService;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.constant.BizConstant;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.enums.company.EmployeeStatus;
import com.lwxf.industry4.webapp.common.enums.content.ContentsType;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.UserExtraUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.company.WxDealerDto;
import com.lwxf.industry4.webapp.domain.dto.companyEmployee.CompanyEmployeeDto;
import com.lwxf.industry4.webapp.domain.dto.companyEmployee.WxDealerUserInfoDto;
import com.lwxf.industry4.webapp.domain.dto.user.LoginedUser;
import com.lwxf.industry4.webapp.domain.entity.company.Company;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;
import com.lwxf.industry4.webapp.domain.entity.contentmng.Contents;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.user.UserExtra;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.wxapi.dealer.user.UserFacade;
import com.lwxf.mybatis.utils.MapContext;

import static com.lwxf.industry4.webapp.facade.AppBeanInjector.baseFileUploadComponent;
import static com.lwxf.industry4.webapp.facade.AppBeanInjector.i18nUtil;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/6/14/014 10:20
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("wxBUserFacade")
public class UserFacadeImpl extends BaseFacadeImpl implements UserFacade {

	@Resource(name = "companyService")
	private CompanyService companyService;

	@Resource(name = "deptService")
	private DeptService deptService;

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "userExtraService")
	private UserExtraService userExtraService;

	@Resource(name = "userThirdInfoService")
	private UserThirdInfoService userThirdInfoService;

	@Resource(name = "contentsService")
	private ContentsService contentsService;
	@Resource(name = "userBasisService")
	private UserBasisService userBasisService;



	@Override
	public RequestResult findDealerInfo(String uid) {
		//判断当前用户企业公司是否存在
		CompanyEmployee companyEmployee = AppBeanInjector.companyEmployeeService.selectByUserId(uid);
		if(companyEmployee==null){
			return ResultFactory.generateResNotFoundResult();
		}
		User user=this.userService.findByUserId(uid);
		String userName=user.getName();
		WxDealerDto wxDealerInfo = this.companyService.findWxDealerInfoByCId(companyEmployee.getCompanyId());
		wxDealerInfo.setDealerName(userName);
		// 获取负责人姓名
		if (wxDealerInfo != null && wxDealerInfo.getLeader() != null) {
			User leaderUser = this.userService.findByUserId(wxDealerInfo.getLeader());
			String leaderName = leaderUser == null ? "" : leaderUser.getName();
			wxDealerInfo.setLeaderName(leaderName);
		}


		return ResultFactory.generateRequestResult(wxDealerInfo);
	}

	@Override
	public RequestResult findDealerUserInfo(String uid) {
		WxDealerUserInfoDto dealerUserInfoByUid = this.companyService.findDealerUserInfoByUid(uid);
		dealerUserInfoByUid.setDepts(this.deptService.findListByUserId(uid));
		return ResultFactory.generateRequestResult(dealerUserInfoByUid);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateMobile(String uid,String oldMobile, String newMobile) {
		//判断是否是当前用户修改手机号
		LoginedUser currUser = AppBeanInjector.userFacade.findLoginedUserById(uid);
		if(!currUser.getMobile().equals(oldMobile)){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020,AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
		}
		//判断新手机号是否已存在
		User byMobile = this.userService.findByMobile(newMobile);
		if(byMobile!=null){
			Map<String, String> validResult = new HashMap<>();
			validResult.put("mobile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}
		MapContext mapContext = new MapContext();
		mapContext.put(WebConstant.KEY_ENTITY_MOBILE,newMobile);
		mapContext.put(WebConstant.KEY_ENTITY_ID,currUser.getId());
		this.userService.updateByMapContext(mapContext);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updatePassword(String uid,String oldPassword,String newPassword) {
		if (!this.userExtraService.isPasswordCorrect(uid,oldPassword)) {
			MapContext error = new MapContext();
			error.put("oldPassword",AppBeanInjector.i18nUtil.getMessage("VALIDATE_PASSWORD_INCORRECT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,error);
		}
		UserExtra userExtra = new UserExtra();
		UserExtraUtil.saltingPassword(userExtra,newPassword);
		MapContext toSave = new MapContext();
		toSave.put(WebConstant.KEY_USER_EXTRA_SALT, userExtra.getSalt());
		toSave.put(WebConstant.KEY_ENTITY_USER_ID, uid);
		toSave.put(WebConstant.KEY_USER_EXTRA_PASSWORD, userExtra.getPassword());
		toSave.put(WebConstant.KEY_USER_EXTRA_TOKEN, userExtra.getToken());
		this.userExtraService.updateByMapContext(toSave);
		toSave = MapContext.newOne();
		toSave.put(WebConstant.KEY_ENTITY_USER_ID, uid);
		toSave.put(WebConstant.KEY_ENTITY_APP_TOKEN, UserExtraUtil.generateAppToken(userExtra, null));
		this.userThirdInfoService.updateByMapContext(toSave);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult findHelps(String uid,String name,Integer pageNum,Integer pageSize) {
		if(name==null){
			return ResultFactory.generateRequestResult(this.contentsService.findTopContentsList(ContentsType.ANSWER.getValue(),AppBeanInjector.companyService.findById(AppBeanInjector.companyEmployeeService.findCompanyByUidAndStatus(uid,EmployeeStatus.NORMAL.getValue()).getCompanyId()).getBranchId()));
		}
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageSize(pageSize);
		pagination.setPageNum(pageNum);
		paginatedFilter.setPagination(pagination);
		MapContext mapContext = new MapContext();
		mapContext.put("contentsTypeId",ContentsType.ANSWER.getValue());
		mapContext.put(WebConstant.KEY_ENTITY_NAME,name);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,AppBeanInjector.companyService.findById(AppBeanInjector.companyEmployeeService.findCompanyByUidAndStatus(uid,EmployeeStatus.NORMAL.getValue()).getCompanyId()).getBranchId());
		paginatedFilter.setFilters(mapContext);
		Map<String,String> created = new HashMap<String, String>();
		created.put(WebConstant.KEY_ENTITY_CREATED,"desc");
		List sort = new ArrayList();
		sort.add(created);
		paginatedFilter.setSorts(sort);
		return ResultFactory.generateRequestResult(this.contentsService.selectByFilter(paginatedFilter));
	}

	@Override
	public RequestResult findHelpsInfo(String id) {
		Contents contents = this.contentsService.findById(id);
		if(contents==null){
			return ResultFactory.generateResNotFoundResult();
		}
		return ResultFactory.generateRequestResult(this.contentsService.findContentMessage(id));
	}

	@Override
	public RequestResult findUserInfo(String uid,String cid) {
		MapContext mapContext = MapContext.newOne();
		mapContext.put(WebConstant.KEY_ENTITY_USER_ID,uid);
		mapContext.put(WebConstant.KEY_ENTITY_COMPANY_ID, cid);
		User user=this.userService.findByUserId(uid);
		UserThirdInfo userThirdInfo=this.userThirdInfoService.findByUserId(uid);
		Company company=this.companyService.findById(cid);
		MapContext result=MapContext.newOne();
		result.put("userId",uid);
		result.put("userName",user.getName());
		result.put("mobile",user.getMobile());
		result.put("companyId",cid);
		result.put("companyName",company.getName());
		result.put("branchId",user.getBranchId());
		result.put("avatar",user.getAvatar());
		result.put("openId",userThirdInfo.getWxOpenId());
		return ResultFactory.generateRequestResult(result);
	}
	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateUserInfo(String userId,MapContext mapContext) {
		MapContext mapContext1=MapContext.newOne();
		if(mapContext.containsKey("sex")){
			Integer sex=mapContext.getTypedValue("sex",Integer.class);
			mapContext1.put("sex",sex);
		}
		if(mapContext.containsKey("avatar")){
			String avatar=mapContext.getTypedValue("avatar",String.class);
			mapContext1.put("avatar",avatar);
		}
		if(mapContext.containsKey("birthday")){
			String birthdayValue = mapContext.getTypedValue("birthday",String.class);
			if(CommonConstant.STRING_EMPTY.equals(birthdayValue )){
				birthdayValue = null;
			}
			mapContext1.put("birthday",birthdayValue);
		}
		if(mapContext1.size()>0) {
			mapContext1.put("id", userId);
			this.userService.updateByMapContext(mapContext1);
		}
		MapContext map=MapContext.newOne();
		if(mapContext.containsKey("identityNumber")){
			String identityNumber=mapContext.getTypedValue("identityNumber",String.class);
			if(!identityNumber.trim().equals("")){
				map.put("identityNumber",identityNumber);
			}
		}
		if(mapContext.containsKey("education")){
			String educationValue = mapContext.getTypedValue("education",String.class);
			if(CommonConstant.STRING_EMPTY.equals(educationValue )){
				educationValue = null;
			}
			map.put("education",educationValue);
		}
		if(map.size()>0) {
			map.put("userId", userId);
			this.userBasisService.updateByMapContext(map);
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult forgetpassword(MapContext mapContext) {
		String mobile=mapContext.getTypedValue("mobile",String.class);
		User user=this.userService.findByMobile(mobile);
		if(user==null){
			return ResultFactory.generateResNotFoundResult();
		}
		//新密码
		String newPassword = mapContext.getTypedValue("newPassword", String.class);
		//确认密码
		String affirmPassword= mapContext.getTypedValue("affirmPassword", String.class);
		if(!newPassword.equals(affirmPassword)){
			return ResultFactory.generateErrorResult(com.lwxf.commons.exception.ErrorCodes.VALIDATE_PASSWORD_IS_NOT_CONSISTENT,AppBeanInjector.i18nUtil.getMessage("VALIDATE_PASSWORD_IS_NOT_CONSISTENT"));
		}
		UserExtra userExtra = new UserExtra();
		UserExtraUtil.saltingPassword(userExtra,newPassword);
		String userId=user.getId();
		MapContext toSave = new MapContext();
		toSave.put(WebConstant.KEY_USER_EXTRA_SALT, userExtra.getSalt());
		toSave.put(WebConstant.KEY_ENTITY_USER_ID, userId);
		toSave.put(WebConstant.KEY_USER_EXTRA_PASSWORD, userExtra.getPassword());
		toSave.put(WebConstant.KEY_USER_EXTRA_TOKEN, userExtra.getToken());
		this.userExtraService.updateByMapContext(toSave);
		toSave = MapContext.newOne();
		toSave.put(WebConstant.KEY_ENTITY_USER_ID, userId);
		toSave.put(WebConstant.KEY_ENTITY_APP_TOKEN, UserExtraUtil.generateAppToken(userExtra, null));
		this.userThirdInfoService.updateByMapContext(toSave);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateAvatar(String userId, MultipartFile multipartFile) {

		MapContext userMap = MapContext.newOne();
		userMap.put(WebConstant.KEY_ENTITY_ID, userId);
		User user = userService.findById(userId);
		if (user == null) {
			String errMsg = i18nUtil.getMessage("BIZ_RES_NOT_FOUND_10001");
			return ResultFactory.generateErrorResult(com.lwxf.commons.exception.ErrorCodes.BIZ_RES_NOT_FOUND_10001, errMsg);
		}
		UploadInfo uploadInfo = baseFileUploadComponent.doUploadByModule(multipartFile, UploadResourceType.AVATAR, userId);
		userMap.put("avatar", uploadInfo.getRelativePath());
		try {
			//判断是系统新的头像地址进行删除
			if (user.getAvatar().startsWith(BizConstant.EASPYM4_FILE_DOMAIN) && !user.getAvatar().equals(AppBeanInjector.configuration.getUserDefaultAvatar())) {
				baseFileUploadComponent.deleteFile(user.getAvatar(), UploadResourceType.AVATAR, 0);
			}
			this.userService.updateByMapContext(userMap);
		} catch (RuntimeException ex) {
			baseFileUploadComponent.deleteFile(uploadInfo.getRelativePath(), UploadResourceType.AVATAR, 0);
			throw ex;
		}
		userMap.remove("id");
		return ResultFactory.generateRequestResult(userMap);
	}





	@Override
	@Transactional(value = "transactionManager")
	public RequestResult userlogout(String uid) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("userId",uid);
		mapContext.put("wxOpenId",null);
		return ResultFactory.generateRequestResult(this.userThirdInfoService.userlogout(mapContext));
	}
}
