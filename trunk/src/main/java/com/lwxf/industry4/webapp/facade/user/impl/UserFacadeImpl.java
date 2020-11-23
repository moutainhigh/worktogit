package com.lwxf.industry4.webapp.facade.user.impl;

import com.lwxf.industry4.webapp.common.utils.AesEncryptUtil;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.util.SavedRequest;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.security.attacklocker.IAttackLocker;
import com.lwxf.commons.security.attacklocker.impl.AttackLockerInfo;
import com.lwxf.commons.uniquekey.IIdGenerator;
import com.lwxf.commons.uniquekey.LwxfWorkerIdGenerator;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.commons.utils.ValidateUtils;
import com.lwxf.industry4.webapp.baseservice.cache.constant.RedisConstant;
import com.lwxf.industry4.webapp.baseservice.sms.yunpian.SmsUtil;
import com.lwxf.industry4.webapp.baseservice.sms.yunpian.VerificationCodeType;
import com.lwxf.industry4.webapp.bizservice.branch.BranchService;
import com.lwxf.industry4.webapp.bizservice.common.CityAreaService;
import com.lwxf.industry4.webapp.bizservice.company.CompanyEmployeeService;
import com.lwxf.industry4.webapp.bizservice.system.RolePermissionService;
import com.lwxf.industry4.webapp.bizservice.system.RoleService;
import com.lwxf.industry4.webapp.bizservice.user.*;
import com.lwxf.industry4.webapp.common.authcode.AuthCodeUtils;
import com.lwxf.industry4.webapp.common.component.BaseFileUploadComponent;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.constant.BizConstant;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.dto.UserInfoObj;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.enums.company.DealerEmployeeRole;
import com.lwxf.industry4.webapp.common.enums.company.EmployeeStatus;
import com.lwxf.industry4.webapp.common.enums.system.MenusDisabled;
import com.lwxf.industry4.webapp.common.enums.system.MenusType;
import com.lwxf.industry4.webapp.common.enums.system.OperationsType;
import com.lwxf.industry4.webapp.common.enums.user.*;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.shiro.LwxfShiroRealm;
import com.lwxf.industry4.webapp.common.shiro.ShiroUtil;
import com.lwxf.industry4.webapp.common.uniquecode.UniquneCodeGenerator;
import com.lwxf.industry4.webapp.common.utils.UserExtraUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.company.CompanyDto;
import com.lwxf.industry4.webapp.domain.dto.companyEmployee.CompanyEmployeeDto;
import com.lwxf.industry4.webapp.domain.dto.user.LoginedUser;
import com.lwxf.industry4.webapp.domain.entity.branch.Branch;
import com.lwxf.industry4.webapp.domain.entity.common.CityArea;
import com.lwxf.industry4.webapp.domain.entity.company.Company;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeePermission;
import com.lwxf.industry4.webapp.domain.entity.system.Menus;
import com.lwxf.industry4.webapp.domain.entity.system.Operations;
import com.lwxf.industry4.webapp.domain.entity.system.Role;
import com.lwxf.industry4.webapp.domain.entity.system.RolePermission;
import com.lwxf.industry4.webapp.domain.entity.user.*;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.user.UserFacade;
import com.lwxf.mybatis.utils.MapContext;

import static com.lwxf.industry4.webapp.facade.AppBeanInjector.i18nUtil;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-24 17:58:17
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("userFacade")
public class UserFacadeImpl extends BaseFacadeImpl implements UserFacade {
    private Logger logger = LoggerFactory.getLogger(UserFacadeImpl.class);
    @Resource
    private UserService userService;
    @Resource(name = "userExtraService")
    private UserExtraService userExtraService;
    @Resource(name = "userResetpassService")
    transient private UserResetpassService userResetpassService;
    @Resource
	private BaseFileUploadComponent baseFileUploadComponent;
    @Resource(name = "rememberMeManager")
    private CookieRememberMeManager rememberMeManager;
    @Resource(name = "userThirdInfoService")
    private UserThirdInfoService userThirdInfoService;
    @Resource(name = "cityAreaService")
    private CityAreaService cityAreaService;
    @Resource(name="userBasisService")
    private UserBasisService userBasisService;
    @Resource(name="roleService")
    private RoleService roleService;
    @Resource(name="rolePermissionService")
    private RolePermissionService rolePermissionService;
    @Resource(name="companyEmployeeService")
    private CompanyEmployeeService companyEmployeeService;
    @Resource(name = "branchService")
    private BranchService branchService;
    //增加锁ip登录用户
    @Autowired
    @Qualifier("loginAttackLocker")
    protected IAttackLocker loginAttackLocker;

    @Resource(name = "ipAttackLocker")
    protected IAttackLocker ipAttackLocker;
    /*@Resource
    private RabbitMQService rabbitMQService;*/

    public static void main(String[] args) {
        String newPassword = "dddddd";
        String md5Password = new Md5Hash(newPassword).toString();
        System.out.println("md5Password:" + md5Password);
        RandomNumberGenerator rng = new SecureRandomNumberGenerator();
		String salt = rng.nextBytes().toBase64();
		String hashedPasswordBase64 = new SimpleHash(LwxfShiroRealm.HASH_ALGORITHM, newPassword, salt, LwxfShiroRealm.HASH_INTERATIONS)
                .toBase64();
        System.out.println(salt);
        System.out.println(hashedPasswordBase64);
    }

    @Override
    public User findUserById(String id) {
        return this.userService.findById(id);
    }

    @Override
    public RequestResult findUserByFilter(Integer pageNum, Integer pageSize, MapContext params) {

        PaginatedFilter filter = PaginatedFilter.newOne();
        params.put(WebConstant.KEY_ENTITY_BRANCH_ID,WebUtils.getCurrBranchId());
        filter.setFilters(params);
        Pagination pagination = Pagination.newOne();
        pagination.setPageNum(pageNum);
        pagination.setPageSize(pageSize);
        filter.setPagination(pagination);
		PaginatedList<User> list = this.userService.findByFilter(filter);
		return ResultFactory.generateRequestResult(list);
	}

    /**
     * 邮箱注册：todo：其他注册方式调用该方法需要相应处理
     * @param user
     * @param codeMap
     * @return
     */
    @Override
    @Transactional(value = "transactionManager",rollbackFor = Exception.class)
    public RequestResult addUser(User user,MapContext codeMap) {
        Map<String, Object> errMap = new HashMap<>();
        //埋数据发送邮件使用
//        TSManualData tsManualData = WebUtils.getTSManualData();
//        tsManualData.setClazz(User.class);
//        tsManualData.setParams(user);
//        //激活账号事件：直接与发邮件一一对应
//        tsManualData.setEvent(JMailService.MAILSEND_CLASSNAME_SENDACTIVATIONMAIL);
        //如果该邮箱未注册用户，则新建未激活用户
        User userOld = userService.findByEmail(user.getEmail());
        if (userOld != null) {
            //判断是否激活:如果已激活，返回前台；如果未激活，返回账户未激活。
            int state = userOld.getState();
            //邮箱已激活
            if (state == UserState.ENABLED.getValue()) {
                errMap.put("email", AppBeanInjector.i18nUtil.getMessage("VALIDATE_EMAIL_HAVE_EXISTED"));
                return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errMap);
            } else if (state == UserState.DISABLED.getValue()) {
                //邮箱已被：禁用
                errMap.put("email", AppBeanInjector.i18nUtil.getMessage("VALIDATE_EMAIL_IS_DISABLED"));
                return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errMap);
            }
        } else {
            user = UserExtraUtil.userNewOne(user.getEmail());
            user.setState(UserState.ENABLED.getValue());
            userService.add(user);
            //埋了一个用户对象
//            tsManualData.put("user", user);
        }
        UserResetpass userResetpass = userResetpassService.findByEmail(user.getEmail());
        if (userResetpass == null) {
            userResetpass = new UserResetpass();
            userResetpass.setCreated(DateUtil.getSystemDate());
            userResetpass.setEmail(user.getEmail());
            //埋了一个对象:为了发送注册激活邮件
//            tsManualData.put("userResetpass", userResetpass);
//            tsManualData.put("codeMap",codeMap);
            userResetpassService.add(userResetpass);
        } else {
            userResetpass.setCreated(DateUtil.getSystemDate());
            //埋了一个对象:为了发送注册激活邮件
//            tsManualData.put("userResetpass", userResetpass);
//            tsManualData.put("codeMap",codeMap);
            userResetpassService.updateUserResetpass(userResetpass);
        }

        return ResultFactory.generateRequestResult(user.getId()==null?userOld.getId():user.getId());
    }

    @Override
    @Transactional(value = "transactionManager",rollbackFor = Exception.class)
    public RequestResult updateUser(MapContext user) {
        String mobile = (String)user.get("mobile");
        String username = (String)user.get("username");
        String currUserId = WebUtils.getCurrUserId();
        if (null != mobile && mobile != "") {
            String s = "(^(\\d{3,4}-)?\\d{7,8})$|(1[3|5|7|8|9]\\d{9})";
            Pattern p=Pattern.compile(s);
            Matcher matcher=p.matcher(mobile);
            if(!matcher.matches()){
                return ResultFactory.generateResNotFoundResult();
            }
            User existUser = this.userService.findByMobile(mobile);
            if(null != existUser && !existUser.getId().equals(currUserId)){
                return ResultFactory.generateResNotFoundResult();
            }
        }
        /*if(username != null) {
            // 当前用户的那条信息
            User existUserCurr = WebUtils.getCurrUser();
            // 修改的用户名的那条信息(不一定存在)
            User existUserName = this.userService.findByUserName(username);
            // 修改的那条信息在userThirdInfo中的数据
            UserThirdInfo userThirdInfo = this.userThirdInfoService.findByUserId(currUserId);
            // 1. 查看当前用户是否是第一次修改用户名
            if(null != userThirdInfo && !userThirdInfo.getWxOpenId().equals(existUserCurr.getUsername())){
                return ResultFactory.generateResNotFoundResult();
            }
            // 2. 这个username是否存在并且是否是这个人的
            if(null != existUserName && !existUserName.getId().equals(currUserId)){
                return ResultFactory.generateResNotFoundResult();
            }
            // 3. 验证权限范围
            if(existUserCurr.getRole().intValue() == UserRole.MEMBER.getValue() || existUserCurr.getRole().intValue() == UserRole.CLERK.getValue()){
                if(!existUserCurr.getId().equals(currUserId)){
                    return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.BIZ_NO_PERMISSION_10003,AppBeanInjector.i18nUtil.getMessage("BIZ_NO_PERMISSION_10003"));
                }
            }
            user.put("username", username);
        }*/
        user.put(WebConstant.KEY_ENTITY_ID,currUserId);
        this.userService.updateByMapContext(user);
        return ResultFactory.generateSuccessResult();
	}

	@Override
    @Transactional(value = "transactionManager",rollbackFor = Exception.class)
    public RequestResult updateUserAvatar(String userId, MapContext userMap, MultipartFile file) {
        User user = userService.findById(userId);
        if (user==null) {
            String errMsg = i18nUtil.getMessage("BIZ_RES_NOT_FOUND_10001");
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RES_NOT_FOUND_10001, errMsg);
		}
        UploadInfo uploadInfo = baseFileUploadComponent.doUploadByModule(file, UploadResourceType.AVATAR, userId);
        userMap.put("avatar", uploadInfo.getRelativePath());
        try {
            //判断是系统新的头像地址进行删除
            if(user.getAvatar().startsWith(BizConstant.EASPYM4_FILE_DOMAIN) && !user.getAvatar().equals(AppBeanInjector.configuration.getUserDefaultAvatar())){
                baseFileUploadComponent.deleteFile(user.getAvatar(), UploadResourceType.AVATAR,0);
            }
            this.userService.updateByMapContext(userMap);
        } catch (RuntimeException ex) {
            logger.error("更新头像失败", ex);
            baseFileUploadComponent.deleteFile(uploadInfo.getRelativePath(), UploadResourceType.AVATAR,0);
			throw ex;
        }
        userMap.remove("id");
        return ResultFactory.generateRequestResult(userMap);
    }

    @Transactional(value = "transactionManager",rollbackFor = Exception.class)
    @Override
    public RequestResult updateUserBackground(String userId, MultipartFile file) {
        User user = userService.findById(userId);
        if (user == null) {
            String errMsg = i18nUtil.getMessage("BIZ_RES_NOT_FOUND_10001");
            return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RES_NOT_FOUND_10001, errMsg);
        }
        UploadInfo uploadInfo = baseFileUploadComponent.doUploadByModule(file, UploadResourceType.BACKGROUND, userId);
        MapContext mapContext = MapContext.newOne();
        mapContext.put("id", userId);
        mapContext.put("background", uploadInfo.getRelativePath());
        userService.updateByMapContext(mapContext);
        if (!LwxfStringUtils.isEmpty(user.getBackground())) {
			baseFileUploadComponent.deleteFile(user.getBackground(), UploadResourceType.BACKGROUND,0);
		}
        return ResultFactory.generateRequestResult(mapContext);
    }

    @Transactional(value = "transactionManager",rollbackFor = Exception.class)
    @Override
    public RequestResult deleteUserBackground(String userId) {
        User user = userService.findById(userId);
        MapContext mapContext = new MapContext();
        mapContext.put("id", userId);
        mapContext.put("background", "");
        userService.updateByMapContext(mapContext);
        baseFileUploadComponent.deleteFile(user.getBackground(), UploadResourceType.BACKGROUND,0);
        return ResultFactory.generateSuccessResult();
    }

    @Override
    public boolean deleteUserById(String id) {
        return this.userService.deleteById(id) > 0;
    }

    @Override
    public User findByEmail(String email) {
        return this.userService.findByEmail(email);
    }

    @Override
    public User findByMobile(String mobile) {
        return this.userService.findByMobile(mobile);
    }

    @Override
    public Boolean isPasswordCorrect(String email, String inputPassword) {
        User user = this.findByEmail(email);
        if(user == null){
            return false;
        }
        return this.userExtraService.isPasswordCorrect(user.getId(),inputPassword);
    }

    @Override
    @Transactional(value = "transactionManager",rollbackFor = Exception.class)
    public RequestResult toFactoryLogin(MapContext userMap, HttpServletRequest request) {

        String loginName = userMap.getTypedValue("loginName", String.class);
        boolean isEmail = false;
        boolean isMobile = false;
        if(ValidateUtils.isEmail(loginName)){
            isEmail = true;
        }
        if(ValidateUtils.isChinaPhoneNumber(loginName)){
            isMobile = true;
        }
        String password = userMap.getTypedValue("password", String.class);
        Boolean rememberMe = userMap.getTypedValue("rememberMe",Boolean.class);
        // 默认为记住我
        if(null == rememberMe){
            rememberMe = true;
        }
        //判断IP有没有被锁定
        AttackLockerInfo ipAttack = (AttackLockerInfo)this.ipAttackLocker.getLockerInfo(request.getServletPath());
        if(ipAttack.isLocked()){
            return ResultFactory.generateErrorResult(ErrorCodes.SYS_ERROR_LOGIN_MORETHAN_LIMIT_00018, i18nUtil.getMessage("SYS_ERROR_LOGIN_MORETHAN_LIMIT_00018",new Object[]{ipAttack.getLockMinutes()}));
        }
        User user = null;
        if(isEmail){
            user = this.userService.findByEmail(loginName);
        }else if(isMobile){
            user = this.userService.findByMobile(loginName);
        }else{
            user = this.userService.findByLoginName(loginName);
        }
        if (user == null){
            //如果被锁定，返回IP锁定信息
            if(ipAttack.tryLock()){
                return ResultFactory.generateErrorResult(ErrorCodes.SYS_ERROR_LOGIN_MORETHAN_LIMIT_00018, i18nUtil.getMessage("SYS_ERROR_LOGIN_MORETHAN_LIMIT_00018",new Object[]{ipAttack.getLockMinutes()}));
            }
            //用户名或密码错误
            return ResultFactory.generateErrorResult(ErrorCodes.LOGIN_FAIL_90000, i18nUtil.getMessage("LOGIN_FAIL_90000"));
        }
        //判断企业有没有过有效期
        String branchId=user.getBranchId();
        Branch branch=this.branchService.findById(branchId);
        if(branch==null){
            return ResultFactory.generateResNotFoundResult();
        }
//        String branchName=branch.getName();
//        String leaderName=branch.getLeaderName();
//        String leaderTel=branch.getTel();
        String expireDate=branch.getExpireDate();
        String keys=branchId;
        //解密有效期的AES加密数据
        String expireDateValue=AESDncode(expireDate,keys);
        if(expireDateValue==null){
            return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.SYS_UNKNOW_00000,AppBeanInjector.i18nUtil.getMessage("SYS_UNKNOW_00000"));
        }
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Long expire=dateFormat.parse(expireDateValue).getTime();
            Long dateTime=DateUtil.getSystemDate().getTime();
            if(dateTime>expire){
                return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.SYS_INVITATION_HAS_EXPIRED_00011,AppBeanInjector.i18nUtil.getMessage("SYS_INVITATION_HAS_EXPIRED_00011"));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        //判断用户被锁定
        AttackLockerInfo attackLockerInfo = (AttackLockerInfo)this.loginAttackLocker.getLockerInfo(user.getId());
        if (attackLockerInfo.isLocked()) {
            //保存用户锁定日志 TODO:
           	//UserExtraUtil.saveUserlog(user.getId(),UserActivityEvent.login_locked);
            return ResultFactory.generateErrorResult(ErrorCodes.SYS_ERROR_LOGIN_MORETHAN_LIMIT_00018, i18nUtil.getMessage("SYS_ERROR_LOGIN_MORETHAN_LIMIT_00018",new Object[]{attackLockerInfo.getLockMinutes()}));
        }
        //2.判断用户是否禁用 //  TODO:
        if (user.getState().equals(UserState.DISABLED.getValue())) {
            //UserExtraUtil.saveUserlog(user.getId(),UserActivityEvent.login_invalid_password);
            //用户被禁用
            return ResultFactory.generateErrorResult(ErrorCodes.SYS_ERROR_USER_ISDISABLED_00019, i18nUtil.getMessage("SYS_ERROR_USER_ISDISABLED_00019"));
        }
        CompanyEmployee employee = null;
        //判断是否是管理员
        if(!user.getType().equals(UserType.ADMIN.getValue())&&!user.getType().equals(UserType.SUPER_ADMIN.getValue())){
            //判断是否是厂家人员
            if(!user.getType().equals(UserType.FACTORY.getValue())){
                //如果不是厂家人员，查看用户是否被允许登陆系统
                if(user.getEnableLoginPc()==0) {//不允许
                    return ResultFactory.generateErrorResult(ErrorCodes.LOGIN_FAIL_90000, i18nUtil.getMessage("LOGIN_FAIL_90000"));
                }
            }
            // 公司职员处理
            employee = AppBeanInjector.companyEmployeeService.findOneByCompanyIdAndUserId(null,user.getId());
            // 不是工厂职工或者职工状态不是正常状态，不允许登录
            if(null == employee ||  employee.getStatus().intValue() != EmployeeStatus.NORMAL.getValue()){
                return ResultFactory.generateErrorResult(ErrorCodes.LOGIN_FAIL_90000, i18nUtil.getMessage("LOGIN_FAIL_90000"));
            }
        }
        //3.判断用户名或密码是否正确
        if (!this.doLogin(user, password,rememberMe)) {
            //锁用户：
            if (attackLockerInfo.isLocked() || attackLockerInfo.tryLock()) {
                ipAttack.tryLock();
                //保存用户锁定日志  TODO:
               // UserExtraUtil.saveUserlog(user.getId(),UserActivityEvent.login_locked);
                //返回用户锁定信息
                return ResultFactory.generateErrorResult(ErrorCodes.SYS_ERROR_LOGIN_MORETHAN_LIMIT_00018, i18nUtil.getMessage("SYS_ERROR_LOGIN_MORETHAN_LIMIT_00018",new Object[]{attackLockerInfo.getLockMinutes()}));
            }
            //  TODO:
            //UserExtraUtil.saveUserlog(user.getId(),UserActivityEvent.login_invalid_password);
            return ResultFactory.generateErrorResult(ErrorCodes.LOGIN_FAIL_90000, i18nUtil.getMessage("LOGIN_FAIL_90000"));
        }
        //  TODO:
        //UserExtraUtil.saveUserlog(UserActivityEvent.login_normal);
        RequestResult result = ResultFactory.generateSuccessResult();
        SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
        String redirectPath;
        if (savedRequest == null) {
            //查询首页路径
            Role byId = AppBeanInjector.roleService.findById(employee.getRoleId());
            String homePage=byId.getHomePage();
            if(homePage==null||homePage.equals("")){
                redirectPath = WebConstant.REDIRECT_PATH_FACTORY_ADMIN+"#/"+WebConstant.PAGE_FACTORY_HOME;
            }else {
                redirectPath = homePage;
            }

		} else {
            redirectPath = savedRequest.getRequestUrl();
        }
        //判断是否是管理员
        if(!user.getType().equals(UserType.ADMIN.getValue())&&!user.getType().equals(UserType.SUPER_ADMIN.getValue())){
            result.put(WebConstant.KEY_ENTITY_COMPANY_ID,employee.getCompanyId());
        }
        //判断是否允许PC端登录
        if (user.getEnableLoginPc() != null && user.getEnableLoginPc().equals(WebConstant.DISABLE)) {
            //用户被禁用登录PC端
            return ResultFactory.generateErrorResult(ErrorCodes.SYS_ERROR_USER_ISDISABLED_00019, i18nUtil.getMessage("SYS_ERROR_USER_ISDISABLED_00019"));
        }

        result.put("go", redirectPath);
        return result;
    }

    private String AESDncode(String expireDate, String keys) {
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen= KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG") ;
            secureRandom.setSeed(keys.getBytes());
            keygen.init(128, secureRandom);
            //keygen.init(128, new SecureRandom(keys.getBytes()));
            //3.产生原始对称密钥
            SecretKey original_key=keygen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte [] raw=original_key.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key=new SecretKeySpec(raw, "AES");
            //6.根据指定算法AES自成密码器
            Cipher cipher=Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key);
            //8.将加密并编码后的内容解码成字节数组
            byte [] byte_content= new BASE64Decoder().decodeBuffer(expireDate);
            /*
             * 解密
             */
            byte [] byte_decode=cipher.doFinal(byte_content);
            String AES_decode=new String(byte_decode,"utf-8");
            return AES_decode;
        } catch (Exception e) {
            e.printStackTrace();
        }

        //如果有错就返加nulll
        return null;
    }

    @Override
    @Transactional(value = "transactionManager",rollbackFor = Exception.class)
    public Boolean doLogin(User user, String inputPassword,boolean remberMe) {
        //  User user = this.findByEmail(email);
        logger.debug("doLogin:userExtraService.isPasswordCorrect");
        if (!this.userExtraService.isPasswordCorrect(user.getId(), inputPassword)) {
            return false;
        }
        //更新用户最后登录时间
        MapContext userMap = MapContext.newOne();
        userMap.put("id", user.getId());
        userMap.put("lastLogin", DateUtil.getSystemDate());
        userService.updateByMapContext(userMap);
        logger.debug("doLogin:userService.updateByMapContext");
        Subject userSubject = SecurityUtils.getSubject();

        UserExtra userExtra = userExtraService.findById(user.getId());
        UsernamePasswordToken token = new UsernamePasswordToken(
                user.getId(), userExtra.getToken());
        token.setRememberMe(remberMe);
        userSubject.login(token);
        logger.debug("doLogin:userSubject.login");
        token.clear();
        return true;
    }

    @Override
    public UserExtra findUserExtraByUserId(String userId) {
        return this.userExtraService.findById(userId);
    }

    /**
     * 获取当前用户的权限信息
     * @param id
     * @return
     */
    @Override
    public Collection<String> getUserHasPermissionsById(String id) {
        Set<String> perms = new HashSet<>();
		LoginedUser currUser = WebUtils.getCurrUser();
		User user = this.userService.findByUserId(id);
		if(user.getState().intValue() != UserState.ENABLED.getValue()){
			return perms;
		}
    		CompanyEmployee employee = currUser.getCompanyEmployee();
		if(employee.getStatus().intValue() != EmployeeStatus.NORMAL.getValue()){
		    return perms;
        }
		int userType = currUser.getType().intValue();
		String userTypeStr = String.valueOf(userType);
//		String currCompanyId = WebUtils.getCurrCompanyId();
//        if(!employee.getCompanyId().equals(currCompanyId)){
//            return perms;
//        }
		switch(userType){
			case 0: // 厂家
				break;
			case 1: // 经销商
				break;
			case 2: // 终端客户
				break;
			/*default:
                return perms;*/
		}
		List<RolePermission> employeePermissions = AppBeanInjector.rolePermissionService.selectRolePermissionList(employee.getRoleId());
		String[] operationsArr;
		String split = ",";
		String permStrTemplate="{0}_{1}:{2}:";
		String permStr;
		int m;
		int operationsArrLen;
		for (RolePermission ep:employeePermissions) {
			operationsArr = ep.getOperations().split(split);
			m = 0;
			operationsArrLen = operationsArr.length;
			permStr = LwxfStringUtils.format(permStrTemplate,userTypeStr,ep.getModuleKey(),ep.getMenuKey());
			for(;m<operationsArrLen;m++){
				perms.add(permStr.concat(operationsArr[m]));
			}
		}
        return perms;
    }

    @Override
    @Transactional(value = "transactionManager",rollbackFor = Exception.class)
    public RequestResult updateUserPassword(String userId, String password) {
        UserExtra userExtra = userExtraService.findById(userId);
        //如果用户的原密码不为空，验证用户的原密码
        /*if(!LwxfStringUtils.isBlank(userExtra.getPassword())){
            // 验证oldPassword
            if (!this.userExtraService.isPasswordCorrect(userId, oldPassword)) {
                Map<String, String> errMap = new HashMap<>();
                errMap.put("password", ErrorCodes.VALIDATE_PASSWORD_INCORRECT);
                return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errMap);
            }
        }*/

        UserExtraUtil.saltingPassword(userExtra, password);
		MapContext mapContext = new MapContext();
		mapContext.put("salt",userExtra.getSalt());
		mapContext.put("userId",userExtra.getUserId());
		mapContext.put("password",userExtra.getPassword());
        mapContext.put("token", userExtra.getToken());
		this.userExtraService.updateByMapContext(mapContext);

		// 更新app token
        mapContext = MapContext.newOne();
        mapContext.put(WebConstant.KEY_ENTITY_USER_ID,userId);
        mapContext.put(WebConstant.KEY_ENTITY_APP_TOKEN,UserExtraUtil.generateAppToken(userExtra,null));
        this.userThirdInfoService.updateByMapContext(mapContext);

        this.resetRememberMeCookie(password);

        return ResultFactory.generateSuccessResult();
    }

    /**
     *  厂家后台的预加载数据
     * @param userId
     * @return
     */
    @Override
    public RequestResult findUserPreloadData(String userId) {
        //todo 设置角色权限的数据
        Map<String, Object> map = new HashMap<>();
        Map<String,Company> companyMap = new HashMap<>();
        CompanyEmployee companyEmployee = null;
        CompanyDto companyDto = null;
        // 1. 当前用户信息
        User userMap = this.userService.findByUserId(userId);
        Integer userType = userMap.getType();
        String branchId=userMap.getBranchId();
        Branch branch = this.branchService.findById(branchId);
        map.put("branchName",branch.getName());
        map.put(WebConstant.KEY_PRELOAD_USER, userMap);
        Set<String> userMenuKeys = new LinkedHashSet<>();
        Map<String,String> userOperations = new HashMap<String, String>();
        Map userMenu = new HashMap();
        //判断用户类型是不是admin或者super_admin
        if(userType==UserType.ADMIN.getValue()||userType==UserType.SUPER_ADMIN.getValue()){
            companyDto = AppBeanInjector.companyService.findCompanyById(userMap.getBranchId());
            companyMap.put(userMap.getBranchId(),companyDto);
            List<Menus> menusList = AppBeanInjector.menusService.findAllByTypeAndDisabled(MenusType.FACTORY_BACKSTAGE.getValue(),null);
            List<Operations> operationsList = AppBeanInjector.operationsService.findAllByTypes(Arrays.asList(OperationsType.PUBLIC.getValue(),OperationsType.FACTORY.getValue()));
            StringBuffer sb = new StringBuffer();
            for(Operations operations:operationsList){
                sb.append(operations.getKey()).append(",");
            }
            for(Menus p:menusList){
                userMenuKeys.add(p.getKey());
//                if(!p.getFolder()){
                    userOperations.put(p.getKey(),sb.toString());
//                }
            }
            userMenu.put("userMenuKeys",userMenuKeys);
            userMenu.put("userOperations",userOperations);
            map.put(WebConstant.KEY_PRELOAD_COMPANY,companyMap);
            map.put(WebConstant.KEY_PRELOAD_USER_MENU, userMenu);
        } else{
            // 2. 加载公司信息
            //Branch branch = AppBeanInjector.branchService.findById(branchId);
            companyEmployee = AppBeanInjector.companyEmployeeService.findCompanyByUidAndStatus(userId,EmployeeStatus.NORMAL.getValue());
            companyDto = AppBeanInjector.companyService.findCompanyById(companyEmployee.getCompanyId());
            map.put("currCompanyId",companyEmployee.getCompanyId());
            companyMap.put(companyEmployee.getCompanyId(),companyDto);
            map.put(WebConstant.KEY_PRELOAD_COMPANY,companyMap);
            // 用户的职位信息
            CompanyEmployee userEmployee = AppBeanInjector.companyEmployeeService.findOneByCompanyIdAndUserId(companyDto.getId(),userId);
            //角色的key
            Role byId = this.roleService.findById(userEmployee.getRoleId());
            map.put("roleKey",byId.getKey());
            if(null != userEmployee && userEmployee.getStatus().intValue() == 0){
                map.put(WebConstant.KEY_PRELOAD_COMPANY_EMPLOYEE, userEmployee);
                // 用户权限
                List<RolePermission> permissions = AppBeanInjector.rolePermissionService.selectRolePermissionList(userEmployee.getRoleId());
                if(permissions!=null){
                    for(RolePermission p:permissions){
                        userMenuKeys.add(p.getModuleKey());
                        userMenuKeys.add(p.getMenuKey());
                        userOperations.put(p.getMenuKey(),p.getOperations());
                    }
                    userMenu.put("userMenuKeys",userMenuKeys);
                    userMenu.put("userOperations",userOperations);
                    map.put(WebConstant.KEY_PRELOAD_USER_MENU, userMenu);
                }
            }
        }
        // 3. 字典数据
        Map<String,Object> dictionaries = new HashMap<>();
        map.put(WebConstant.KEY_PRELOAD_DICTIONARIES, dictionaries);
        // 加载菜单
        List<Menus> menus = AppBeanInjector.menusService.findAll();
        Iterator<Menus> menusIt = menus.iterator();
        Menus menuTemp;
        while(menusIt.hasNext()){
            menuTemp = menusIt.next();
            if(userMenuKeys.contains(menuTemp.getKey())){
                continue;
            }
            menusIt.remove();
        }
        dictionaries.put(WebConstant.KEY_PRELOAD_MENUS,menus);
        // 加载操作按钮
        dictionaries.put(WebConstant.KEY_PRELOAD_OPERATIONS,AppBeanInjector.operationsService.findAll());
        // 加载产品分类
        dictionaries.put(WebConstant.KEY_PRELOAD_PRODUCT_CATEGORY,AppBeanInjector.productCategoryService.findAllByBranchId(companyDto==null?null:companyDto.getBranchId()));
        // 加载产品材质
        dictionaries.put(WebConstant.KEY_PRELOAD_PRODUCT_MATERIAL,AppBeanInjector.productMaterialService.findAll());
        // 加载产品规格
        dictionaries.put(WebConstant.KEY_PRELOAD_PRODUCT_SPEC,AppBeanInjector.productSpecService.findAll());
        // 加载产品颜色
        dictionaries.put(WebConstant.KEY_PRELOAD_PRODUCT_COLOR,AppBeanInjector.productColorService.findAll());
        // 加载角色
        dictionaries.put(WebConstant.KEY_PRELOAD_ROLES,AppBeanInjector.roleService.findListByType(userType,null,WebUtils.getCurrBranchId()));
        // 加载部门
        dictionaries.put(WebConstant.KEY_PRELOAD_DEPTS,AppBeanInjector.deptService.findListByCompanyId(companyDto==null?null:companyDto.getId()));
        // 加载字典数据
        dictionaries.put(WebConstant.KEY_BASE_CODE,AppBeanInjector.basecodeService.findAll());
        //4. 用户权限数据
        Map employeePermission = new HashMap<>();
        return ResultFactory.generateRequestResult(map);
    }

    @Override
    public List<User> findAll() {
        //批量给用户发邮件时需要　TODO:2017年11月24日　未实现，等业务需要时候确定是否要分页
        return null;
    }

    @Override
    @Transactional(value = "transactionManager",rollbackFor = Exception.class)
    public RequestResult resendEmail(User user) {
        //同注册逻辑，但是 未注册：邮箱未被注册，返回验证类的错误信息，告诉用户该邮箱未被注册
        //未注册：邮箱未被注册，返回验证类的错误信息，告诉用户该邮箱未被注册
        //已激活：返回验证类的错误信息，告诉用户该邮箱账号已被激活
        Map<String, Object> errMap = new HashMap<>();
        //埋数据发送邮件使用
//        TSManualData tsManualData = WebUtils.getTSManualData();
//        tsManualData.setClazz(UserResetpass.class);

        //激活账号事件：直接与发邮件一一对应
//        tsManualData.setEvent(JMailService.MAILSEND_CLASSNAME_SENDACTIVATIONMAIL);
        //如果该邮箱未注册用户，则新建未激活用户
        User userOld = userService.findByEmail(user.getEmail());
        if (userOld != null) {
            //判断是否激活:如果已激活，返回前台；如果未激活，返回账户未激活。
            int state = userOld.getState();
            //邮箱已激活
            if (state == UserState.ENABLED.getValue()) {
                errMap.put("email", AppBeanInjector.i18nUtil.getMessage("VALIDATE_EMAIL_HAVE_EXISTED"));
                return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errMap);
            } else if (state == UserState.DISABLED.getValue()) {
                //邮箱已被：禁用
                errMap.put("email", AppBeanInjector.i18nUtil.getMessage("VALIDATE_EMAIL_IS_DISABLED"));
                return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errMap);
            }
        } else {
            errMap.put("email", AppBeanInjector.i18nUtil.getMessage("VALIDATE_EMAIL_IS_DISABLED"));
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errMap);
        }
        UserResetpass userResetpass = userResetpassService.findByEmail(user.getEmail());
        if (userResetpass == null) {
            userResetpass = new UserResetpass();
            userResetpass.setCreated(DateUtil.getSystemDate());
            userResetpass.setEmail(user.getEmail());
//            tsManualData.setParams(userResetpass);
            userResetpassService.add(userResetpass);
        } else {
            userResetpass.setCreated(DateUtil.getSystemDate());
//            tsManualData.setParams(userResetpass);
            userResetpassService.updateUserResetpass(userResetpass);
        }

        //埋了一个对象:为了发送注册激活邮件
//        tsManualData.put("userResetpass", userResetpass);
        return ResultFactory.generateSuccessResult();
    }

    @Override
    public RequestResult passwordAccredit(String password) {
        if(userExtraService.isPasswordCorrect(WebUtils.getCurrUserId(), password)){
            return ResultFactory.generateSuccessResult();
        }

        Map<String, Object> errormap = new HashMap<>();
        errormap.put("password", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INVALID_PASSWORD"));
        return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errormap);
    }

    private void resetRememberMeCookie(String password){
        if(null != ShiroUtil.getCurrUserId()){
            UsernamePasswordToken token = new UsernamePasswordToken(
                    ShiroUtil.getCurrUserId(), password);
            token.setRememberMe(true);
            SecurityUtils.getSubject().login(token);
        }
    }

    @Override
    @Transactional(value = "transactionManager",rollbackFor = Exception.class)
    public RequestResult userMobileRegister(User user, MapContext registerMapContext) {
        Map<String, Object> errMap = new HashMap<>();
        //如果该邮箱未注册用户，则新建未激活用户
        User userOld = userService.findByMobile(user.getMobile());
        if (userOld != null) {
            //判断是否激活:如果已激活，返回前台；如果未激活，返回账户未激活。
            int state = userOld.getState();
            //邮箱已激活
            if (state == UserState.ENABLED.getValue()) {
                errMap.put("mobile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_MOBILE_HAVE_EXISTED"));
                return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errMap);
            } else if (state == UserState.DISABLED.getValue()) {
                //账号禁用
                errMap.put("mobile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_MOBILE_IS_DISABLED"));
                return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errMap);
            }
        } else {
            //设置主账号类型为：手机
            //用户激活状态为:已激活
            user.setState(UserState.ENABLED.getValue());
            //用户默认头相，语言等设置
            UserExtraUtil.setOtherInfo(user);
            //1.保存用户主表信息
            userService.add(user);
            //2.保存用户密码表信息
            UserExtra userExtra = new UserExtra();
            userExtra.setUserId(user.getId());
            UserExtraUtil.saltingPassword(userExtra,registerMapContext.getTypedValue("password",String.class));
            userExtra.setUpdated(DateUtil.getSystemDate());
            userExtraService.add(userExtra);
            //3.保存用户第三方信息:手机号
            UserThirdInfo thirdInfo = UserExtraUtil.userThirdInfoInitFromMobileRegister(user.getId());
            thirdInfo.setAppToken(UserExtraUtil.generateAppToken(userExtra,null));
            userThirdInfoService.add(thirdInfo);
        }
        return ResultFactory.generateSuccessResult();
    }

    @Override
    @Transactional(value = "transactionManager",rollbackFor = Exception.class)
    public RequestResult userEmailRegister(User user, MapContext registerMapContext, MapContext codeMap) {
        Map<String, Object> errMap = new HashMap<>();
        //埋数据发送邮件使用
//        TSManualData tsManualData = WebUtils.getTSManualData();
//        tsManualData.setClazz(User.class);
//        tsManualData.setParams(user);
//        //激活账号事件：直接与发邮件一一对应
//        tsManualData.setEvent(JMailService.MAILSEND_CLASSNAME_SENDACTIVATIONMAIL);
        //如果该邮箱未注册用户，则新建未激活用户
        User userOld = userService.findByEmail(user.getEmail());
        if (userOld != null) {
            //判断是否激活:如果已激活，返回前台；如果未激活，返回账户未激活。
            int state = userOld.getState();
            //邮箱已激活
            if (state == UserState.ENABLED.getValue()) {
                errMap.put("email", AppBeanInjector.i18nUtil.getMessage("VALIDATE_EMAIL_HAVE_EXISTED"));
                return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errMap);
            } else if (state == UserState.DISABLED.getValue()) {
                //邮箱已被：禁用
                errMap.put("email", AppBeanInjector.i18nUtil.getMessage("VALIDATE_EMAIL_IS_DISABLED"));
                return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errMap);
            }
        } else {
            //设置主账号类型为：邮箱
            //用户激活状态为:未激活
            user.setState(UserState.ENABLED.getValue());
            //用户默认头相，语言等设置
            UserExtraUtil.setOtherInfo(user);
            //1.保存用户主表信息
            userService.add(user);
            //埋了一个用户对象
//            tsManualData.put("user", user);
            //2.保存用户密码表信息
            UserExtra userExtra = new UserExtra();
            userExtra.setUserId(user.getId());
            UserExtraUtil.saltingPassword(userExtra,registerMapContext.getTypedValue("password",String.class));
            userExtra.setUpdated(DateUtil.getSystemDate());
            userExtraService.add(userExtra);
            //3.保存用户第三方信息:邮箱
            UserThirdInfo thirdInfo = UserExtraUtil.userThirdInfoInitFromEmailRegister(user.getId());
            thirdInfo.setAppToken(UserExtraUtil.generateAppToken(userExtra,null));
            userThirdInfoService.add(thirdInfo);
        }
        //发送激活邮件
        UserResetpass userResetpass = userResetpassService.findByEmail(user.getEmail());
        //原激活邮件不存在
        if (userResetpass == null) {
            userResetpass = new UserResetpass();
            userResetpass.setCreated(DateUtil.getSystemDate());
            userResetpass.setEmail(user.getEmail());
            //埋了一个对象:为了发送注册激活邮件
//            tsManualData.put("userResetpass", userResetpass);
//            tsManualData.put("codeMap",codeMap);
            userResetpassService.add(userResetpass);
        }
        //原激活邮件已经存在，则重新发送
        else {
            userResetpass.setCreated(DateUtil.getSystemDate());
            //埋了一个对象:为了发送注册激活邮件
//            tsManualData.put("userResetpass", userResetpass);
//            tsManualData.put("codeMap",codeMap);
            userResetpassService.updateUserResetpass(userResetpass);
        }

        return ResultFactory.generateSuccessResult();
    }

    @Override
    public RequestResult getPasswordState() {
        UserExtra userExtra = userExtraService.findById(WebUtils.getCurrUserId());
        Map result = new HashMap(1);
        result.put("pwdBlank", LwxfStringUtils.isBlank(userExtra.getPassword()) ? Boolean.TRUE : Boolean.FALSE);
        return ResultFactory.generateRequestResult(result);
    }

	@Override
	@Transactional(value = "transactionManager",rollbackFor = Exception.class)
	public RequestResult updateUserState(String id, MapContext update) {
		User user = this.userService.findById(id);
		if (user==null)
		{
			return ResultFactory.generateResNotFoundResult();
		}
		update.put(WebConstant.KEY_ENTITY_ID,id);
		this.userService.updateByMapContext(update);
		return  ResultFactory.generateRequestResult(update);
	}

    @Override
    public RequestResult findUserInfoByUserIds(List<String> id) {
        return ResultFactory.generateRequestResult(this.userService.findUserInfoByUserIds(id));
    }

    @Override
    public LoginedUser findLoginedUserById(String id) {
        User user = this.userService.findById(id);
        LoginedUser loginedUser = new LoginedUser(user);
        CompanyEmployee employee = AppBeanInjector.companyEmployeeService.selectByUserId(id);
        if(null != employee){
			loginedUser.setCompanyEmployee(employee);
		}
		/*List<CompanyShareMember> members = AppBeanInjector.companyShareMemberService.findActiveByUserId(id);
        if(members.size()>0){
            loginedUser.setCompanyShareMember(members.get(0));
        }*/
        return loginedUser;
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult userRegister(String userPass,String mobile) {
        // 1. 创建用户账号（user）
        User user = new User();
        user.setName(mobile);
        user.setSex(UserSex.MAN.getValue());
        user.setAvatar(AppBeanInjector.configuration.getUserDefaultAvatar());
        user.setTimeZone(WebConstant.TIMEZONE_DEFAULT);
        user.setLanguage(WebConstant.LANGUAGE_DEFAULT);
        user.setCreated(DateUtil.getSystemDate());
        user.setState(UserState.ENABLED.getValue());
        user.setMobile(mobile);
        user.setFollowers(0);
        AppBeanInjector.userService.add(user);


        // 2. 创建用户扩展信息（UserExtro）
        UserExtra extra = new UserExtra();
        extra.setUserId(user.getId());
        UserExtraUtil.saltingPassword(extra, userPass);
        AppBeanInjector.userExtraService.add(extra);





        // 3. 创建用户第三方账号信息（UserThirdInfo）
        UserThirdInfo thirdInfo = new UserThirdInfo();
        thirdInfo.setWxNickname(user.getMobile());
        thirdInfo.setWxIsBind(Boolean.FALSE);
        thirdInfo.setWxIsSubscribe(Boolean.FALSE);
        thirdInfo.setEmailIsBind(Boolean.FALSE);
        thirdInfo.setMobileIsBind(Boolean.FALSE);
        thirdInfo.setUserId(user.getId());
        thirdInfo.setAppToken(UserExtraUtil.generateAppToken(extra,null));
        AppBeanInjector.userThirdInfoService.add(thirdInfo);
        AppBeanInjector.redisUtils.hPut(RedisConstant.PLATFORM_TAG, user.getId(), Integer.valueOf(1));
        return ResultFactory.generateRequestResult(UserInfoObj.newOne(user,null,thirdInfo,null));
    }

	@Override
    @Transactional(value = "transactionManager")
	public RequestResult updateUserPassword(MapContext update) {
		Map<String, String> errorMap = new HashMap<>();
		// 验证密码有效性
		String newPassword = update.getTypedValue("newpassword", String.class);

		String mobile = (String) update.get("mobile");
		User user = this.userService.findByMobile(mobile);

		// 验证手机验证码有效性
		String mobileCode = update.getTypedValue("smsCode", String.class);
		//从缓存中取短信验证码
		String serverMobileCode = SmsUtil.getMobileVerificationCode(user.getMobile(), VerificationCodeType.UPDATE_PASSWORD);
		if (serverMobileCode == null || !serverMobileCode.equals(mobileCode)) {
			errorMap.put("smsCode", AppBeanInjector.i18nUtil.getMessage("VALIDATE_AUTHCODE_ERROR_20024"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errorMap);
		}
		UserExtra userExtra = new UserExtra();
		UserExtraUtil.saltingPassword(userExtra, newPassword);
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

		this.resetRememberMeCookie(newPassword);

//		toSave.remove(WebConstant.KEY_ENTITY_USER_ID);
		return ResultFactory.generateSuccessResult();
	}
//用户信息展示
    @Override
    public RequestResult findUserMessageById(String userId) {
         User user=this.userService.findByUserId(userId);
         if(user==null){
             return ResultFactory.generateResNotFoundResult();

         }
        return ResultFactory.generateRequestResult(this.userService.findUserMessageById(userId));
    }
//修改个人信息
    @Override
    @Transactional(value = "transactionManager")
    public RequestResult updateUserMessage(String userId, MapContext mapContext) {
        User user=this.userService.findByUserId(userId);
        if(mapContext.containsKey("mobile")){
            //验证手机
            String mobile=mapContext.getTypedValue("mobile",String.class);
            if (!ValidateUtils.isChinaPhoneNumber(mobile)) {
                return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_INVALID_MOBILE_NO, AppBeanInjector.i18nUtil.getMessage("VALIDATE_INVALID_MOBILE_NO"));
            }
            //查询新手机号是否已存在
            User us=this.userService.findByMobile(mobile);
            if(us!=null){
                return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_MOBILE_HAVE_EXISTED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_MOBILE_HAVE_EXISTED"));
            }
        }
        if(user==null){
            return ResultFactory.generateResNotFoundResult();
        }
        if(mapContext.containsKey("loginName")){

            mapContext.put("changedLoginName",true);

        }
        mapContext.put("id",userId);
        this.userService.updateByMapContext(mapContext);
        return ResultFactory.generateRequestResult("update success");
    }

    @Override
    public RequestResult findUserInfo(String uid) {
        MapContext mapContext = MapContext.newOne();
        mapContext.put(WebConstant.KEY_ENTITY_USER_ID,uid);
        mapContext.put(WebConstant.KEY_ENTITY_COMPANY_ID,WebUtils.getCurrCompanyId());
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        paginatedFilter.setFilters(mapContext);
        PaginatedList<CompanyEmployeeDto> filter = AppBeanInjector.companyEmployeeService.findListByFilter(paginatedFilter);
        return ResultFactory.generateRequestResult(filter.getRows().get(0));
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult updatePassword(String userId,MapContext mapContext) {
        String newPassword = mapContext.getTypedValue("newpassword", String.class);
        UserExtra userExtra = new UserExtra();
        UserExtraUtil.saltingPassword(userExtra, newPassword);
        MapContext toSave = new MapContext();
        toSave.put(WebConstant.KEY_USER_EXTRA_SALT, userExtra.getSalt());
        toSave.put(WebConstant.KEY_ENTITY_USER_ID, userId);
        toSave.put(WebConstant.KEY_USER_EXTRA_PASSWORD, userExtra.getPassword());
        toSave.put(WebConstant.KEY_USER_EXTRA_TOKEN, userExtra.getToken());
        this.userExtraService.updateByMapContext(toSave);
        return ResultFactory.generateRequestResult("update success");
    }

    @Override
    public RequestResult registerAuthCode(MapContext mapContext) {
        Map<String, String> errorMap = new HashMap<>();
        String mobile = mapContext.getTypedValue(WebConstant.KEY_ENTITY_MOBILE, String.class);
        //页面短信验证码
        String mobileCode = mapContext.getTypedValue("smsCode", String.class);
        //从缓存中取短信验证码
        String serverMobileCode = SmsUtil.getMobileVerificationCode(mobile, VerificationCodeType.REGISTER);
        if (serverMobileCode == null || !serverMobileCode.equals(mobileCode)) {
            errorMap.put("smsCode", AppBeanInjector.i18nUtil.getMessage("VALIDATE_AUTHCODE_ERROR_20024"));
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errorMap);
        }
        MapContext data = MapContext.newOne();
        return ResultFactory.generateRequestResult(data);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult BuserRegister(MapContext mapContext) {
        Map<String, Object> errMap = new HashMap<>();
        String mobile=mapContext.getTypedValue("mobile",String.class);
        User userOld=this.userService.findByMobile(mobile);
        //判断该手机是否已注册
        if(userOld!=null){
         int state=userOld.getState();
            //已激活
            if (state == UserState.ENABLED.getValue()) {
                errMap.put(WebConstant.KEY_ENTITY_MOBILE, AppBeanInjector.i18nUtil.getMessage("VALIDATE_MOBILE_HAVE_EXISTED"));
                return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errMap);
            } else if (state == UserState.DISABLED.getValue()) {
                //账号禁用
                errMap.put(WebConstant.KEY_ENTITY_MOBILE, AppBeanInjector.i18nUtil.getMessage("VALIDATE_MOBILE_IS_DISABLED"));
                return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errMap);
            }
        }else {
            User user=new User();
            user.setName(mapContext.getTypedValue("name",String.class));
            user.setMobile(mobile);
            user.setSex(UserSex.MAN.getValue());
            user.setFollowers(0);
            user.setLoginName(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.USER_LOGNAME_NO));
            user.setType(UserType.SHARE.getValue());
            user.setChangedLoginName(false);
            user.setState(UserState.ENABLED.getValue());
            //用户默认头相，语言等设置
            user.setAvatar(AppBeanInjector.configuration.getUserDefaultAvatar());
            user.setTimeZone(WebConstant.TIMEZONE_DEFAULT);
            user.setLanguage(WebConstant.LANGUAGE_DEFAULT);
            user.setCreated(DateUtil.getSystemDate());
            //1.保存用户主表信息
            this.userService.add(user);
            //2.保存用户密码表信息
            UserExtra userExtra = new UserExtra();
            userExtra.setUserId(user.getId());
            UserExtraUtil.saltingPassword(userExtra,mapContext.getTypedValue("password",String.class));
            userExtra.setUpdated(DateUtil.getSystemDate());
            this.userExtraService.add(userExtra);
            //3.保存用户第三方信息
            UserThirdInfo thirdInfo = UserExtraUtil.userThirdInfoInitFromMobileRegister(user.getId());
            thirdInfo.setAppToken(UserExtraUtil.generateAppToken(userExtra,null));
            AppBeanInjector.redisUtils.hPut(RedisConstant.PLATFORM_TAG, user.getId(), Integer.valueOf(1));
            this.userThirdInfoService.add(thirdInfo);
            //用户基础扩展信息
            String userId=user.getId();
            UserBasis userBasis=new UserBasis();
            userBasis.setUserId(userId);
            if(mapContext.containsKey("address")) {
                userBasis.setAddress((String) mapContext.get("address"));
            }
            userBasis.setEducation(EducationType.UNDERGRADUATE.getValue());
            userBasis.setIncome(IncomeType.FOUR.getValue());
            if(mapContext.containsKey("work")) {
                userBasis.setWork((String) mapContext.get("work"));
            }
            if(mapContext.containsKey("workUnit")) {
                userBasis.setWorkUnit((String) mapContext.get("workUnit"));
            }
            userBasis.setPoliticalOutlook(UserPoliticalOutlookType.MASSES.getValue());
            this.userBasisService.add(userBasis);
            //分配指定的店铺
            CompanyEmployee companyEmployee = new CompanyEmployee();
            companyEmployee.setCompanyId(mapContext.getTypedValue("companyId",String.class));
            companyEmployee.setUserId(user.getId());
            String key= DealerEmployeeRole.CLERK.getValue();
            Role role=this.roleService.selectByKey(key,WebUtils.getCurrBranchId());
            companyEmployee.setRoleId(role.getId());
            companyEmployee.setCreated(DateUtil.getSystemDate());
            companyEmployee.setStatus(EmployeeStatus.NORMAL.getValue());
            this.companyEmployeeService.add(companyEmployee);
            //添加员工权限信息
            List<RolePermission> rolePermissionList= this.rolePermissionService.selectRolePermissionList(role.getId());
            if(rolePermissionList!=null&&rolePermissionList.size()!=0){
                IIdGenerator idGenerator = new LwxfWorkerIdGenerator(1);
                for(RolePermission rolePermission:rolePermissionList){
                    //重新生成主键ID
                    rolePermission.setId(idGenerator.nextStringId());
                    //用公司员工主键ID替换权限ID
                    rolePermission.setRoleId(companyEmployee.getId());
                }
            }

        }
        return ResultFactory.generateSuccessResult();
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult restPassword(String userId) {
        UserExtra userExtra = new UserExtra();
        UserExtraUtil.saltingPassword(userExtra, new Md5Hash("111111").toString());
        MapContext toSave = new MapContext();
        toSave.put(WebConstant.KEY_USER_EXTRA_SALT, userExtra.getSalt());
        toSave.put(WebConstant.KEY_ENTITY_USER_ID, userId);
        toSave.put(WebConstant.KEY_USER_EXTRA_PASSWORD, userExtra.getPassword());
        toSave.put(WebConstant.KEY_USER_EXTRA_TOKEN, userExtra.getToken());
        this.userExtraService.updateByMapContext(toSave);
        return ResultFactory.generateRequestResult("rest success");
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult enableAndProhibitFactory(String uid) {
        User user = this.userService.findByUserId(uid);
        if (null == user) {
            return ResultFactory.generateResNotFoundResult();
        }
        MapContext mapContext=MapContext.newOne();
        if(user.getState()==UserState.DISABLED.getValue()){//禁用改成启用
            //TODO 判断工厂启用账号数量是否已经超过系统设置值
            String branchId = WebUtils.getCurrBranchId();
            Branch branch = branchService.findById(branchId);
            if (branch != null && branch.getFactoryLoginNum() != null) {
                String encryptNum = branch.getFactoryLoginNum();
                String num = AesEncryptUtil.AESDecode(encryptNum, branchId); // 加密的数量
                Integer enableNum = this.userService.countEnableFactoryUser(branchId); // 已启用工厂账户的数量
                if (num != null && enableNum != null && enableNum >= Integer.valueOf(num)) {
                    return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.BIZ_ENABLE_DEALER_USER_EXCEED_10096, AppBeanInjector.i18nUtil.getMessage("BIZ_ENABLE_DEALER_USER_EXCEED_10096"));
                }
            }

            mapContext.put("state", UserState.ENABLED.getValue());
            mapContext.put("id", uid);
            this.userService.updateByMapContext(mapContext);

            // 更新公司员工表的状态
            MapContext employeeMap =MapContext.newOne();
            CompanyEmployee employee = companyEmployeeService.selectByUserId(uid);
            if (employee != null) {
                employeeMap.put("id", employee.getId());
                employeeMap.put("status", 0);
                employeeMap.put("userId", uid);
                this.companyEmployeeService.updateByMapContext(employeeMap);
            }

            UserExtra userExtra = new UserExtra();
            UserExtraUtil.saltingPassword(userExtra,new Md5Hash("111111").toString());
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
        }else {
            // 启用改成禁用
            mapContext.put("state",UserState.DISABLED.getValue());
            mapContext.put("id",uid);
            mapContext.put("enableLoginPc", WebConstant.DISABLE); // 禁用pc登录
            mapContext.put("enableLoginWx", WebConstant.DISABLE); // 禁用微信小程序登录
            this.userService.updateByMapContext(mapContext);

            // 更新公司员工表的状态
//            MapContext employeeMap =MapContext.newOne();
//            CompanyEmployee employee = companyEmployeeService.selectByUserId(uid);
//            if (employee != null) {
//                employeeMap.put("id", employee.getId());
//                employeeMap.put("status", 1);
//                employeeMap.put("userId", uid);
//                this.companyEmployeeService.updateByMapContext(employeeMap);
//            }

        }
        User user1 = this.userService.findByUserId(uid);
        return ResultFactory.generateRequestResult(UserInfoObj.newOne(user1,null,null,null));
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult enableAndProhibitWxLogin(String uid) {
        User user = this.userService.findByUserId(uid);
        if (null == user) {
            return ResultFactory.generateResNotFoundResult();
        }
        MapContext mapContext = MapContext.newOne();
        String enableLoginWx = String.valueOf(WebConstant.DISABLE);
        if (user.getEnableLoginWx() != null && user.getEnableLoginWx().equals(WebConstant.DISABLE)) {
            //禁用改成启用
            //TODO 判断工厂微信小程序登录账号数量是否已经超过系统设置值
            String branchId = WebUtils.getCurrBranchId();
            Branch branch = branchService.findById(branchId);
            if (branch != null && branch.getFactoryLoginWxNum() != null) {
                String encryptNum = branch.getFactoryLoginWxNum();
                String num = AesEncryptUtil.AESDecode(encryptNum, branchId); // 加密的数量
                Integer enableNum = this.userService.countEnableFactoryUser(branchId); // 已启用工厂微信小程序登录账户的数量
                if (num != null && enableNum != null && enableNum >= Integer.valueOf(num)) {
                    return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.BIZ_ENABLE_DEALER_USER_EXCEED_10096, AppBeanInjector.i18nUtil.getMessage("BIZ_ENABLE_DEALER_USER_EXCEED_10096"));
                }
            }
            enableLoginWx = String.valueOf(WebConstant.ENABLE);

        }else {
            // 启用改成禁用,微信端登出用户
            MapContext map = MapContext.newOne();
            map.put("userId", uid);
            map.put("wxOpenId",null);
            this.userThirdInfoService.userlogout(mapContext);
        }
        mapContext.put("enableLoginWx",enableLoginWx);
        mapContext.put("id",uid);
        this.userService.updateByMapContext(mapContext);

        return ResultFactory.generateSuccessResult();
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult enableAndProhibitPcLogin(String uid) {
        User user = this.userService.findByUserId(uid);
        if (null == user) {
            return ResultFactory.generateResNotFoundResult();
        }
        MapContext mapContext = MapContext.newOne();
        String enableLoginPc = String.valueOf(WebConstant.DISABLE);
        if (user.getEnableLoginPc() != null && user.getEnableLoginPc().equals(WebConstant.DISABLE)) {
            //禁用改成启用
            //TODO 判断工厂PC登录账号数量是否已经超过系统设置值
            String branchId = WebUtils.getCurrBranchId();
            Branch branch = branchService.findById(branchId);
            if (branch != null && branch.getFactoryLoginPcNum() != null) {
                String encryptNum = branch.getFactoryLoginPcNum();
                String num = AesEncryptUtil.AESDecode(encryptNum, branchId); // 加密的数量
                Integer enableNum = this.userService.countEnablePcLoginFactoryUser(branchId); // 已启用工厂PC登录账户的数量
                if (num != null && enableNum != null && enableNum >= Integer.valueOf(num)) {
                    return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.BIZ_ENABLE_DEALER_USER_EXCEED_10096, AppBeanInjector.i18nUtil.getMessage("BIZ_ENABLE_DEALER_USER_EXCEED_10096"));
                }
            }
            enableLoginPc = String.valueOf(WebConstant.ENABLE);

        }
        mapContext.put("enableLoginPc", enableLoginPc);
        mapContext.put("id", uid);
        this.userService.updateByMapContext(mapContext);

        return ResultFactory.generateSuccessResult();
    }


}
