package com.lwxf.industry4.webapp.facade.wxapi.factory.procurement;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.procurement.PurchaseRequestDto;
import com.lwxf.industry4.webapp.domain.dto.supplier.SupplierDtoFowWx;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.procurement.PurchaseFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/17/017 11:13
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value = "WxFactoryPurchaseController",tags = {"f端采购申请单管理接口"})
@RestController
@RequestMapping(value = "/api/f/WxFactoryPurchase", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class WxFactoryPurchaseController {
    @Resource(name = "purchaseFacade")
    private PurchaseFacade purchaseFacade;

    /**
     * 查询申请采购单列表
     *
     * @param pageSize
     * @param pageNum
     * @param no
     * @param storageId
     * @param name
     * @param productName
     * @param creator
     * @param status
     * @return
     */
    @GetMapping("requests")
	@ApiOperation(value = "查询申请采购单列表",notes = "",response = PurchaseRequestDto.class)
    private String findRequestList(@RequestParam(required = false)@ApiParam(value = "页面大小") Integer pageSize,
                                          @RequestParam(required = false)@ApiParam(value = "页码") Integer pageNum,
                                          @RequestParam(required = false)@ApiParam(value = "申请单编号") String no,
                                          @RequestParam(required = false)@ApiParam(value = "仓库id") String storageId,
								          @RequestParam(required = false)@ApiParam(value = "申请单id") String purchaseRequestId,
                                          @RequestParam(required = false)@ApiParam(value = "申请单名称") String name,
                                          @RequestParam(required = false)@ApiParam(value = "产品名称") String productName,
                                           @RequestParam(required = false)@ApiParam(value = "申请单分类") String type,
                                          @RequestParam(required = false)@ApiParam(value = "创建人") String creator,
                                          @RequestParam(required = false)@ApiParam(value = "状态") String status) {

        if (null == pageSize) {
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        if (null == pageNum) {
            pageNum = 1;
        }
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        MapContext mapContext = this.createMapContent(no, storageId,purchaseRequestId, name, productName, type, creator, status);
        RequestResult result=this.purchaseFacade.findRequestList(mapContext, pageNum, pageSize);
        return jsonMapper.toJson(result);
    }

	@PutMapping("/audits/{purchaseRequestId}")
	@ApiOperation(value = "更改审核人员的审核状态",notes = "")
	private String updatePurchaseAuditStatus(@PathVariable String purchaseRequestId,@RequestBody MapContext mapContext){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		RequestResult result=this.purchaseFacade.updatePurchaseAuditStatus(purchaseRequestId,mapContext);
		return jsonMapper.toJson(result);
	}
	@PutMapping("/requests/{purchaseRequestId}")
	@ApiOperation(value = "财务审核申请单",notes = "")
	private String updatePurchaseRequestStatus(@PathVariable@ApiParam(value = "申请单id") String purchaseRequestId
											   ){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		RequestResult result=this.purchaseFacade.updatePurchaseRequestStatus(purchaseRequestId);
		return jsonMapper.toJson(result);
	}
	@PutMapping("/products/{purchaseRequestId}")
	@ApiOperation(value = "采购单产品入库",notes = "")
	private String updatePurchaseProduct(@PathVariable@ApiParam(value = "申请单id") String purchaseRequestId,
										@RequestBody PurchaseRequestDto requestDto){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		RequestResult result=this.purchaseFacade.updatePurchaseProduct(purchaseRequestId,requestDto);
		return jsonMapper.toJson(result);
	}
	/**
	 * f分类查询查询供应商列表
	 */
	@ApiResponse(code = 200, message = "查询成功")
	@ApiOperation(value = "供应商信息列表", notes = "",response = SupplierDtoFowWx.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "type", value = "类型id basecode表里的id", dataType = "string", paramType = "query" ),
	})
	@GetMapping("suppliers/{type}")
	private RequestResult findAllSupplierList(@PathVariable String type){
		MapContext mapContext=MapContext.newOne();
		mapContext.put("branchId", WebUtils.getCurrBranchId());
		mapContext.put("categoryId",type);
		return this.purchaseFacade.findAllSupplierList(mapContext);
	}

	private MapContext createMapContent(String no, String storageId, String purchaseRequestId, String name, String productName, String type, String creator, String status) {
		MapContext mapContext = MapContext.newOne();
		if (no != null && !no.trim().equals("")) {
			mapContext.put(WebConstant.STRING_NO, no);
		}
		if (storageId != null && !storageId.trim().equals("")) {
			mapContext.put("storageId", storageId);
		}
		if (name != null && !name.trim().equals("")) {
			mapContext.put("name", name);
		}
		if (type != null && !type.trim().equals("")) {
			mapContext.put("categoryId", type);
		}
		if (purchaseRequestId != null && !purchaseRequestId.trim().equals("")) {
			mapContext.put("id", purchaseRequestId);
		}
		if (productName != null && !productName.trim().equals("")) {
			mapContext.put("productName", productName);
		}
		if (creator != null && !creator.trim().equals("")) {
			mapContext.put("creator", creator);
		}
		if (status != null && !status.trim().equals("")) {
			mapContext.put("status", status);
		}
		return mapContext;
	}

}
