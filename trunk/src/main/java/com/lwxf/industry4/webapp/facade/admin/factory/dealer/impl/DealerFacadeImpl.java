package com.lwxf.industry4.webapp.facade.admin.factory.dealer.impl;

import com.lwxf.commons.uniquekey.IIdGenerator;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.commons.utils.ValidateUtils;
import com.lwxf.industry4.webapp.baseservice.cache.constant.RedisConstant;
import com.lwxf.industry4.webapp.bizservice.branch.BranchService;
import com.lwxf.industry4.webapp.bizservice.common.CityAreaService;
import com.lwxf.industry4.webapp.bizservice.common.UploadFilesService;
import com.lwxf.industry4.webapp.bizservice.company.*;
import com.lwxf.industry4.webapp.bizservice.dealer.DealerAccountService;
import com.lwxf.industry4.webapp.bizservice.dealer.DealerLogisticsService;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentService;
import com.lwxf.industry4.webapp.bizservice.system.RolePermissionService;
import com.lwxf.industry4.webapp.bizservice.system.RoleService;
import com.lwxf.industry4.webapp.bizservice.user.UserBasisService;
import com.lwxf.industry4.webapp.bizservice.user.UserExtraService;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.bizservice.user.UserThirdInfoService;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationMoudule;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationType;
import com.lwxf.industry4.webapp.common.aop.syslog.SysOperationLog;
import com.lwxf.industry4.webapp.common.authcode.AuthCodeUtils;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.component.UploadType;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.dto.UserInfoObj;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.enums.company.CompanyStatus;
import com.lwxf.industry4.webapp.common.enums.company.DealerAccountType;
import com.lwxf.industry4.webapp.common.enums.company.DealerEmployeeRole;
import com.lwxf.industry4.webapp.common.enums.company.EmployeeStatus;
import com.lwxf.industry4.webapp.common.enums.user.*;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.uniquecode.UniquneCodeGenerator;
import com.lwxf.industry4.webapp.common.utils.AesEncryptUtil;
import com.lwxf.industry4.webapp.common.utils.UserExtraUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.company.CompanyCertificatesDto;
import com.lwxf.industry4.webapp.domain.dto.company.CompanyDto;
import com.lwxf.industry4.webapp.domain.dto.dealer.WxDealerLogisticsDto;
import com.lwxf.industry4.webapp.domain.entity.branch.Branch;
import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.industry4.webapp.domain.entity.company.*;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAccount;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerLogistics;
import com.lwxf.industry4.webapp.domain.entity.financing.Payment;
import com.lwxf.industry4.webapp.domain.entity.system.Role;
import com.lwxf.industry4.webapp.domain.entity.system.RolePermission;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.user.UserBasis;
import com.lwxf.industry4.webapp.domain.entity.user.UserExtra;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.dealer.DealerFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/5/005 13:27
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("dealerFacade")
public class DealerFacadeImpl extends BaseFacadeImpl implements DealerFacade {
    @Resource(name = "companyService")
    private CompanyService companyService;
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "userExtraService")
    private UserExtraService userExtraService;
    @Resource(name = "userThirdInfoService")
    private UserThirdInfoService userThirdInfoService;
    @Resource(name = "companyEmployeeService")
    private CompanyEmployeeService companyEmployeeService;
    @Resource(name = "rolePermissionService")
    private RolePermissionService rolePermissionService;
    @Resource(name = "roleService")
    private RoleService roleService;
    @Resource(name = "userBasisService")
    private UserBasisService userBasisService;
    @Resource(name = "dealerAccountService")
    private DealerAccountService dealerAccountService;
    @Resource(name = "uploadFilesService")
    private UploadFilesService uploadFilesService;
    @Resource(name = "paymentService")
    private PaymentService paymentService;
    @Resource(name = "dealerShopService")
    private DealerShopService dealerShopService;
    @Resource(name = "employeeInfoService")
    private EmployeeInfoService employeeInfoService;
    @Resource(name = "dealerShippingLogisticsService")
    private DealerShippingLogisticsService dealerShippingLogisticsService;
    @Resource(name = "cityAreaService")
    private CityAreaService cityAreaService;
    @Resource(name = "dealerLogisticsService")
    private DealerLogisticsService dealerLogisticsService;
    @Resource(name = "branchService")
    private BranchService branchService;
    @Resource(name = "companyCertificatesService")
    private CompanyCertificatesService companyCertificatesService;


    @Override
    @Transactional(value = "transactionManager")
    @SysOperationLog(detail = "新增经销商公司信息", operationType = OperationType.INSERT, operationMoudule = OperationMoudule.COMPANY)
    public RequestResult addDealer(CompanyDto company) {
        String branchId = WebUtils.getCurrBranchId();
        //设置经销商编号
        //String companyNo=AppBeanInjector.uniquneCodeGenerator.getNumberIncrement(UniquneCodeGenerator.UniqueResource.COMPANY_NO.toString());
        MapContext mapContext = MapContext.newOne();
        mapContext.put("branchId", WebUtils.getCurrBranchId());
        List<Company> allCompany = this.companyService.findAllCompany(mapContext);
        String companyNo = "10001";
        if (allCompany != null && allCompany.size() > 0) {
            Company company1 = allCompany.get(allCompany.size() - 1);
            Integer companyNoInt = Integer.valueOf(company1.getNo());
            companyNo = String.valueOf(companyNoInt + 1);
        }
        //验证电话号码是否正确
        if (!ValidateUtils.isChinaPhoneNumber(company.getLeaderTel())) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put(WebConstant.KEY_ENTITY_MOBILE, AppBeanInjector.i18nUtil.getMessage("VALIDATE_INVALID_MOBILE_NO"));
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, company.getLeaderTel());
        }
        //判断手机号和名称是否已存在
        if (this.companyService.findByTelAndName(company.getLeaderTel(), null, WebUtils.getCurrBranchId()) != null) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_MOBILE_HAVE_EXISTED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_MOBILE_HAVE_EXISTED"));
        }
        company.setBranchId(branchId);
        company.setNo(companyNo);
        company.setIntegral(new BigDecimal(0));
        if (company.getStatus() == null || company.getStatus().equals("")) {
            company.setStatus(1);
        }
        this.companyService.add(company);
        //添加经销商物流公司（多个）
        String logisticsCompanyIds = company.getLogisticsCompanyId();
        if (logisticsCompanyIds != null && !logisticsCompanyIds.equals("")) {
            String[] split = logisticsCompanyIds.split(",");
            if (split != null && split.length > 0) {
                for (String logisticsCompanyId : split) {
                    // 经销商多物流公司新增数据
                    DealerLogistics dealerLogistics = new DealerLogistics();
                    dealerLogistics.setCompanyId(company.getId()); // 经销商id
                    dealerLogistics.setChecked(Byte.valueOf("0")); // 设置为默认
                    dealerLogistics.setLogisticsCompanyId(logisticsCompanyId); // 物流公司id
                    dealerLogistics.setCreated(DateUtil.getSystemDate());
                    dealerLogistics.setCreator(WebUtils.getCurrUserId());
                    this.dealerLogisticsService.add(dealerLogistics);
                }
            }

        }
        for (int i = 1; i < 6; i++) {
            DealerAccount dealerAccount = new DealerAccount();
            dealerAccount.setType(i);
            dealerAccount.setBalance(new BigDecimal(0));
            dealerAccount.setCompanyId(company.getId());
            dealerAccount.setNature(1);
            dealerAccount.setStatus(1);
            dealerAccount.setBranchId(WebUtils.getCurrBranchId());
            this.dealerAccountService.add(dealerAccount);
        }
        User user = new User();
        //用户信息
        user.setMobile(company.getLeaderTel());
        user.setName(company.getLeaderName());
        user.setType(UserType.DEALER.getValue());
        user.setSex(UserSex.MAN.getValue());
        user.setAvatar(AppBeanInjector.configuration.getUserDefaultAvatar());
        user.setTimeZone(WebConstant.TIMEZONE_DEFAULT);
        user.setLanguage(WebConstant.LANGUAGE_DEFAULT);
        user.setCreated(DateUtil.getSystemDate());
        user.setState(UserState.DISABLED.getValue());
        user.setLoginName(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.USER_LOGNAME_NO));
        user.setFollowers(0);
        user.setChangedLoginName(false);
        user.setBranchId(WebUtils.getCurrBranchId());
        user.setEnableLoginPc(WebConstant.DISABLE);
        user.setEnableLoginWx(WebConstant.DISABLE);
        //判断公司是否存在店主
        if (this.companyEmployeeService.selectShopkeeperByCompanyIds(Arrays.asList(company.getId())).size() != 0) {
            return ResultFactory.generateErrorResult(ErrorCodes.BIZ_SHOPKEEPERS_ALREADY_EXIST_10080, AppBeanInjector.i18nUtil.getMessage("BIZ_SHOPKEEPERS_ALREADY_EXIST_10080"));
        }
        //给经销商设定用户生日
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            user.setBirthday(simpleDateFormat.parse("1970-1-1 00:00:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.userService.add(user);

        //用户扩展信息
        UserExtra userExtra = new UserExtra();
        userExtra.setUserId(user.getId());
        UserExtraUtil.saltingPassword(userExtra, new Md5Hash("111111").toString());
        this.userExtraService.add(userExtra);

        // 第三方账号信息
        UserThirdInfo userThirdInfo = new UserThirdInfo();
        userThirdInfo.setWxNickname(user.getMobile());
        userThirdInfo.setWxIsBind(Boolean.FALSE);
        userThirdInfo.setWxIsSubscribe(Boolean.FALSE);
        userThirdInfo.setEmailIsBind(Boolean.FALSE);
        userThirdInfo.setMobileIsBind(Boolean.FALSE);
        userThirdInfo.setUserId(user.getId());
        userThirdInfo.setAppToken(UserExtraUtil.generateAppToken(userExtra, null));
        userThirdInfo.setCompanyId(company.getId());
        userThirdInfo.setBranchId(WebUtils.getCurrBranchId());
        AppBeanInjector.redisUtils.hPut(RedisConstant.PLATFORM_TAG, user.getId(), Integer.valueOf(1));
        this.userThirdInfoService.add(userThirdInfo);

        //用户基本信息表
        UserBasis userBasis = new UserBasis();
        userBasis.setUserId(user.getId());
        userBasis.setPoliticalOutlook(UserPoliticalOutlookType.MASSES.getValue());
        userBasis.setEducation(EducationType.UNDERGRADUATE.getValue());
        userBasis.setIncome(IncomeType.FOUR.getValue());
        this.userBasisService.add(userBasis);

        //修改公司的状态为正常
        MapContext updateCompany = new MapContext();
        updateCompany.put(WebConstant.KEY_ENTITY_ID, company.getId());
        if (company.getStatus() == null || company.getStatus().equals("")) {
            updateCompany.put(WebConstant.KEY_ENTITY_STATUS, CompanyStatus.NORMAL.getValue());
        }
        updateCompany.put("leader", user.getId());
        this.companyService.updateByMapContext(updateCompany);

        //生成店铺数据
        DealerShop dealerShop = new DealerShop();
        dealerShop.setId(company.getId());
        dealerShop.setAddress(company.getAddress());
        dealerShop.setBusinessManager(company.getBusinessManager());
        dealerShop.setCityAreaId(company.getCityAreaId());
        dealerShop.setCompanyId(company.getId());
        dealerShop.setCreated(company.getCreated());
        dealerShop.setCreator(company.getCreator());
        dealerShop.setFollowers(company.getFollowers());
        dealerShop.setGrade(company.getGrade());
        dealerShop.setLat(company.getLat());
        dealerShop.setLeader(user.getId());
        dealerShop.setLeaderTel(company.getLeaderTel());
        dealerShop.setLng(company.getLng());
        dealerShop.setLogo(company.getLogo());
        dealerShop.setName(company.getName());
        dealerShop.setServiceStaff(company.getServiceStaff());
        dealerShop.setServiceTel(company.getServiceTel());
        dealerShop.setShopArea(company.getShopArea());
        dealerShop.setShopInfo(company.getCompanyInfo());
        dealerShop.setStatus(company.getStatus());
        dealerShop.setType(company.getType());
        this.dealerShopService.add(dealerShop);

        CompanyDto companyDto = new CompanyDto();
        companyDto.clone(company);
        companyDto.setCreatorName(this.userService.findById(companyDto.getCreator()).getName());

        //给该用户添加公司角色 店主
        CompanyEmployee companyEmployee = new CompanyEmployee();
        companyEmployee.setCompanyId(company.getId());
        companyEmployee.setUserId(user.getId());
        //店主编号未定
        Role role = this.roleService.selectByKey(DealerEmployeeRole.SHOPKEEPER.getValue(), WebUtils.getCurrBranchId());
        companyEmployee.setRoleId(role.getId());
        companyEmployee.setStatus(EmployeeStatus.DISABLED.getValue());
        companyEmployee.setCreated(DateUtil.getSystemDate());
        this.companyEmployeeService.add(companyEmployee);
        //把店主相关权限添加至 员工表中
        List<RolePermission> rolePermissionList = this.rolePermissionService.selectRolePermissionList(role.getId());
        if (rolePermissionList != null && rolePermissionList.size() != 0) {
            IIdGenerator idGenerator = AppBeanInjector.idGererateFactory;
            for (RolePermission rolePermission : rolePermissionList) {
                //重新生成主键ID
                rolePermission.setId(idGenerator.nextStringId());
                //用公司员工主键ID替换权限ID
                rolePermission.setRoleId(companyEmployee.getId());
            }
        }

        //设置经销商的资金账户信息
//		DealerAccount dealerAccount = new DealerAccount();
//		dealerAccount.setBalance(new BigDecimal(0));
//		dealerAccount.setCompanyId(cid);
//		dealerAccount.setNature(1);
//		dealerAccount.setStatus(1);
//		dealerAccount.setBranchId(WebUtils.getCurrBranchId());
//		for (int i =3;i>0;i--){
//			dealerAccount.setType(i);
//			this.dealerAccountService.add(dealerAccount);
//		}

        //新增员工时 设置 员工信息 初始数据
        EmployeeInfo employeeInfo = new EmployeeInfo();
        employeeInfo.setCompanyEmployeeId(companyEmployee.getId());
        employeeInfo.setUserId(user.getId());
        employeeInfo.setStatus(EmployeeInfoStatus.NORMAL.getValue() + "");
        this.employeeInfoService.add(employeeInfo);

        CompanyDto companyById = this.companyService.findCompanyById(company.getId());
        List<WxDealerLogisticsDto> byCid = this.dealerLogisticsService.findByCid(company.getId());
        companyById.setDealerLogisticsDtos(byCid);
        return ResultFactory.generateRequestResult(companyById);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult initDealerAccount(String branchId) {
        MapContext map = MapContext.newOne();
        map.put("branchId", branchId);
        List<Company> companys = companyService.findAllCompany(map);
        for (Company c : companys) {
            for (int i = 1; i < 6; i++) {
                DealerAccount dealerAccount = new DealerAccount();
                dealerAccount.setType(i);
                dealerAccount.setBalance(new BigDecimal(0));
                dealerAccount.setCompanyId(c.getId());
                dealerAccount.setNature(1);
                dealerAccount.setStatus(1);
                dealerAccount.setBranchId(branchId);
                this.dealerAccountService.add(dealerAccount);
            }
        }
        return ResultFactory.generateSuccessResult();
    }

    @Override
    public RequestResult findDealerCompanyList(MapContext mapContent, Integer pageNum, Integer pageSize) {
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        Pagination pagination = new Pagination();
        pagination.setPageSize(pageSize);
        pagination.setPageNum(pageNum);
        paginatedFilter.setPagination(pagination);
        mapContent.put("key", DealerEmployeeRole.SHOPKEEPER.getValue());
        mapContent.put("accountType", DealerAccountType.FREE_ACCOUNT.getValue());
        mapContent.put("depositType", DealerAccountType.DEPOSIT_ACCOUNT.getValue());
        mapContent.put("designType", DealerAccountType.FREEZE_ACCOUNT.getValue());
        paginatedFilter.setFilters(mapContent);
        Map dataSort = new HashMap();
        List sorts = new ArrayList();
        dataSort.put("created", "desc");
        sorts.add(dataSort);
        paginatedFilter.setSorts(sorts);
        PaginatedList<CompanyDto> companyPaginatedList = this.companyService.selectByFilter(paginatedFilter);
        List ids = new ArrayList();
        for (Company company : companyPaginatedList.getRows()) {
            ids.add(company.getId());
        }
        MapContext result = MapContext.newOne();
        List<CompanyDto> companyDtoList = new ArrayList<>();
        if (companyPaginatedList.getRows().size() > 0) {
            result.put("userList", new ArrayList<>());
            //给每个经销商填充账户信息
            for (CompanyDto company : companyPaginatedList.getRows()) {
                List<DealerAccount> listDealerAccount = dealerAccountService.findByCompanIdAndNature(company.getId(), 1);
                company.setAccountList(listDealerAccount);
                companyDtoList.add(company);
            }
            result.put("companyList", companyDtoList);
            return ResultFactory.generateRequestResult(result, companyPaginatedList.getPagination());
        }
        if (ids.size() > 0) {
            List<User> userList = this.userService.selectCompanyShopkeeperByCompanyIds(ids, DealerEmployeeRole.SHOPKEEPER.getValue());
            result.put("userList", userList);
        } else {
            result.put("userList", new ArrayList<>());
        }
        result.put("companyList", companyPaginatedList.getRows());
        return ResultFactory.generateRequestResult(result, companyPaginatedList.getPagination());
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult openDealer(User user, String cid, StringBuffer pwd) {
        //判断公司是否存在
        Company company = this.companyService.findById(cid);
        if (company == null || !company.getStatus().equals(CompanyStatus.TO_BE_ENABLED.getValue())) {
            return ResultFactory.generateResNotFoundResult();
        }
        //用户信息
        user.setMobile(company.getLeaderTel());
        user.setName(company.getFounderName());
        user.setType(UserType.DEALER.getValue());
        user.setSex(UserSex.MAN.getValue());
        user.setAvatar(AppBeanInjector.configuration.getUserDefaultAvatar());
        user.setTimeZone(WebConstant.TIMEZONE_DEFAULT);
        user.setLanguage(WebConstant.LANGUAGE_DEFAULT);
        user.setCreated(DateUtil.getSystemDate());
        user.setState(UserState.ENABLED.getValue());
        user.setLoginName(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.USER_LOGNAME_NO));
        user.setFollowers(0);
        user.setChangedLoginName(false);
        user.setBranchId(WebUtils.getCurrBranchId());
        user.setEnableLoginPc(WebConstant.DISABLE); // 默认禁止PC登录
        user.setEnableLoginWx(WebConstant.DISABLE); // 默认禁止微信小程序登录
        RequestResult validate = user.validateFields();
        if (validate != null) {
            return validate;
        }

        //验证电话号码是否正确
        if (!ValidateUtils.isChinaPhoneNumber(user.getMobile())) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put(WebConstant.KEY_ENTITY_MOBILE, AppBeanInjector.i18nUtil.getMessage("VALIDATE_INVALID_MOBILE_NO"));
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, user.getMobile());
        }
        //判断手机号是否已存在
        if (this.userService.findByMobile(user.getMobile()) != null) {
            Map result = new HashMap<>();
            result.put(WebConstant.KEY_ENTITY_MOBILE, AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, result);
        }

        //判断公司是否存在店主
        if (this.companyEmployeeService.selectShopkeeperByCompanyIds(Arrays.asList(cid)).size() != 0) {
            return ResultFactory.generateErrorResult(ErrorCodes.BIZ_SHOPKEEPERS_ALREADY_EXIST_10080, AppBeanInjector.i18nUtil.getMessage("BIZ_SHOPKEEPERS_ALREADY_EXIST_10080"));
        }
        //给经销商设定用户生日
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            user.setBirthday(simpleDateFormat.parse("1970-1-1 00:00:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.userService.add(user);

        //用户扩展信息
        UserExtra userExtra = new UserExtra();
        userExtra.setUserId(user.getId());
        pwd.append(AuthCodeUtils.getRandomNumberCode(6));
        UserExtraUtil.saltingPassword(userExtra, new Md5Hash(pwd.toString()).toString());
        this.userExtraService.add(userExtra);

        // 第三方账号信息
        UserThirdInfo userThirdInfo = new UserThirdInfo();
        userThirdInfo.setWxNickname(user.getMobile());
        userThirdInfo.setWxIsBind(Boolean.FALSE);
        userThirdInfo.setWxIsSubscribe(Boolean.FALSE);
        userThirdInfo.setEmailIsBind(Boolean.FALSE);
        userThirdInfo.setMobileIsBind(Boolean.FALSE);
        userThirdInfo.setUserId(user.getId());
        userThirdInfo.setAppToken(UserExtraUtil.generateAppToken(userExtra, null));
        userThirdInfo.setCompanyId(WebUtils.getCurrCompanyId());
        userThirdInfo.setBranchId(WebUtils.getCurrBranchId());
        AppBeanInjector.redisUtils.hPut(RedisConstant.PLATFORM_TAG, user.getId(), Integer.valueOf(1));
        this.userThirdInfoService.add(userThirdInfo);

        //用户基本信息表
        UserBasis userBasis = new UserBasis();
        userBasis.setUserId(user.getId());
        userBasis.setPoliticalOutlook(UserPoliticalOutlookType.MASSES.getValue());
        userBasis.setEducation(EducationType.UNDERGRADUATE.getValue());
        userBasis.setIncome(IncomeType.FOUR.getValue());
        this.userBasisService.add(userBasis);

        //修改公司的状态为正常
        MapContext updateCompany = new MapContext();
        updateCompany.put(WebConstant.KEY_ENTITY_ID, cid);
        updateCompany.put(WebConstant.KEY_ENTITY_STATUS, CompanyStatus.NORMAL.getValue());
        updateCompany.put("leader", user.getId());
        this.companyService.updateByMapContext(updateCompany);

        //生成店铺数据
        DealerShop dealerShop = new DealerShop();
        dealerShop.setId(cid);
        dealerShop.setAddress(company.getAddress());
        dealerShop.setBusinessManager(company.getBusinessManager());
        dealerShop.setCityAreaId(company.getCityAreaId());
        dealerShop.setCompanyId(cid);
        dealerShop.setCreated(company.getCreated());
        dealerShop.setCreator(company.getCreator());
        dealerShop.setFollowers(company.getFollowers());
        dealerShop.setGrade(company.getGrade());
        dealerShop.setLat(company.getLat());
        dealerShop.setLeader(user.getId());
        dealerShop.setLeaderTel(company.getLeaderTel());
        dealerShop.setLng(company.getLng());
        dealerShop.setLogo(company.getLogo());
        dealerShop.setName(company.getName());
        dealerShop.setServiceStaff(company.getServiceStaff());
        dealerShop.setServiceTel(company.getServiceTel());
        dealerShop.setShopArea(company.getShopArea());
        dealerShop.setShopInfo(company.getCompanyInfo());
        dealerShop.setStatus(company.getStatus());
        dealerShop.setType(company.getType());
        this.dealerShopService.add(dealerShop);

        CompanyDto companyDto = new CompanyDto();
        companyDto.clone(company);
        companyDto.setCreatorName(this.userService.findById(companyDto.getCreator()).getName());

        //给该用户添加公司角色 店主
        CompanyEmployee companyEmployee = new CompanyEmployee();
        companyEmployee.setCompanyId(cid);
        companyEmployee.setUserId(user.getId());
        //店主编号未定
        Role role = this.roleService.selectByKey(DealerEmployeeRole.SHOPKEEPER.getValue(), WebUtils.getCurrBranchId());
        companyEmployee.setRoleId(role.getId());
        companyEmployee.setStatus(EmployeeStatus.NORMAL.getValue());
        companyEmployee.setCreated(DateUtil.getSystemDate());
        this.companyEmployeeService.add(companyEmployee);
        //把店主相关权限添加至 员工表中
        List<RolePermission> rolePermissionList = this.rolePermissionService.selectRolePermissionList(role.getId());
        if (rolePermissionList != null && rolePermissionList.size() != 0) {
            IIdGenerator idGenerator = AppBeanInjector.idGererateFactory;
            for (RolePermission rolePermission : rolePermissionList) {
                //重新生成主键ID
                rolePermission.setId(idGenerator.nextStringId());
                //用公司员工主键ID替换权限ID
                rolePermission.setRoleId(companyEmployee.getId());
            }
        }

        //设置经销商的资金账户信息
//		DealerAccount dealerAccount = new DealerAccount();
//		dealerAccount.setBalance(new BigDecimal(0));
//		dealerAccount.setCompanyId(cid);
//		dealerAccount.setNature(1);
//		dealerAccount.setStatus(1);
//		dealerAccount.setBranchId(WebUtils.getCurrBranchId());
//		for (int i =3;i>0;i--){
//			dealerAccount.setType(i);
//			this.dealerAccountService.add(dealerAccount);
//		}

        //新增员工时 设置 员工信息 初始数据
        EmployeeInfo employeeInfo = new EmployeeInfo();
        employeeInfo.setCompanyEmployeeId(companyEmployee.getId());
        employeeInfo.setUserId(user.getId());
        employeeInfo.setStatus(EmployeeInfoStatus.NORMAL.getValue() + "");
        this.employeeInfoService.add(employeeInfo);
        return ResultFactory.generateRequestResult(UserInfoObj.newOne(user, userExtra, userThirdInfo, companyDto));
    }

    @Override
    @Transactional(value = "transactionManager")
    @SysOperationLog(detail = "更新经销商公司信息", operationType = OperationType.UPDATE, operationMoudule = OperationMoudule.COMPANY)
    public RequestResult updateDealerCompany(MapContext mapContext, String cid, String logisticsCompanyId) {
        String branchId = WebUtils.getCurrBranchId();
        //判断公司是否存在
        Company companyById = this.companyService.findById(cid);
        if (companyById == null) {
            return ResultFactory.generateResNotFoundResult();
        }
        Integer status = mapContext.getTypedValue("status", Integer.class);
        if (status != null) {
            //如果修改状态为 已退网或者 已禁用 已倒闭 则经销商下员工状态改为离职
            if (status == CompanyStatus.CLOSED_DOWN.getValue() || status == CompanyStatus.RETIRED_NETWORK.getValue() || status == CompanyStatus.DISABLED.getValue()) {
                this.companyEmployeeService.updateAllDisabledByCompanyId(cid);
            }
            //如果修改状态为 正常 则修改经销商店主状态为正常
            if (status == CompanyStatus.NORMAL.getValue()) {
                this.companyEmployeeService.updateShopkeeperByCompanyId(cid, DealerEmployeeRole.SHOPKEEPER.getValue());
            }
            //如果是提交操作
            if (status == CompanyStatus.PENDING_APPROVAL.getValue()) {
                MapContext result = new MapContext();
                //判断是否设置签约时间 到期时间 地址是否为空
                if (companyById.getContractExpiredDate() == null) {
                    result.put("contractExpiredDate", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
                }
                if (companyById.getContractTime() == null) {
                    result.put("contractTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
                }
                if (LwxfStringUtils.isBlank(companyById.getAddress())) {
                    result.put("address", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
                }
                if (result.size() > 0) {
                    return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, result);
                }
            }
        }
        String no = mapContext.getTypedValue("no", String.class);
        if (no == null || no.trim().equals("")) {
            mapContext.put("no", null);
            no = null;
        }
        if (no != null) {
            Company company = this.companyService.selectByNo(no, branchId);
            if (company != null && !company.getId().equals(cid)) {
                Map result = new HashMap();
                result.put(WebConstant.STRING_NO, AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
                return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, result);
            }
        }
        mapContext.put(WebConstant.KEY_ENTITY_ID, cid);
        //是否修改負責人信息
        String leaderName = mapContext.getTypedValue("leaderName", String.class);
        String leaderTel = mapContext.getTypedValue("leaderTel", String.class);
        if (leaderName != null || leaderTel != null) {
            MapContext usermap = MapContext.newOne();
            usermap.put("name", leaderName);
            usermap.put("mobile", leaderTel);
            usermap.put("id", companyById.getLeader());
            this.userService.updateByMapContext(usermap);
        }
        if(mapContext.containsKey("contractTime")) {
            if (mapContext.getTypedValue("contractTime", String.class).equals("")) {
                mapContext.put("contractTime", null);
            }
        }
        //修改公司信息
        this.companyService.updateByMapContext(mapContext);
        //是否修改默认物流公司
        if (logisticsCompanyId != null) {
            // 删除原经销商物流公司
            Integer i=this.dealerLogisticsService.deleteByCid(cid);
            //添加新得物流公司
            String[] split = logisticsCompanyId.split(",");
            if(split!=null&&split.length>0) {
                for (String logisticsId : split) {
                        DealerLogistics dealerLogisticsTemp = new DealerLogistics();
                        dealerLogisticsTemp.setCompanyId(cid); // 经销商id
                        dealerLogisticsTemp.setChecked(new Byte("0"));
                        dealerLogisticsTemp.setLogisticsCompanyId(logisticsId); // 物流公司id
                        dealerLogisticsTemp.setCreated(DateUtil.getSystemDate());
                        dealerLogisticsTemp.setCreator(WebUtils.getCurrUserId());
                        this.dealerLogisticsService.add(dealerLogisticsTemp);
                }
            }
        }

        CompanyDto companyDto = this.companyService.findCompanyById(cid);
        List<User> userList = this.userService.selectCompanyShopkeeperByCompanyIds(Arrays.asList(companyDto.getId()), DealerEmployeeRole.SHOPKEEPER.getValue());
        MapContext result = new MapContext();
        result.put("company", companyDto);
        result.put("user", userList.get(0));

        //所需修改的数据中 移除店铺表所不存在的数据 如果还存在修改 就修改店铺
        mapContext.remove("no");
        mapContext.remove("depositBank");
        mapContext.remove("bankAccount");
        mapContext.remove("bankAccountHolder");
        if (mapContext.size() != 0) {
            this.dealerShopService.updateByMapContext(mapContext);
        }
        return ResultFactory.generateRequestResult(result);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult uploadDealerFiles(String cid, List<MultipartFile> multipartFileList) {
        List list = new ArrayList<>();
        UploadInfo uploadInfo;
        for (MultipartFile multipartFile : multipartFileList) {
            uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.FORMAL, multipartFile, UploadResourceType.COMPANY, cid);
            UploadFiles uploadFiles = UploadFiles.create(cid, null, uploadInfo, UploadResourceType.COMPANY, UploadType.FORMAL);
            uploadFiles.setCompanyId(cid);
            this.uploadFilesService.add(uploadFiles);
            list.add(uploadFiles);
        }
        return ResultFactory.generateRequestResult(list);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult deleteDealerFiles( String fileId) {
        UploadFiles files = this.uploadFilesService.findById(fileId);
        if(files==null){
            return ResultFactory.generateResNotFoundResult();
        }
        //删除本地资源
        AppBeanInjector.baseFileUploadComponent.deleteFileByDir(AppBeanInjector.configuration.getUploadFileRootDir().concat(files.getPath()));
        this.uploadFilesService.deleteById(fileId);
        return ResultFactory.generateSuccessResult();
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult submitDealer(String cid, Payment payment) {
        //判断经销商公司是否存在  当前状态是否是意向
        Company company = this.companyService.findById(cid);
        if (company == null || !company.getStatus().equals(CompanyStatus.INTENTION.getValue())) {
            return ResultFactory.generateResNotFoundResult();
        }
        //生成支付记录
        this.paymentService.add(payment);
        //修改公司状态为待财务审核
        MapContext updateCompany = new MapContext();
        updateCompany.put(WebConstant.KEY_ENTITY_ID, cid);
        updateCompany.put(WebConstant.KEY_ENTITY_STATUS, CompanyStatus.PENDING_APPROVAL.getValue());
        this.companyService.updateByMapContext(updateCompany);
        return ResultFactory.generateSuccessResult();
    }

    @Override
    public RequestResult findDealerList(MapContext mapContext, Integer pageNum, Integer pageSize) {
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        Pagination pagination = new Pagination();
        pagination.setPageNum(pageNum);
        pagination.setPageSize(pageSize);
        paginatedFilter.setPagination(pagination);
        Role role = this.roleService.selectByKey(DealerEmployeeRole.SHOPKEEPER.getValue(), WebUtils.getCurrBranchId());

        if (role != null) {
            mapContext.put("roleId", role.getId());
        }
        paginatedFilter.setFilters(mapContext);
        List<Map<String, String>> sorts = new ArrayList<Map<String, String>>();
        Map<String, String> ceated = new HashMap<String, String>();
        ceated.put(WebConstant.KEY_ENTITY_CREATED, "desc");
        sorts.add(ceated);
        return ResultFactory.generateRequestResult(this.companyEmployeeService.findListByFilter(paginatedFilter));
    }

    @Override
    @Transactional(value = "transactionManager")
    @SysOperationLog(detail = "更新经销商信息", operationType = OperationType.UPDATE, operationMoudule = OperationMoudule.COMPANY)
    public RequestResult updateDealer(MapContext mapContext, String id) {
        //判断该店主是否存在
        CompanyEmployee companyEmployee = this.companyEmployeeService.findById(id);
        if (companyEmployee == null || !companyEmployee.getStatus().equals(EmployeeStatus.NORMAL.getValue())) {
            return ResultFactory.generateResNotFoundResult();
        }
        //修改用户信息
        mapContext.put(WebConstant.KEY_ENTITY_ID, companyEmployee.getUserId());
        this.userService.updateByMapContext(mapContext);
        return ResultFactory.generateRequestResult(this.userService.findById(companyEmployee.getUserId()));
    }

    @Override
    @Transactional(value = "transactionManager")
    @SysOperationLog(detail = "更新经销商手机信息", operationType = OperationType.UPDATE, operationMoudule = OperationMoudule.COMPANY)
    public RequestResult updateDealerMobile(String id, String mobile) {
        //判断该店主是否存在
        CompanyEmployee companyEmployee = this.companyEmployeeService.findById(id);
        if (companyEmployee == null) {
            return ResultFactory.generateResNotFoundResult();
        }
        //验证电话号码是否正确
        if (!ValidateUtils.isChinaPhoneNumber(mobile)) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put(WebConstant.KEY_ENTITY_MOBILE, AppBeanInjector.i18nUtil.getMessage("VALIDATE_INVALID_MOBILE_NO"));
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, mobile);
        }
        //判断新的登录名 是否已存在
        User byMobile = this.userService.findByMobile(mobile);
        if (byMobile != null && !byMobile.getId().equals(companyEmployee.getUserId())) {
            Map<String, String> validResult = new HashMap<>();
            validResult.put("mobile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, validResult);
        }
        MapContext mapContext = new MapContext();
        mapContext.put(WebConstant.KEY_ENTITY_MOBILE, mobile);
        mapContext.put(WebConstant.KEY_ENTITY_ID, companyEmployee.getUserId());
        this.userService.updateByMapContext(mapContext);
        return ResultFactory.generateRequestResult(this.userService.findById(companyEmployee.getUserId()));
    }

    @Override
    @Transactional(value = "transactionManager")
    @SysOperationLog(detail = "更新经销商密码信息", operationType = OperationType.UPDATE, operationMoudule = OperationMoudule.COMPANY)
    public RequestResult updateDealerAccountPwd(String id, String newPassword) {
        //判断该店主是否存在
        CompanyEmployee companyEmployee = this.companyEmployeeService.findById(id);
        if (companyEmployee == null || !companyEmployee.getStatus().equals(EmployeeStatus.NORMAL.getValue())) {
            return ResultFactory.generateResNotFoundResult();
        }
        UserExtra userExtra = new UserExtra();
        UserExtraUtil.saltingPassword(userExtra, newPassword);
        MapContext toSave = new MapContext();
        toSave.put(WebConstant.KEY_USER_EXTRA_SALT, userExtra.getSalt());
        toSave.put(WebConstant.KEY_ENTITY_USER_ID, companyEmployee.getUserId());
        toSave.put(WebConstant.KEY_USER_EXTRA_PASSWORD, userExtra.getPassword());
        toSave.put(WebConstant.KEY_USER_EXTRA_TOKEN, userExtra.getToken());
        this.userExtraService.updateByMapContext(toSave);
        toSave = MapContext.newOne();
        toSave.put(WebConstant.KEY_ENTITY_USER_ID, companyEmployee.getUserId());
        toSave.put(WebConstant.KEY_ENTITY_APP_TOKEN, UserExtraUtil.generateAppToken(userExtra, null));
        this.userThirdInfoService.updateByMapContext(toSave);
        return ResultFactory.generateSuccessResult();
    }

    @Override
    public RequestResult findDealerCompanyInfo(String cid) {
        MapContext mapContext = new MapContext();
        mapContext.put("fileList", this.uploadFilesService.findByResourceId(cid));
        CompanyDto companyById = this.companyService.findCompanyById(cid);
        mapContext.put("company", companyById);
        String provinceId = companyById.getProvinceId();
        String cityId = companyById.getCityId();
        String cityAreaId = companyById.getCityAreaId();
        if (cityId == null) {
            provinceId = companyById.getCityAreaId();
            cityAreaId = null;
        } else if (provinceId == null) {
            provinceId = companyById.getCityId();
            cityId = companyById.getCityAreaId();
            cityAreaId = null;
        }
        companyById.setProvinceId(provinceId);
        companyById.setCityId(cityId);
        companyById.setCityAreaId(cityAreaId);
        //查询经销商物流信息
        List<WxDealerLogisticsDto> dealerLogisticsDtos=this.dealerLogisticsService.findByCid(cid);
        mapContext.put("dealerLogisticsDtos", dealerLogisticsDtos);
        //查询证件信息
        List<CompanyCertificatesDto> certificatesDtos = this.companyCertificatesService.findByCid(cid);
        companyById.setCompanyCertificates(certificatesDtos);
        List<DealerAccount> listDealerAccount = dealerAccountService.findByCompanIdAndNature(cid, 1);
        mapContext.put("accountList", listDealerAccount);
        return ResultFactory.generateRequestResult(mapContext);
    }

    @Override
    public RequestResult findDealerCount(String branchId) {
        MapContext mapContext = this.companyService.findDealerCount(branchId);
        List result = new ArrayList();
        int a = 0;
        for (int i = 0; i < 3; i++) {
            Map map = new HashMap();
            switch (a) {
                case 0:
                    map.put("name", "意向经销商数量");
                    map.put("value", mapContext.getTypedValue("intentionNum", Integer.class));
                    a = a + 1;
                    break;
                case 1:
                    map.put("name", "签约经销商数量");
                    map.put("value", mapContext.getTypedValue("SignNum", Integer.class));
                    a = a + 1;
                    break;
                case 2:
                    map.put("name", "经销商总数量");
                    map.put("value", mapContext.getTypedValue("placeOrder", Integer.class));
                    a = a + 1;
                    break;
            }
            result.add(map);
        }
        return ResultFactory.generateRequestResult(result);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult enableAndProhibitDealer(User user, String cid) {
        Company company = this.companyService.findById(cid);
        if (company == null) {
            return ResultFactory.generateResNotFoundResult();
        }
        String dealerId = company.getLeader();
        MapContext mapContext = MapContext.newOne();
        user = this.userService.findByUserId(dealerId);
        if (null == user) {
            return ResultFactory.generateResNotFoundResult();
        }
        if (user.getState() == UserState.DISABLED.getValue()) {//禁用改成启用
            //TODO 判断经销商账号数量是否已经超过系统设置值
            String branchId = WebUtils.getCurrBranchId();
            Branch branch = branchService.findById(branchId);
            if (branch != null && branch.getDealerLoginNum() != null) {
                String encryptNum = branch.getDealerLoginNum();
                String num = AesEncryptUtil.AESDecode(encryptNum, branchId); // 加密的数量
                // 经销商的员工，是店主身份才可以登录系统
                MapContext paramMap = MapContext.newOne();
                Role role = this.roleService.selectByKey(DealerEmployeeRole.SHOPKEEPER.getValue(), branchId);
                if (role != null) {
                    paramMap.put("roleId", role.getId());
                }
                paramMap.put("branchId", branchId);
                Integer enableNum = this.userService.countEnableDealerUser(paramMap); // 已启用经销商账户的数量
                if (num != null && enableNum != null && enableNum >= Integer.valueOf(num)) {
                    return ResultFactory.generateErrorResult(ErrorCodes.BIZ_ENABLE_DEALER_USER_EXCEED_10096, AppBeanInjector.i18nUtil.getMessage("BIZ_ENABLE_DEALER_USER_EXCEED_10096"));
                }
            }

            mapContext.put("state", UserState.ENABLED.getValue());
            mapContext.put("id", dealerId);
            this.userService.updateByMapContext(mapContext);
            // 更新公司员工表的状态
            MapContext employeeMap = MapContext.newOne();
            CompanyEmployee employee = companyEmployeeService.findOneByCompanyIdAndUserId(cid, dealerId);
            if (employee != null) {
                employeeMap.put("id", employee.getId());
                employeeMap.put("status", 0);
                employeeMap.put("companyId", cid);
                employeeMap.put("userId", dealerId);
                this.companyEmployeeService.updateByMapContext(employeeMap);
            }

            UserExtra userExtra = new UserExtra();
            UserExtraUtil.saltingPassword(userExtra, new Md5Hash("111111").toString());
            MapContext toSave = new MapContext();
            toSave.put(WebConstant.KEY_USER_EXTRA_SALT, userExtra.getSalt());
            toSave.put(WebConstant.KEY_ENTITY_USER_ID, user.getId());
            toSave.put(WebConstant.KEY_USER_EXTRA_PASSWORD, userExtra.getPassword());
            toSave.put(WebConstant.KEY_USER_EXTRA_TOKEN, userExtra.getToken());
            this.userExtraService.updateByMapContext(toSave);
            toSave = MapContext.newOne();
            toSave.put(WebConstant.KEY_ENTITY_USER_ID, user.getId());
            toSave.put(WebConstant.KEY_ENTITY_APP_TOKEN, UserExtraUtil.generateAppToken(userExtra, null));
            this.userThirdInfoService.updateByMapContext(toSave);
        } else {
            // 启用改成禁用
            mapContext.put("state", UserState.DISABLED.getValue());
            mapContext.put("id", dealerId);
            mapContext.put("enableLoginPc", WebConstant.DISABLE); // 禁用pc登录
            mapContext.put("enableLoginWx", WebConstant.DISABLE); // 禁用微信小程序登录
            this.userService.updateByMapContext(mapContext);

//            // 更新公司员工表的状态
//            MapContext employeeMap =MapContext.newOne();
//            CompanyEmployee employee = companyEmployeeService.findOneByCompanyIdAndUserId(cid, dealerId);
//            if (employee != null) {
//                employeeMap.put("id", employee.getId());
//                employeeMap.put("status", 1);
//                employeeMap.put("companyId", cid);
//                employeeMap.put("userId", dealerId);
//                this.companyEmployeeService.updateByMapContext(employeeMap);
//            }

        }
        User user1 = this.userService.findByUserId(dealerId);
        return ResultFactory.generateRequestResult(UserInfoObj.newOne(user1, null, null, null));
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult enableAndProhibitWxLogin(String cid) {
        Company company = this.companyService.findById(cid);
        if (null == company) {
            return ResultFactory.generateResNotFoundResult();
        }
        String dealerId = company.getLeader();
        User user = this.userService.findByUserId(dealerId);
        if (null == user) {
            return ResultFactory.generateResNotFoundResult();
        }
        MapContext mapContext = MapContext.newOne();
        String enableLoginWx = String.valueOf(WebConstant.DISABLE);
        if (user.getEnableLoginWx() != null && user.getEnableLoginWx().equals(WebConstant.DISABLE)) {
            //禁用改成启用
            //TODO 判断经销商微信小程序登录账号数量是否已经超过系统设置值
            String branchId = WebUtils.getCurrBranchId();
            Branch branch = branchService.findById(branchId);
            if (branch != null && branch.getDealerLoginWxNum() != null) {
                String encryptNum = branch.getDealerLoginWxNum();
                String num = AesEncryptUtil.AESDecode(encryptNum, branchId); // 加密的数量
                // 经销商的员工，是店主身份才可以登录系统
                MapContext paramMap = MapContext.newOne();
                Role role = this.roleService.selectByKey(DealerEmployeeRole.SHOPKEEPER.getValue(), branchId);
                if (role != null) {
                    paramMap.put("roleId", role.getId());
                }
                paramMap.put("branchId", branchId);
                Integer enableNum = this.userService.countEnableWxLoginDealerUser(paramMap); // 已启用经销商微信小程序登录账户的数量
                if (num != null && enableNum != null && enableNum >= Integer.valueOf(num)) {
                    return ResultFactory.generateErrorResult(ErrorCodes.BIZ_ENABLE_DEALER_USER_EXCEED_10096, AppBeanInjector.i18nUtil.getMessage("BIZ_ENABLE_DEALER_USER_EXCEED_10096"));
                }
            }
            enableLoginWx = String.valueOf(WebConstant.ENABLE);

        } else {
            // 启用改成禁用,微信端登出用户
            MapContext map = MapContext.newOne();
            map.put("userId", dealerId);
            map.put("wxOpenId", null);
            this.userThirdInfoService.userlogout(mapContext);
        }
        mapContext.put("enableLoginWx", enableLoginWx);
        mapContext.put("id", dealerId);
        this.userService.updateByMapContext(mapContext);

        return ResultFactory.generateSuccessResult();
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult enableAndProhibitPcLogin(String cid) {
        Company company = this.companyService.findById(cid);
        if (null == company) {
            return ResultFactory.generateResNotFoundResult();
        }
        String dealerId = company.getLeader();
        User user = this.userService.findByUserId(dealerId);
        if (null == user) {
            return ResultFactory.generateResNotFoundResult();
        }
        MapContext mapContext = MapContext.newOne();
        String enableLoginPc = String.valueOf(WebConstant.DISABLE);
        if (user.getEnableLoginPc() != null && user.getEnableLoginPc().equals(WebConstant.DISABLE)) {
            //禁用改成启用
            //TODO 判断经销商微信小程序登录账号数量是否已经超过系统设置值
            String branchId = WebUtils.getCurrBranchId();
            Branch branch = branchService.findById(branchId);
            if (branch != null && branch.getDealerLoginPcNum() != null) {
                String encryptNum = branch.getDealerLoginPcNum();
                String num = AesEncryptUtil.AESDecode(encryptNum, branchId); // 加密的数量
                // 经销商的员工，是店主身份才可以登录系统
                MapContext paramMap = MapContext.newOne();
                Role role = this.roleService.selectByKey(DealerEmployeeRole.SHOPKEEPER.getValue(), branchId);
                if (role != null) {
                    paramMap.put("roleId", role.getId());
                }
                paramMap.put("branchId", branchId);
                Integer enableNum = this.userService.countEnablePcLoginDealerUser(paramMap); // 已启用经销商微信小程序登录账户的数量
                if (num != null && enableNum != null && enableNum >= Integer.valueOf(num)) {
                    return ResultFactory.generateErrorResult(ErrorCodes.BIZ_ENABLE_DEALER_USER_EXCEED_10096, AppBeanInjector.i18nUtil.getMessage("BIZ_ENABLE_DEALER_USER_EXCEED_10096"));
                }
            }
            enableLoginPc = String.valueOf(WebConstant.ENABLE);

        }
        mapContext.put("enableLoginPc", enableLoginPc);
        mapContext.put("id", dealerId);
        this.userService.updateByMapContext(mapContext);

        return ResultFactory.generateSuccessResult();
    }

    @Override
    public RequestResult findDealerAccountCount(String branchId) {
        MapContext map = MapContext.newOne();
        MapContext mapContext = MapContext.newOne();
        // 经销员工，是店主身份才可以登录系统
        Role role = this.roleService.selectByKey(DealerEmployeeRole.SHOPKEEPER.getValue(), branchId);
        if (role != null) {
            mapContext.put("roleId", role.getId());
        }
        mapContext.put("branchId", branchId);
        // 经销商账号数量
        Integer userNum = this.userService.countDealerUser(mapContext);
        map.put("userNum", userNum);
        // 经销启用账号的数量
        Integer enableNum = this.userService.countEnableDealerUser(mapContext);
        map.put("enableNum", enableNum);
        // 经销商启用PC登录的数量
        Integer enablePcNum = this.userService.countEnablePcLoginDealerUser(mapContext);
        map.put("enablePcNum", enablePcNum);
        // 经销商启用WX小程序的登录的数量
        Integer enableWxNum = this.userService.countEnableWxLoginDealerUser(mapContext);
        map.put("enableWxNum", enableWxNum);

        return ResultFactory.generateRequestResult(map);
    }

    @Override
    public RequestResult findDealerNum(String branchId) {
        MapContext params = MapContext.newOne();
        params.put("branchId", branchId);
        params.put("status", CompanyStatus.NORMAL.getValue());
        List<MapContext> result = this.companyService.findCompanyByNameAndTel(params);
        return ResultFactory.generateRequestResult(result);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult addOrderFilesByType(List<MultipartFile> multipartFileList, String fileType) {
        //添加图片到本地
        List<MapContext> listUrls = new ArrayList<>();
        if (multipartFileList != null && multipartFileList.size() > 0) {
            for (MultipartFile multipartFile : multipartFileList) {
                UploadInfo uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.TEMP, multipartFile, UploadResourceType.COMPANY, "company");
                //添加图片到数据库
                UploadFiles file = new UploadFiles();
                file.setCreated(DateUtil.getSystemDate());
                if (fileType.equals("0")) {
                    file.setResourceType(UploadResourceType.BUSINESS_LICENSE.getType());
                } else if (fileType.equals("1")) {
                    file.setResourceType(UploadResourceType.FRONT_OF_ID_CARD.getType());
                } else if (fileType.equals("2")) {
                    file.setResourceType(UploadResourceType.CONTRACT_FILE.getType());
                } else if (fileType.equals("3")) {
                    file.setResourceType(UploadResourceType.COVER.getType());
                } else if (fileType.equals("4")) {
                    file.setResourceType(UploadResourceType.RENOVATION.getType());
                } else if (fileType.equals("5")) {
                    file.setResourceType(UploadResourceType.AVATAR.getType());
                }
                file.setCreated(DateUtil.getSystemDate());
                file.setCreator(WebUtils.getCurrUserId());
                file.setMime(uploadInfo.getFileMimeType().getRealType());
                file.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
                file.setName(uploadInfo.getFileName());
                file.setPath(uploadInfo.getRelativePath());
                file.setStatus(UploadType.TEMP.getValue());
                file.setFullPath(uploadInfo.getRealPath());
                this.uploadFilesService.add(file);
                MapContext map = MapContext.newOne();
                map.put("fullPath", file.getFullPath());
                map.put("imgRelPath", uploadInfo.getRelativePath());
                map.put("id", file.getId());
                listUrls.add(map);
            }
        }
        return ResultFactory.generateRequestResult(listUrls);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult addCertificates(CompanyCertificatesDto certificates) {
        if(certificates.getType()!=null){
            if(certificates.getType()==0){
                certificates.setName("营业执照");
            }else if(certificates.getType()==1){
                certificates.setName("身份证");
            }else if(certificates.getType()==2){
                certificates.setName("合同");
            }
        }
        this.companyCertificatesService.add(certificates);
        String fileIds = certificates.getFileIds();
        if ((fileIds != null && !fileIds.equals(""))) {
            String[] split = fileIds.split(",");
            for (String id : split) {
                MapContext mapContext = MapContext.newOne();
                mapContext.put("id", id);
                mapContext.put("status", 1);
                mapContext.put("resourceId", certificates.getId());
                this.uploadFilesService.updateByMapContext(mapContext);
            }
        }
        List<UploadFiles> byResourceId = this.uploadFilesService.findByResourceId(certificates.getId());
        certificates.setFilesList(byResourceId);
        return ResultFactory.generateRequestResult(certificates);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult updateCertificates(String id, MapContext mapContext) {
        CompanyCertificates byId = this.companyCertificatesService.findById(id);
        if (byId == null) {
            return ResultFactory.generateResNotFoundResult();
        }
        mapContext.put("id", id);
        this.companyCertificatesService.updateByMapContext(mapContext);
        List<UploadFiles> filesList = mapContext.getTypedValue("filesList", List.class);
        //删除原图片
        this.uploadFilesService.deleteByResourceId(id);
        if (filesList != null && filesList.size() > 0) {//添加新图片
            for (UploadFiles files : filesList) {
                MapContext map = MapContext.newOne();
                map.put("id", files.getId());
                map.put("status", 1);
                map.put("resourceId", id);
                this.companyCertificatesService.updateByMapContext(mapContext);
            }
        }
        return ResultFactory.generateSuccessResult();
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult fixDealers() {
        User user = new User();
        StringBuffer pwd = new StringBuffer();
        //循环获取没有用户信息的经销商
        List<Company> list = companyService.findAllCompany(MapContext.newOne());
        List<Company> noneUsers = new ArrayList();
        int i = 0;
        for (Company c : list) {
            User u = userService.findByMobile(c.getLeaderTel());
            if (u == null) {
                noneUsers.add(c);
            }
        }
        //判断公司是否存在
        for (Company company : noneUsers) {

            //判断手机号是否已存在
            if (this.userService.findByMobile(company.getLeaderTel()) != null) {
                continue;
            }
            //验证电话号码是否正确
            if (!ValidateUtils.isChinaPhoneNumber(company.getLeaderTel())) {
                continue;
            }
            //用户信息
            user.setMobile(company.getLeaderTel());
            user.setName(company.getName());
            user.setType(UserType.DEALER.getValue());
            user.setSex(UserSex.MAN.getValue());
            user.setAvatar(AppBeanInjector.configuration.getUserDefaultAvatar());
            user.setTimeZone(WebConstant.TIMEZONE_DEFAULT);
            user.setLanguage(WebConstant.LANGUAGE_DEFAULT);
            user.setCreated(DateUtil.getSystemDate());
            user.setState(UserState.ENABLED.getValue());
            user.setLoginName(user.getMobile());
            user.setFollowers(0);
            user.setChangedLoginName(false);
            user.setBranchId(WebUtils.getCurrBranchId());
            RequestResult validate = user.validateFields();
            if (validate != null) {
                return validate;
            }


            //判断公司是否存在店主
            if (this.companyEmployeeService.selectShopkeeperByCompanyIds(Arrays.asList(company.getId())).size() != 0) {
                continue;
            }
            //给经销商设定用户生日
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                user.setBirthday(simpleDateFormat.parse("1970-1-1 00:00:00"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.userService.add(user);

            //用户扩展信息
            UserExtra userExtra = new UserExtra();
            userExtra.setUserId(user.getId());
            //pwd.append(111111);
            //UserExtraUtil.saltingPassword(userExtra,new Md5Hash(pwd.toString()).toString());
            userExtra.setPassword("zoMnSkpB8qA4xboSvQ1O1RidHDZpVPsnBU9yYE7kWK8=");
            userExtra.setSalt("frsEm4pbRP8j3ul8sa+IsQ==");
            userExtra.setToken("Wxtpr9ZfTFx1H3cLVVIZE5OBDHdPcVqQjp4fOAdbb3s=");
            this.userExtraService.add(userExtra);

            // 第三方账号信息
            UserThirdInfo userThirdInfo = new UserThirdInfo();
            userThirdInfo.setWxNickname(user.getMobile());
            userThirdInfo.setWxIsBind(Boolean.FALSE);
            userThirdInfo.setWxIsSubscribe(Boolean.FALSE);
            userThirdInfo.setEmailIsBind(Boolean.FALSE);
            userThirdInfo.setMobileIsBind(Boolean.FALSE);
            userThirdInfo.setUserId(user.getId());
            userThirdInfo.setAppToken(UserExtraUtil.generateAppToken(userExtra, null));
            userThirdInfo.setCompanyId(company.getId());
            userThirdInfo.setBranchId(WebUtils.getCurrBranchId());
            AppBeanInjector.redisUtils.hPut(RedisConstant.PLATFORM_TAG, user.getId(), Integer.valueOf(1));
            this.userThirdInfoService.add(userThirdInfo);

            //用户基本信息表
            UserBasis userBasis = new UserBasis();
            userBasis.setUserId(user.getId());
            userBasis.setPoliticalOutlook(UserPoliticalOutlookType.MASSES.getValue());
            userBasis.setEducation(EducationType.UNDERGRADUATE.getValue());
            userBasis.setIncome(IncomeType.FOUR.getValue());
            this.userBasisService.add(userBasis);

            //生成店铺数据
            DealerShop dealerShop = new DealerShop();
            dealerShop.setId(company.getId());
            dealerShop.setAddress(company.getAddress());
            dealerShop.setBusinessManager(company.getBusinessManager());
            dealerShop.setCityAreaId(company.getCityAreaId());
            dealerShop.setCompanyId(company.getId());
            dealerShop.setCreated(company.getCreated());
            dealerShop.setCreator(company.getCreator());
            dealerShop.setFollowers(company.getFollowers());
            dealerShop.setGrade(company.getGrade());
            dealerShop.setLat(company.getLat());
            dealerShop.setLeader(user.getId());
            dealerShop.setLeaderTel(company.getLeaderTel());
            dealerShop.setLng(company.getLng());
            dealerShop.setLogo(company.getLogo());
            dealerShop.setName(company.getName());
            dealerShop.setServiceStaff(company.getServiceStaff());
            dealerShop.setServiceTel(company.getServiceTel());
            dealerShop.setShopArea(company.getShopArea());
            dealerShop.setShopInfo(company.getCompanyInfo());
            dealerShop.setStatus(company.getStatus());
            dealerShop.setType(company.getType());
            this.dealerShopService.add(dealerShop);

            //给该用户添加公司角色 店主
            CompanyEmployee companyEmployee = new CompanyEmployee();
            companyEmployee.setCompanyId(company.getId());
            companyEmployee.setUserId(user.getId());
            //店主编号未定
            Role role = this.roleService.selectByKey(DealerEmployeeRole.SHOPKEEPER.getValue(), WebUtils.getCurrBranchId());
            companyEmployee.setRoleId(role.getId());
            companyEmployee.setStatus(EmployeeStatus.NORMAL.getValue());
            companyEmployee.setCreated(DateUtil.getSystemDate());
            this.companyEmployeeService.add(companyEmployee);
            //把店主相关权限添加至 员工表中
            List<RolePermission> rolePermissionList = this.rolePermissionService.selectRolePermissionList(role.getId());
            if (rolePermissionList != null && rolePermissionList.size() != 0) {
                IIdGenerator idGenerator = AppBeanInjector.idGererateFactory;
                for (RolePermission rolePermission : rolePermissionList) {
                    //重新生成主键ID
                    rolePermission.setId(idGenerator.nextStringId());
                    //用公司员工主键ID替换权限ID
                    rolePermission.setRoleId(companyEmployee.getId());
                }
            }

            //新增员工时 设置 员工信息 初始数据
            EmployeeInfo employeeInfo = new EmployeeInfo();
            employeeInfo.setCompanyEmployeeId(companyEmployee.getId());
            employeeInfo.setUserId(user.getId());
            employeeInfo.setStatus(EmployeeInfoStatus.NORMAL.getValue() + "");
            this.employeeInfoService.add(employeeInfo);
            i++;
        }

        return ResultFactory.generateRequestResult("共修复经销商" + i + "个");
    }

}
