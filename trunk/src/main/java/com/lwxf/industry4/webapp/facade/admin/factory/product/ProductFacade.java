package com.lwxf.industry4.webapp.facade.admin.factory.product;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.product.Product;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/4/004 10:49
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface ProductFacade extends BaseFacade {
	RequestResult findProductList(MapContext mapContext,Integer pageNum,Integer pageSize);

	RequestResult addProduct(Product product);

	RequestResult updateProduct(MapContext mapContext,  String id);

	RequestResult uploadFileImg(String id, Integer type, List<MultipartFile> multipartFile);

	RequestResult findResourcesList(MapContext mapContent);

	RequestResult findProductInfo(String id);

	RequestResult deleteFile(String productId, String fileId);

}
