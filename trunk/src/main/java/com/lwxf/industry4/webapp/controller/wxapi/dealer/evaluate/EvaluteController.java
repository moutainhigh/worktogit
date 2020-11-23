package com.lwxf.industry4.webapp.controller.wxapi.dealer.evaluate;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.bizservice.customer.CompanyCustomerService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderLogService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.bizservice.evalate.EvaluateInfoFilesService;
import com.lwxf.industry4.webapp.bizservice.evalate.EvaluateInfoService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.aftersale.AftersaleStatus;
import com.lwxf.industry4.webapp.common.enums.customorder.CustomOrderCoordination;
import com.lwxf.industry4.webapp.common.enums.order.OrderStage;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.uniquecode.UniquneCodeGenerator;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.common.utils.PinYinUtils;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dao.aftersale.AftersaleApplyDao;
import com.lwxf.industry4.webapp.domain.dao.aftersale.AftersaleApplyFilesDao;
import com.lwxf.industry4.webapp.domain.dao.common.UploadFilesDao;
import com.lwxf.industry4.webapp.domain.dao.evaluate.EvaluateInfoDao;
import com.lwxf.industry4.webapp.domain.dto.company.WxCompanyDto;
import com.lwxf.industry4.webapp.domain.dto.customer.WxCustomerDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.*;
import com.lwxf.industry4.webapp.domain.entity.aftersale.AftersaleApply;
import com.lwxf.industry4.webapp.domain.entity.customer.CompanyCustomer;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderLog;
import com.lwxf.industry4.webapp.domain.entity.evaluate.EvaluateInfo;
import com.lwxf.industry4.webapp.domain.entity.evaluate.EvaluateInfoFiles;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.aftersale.AftersaleApplyFacade;
import com.lwxf.industry4.webapp.facade.admin.factory.dealer.OrderFacade;
import com.lwxf.industry4.webapp.facade.wxapi.dealer.order.BWxCustomOrderFacade;
import com.lwxf.industry4.webapp.facade.wxapi.factory.customer.CustomerFacade;
import com.lwxf.industry4.webapp.facade.wxapi.factory.dealer.DealerFacade;
import com.lwxf.industry4.webapp.facade.wxapi.factory.order.WxOrderFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.*;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/19 0019 17:32
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value = "BWxCustomOrderController", tags = {"B端微信小程序:售后评价"})
@RestController(value = "EvaluteController")
@RequestMapping(value = "/EvaluteController", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class EvaluteController {

    @Resource(name = "evaluateInfoService")
    private EvaluateInfoService evaluateInfoService;

    @Resource(name="evaluateInfoFilesService")
    private EvaluateInfoFilesService evaluateInfoFilesService;


    @ApiOperation(value = "设置售后评价", notes = "设置售后评价", response = EvaluateInfo.class)
    @PostMapping("/setEvalute")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "evaluateInfo", value = "设置售后评价", dataType = "EvaluateInfo", paramType = "body", required = true)
    })
    public RequestResult setEvalute(@RequestBody EvaluateInfo evaluateInfo) {
        int insert = evaluateInfoService.add(evaluateInfo);
        String[] fileIds = evaluateInfo.getFileIds();
        for (String fileid:fileIds){
            if("".equals(fileid)){
                continue;
            }
            MapContext mapContext =new MapContext();
            mapContext.put("id",fileid);
            mapContext.put("evaluateInfoId",evaluateInfo.getId());
            evaluateInfoFilesService.updateByMapContext(mapContext);
        }
        if(insert == 0){
            return ResultFactory.generateResNotFoundResult();
        }
        return ResultFactory.generateSuccessResult();
    }

    @ApiOperation(value = "查询售后评价", notes = "查询售后评价", response = EvaluateInfo.class)
    @GetMapping("/queryEvaluateInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "id", value = "订单id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "evaluatePrductId", value = "评价商品id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "evaluatePrductId", value = "评价商品id", dataType = "String", paramType = "query")
    })
    public RequestResult queryEvaluateInfo(@RequestParam(required = false) String evaluateOrderId,
                                           @RequestParam(required = false) Integer id,
                                           @RequestParam(required = false) Integer evaluatePrductId) {
        PaginatedFilter paginatedFilter =new PaginatedFilter();
        MapContext mapContext = new MapContext();
        mapContext.put("evaluateOrderId",evaluateOrderId);
        mapContext.put("id",id);
        mapContext.put("evaluatePrductId",evaluatePrductId);
        paginatedFilter.setFilters(mapContext);
        PaginatedList<EvaluateInfoFiles> evaluateInfoPaginatedList = evaluateInfoFilesService.selectByFilter(paginatedFilter);
        return ResultFactory.generateRequestResult(evaluateInfoPaginatedList);
    }



    @ApiOperation(value = "访问单上传附件", notes = "访问单上传附件")
    @PostMapping(value = "/uploadEvaluateInfoFile")
    public RequestResult uploadEvaluateInfoFile(@RequestBody MultipartFile multipartFile) {
        //updateFilesList
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        if (uid == null) {
            return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        return evaluateInfoFilesService.uploadEvaluateInfoFile(multipartFile, uid);
    }


























}
