package com.lwxf.industry4.webapp.facade.admin.factory.customermng.impl;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.commons.utils.ValidateUtils;
import com.lwxf.industry4.webapp.baseservice.cache.constant.RedisConstant;
import com.lwxf.industry4.webapp.bizservice.company.CompanyEmployeeService;
import com.lwxf.industry4.webapp.bizservice.company.CompanyService;
import com.lwxf.industry4.webapp.bizservice.customer.CompanyCustomerService;
import com.lwxf.industry4.webapp.bizservice.user.UserBasisService;
import com.lwxf.industry4.webapp.bizservice.user.UserExtraService;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.bizservice.user.UserThirdInfoService;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationMoudule;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationType;
import com.lwxf.industry4.webapp.common.aop.syslog.SysOperationLog;
import com.lwxf.industry4.webapp.common.authcode.AuthCodeUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.company.CompanyCustomerGrade;
import com.lwxf.industry4.webapp.common.enums.company.CompanyCustomerSource;
import com.lwxf.industry4.webapp.common.enums.company.CompanyCustomerStatus;
import com.lwxf.industry4.webapp.common.enums.user.*;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.uniquecode.UniquneCodeGenerator;
import com.lwxf.industry4.webapp.common.utils.AddressUtils;
import com.lwxf.industry4.webapp.common.utils.UserExtraUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.customer.CustomerDto;
import com.lwxf.industry4.webapp.domain.dto.customer.CustomerDtoV2;
import com.lwxf.industry4.webapp.domain.dto.user.UserAreaDto;
import com.lwxf.industry4.webapp.domain.entity.company.Company;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;
import com.lwxf.industry4.webapp.domain.entity.customer.CompanyCustomer;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.user.UserBasis;
import com.lwxf.industry4.webapp.domain.entity.user.UserExtra;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.admin.factory.customermng.CustomerFacade;
import com.lwxf.mybatis.utils.MapContext;

import org.apache.shiro.crypto.hash.Md5Hash;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.*;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2019/1/15 10:11
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("fCustomerFacade")
public class CustomerFacadeImpl extends BaseFacadeImpl implements CustomerFacade {
	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "companyCustomerService")
	private CompanyCustomerService companyCustomerService;
	@Resource(name = "userExtraService")
	private UserExtraService userExtraService;
	@Resource(name = "userThirdInfoService")
	private UserThirdInfoService userThirdInfoService;
	@Resource(name = "companyService")
	private CompanyService companyService;
	@Resource(name = "userBasisService")
	private UserBasisService userBasisService;
	@Resource(name = "companyEmployeeService")
	private CompanyEmployeeService companyEmployeeService;

	@Override
	public RequestResult findByClient(MapContext mapContext, Integer pageSize, Integer pageNum) {
		PaginatedFilter filter = PaginatedFilter.newOne();
		Pagination pagination = Pagination.newOne();
		pagination.setPageSize(pageSize);
		pagination.setPageNum(pageNum);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,WebUtils.getCurrBranchId());
		filter.setFilters(mapContext);
		filter.setPagination(pagination);
		PaginatedList<CustomerDtoV2> userList = this.companyCustomerService.findByClient(filter);
		List<CustomerDtoV2> rows = userList.getRows();
		for (CustomerDtoV2 u : rows) {
			String cityAreaName = u.getCityAreaName();
			String address = u.getAddress();
			String address1 = AddressUtils.getAddress(cityAreaName, null);
			u.setCityAreaName(address1);
		}
		return ResultFactory.generateRequestResult(userList);
	}


	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "创建终端客户",operationType = OperationType.INSERT,operationMoudule = OperationMoudule.CUSTOMER)
	public RequestResult addCustomer(CompanyCustomer customer, String uid) {
		//用户表基本信息
		String mobile = customer.getPhone();
//		List<CompanyEmployee> companyEmployees = this.companyEmployeeService.selectShopkeeperByCompanyIds(Arrays.asList(customer.getCompanyId()));
//		if (null == companyEmployees || companyEmployees.size() != 1) {
//			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_USER_NOT_FOUND_10002, AppBeanInjector.i18nUtil.getMessage("BIZ_USER_NOT_FOUND_10002"));
//		}
//		String customerManager = companyEmployees.get(0).getUserId();
		//验证电话号码是否正确
		if (mobile!=null){
			if (!ValidateUtils.isChinaPhoneNumber(mobile)) {
				Map<String, String> errorMap = new HashMap<>();
				errorMap.put(WebConstant.KEY_ENTITY_MOBILE, AppBeanInjector.i18nUtil.getMessage("VALIDATE_INVALID_MOBILE_NO"));
				return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errorMap);
			}
		}
		//判断客户是否存在 (公司 电话 姓名)
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		MapContext mapContext = new MapContext();
		mapContext.put(WebConstant.KEY_ENTITY_COMPANY_ID,customer.getCompanyId());
		mapContext.put(WebConstant.KEY_ENTITY_NAME,customer.getName());
		mapContext.put(WebConstant.KEY_ENTITY_PHONE,customer.getPhone());
		paginatedFilter.setFilters(mapContext);
		PaginatedList<CustomerDto> paginatedList = this.companyCustomerService.selectByFilter(paginatedFilter);
		if(paginatedList.getRows()!=null&&paginatedList.getRows().size()>0){
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put(WebConstant.KEY_ENTITY_MOBILE, AppBeanInjector.i18nUtil.getMessage("VALIDATE_INVALID_MOBILE_NO"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errorMap);
		}
		//把客户添加到公司下
		customer.setCompanyId(customer.getCompanyId());
		customer.setStatus(CompanyCustomerStatus.CREATE.getValue());
		customer.setCreator(uid);
		customer.setCreated(DateUtil.getSystemDate());
		customer.setGrade(CompanyCustomerGrade.LOW.getValue());
		customer.setSource(CompanyCustomerSource.CSOCKET.getValue());
		RequestResult result = customer.validateFields();
		if (result != null) {
			return result;
		}
		this.companyCustomerService.add(customer);
		CustomerDtoV2 userDto = this.companyCustomerService.selectDtoById(customer.getId());
		if (null != userDto) {
			String cityAreaName = userDto.getCityAreaName();
			String address = userDto.getAddress();
			String address1 = AddressUtils.getAddress(cityAreaName, address);
			userDto.setCityAreaName(address1);
		}
		if(userDto.getCustomerManager()!=null){
			userDto.setCustomerManagerName(this.userService.findById(userDto.getCustomerManager()).getName());
		}
		return ResultFactory.generateRequestResult(userDto);


	}

	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "修改终端客户",operationType = OperationType.UPDATE,operationMoudule = OperationMoudule.CUSTOMER)
	public RequestResult updateClient(String cid, MapContext mapContext) {
		//判断客户是否存在
		CompanyCustomer client = this.companyCustomerService.findById(cid);
		if (client == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		mapContext.put(WebConstant.KEY_ENTITY_ID, cid);
		this.companyCustomerService.updateByMapContext(mapContext);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult findClientInfo(String id) {
		//判断客户是否存在
		CustomerDtoV2 companyCustomer = this.companyCustomerService.findOneById(id);
		if(companyCustomer==null){
			return ResultFactory.generateResNotFoundResult();
		}
		Company company = this.companyService.findById(companyCustomer.getCompanyId());
		if(!company.getBranchId().equals(WebUtils.getCurrBranchId())){
			return ResultFactory.generateResNotFoundResult();
		}
		return ResultFactory.generateRequestResult(companyCustomer);
	}

	@Override
	public RequestResult findCustomerCount(String branchId) {
		MapContext mapContext=this.companyCustomerService.findCustomerCount(branchId);
		List result=new ArrayList();
		int a=0;
		for(int i=0;i<3;i++){
			Map map = new HashMap();
			switch (a) {
				case 	0:
					map.put("name", "本月新增客户");
					map.put("value",mapContext.getTypedValue("monthNum",Integer.class));
					a=a+1;
					break;
				case 1:
					map.put("name", "本周新增客户");
					map.put("value",mapContext.getTypedValue("weekNum",Integer.class));
					a=a+1;
					break;
				case 2:
					map.put("name", "今日新增客户");
					map.put("value",mapContext.getTypedValue("todayNum",Integer.class));
					a=a+1;
					break;
			}
			result.add(map);
		}
		return ResultFactory.generateRequestResult(result);
	}

}

