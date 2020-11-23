package com.lwxf.industry4.webapp.controller.wxapi.factory.payment;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentSimpleFunds;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.FileMimeTypeUtil;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentSimpleDto;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.wxapi.factory.payment.PaymentSimpleFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author：zhangxiaolin(3965488@qq.com)
 * @create：2019/06/14/ 13:24
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */

@Api(value="PaymentSimpleController",tags={"F端微信小程序接口:财务日常账管理"})
@RestController
@RequestMapping(value = "/wxapi/f/", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class PaymentSimpleController {

    @Resource(name = "wxPaymentSimpleFacade")
    private PaymentSimpleFacade paymentSimpleFacade;

    /**
     * 条件查询记账信息
     * @return
     */
    @ApiResponse(code = 200, message = "查询成功")
    @ApiOperation(value = "条件查询日常账信息", notes = "", response = PaymentSimpleDto.class)
    @GetMapping("paymentSimples")
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
                                         @RequestParam(required = false) Integer pageSize,
                                         @RequestParam(required = false) Integer pageNum,
                                         HttpServletRequest request){
        if (null == pageNum) {
            pageNum = 1;
        }
        if (null == pageSize) {
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        JsonMapper jsonMapper=new JsonMapper();
        String atoken=request.getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        if(mapInfo==null)
            return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
        if(uid==null){
            return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        }
        MapContext map = createMapContent(beginTime,endTime,funds,type,order,sort);
        if(order==null || order==""){
            map.put("order","created");
        }
        if(sort==null || sort ==""){
            map.put("sort","desc");
        }
        map.put("branchId",mapInfo.get("branchId").toString());
        RequestResult result=this.paymentSimpleFacade.findPaymentSimpleList(map,pageNum,pageSize);
        return jsonMapper.toJson(result);
    }
    /**
     * 添加日常账信息
     * @param paymentSimple 日常账实体
     * @return
     */
    @ApiOperation(value="日常记账信息添加",notes="日常记账信息添加")
    @PostMapping(value = "paymentSimples")
    public String addPaymentSimple(@RequestBody PaymentSimpleDto paymentSimple,
                                   HttpServletRequest request){
        JsonMapper jsonMapper=new JsonMapper();
        String atoken=request.getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
        if(uid==null){
            return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        }
//        paymentSimple.setFundsName(PaymentSimpleFunds.getByValue(paymentSimple.getFunds())==null?"":PaymentSimpleFunds.getByValue(paymentSimple.getFunds()).getName());
        paymentSimple.setCreator(uid);
        paymentSimple.setBranchId(mapInfo.get("branchId").toString());
        return jsonMapper.toJson(this.paymentSimpleFacade.addPaymentSimple(paymentSimple));
    }

    /**
     * 记账信息首页
     * @return
     */
    @ApiOperation(value = "日常账首页展示信息", notes = "")
    @GetMapping("paymentSimples/viewIndex")
    private String viewCompanyIndex(HttpServletRequest request) {
        JsonMapper jsonMapper=new JsonMapper();
        String atoken=request.getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid =mapInfo.get("userId").toString();
        if(uid==null){
            return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        }
        RequestResult result = this.paymentSimpleFacade.viewIndex();
        return jsonMapper.toJson(result);
    }

    /**
     * 日常账附件图片上传
     * @param multipartFileList 多个附件
     * @return
     */
    @ApiOperation(value="日常账附件图片上传",notes="返回值样例:{ \"code\": \"200\", \"data\": { \"ids\": [ \"4tuqvoqutzpc\", \"4tuqvovumolc\" ] } }")
    @PostMapping(value = "paymentSimples/uploadImages")
    public String uploadImages(@RequestBody List<MultipartFile> multipartFileList, HttpServletRequest request){
        JsonMapper jsonMapper=new JsonMapper();
        String atoken=request.getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
        if(uid==null){
            return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        }
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
                    return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.BIZ_FILE_SIZE_LIMIT_10031, LwxfStringUtils.format(AppBeanInjector.i18nUtil.getMessage("BIZ_FILE_SIZE_LIMIT_10031"), AppBeanInjector.configuration.getUploadBackgroundMaxsize())));
                }
                if (errorInfo.size() > 0) {
                    return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errorInfo));
                }
            }
        }
        return jsonMapper.toJson(this.paymentSimpleFacade.uploadImage(uid,multipartFileList));
    }

    /**
     * 根据ID查询记账详细
     * @return
     */
    @ApiResponse(code = 200, message = "查询成功")
    @ApiOperation(value="根据ID查询日常账详细信息",notes="根据ID查询日常账详细信息",response = PaymentSimpleDto.class)
    @GetMapping("paymentSimples/{paymentSimpleId}/paymentSimple")
    @ApiImplicitParam(name = "paymentSimpleId", value = "公司id", dataType = "string", paramType = "path")
    private String findCompanyInfo(@PathVariable String paymentSimpleId, HttpServletRequest request){
        JsonMapper jsonMapper=new JsonMapper();
        String atoken=request.getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
        if(uid==null){
            return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        }
        RequestResult result=this.paymentSimpleFacade.getPaymentSimpleById(paymentSimpleId);
        return jsonMapper.toJson(result);
    }


    /**
     * 删除记账信息
     * @param paymentSimpleId  记账信息id
     * @return
     */
    @ApiOperation(value="删除记账信息",notes="删除记账信息")
    @DeleteMapping(value = "paymentSimples/{paymentSimpleId}")
    public String deleteCompanyStatus(@PathVariable String paymentSimpleId,HttpServletRequest request) {
        JsonMapper jsonMapper=new JsonMapper();
        String atoken=request.getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
        if(uid==null){
            return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        }
        return jsonMapper.toJson(this.paymentSimpleFacade.deleteById(paymentSimpleId));
    }


    /**
     * 参数组成
     * @param beginTime  开始时间
     * @return
     */
    private MapContext createMapContent(String beginTime,String endTime,String fund,Integer type,String order,String sort) {
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
        return mapContext;
    }

}
