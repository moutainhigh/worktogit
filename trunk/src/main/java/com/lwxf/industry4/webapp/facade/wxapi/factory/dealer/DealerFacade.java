package com.lwxf.industry4.webapp.facade.wxapi.factory.dealer;

import org.springframework.web.multipart.MultipartFile;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.company.Company;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/6 0006 15:18
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */

public interface DealerFacade extends BaseFacade {
	RequestResult findWxDealers(String branchId, Integer pageNum, Integer pageSize, MapContext params);

	RequestResult findWxDealerInfo(String branchId, String dealerId);



	RequestResult addDealerShopCoverImage(MultipartFile multipartFile);

	RequestResult addWxDealer(Company company,String fileId,String uid,Integer status);

	RequestResult updateWxDealerInfo(String dealerId, MapContext mapContext);

	RequestResult companyOrderInfoCount(String dealerId, String yearTime);
}
