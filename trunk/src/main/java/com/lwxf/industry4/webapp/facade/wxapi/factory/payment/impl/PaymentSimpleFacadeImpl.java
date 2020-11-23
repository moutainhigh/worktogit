package com.lwxf.industry4.webapp.facade.wxapi.factory.payment.impl;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.financing.BankAccountService;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentSimpleFilesService;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentSimpleService;
import com.lwxf.industry4.webapp.bizservice.system.BasecodeService;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.component.UploadType;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentSimpleFunds;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.UniqueKeyUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentSimpleDto;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentSimpleListDtoForApp;
import com.lwxf.industry4.webapp.domain.entity.financing.PaymentSimpleFiles;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.wxapi.factory.payment.PaymentSimpleFacade;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("wxPaymentSimpleFacade")
public class PaymentSimpleFacadeImpl extends BaseFacadeImpl implements PaymentSimpleFacade {
    @Resource(name = "paymentSimpleService")
    private PaymentSimpleService paymentSimpleService;

    @Resource(name = "paymentSimpleFilesService")
    private PaymentSimpleFilesService paymentSimpleFilesService;

    @Resource(name = "basecodeService")
    private BasecodeService basecodeService;
    @Resource(name = "bankAccountService")
    private BankAccountService bankAccountService;


    @Override
    public RequestResult viewIndex() {
        //获取银行信息
        MapContext mapParam = MapContext.newOne();
        mapParam.put("type","bankAccount");
        List<Basecode> listBaseCodeBanks = basecodeService.selectByFilter(mapParam);
        List<MapContext> listBanks = new ArrayList<>();
        for(Basecode bs1 : listBaseCodeBanks){
            MapContext mapBank = MapContext.newOne();
            mapBank.put("name",bs1.getName());
            mapBank.put("id",bs1.getCode());
            listBanks.add(mapBank);
        }
        //返回结果Map
        MapContext resMap= MapContext.newOne();
        //获取统计
        Map<String,String> reportMap = paymentSimpleService.countPaymentSimpleForApp();
        resMap.put("report",reportMap);
        //今日流水
        List<PaymentSimpleListDtoForApp> paymentSimplelist = paymentSimpleService.selectCurrentDayListByFilterForApp();
        resMap.put("dataList",paymentSimplelist);
        List<MapContext> list1 = new ArrayList<>(); //一级类目
        MapContext mapIn = MapContext.newOne();
        mapIn.put("name", "收入");
        mapIn.put("hasChild","1");
        mapIn.put("id","1");
        //收入的类型科目
        List<MapContext> list2In = new ArrayList<>();  //收入二级类目
        for (PaymentSimpleFunds s : PaymentSimpleFunds.values()) {
            if(s.getValue().intValue()>10 &&s.getValue().intValue()<20) {
                MapContext mapCompanyStatus = MapContext.newOne();
                mapCompanyStatus.put("name", s.getName());
                mapCompanyStatus.put("id", s.getValue());

                if(s.getValue().intValue()==11){
                    //手工录入货款子项目
                    MapContext mapways = MapContext.newOne();
                    //获取支付方式
                    mapways.put("type","payment_simple_ways");
                    List<Basecode> list = basecodeService.selectByFilter(mapways);
                    List<MapContext> listWays = new ArrayList<>();
                    for(Basecode bs : list){
                        MapContext map = MapContext.newOne();
                        map.put("name",bs.getValue());
                        map.put("id",bs.getCode().toString());
                        map.put("hasChild","0");
                        listWays.add(map);
                    }
                    mapCompanyStatus.put("items",listWays);
                    mapCompanyStatus.put("hasChild","1");
                }else{
                    mapCompanyStatus.put("hasChild","0");
                }
                list2In.add(mapCompanyStatus);
            }
        }

        mapIn.put("items",list2In);
        list1.add(mapIn);
        MapContext mapOut = MapContext.newOne();
        mapOut.put("name", "支出");
        mapOut.put("hasChild","1");
        mapOut.put("id","2");
        //支出的类型科目
        List<MapContext> list2Out = new ArrayList<>();  //收入二级类目
        for (PaymentSimpleFunds s : PaymentSimpleFunds.values()) {
            if(s.getValue().intValue()>20 &&s.getValue().intValue()<30) {
                MapContext maplevel2 = MapContext.newOne();
                maplevel2.put("name", s.getName());
                maplevel2.put("id", s.getValue());
                //消耗类支出子项目
                if(s.getValue().intValue()==25){
                    //添加3级类目
                    //收入的类型科目
                    List<MapContext> list3Out = new ArrayList<>();  //收入二级类目
                    for (PaymentSimpleFunds s2 : PaymentSimpleFunds.values()) {
                        if(s2.getValue().intValue()>250 &&s2.getValue().intValue()<260) {
                            MapContext map3 = MapContext.newOne();
                            map3.put("name", s2.getName());
                            map3.put("id", s2.getValue());
                            map3.put("hasChild","0");
                            //添加3级类目
                            //收入的类型科目
                            if(s2.getValue().intValue()==251) {
                                List<MapContext> list4Out = new ArrayList<>();
                                for (PaymentSimpleFunds s3 : PaymentSimpleFunds.values()) {
                                    if (s3.getValue().intValue() > 25100 && s3.getValue().intValue() < 25200) {
                                        MapContext map4 = MapContext.newOne();
                                        map4.put("name", s3.getName());
                                        map4.put("id", s3.getValue());
                                        map4.put("hasChild", "0");
                                        list4Out.add(map4);
                                    }
                                }
                                map3.put("hasChild","1");
                                map3.put("items",list4Out);
                            }
                            if(s2.getValue().intValue()==252) {
                                List<MapContext> list4Out = new ArrayList<>();
                                for (PaymentSimpleFunds s3 : PaymentSimpleFunds.values()) {
                                    if (s3.getValue().intValue() > 25200 && s3.getValue().intValue() < 25300) {
                                        MapContext map4 = MapContext.newOne();
                                        map4.put("name", s3.getName());
                                        map4.put("id", s3.getValue());
                                        map4.put("hasChild", "0");
                                        list4Out.add(map4);
                                    }
                                }
                                map3.put("hasChild","1");
                                map3.put("items",list4Out);
                            }
                            if(s2.getValue().intValue()==253) {
                                List<MapContext> list4Out = new ArrayList<>();
                                for (PaymentSimpleFunds s3 : PaymentSimpleFunds.values()) {
                                    if (s3.getValue().intValue() > 25300 && s3.getValue().intValue() < 25400) {
                                        MapContext map4 = MapContext.newOne();
                                        map4.put("name", s3.getName());
                                        map4.put("id", s3.getValue());
                                        map4.put("hasChild", "0");
                                        list4Out.add(map4);
                                    }
                                }
                                map3.put("hasChild","1");
                                map3.put("items",list4Out);
                            }
                            list3Out.add(map3);
                        }
                    }
                    maplevel2.put("hasChild","1");
                    maplevel2.put("items",list3Out);
                }else{
                    maplevel2.put("hasChild","0");
                }
                list2Out.add(maplevel2);
            }
        }
        list1.add(mapOut);
        mapOut.put("items",list2Out);
        resMap.put("funds",list1);
        resMap.put("bankList",bankAccountService.selectByFilter(MapContext.newOne()));
        return ResultFactory.generateRequestResult(resMap);
    }

    @Override
    public RequestResult findPaymentSimpleList(MapContext mapContext, Integer pageNum, Integer pageSize) {
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        Pagination pagination = new Pagination();
        pagination.setPageNum(pageNum);
        pagination.setPageSize(pageSize);
        paginatedFilter.setPagination(pagination);
        mapContext.put(WebConstant.KEY_ENTITY_STATUS, 0);
        paginatedFilter.setFilters(mapContext);
        return ResultFactory.generateRequestResult(this.paymentSimpleService.selectDtoByFilter(paginatedFilter));
    }

    @Override
    public RequestResult getPaymentSimpleById(String paymentId) {
        PaymentSimpleDto paymentSimple=this.paymentSimpleService.findDtoById(paymentId);
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
    public RequestResult deleteById(String paymentSimpleId) {
        return ResultFactory.generateRequestResult(this.paymentSimpleService.deleteById(paymentSimpleId));
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult addPaymentSimple(PaymentSimpleDto paymentSimple) {
        String id = UniqueKeyUtil.getStringId();
        paymentSimple.setCreated(DateUtil.getSystemDate());
        paymentSimple.setId(id);
        if(paymentSimple.getWays()==null){
            paymentSimple.setWays(4);
        }
        if(this.paymentSimpleService.add(paymentSimple)==1){
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
            return ResultFactory.generateRequestResult(paymentSimple);
        }else{
            return ResultFactory.generateErrorResult(ErrorCodes.SYS_EXECUTE_FAIL_00001,"数据保存失败");
        }
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult uploadImage(String userId,List<MultipartFile> multipartFileList) {
        //添加图片到本地
        List<String> listUrls = new ArrayList<>();
        List<String> listIds = new ArrayList<>();
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
                //paymentSimpleFiles.setCreator(userId);
                paymentSimpleFiles.setCreator(userId);
                this.paymentSimpleFilesService.add(paymentSimpleFiles);
                listIds.add(paymentSimpleFiles.getId());
                listUrls.add(paymentSimpleFiles.getFullPath());
            }
        }
        MapContext mapContext=MapContext.newOne();
        mapContext.put("listUrls",listUrls);
        mapContext.put("listIds",listIds);
        return ResultFactory.generateRequestResult(mapContext);
    }
}
