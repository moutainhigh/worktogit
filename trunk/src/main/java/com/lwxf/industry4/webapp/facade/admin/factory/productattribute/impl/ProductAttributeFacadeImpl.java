package com.lwxf.industry4.webapp.facade.admin.factory.productattribute.impl;

import com.lwxf.industry4.webapp.bizservice.productattribute.ProductAttributeKeyService;
import com.lwxf.industry4.webapp.bizservice.productattribute.ProductAttributeValueService;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.productattribute.ProductAttributeKeyDto;
import com.lwxf.industry4.webapp.domain.entity.productattribute.ProductAttributeKey;
import com.lwxf.industry4.webapp.domain.entity.productattribute.ProductAttributeValue;
import com.lwxf.industry4.webapp.facade.admin.factory.productattribute.ProductAttributeFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @version 1.0
 * @Auther: SunXianWei
 * @Date: 2020/7/29 10:39
 * @Description:
 */
@Component("productAttributeFacade")
public class ProductAttributeFacadeImpl extends BaseFacadeImpl implements ProductAttributeFacade {

    @Resource(name = "productAttributeKeyService")
    private ProductAttributeKeyService productAttributeKeyService;
    @Resource(name = "productAttributeValueService")
    private ProductAttributeValueService productAttributeValueService;
    @Override
    public RequestResult findProductAttributes(String branchId,String status,String name) {
        MapContext mapContext=MapContext.newOne();
        mapContext.put("branchId",branchId);
        if(status!=null&&!status.equals("")){
            mapContext.put("status",status);
        }
        if(name!=null&&!name.equals("")){
            mapContext.put("attributeName",name);
        }
        mapContext.put("del_flag",0);
        List<ProductAttributeKeyDto> listBybranchId = this.productAttributeKeyService.findListBybranchId(mapContext);
        if(listBybranchId!=null){
            for(ProductAttributeKeyDto keyDto:listBybranchId){
                String keyid=keyDto.getId();
                MapContext map=MapContext.newOne();
                map.put("productAttributeKeyId",keyid);
                if(status!=null&&status.equals("1")) {
                    map.put("status", 1);
                }
                map.put("delFlag",0);
                List<ProductAttributeValue> attributeValueList=this.productAttributeValueService.findByKeyId(map);
                keyDto.setAttributeValueList(attributeValueList);
            }
        }
        return ResultFactory.generateRequestResult(listBybranchId);
    }

    @Override
    @Transactional(value = "transactionManager",rollbackFor = Exception.class)
    public RequestResult addProductAttribute(List<ProductAttributeKeyDto> attributeKeys) {
        if(attributeKeys!=null&&attributeKeys.size()>0) {
            for (ProductAttributeKeyDto attributeKey : attributeKeys) {
                attributeKey.setCreator(WebUtils.getCurrUserId());
                attributeKey.setCreated(new Date());
                attributeKey.setDelFlag(0);
                if (attributeKey.getStatus() == null || attributeKey.getStatus().equals("")) {
                    attributeKey.setStatus(1);
                }
                this.productAttributeKeyService.add(attributeKey);
                String attributeValues = attributeKey.getAttributeValues();
                if (attributeKey != null && !attributeKey.equals("")) {
                    String[] split = attributeValues.split(",");
                    if (split != null && split.length > 0) {
                        for (String value : split) {
                            ProductAttributeValue productAttributeValue = new ProductAttributeValue();
                            productAttributeValue.setCreated(new Date());
                            productAttributeValue.setCreator(WebUtils.getCurrUserId());
                            productAttributeValue.setKeyName(attributeKey.getAttributeName());
                            productAttributeValue.setBranchId(WebUtils.getCurrBranchId());
                            productAttributeValue.setDelFlag(0);
                            productAttributeValue.setStatus(1);
                            productAttributeValue.setAttributeValue(value);
                            productAttributeValue.setProductAttributeKeyId(attributeKey.getId());
                            this.productAttributeValueService.add(productAttributeValue);
                        }
                    }
                }
            }
        }
        return ResultFactory.generateSuccessResult();
    }



    @Override
    @Transactional(value = "transactionManager",rollbackFor = Exception.class)
    public RequestResult addProductAttributekey(ProductAttributeKey attributeKey) {
        attributeKey.setCreator(WebUtils.getCurrUserId());
        attributeKey.setCreated(new Date());
        attributeKey.setDelFlag(0);
        if(attributeKey.getStatus()==null||attributeKey.getStatus().equals("")) {
            attributeKey.setStatus(1);
        }
        this.productAttributeKeyService.add(attributeKey);
        return ResultFactory.generateRequestResult(attributeKey);
    }

    @Override
    @Transactional(value = "transactionManager",rollbackFor = Exception.class)
    public RequestResult addProductAttributeValue(String keyId, String attributeValues) {
        ProductAttributeKey byId = this.productAttributeKeyService.findById(keyId);
        if(byId==null){
            return ResultFactory.generateResNotFoundResult();
        }
        if (attributeValues != null && !attributeValues.equals("")) {
            String[] split = attributeValues.split("，");
            if (split != null && split.length > 0) {
                for (String value : split) {
                    ProductAttributeValue productAttributeValue = new ProductAttributeValue();
                    productAttributeValue.setCreated(new Date());
                    productAttributeValue.setCreator(WebUtils.getCurrUserId());
                    productAttributeValue.setKeyName(byId.getAttributeName());
                    productAttributeValue.setBranchId(WebUtils.getCurrBranchId());
                    productAttributeValue.setDelFlag(0);
                    productAttributeValue.setStatus(1);
                    productAttributeValue.setAttributeValue(value);
                    productAttributeValue.setProductAttributeKeyId(keyId);
                    this.productAttributeValueService.add(productAttributeValue);
                }
            }
        }
        MapContext mapContext=MapContext.newOne();
        mapContext.put("productAttributeKeyId",keyId);
        mapContext.put("delFlag",0);
        List<ProductAttributeValue> byKeyId = this.productAttributeValueService.findByKeyId(mapContext);
        return ResultFactory.generateRequestResult(byKeyId);
    }

    @Override
    @Transactional(value = "transactionManager",rollbackFor = Exception.class)
    public RequestResult updateProductAttribute(String keyId, MapContext mapContext) {
        mapContext.put("id",keyId);
        this.productAttributeKeyService.updateByMapContext(mapContext);
        return ResultFactory.generateSuccessResult();
    }

    @Override
    @Transactional(value = "transactionManager",rollbackFor = Exception.class)
    public RequestResult deleteProductAttribute(String keyId) {
        MapContext mapContext=MapContext.newOne();
        mapContext.put("id",keyId);
        mapContext.put("status",0);
        mapContext.put("delFlag",1);
        this.productAttributeKeyService.updateByMapContext(mapContext);
        return ResultFactory.generateSuccessResult();
    }
    @Override
    @Transactional(value = "transactionManager",rollbackFor = Exception.class)
    public RequestResult updateProductAttributeValue(String valueId, MapContext mapContext) {
        ProductAttributeValue byId = this.productAttributeValueService.findById(valueId);
        if(byId==null){
            return ResultFactory.generateResNotFoundResult();
        }
        mapContext.put("id",valueId);
        this.productAttributeValueService.updateByMapContext(mapContext);

        MapContext map=MapContext.newOne();
        map.put("productAttributeKeyId",byId.getProductAttributeKeyId());
        map.put("delFlag",0);
        List<ProductAttributeValue> byKeyId = this.productAttributeValueService.findByKeyId(map);
        return ResultFactory.generateRequestResult(byKeyId);
    }

    @Override
    @Transactional(value = "transactionManager",rollbackFor = Exception.class)
    public RequestResult deleteProductAttributeValue(String valueId) {
        ProductAttributeValue byId = this.productAttributeValueService.findById(valueId);
        if(byId==null){
            return ResultFactory.generateResNotFoundResult();
        }
        MapContext mapContext=MapContext.newOne();
        mapContext.put("id",valueId);
        mapContext.put("status",0);
        mapContext.put("delFlag",1);
        this.productAttributeValueService.updateByMapContext(mapContext);
        MapContext map=MapContext.newOne();
        map.put("productAttributeKeyId",byId.getProductAttributeKeyId());
        map.put("delFlag",0);
        List<ProductAttributeValue> byKeyId = this.productAttributeValueService.findByKeyId(map);
        return ResultFactory.generateRequestResult(byKeyId);
    }
}
