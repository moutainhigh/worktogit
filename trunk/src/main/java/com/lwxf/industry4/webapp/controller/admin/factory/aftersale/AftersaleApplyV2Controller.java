package com.lwxf.industry4.webapp.controller.admin.factory.aftersale;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.company.FactoryEmployeeRole;
import com.lwxf.industry4.webapp.common.enums.customorder.ProduceOrderPermit;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.FileMimeTypeUtil;
import com.lwxf.industry4.webapp.common.utils.UserUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.aftersale.AftersaleDtoForAdd;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.ProduceOrderDto;
import com.lwxf.industry4.webapp.domain.entity.aftersale.AftersaleApply;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.aftersale.AfterSaleApplyV2Facade;
import com.lwxf.mybatis.utils.MapContext;

import io.swagger.annotations.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * 功能：
 *
 * @author：zhangxiaolin(3965488@qq.com)
 * @create：2019/1/8/008 9:56
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value="AftersaleApplyV2Controller",tags={"F端后台管理接口:售后管理"})
@RestController(value = "fAftersaleApplyV2Controller")
@RequestMapping(value = "/api/f/aftersales/v2", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class AftersaleApplyV2Controller {

    @Resource(name = "fAftersaleApplyV2Facade")
    private AfterSaleApplyV2Facade afterSaleApplyV2Facade;


    /**
     * 售后申请单列表

    @ApiOperation(value = "售后单查询列表", notes = "")
    @GetMapping("/aftersaleApplies")
    public  String findAftersaleApplyList(@RequestParam(required = false) String beginTime,
                                          @RequestParam(required = false) String endTime,
                                          @RequestParam(required = false) String leaderName,
                                          @RequestParam(required = false) String companyName,
                                          @RequestParam(required = false) String custName,
                                          @RequestParam(required = false) String cityName,
                                          @RequestParam(required = false) String prodType,
                                          @RequestParam(required = false) String orderNo,
                                          @RequestParam(required = false) String no,
                                          @RequestParam(required = false) String type,
                                          @RequestParam(required = false) Integer isHandle,
                                          @RequestParam(required = false) String series,
                                          @RequestParam(required = false) List<Integer> status,
                                          @RequestParam(required = false) Integer pageNum,
                                          @RequestParam(required = false) Integer pageSize){
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        if (null == pageSize) {
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        if (null == pageNum) {
            pageNum = 1;
        }
        MapContext mapContext = createMapContent(beginTime,endTime,companyName,leaderName,custName,cityName,prodType,series,status,isHandle,orderNo,no,type);
        RequestResult result=this.afterSaleApplyV2Facade.findAftersaleApplyList(pageNum,pageSize,mapContext);
        return jsonMapper.toJson(result);
    }
     */
    /**
     * 通过条件查询售后补料单列表
     *
     * @param no
     * @param customerTel
     * @param pageNum
     * @param pageSize
     * @param status
     * @param allocated
     * @param confirmFprice
     * @return
     */
    @GetMapping("/aftersaleApplies")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "开始日期",name = "startTime",dataType = "date",paramType = "query")
    })
    @ApiOperation(value = "通过条件查询售后补料单列表",notes = "通过条件查询售后补料单列表",response = CustomOrderDto.class)
    private String findOrderList(@RequestParam(required = false)@ApiParam(value = "单据编号") String no,
                                 @RequestParam(required = false)@ApiParam(value = "订单Id") String orderId,
                                 @RequestParam(required = false)@ApiParam(value = "客户电话") String customerTel,
                                 @RequestParam(required = false)@ApiParam(value = "客户名称") String customerName,
                                 @RequestParam(required = false)@ApiParam(value = "页码") Integer pageNum,
                                 @RequestParam(required = false)@ApiParam(value = "每页数据量") Integer pageSize,
                                 @RequestParam(required = false)@ApiParam(value = "订单状态集合") List<Integer> status,
                                 @RequestParam(required = false)@ApiParam(value = "是否分配设计师") Boolean allocated,
                                 @RequestParam(required = false)@ApiParam(value = "厂房最终报价是否已确认") Boolean confirmFprice,
                                 @RequestParam(required = false)@ApiParam(value = "经销商电话") String dealerTel,
                                 @RequestParam(required = false)@ApiParam(value = "收货地址") String address,
                                 @RequestParam(required = false)@ApiParam(value = "公司主键ID") String companyId,
                                 @RequestParam(required = false)@ApiParam(value = "经销商公司名称") String companyName,
                                 @RequestParam(required = false)@ApiParam(value = "经销商地址") String dealerAddress,
                                 @RequestParam(required = false)@ApiParam(value = "开始日期") String startTime,
                                 @RequestParam(required = false)@ApiParam(value = "下单人") String placeOrder,
                                 @RequestParam(required = false)@ApiParam(value = "接单人") String receiver,
                                 @RequestParam(required = false)@ApiParam(value = "登录人") String loginId,
                                 @RequestParam(required = false)@ApiParam(value = "是否需要设计 0 不需要 1 需要") Integer design,
                                 @RequestParam(required = false)@ApiParam(value = "结束日期") String endTime
    ) {
        if (null == pageSize) {
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        if (null == pageNum) {
            pageNum = 1;
        }
        MapContext mapContext = this.createMapContext(no,orderId, customerTel, status,companyId ,address, allocated, confirmFprice,dealerTel,startTime,endTime,design,companyName,dealerAddress,customerName,placeOrder,receiver,loginId);
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.toJson(this.afterSaleApplyV2Facade.findAftersaleOrderList(mapContext, pageNum, pageSize));
    }
    /**
     * 售后单详情
     * @param aftersaleApplyId
     * @return
     */
    @ApiOperation(value = "售后单详情", notes = "")
    @GetMapping("/aftersaleApplies/{aftersaleApplyId}")
    public String factoryAftersaleApplyInfo(@PathVariable String aftersaleApplyId){
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        RequestResult result=this.afterSaleApplyV2Facade.factoryAftersaleApplyInfo(aftersaleApplyId);
        return jsonMapper.toJson(result);
    }

    /**
     * 经销商列表
     * @return
     */
    @ApiOperation(value = "经销商列表", notes = "")
    @GetMapping("/dealers")
    public String findDealersList(@RequestParam(required = false) String mergerName,
                                  @RequestParam(required = false) String dealerName){
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        MapContext params=MapContext.newOne();
        if(LwxfStringUtils.isNotBlank(mergerName)){
            params.put("mergerName",mergerName);
        }
        if(LwxfStringUtils.isNotBlank(dealerName)){
            params.put("dealerName",dealerName);
        }
        RequestResult result=this.afterSaleApplyV2Facade.findDealersList(params);
        return jsonMapper.toJson(result);
    }

    @ApiResponse(code = 200, message = "编辑成功")
    @ApiOperation(value="编辑售后信息",notes="编辑售后信息")
    @PutMapping("/edit/{aftersaleId}")
    private RequestResult editAftersaleApply(@PathVariable String aftersaleId,@RequestBody MapContext map){
        JsonMapper jsonMapper=new JsonMapper();
        map.put("id",aftersaleId);
        return this.afterSaleApplyV2Facade.editAftersaleApply(map);
    }



    /**
     * 客户列表
     * @param dealerId
     * @param request
     * @return
     */
    @ApiOperation(value = "客户列表", notes = "")
    @GetMapping("/dealers/{dealerId}/customers")
    public String findCustomerList(@PathVariable String dealerId,
                                   HttpServletRequest request){
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        RequestResult result=this.afterSaleApplyV2Facade.findCustomerList(dealerId);
        return jsonMapper.toJson(result);
    }

    /**
     * 创建售后申请单
     * @return
     */
    @ApiOperation(value = "添加售后申请单", notes = "")
    @PostMapping("/aftersale/request")
    public RequestResult addFactoryAftersale(@RequestBody AftersaleApply aftersaleApply){
        RequestResult result=this.afterSaleApplyV2Facade.addFactoryAftersaleRequest(aftersaleApply);
        return  result;
    }

    /**
     * 创建售后单
     * @return
     */
    @ApiOperation(value = "添加售后单", notes = "",response = AftersaleDtoForAdd.class)
    @PostMapping("/aftersale")
    public RequestResult addFactoryAftersale(@RequestBody AftersaleDtoForAdd aftersaleApply){
        if(aftersaleApply.getCharge()==null){
            aftersaleApply.setCharge(false);
        }
        if(aftersaleApply.getChargeAmount()==null){
            aftersaleApply.setChargeAmount(new BigDecimal(0));
        }
        if(aftersaleApply.getPayAmount()==null){
            aftersaleApply.setPayAmount(new BigDecimal(0));
        }
        RequestResult result=this.afterSaleApplyV2Facade.addFactoryAftersale(aftersaleApply);
        return  result;
    }

    /**
     * 条件查询经销商账户金额
     * @return
     */
    @ApiResponse(code = 200, message = "查询成功")
    @ApiOperation(value = "首页统计信息", notes = "")
    @GetMapping("/countAftersale")
    private String countPayments(){
        JsonMapper jsonMapper=new JsonMapper();
        RequestResult result=this.afterSaleApplyV2Facade.countAftersaleForPageIndex();
        return jsonMapper.toJson(result);
    }


    /**
     * 更新售后单状态
     * @return
     */
    @ApiOperation(value = " 更新售后单状态", notes = "")
    @PutMapping("/aftersaleApplies/{aftersaleId}/status/{status}")
    public RequestResult updateAftersaleStatus(@PathVariable String aftersaleId,@PathVariable String status){
        RequestResult result=this.afterSaleApplyV2Facade.updateAftersaleStatus(aftersaleId,status);
        return  result;
    }

    /**
     * 处理售后单
     * @return
     */
    @ApiOperation(value = "售后单处理", notes = "")
    @PutMapping("/handle")
    public RequestResult handleFactoryAftersale(@RequestBody MapContext mapContext){
        RequestResult result=this.afterSaleApplyV2Facade.handleFactoryAftersale(mapContext);
        return  result;
    }

    /**
     * 上传售后申请证据图片
     */
    @ApiOperation(value = " 上传售后申请证据图片", notes = "")
    @PostMapping("/aftersaleApplies/addfiles")
    public RequestResult addFiles(@RequestBody List<MultipartFile> multipartFiles){
        Map<String, Object> errorInfo = new HashMap<>();
        if (multipartFiles == null||multipartFiles.size()==0) {
            errorInfo.put("multipartFiles", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        }
        for (MultipartFile multipartFile:multipartFiles) {
            if (multipartFile==null){
                errorInfo.put("multipartFile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
            }
            if (!FileMimeTypeUtil.isLegalImageType(multipartFile)) {
                errorInfo.put("multipartFile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_ILLEGAL_ARGUMENT"));
            }
            if (multipartFile.getSize() > 1024L * 1024L * AppBeanInjector.configuration.getUploadBackgroundMaxsize()) {
                return ResultFactory.generateErrorResult(com.lwxf.commons.exception.ErrorCodes.BIZ_FILE_SIZE_LIMIT_10031, LwxfStringUtils.format(AppBeanInjector.i18nUtil.getMessage("BIZ_FILE_SIZE_LIMIT_10031"), AppBeanInjector.configuration.getUploadBackgroundMaxsize()));
            }
        }
        if (errorInfo.size() > 0) {
            return ResultFactory.generateErrorResult(com.lwxf.commons.exception.ErrorCodes.VALIDATE_ERROR, errorInfo);
        }
        return this.afterSaleApplyV2Facade.addFiles(multipartFiles);
    }

    /**
     * 删除记账信息
     * @param aftersaleId  记账信息id
     * @return
     */
    @ApiOperation(value="删除售后信息",notes="删除售后信息")
    @DeleteMapping(value = "/aftersaleApplies/{aftersaleId}")
    public String delete(@PathVariable String aftersaleId) {
        JsonMapper jsonMapper=new JsonMapper();
        return jsonMapper.toJson(this.afterSaleApplyV2Facade.deleteAftersale(aftersaleId));
    }

	/**
	 * 新建（售后）生产单
	 *
	 */

	@ApiResponses({
			@ApiResponse(code = 200,message = "添加成功",response = ProduceOrderDto.class )
	})
	@ApiOperation(value = "新增生产拆单",notes = "新增生产拆单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "售后单id",dataType = "string",paramType = "path",required = true),
            @ApiImplicitParam(name = "productId",value = "产品id",dataType = "string",paramType = "path",required = true)
    })
	@PostMapping("/{id}/produces/{productId}")
	private RequestResult addProduceOrder(@PathVariable@ApiParam(value = "售后单Id") String id,
										  @PathVariable@ApiParam(value = "产品ID") String productId,
										  @RequestBody ProduceOrderDto produceOrderDto){
		produceOrderDto.setOrderProductId(productId);
		produceOrderDto.setPermit(ProduceOrderPermit.NOT_ALLOW.getValue());
		return this.afterSaleApplyV2Facade.addProduceAftersaleOrder(id,produceOrderDto,produceOrderDto.getFileIds());
	}

    /**
     * 售后产品列表
     * @param id
     * @return
     */
    @ApiOperation(value = "售后单产品列表",notes = "售后单产品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "售后单id",dataType = "string",paramType = "path",required = true)
    })
	@GetMapping("/{id}/products")
    private String findAftersaleProduct(@PathVariable@ApiParam(value = "售后单Id") String id){
	    JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
	    RequestResult result=this.afterSaleApplyV2Facade.findAftersaleProduct(id);
	    return jsonMapper.toJson(result);

    }


	/**
     * 参数组成
     * @param beginTime  开始时间
     * @return
     */
    private MapContext createMapContent(String beginTime,String endTime,String companyName,String leaderName,String custName,String cityName,String prodType,String series,List<Integer> status,Integer isHandle,String orderNo,String no,String type) {
        MapContext mapContext = MapContext.newOne();
        if (beginTime!=null && !beginTime.equals("")) {
            mapContext.put("beginTime", beginTime);
        }
        if (endTime!=null && !endTime.equals("")) {
            mapContext.put("endTime", endTime);
        }
        if (leaderName!=null && !leaderName.equals("")) {
            mapContext.put("leaderName", leaderName);
        }
        if (companyName!=null && !companyName.equals("")) {
            mapContext.put("companyName", companyName);
        }
        if (custName!=null && !custName.equals("")) {
            mapContext.put("status", custName);
        }
        if (cityName!=null && !cityName.equals("")) {
            mapContext.put("cityName", cityName);
        }
        if (prodType!=null && !prodType.equals("")) {
            mapContext.put("productType", prodType);
        }
        if (series!=null && !series.equals("")) {
            mapContext.put("productSeries", series);
        }
        if (orderNo!=null && !orderNo.equals("")) {
            mapContext.put("orderNo", orderNo);
        }
        if (no!=null && !no.equals("")) {
            mapContext.put("no", no);
        }
        if (status!=null&&status.size()>0) {
            mapContext.put("status", status);
        }else{
            //处理页面查询
            if(isHandle!=null && isHandle==1){
                mapContext.put("statusHandles", "1");
            }
        }
        if(type!=null){
            mapContext.put("type",type);
        }
        return mapContext;
    }

    private MapContext createMapContext(String no,String orderId, String customerTel, List<Integer> status, String companyId,String address, Boolean allocated, Boolean confirmFprice,String dealerTel,String startTime,
                                        String endTime,Integer design,String companyName,String dealerAddress,String customerName,String placeOrder,String receiver,String loginId) {
        MapContext mapContext = MapContext.newOne();
        if (no != null && !no.trim().equals("")) {
            mapContext.put(WebConstant.STRING_NO, no);
        }
        if (orderId != null && !orderId.trim().equals("")) {
            mapContext.put("orderId", orderId);
        }
        if (customerTel != null && !customerTel.trim().equals("")) {
            mapContext.put("customerTel", customerTel);
        }
        if (status != null && status.size() != 0) {
            mapContext.put(WebConstant.KEY_ENTITY_STATUS, status);
        }
        if (companyId!=null&&!companyId.equals("")) {
            mapContext.put(WebConstant.KEY_ENTITY_COMPANY_ID, companyId);
        }
        if (allocated != null) {
            if (allocated) {
                if (UserUtil.hasRoleByKey(WebUtils.getCurrUserId(), FactoryEmployeeRole.DESIGNER.getValue())) {
                    mapContext.put("designer", WebUtils.getCurrUserId());
                } else {
                    mapContext.put("designer", "notNull");
                }
            } else {
                mapContext.put("designer", "null");
            }
        }
        if (confirmFprice != null  && !confirmFprice.equals("")) {
            mapContext.put("confirmFprice", confirmFprice);
        }
        if(dealerTel!=null &&!dealerTel.trim().equals("")){
            mapContext.put("dealerTel",dealerTel);
        }
        if(dealerAddress!=null &&!dealerAddress.trim().equals("")){
            mapContext.put("dealerAddress",dealerAddress);
        }
        if(placeOrder!=null &&!placeOrder.trim().equals("")){
            mapContext.put("placeOrder",placeOrder);
        }
        if(receiver!=null &&!receiver.trim().equals("")){
            mapContext.put("receiver",receiver);
        }
        if(loginId!=null &&!loginId.trim().equals("")){
            mapContext.put("loginId",loginId);
        }
        if(address!=null &&!address.trim().equals("")){
            mapContext.put("address",address);
        }
        if(startTime!=null &&!startTime.trim().equals("")){
            mapContext.put("startTime",startTime);
        }
        if(endTime!=null &&!endTime.trim().equals("")){
            mapContext.put("endTime",endTime);
        }
        if(design!=null &&!design.equals("")){
            mapContext.put("design",design);
        }
        if(companyName!=null &&!companyName.trim().equals("")){
            mapContext.put("companyName",companyName);
        }
        if(customerName!=null &&!customerName.trim().equals("")){
            mapContext.put("customerName",customerName);
        }
        return mapContext;
    }
}
