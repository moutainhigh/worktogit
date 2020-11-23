package com.lwxf.industry4.webapp.controller.admin.factory.dealer;

import io.swagger.annotations.Api;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.facade.admin.factory.dealer.OutsourcingFacade;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/6/1/001 17:10
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/api/f/outsourcing", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
@Api(value = "外协厂家管理",tags = "外协厂家管理")
public class OutsourcingFactoryController {

	@Resource(name = "outsourcingFacade")
	private OutsourcingFacade outsourcingFacade;

}
