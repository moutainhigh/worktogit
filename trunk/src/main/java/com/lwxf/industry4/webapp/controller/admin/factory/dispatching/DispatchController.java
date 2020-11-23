package com.lwxf.industry4.webapp.controller.admin.factory.dispatching;

import io.swagger.annotations.*;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.FileMimeTypeUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.dispatch.DispatchBillDto;
import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.dispatching.DispatchFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/25/025 13:53
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/api/f/dispatchs", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
@Api(value = "DispatchController",tags = "F端后台管理接口：发货管理")
public class DispatchController {
    @Resource(name = "dispatchFacade")
    private DispatchFacade dispatchFacade;

    /**
     * 查询发货单列表
     *
     * @param orderNo
     * @param logisticsNo
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询发货单列表",notes = "查询发货单列表",response = DispatchBillDto.class)
    private String findDispatchList(@RequestParam(required = false) String orderNo,
                                           @RequestParam(required = false)@ApiParam(value = "物流编号") String logisticsNo,
									       @RequestParam(required = false)@ApiParam(value = "物流公司id") String logisticsCompanyId,
                                           @RequestParam(required = false)@ApiParam(value = "状态") Integer status,
                                           @RequestParam(required = false)@ApiParam(value = "物流单id") String id,
									       @RequestParam(required = false)@ApiParam(value = "经销商名称") String companyName,
									       @RequestParam(required = false)@ApiParam(value = "收货人名称") String consignee,
									       @RequestParam(required = false)@ApiParam(value = "收货人电话") String consigneeTel,
									       @RequestParam(required = false)@ApiParam(value = "录入人") String creatorName,
									       @RequestParam(required = false)@ApiParam(value = "收货地址") String address,
									       @RequestParam(required = false)@ApiParam(value = "发货开始时间") String startTime,
									       @RequestParam(required = false)@ApiParam(value = "发货结束时间") String endTime,
                                           @RequestParam(required = false)@ApiParam(value = "页码") Integer pageNum,
                                           @RequestParam(required = false)@ApiParam(value = "页面大小") Integer pageSize) {
    	JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        if (null == pageSize) {
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        if (null == pageNum) {
            pageNum = 1;
        }
        MapContext mapContext = MapContext.newOne();
		if (orderNo != null && !orderNo.trim().equals("")) {
			mapContext.put("orderNo", orderNo);
		}
		if (logisticsNo != null && !logisticsNo.trim().equals("")) {
			mapContext.put("logisticsNo", logisticsNo);
		}
		if (logisticsCompanyId != null && !logisticsCompanyId.trim().equals("")) {
			mapContext.put("logisticsCompanyId", logisticsCompanyId);
		}
		if (companyName != null && !companyName.trim().equals("")) {
			mapContext.put("companyName", companyName);
		}
		if (consignee != null && !consignee.trim().equals("")) {
			mapContext.put("consignee", consignee);
		}
		if (consigneeTel != null && !consigneeTel.trim().equals("")) {
			mapContext.put("consigneeTel", consigneeTel);
		}
		if (creatorName != null && !creatorName.trim().equals("")) {
			mapContext.put("creatorName", creatorName);
		}
		if (address != null && !address.trim().equals("")) {
			mapContext.put("address", address);
		}
		if (startTime != null && !startTime.trim().equals("")) {
			mapContext.put("startTime", startTime);
		}
		if (endTime != null && !endTime.trim().equals("")) {
			mapContext.put("endTime", endTime);
		}
		if (status != null) {
			mapContext.put(WebConstant.KEY_ENTITY_STATUS, status);
		}
		if(id!=null){
			mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		}
        return jsonMapper.toJson(this.dispatchFacade.findDispatchList(mapContext, pageNum, pageSize));
    }

    /**
     * 新增发货单 并同时发货
     *
     * @param dispatchBillDto
     * @return
     */
    @ApiOperation(value = "新增发货单 并同时发货",notes = "新增发货单 并同时发货" )
    @PostMapping
    private RequestResult addDispatch(@RequestBody DispatchBillDto dispatchBillDto) {
        return this.dispatchFacade.addDispatch(dispatchBillDto);
    }

    /**
     * 更新发货单
     * @return
     */
    @ApiOperation(value = "更新发货单",notes = "发货单更新")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "配送单信息")
    })
    @PutMapping("/{id}")
    private RequestResult updateDispatch(@PathVariable String id,@RequestBody MapContext mapContext) {
        return this.dispatchFacade.updateDispatch(mapContext,id);
    }
    /**
     * 发货单详情
     *
     * @param
     * @return
     */
    @GetMapping("/{dispatchBillId}/info")
    @ApiOperation(value = "发货单详情",notes = "发货单详情")
    private String findDispatchBillInfo(@PathVariable String dispatchBillId) {
        //String branchId=WebUtils.getCurrBranchId();
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        return jsonMapper.toJson(this.dispatchFacade.findDispatchBillInfo(dispatchBillId));
    }

    /**
     * 上传发货记录附件
     * @param id
     * @param multipartFileList
     * @return
     */
    @PostMapping("/{id}/files")
    @ApiOperation(value = "上传发货记录附件",notes = "上传发货记录附件",response = UploadFiles.class)
    private RequestResult uploadFiles(@PathVariable@ApiParam(value = "发货单ID 或者 dispatchId") String id, @RequestBody List<MultipartFile> multipartFileList){
        Map<String, String> result = new HashMap<>();
        if (multipartFileList == null || multipartFileList.size() == 0) {
            result.put("file", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, result);
        }
        for (MultipartFile multipartFile : multipartFileList) {
            if (multipartFile == null) {
                result.put("file", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
            } else if (!FileMimeTypeUtil.isLegalImageType(multipartFile)) {
                result.put("file", AppBeanInjector.i18nUtil.getMessage("VALIDATE_ILLEGAL_ARGUMENT"));
            } else if (multipartFile.getSize() > 1024 * 1024 * AppBeanInjector.configuration.getUploadBackgroundMaxsize()) {
                return ResultFactory.generateErrorResult(ErrorCodes.BIZ_FILE_SIZE_LIMIT_10031, LwxfStringUtils.format(AppBeanInjector.i18nUtil.getMessage("BIZ_FILE_SIZE_LIMIT_10031"), AppBeanInjector.configuration.getUploadBackgroundMaxsize()));
            }
            if (result.size() > 0) {
                return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, result);
            }
        }
        return this.dispatchFacade.uploadFiles(id, multipartFileList);
    }

    /**
     * 删除发货单资源文件
     * @param fileId
     * @return
     */
    @DeleteMapping("/files/{fileId}")
    @ApiOperation(value = "删除发货单附件",notes = "删除发货单附件")
    private RequestResult deleteFile(@PathVariable@ApiParam(value = "资源文件ID") String fileId){
        return this.dispatchFacade.deleteFile(fileId);
    }

    /**
     * 删除发货单
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    private RequestResult deleteById(@PathVariable String id) {
        return this.dispatchFacade.deleteById(id);
    }


    /**
     * 发货信息统计
     *
     * @param
     * @return
     */
    @GetMapping("/count")
    @ApiOperation(value = "发货信息统计",notes = "发货信息统计")
    private RequestResult findDispatchBillCount() {
        String branchId=WebUtils.getCurrBranchId();
        return this.dispatchFacade.findDispatchBillCount(branchId);
    }

    private MapContext createMapContent(String orderNo, String logisticsNo, Integer status,String id) {
        MapContext mapContext = MapContext.newOne();
        if (orderNo != null && !orderNo.trim().equals("")) {
            mapContext.put("orderNo", orderNo);
        }
        if (logisticsNo != null && !logisticsNo.trim().equals("")) {
            mapContext.put("logisticsNo", logisticsNo);
        }
        if (status != null) {
            mapContext.put(WebConstant.KEY_ENTITY_STATUS, status);
        }
        if(id!=null){
            mapContext.put(WebConstant.KEY_ENTITY_ID,id);
        }
        return mapContext;
    }
}
