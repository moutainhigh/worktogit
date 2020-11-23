package com.lwxf.industry4.webapp.facade.admin.factory.system.impl;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lwxf.industry4.webapp.bizservice.system.OperationsService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.entity.system.Operations;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.admin.factory.system.OperationsFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/1/5/005 15:12
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("operationsFacade")
public class OperationsFacadeImpl extends BaseFacadeImpl implements OperationsFacade {
	@Resource(name = "operationsService")
	private OperationsService operationsService;
	@Override
	public RequestResult findOperationList(MapContext mapContext) {
		return ResultFactory.generateRequestResult(this.operationsService.findListByMapContext(mapContext));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addOperation(Operations operations) {
		//判断名称是否重复
		Operations nameOperations = this.operationsService.findOneByName(operations.getName());
		if (nameOperations!=null){
			Map result = new HashMap();
			result.put(WebConstant.KEY_ENTITY_NAME,AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,result);
		}
		//判断key是否重复
		Operations keyOperations = this.operationsService.findOneByKey(operations.getKey());
		if (keyOperations!=null){
			Map result = new HashMap();
			result.put("key",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,result);
		}
		this.operationsService.add(operations);
		return ResultFactory.generateRequestResult(operations);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteById(String id) {
		Operations operations = this.operationsService.findById(id);
		if (operations==null){
			return ResultFactory.generateSuccessResult();
		}
		this.operationsService.deleteById(id);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateOperations(String id, MapContext mapContext) {
		//判断该按钮是否存在
		if(this.operationsService.findById(id)==null){
			return ResultFactory.generateResNotFoundResult();
		}
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		this.operationsService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult(this.operationsService.findById(id));
	}
}

