package com.lwxf.industry4.webapp.facade.admin.factory.CorporatePartners.impl;

import javax.annotation.Resource;

import java.util.*;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.baseservice.cache.constant.RedisConstant;
import com.lwxf.industry4.webapp.baseservice.sms.yunpian.SmsUtil;
import com.lwxf.industry4.webapp.bizservice.company.CompanyEmployeeService;
import com.lwxf.industry4.webapp.bizservice.company.EmployeeInfoService;
import com.lwxf.industry4.webapp.bizservice.system.RoleService;
import com.lwxf.industry4.webapp.bizservice.user.UserBasisService;
import com.lwxf.industry4.webapp.bizservice.user.UserExtraService;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.bizservice.user.UserThirdInfoService;
import com.lwxf.industry4.webapp.common.authcode.AuthCodeUtils;
import com.lwxf.industry4.webapp.common.enums.user.EmployeeInfoStatus;
import com.lwxf.industry4.webapp.common.enums.user.UserSex;
import com.lwxf.industry4.webapp.common.enums.user.UserState;
import com.lwxf.industry4.webapp.common.enums.user.UserType;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.uniquecode.UniquneCodeGenerator;
import com.lwxf.industry4.webapp.common.utils.UserExtraUtil;
import com.lwxf.industry4.webapp.domain.dao.company.CompanyEmployeeDao;
import com.lwxf.industry4.webapp.domain.dto.corporatePartners.CorporatePartnersDto;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeeInfo;
import com.lwxf.industry4.webapp.domain.entity.system.Role;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.user.UserBasis;
import com.lwxf.industry4.webapp.domain.entity.user.UserExtra;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lwxf.industry4.webapp.bizservice.corporatePartners.CorporatePartnersService;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationMoudule;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationType;
import com.lwxf.industry4.webapp.common.aop.syslog.SysOperationLog;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.corporatePartners.CorporatePartners;
import com.lwxf.industry4.webapp.facade.admin.factory.CorporatePartners.CorporatePartnersFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/8/1/001 14:26
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("corporatePartnersFacade")
public class CorporatePartnersFacadeImpl extends BaseFacadeImpl implements CorporatePartnersFacade {

	@Resource(name = "corporatePartnersService")
	private CorporatePartnersService corporatePartnersService;


	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "companyEmployeeService")
	private CompanyEmployeeService companyEmployeeService;

	@Resource(name = "roleService")
	private RoleService roleService;

    @Resource(name = "userExtraService")
    private UserExtraService userExtraService;

    @Resource(name = "userThirdInfoService")
    private UserThirdInfoService userThirdInfoService;

    @Resource(name = "userBasisService")
    private UserBasisService userBasisService;

    @Resource(name = "employeeInfoService")
    private EmployeeInfoService employeeInfoService;

    @Resource(name = "companyEmployeeDao")
    private CompanyEmployeeDao companyEmployeeDao;


	@Override
	public RequestResult findCorporatePartnersList(MapContext mapContext, Integer pageSize, Integer pageNum) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,WebUtils.getCurrBranchId());
		paginatedFilter.setFilters(mapContext);
		List<Map<String,String>> sort = new ArrayList<Map<String,String>>();
		HashMap<String, String> created = new HashMap<>();
		created.put(WebConstant.KEY_ENTITY_CREATED,"desc");
		sort.add(created);
		paginatedFilter.setSorts(sort);
        PaginatedList<CorporatePartnersDto> listByFilter = this.corporatePartnersService.findListByFilter(paginatedFilter);
        List<CorporatePartnersDto> rows = listByFilter.getRows();
        for (CorporatePartnersDto corporatePartnersDto:rows){
            String id = corporatePartnersDto.getId();
            CompanyEmployee companyEmployee = companyEmployeeDao.selectById(id);
            if(companyEmployee !=null){
                corporatePartnersDto.setUid(companyEmployee.getUserId());
                //是否启用pc登录
                User byUserId = userService.findByUserId(companyEmployee.getUserId());
                corporatePartnersDto.setEnableLoginPc(byUserId.getEnableLoginPc());

            }

        }
        return ResultFactory.generateRequestResult(listByFilter);
	}

    @Transactional(value = "transactionManager")
    @Override
    public RequestResult addoutAccount(CorporatePartners corporatePartners) {

        MapContext mapContext = new MapContext();
        mapContext.put("contactName",corporatePartners.getContactName());
        mapContext.put("tel",corporatePartners.getTel());
        mapContext.put("id",corporatePartners.getId());
        corporatePartnersService.updateByMapContext(mapContext);
        // TODO 添加角色
        //用户信息
        User user = new User();
        String name = corporatePartners.getContactName();
        user.setName(name);
        String tel = corporatePartners.getTel();
        user.setMobile(tel);
        user.setType(UserType.FACTORY.getValue());
        user.setSex(UserSex.MAN.getValue());
        user.setAvatar(AppBeanInjector.configuration.getUserDefaultAvatar());
        user.setTimeZone(WebConstant.TIMEZONE_DEFAULT);
        user.setLanguage(WebConstant.LANGUAGE_DEFAULT);
        user.setCreated(DateUtil.getSystemDate());
        user.setState(UserState.ENABLED.getValue()); // 默认公司员工启用状态
        user.setLoginName(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.USER_LOGNAME_NO));
        user.setFollowers(0);
        user.setChangedLoginName(false);
        user.setBranchId(WebUtils.getCurrBranchId());
        user.setEnableLoginPc(WebConstant.ENABLE); // 默认可以登录PC登录
        user.setEnableLoginWx(WebConstant.DISABLE); // 默认禁止微信小程序登录
        userService.add(user);
        String currBranchId = WebUtils.getCurrBranchId();
        CompanyEmployee companyEmployee = new CompanyEmployee();
        companyEmployee.setCompanyId(currBranchId);
        companyEmployee.setCreated(new Date());
        Role outfacturer = roleService.selectByKey("outfacturer", currBranchId);//查询外协角色
        companyEmployee.setRoleId(outfacturer.getId());
        companyEmployee.setStatus(0);
        companyEmployee.setUserId(user.getId());
        companyEmployee.setCorporatePartnersId(corporatePartners.getId());
        companyEmployeeService.add(companyEmployee);//插入公司雇员表

        //用户扩展信息
        StringBuffer pwd = new StringBuffer();
        UserExtra userExtra = new UserExtra();
        userExtra.setUserId(user.getId());
        pwd.append("111111");
        UserExtraUtil.saltingPassword(userExtra,new Md5Hash(pwd.toString()).toString());
        this.userExtraService.add(userExtra);

        // 第三方账号信息
        UserThirdInfo userThirdInfo = new UserThirdInfo();
        userThirdInfo.setWxNickname(user.getMobile());
        userThirdInfo.setWxIsBind(Boolean.FALSE);
        userThirdInfo.setWxIsSubscribe(Boolean.FALSE);
        userThirdInfo.setEmailIsBind(Boolean.FALSE);
        userThirdInfo.setMobileIsBind(Boolean.FALSE);
        userThirdInfo.setUserId(user.getId());
        userThirdInfo.setCompanyId(WebUtils.getCurrCompanyId());
        userThirdInfo.setBranchId(WebUtils.getCurrBranchId());
        userThirdInfo.setAppToken(UserExtraUtil.generateAppToken(userExtra,null));
        AppBeanInjector.redisUtils.hPut(RedisConstant.PLATFORM_TAG, user.getId(), Integer.valueOf(1));
        this.userThirdInfoService.add(userThirdInfo);

        //用户基本信息表
        UserBasis userBasis = new UserBasis();
        userBasis.setUserId(user.getId());
        this.userBasisService.add(userBasis);

        //新增员工时 设置 员工信息 初始数据
        EmployeeInfo employeeInfo = new EmployeeInfo();
        employeeInfo.setCompanyEmployeeId(companyEmployee.getId());
        employeeInfo.setUserId(user.getId());
        employeeInfo.setStatus(EmployeeInfoStatus.NORMAL.getValue()+"");
        this.employeeInfoService.add(employeeInfo);

        return ResultFactory.generateSuccessResult();
    }

    @Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "创建外协厂家",operationType = OperationType.INSERT,operationMoudule = OperationMoudule.CORPORATE_PARTNERS)
	public RequestResult addCorporatePartners(CorporatePartners corporatePartners) {
        this.corporatePartnersService.add(corporatePartners);

		// TODO 添加角色
		//用户信息
		User user = new User();
        String name = corporatePartners.getContactName();
        user.setName(name);
		String tel = corporatePartners.getTel();
		user.setMobile(tel);
		user.setType(UserType.FACTORY.getValue());
		user.setSex(UserSex.MAN.getValue());
		user.setAvatar(AppBeanInjector.configuration.getUserDefaultAvatar());
		user.setTimeZone(WebConstant.TIMEZONE_DEFAULT);
		user.setLanguage(WebConstant.LANGUAGE_DEFAULT);
		user.setCreated(DateUtil.getSystemDate());
		user.setState(UserState.ENABLED.getValue()); // 默认公司员工启用状态
		user.setLoginName(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.USER_LOGNAME_NO));
		user.setFollowers(0);
		user.setChangedLoginName(false);
		user.setBranchId(WebUtils.getCurrBranchId());
		user.setEnableLoginPc(WebConstant.ENABLE); // 默认可以登录PC登录
		user.setEnableLoginWx(WebConstant.DISABLE); // 默认禁止微信小程序登录
		userService.add(user);
        String currBranchId = WebUtils.getCurrBranchId();
        CompanyEmployee companyEmployee = new CompanyEmployee();
        companyEmployee.setCompanyId(currBranchId);
        companyEmployee.setCreated(new Date());
        Role outfacturer = roleService.selectByKey("outfacturer", currBranchId);//查询外协角色
        companyEmployee.setRoleId(outfacturer.getId());
        companyEmployee.setStatus(0);
        companyEmployee.setUserId(user.getId());
        companyEmployee.setCorporatePartnersId(corporatePartners.getId());
        companyEmployeeService.add(companyEmployee);//插入公司雇员表

        //用户扩展信息
        StringBuffer pwd = new StringBuffer();
        UserExtra userExtra = new UserExtra();
        userExtra.setUserId(user.getId());
        pwd.append("111111");
        UserExtraUtil.saltingPassword(userExtra,new Md5Hash(pwd.toString()).toString());
        this.userExtraService.add(userExtra);

        // 第三方账号信息
        UserThirdInfo userThirdInfo = new UserThirdInfo();
        userThirdInfo.setWxNickname(user.getMobile());
        userThirdInfo.setWxIsBind(Boolean.FALSE);
        userThirdInfo.setWxIsSubscribe(Boolean.FALSE);
        userThirdInfo.setEmailIsBind(Boolean.FALSE);
        userThirdInfo.setMobileIsBind(Boolean.FALSE);
        userThirdInfo.setUserId(user.getId());
        userThirdInfo.setCompanyId(WebUtils.getCurrCompanyId());
        userThirdInfo.setBranchId(WebUtils.getCurrBranchId());
        userThirdInfo.setAppToken(UserExtraUtil.generateAppToken(userExtra,null));
        AppBeanInjector.redisUtils.hPut(RedisConstant.PLATFORM_TAG, user.getId(), Integer.valueOf(1));
        this.userThirdInfoService.add(userThirdInfo);

        //用户基本信息表
        UserBasis userBasis = new UserBasis();
        userBasis.setUserId(user.getId());
        this.userBasisService.add(userBasis);

        //新增员工时 设置 员工信息 初始数据
        EmployeeInfo employeeInfo = new EmployeeInfo();
        employeeInfo.setCompanyEmployeeId(companyEmployee.getId());
        employeeInfo.setUserId(user.getId());
        employeeInfo.setStatus(EmployeeInfoStatus.NORMAL.getValue()+"");
        this.employeeInfoService.add(employeeInfo);


		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "修改外协厂家",operationType = OperationType.UPDATE,operationMoudule = OperationMoudule.CORPORATE_PARTNERS)
	public RequestResult updateCorporatePartners(String id, MapContext update) {
		update.put(WebConstant.KEY_ENTITY_ID,id);
		this.corporatePartnersService.updateByMapContext(update);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "删除外协厂家",operationType = OperationType.DELETE,operationMoudule = OperationMoudule.CORPORATE_PARTNERS)
	public RequestResult deleteCorporatePartners(String id) {
		this.corporatePartnersService.deleteById(id);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult findCorporatePartnersInfo(String id) {
		return ResultFactory.generateRequestResult(this.corporatePartnersService.findCorporatePartnersInfo(id));
	}
}
