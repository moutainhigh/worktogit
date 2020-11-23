package com.lwxf.industry4.webapp.facade.wxapi.dealer.company;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerLogistics;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：B端微信小程序经销商物流公司管理Facade
 *
 * @author：DJL
 * @create：2019/11/29 10:26
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface BWxLogisticsFacade extends BaseFacade {
    RequestResult findAllWxLogistics(Integer pageNum, Integer pageSize, MapContext mapContext);

    RequestResult findWxLogisticsInfo(String logisticsId);

    RequestResult findDefaultWxLogistics(String cid);

    RequestResult addWxLogistics(DealerLogistics dealerLogistics);

    RequestResult updateWxLogistics(MapContext mapContext);

    RequestResult deleteWxLogistics(String logisticsId);

    Integer countByCid(String cid);

    RequestResult findAllFactoryLogistics(MapContext mapContext);
}
