package com.lwxf.industry4.webapp.controller.admin.factory.finance;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentSimpleFunds;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.FileMimeTypeUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentSimpleDto;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.finance.PaymentSimpleFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value="PaymentSimpleController",tags={"F端后台管理接口:财务日常账管理"})
@RestController("PaymentSimpleController")
@RequestMapping(value = "/api/f/", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class PaymentSimpleController {

    @Resource(name = "paymentSimpleFacade")
    private PaymentSimpleFacade paymentSimpleFacade;

    /**
     * 条件查询记账信息
     * @return
     */
    @ApiResponse(code = 200, message = "查询成功")
    @ApiOperation(value = "条件查询日常账信息", notes = "", response = PaymentSimpleDto.class)
    @GetMapping("/payment_simples")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginTime", value = "起始时间 eg:2018-01-01", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "终止时间 eg:2018-01-01", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "funds", value = "科目", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "类型,1:收入;2:支出", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "order", value = "排序列，按日期排序则传递：created,按金额排序传递：amount", dataType = "string", paramType = "query" ),
            @ApiImplicitParam(name = "sort", value = "排序方式，desc：倒序,asc：正序", dataType = "string", paramType = "query")
    })
    private String findPaymentSimpleList(@RequestParam(required = false) String beginTime,
                                         @RequestParam(required = false) String endTime,
                                         @RequestParam(required = false) String funds,
                                         @RequestParam(required = false) Integer type,
                                         @RequestParam(required = false) String order,
                                         @RequestParam(required = false) String sort,
                                         @RequestParam(required = false) String bank,
                                         @RequestParam(required = false) Integer pageSize,
                                         @RequestParam(required = false) Integer pageNum){
        if (null == pageNum) {
            pageNum = 1;
        }
        if (null == pageSize) {
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        JsonMapper jsonMapper=new JsonMapper();
        MapContext map = createMapContent(beginTime,endTime,funds,type,bank,order,sort);
        if(order==null || order==""){
            map.put("order","created");
        }
        if(sort==null || sort ==""){
            map.put("sort","desc");
        }
        RequestResult result=this.paymentSimpleFacade.findPaymentSimpleList(map,pageNum,pageSize);
        return jsonMapper.toJson(result);
    }
    /**
     * 添加简单支付信息
     * @param paymentSimple 简单支付实体
     * @return
     */
    @ApiOperation(value="日常记账信息添加",notes="日常记账信息添加")
    @PostMapping(value = "/payment_simples")
    public String addPaymentSimple(@RequestBody PaymentSimpleDto paymentSimple){
        JsonMapper jsonMapper=new JsonMapper();
//        if(paymentSimple.getFunds()!=null) {
//            paymentSimple.setFundsName(PaymentSimpleFunds.getByValue(paymentSimple.getFunds()).getName());
//        }
        return jsonMapper.toJson(this.paymentSimpleFacade.addPaymentSimple(paymentSimple));
    }

    /**
     * 日常账附件图片上传
     * @param multipartFileList 多个附件
     * @return
     */
    @ApiOperation(value="日常账附件图片上传",notes="返回值样例:{ \"code\": \"200\", \"data\": { \"ids\": [ \"4tuqvoqutzpc\", \"4tuqvovumolc\" ] } }")
    @PostMapping(value = "payment_simples/uploadImages")
    public RequestResult uploadImages(@RequestBody List<MultipartFile> multipartFileList){
        Map<String, Object> errorInfo = new HashMap<>();
        if(multipartFileList!=null && multipartFileList.size()>0) {
            for (MultipartFile multipartFile : multipartFileList) {
                if (multipartFile == null) {
                    errorInfo.put("multipartFile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
                }
                if (!FileMimeTypeUtil.isLegalImageType(multipartFile)) {
                    errorInfo.put("multipartFile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_ILLEGAL_ARGUMENT"));
                }
                if (multipartFile.getSize() > 1024L * 1024L * AppBeanInjector.configuration.getUploadBackgroundMaxsize()) {
                    return ResultFactory.generateErrorResult(ErrorCodes.BIZ_FILE_SIZE_LIMIT_10031, LwxfStringUtils.format(AppBeanInjector.i18nUtil.getMessage("BIZ_FILE_SIZE_LIMIT_10031"), AppBeanInjector.configuration.getUploadBackgroundMaxsize()));
                }
                if (errorInfo.size() > 0) {
                    return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errorInfo);
                }
            }
        }
        return this.paymentSimpleFacade.uploadImage(WebUtils.getCurrUserId(),multipartFileList);
    }

    /**
     * 根据ID查询记账详细
     * @return
     */
    @ApiResponse(code = 200, message = "查询成功")
    @ApiOperation(value="根据ID查询日常账详细信息",notes="根据ID查询日常账详细信息",response = PaymentSimpleDto.class)
    @GetMapping("payment_simples/{paymentSimpleId}")
    private String findCompanyInfo(@PathVariable String paymentSimpleId){
        JsonMapper jsonMapper=new JsonMapper();
        RequestResult result=this.paymentSimpleFacade.getPaymentSimpleById(paymentSimpleId);
        return jsonMapper.toJson(result);
    }

    /**
     * 条件查询经销商账户金额
     * @return
     */
    @ApiResponse(code = 200, message = "查询成功")
    @ApiOperation(value = "首页统计信息", notes = "")
    @GetMapping("payment_simples/countPayments")
    private String countPayments(){
        JsonMapper jsonMapper=new JsonMapper();
        RequestResult result=this.paymentSimpleFacade.countPaymentForPageIndex();
        return jsonMapper.toJson(result);
    }

    /**
     * 获得科目信息
     * @return
     */
    @ApiResponse(code = 200, message = "查询成功")
    @ApiOperation(value="获得科目信息",notes="获得科目信息",response = PaymentSimpleDto.class)
    @GetMapping("payment_simples/funds")
    private String getPaymentSimpleFunds(){
        JsonMapper jsonMapper=new JsonMapper();
        //类型科目
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
                mapCompanyStatus.put("hasChild","0");
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
        mapOut.put("items",list2Out);
        list1.add(mapOut);
        MapContext mapBankTranse = MapContext.newOne();
        mapBankTranse.put("name", "银行调拨");
        mapBankTranse.put("hasChild","0");
        mapBankTranse.put("id","3");
        list1.add(mapBankTranse);
        return jsonMapper.toJson(ResultFactory.generateRequestResult(list1));
    }

    /**
     * 更新日常记账信息
     * @param paymentSimpleId  记账信息id
     * @return
     */
    @ApiOperation(value="更新日常记账信息",notes="更新日常记账信息")
    @PutMapping(value = "payment_simples/{paymentSimpleId}")
    public String putPaymentSimple(@PathVariable String paymentSimpleId,
                                   @RequestBody MapContext map) {
        JsonMapper jsonMapper=new JsonMapper();
        return jsonMapper.toJson(this.paymentSimpleFacade.updatePaymentSimple(paymentSimpleId,map));
    }


    /**
     * 删除记账信息
     * @param paymentSimpleId  记账信息id
     * @return
     */
    @ApiOperation(value="删除记账信息",notes="删除记账信息")
    @DeleteMapping(value = "payment_simples/{paymentSimpleId}")
    public String delete(@PathVariable String paymentSimpleId) {
        JsonMapper jsonMapper=new JsonMapper();
        return jsonMapper.toJson(this.paymentSimpleFacade.deleteById(paymentSimpleId));
    }

    @ApiOperation(value="获取财务人员信息列表",notes="获取财务人员信息列表")
    @GetMapping(value="payment_simples/users")
    public String getUserForPaymentSimple(){
        JsonMapper jsonMapper=new JsonMapper();
        return jsonMapper.toJson(this.paymentSimpleFacade.getUserForPaymentSimple());
    }
    @ApiOperation(value="日常账统计",notes="日常账统计")
    @GetMapping(value="payment_simples/count")
    public String getPaymentSimpleCount(@RequestParam String type){
        JsonMapper jsonMapper=new JsonMapper();
        return jsonMapper.toJson(this.paymentSimpleFacade.getPaymentSimpleCount(type));
    }


    /**
     * 参数组成
     * @param beginTime  开始时间
     * @return
     */
    private MapContext createMapContent(String beginTime,String endTime,String fund,Integer type,String bank,String order,String sort) {
        MapContext mapContext = MapContext.newOne();
        if (beginTime!=null && !beginTime.equals("")) {
            mapContext.put("beginTime", beginTime);
        }
        if (endTime!=null && !endTime.equals("")) {
            mapContext.put("endTime", endTime);
        }
        if (fund!=null && !fund.equals("")) {
            mapContext.put("funds", fund);
        }
        if (order!=null && !order.equals("")) {
            mapContext.put("order", order);
        }
        if (sort!=null && !sort.equals("")) {
            mapContext.put("sort", sort);
        }
        if (type!=null && !type.equals("")) {
            mapContext.put("type", type);
        }
        if (bank!=null && !bank.equals("")) {
            mapContext.put("bank", bank);
        }
        return mapContext;
    }

}
