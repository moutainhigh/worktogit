package com.lwxf.industry4.webapp.facade.wxapi.factory.product;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/6/15/015 18:14
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface ProductFacade extends BaseFacade {
	RequestResult findProductList(Integer pageSize, Integer pageNum,String uid);

	RequestResult findProductInfo(String id);

	RequestResult updateLowerShelf(String id, Integer lowerShelf);
}
