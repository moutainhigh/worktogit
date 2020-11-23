package com.lwxf.industry4.webapp.facade.admin.factory.warehouse;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.warehouse.StorageOutputInDto;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

import java.util.List;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/20/020 10:25
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface StorageOutputInFacade extends BaseFacade {
	RequestResult findStorageOutPutInList(MapContext mapContext,Integer pageNum,Integer pageSize);

	RequestResult addStorageInput(StorageOutputInDto storageOutputInDto);



	RequestResult addStorageOutput(StorageOutputInDto storageOutputInDto);

	RequestResult updateStorageOutputItem(String id, String itemId, MapContext mapContext);

	RequestResult cancelStorageOutput(String id);

	RequestResult updateStorageOutputInStatus(String id, List<MapContext> storageItems);
}
