package com.lwxf.industry4.webapp.controller.wxapi.dealer.order;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.bizservice.customer.CompanyCustomerService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderLogService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.aftersale.AftersaleCoupleBackStatus;
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
import com.lwxf.industry4.webapp.domain.dao.customorder.CustomOrderFilesDao;
import com.lwxf.industry4.webapp.domain.dto.aftersale.AftersaleDto;
import com.lwxf.industry4.webapp.domain.dto.company.CompanyDto;
import com.lwxf.industry4.webapp.domain.dto.company.WxCompanyDto;
import com.lwxf.industry4.webapp.domain.dto.customer.WxCustomerDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.*;
import com.lwxf.industry4.webapp.domain.entity.aftersale.AftersaleApply;
import com.lwxf.industry4.webapp.domain.entity.customer.CompanyCustomer;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderLog;
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
@Api(value = "BWxCustomOrderController", tags = {"B端微信小程序: 订单管理"})
@RestController(value = "BWxCustomOrderController")
@RequestMapping(value = "/wxapi/b", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class BWxCustomOrderController {
    @Resource(name = "bWxCustomOrderFacade")
    private BWxCustomOrderFacade bWxCustomOrderFacade;

    @Resource(name = "aftersaleApplyDao")
    private AftersaleApplyDao aftersaleApplyDao;



    @Resource(name = "customOrderService")
    private CustomOrderService customOrderService;

    @Resource(name = "fAftersaleApplyFacade")
    private AftersaleApplyFacade aftersaleApplyFacade;

    @Resource(name = "wxOrderFacade")
    private WxOrderFacade wxOrderFacade;

    @Resource(name = "orderFacade")
    private OrderFacade orderFacade;

    @Resource(name = "wxCustomerFacade")
    private CustomerFacade customerFacade;


    @Resource(name = "wxDealerFacade")
    private DealerFacade dealerFacade;

    @Resource(name = "companyCustomerService")
    private CompanyCustomerService companyCustomerService;
    @Resource(name = "customOrderLogService")
    private CustomOrderLogService customOrderLogService;
    @Resource(name = "aftersaleApplyFilesDao")
    private AftersaleApplyFilesDao aftersaleApplyFilesDao;

    @Resource(name = "customOrderFilesDao")
    private CustomOrderFilesDao customOrderFilesDao;


//    @ApiOperation(value = "查询经销商反馈", notes = "查询经销商反馈", response = WxOrderTrace.class)
//    @GetMapping("/queryCoupleBack/{orderId}")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "orderId", value = "订单id", dataType = "string", paramType = "path", required = true)
//    })
//    public String queryCoupleBack(@PathVariable String orderId) {
//        List<Map> aftersaleByOrderId = aftersaleApplyDao.findAftersaleByOrderId(orderId);
//        JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
//        if (aftersaleByOrderId != null && aftersaleByOrderId.size() != 0) {
//            return jsonMapper.toJson(aftersaleByOrderId);//返回反馈信息列表
//        } else {
//            return "";//未查到反馈信息
//        }
//    }

    @ApiOperation(value = "查询经销商售后反馈详情", notes = "查询经销商售后反馈详情", response = AftersaleDto.class)
    @GetMapping("/queryCoupleBack/{id}")
    public RequestResult findAftersaleDetail(@PathVariable(value = "id") String id) {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        if (uid == null) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        return this.aftersaleApplyFacade.findAftersalesDetail(id);
    }


    /**
     * 传单上传附件
     * @return
     */
    @ApiOperation(value = "反馈单上传附件",notes = "反馈单上传附件")
    @PostMapping(value = "/coupleBack/updatefiles")
    public RequestResult coupleBackUpdatefiles(@RequestBody MultipartFile multipartFile) {
        //updateFilesList
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
        if(uid==null){
            return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        return this.bWxCustomOrderFacade.insertCoupleBackUpdatefiles(uid, multipartFile);
    }

    /**
     * 订单上传附件
     * @return
     */
    @ApiOperation(value = "传单上传附件",notes = "订单上传附件")
    @PostMapping(value = "/order/updatefiles")
    public RequestResult orderUpdatefiles(@RequestBody MultipartFile multipartFile) {
        //updateFilesList
         String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
        if(uid==null){
            return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        return this.bWxCustomOrderFacade.orderUpdatefiles(uid, multipartFile);
    }

    @ApiOperation(value = "查询经销商反馈列表", notes = "查询经销商反馈列表", response = WxOrderTrace.class)
    @GetMapping("/queryCoupleBackList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页面条数", dataType = "integer", paramType = "query")
               })
    public String queryCoupleBackList(@RequestParam(required = false) Integer pageNum,
                                      @RequestParam(required = false) Integer pageSize,
                                      @RequestParam(required = false) String status) {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        MapContext mapContext = new MapContext();
        String branchId = mapInfo.get("branchId") == null ? null : mapInfo.get("branchId").toString();
        String companyId = mapInfo.get("companyId") == null ? null : mapInfo.get("companyId").toString();
        mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,branchId);
        mapContext.put("companyId",companyId);
        mapContext.put("status",status);
        paginatedFilter.setFilters(mapContext);
        Pagination pagination = new Pagination();
        pagination.setPageSize(pageSize);
        pagination.setPageNum(pageNum);
        paginatedFilter.setPagination(pagination);

        PaginatedList<MapContext> aftersaleApplyList = aftersaleApplyDao.findAftersaleApplyList(paginatedFilter);
        JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
        if (aftersaleApplyList != null) {
            List<MapContext> list = aftersaleApplyList.getRows();
            if(list != null) {
                for (MapContext map : list) {
                    if (map.containsKey("status") && map.get("status") != null) {
                        map.put("statusName", AftersaleCoupleBackStatus.getByValue(Integer.valueOf(map.get("status").toString())).getName());
                    }
                }
            }
            return jsonMapper.toJson(aftersaleApplyList);//返回反馈信息列表
        } else {
            return "";//未查到反馈信息
        }
    }

    @ApiOperation(value = "设置经销商反馈", notes = "设置经销商反馈", response = AftersaleApply.class)
    @PostMapping("/SetCoupleBack/{orderId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", dataType = "string", paramType = "path", required = true),
            @ApiImplicitParam(name = "aftersaleApply", value = "反馈说明",
                    dataType = "com.lwxf.industry4.webapp.domain.entity.aftersale.AftersaleApply", paramType = "path", required = true),

    })
    public RequestResult SetCoupleBack(@PathVariable String orderId,
                                       @RequestBody AftersaleApply aftersaleApply) {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        String companyId = mapInfo.get("companyId") == null ? null : mapInfo.get("companyId").toString();
        String branchId = mapInfo.get("branchId") == null ? null : mapInfo.get("branchId").toString();

        aftersaleApply.setCustomOrderId(orderId);
        aftersaleApply.setCreated(DateUtil.getSystemDate());
        aftersaleApply.setCreator(WebUtils.getCurrUserId());
        aftersaleApply.setStatus(AftersaleStatus.WAIT.getValue());
        aftersaleApply.setCreator(uid);
        aftersaleApply.setBranchId(branchId);
        aftersaleApply.setCompanyId(companyId);
        aftersaleApply.setCharge(false);
        aftersaleApply.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.AFTERSALE_APPLY_NO));
        RequestResult result = aftersaleApply.validateFields();
        if (result != null) {
            return result;
        }
        return this.aftersaleApplyFacade.addAftersale(orderId, aftersaleApply);
    }

    @ApiOperation(value = "查询经销商下的全部终端客户", notes = "查询经销商下的全部终端客户", response = WxOrderTrace.class)
    @GetMapping("/querydealerCustom")
    public String querydealerCustom() {
        JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        String dealerId = mapInfo.get("companyId") == null ? null : mapInfo.get("companyId").toString();
        if (uid == null) {
            return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        }
        String branchid = mapInfo.get("branchId") == null ? null : mapInfo.get("branchId").toString();
        MapContext mapContext = MapContext.newOne();
        if (LwxfStringUtils.isNotBlank(dealerId)) {
            mapContext.put("dealerId", dealerId);
        }
        mapContext.put("branchId", branchid);
        RequestResult result = this.customerFacade.findWxCustomers(0, Integer.MAX_VALUE, mapContext);
        Object data = result.getData();

        ArrayList<WxCustomerDto> dataList = new ArrayList<>();
        if (data != null) {
            Map<String, Object> dataMap = (Map<String, Object>) data;
            Object result1 = dataMap.get("result");
            if (result1 != null)
                dataList = (ArrayList<WxCustomerDto>) result1;
        }
        Map<String, List<WxCustomerDto>> map = new HashedMap();
        for (WxCustomerDto m : dataList) {
            String customerName = "" + m.getCustomerName();
            String c = PinYinUtils.getfirstName(customerName);
            if (map.containsKey(c)) {
                List<WxCustomerDto> tmpList = map.get(c);
                tmpList.add(m);
            } else {
                List<WxCustomerDto> WxCustomerDtos = new ArrayList<>();
                WxCustomerDtos.add(m);
                map.put(c, WxCustomerDtos);
            }
        }






      return jsonMapper.toJson(map);
    }


    //查询订单详情
    @ApiOperation(value = "查询订单详情接口", notes = "查询订单详情接口", response = WxCustomerOrderInfoDto.class)
    @GetMapping("/aftersaleOrder/{orderId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", dataType = "string", paramType = "path", required = true)
    })
    public String aftersaleOrderDetail(@PathVariable String orderId) {
        JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        if (uid == null) {
            return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        }
        String branchid = mapInfo.get("branchId") == null ? null : mapInfo.get("branchId").toString();
        RequestResult result = this.wxOrderFacade.findWxOrderInfo(branchid, orderId);
        return jsonMapper.toJson(result);

    }

    //查询订单详情
    @ApiOperation(value = "查询订单详情接口(不需要登录查询)", notes = "查询订单详情接口(不需要登录查询)", response = WxCustomerOrderInfoDto.class)
    @GetMapping("/aftersaleOrderDetailNoLogin/{orderId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", dataType = "string", paramType = "path", required = true)
    })
    public String aftersaleOrderDetailNoLogin(@PathVariable String orderId) {
        JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
        RequestResult result = this.wxOrderFacade.findWxOrderInfo("", orderId);
        return jsonMapper.toJson(result);
    }

    /**
     * 经销商新建订单
     *
     * @param customOrderInfoDto
     * @return
     */
    @ApiResponses({
            @ApiResponse(code = 200, message = "添加成功")
    })
    @ApiOperation(value = "经销商新增订单", notes = "经销商新增订单", response = CustomOrderInfoDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "订单数据", name = "customOrderInfoDto", dataTypeClass = CustomOrderInfoDto.class, paramType = "body")
    })
    @PostMapping("/aftersaleOrder")
    private RequestResult addOrder(@RequestBody CustomOrderInfoDto customOrderInfoDto) {
        CustomOrderDto customOrder = customOrderInfoDto.getCustomOrder();
        customOrder.setCreated(DateUtil.getSystemDate());
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        String companyId = mapInfo.get("companyId") == null ? null : mapInfo.get("companyId").toString();
        String branchId = mapInfo.get("branchId") == null ? null : mapInfo.get("branchId").toString();
        CompanyDto companyById = AppBeanInjector.companyService.findCompanyById(companyId);
        customOrder.setCityAreaId(companyById.getCityAreaId());
        customOrder.setCreator(uid);
        String orderNo = null;
        String noValue = null;
        Integer state = customOrder.getState();
        //orderNo = WebConstant.FACTORY_NAME_CODE + noValue;
        customOrder.setNo(orderNo);
        customOrder.setImprest(new BigDecimal(0));
        customOrder.setRetainage(new BigDecimal(0));
        //  customOrder.setMerchandiser(WebUtils.getCurrUserId()); 跟单员需用户选择
        customOrder.setEarnest(0);
        if (customOrder.getMarketPrice() == null || customOrder.getMarketPrice().equals("")) {
            customOrder.setMarketPrice(new BigDecimal(0));
        }
        customOrder.setDiscounts(new BigDecimal(0));
        if (customOrder.getFactoryDiscounts() == null || customOrder.getFactoryDiscounts().equals("")) {
            customOrder.setFactoryDiscounts(new BigDecimal(0));
        }
        if (customOrder.getFactoryFinalPrice() == null || customOrder.getFactoryFinalPrice().equals("")) {
            customOrder.setFactoryFinalPrice(new BigDecimal(0));
        }
        if (customOrder.getDesignFee() == null || customOrder.getDesignFee().equals("")) {
            customOrder.setDesignFee(new BigDecimal(0));
        }
        customOrder.setFactoryPrice(customOrder.getMarketPrice());
        customOrder.setAmount(new BigDecimal(0));
        customOrder.setConfirmFprice(false);
        customOrder.setConfirmCprice(false);
        customOrder.setEstimatedDeliveryDate(null);
        customOrder.setCoordination(CustomOrderCoordination.UNWANTED_COORDINATION.getValue());
        customOrder.setChange(0);
        customOrder.setOrderProductType(customOrder.getOrderProductType());
        customOrder.setBranchId(WebUtils.getCurrBranchId());
        customOrder.setCreated(new Date());
        customOrder.setDesignFee(new BigDecimal(0));
        customOrder.setFactoryPrice(new BigDecimal(0));
        customOrder.setMarketPrice(new BigDecimal(0));
        customOrder.setDiscounts(new BigDecimal(0));
        customOrder.setFactoryDiscounts(new BigDecimal(0));
        customOrder.setFactoryFinalPrice(new BigDecimal(0));
        customOrder.setConfirmCprice(false);
        customOrder.setConfirmFprice(false);
        customOrder.setOrderSource(1);
        customOrder.setCompanyId(uid);
        customOrder.setCompanyId(companyId);
        customOrder.setBranchId(branchId);
        if(customOrder.getPaymentWithholding()==null||customOrder.getPaymentWithholding().equals("")) {
            customOrder.setPaymentWithholding(0);
        }
        customOrder.setFlag(0);
        int add = this.customOrderService.add(customOrder);
        String[] ids = customOrder.getFiles().split(",");
        //更新图片附件信息
        MapContext mapContext = new MapContext();
        mapContext.put("id",ids);
        mapContext.put("customOrderId",customOrder.getId());
        customOrderFilesDao.updateByIds(mapContext);
        //记录操作日志
        Integer state1 = customOrderInfoDto.getCustomOrder().getState();
        if(state1 ==1){
            CustomOrderLog log = new CustomOrderLog();
            log.setCreated(new Date());
            log.setCreator(uid);
            log.setName("传单");
            log.setStage(OrderStage.LEAFLET.getValue());
            log.setContent("订单传单成功");
            log.setCustomOrderId(customOrder.getId());
            customOrderLogService.add(log);
        }
        if(add == 1){
            return ResultFactory.generateRequestResult(customOrder);
        }
        return ResultFactory.generateResNotFoundResult();
    }




    /**
     * 经销商提交订单
     *
     * @return
     */
    @ApiResponses({
            @ApiResponse(code = 200, message = "添加成功")
    })
    @ApiOperation(value = "经销商草稿订单提交", notes = "经销商草稿订单提交", response = CustomOrderInfoDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "经销商草稿订单提交", name = "customOrderInfoDto", dataTypeClass = CustomOrderInfoDto.class, paramType = "body")
    })
    @PostMapping("/aftersaleOrderCommit")
    private RequestResult aftersaleOrderCommit(@RequestBody CustomOrderDto customOrder) {
        customOrder.setCreated(DateUtil.getSystemDate());
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        MapContext updateOrder = new MapContext();
        updateOrder.put(WebConstant.KEY_ENTITY_ID, customOrder.getId());
        updateOrder.put("state", "1");
        Integer state = customOrder.getState();
        String orderNo = null;
        String noValue = null;
        if(state!=null && state==1) {
            //记录操作日志
            CustomOrderLog log = new CustomOrderLog();
            log.setCreated(new Date());
            log.setCreator(uid);
            log.setName("传单");
            log.setStage(OrderStage.LEAFLET.getValue());
            log.setContent("订单传单成功");
            log.setCustomOrderId(customOrder.getId());
            customOrderLogService.add(log);
        }
        updateOrder.put("no",orderNo);
        this.customOrderService.updateByMapContext(updateOrder);

        return ResultFactory.generateSuccessResult();
    }



    /**
     * 经销商编辑订单
     *
     * @return
     */
    @ApiResponses({
            @ApiResponse(code = 200, message = "添加成功")
    })
    @ApiOperation(value = "经销商草稿订单修改提交", notes = "经销商草稿订单修改提交", response = CustomOrderInfoDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "订单数据", name = "customOrderInfoDto", dataTypeClass = CustomOrderInfoDto.class, paramType = "body")
    })
    @PostMapping("/aftersaleOrderEdit")
    private RequestResult aftersaleOrderEdit(@RequestBody CustomOrderInfoDto customOrderInfoDto) {


        //
        CustomOrderDto customOrder = customOrderInfoDto.getCustomOrder();
        customOrder.setCreated(DateUtil.getSystemDate());
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        String branchid =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
        String companyId = mapInfo.get("companyId").toString();
        if (uid == null) {
            return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        customOrder.setCreator(uid);
        String orderNo = null;
        String noValue = null;
        Integer orderProductType = customOrder.getOrderProductType();
        Integer state = customOrder.getState();


        //orderNo = WebConstant.FACTORY_NAME_CODE + noValue;
        customOrder.setNo(orderNo);
        customOrder.setImprest(new BigDecimal(0));
        customOrder.setRetainage(new BigDecimal(0));
//        customOrder.setMerchandiser(WebUtils.getCurrUserId()); 跟单员需用户选择
        customOrder.setEarnest(0);
        if (customOrder.getMarketPrice() == null || customOrder.getMarketPrice().equals("")) {
            customOrder.setMarketPrice(new BigDecimal(0));
        }
        customOrder.setDiscounts(new BigDecimal(0));
        if (customOrder.getFactoryDiscounts() == null || customOrder.getFactoryDiscounts().equals("")) {
            customOrder.setFactoryDiscounts(new BigDecimal(0));
        }
        if (customOrder.getFactoryFinalPrice() == null || customOrder.getFactoryFinalPrice().equals("")) {
            customOrder.setFactoryFinalPrice(new BigDecimal(0));
        }
        if (customOrder.getDesignFee() == null || customOrder.getDesignFee().equals("")) {
            customOrder.setDesignFee(new BigDecimal(0));
        }
        customOrder.setFactoryPrice(customOrder.getMarketPrice());
        customOrder.setAmount(new BigDecimal(0));
        customOrder.setConfirmFprice(false);
        customOrder.setConfirmCprice(false);
        customOrder.setEstimatedDeliveryDate(null);
        customOrder.setCoordination(CustomOrderCoordination.UNWANTED_COORDINATION.getValue());
        customOrder.setChange(0);
        customOrder.setOrderProductType(customOrder.getOrderProductType());
        customOrder.setBranchId(WebUtils.getCurrBranchId());
        customOrder.setCreated(new Date());
        customOrder.setDesignFee(new BigDecimal(0));
        customOrder.setFactoryPrice(new BigDecimal(0));
        customOrder.setMarketPrice(new BigDecimal(0));
        customOrder.setDiscounts(new BigDecimal(0));
        customOrder.setFactoryDiscounts(new BigDecimal(0));
        customOrder.setFactoryFinalPrice(new BigDecimal(0));
        customOrder.setConfirmCprice(false);
        customOrder.setConfirmFprice(false);
        customOrder.setOrderSource(1);
        customOrder.setCompanyId(WebUtils.getCurrCompanyId());
        RequestResult result = customOrder.validateFields();
        this.customOrderService.deleteById(customOrder.getId());

        customOrder.setId(null);
        //判断客户是否存在
        MapContext filter = new MapContext();
        filter.put(WebConstant.KEY_ENTITY_NAME, customOrderInfoDto.getCustomOrder().getCustomerName());
        filter.put(WebConstant.KEY_ENTITY_COMPANY_ID, companyId);
        List<CompanyCustomer> customerByMap = this.companyCustomerService.findCustomerByMap(filter);
        if (customerByMap == null || customerByMap.size() == 0) {
            CompanyCustomer companyCustomer = new CompanyCustomer();
            companyCustomer.setName(customOrderInfoDto.getCustomOrder().getCustomerName());
            companyCustomer.setCompanyId(companyId);
            companyCustomer.setCreated(DateUtil.getSystemDate());
            companyCustomer.setCreator(uid);
            companyCustomer.setStatus(0);
            this.companyCustomerService.add(companyCustomer);
            customOrder.setCustomerId(companyCustomer.getId());
        } else {
            customOrder.setCustomerId(customerByMap.get(0).getId());
        }
        customOrder.setCompanyId(companyId);
        customOrder.setBranchId(branchid);
        int add = this.customOrderService.add(customOrder);
        String files = customOrder.getFiles();

        if(files!=null && !"".equals(files)){
            String[] split =  files.split(",");
            //更新图片附件信息
            MapContext mapContext = new MapContext();
            mapContext.put("id",split);
            mapContext.put("customOrderId",customOrder.getId());
            customOrderFilesDao.updateByIds(mapContext);
        }

        Integer state1 = customOrder.getState();
        if(state1==1){
            //记录操作日志
            CustomOrderLog log = new CustomOrderLog();
            log.setCreated(new Date());
            log.setCreator(uid);
            log.setName("传单");
            log.setStage(OrderStage.LEAFLET.getValue());
            log.setContent("订单传单成功");
            log.setCustomOrderId(customOrder.getId());
            customOrderLogService.add(log);
        }
        if(add == 1){
            return ResultFactory.generateRequestResult(customOrder);
        }
        return ResultFactory.generateResNotFoundResult();
    }


    /**
     * 经销商草稿修改
     *
     * @param id
     * @param mapContext
     * @return
     */
    @PutMapping("{id}")
    private RequestResult factoryUpdateOrder(@PathVariable String id,
                                             @RequestBody MapContext mapContext) {
        return this.orderFacade.factoryUpdateOrder(id, mapContext);
    }


    @ApiOperation(value = "经销商确认收货", notes = "经销商确认收货", response = WxOrderTrace.class)
    @GetMapping("/confirmationOfGoodsReceipt/{orderId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", dataType = "string", paramType = "path", required = true)
    })
    public RequestResult confirmationOfGoodsReceipt(@PathVariable String orderId) {

        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        if (uid == null) {
            return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        MapContext mapContext = new MapContext();
        mapContext.put("id", orderId);
        mapContext.put("status", "7");
        mapContext.put("sureDeliverType", 1);
        //添加确认收货custom_order_log
        CustomOrderLog log = new CustomOrderLog();
        log.setCreated(new Date());
        log.setCreator(uid);
        log.setName("完成");
        log.setStage(OrderStage.END.getValue());
        log.setContent("订单已确认收货");
        log.setCustomOrderId(orderId);
        customOrderLogService.add(log);

        int i = customOrderService.updateByMapContext(mapContext);
        if (i != 0) {
            return ResultFactory.generateRequestResult(mapContext);
        } else {
            return ResultFactory.generateResNotFoundResult();
        }

    }


    @ApiOperation(value = "订单跟踪接口", notes = "订单跟踪", response = WxOrderTrace.class)
    @GetMapping("/orders/Trace/{orderId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", dataType = "string", paramType = "path", required = true)
    })
    public RequestResult ordersTrace(@PathVariable String orderId) {
        JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        if (uid == null) {
            return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        String branchid = mapInfo.get("branchId") == null ? null : mapInfo.get("branchId").toString();

        RequestResult requestResult = wxOrderFacade.QueryOrdersTrace(orderId);
        return requestResult;

    }

    /**
     * 经销商传单详情查询接口
     * @return
     */
    @ApiOperation(value = "传单经销商详情查询接口",notes = "传单经销商详情查询接口",response = WxCompanyDto.class)
    @GetMapping("/dealersInfo/")
    private String findWxDealerInfo(){
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        String atoken= WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
        if(uid==null){
            return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        }
        String branchid =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
        String dealerId = mapInfo.get("companyId").toString();
        RequestResult result=this.dealerFacade.findWxDealerInfo(branchid,dealerId);
        Object data = result.getData();
        return jsonMapper.toJson(result);
    }

    /**
     * 经销商传单删除
     * @return
     */
    @ApiOperation(value = "经销商传单删除",notes = "经销商传单删除",response = WxCompanyDto.class)
    @GetMapping("/deleteDealerOrderInfo/{orderId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", dataType = "string", paramType = "path", required = true)
    })
    private RequestResult deleteOrderInfo(@PathVariable String orderId){
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        String atoken= WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
        if (uid == null) {
            return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        int i = customOrderService.deleteById(orderId);
        if(i!=0){
            return ResultFactory.generateSuccessResult();
        }else {
            return ResultFactory.generateResNotFoundResult();
        }
    }

    /**
     * 经销商传单删除
     * @return
     */
    @ApiOperation(value = "经销商附件传单删除",notes = "经销商附件传单删除",response = WxCompanyDto.class)
    @GetMapping("/deleteOrderFile/{filesId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", dataType = "string", paramType = "path", required = true)
    })
    private RequestResult deleteOrderFile(@PathVariable String filesId){
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        String atoken= WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
        if (uid == null) {
            return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        int i = customOrderFilesDao.deleteById(filesId);
        if(i!=0){
            return ResultFactory.generateSuccessResult();
        }else {
            return ResultFactory.generateResNotFoundResult();
        }
    }



    /**
     * 订单列表
     *
     * @param pageNum
     * @param pageSize
     * @param condation
     * @param startTime
     * @param endTime
     * @param cityId
     * @return
     */
    @ApiOperation(value = "查询订单列表接口", notes = "查询订单列表接口", response = WxCustomOrderDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页面条数", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "condation", value = "手机号、客户姓名、订单编号", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "cityId", value = "地区id ", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startTime", value = "开始时间 2019-06-01", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间 2019-06-10", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "salesman", value = "业务员id", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "orderProductType", value = "订单类型（橱柜、五金、样块、衣柜）", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "customerId", value = "终端客户id", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "state", value = "经销商端创建订单专用状态 ：0-草稿  1-已提交", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "orderSource", value = "订单来源：0-工厂端创建 1-经销商创建", dataType = "string", paramType = "query")
    })
    @GetMapping("/orders")
    public String findWxOrderList(@RequestParam(required = false) Integer pageNum,
                                  @RequestParam(required = false) Integer pageSize,
                                  @RequestParam(required = false) String condation,
                                  @RequestParam(required = false) String startTime,
                                  @RequestParam(required = false) String endTime,
                                  @RequestParam(required = false) String cityId,
                                  @RequestParam(required = false) String salesman,
                                  @RequestParam(required = false) String status,
                                  @RequestParam(required = false) String orderProductType,
                                  @RequestParam(required = false) String customerId,
                                  @RequestParam(required = false) String state,
                                  @RequestParam(required = false) String orderSource) {
        JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        if (uid == null) {
            return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        }
//		String branchid =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
        if (null == pageSize) {
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        if (null == pageNum) {
            pageNum = 1;
        }
        MapContext mapContext = MapContext.newOne();
        if (LwxfStringUtils.isNotBlank(condation)) {
            mapContext.put("condation", condation);
        }
        if (LwxfStringUtils.isNotBlank(startTime)) {
            mapContext.put("startTime", startTime);
        }
        if (LwxfStringUtils.isNotBlank(endTime)) {
            mapContext.put("endTime", endTime);
        }
        if (LwxfStringUtils.isNotBlank(cityId)) {
            mapContext.put("cityId", cityId);
        }
        if (LwxfStringUtils.isNotBlank(salesman)) {
            mapContext.put("salesman", salesman);
        }
        if (LwxfStringUtils.isNotBlank(orderProductType)) {//订单产品类型
            mapContext.put("orderProductType", orderProductType);
        }
        if (LwxfStringUtils.isNotBlank(customerId)) {//订单产品类型
            mapContext.put("customerId", customerId);
        }
//        ArrayList<String> statusList = new ArrayList<>();
//        if (status != null && !status.equals("")) {
//            statusList.addAll(Arrays.asList(status.split("A")));
//        }
        if (LwxfStringUtils.isNotBlank(status)) {
            mapContext.put("status", status);
        }
        if (LwxfStringUtils.isNotBlank(state)) {
            mapContext.put("state", state);
        }
        if (LwxfStringUtils.isNotBlank(orderSource)){
            mapContext.put("orderSource", orderSource);
        }

        String dealerId = mapInfo.get("companyId").toString();
        mapContext.put("dealerId", dealerId);
        RequestResult result = this.bWxCustomOrderFacade.findWxOrderList(dealerId, pageNum, pageSize, mapContext);
        return jsonMapper.toJson(result);

    }


    @ApiOperation(value = "查询订单详情接口", notes = "查询订单详情接口", response = WxCustomerOrderInfoDto.class)
    @GetMapping("/orders/{orderId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", dataType = "string", paramType = "path", required = true)
    })
    public String findWxOrderInfo(@PathVariable String orderId) {
        JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        if (uid == null) {
            return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        }
        String branchid = mapInfo.get("branchId") == null ? null : mapInfo.get("branchId").toString();
        RequestResult result = this.bWxCustomOrderFacade.findWxOrderInfo(branchid, orderId);
        return jsonMapper.toJson(result);

    }

    /**
     * 消息订单接口
     */
    @ApiOperation(value = "查询消息订单接口", notes = "查询消息订单接口(分页)")
    @GetMapping("/orders/messageOrders")
    public String findMessageOrderInfo(@RequestParam(required = false) Integer pageNum,
                                       @RequestParam(required = false) Integer pageSize) {
        JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
        if (null == pageSize) {
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        if (null == pageNum) {
            pageNum = 1;
        }
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        if (uid == null) {
            return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        }
        String companyId = mapInfo.get("companyId") == null ? null : mapInfo.get("companyId").toString();
        RequestResult result = this.bWxCustomOrderFacade.findMessageOrderInfo(pageNum, pageSize, companyId);
        return jsonMapper.toJson(result);
    }

    /**
     * 查询最近的订单列表，默认显示3条
     *
     * @param num 查询订单数量
     * @return
     */
    @ApiOperation(value = "查询最近订单", notes = "查询最近订单")
    @GetMapping("/orders/recentOrders")
    public String findRecentOrder(@RequestParam(value = "num", required = false, defaultValue = "3") Integer num) {
        JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        // 登录用户id
        String uid = mapInfo == null ? null : mapInfo.get("userId").toString();
        if (uid == null) {
            return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        }
        // 经销商id
        String dealerId = mapInfo.get("companyId").toString();
        // TODO 判断经销商是否存在

        RequestResult result = this.bWxCustomOrderFacade.findRecentOrder(dealerId, num);

        return jsonMapper.toJson(result);
    }
}
