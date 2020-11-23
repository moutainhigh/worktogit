package com.lwxf.industry4.webapp.controller.admin.factory.productattribute;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.productattribute.ProductAttributeKeyDto;
import com.lwxf.industry4.webapp.domain.entity.productattribute.ProductAttributeKey;
import com.lwxf.industry4.webapp.domain.entity.productattribute.ProductAttributeValue;
import com.lwxf.industry4.webapp.facade.admin.factory.productattribute.ProductAttributeFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @version 1.0
 * @Auther: SunXianWei
 * @Date: 2020/7/29 10:38
 * @Description:产品属性
 */
@Api(value = "ProductAttributeController",tags = {"产品属性"})
@RestController
@RequestMapping(value = "/api/f/productattributes",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class ProductAttributeController {

    @Resource(name = "productAttributeFacade")
    private ProductAttributeFacade productAttributeFacade;


    @ApiOperation(value = "产品属性信息列表",notes = "",response = ProductAttributeKey.class)
    @GetMapping
    private String findProductAttributes(@ApiParam(value = "启用状态 0-未启用 1-已启用")@RequestParam(required = false)String status,
                                         @ApiParam(value = "属性名称")@RequestParam(required = false)String name){
        String branchId= WebUtils.getCurrBranchId();
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        return jsonMapper.toJson(this.productAttributeFacade.findProductAttributes(branchId,status,name));
    }
    @ApiOperation(value = "添加属性和属性值",notes = "",response = ProductAttributeKeyDto.class)
    @PostMapping("/keys_values")
    private RequestResult addProductAttribute(@RequestBody List<ProductAttributeKeyDto> attributeKeys){
        return this.productAttributeFacade.addProductAttribute(attributeKeys);

    }
    @ApiOperation(value = "添加属性",notes = "",response = ProductAttributeKey.class)
    @PostMapping("/keys")
    private RequestResult addProductAttributekey(@RequestBody ProductAttributeKey attributeKey){
        String branchId= WebUtils.getCurrBranchId();
        attributeKey.setBranchId(branchId);
        return this.productAttributeFacade.addProductAttributekey(attributeKey);

    }
    @ApiOperation(value = "添加属性值",notes = "",response = ProductAttributeValue.class)
    @PostMapping("/{keyId}/texts")
    private RequestResult addProductAttributeValue(@PathVariable String keyId
                                            ,@RequestParam String attributeValues){
        return this.productAttributeFacade.addProductAttributeValue(keyId,attributeValues);

    }
    @ApiOperation(value = "修改属性",notes = "",response = ProductAttributeKey.class)
    @PutMapping("/{keyId}")
    private RequestResult updateProductAttribute(@PathVariable String keyId,@RequestBody MapContext mapContext){
        return this.productAttributeFacade.updateProductAttribute(keyId,mapContext);
    }
    @ApiOperation(value = "删除属性",notes = "",response = ProductAttributeKey.class)
    @DeleteMapping("/{keyId}")
    private RequestResult deleteProductAttribute(@PathVariable String keyId){
        return this.productAttributeFacade.deleteProductAttribute(keyId);
    }
    @ApiOperation(value = "修改属性值",notes = "",response = ProductAttributeValue.class)
    @PutMapping("/{valueId}/update")
    private RequestResult updateProductAttributeValue(@PathVariable String valueId,@RequestBody MapContext mapContext){
        return this.productAttributeFacade.updateProductAttributeValue(valueId,mapContext);
    }
    @ApiOperation(value = "删除属性值",notes = "",response = ProductAttributeValue.class)
    @DeleteMapping("/{valueId}/delete")
    private RequestResult deleteProductAttributeValue(@PathVariable String valueId){
        return this.productAttributeFacade.deleteProductAttributeValue(valueId);
    }

}
