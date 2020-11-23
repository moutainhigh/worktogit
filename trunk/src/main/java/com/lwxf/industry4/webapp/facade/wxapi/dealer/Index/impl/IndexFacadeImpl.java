package com.lwxf.industry4.webapp.facade.wxapi.dealer.Index.impl;

import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.bizservice.product.ProductFilesService;
import com.lwxf.industry4.webapp.bizservice.product.ProductService;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.dto.dealer.WxDealerInfoDto;
import com.lwxf.industry4.webapp.domain.dto.product.ProductDto;
import com.lwxf.industry4.webapp.domain.entity.product.Product;
import com.lwxf.industry4.webapp.domain.entity.product.ProductFiles;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.wxapi.dealer.Index.IndexFacade;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("wxDealerIndexFacade")
public class IndexFacadeImpl extends BaseFacadeImpl implements IndexFacade {

    @Resource(name = "productService")
    private ProductService productService;
    @Resource(name = "productFilesService")
    private ProductFilesService productFilesService;
    @Resource(name = "customOrderService")
    private CustomOrderService customOrderService;
    @Override
    public RequestResult viewIndex(String companyId) {
        ProductDto prodDto = new ProductDto();
        WxDealerInfoDto resDto =customOrderService.selectDealerInfo(companyId);
        List<Product> listProd = productService.findProductsRecommend(companyId);
        //获取第一条
        if(listProd!=null&&listProd.size()>0){
            BeanUtils.copyProperties(listProd.get(0),prodDto);
            //获取产品图片
            ProductFiles pf = productFilesService.findOneByProductIdAndType(listProd.get(0).getId(),0);
            if(pf!=null)
                prodDto.setWxCover(pf.getFullPath());
        }
        resDto.setProduct(prodDto);
        return ResultFactory.generateRequestResult(resDto);
    }
}
