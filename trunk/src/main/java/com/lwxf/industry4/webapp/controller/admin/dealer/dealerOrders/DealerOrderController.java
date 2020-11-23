package com.lwxf.industry4.webapp.controller.admin.dealer.dealerOrders;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.FileMimeTypeUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.aftersale.AftersaleDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderInfoDto;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.dealer.OrderFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2020-03-24 10:33
 * @version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value = "DealerOrderController", tags = {"B端后台管理接口:经销商订单管理"})
@RestController(value = "DealerOrderController")
@RequestMapping(value = "/api/b/orders", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class DealerOrderController {

    @Resource(name = "orderFacade")
    private OrderFacade orderFacade;

    /**
     * 订单列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "查询订单列表接口", notes = "查询订单列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页面条数", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "orderNo", value = "订单编号", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "customerName", value = "客户姓名 ", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态 ", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "state", value = "草稿-0 正式 -1 ", dataType = "string", paramType = "query"),
    })
    @GetMapping("/list")
    public String findWxOrderList(@RequestParam(required = false) Integer pageNum,
                                  @RequestParam(required = false) Integer pageSize,
                                  @RequestParam(required = false) String orderNo,
                                  @RequestParam(required = false) String customerName,
                                  @RequestParam(required = false) List<Integer> status ,
                                  @RequestParam(required = false)  String startTime,
                                  @RequestParam(required = false)  String endTime,
                                  @RequestParam(required = false) String state
                               ) {
        JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
        if (null == pageSize) {
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        if (null == pageNum) {
            pageNum = 1;
        }
        MapContext mapContext = MapContext.newOne();
        if (LwxfStringUtils.isNotBlank(orderNo)) {
            mapContext.put("no", orderNo);
        }
        if (LwxfStringUtils.isNotBlank(customerName)) {
            mapContext.put("customerName", customerName);
        }
        if (status != null && status.size() != 0) {
            mapContext.put(WebConstant.KEY_ENTITY_STATUS, status);
        }
        if (LwxfStringUtils.isNotBlank(state)) {
            mapContext.put("state", state);
        }
        if (startTime != null && !startTime.trim().equals("")) {
            mapContext.put("startTime", startTime);
        }
        if (endTime != null && !endTime.trim().equals("")) {
            mapContext.put("endTime", endTime);
        }
        String dealerId = WebUtils.getCurrCompanyId();
        mapContext.put("companyId", dealerId);
        RequestResult result = this.orderFacade.findDealerOrderList(dealerId, pageNum, pageSize, mapContext);
        return jsonMapper.toJson(result);

    }

    /**
     * 新增订单
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
    @PostMapping("/add")
    private String addOrder(@RequestBody CustomOrderInfoDto customOrderInfoDto) {
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.toJson(this.orderFacade.dealerAddOrder(customOrderInfoDto));
    }
    /**
     * 上传设计图片、效果图片、安装图片等
     *
     * @param multipartFileList
     * @param fileType 1-设计图 2-效果图 3-安装图
     * @return
     */
    @PostMapping("/files")
    @ApiOperation(value = "上传设计图片、效果图片、安装图片", notes = "上传设计图片、效果图片、安装图片")
    private RequestResult uploadFile(
                                     @RequestBody List<MultipartFile> multipartFileList,
                                     @RequestParam(required = true) String fileType) {
        Map<String, String> result = new HashMap<>();
        if (multipartFileList == null || multipartFileList.size() == 0) {
            result.put("file", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
            return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_ERROR, result);
        }
        for (MultipartFile multipartFile : multipartFileList) {
            if (multipartFile == null) {
                result.put("file", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
            } else if (!FileMimeTypeUtil.isLegalImageType(multipartFile)) {
                result.put("file", AppBeanInjector.i18nUtil.getMessage("VALIDATE_ILLEGAL_ARGUMENT"));
            } else if (multipartFile.getSize() > 1024 * 1024 * AppBeanInjector.configuration.getUploadBackgroundMaxsize()) {
                return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.BIZ_FILE_SIZE_LIMIT_10031, LwxfStringUtils.format(AppBeanInjector.i18nUtil.getMessage("BIZ_FILE_SIZE_LIMIT_10031"), AppBeanInjector.configuration.getUploadBackgroundMaxsize()));
            }
            if (result.size() > 0) {
                return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, result);
            }
        }
        return this.orderFacade.addOrderFilesByType(multipartFileList,fileType);
    }
    /**
     * 修改订单信息
     *
     * @param id
     * @param mapContext
     * @return
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "经销商修改订单", notes = "经销商修改订单", response = CustomOrderInfoDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "订单数据", name = "customOrderInfoDto", dataTypeClass = CustomOrderInfoDto.class, paramType = "body")
    })
    private RequestResult factoryUpdateOrder(@PathVariable String id,
                                             @RequestBody MapContext mapContext) {
        return this.orderFacade.dealerUpdateOrder(id, mapContext);
    }
    /**
     * 查询订单信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "查询订单信息", notes = "查询订单信息", response = CustomOrderDto.class)
    private String dealerFindOrderInfo(@PathVariable String id) {
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();

        return jsonMapper.toJson(this.orderFacade.dealerFindOrderInfo(id));
    }

    /**
     * 删除订单信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除订单信息", notes = "删除订单信息", response = CustomOrderDto.class)
    private String deleteDealerOrderInfo(@PathVariable String id) {
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();

        return jsonMapper.toJson(this.orderFacade.deleteDealerOrderInfo(id));
    }

    /**
     * 售后申请
     *
     * @param aftersaleDto
     * @return
     */
    @ApiResponses({
            @ApiResponse(code = 200, message = "添加成功")
    })
    @ApiOperation(value = "售后申请", notes = "售后申请", response = AftersaleDto.class)
    @PostMapping("/add/aftersales")
    private String addAfterOrder(@RequestBody AftersaleDto aftersaleDto) {
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.toJson(this.orderFacade.addAfterOrder(aftersaleDto));
    }

}
