package com.lwxf.industry4.webapp.facade.admin.factory.dealer;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAddress;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：PC端经销商收货地址管理Facade
 *
 * @author：DJL
 * @create：2019/12/2 10:16
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface DealerAddressFacade extends BaseFacade {

    RequestResult findAllWxAddress(Integer pageNum, Integer pageSize, MapContext mapContext);

    RequestResult findWxAddressInfo(String addressId);

    RequestResult findDefaultWxAddress(String cid);

    RequestResult addWxAddress(DealerAddress dealerAddress);

    RequestResult updateWxAddress(MapContext mapContext);

    RequestResult deleteWxAddress(String addressId);

    Integer countByCid(String cid);
}
