package com.lwxf.industry4.webapp.facade.admin.factory.supplier;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.supplier.MaterialDto;
import com.lwxf.industry4.webapp.domain.entity.supplier.Material;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/8/1 0001 11:38
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface MaterialFacade extends BaseFacade {
	RequestResult findMaterialList(Integer pageNum,Integer pageSize,MapContext mapContext);

	RequestResult findMaterialInfo(String materialId);

	RequestResult addMaterial(MaterialDto materialDto);

	RequestResult updateMaterial(String materialId,MapContext material);

	RequestResult deleteMaterial(String materialId);

	RequestResult findSuppliersByMaterialId(String materialId);

	RequestResult materialImages(List<MultipartFile> multipartFileList);

}
