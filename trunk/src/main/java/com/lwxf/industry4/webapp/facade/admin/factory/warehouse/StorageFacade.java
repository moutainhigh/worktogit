package com.lwxf.industry4.webapp.facade.admin.factory.warehouse;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.warehouse.Storage;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/13/013 15:33
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface StorageFacade extends BaseFacade {
	RequestResult addStorage(Storage storage);

	RequestResult findStorageList(MapContext mapContext, Integer pageNum, Integer pageSize);

	RequestResult updateStorage(String id, MapContext mapContext);

	RequestResult deleteById(String id);

	RequestResult findAllProduct();
}
