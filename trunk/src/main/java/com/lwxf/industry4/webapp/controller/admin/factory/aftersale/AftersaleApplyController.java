package com.lwxf.industry4.webapp.controller.admin.factory.aftersale;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.DataValidatorUtils;
import com.lwxf.industry4.webapp.common.enums.aftersale.AftersaleCoupleBackStatus;
import com.lwxf.industry4.webapp.common.enums.aftersale.AftersaleFrom;
import com.lwxf.industry4.webapp.common.enums.aftersale.AftersaleType;
import com.lwxf.industry4.webapp.domain.dto.aftersale.AftersaleDto;
import com.lwxf.industry4.webapp.domain.dto.aftersale.AftersaleReportDto;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.aftersale.AftersaleStatus;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.uniquecode.UniquneCodeGenerator;
import com.lwxf.industry4.webapp.common.utils.FileMimeTypeUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.aftersale.AftersaleApply;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.aftersale.AftersaleApplyFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/1/8/008 9:56
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value = "AftersaleApplyController", tags = {"F端后台管理接口:售后反馈管理"})
@RestController(value = "fAftersaleApplyController")
@RequestMapping(value = "/api/f/aftersales", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class AftersaleApplyController {
    @Resource(name = "fAftersaleApplyFacade")
    private AftersaleApplyFacade aftersaleApplyFacade;

    /**
     * 查询售后列表，不指定售后单类型，默认查询售后反馈单列表
     * @param type 售后单类型
     * @param status 售后单状态
     * @param no 售后单号
     * @param orderNo 售后订单编号
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功", response = AftersaleDto.class)
    })
    @ApiOperation(value = "查询售后单列表", notes = "查询售后单列表")
    @GetMapping
    public String findListAftersale(@RequestParam(required = false) Integer type,
                                            @RequestParam(required = false) Integer status,
                                            @RequestParam(required = false) String no,
                                            @RequestParam(required = false) String orderNo,
                                            @RequestParam(required = false) Integer pageNum,
                                            @RequestParam(required = false) Integer pageSize) {

        if (null == pageSize) {
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        if (null == pageNum) {
            pageNum = 1;
        }
//        if (type == null) {
//            type = AftersaleType.FANKUI.getValue(); // 不指定售后单类型，默认查询售后反馈单
//        }
        MapContext mapContext = this.createMapContext(type, status, no, orderNo);
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        return jsonMapper.toJson(this.aftersaleApplyFacade.findListAftersale(mapContext, pageNum, pageSize));
    }

    /**
     * 售后反馈首页顶部简单报表数据
     * @return
     */
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功", response = AftersaleReportDto.class)
    })
    @ApiOperation(value = "查询售后反馈简单报表", notes = "查询售后反馈简单报表")
    @GetMapping("/coupleBack/report")
    public RequestResult aftersaleReport() {
        return this.aftersaleApplyFacade.findAftersaleReport();
    }


    @ApiOperation(value = "查询售后详情", notes = "查询售后单详情")
    @GetMapping("/detail/{id}")
    public RequestResult findAftersaleDetail(@PathVariable(value = "id") String id) {
        return this.aftersaleApplyFacade.findAftersalesDetail(id);
    }

    /**
     * 新增售后单
     *
     * @param orderId
     * @param aftersaleApply
     * @return
     */
    @PostMapping("{orderId}")
    public RequestResult addAftersale(@PathVariable String orderId,
                                       @RequestBody AftersaleApply aftersaleApply) {
        aftersaleApply.setCustomOrderId(orderId);
        aftersaleApply.setCreated(DateUtil.getSystemDate());
        aftersaleApply.setCreator(WebUtils.getCurrUserId());
        aftersaleApply.setBranchId(WebUtils.getCurrBranchId());
        aftersaleApply.setChargeAmount(new BigDecimal(0)); // 默认收费金额为0
        aftersaleApply.setCharge(false); // 默认免费
        aftersaleApply.setStatus(AftersaleStatus.WAIT.getValue());
        aftersaleApply.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.AFTERSALE_APPLY_NO));
        RequestResult result = aftersaleApply.validateFields();
        if (result != null) {
            return result;
        }
        return this.aftersaleApplyFacade.addAftersale(orderId, aftersaleApply);
    }

    /**
     * 添加售后反馈
     * @param orderId
     * @param aftersaleApply
     * @return
     */
    @ApiOperation(value = "添加售后反馈", notes = "添加售后反馈")
    @PostMapping("/coupleBack/{orderId}")
    public RequestResult addAftersaleCoupleBack(@PathVariable String orderId,
                                       @RequestBody AftersaleApply aftersaleApply) {
        aftersaleApply.setCustomOrderId(orderId);
        aftersaleApply.setCreated(DateUtil.getSystemDate());
        aftersaleApply.setCreator(WebUtils.getCurrUserId());
        aftersaleApply.setBranchId(WebUtils.getCurrBranchId());
        aftersaleApply.setType(AftersaleType.FANKUI.getValue()); // 反馈单
        aftersaleApply.setChargeAmount(new BigDecimal(0)); // 默认收费金额为0
        aftersaleApply.setCharge(false); // 默认免费
        aftersaleApply.setFromType(AftersaleFrom.FACTORY.getValue()); // 售后来源工长
        if (aftersaleApply.getStatus() == null) {
            aftersaleApply.setStatus(AftersaleCoupleBackStatus.PROCESSING.getValue()); // 工厂添加反馈默认处理中
        }
        aftersaleApply.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.AFTERSALE_APPLY_NO));
        RequestResult result = aftersaleApply.validateFields();
        if (result != null) {
            return result;
        }
        return this.aftersaleApplyFacade.addAftersale(orderId, aftersaleApply);
    }


    /**
     * 批量上传售后图片
     *
     * @param id
     * @param multipartFileList
     * @return
     */
    @ApiOperation(value = "添加售后反馈图片", notes = "添加售后反馈图片")
    @PostMapping("{id}/files")
    public RequestResult uploadImgFile(@PathVariable String id,
                                        @RequestBody List<MultipartFile> multipartFileList) {
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
        return this.aftersaleApplyFacade.uploadImgFile(multipartFileList, id);
    }

    /**
     * 删除售后单下的图片
     *
     * @param id
     * @param fileId
     * @return
     */
    @ApiOperation(value = "删除售后反馈图片", notes = "删除售后反馈图片")
    @DeleteMapping("{id}/files/{fileId}")
    public RequestResult deleteFileImg(@PathVariable String id,
                                        @PathVariable String fileId) {
        return this.aftersaleApplyFacade.deleteFileImg(id, fileId);
    }

    /**
     * 修改售后单
     *
     * @param id
     * @param mapContext
     * @return
     */
    @ApiOperation(value = "更新售后反馈信息", notes = "更新售后反馈信息")
    @PutMapping("{id}")
    public RequestResult updateAftersaleApply(@PathVariable String id,
                                               @RequestBody MapContext mapContext) {
        // 原有验证的字段信息 "type","notes","status","checker","checkTime","result","information","reason","resultOrderId","is_charge","charge_amount"
//        RequestResult result = AftersaleApply.validateFields(mapContext);
//        if (result != null) {
//            return result;
//        }
        // 新的验证字段信息
        if(mapContext.containsKey("status")) {
            if (!DataValidatorUtils.isInteger1(mapContext.getTypedValue("status",String.class))) {
                ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT, AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
            }

            if (mapContext.getTypedValue("status", Integer.class).equals(AftersaleCoupleBackStatus.COMPLETED.getValue())) {
                mapContext.put("finishDate", DateUtil.getSystemDate());
            }
        }
        if(mapContext.containsKey("notes")) {
            if (LwxfStringUtils.getStringLength(mapContext.getTypedValue("notes",String.class)) > 500) {
                ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_LENGTH_TOO_LONG, AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
            }
        }
        if(mapContext.containsKey("result")) {
            if (LwxfStringUtils.getStringLength(mapContext.getTypedValue("result",String.class)) > 500) {
                ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_LENGTH_TOO_LONG, AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
            }
        }

        return this.aftersaleApplyFacade.updateAftersaleApply(id, mapContext);
    }

    /**
     * 删除售后单
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除售后反馈单", notes = "删除售后反馈单")
    @DeleteMapping("{id}")
    public RequestResult deleteAftersaleApply(@PathVariable String id) {
        return this.aftersaleApplyFacade.deleteAftersaleApply(id);
    }

    private MapContext createMapContext(Integer type, Integer status, String no, String orderNo) {
        MapContext mapContext = MapContext.newOne();
        if (type != null && type != -1) {
            mapContext.put("type", type);
        }
        if (status != null && status != -1) {
            mapContext.put(WebConstant.KEY_ENTITY_STATUS, status);
        }
        if (no != null && !no.trim().equals("")) {
            mapContext.put(WebConstant.STRING_NO, no);
        }
        if (orderNo != null && !orderNo.trim().equals("")) {
            mapContext.put("orderNo", orderNo);
        }
        return mapContext;
    }
}
