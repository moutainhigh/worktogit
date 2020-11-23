package com.lwxf.industry4.webapp.facade.admin.factory.finance.impl;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.bizservice.common.UploadFilesService;
import com.lwxf.industry4.webapp.bizservice.company.CompanyService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.bizservice.customorder.ProduceOrderService;
import com.lwxf.industry4.webapp.bizservice.dealer.DealerAccountLogService;
import com.lwxf.industry4.webapp.bizservice.dealer.DealerAccountService;
import com.lwxf.industry4.webapp.bizservice.financing.BankAccountService;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentFilesService;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentService;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationMoudule;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationType;
import com.lwxf.industry4.webapp.common.aop.syslog.SysOperationLog;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.component.UploadType;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.enums.company.DealerAccountLogType;
import com.lwxf.industry4.webapp.common.enums.company.DealerAccountType;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentFunds;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentTypeNew;
import com.lwxf.industry4.webapp.common.exceptions.DealerAccountBalanceLowException;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.uniquecode.UniquneCodeGenerator;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.config.LoggerBuilder;
import com.lwxf.industry4.webapp.domain.dto.company.CompanyPaymentInfoDto;
import com.lwxf.industry4.webapp.domain.dto.financing.dtoForApp.CompanyFinanceInfoDto;
import com.lwxf.industry4.webapp.domain.entity.company.Company;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAccount;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAccountLog;
import com.lwxf.industry4.webapp.domain.entity.financing.Payment;
import com.lwxf.industry4.webapp.domain.entity.financing.PaymentFiles;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.finance.CompanyFinanceFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("CompanyFinanceFacade")
public class CompanyFinanceFacadeImpl extends BaseFacadeImpl implements CompanyFinanceFacade {
    LoggerBuilder loggerBuilder =new LoggerBuilder();
    Logger logger = loggerBuilder.getLogger("paymentsimple");
    @Resource(name = "paymentService")
    private PaymentService paymentService;
    @Resource(name = "paymentFilesService")
    private PaymentFilesService paymentFilesService;
    @Resource(name = "dealerAccountService")
    private DealerAccountService dealerAccountService;
    @Resource(name = "uploadFilesService")
    private UploadFilesService uploadFilesService;
    @Resource(name = "companyService")
    private CompanyService companyService;
    @Resource(name = "customOrderService")
    private CustomOrderService customOrderService;
    @Resource(name = "produceOrderService")
    private ProduceOrderService produceOrderService;
    @Resource(name = "bankAccountService")
    private BankAccountService bankAccountService;
    @Resource(name = "dealerAccountLogService")
    private DealerAccountLogService dealerAccountLogService;

    @Override
    public RequestResult findCompanyFinanceList(MapContext mapContext, Integer pageNum, Integer pageSize) {
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        Pagination pagination = new Pagination();
        pagination.setPageNum(pageNum);
        pagination.setPageSize(pageSize);
        paginatedFilter.setPagination(pagination);
        Map<String,String> created = new HashMap<String, String>();
        if(mapContext.get("order")!=null && mapContext.get("sort")!=null) {
            created.put(mapContext.get("order").toString(), mapContext.get("sort").toString());
        }else{
            created.put(WebConstant.KEY_ENTITY_CREATED,"desc");
        }
        List sort = new ArrayList();
        sort.add(created);
        paginatedFilter.setSorts(sort);
        paginatedFilter.setFilters(mapContext);
        return ResultFactory.generateRequestResult(this.paymentService.findCompanyFinanceList(paginatedFilter));
    }

    @Override
    public RequestResult findAccountListInfo(MapContext mapContext, Integer pageNum, Integer pageSize) {
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        Pagination pagination = new Pagination();
        pagination.setPageNum(pageNum);
        pagination.setPageSize(pageSize);
        paginatedFilter.setPagination(pagination);
        paginatedFilter.setFilters(mapContext);
        return ResultFactory.generateRequestResult(this.companyService.findAccountListInfo(paginatedFilter));
    }


    @Override
    public RequestResult getCompanyFinanceById(String paymentId) {
        CompanyFinanceInfoDto dto = this.paymentService.getCompanyFinanceInfoByPaymentId(paymentId);
        //将图片放入实体
        List<String> pathList= new ArrayList<>();
        List<PaymentFiles> fileList=paymentFilesService.findByPaymentId(paymentId);
        if(fileList!=null && fileList.size()>0){
            pathList = new ArrayList<>();
            for(PaymentFiles file: fileList){
                pathList.add(file.getFullPath());
            }
        }
        dto.setFileList(pathList);
        return ResultFactory.generateRequestResult(this.paymentService.getCompanyFinanceInfoByPaymentId(paymentId));
    }

    //bug:代码需要优化，可以写专门接口来更新经销商账户，不用每次都遍历dealer_account
    @Override
    @Transactional(value = "transactionManager")
    @SysOperationLog(detail = "添加经销商财务信息",operationType = OperationType.INSERT,operationMoudule = OperationMoudule.COMPANY_FINANCE)
    public RequestResult addCompanyPayment(CompanyPaymentInfoDto companyPaymentInfoDto) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //查询登陆人名称
        String loginName="";
        if(WebUtils.getCurrUserId()!=null) {
             loginName= AppBeanInjector.userService.findByUserId(WebUtils.getCurrUserId()).getName();
        }else {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED,AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        Payment payment = new Payment();
        payment.setWay(companyPaymentInfoDto.getWay());
        payment.setFunds(companyPaymentInfoDto.getFunds());
        payment.setType(companyPaymentInfoDto.getType());
        payment.setCreated(DateUtil.getSystemDate());
       // payment.setCreator(WebUtils.getCurrUserId());
        payment.setCreator(WebUtils.getCurrUserId());
        payment.setPayee("4j1u3r1efshq");//bug 需要替换为常量
        payment.setStatus(1);
        payment.setNotes(companyPaymentInfoDto.getNotes());
        payment.setCompanyId(companyPaymentInfoDto.getCompanyId());
        payment.setHolder(companyPaymentInfoDto.getHolder());
        payment.setAmount(companyPaymentInfoDto.getAmount());
        payment.setBranchId(WebUtils.getCurrBranchId());
        payment.setBank(companyPaymentInfoDto.getBank());
        payment.setAudited(DateUtil.getSystemDate());
        payment.setAuditor(WebUtils.getCurrUserId());
        payment.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.PAYMENT_NO));
        payment.setFlag(0);
        paymentService.add(payment);
        List<DealerAccount> list =dealerAccountService.findByCompanIdAndNature(companyPaymentInfoDto.getCompanyId(),1);

        //更新账户信息
        //自由资金充值
        if(companyPaymentInfoDto.getType()==1) {
            DealerAccountLog dealerAccountLog=new DealerAccountLog();
            if (payment.getFunds() == PaymentFunds.FREE_FUNDS.getValue()) {
                //给自由资金充值
                for (DealerAccount d : list) {
                    if (d.getType() == DealerAccountType.FREE_ACCOUNT.getValue()) {
                        MapContext map = MapContext.newOne();
                        map.put("id", d.getId());
                        map.put("balance", d.getBalance().add(companyPaymentInfoDto.getAmount()));
                        dealerAccountService.updateByMapContext(map);
                        //记录账户变动日志
                        Company byId = this.companyService.findById(d.getCompanyId());
                        String dealerName=byId.getName();
                        dealerAccountLog.setAmount(companyPaymentInfoDto.getAmount());
                        dealerAccountLog.setDealerAccountId(d.getId());
                        dealerAccountLog.setContent(dateFormat.format(DateUtil.getSystemDate())+loginName+"给经销商【"+dealerName+"】自由资金账户充值"+companyPaymentInfoDto.getAmount()+",之前账户余额为："+d.getBalance()+",剩余金额为："+d.getBalance().add((companyPaymentInfoDto.getAmount())));
                        dealerAccountLog.setType(DealerAccountLogType.TO_CHANGE_INTO.getValue());
                        dealerAccountLog.setCreator(WebUtils.getCurrUserId());
                        dealerAccountLog.setCreated(DateUtil.getSystemDate());
                        dealerAccountLog.setDisburse(false);
                        //dealerAccountLog.setResourceId(customOrder.getId());
                        dealerAccountLog.setCompanyId(WebUtils.getCurrCompanyId());
                        this.dealerAccountLogService.add(dealerAccountLog);
                        logger.info(dateFormat.format(DateUtil.getSystemDate())+loginName+"给经销商【"+dealerName+"】自由资金账户充值"+companyPaymentInfoDto.getAmount()+",之前账户余额为："+d.getBalance()+",剩余金额为："+d.getBalance().add((companyPaymentInfoDto.getAmount())));
                    }
                }
            }
            //保证金
            if (payment.getFunds() == PaymentFunds.BOND.getValue()) {
                //给押金充值
                for (DealerAccount d : list) {
                    if (d.getType() == DealerAccountType.DEPOSIT_ACCOUNT.getValue()) {
                        MapContext map = MapContext.newOne();
                        map.put("id", d.getId());
                        map.put("balance", d.getBalance().add(companyPaymentInfoDto.getAmount()));
                        dealerAccountService.updateByMapContext(map);
                    }
                }
            }
            //设计金
            if (payment.getFunds() == PaymentFunds.DESSIGN_FEE.getValue()) {
                //给设计账户充值
                for (DealerAccount d : list) {
                    if (d.getType() == DealerAccountType.FREEZE_ACCOUNT.getValue()) {
                        MapContext map = MapContext.newOne();
                        map.put("id", d.getId());
                        map.put("balance", d.getBalance().add(companyPaymentInfoDto.getAmount()));
                        dealerAccountService.updateByMapContext(map);
                    }
                }
            }
        }else if(companyPaymentInfoDto.getType()==2){
            //设计金支付
            if (payment.getFunds() == PaymentFunds.DESIGN_FEE_PAY.getValue()) {
                //从自由账户扣款
                for (DealerAccount d : list) {
                    if (d.getType() == DealerAccountType.FREE_ACCOUNT.getValue()) {
                        if(d.getBalance().subtract(companyPaymentInfoDto.getAmount()).doubleValue()<0){
                            throw new DealerAccountBalanceLowException();
                        }
                        MapContext map = MapContext.newOne();
                        map.put("id", d.getId());
                        map.put("balance", d.getBalance().subtract(companyPaymentInfoDto.getAmount()));
                        dealerAccountService.updateByMapContext(map);
                    }
                }
            }
            //经销商账户退款
            if (payment.getFunds() == PaymentFunds.FREE_FUNDS_REFUND.getValue()) {
                //自由账户退款
                for (DealerAccount d : list) {
                    if (d.getType() == DealerAccountType.FREE_ACCOUNT.getValue()) {
                        if(d.getBalance().subtract(companyPaymentInfoDto.getAmount()).doubleValue()<0){
                            throw new DealerAccountBalanceLowException();
                        }
                        MapContext map = MapContext.newOne();
                        map.put("id", d.getId());
                        map.put("balance", d.getBalance().subtract(companyPaymentInfoDto.getAmount()));
                        dealerAccountService.updateByMapContext(map);
                    }
                }
            }
            //经销商设计费退款
            if (payment.getFunds() == PaymentFunds.DESIGN_FEE_REFUND.getValue()) {
                //自由账户退款
                for (DealerAccount d : list) {
                    if (d.getType() == DealerAccountType.FREE_ACCOUNT.getValue()) {
                        if(d.getBalance().subtract(companyPaymentInfoDto.getAmount()).doubleValue()<0){
                            throw new DealerAccountBalanceLowException();
                        }
                        MapContext map = MapContext.newOne();
                        map.put("id", d.getId());
                        map.put("balance", d.getBalance().subtract(companyPaymentInfoDto.getAmount()));
                        dealerAccountService.updateByMapContext(map);
                    }
                }
            }
            //保证金退款
            if (payment.getFunds() == PaymentFunds.BOND_REFUND.getValue()) {
                //自由账户退款
                for (DealerAccount d : list) {
                    if (d.getType() == DealerAccountType.DEPOSIT_ACCOUNT.getValue()) {
                        if(d.getBalance().subtract(companyPaymentInfoDto.getAmount()).doubleValue()<0){
                            throw new DealerAccountBalanceLowException();
                        }
                        MapContext map = MapContext.newOne();
                        map.put("id", d.getId());
                        map.put("balance", d.getBalance().subtract(companyPaymentInfoDto.getAmount()));
                        dealerAccountService.updateByMapContext(map);
                    }
                }
            }
        }
        //更新图片信息为正常
        if(companyPaymentInfoDto.getFileIds()!=null && !companyPaymentInfoDto.getFileIds().equals("")){
            String[] ids = companyPaymentInfoDto.getFileIds().split(",");
            for(String file_id: ids){
                MapContext map = MapContext.newOne();
                map.put("id",file_id);
                map.put("payment",payment.getId());
                map.put("status",1);
                this.paymentFilesService.updateByMapContext(map);
            }
        }
//        //更新银行费用
//        BankAccount b = bankAccountService.findById(companyPaymentInfoDto.getBank());
//        if(b==null){
//            throw new BankNotFoundException();
//        }else{
//            if(companyPaymentInfoDto.getType()==1) {
//                MapContext map = MapContext.newOne();
//                map.put("id",companyPaymentInfoDto.getBank());
//                map.put("amount",companyPaymentInfoDto.getAmount().add(b.getAmount()));
//                bankAccountService.updateByMapContext(map);
//            }else if (companyPaymentInfoDto.getType()==2){
//                BigDecimal res = b.getAmount().subtract(companyPaymentInfoDto.getAmount());
//                if(res.doubleValue()<0){
//                    throw new BankBalanceLowException();
//                }else{
//                    MapContext map = MapContext.newOne();
//                    map.put("id",companyPaymentInfoDto.getBank());
//                    map.put("amount",b.getAmount().subtract(companyPaymentInfoDto.getAmount()));
//                    bankAccountService.updateByMapContext(map);
//                }
//            }
//        }
        MapContext mapparam = MapContext.newOne();
        mapparam.put("id",payment.getId());
        return  ResultFactory.generateRequestResult(findCompanyFinanceList(mapparam,1,1).getData());
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult uploadImage(String userId, String companyId, List<MultipartFile> multipartFileList) {
        //添加图片到本地
        List<MapContext> listUrls = new ArrayList<>();
        if(multipartFileList!=null && multipartFileList.size()>0){
            for(MultipartFile multipartFile:multipartFileList){
                UploadInfo uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.FORMAL, multipartFile, UploadResourceType.FCOMPANY_FINANCE, "file");
                //添加图片到数据库
                PaymentFiles paymentFiles = new PaymentFiles();
                paymentFiles.setCreated(DateUtil.getSystemDate());
                paymentFiles.setFullPath(WebUtils.getDomainUrl() + uploadInfo.getRelativePath());
                paymentFiles.setMime(uploadInfo.getFileMimeType().getRealType());
                paymentFiles.setName(uploadInfo.getFileName());
                paymentFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
                paymentFiles.setPath(uploadInfo.getRelativePath());
                paymentFiles.setStatus(0);
                paymentFiles.setCreator(userId);
               // paymentFiles.setCreator("4v14mj3ampdy");
                this.paymentFilesService.add(paymentFiles);
                MapContext map = MapContext.newOne();
                map.put("imgFullPath",paymentFiles.getFullPath());
                map.put("imgRelPath",uploadInfo.getRelativePath());
                map.put("fileId",paymentFiles.getId());
                listUrls.add(map);
            }
        }
        return ResultFactory.generateRequestResult(listUrls);
    }

    @Override
    @Transactional(value = "transactionManager")
    @SysOperationLog(detail = "删除经销商财务信息",operationType = OperationType.DELETE,operationMoudule = OperationMoudule.COMPANY_FINANCE)
    public RequestResult deleteById(String paymentId) {
        //删除图片资源
        List<PaymentFiles> byPaymentId = this.paymentFilesService.findByPaymentId(paymentId);
        //删除数据库图片资源
        this.paymentFilesService.deleteByPaymentId(paymentId);
        for(PaymentFiles paymentFiles:byPaymentId){
            //清楚本地文件
            AppBeanInjector.baseFileUploadComponent.deleteFileByDir(paymentFiles.getFullPath());
        }
        return ResultFactory.generateRequestResult(paymentService.deleteById(paymentId));
    }

    @Override
    @Transactional(value = "transactionManager")
    @SysOperationLog(detail = "更新经销商财务信息",operationType = OperationType.UPDATE,operationMoudule = OperationMoudule.COMPANY_FINANCE)
    public RequestResult editCompanyPayment(MapContext mapContext) {
       String notes=mapContext.getTypedValue("notes",String.class);
        if (LwxfStringUtils.getStringLength(notes) > 50) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_LENGTH_TOO_LONG,AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
        }
        return ResultFactory.generateRequestResult(paymentService.updateByMapContext(mapContext));
    }

    @Override
    public RequestResult countPaymentForPageIndex() {
        return ResultFactory.generateRequestResult(paymentService.countPaymentForPageIndex(WebUtils.getCurrBranchId()));
    }

    @Override
    public RequestResult findCompanyCapitalCount(MapContext mapContext) {
        mapContext.put("income", PaymentTypeNew.INCOME.getValue());
        mapContext.put("outlay", PaymentTypeNew.OUTLAY.getValue());
        mapContext.put("chargeback",PaymentTypeNew.CHARGEBACK.getValue());
        MapContext map=this.paymentService.findCompanyCapitalByCidAndType(mapContext);
        return ResultFactory.generateRequestResult(map);
    }
}
