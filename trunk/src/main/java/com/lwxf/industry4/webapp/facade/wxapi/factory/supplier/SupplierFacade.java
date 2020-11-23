package com.lwxf.industry4.webapp.facade.wxapi.factory.supplier;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.supplier.Supplier;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SupplierFacade extends BaseFacade {

    RequestResult findAllSupplierList(Integer pageNum, Integer pageSize, MapContext mapContext);

    RequestResult addSupplier(Supplier supplier);

    RequestResult viewSupplierInfo(String id);

    RequestResult uploadImage(List<MultipartFile> multipartFile);

    RequestResult updateSupplier(String paymentSimpleId, MapContext map);
}
