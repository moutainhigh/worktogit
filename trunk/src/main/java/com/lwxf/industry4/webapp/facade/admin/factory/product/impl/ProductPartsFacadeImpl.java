package com.lwxf.industry4.webapp.facade.admin.factory.product.impl;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.customorder.OrderProductPartsService;
import com.lwxf.industry4.webapp.bizservice.product.ProductPartsService;
import com.lwxf.industry4.webapp.bizservice.system.BasecodeService;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.product.ProductPartsDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderProductParts;
import com.lwxf.industry4.webapp.domain.entity.product.ProductParts;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.product.ProductPartsFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/8/30 0030 10:53
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("productPartsFacade")
public class ProductPartsFacadeImpl extends BaseFacadeImpl implements ProductPartsFacade {
	@Resource(name = "productPartsService")
	private ProductPartsService productPartsService;
	@Resource(name = "basecodeService")
	private BasecodeService basecodeService;
	@Resource(name = "orderProductPartsService")
	private OrderProductPartsService orderProductPartsService;

	@Override
	public RequestResult findProducts(String branchId) {
		List result = new ArrayList<>();
		String type="orderProductType";
		Integer delFlag=0;
		List<Basecode> byType =this.basecodeService.findByTypeAndDelFlag(type,delFlag);
		for (Basecode basecode:byType){
			Integer productType=Integer.valueOf(basecode.getCode()) ;
			List<ProductPartsDto> productPartsDtos=this.productPartsService.findByProductType(productType,branchId);
			List list=new ArrayList();
			if(productPartsDtos!=null&&productPartsDtos.size()>0) {
				for (ProductPartsDto partsDto : productPartsDtos) {
					MapContext mapContext = MapContext.newOne();
					String parts = partsDto.getPartsTypeName() + "-" + partsDto.getProduceTypeName();
					String partsId = partsDto.getId();
					mapContext.put("parts", parts);
					mapContext.put("partsId", partsId);
					mapContext.put("orderPartsIdentify", partsDto.getOrderPartsIdentify());
					mapContext.put("productType", partsDto.getProductType());
					mapContext.put("produceType", partsDto.getProduceType());
					mapContext.put("partsType", partsDto.getPartsType());
					list.add(mapContext);
				}
			}
			MapContext mapContext=MapContext.newOne();
			mapContext.put("name",basecode.getValue());
			mapContext.put("code",productType);
			mapContext.put("value",list);
			result.add(mapContext);
		}
		return ResultFactory.generateRequestResult(result);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addProductParts(ProductParts productParts) {
		ProductParts parts=this.productPartsService.findByType(productParts.getProductType(),productParts.getPartsType(),productParts.getProduceType());
		if(parts!=null){
			MapContext map=MapContext.newOne();
			map.put("id",parts.getId());
			map.put("updated", DateUtil.getSystemDate());
			map.put("updator", WebUtils.getCurrUserId());
			map.put("orderPartsIdentify",productParts.getOrderPartsIdentify());
			this.productPartsService.updateByMapContext(map);
		}else {
			productParts.setBranchId(WebUtils.getCurrBranchId());
			productParts.setCreator(WebUtils.getCurrUserId());
			productParts.setCreated(DateUtil.getSystemDate());
			this.productPartsService.add(productParts);
		}
		return ResultFactory.generateRequestResult(productParts);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateProductParts(String partsId, MapContext mapContext) {
		ProductParts parts=this.productPartsService.findById(partsId);
		if(parts==null){
			return ResultFactory.generateResNotFoundResult();
		}
		Integer productValue=mapContext.getTypedValue("productType",Integer.class);
		Integer partsValue = mapContext.getTypedValue("partsType",Integer.class);
		Integer produceValue = mapContext.getTypedValue("produceType",Integer.class);
		ProductParts productParts=this.productPartsService.findByType(productValue,partsValue,produceValue);
		if(productParts!=null){
			if(!productParts.getId().equals(partsId)) {
				return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_NOT_ALLOWED_REPEAT, AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
			}
		}
		mapContext.put("id",partsId);
		mapContext.put("updated", DateUtil.getSystemDate());
		mapContext.put("updator", WebUtils.getCurrUserId());
		this.productPartsService.updateByMapContext(mapContext);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteProductParts(String partsId) {
		//查询此部件是否被使用
		List<OrderProductParts> orderProductParts=this.orderProductPartsService.findByProductPartsId(partsId);
		if(orderProductParts!=null&&orderProductParts.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RES_BE_IN_USE_10006, AppBeanInjector.i18nUtil.getMessage("BIZ_RES_BE_IN_USE_10006"));
		}
		ProductParts parts=this.productPartsService.findById(partsId);
		String productCode=parts.getProductType().toString();
		String partsCode=parts.getPartsType().toString();
		String produceCode=parts.getProduceType().toString();
		//删除产品部件表信息
		this.productPartsService.deleteById(partsId);
//		//如果品类不被别的资源使用，则删除品类的字典数据
//		String productType="orderProductType";
//		List<ProductPartsDto> productParts=this.productPartsService.findByProductType(Integer.valueOf(productCode),WebUtils.getCurrBranchId());
//		if(productParts==null||productParts.size()==0){//删除字典数据
//			Basecode basecode=this.basecodeService.findByTypeAndCode(productType,productCode);
//			this.basecodeService.deleteById(basecode.getId());
//		}
//		//如果部件不被别的资源使用，则删除部件的字典数据
//		String partsType="produceOrderType";
//		List<ProductPartsDto> partsDtos=this.productPartsService.findByProductType(Integer.valueOf(partsCode),WebUtils.getCurrBranchId());
//		if(partsDtos==null||partsDtos.size()==0) {
//			Basecode basecode = this.basecodeService.findByTypeAndCode(partsType, partsCode);
//			this.basecodeService.deleteById(basecode.getId());
//		}
		//如果生产方式不被别的资源使用，则删除生产方式的字典数据
//		String way="produceOrderWay";
//		List<ProductPartsDto> partsDtos1=this.productPartsService.findByProductType(Integer.valueOf(produceCode),WebUtils.getCurrBranchId());
//		if(partsDtos1==null||partsDtos1.size()==0){
//			Basecode basecode = this.basecodeService.findByTypeAndCode(way, produceCode);
//			this.basecodeService.deleteById(basecode.getId());
//		}
		return ResultFactory.generateSuccessResult();
	}


}
