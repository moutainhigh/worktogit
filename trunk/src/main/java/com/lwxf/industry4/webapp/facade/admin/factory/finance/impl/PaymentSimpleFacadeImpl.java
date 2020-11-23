package com.lwxf.industry4.webapp.facade.admin.factory.finance.impl;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.dept.DeptService;
import com.lwxf.industry4.webapp.bizservice.financing.BankAccountService;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentSimpleFilesService;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentSimpleFundsService;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentSimpleService;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationMoudule;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationType;
import com.lwxf.industry4.webapp.common.aop.syslog.SysOperationLog;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.component.UploadType;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentTypeNew;
import com.lwxf.industry4.webapp.common.exceptions.BankNotFoundException;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.UniqueKeyUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentSimpleDto;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentSimpleFundsDto;
import com.lwxf.industry4.webapp.domain.entity.financing.BankAccount;
import com.lwxf.industry4.webapp.domain.entity.financing.PaymentSimple;
import com.lwxf.industry4.webapp.domain.entity.financing.PaymentSimpleFiles;
import com.lwxf.industry4.webapp.domain.entity.financing.PaymentSimpleFunds;
import com.lwxf.industry4.webapp.domain.entity.org.Dept;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.finance.PaymentSimpleFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("paymentSimpleFacade")
public class PaymentSimpleFacadeImpl extends BaseFacadeImpl implements PaymentSimpleFacade {
    @Resource(name = "paymentSimpleService")
    private PaymentSimpleService paymentSimpleService;

    @Resource(name = "paymentSimpleFilesService")
    private PaymentSimpleFilesService paymentSimpleFilesService;

    @Resource(name = "bankAccountService")
    private BankAccountService bankAccountService;

    @Resource(name = "paymentSimpleFundsService")
    private PaymentSimpleFundsService paymentSimpleFundsService;
    @Resource(name = "deptService")
    private DeptService deptService;

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult findPaymentSimpleList(MapContext mapContext, Integer pageNum, Integer pageSize) {
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        Pagination pagination = new Pagination();
        pagination.setPageNum(pageNum);
        pagination.setPageSize(pageSize);
        paginatedFilter.setPagination(pagination);
        mapContext.put(WebConstant.KEY_ENTITY_STATUS, 0);
        mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,WebUtils.getCurrBranchId());
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
        return ResultFactory.generateRequestResult(this.paymentSimpleService.selectDtoByFilter(paginatedFilter));
    }

    @Override
    public RequestResult exportExcel() {
        return null;
    }


    @Override
    public RequestResult getPaymentSimpleById(String paymentId) {
        PaymentSimpleDto paymentSimple=this.paymentSimpleService.findDtoById(paymentId);
        List<PaymentSimpleFundsDto> listByPaymentId=this.paymentSimpleFundsService.findListByPaymentId(paymentId);
        paymentSimple.setPaymentSimpleFundsList(listByPaymentId);
        //将图片放入实体
        List<String> pathList;
        List<PaymentSimpleFiles> fileList=paymentSimpleFilesService.findByPaymentSimpleId(paymentId);
        if(fileList!=null && fileList.size()>0){
            pathList = new ArrayList<>();
            for(PaymentSimpleFiles file: fileList){
                pathList.add(file.getFullPath());
            }
            paymentSimple.setImgPathList(pathList);
        }

        return ResultFactory.generateRequestResult(paymentSimple);
    }

    @Override
    @Transactional(value = "transactionManager")
    @SysOperationLog(detail = "更新日常账信息",operationType = OperationType.UPDATE,operationMoudule = OperationMoudule.PAYMENT_SIMPLE)
    public RequestResult updatePaymentSimple(String paymentSimpleId, MapContext map) {
        map.put("id",paymentSimpleId);
        return ResultFactory.generateRequestResult(this.paymentSimpleService.updateByMapContext(map));
    }

    @Override
    @Transactional(value = "transactionManager")
    @SysOperationLog(detail = "删除日常账信息",operationType = OperationType.DELETE,operationMoudule = OperationMoudule.PAYMENT_SIMPLE)
    public RequestResult deleteById(String paymentSimpleId) {
        PaymentSimple payment = paymentSimpleService.findById(paymentSimpleId);
        this.paymentSimpleService.deleteById(paymentSimpleId);
        // 删除科目列表
        this.paymentSimpleFundsService.deleteByPid(paymentSimpleId);

        //更新银行费用
        if(payment.getType()!=3) {
            BankAccount b = bankAccountService.findById(payment.getBank());
            if (b == null) {
                throw new BankNotFoundException();
            } else {
                if (payment.getType() == 1) {
                    MapContext map = MapContext.newOne();
                    map.put("id", payment.getBank());
                    map.put("amount", b.getAmount().subtract(payment.getAmount()));
                    bankAccountService.updateByMapContext(map);
                } else if (payment.getType() == 2) {
                    MapContext map = MapContext.newOne();
                    map.put("id", payment.getBank());
                    map.put("amount", b.getAmount().add(payment.getAmount()));
                    bankAccountService.updateByMapContext(map);
                }
            }
        }else {
            BankAccount incomeBank=bankAccountService.findById(payment.getIncomeBank());
            if(incomeBank==null){
                throw new BankNotFoundException();
            }
            BankAccount outcomeBank=bankAccountService.findById(payment.getOutcomeBank());
            if(outcomeBank==null){
                throw new BankNotFoundException();
            }
            //收入行减去相应金额
            MapContext map=MapContext.newOne();
            map.put("id",incomeBank.getId());
            map.put("amount",incomeBank.getAmount().subtract(payment.getAmount()));
            bankAccountService.updateByMapContext(map);
            //支出行加上相应金额
            MapContext map1=MapContext.newOne();
            map1.put("id",outcomeBank.getId());
            map1.put("amount",outcomeBank.getAmount().add(payment.getAmount()));
            bankAccountService.updateByMapContext(map1);
        }
        return ResultFactory.generateSuccessResult();
    }

    @Override
    @Transactional(value = "transactionManager")
    @SysOperationLog(detail = "新增日常账信息",operationType = OperationType.INSERT,operationMoudule = OperationMoudule.PAYMENT_SIMPLE)
    public RequestResult addPaymentSimple(PaymentSimpleDto paymentSimple) {
        String id = UniqueKeyUtil.getStringId();
        paymentSimple.setCreated(DateUtil.getSystemDate());
        // paymentSimple.setId(id);
        paymentSimple.setBranchId(WebUtils.getCurrBranchId());
        paymentSimple.setCreator(WebUtils.getCurrUserId());
        paymentSimple.setBank(paymentSimple.getBank());
        if(paymentSimple.getWays()==null){
            paymentSimple.setWays(0);
        }
        // 计算科目总金额
        List<PaymentSimpleFundsDto> fundsList = paymentSimple.getPaymentSimpleFundsList();
        if (fundsList != null && fundsList.size() > 0) {
            BigDecimal amount = new BigDecimal("0.00");
            for (PaymentSimpleFunds funds : fundsList) {
                if (funds != null) {
                    // 科目金额累加
                    amount = amount.add(funds.getAmount());
                }
            }
            // 修改账目金额
            paymentSimple.setAmount(amount);
        }

        if(paymentSimple.getType()==3){
            //银行调拨
            String outcomeBankId = paymentSimple.getOutcomeBank();
            String incomeBankId = paymentSimple.getIncomeBank();
            //转出
            BankAccount outcomeB = bankAccountService.findById(outcomeBankId);
            if(outcomeB.getAmount().doubleValue()<paymentSimple.getAmount().doubleValue()){
                return ResultFactory.generateErrorResult(ErrorCodes.BIZ_BANK_NOT_FOUND_10091,AppBeanInjector.i18nUtil.getMessage("BIZ_BANK_NOT_FOUND_10091"));
            }
            MapContext map = MapContext.newOne();
            map.put("id",outcomeB.getId());
            map.put("amount",outcomeB.getAmount().subtract(paymentSimple.getAmount()));
            bankAccountService.updateByMapContext(map);
            //转入
            BankAccount incomeB = bankAccountService.findById(incomeBankId);
            MapContext mapIncome = MapContext.newOne();
            mapIncome.put("id",incomeB.getId());
            mapIncome.put("amount",paymentSimple.getAmount().add(incomeB.getAmount()));
            bankAccountService.updateByMapContext(mapIncome);
        }else{
            //更新银行费用
            BankAccount b = bankAccountService.findById(paymentSimple.getBank());
            if(b==null){
                return ResultFactory.generateErrorResult(ErrorCodes.BIZ_BANK_NOT_FOUND_10090,AppBeanInjector.i18nUtil.getMessage("BIZ_BANK_NOT_FOUND_10090"));
            }else{
                if(paymentSimple.getType()==1) {
                    MapContext map = MapContext.newOne();
                    map.put("id",paymentSimple.getBank());
                    map.put("amount",paymentSimple.getAmount().add(b.getAmount()));
                    bankAccountService.updateByMapContext(map);
                }else if (paymentSimple.getType()==2){
                    BigDecimal res = b.getAmount().subtract(paymentSimple.getAmount());
                    if(res.doubleValue()<0){
                        return ResultFactory.generateErrorResult(ErrorCodes.BIZ_BANK_NOT_FOUND_10091,AppBeanInjector.i18nUtil.getMessage("BIZ_BANK_NOT_FOUND_10091"));
                    }else{
                        MapContext map = MapContext.newOne();
                        map.put("id",paymentSimple.getBank());
                        map.put("amount",b.getAmount().subtract(paymentSimple.getAmount()));
                        bankAccountService.updateByMapContext(map);
                    }

                }
            }
        }


        if(this.paymentSimpleService.add(paymentSimple)==1){

            // 将科目数据添加到PaymentSimpleFunds科目表中
            if (fundsList != null && fundsList.size() > 0) {
                for (PaymentSimpleFunds funds : fundsList) {
                    if (funds != null) {
                        funds.setId(UniqueKeyUtil.getStringId());
                        funds.setPaymentSimpleId(paymentSimple.getId());
                        funds.setCreated(DateUtil.getSystemDate());
                        funds.setCreator(paymentSimple.getCreator());
                        funds.setOperator(paymentSimple.getOperator());
                        // 循环单条添加
                        // this.paymentSimpleFundsService.add(funds);
                    }
                }
                // 批量添加
                this.paymentSimpleFundsService.addBatch(fundsList);
            }

            //更新图片信息为正常
            if(paymentSimple.getFileIds()!=null && !paymentSimple.getFileIds().equals("")){
                String[] ids = paymentSimple.getFileIds().split(",");
                for(String file_id: ids){
                    MapContext map = MapContext.newOne();
                    map.put("id",file_id);
                    map.put("paymentSimple",paymentSimple.getId());
                    map.put("status",1);
                    this.paymentSimpleFilesService.updateByMapContext(map);
                }
            }
            //删除多余图片垃圾信息(待实现)
            return ResultFactory.generateRequestResult(getPaymentSimpleById(paymentSimple.getId()).getData());
        }else{
            return ResultFactory.generateErrorResult(ErrorCodes.SYS_EXECUTE_FAIL_00001,"数据保存失败");
        }
    }


    @Override
    public RequestResult getUserForPaymentSimple() {
        return ResultFactory.generateRequestResult(this.paymentSimpleService.getUserForPaymentSimple("4k8cbm6hbswh"));
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult uploadImage(String userId,List<MultipartFile> multipartFileList) {
        //添加图片到本地
        List<MapContext> listUrls = new ArrayList<>();
        if(multipartFileList!=null && multipartFileList.size()>0){
            for(MultipartFile multipartFile:multipartFileList){
                UploadInfo uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.FORMAL, multipartFile, UploadResourceType.PAYMENT_SIMPLE, "cover");
                //添加图片到数据库
                PaymentSimpleFiles paymentSimpleFiles = new PaymentSimpleFiles();
                paymentSimpleFiles.setCreated(DateUtil.getSystemDate());
                paymentSimpleFiles.setFullPath(WebUtils.getDomainUrl() + uploadInfo.getRelativePath());
                paymentSimpleFiles.setMime(uploadInfo.getFileMimeType().getRealType());
                paymentSimpleFiles.setName(uploadInfo.getFileName());
                paymentSimpleFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
                paymentSimpleFiles.setPath(uploadInfo.getRelativePath());
                paymentSimpleFiles.setStatus(0);
                paymentSimpleFiles.setCreator(userId);
              //  paymentSimpleFiles.setCreator("4v14mj3ampdy");
                this.paymentSimpleFilesService.add(paymentSimpleFiles);
                MapContext map = MapContext.newOne();
                map.put("imgFullPath",paymentSimpleFiles.getFullPath());
                map.put("imgRelPath",uploadInfo.getRelativePath());
                map.put("fileId",paymentSimpleFiles.getId());
                listUrls.add(map);
            }
        }
        return ResultFactory.generateRequestResult(listUrls);
    }

    @Override
    public RequestResult getPaymentSimpleCount(String type) {
        if(type==null||type.equals("")){//默认收入页面
            type=PaymentTypeNew.INCOME.getValue().toString();
        }
        String branchId=WebUtils.getCurrBranchId();
        MapContext mapContext=MapContext.newOne();
        mapContext.put("branchId",branchId);
        mapContext.put("type",type);
        List<PaymentSimpleFunds> paymentSimples=this.paymentSimpleFundsService.findFundsList(mapContext);//查询所有有资金往来的科目
        List<Dept> all = this.deptService.findAll(WebUtils.getCurrCompanyId());//查询所有部门
        List result=new ArrayList();
        for(PaymentSimpleFunds simple:paymentSimples){
            MapContext map=MapContext.newOne();
            String fundsName = simple.getFundsName();
            List list=new ArrayList();
            for(Dept dept:all) {
                MapContext map1=MapContext.newOne();
                String deptId=dept.getId();
                MapContext deptAmount=this.paymentSimpleFundsService.findCountByFundsNameAndDeptId(fundsName,deptId,type);
                map1.put("deptId",deptId);
                map1.put("deptName",dept.getName());
                map1.put("amount",deptAmount.getTypedValue("sumAmount",String.class));
                list.add(map1);
            }
            map.put("fundsName",fundsName);
            map.put("deptAmount",list);
            result.add(map);
        }
        return ResultFactory.generateRequestResult(result);
    }

    @Override
    public RequestResult countPaymentForPageIndex() {
        MapContext mapContext = paymentSimpleService.countPaymentForPageIndex(WebUtils.getCurrBranchId());
        String today_income_amount=String.format("%.2f",mapContext.getTypedValue("today_income_amount",Double.class));
        String today_outcome_amount=String.format("%.2f",mapContext.getTypedValue("today_outcome_amount",Double.class));
        mapContext.put("today_income_amount",today_income_amount);
        mapContext.put("today_outcome_amount",today_outcome_amount);
        return ResultFactory.generateRequestResult(mapContext);
    }
}
