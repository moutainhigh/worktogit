package com.lwxf.industry4.webapp.facade.admin.factory.dealer;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerLogistics;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：pc端经销商物流公司管理Facade
 *
 * @author：DJL
 * @create：2019/12/2 10:16
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface DealerLogisticsFacade extends BaseFacade {
    RequestResult findAllWxLogistics(Integer pageNum, Integer pageSize, MapContext mapContext);

    RequestResult findWxLogisticsInfo(String logisticsId);

    RequestResult findDefaultWxLogistics(String cid);

    RequestResult addWxLogistics(DealerLogistics dealerLogistics);

    RequestResult updateWxLogistics(MapContext mapContext);

    RequestResult deleteWxLogistics(String logisticsId);

    Integer countByCid(String cid);

    RequestResult findAllFactoryLogistics(MapContext mapContext);
}
