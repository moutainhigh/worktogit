package com.lwxf.industry4.webapp.controller.admin.factory.warehouse;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.storage.FlowType;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.uniquecode.UniquneCodeGenerator;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.warehouse.StorageOutputInDto;
import com.lwxf.industry4.webapp.domain.entity.warehouse.StorageOutputInItem;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.warehouse.StorageOutputInFacade;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/20/020 10:21
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/api/f/storages/outputins", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class StorageOutputInController {
    @Resource(name = "storageOutputInFacade")
    private StorageOutputInFacade storageOutputInFacade;

    /**
     * 查询出库入库单列表
     *
     * @param storageId
     * @param no
     * @param type
     * @param flow
     * @param productId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping
    private String findStorageOutPutInList(@RequestParam(required = false) String storageId,
                                                  @RequestParam(required = false) String no,
                                                  @RequestParam(required = false) Integer type,
                                                  @RequestParam(required = false) Integer flow,
                                                  @RequestParam(required = false) String productId,
                                                  @RequestParam(required = false) Integer pageNum,
                                                  @RequestParam(required = false) Integer pageSize) {

        if (null == pageSize) {
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        if (null == pageNum) {
            pageNum = 1;
        }
        MapContext mapContext = this.createMapContent(storageId, no, type, flow, productId);
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        return jsonMapper.toJson(this.storageOutputInFacade.findStorageOutPutInList(mapContext, pageNum, pageSize));
    }

    private MapContext createMapContent(String storageId, String no, Integer type, Integer flow, String productId) {
        MapContext mapContext = MapContext.newOne();
        if (storageId != null && !storageId.trim().equals("")) {
            mapContext.put("storageId", storageId);
        }
        if (no != null && !no.trim().equals("")) {
            mapContext.put(WebConstant.STRING_NO, no);
        }
        if (type != null && type != -1) {
            mapContext.put("type", type);
        }
        if (flow != null && flow != -1) {
            mapContext.put("flow", flow);
        }
        if (productId != null && !productId.trim().equals("")) {
            mapContext.put("productId", productId);
        }
        return mapContext;
    }

    /**
     * 入库
     *
     * @param storageOutputInDto
     * @return
     */
    @PostMapping("/in")
    private RequestResult addStorageInput(@RequestBody StorageOutputInDto storageOutputInDto) {
        storageOutputInDto.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.STORAGE_OUTPUTIN_NO));
        if(storageOutputInDto.getCreator()==null||storageOutputInDto.getCreator().equals("")) {
            storageOutputInDto.setCreator(WebUtils.getCurrUserId());
        }
        if(storageOutputInDto.getCreated()==null||storageOutputInDto.getCreated().equals("")) {
            storageOutputInDto.setCreated(DateUtil.getSystemDate());
        }
        storageOutputInDto.setFlow(FlowType.WAREHOUSING.getValue());
       // storageOutputInDto.setStatus(StorageOutputInStatus.UNCERTAIN.getValue());
        storageOutputInDto.setBranchId(WebUtils.getCurrBranchId());
        RequestResult result = storageOutputInDto.validateFields();
        if (result != null) {
            return result;
        }
        return this.storageOutputInFacade.addStorageInput(storageOutputInDto);
    }

    /**
     * 确认出入库单
     */
    @PutMapping("/{id}")
    private RequestResult updateStorageOutputInStatus(@PathVariable String id,
                                                      @RequestBody List<MapContext> storageItems) {
        return this.storageOutputInFacade.updateStorageOutputInStatus(id,storageItems);
    }

    /**
     * 出库单创建
     *
     * @param storageOutputInDto
     * @return
     */
    @PostMapping("/out")
    private RequestResult addStorageOutput(@RequestBody StorageOutputInDto storageOutputInDto) {
        storageOutputInDto.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.STORAGE_OUTPUTIN_NO));
        if(storageOutputInDto.getCreator()==null||storageOutputInDto.getCreator().equals("")) {
            storageOutputInDto.setCreator(WebUtils.getCurrUserId());
        }
        if(storageOutputInDto.getCreated()==null||storageOutputInDto.getCreated().equals("")) {
            storageOutputInDto.setCreated(DateUtil.getSystemDate());
        }
        storageOutputInDto.setFlow(FlowType.OUT_STOCK.getValue());
        //storageOutputInDto.setStatus(StorageOutputInStatus.UNCERTAIN.getValue());
        RequestResult result = storageOutputInDto.validateFields();
        if (result != null) {
            return result;
        }
        return this.storageOutputInFacade.addStorageOutput(storageOutputInDto);
    }

    /**
     * 修改入库单下的产品位置
     *
     * @param id
     * @param itemId
     * @param mapContext
     * @return
     */
    @PutMapping("{id}/items/{itemId}")
    private RequestResult updateStorageOutputItem(@PathVariable String id, @PathVariable String itemId, @RequestBody MapContext mapContext) {
        RequestResult result = StorageOutputInItem.validateFields(mapContext);
        if (result != null) {
            return result;
        }
        return this.storageOutputInFacade.updateStorageOutputItem(id, itemId, mapContext);
    }

    /**
     * 取消出库单
     *
     * @param id
     * @return
     */
    @PutMapping("{id}/cancel")
    private RequestResult cancelStorageOutput(@PathVariable String id) {
        return this.storageOutputInFacade.cancelStorageOutput(id);
    }
}
