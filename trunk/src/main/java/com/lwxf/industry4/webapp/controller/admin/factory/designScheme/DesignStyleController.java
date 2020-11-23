package com.lwxf.industry4.webapp.controller.admin.factory.designScheme;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.design.DesignStyle;
import com.lwxf.industry4.webapp.facade.admin.factory.design.DesignStyleFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/2/25/025 14:04
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/api/f/designs/styles", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class DesignStyleController {


    @Resource(name = "designStyleFacade")
    private DesignStyleFacade designStyleFacade;

    /**
     * 根据条件查询全部的风格
     *
     * @param name
     * @param disabled
     * @return
     */
    @GetMapping
    private RequestResult findStyleList(@RequestParam(required = false) String name,
                                        @RequestParam(required = false) Boolean disabled) {
        MapContext mapContext = this.createMapContext(name, disabled);
        return this.designStyleFacade.findStyleList(mapContext);
    }

    /**
     * 新增设计风格
     *
     * @param designStyle
     * @return
     */
    @PostMapping
    private RequestResult addStyle(@RequestBody DesignStyle designStyle) {
        RequestResult result = designStyle.validateFields();
        if (result != null) {
            return result;
        }
        return this.designStyleFacade.addStyle(designStyle);
    }

    /**
     * 修改风格信息
     *
     * @param id
     * @param mapContext
     * @return
     */
    @PutMapping("{id}")
    private RequestResult updateStyle(@PathVariable String id,
                                      @RequestBody MapContext mapContext) {
        RequestResult result = DesignStyle.validateFields(mapContext);
        if (result != null) {
            return result;
        }
        return this.designStyleFacade.updateStyle(id, mapContext);
    }

    /**
     * 删除风格
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    private RequestResult deleteStyle(@PathVariable String id){
        return this.designStyleFacade.deleteStyle(id);
    }

    private MapContext createMapContext(String name, Boolean disabled) {
        MapContext mapContext = new MapContext();
        if (name != null && !name.trim().equals("")) {
            mapContext.put(WebConstant.KEY_ENTITY_NAME, name);
        }
        if (disabled != null) {
            mapContext.put(WebConstant.KEY_ENTITY_DISABLED, disabled);
        }
        return mapContext;
    }

}
