package com.lwxf.industry4.webapp.controller.admin.factory.printTable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.printTable.PaymentPrintTableDto;
import com.lwxf.industry4.webapp.facade.admin.factory.finance.FinanceFacade;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/7/11/011 18:45
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/api/f/payments", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
@Api(value = "PaymentPrintTableController", tags = {"pc端后台接口:支出单打印信息管理"})
public class PaymentPrintTableController {

	@Resource(name = "financeFacade")
	private FinanceFacade financeFacade;

	@GetMapping("/print/{id}")
	@ApiOperation(value = "支出单",notes = "支出单",response = PaymentPrintTableDto.class)
	private RequestResult findPaymentInfo(@PathVariable@ApiParam(value = "支付记录主键ID") String id){
		return this.financeFacade.findPaymentInfo(id);
	}

}
